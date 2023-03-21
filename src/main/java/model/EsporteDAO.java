package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class EsporteDAO {
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://127.0.0.1:3306/sportsocial?useTimezone=true&serverTimezone=UTC";
	private String user = "root";
	private String password = "";

	// Método de conexão
	private Connection conectar() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	public ArrayList<Esporte> listarEsportes() {

		ArrayList<Esporte> esportes = new ArrayList<>();
		String read = "select * from esporte ";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read);
			ResultSet rs = pst.executeQuery();
			// O laço sera executado enquanto houver jogadores
			while (rs.next()) {
				// variaveis de apoio que recebem os dados do banco
				String idEsporte= rs.getString(1);
				String nome= rs.getString(2);
				String duracao= rs.getString(3);
				String descricao = rs.getString(4);
				// armazenando no arraylist
				esportes.add(new Esporte(idEsporte,nome , duracao, descricao));
			}
			con.close();
			return esportes;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	public void selecionarEsporte(Esporte esporte) {
		String read2 = "select * from esporte where idEsporte= ?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read2);
			pst.setString(1, esporte.getIdEsporte());
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				// Setar as variaveis javabeans
				esporte.setIdEsporte(rs.getString(1));
				esporte.setNome(rs.getString(2));
				esporte.setDuracao(rs.getString(3));
				esporte.setDescricao(rs.getString(4));
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// Editar o Jogador
	public void alterarEsporte(Esporte esporte) {
		String create = "update esporte set nome=?, duracao=?, descricao=? where idEsporte=?";

		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(create);
			pst.setString(1, esporte.getNome());
			pst.setString(2, esporte.getDuracao());
			pst.setString(3, esporte.getDescricao());

			// id que vai ser usado para selecionar o jogador
			pst.setString(4, esporte.getIdEsporte());
			// executando o update e finalizando a conexão
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	public void verTimes(Esporte esporte) {
		ArrayList<Time> times = new ArrayList<Time>();
		String read = "select * from `esporte` as e inner join `time` as t on e.idEsporte = ? and t.idEsporte = ? order by t.nome";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read);
			pst.setString(1, esporte.getIdEsporte());
			pst.setString(2, esporte.getIdEsporte());
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				String idTime = rs.getString("idTime");
				String Nome = rs.getString(6);
				double receita= rs.getDouble("receita");
				// Setar as variaveis javabeans
				esporte.setIdEsporte(rs.getString(1));
				esporte.setNome(rs.getString(2));
				
				times.add(new Time(idTime, Nome, receita));

			}
			esporte.setTimes(times);
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
