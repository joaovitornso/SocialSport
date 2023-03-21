package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class LigaDAO {

	// Parâmetros de conexão
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://127.0.0.1:3306/sportsocial?useTimezone=true&serverTimezone=UTC";
	private String user = "root";
	private String password = "";

	// Método de conexão
	private Connection conectar(){
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
	
	public void inserirLiga(Liga liga) {
		String create = "INSERT INTO `liga`(`nome`, `medalhas`, `trofeu`) VALUES (?, ?, ?);";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(create);
			pst.setString(1, liga.getNome());
			pst.setString(2, liga.getMedalhas());
			pst.setString(3, liga.getTrofeu());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	

	public void atualizarLiga(Liga liga) {
		String update = "UPDATE `liga` SET `medalhas` = ?, `nome` = ?, `trofeu` = ? WHERE `idCampeonato` = ? ;";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(update);
			pst.setString(1, liga.getMedalhas());
			pst.setString(2, liga.getNome());
			pst.setString(3, liga.getTrofeu());
			pst.setInt(4, liga.getId());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void deletarLiga(int idLiga) {
		String delete = "DELETE FROM `liga` WHERE `idLiga` = ? ;";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(delete);
			pst.setInt(1, idLiga);
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public ArrayList<Liga> listarLigas() {
		ArrayList<Liga> listaLigas= new ArrayList<>();
		String read = "SELECT * from `liga`";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read);
			ResultSet rst = pst.executeQuery();
			while (rst.next()) {
				int idLiga = rst.getInt("idLIga");
				String nome = rst.getString("nome");
				String medalhas = rst.getString("medalhas");
				String trofeu = rst.getString("trofeu");
				listaLigas.add(new Liga(idLiga, nome, medalhas, trofeu));
			}
			con.close();
			return listaLigas;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	public void selecionarCompeticao(Liga liga) {
		String select = "SELECT * FROM `liga` WHERE `idLiga` = ? ;";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(select);
			pst.setInt(1, liga.getId());
			ResultSet rst = pst.executeQuery();
			while (rst.next()) {
				liga.setId(rst.getInt("idLiga"));
				liga.setNome(rst.getString("nome"));
				liga.setMedalhas(rst.getString("medalhas"));
				liga.setTrofeu(rst.getString("trofeu"));
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	

	public void selecionarTimesCompeticao(Liga liga) {
		ArrayList<Time> times = new ArrayList<Time>();

		String select = "select * from `liga` as l inner join `time` as t on l.idLiga = ? and t.idLiga= ?";

		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(select);
			pst.setInt(1, liga.getId());
			pst.setInt(2, liga.getId());
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				String nomeTime = rs.getString(6);
				String idTime = rs.getString("idTime");
				String idEsporte = rs.getString("idEsporte");
				int vitorias = rs.getInt("vitorias");
				int derrotas = rs.getInt("derrotas");
				int empates = rs.getInt("empates");
				// Setar as variaveis javabeans
				liga.setId(rs.getInt("idLiga"));
				liga.setNome(rs.getString(2));

				times.add(new Time(idTime, nomeTime, idEsporte, vitorias, derrotas, empates));
			}
			liga.setTimes(times);
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	public void selecionarPartidas(Liga liga) {
		ArrayList<Partida> partidas = new ArrayList<Partida>();

		String select = "select * from `liga` as c inner join `partida` as p on c.idLiga = ? and p.idLiga = ?";

		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(select);
			pst.setInt(1, liga.getId());
			pst.setInt(2, liga.getId());
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				String idPartida = rs.getString("idPartida");
				String data = rs.getString("data");
				String horario = rs.getString("horario");
				int pontosTime1 = rs.getInt("pontosTime1");
				int pontosTime2 = rs.getInt("pontosTime2");
				// Setar as variaveis javabeans
				liga.setId(rs.getInt("idLiga"));
				liga.setNome(rs.getString(2));

				partidas.add(new Partida(idPartida, data,horario,pontosTime1,pontosTime2));
			}
			liga.setPartidas(partidas);
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
}
