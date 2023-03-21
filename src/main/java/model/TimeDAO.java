package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class TimeDAO {
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
	public void inserirTime(Time time) {
		String create = "insert into time (nome,qntJogadores,idEsporte) values (?,?,?)";
		try {
			// abrir a conexão
			Connection con = conectar();
			// Preparar a query para execução no banco de dados
			PreparedStatement pst = con.prepareStatement(create);
			// Substituir os parâmetros (?) pelo conteúdo das variáveis Java Beans

			pst.setString(1, time.getNome());
			pst.setInt(2, time.getQntJogadores());
			pst.setString(3, time.getIdEsporte());
			// Executar a query
			pst.executeUpdate();
			// encerrar a conexão com o banco
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// CRUD READ
	public ArrayList<Time> listarTimes() {
		// Criando um objeto para acessar a classe JavaBeans
		ArrayList<Time> times = new ArrayList<>();
		String read = "select * from time order by nome";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read);
			ResultSet rs = pst.executeQuery();

			// O laço sera executado enquanto houver jogadores

			while (rs.next()) {
				// variaveis de apoio que recebem os dados do banco
				String idTime = rs.getString(1);
				String nome = rs.getString(2);
				int qntJogadores = rs.getInt("qntJogadores");
				String idEsporte = rs.getString("idEsporte");
				int idCampeonato = rs.getInt("idCampeonato");
				int vitorias = rs.getInt("vitorias");
				int derrotas = rs.getInt("derrotas");
				int empates = rs.getInt("empates");
				int idLiga = rs.getInt("idLiga");
				double receita = rs.getDouble("receita");
				// armazenando no arraylist
				times.add(new Time(idTime, nome, qntJogadores,idEsporte, idCampeonato, vitorias, derrotas, empates,idLiga,receita));
			}
			con.close();
			return times;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	// CRUD UPDATE 
	//Selecionar Time
	public void selecionarTime(Time time) {
		String read2 = "select * from time where idTime = ?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read2);
			pst.setString(1, time.getIdTime());
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				// Setar as variaveis javabeans
				time.setIdTime(rs.getString(1));
				time.setNome(rs.getString(2));
				time.setQntJogadores(rs.getInt("qntJogadores"));
				time.setIdEsporte(rs.getString("idEsporte"));
				time.setVitorias(rs.getInt("vitorias"));
				time.setDerrotas(rs.getInt("derrotas"));
				time.setEmpates(rs.getInt("empates"));
				time.setIdCampeonato(rs.getInt("idCampeonato"));
				time.setIdLiga(rs.getInt("idLiga"));
				time.setReceita(rs.getDouble("receita"));
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// Editar o Time
	public void alterarTime(Time time) {
		String create = "update time set nome=?, qntJogadores=? where idTime=?";

		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(create);
			pst.setString(1, time.getNome());
			pst.setInt(2, time.getQntJogadores());
			// id que vai ser usado para selecionar o jogador
			pst.setString(3, time.getIdTime());
			// executando o update e finalizando a conexão
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	//CRUD DELETE
	public void deletarTime(Time time) {
		String delete = "delete from time where idTime=?";
		
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(delete);
			pst.setString(1, time.getIdTime());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	//ver os jogadores do time
	public void verTime(Time time) {
		ArrayList<Jogador> jogadores = new ArrayList<Jogador>();
		String read = "select * from `time` as t inner join jogador as j on t.idTime = ? and j.idTime = ? order by j.nome";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read);
			pst.setString(1, time.getIdTime());
			pst.setString(2, time.getIdTime());
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				String idJogador = rs.getString("idjogador");
				String nomeJogador = rs.getString(12);
				String nmrCamisa = rs.getString("nmrCamisa");
				String idade = rs.getString("idade");
				String idTime = rs.getString("idTime");
				int nmrPunicoes = rs.getInt("nmrPunicoes");
				// Setar as variaveis javabeans
				time.setIdTime(rs.getString(1));
				time.setNome(rs.getString(2));
				
				jogadores.add(new Jogador(idJogador, nomeJogador, nmrCamisa, idade, idTime, nmrPunicoes));

			}
			time.setJogadores(jogadores);
			read = "select count(qntJogadores)as quantidade from `time` as t inner join jogador as j on t.idTime = ? and j.idTime = ? ";
			pst = con.prepareStatement(read);
			pst.setString(1, time.getIdTime());
			pst.setString(2, time.getIdTime());
			rs = pst.executeQuery();
			
			while(rs.next()) {
				time.setQntJogadores(rs.getInt(1));
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	public void qntdJogadoresTime(Time time) {
		String read = "select count(qntJogadores) as quantidade from `time` as t inner join jogador as j on t.idTime = ? and j.idTime = ? ";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read);
			pst.setString(1, time.getIdTime());
			pst.setString(2, time.getIdTime());
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				time.setQntJogadores(rs.getInt(1));
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	//ganhar partida
	public void ganharPartida(Time time) {
		String update = "update time set vitorias=? where idTime=?";
		
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(update);
			pst.setInt(1, time.getVitorias());
			// id que vai ser usado para selecionar o jogador
			pst.setString(2, time.getIdTime());
			// executando o update e finalizando a conexão
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	//perder partida
	public void perderPartida(Time time) {
		String update = "update time set derrotas=? where idTime=?";
		
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(update);
			pst.setInt(1, time.getDerrotas());
			// id que vai ser usado para selecionar o jogador
			pst.setString(2, time.getIdTime());
			// executando o update e finalizando a conexão
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	//empatar partida
	public void empatarPartida(Time time) {
		String update = "update time set empates=? where idTime=?";
		
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(update);
			pst.setInt(1, time.getEmpates());
			// id que vai ser usado para selecionar o jogador
			pst.setString(2, time.getIdTime());
			// executando o update e finalizando a conexão
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	// selecionar o id de campeonato 
	public void selecionarCampeonato(Time time) {
		String select = "select (idCampeonato) from time where idTime=?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(select);
			pst.setString(1, time.getIdTime());
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				// Setar as variaveis javabeans
				time.setIdCampeonato(rs.getInt("idCampeonato"));
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	//setar o id do campeonato em time 
	public void inscreverCampeonato(Time time) {
		String update = "update time set idCampeonato = ? where idTime = ?";
	
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(update);
			pst.setInt(1, time.getIdCampeonato());
			pst.setString(2,time.getIdTime());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	public void inscreverLiga(Time time) {
		String update = "update time set idLiga = ? where idTime = ?";
	
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(update);
			pst.setInt(1, time.getIdLiga());
			pst.setString(2,time.getIdTime());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	public void receberPremio(Time time) {
		String update = "update time set receita = ? where idTime = ?";
		
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(update);
			pst.setDouble(1, time.getReceita());
			pst.setString(2,time.getIdTime());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	
}
