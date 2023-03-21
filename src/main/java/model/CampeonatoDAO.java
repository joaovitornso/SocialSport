package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CampeonatoDAO {
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

	public void inserirCampeonato(Campeonato campeonato) {
		String create = "INSERT INTO `campeonato`(`nome`, `medalhas`) VALUES (?, ?)";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(create);
			pst.setString(1, campeonato.getNome());
			pst.setString(2, campeonato.getMedalhas());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void atualizarCampeonato(Campeonato campeonato) {
		String update = "UPDATE `campeonato` SET `medalhas` = ?, `nome` = ? WHERE `idCampeonato` = ? ;";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(update);
			pst.setString(1, campeonato.getMedalhas());
			pst.setString(2, campeonato.getNome());
			pst.setInt(3, campeonato.getId());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void deletarCampeonato(int idCampeonato) {
		String delete = "DELETE FROM `campeonato` WHERE `idCampeonato` = ? ;";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(delete);
			pst.setInt(1, idCampeonato);
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public ArrayList<Campeonato> listarCampeonatos() {
		ArrayList<Campeonato> listaCampeonatos = new ArrayList<>();
		String read = "SELECT * from `campeonato`";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read);
			ResultSet rst = pst.executeQuery();
			while (rst.next()) {
				int idCampeonato = rst.getInt("idCampeonato");
				String nome = rst.getString("nome");
				String medalhas = rst.getString("medalhas");
				listaCampeonatos.add(new Campeonato(idCampeonato, nome, medalhas));
			}
			con.close();
			return listaCampeonatos;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	public void selecionarCompeticao(Campeonato campeonato) {
		String select = "SELECT * FROM `campeonato` WHERE `idCampeonato` = ? ;";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(select);
			pst.setInt(1, campeonato.getId());
			ResultSet rst = pst.executeQuery();
			while (rst.next()) {
				campeonato.setId(rst.getInt("idCampeonato"));
				campeonato.setNome(rst.getString("nome"));
				campeonato.setMedalhas(rst.getString("medalhas"));
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	public void selecionarPartida(Campeonato campeonato) {
		String select = "SELECT  FROM `campeonato` WHERE `idCampeonato` = ? ;";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(select);
			pst.setInt(1, campeonato.getId());
			ResultSet rst = pst.executeQuery();
			while (rst.next()) {
				campeonato.setId(rst.getInt("idCampeonato"));
				campeonato.setNome(rst.getString("nome"));
				campeonato.setMedalhas(rst.getString("medalhas"));
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void atualizarEsporte(Campeonato campeonato) {
		String update = "update campeonato set idEsporte = ? where idCampeonato = ?";

		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(update);
			pst.setString(1, campeonato.getEsporte().getIdEsporte());
			pst.setInt(2, campeonato.getId());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void selecionarTimesCompeticao(Campeonato campeonato) {
		ArrayList<Time> times = new ArrayList<Time>();

		String select = "select * from `campeonato` as c inner join `time` as t on c.idCampeonato = ? and t.idCampeonato = ?";

		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(select);
			pst.setInt(1, campeonato.getId());
			pst.setInt(2, campeonato.getId());
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				String nomeTime = rs.getString(7);
				String idTime = rs.getString("idTime");
				String idEsporte = rs.getString("idEsporte");
				int vitorias = rs.getInt("vitorias");
				int derrotas = rs.getInt("derrotas");
				int empates = rs.getInt("empates");
				// Setar as variaveis javabeans
				campeonato.setId(rs.getInt("idCampeonato"));
				campeonato.setNome(rs.getString(2));

				times.add(new Time(idTime, nomeTime, idEsporte, vitorias, derrotas, empates));
			}
			campeonato.setTimes(times);
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void selecionarPartidas(Campeonato campeonato) {
		ArrayList<Partida> partidas = new ArrayList<Partida>();

		String select = "select * from `campeonato` as c inner join `partida` as p on c.idCampeonato = ? and p.idCampeonato = ?";

		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(select);
			pst.setInt(1, campeonato.getId());
			pst.setInt(2, campeonato.getId());
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				String idPartida = rs.getString("idPartida");
				String data = rs.getString("data");
				String horario = rs.getString("horario");
				int pontosTime1 = rs.getInt("pontosTime1");
				int pontosTime2 = rs.getInt("pontosTime2");
				// Setar as variaveis javabeans
				campeonato.setId(rs.getInt("idCampeonato"));
				campeonato.setNome(rs.getString(2));

				partidas.add(new Partida(idPartida, data,horario,pontosTime1,pontosTime2));
			}
			campeonato.setPartidas(partidas);
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	public void atualizarLocal(Campeonato campeonato) {
		String update = "update campeonato set idLocal = ? where idCampeonato= ?";

		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(update);
			pst.setString(1, campeonato.getLocal().getIdLocal());
			pst.setInt(2, campeonato.getId());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	// Ver partidas do campeonato
//	public void verCampeonato(Campeonato campeonato) {
//		ArrayList<Partida> partidas = new ArrayList<Partida>();
//		String read = "select * from `campeonato` as c inner join partida as p on c.idCampeonato = ? and p.idCampeonato = ? order by p.nome";
//		try {
//			Connection con = conectar();
//			PreparedStatement pst = con.prepareStatement(read);
//			pst.setInt(1, campeonato.getIdCampeonato());
//			pst.setInt(2, campeonato.getIdCampeonato());
//			ResultSet rs = pst.executeQuery();
//			
//			while (rs.next()) {
//				String idPartida = rs.getString("idPartida");
//				String data = rs.getString("data");
//				String horario = rs.getString("horario");
//				String pontuacao = rs.getString("pontuacao");
//				int pontosTime1 = rs.getInt("pontosTime1");
//				int pontosTime2 = rs.getInt("pontosTime2");
//				String idTime = rs.getString("idTime");
//				String idTime2 = rs.getString("idTime2");
//				String idEsporte = rs.getString("idEsporte");
//				String idLocal = rs.getString("idLocal");
//				int idCampeonato = rs.getInt("idCampeonato");
//				
////				String nomeCampeonato = rs.getString("nome");
////				String medalhas = rs.getString("medalhas");
////				int idLocal = rs.getInt("idLocal");
////				int idEsporte= rs.getInt("idEsporte");
//				
//				// Setar as variaveis javabeans
//				
//				campeonato.setIdCampeonato(rs.getInt("idCampeonato"));
//				campeonato.setNome(rs.getString("nome"));
//				campeonato.setMedalhas(rs.getString("medalhas"));
//				campeonato.getLocal().setIdLocal(rs.getString("idLocal"));
//				campeonato.getEsporte().setId(rs.getString("idEsporte"));
//				
//				partidas.add(new Partida(idPartida, data, horario, pontuacao, pontosTime1, pontosTime2, idTime,idTime2, idEsporte, idLocal, idCampeonato));
//
//			}
//			campeonato.setPartidas(partidas);
//			
//			read = "select count(qntJogadores)as quantidade from `time` as t inner join jogador as j on t.idTime = ? and j.idTime = ? ";
//			pst = con.prepareStatement(read);
//			pst.setString(1, time.getIdTime());
//			pst.setString(2, time.getIdTime());
//			rs = pst.executeQuery();
//			
//			while(rs.next()) {
////				time.setQntJogadores(rs.getString(1));
//				time.setQntJogadores(rs.getString(1));
//			}
//			con.close();
//		} catch (Exception e) {
//			System.out.println(e);
//		}
//	}

}