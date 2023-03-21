package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class LocalDAO {
	// Modulo de conexão
	// Parâmetros de conexão
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

	// CRUD CREATE
	public void inserirLocal(Local local) {
		String create = "insert into local (rua,bairro,cidade,tipo) values (?,?,?,?)";
		try {
			// abrir a conexão
			Connection con = conectar();
			// Preparar a query para execução no banco de dados
			PreparedStatement pst = con.prepareStatement(create);
			// Substituir os parâmetros (?) pelo conteúdo das variáveis Java Beans

			pst.setString(1, local.getRua());
			pst.setString(2, local.getBairro());
			pst.setString(3, local.getCidade());
			pst.setString(4, local.getTipo());
			// Executar a query
			pst.executeUpdate();
			// encerrar a conexão com o banco
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// CRUD READ
	public ArrayList<Local> listarLocais() {
		// Criando um objeto para acessar a classe JavaBeans
		ArrayList<Local> locais = new ArrayList<>();
		String read = "select * from local ";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read);
			ResultSet rs = pst.executeQuery();
			// O laço sera executado enquanto houver jogadores
			while (rs.next()) {
				// variaveis de apoio que recebem os dados do banco
				String idLocal= rs.getString(1);
				String rua= rs.getString(2);
				String bairro= rs.getString(3);
				String cidade = rs.getString(4);
				String tipo = rs.getString(5);
				// armazenando no arraylist
				locais.add(new Local(idLocal,rua , bairro, cidade, tipo));
			}
			con.close();
			return locais;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	// CRUD UPDATE
	// Selecionar o Jogador
	public void selecionarLocal(Local local) {
		String read2 = "select * from local where idLocal= ?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read2);
			pst.setString(1, local.getIdLocal());
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				// Setar as variaveis javabeans
				local.setIdLocal(rs.getString(1));
				local.setRua(rs.getString(2));
				local.setBairro(rs.getString(3));
				local.setCidade(rs.getString(4));
				local.setTipo(rs.getString(5));
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// Editar o Jogador
	public void alterarLocal(Local local) {
		String create = "update local set rua=?, bairro=?, cidade=?, tipo=? where idLocal=?";

		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(create);
			pst.setString(1, local.getRua());
			pst.setString(2, local.getBairro());
			pst.setString(3, local.getCidade());
			pst.setString(4, local.getTipo());

			// id que vai ser usado para selecionar o jogador
			pst.setString(5, local.getIdLocal());
			// executando o update e finalizando a conexão
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// CRUD DELETE
	public void deletarLocal(Local local) {
		String delete = "delete from local where idLocal=?";

		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(delete);
			pst.setString(1, local.getIdLocal());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
