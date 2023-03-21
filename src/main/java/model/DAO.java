package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DAO {
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
	public void inserirJogador(Jogador jogador) {
		String create = "insert into jogador (nome,nmrCamisa,idade) values (?,?,?)";
		try {
			// abrir a conexão
			Connection con = conectar();
			// Preparar a query para execução no banco de dados
			PreparedStatement pst = con.prepareStatement(create);
			// Substituir os parâmetros (?) pelo conteúdo das variáveis Java Beans

			pst.setString(1, jogador.getNome());
			pst.setString(2, jogador.getNmrCamisa());
			pst.setString(3, jogador.getIdade());
			// Executar a query
			pst.executeUpdate();
			// encerrar a conexão com o banco
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// CRUD READ
	public ArrayList<Jogador> listarJogadores() {
		// Criando um objeto para acessar a classe JavaBeans
		ArrayList<Jogador> jogadores = new ArrayList<>();
		String read = "select * from jogador order by nome";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read);
			ResultSet rs = pst.executeQuery();
			// O laço sera executado enquanto houver jogadores
			while (rs.next()) {
				// variaveis de apoio que recebem os dados do banco
				String idjogador = rs.getString(1);
				String nome = rs.getString(2);
				String nmrCamisa = rs.getString("nmrCamisa");
				String idade = rs.getString("idade");
				String idTime = rs.getString("idTime");
				int nmrPunicoes = rs.getInt("nmrPunicoes");
				// armazenando no arraylist
				jogadores.add(new Jogador(idjogador, nome, nmrCamisa, idade, idTime, nmrPunicoes));
			}
			con.close();
			return jogadores;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	// CRUD UPDATE
	//Selecionar o Jogador
	public void selecionarJogador(Jogador jogador) {
		String read2 = "select * from jogador where idjogador = ?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read2);
			pst.setString(1, jogador.getIdjogador());
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				//Setar as variaveis javabeans
				jogador.setIdjogador(rs.getString("idjogador"));
				jogador.setNome(rs.getString("nome"));
				jogador.setNmrCamisa(rs.getString("nmrCamisa"));
				jogador.setIdade(rs.getString("idade"));
				jogador.setIdTime(rs.getString("idTime"));
				jogador.setNmrPunicoes(rs.getInt("nmrPunicoes"));
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	//Editar o Jogador
	public void alterarJogador(Jogador jogador) {
		String create = "update jogador set nome=?, nmrCamisa=?, idade=? where idjogador=?";
		
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(create);
			pst.setString(1, jogador.getNome());
			pst.setString(2, jogador.getNmrCamisa());
			pst.setString(3, jogador.getIdade());
			
			//id que vai ser usado para selecionar o jogador
			pst.setString(4, jogador.getIdjogador());
			//executando o update e finalizando a conexão
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	//CRUD DELETE
	public void deletarJogador(Jogador jogador) {
		String delete = "delete from jogador where idjogador=?";
		
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(delete);
			pst.setString(1, jogador.getIdjogador());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void alocarJogador(Jogador jogador) {
		String alocar = "update jogador set idTime = ? where idjogador = ?";
		
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(alocar);
			pst.setString(1, jogador.getIdTime());
			pst.setString(2,jogador.getIdjogador());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void aplicarPunicao(Jogador jogador){
		String update = "update jogador set nmrPunicoes=? where idjogador=?";
		
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(update);
			pst.setInt(1, jogador.getNmrPunicoes());
			
			//id que vai ser usado para selecionar o jogador
			pst.setString(2, jogador.getIdjogador());
			//executando o update e finalizando a conexão
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	public void expulsarJogador(Jogador jogador) {
		String update = "update jogador set idTime=null where idJogador=?";
		
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(update);
			pst.setString(1, jogador.getIdjogador());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
}
