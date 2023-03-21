package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class PartidaDAO {
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
	public void inserirPartida(Partida partida) {
		String create = "insert into partida (data,horario) values (?,?)";
		try {
			// abrir a conexão
			Connection con = conectar();
			// Preparar a query para execução no banco de dados
			PreparedStatement pst = con.prepareStatement(create);
			// Substituir os parâmetros (?) pelo conteúdo das variáveis Java Beans

			pst.setString(1, partida.getData());
			pst.setString(2, partida.getHorario());
			// Executar a query
			pst.executeUpdate();
			// encerrar a conexão com o banco
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	public void inserirPartidaCompeticao(Partida partida) {
		String create = "insert into partida (data,horario,idEsporte,idLocal) values (?,?,?,?)";
		try {
			// abrir a conexão
			Connection con = conectar();
			// Preparar a query para execução no banco de dados
			PreparedStatement pst = con.prepareStatement(create);
			// Substituir os parâmetros (?) pelo conteúdo das variáveis Java Beans

			pst.setString(1, partida.getData());
			pst.setString(2, partida.getHorario());
			pst.setString(3, partida.getEsporte().getIdEsporte());
			pst.setString(4, partida.getLocal().getIdLocal());
			// Executar a query
			pst.executeUpdate();
			// encerrar a conexão com o banco
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// CRUD READ
	public ArrayList<Partida> listarPartidas() {

		ArrayList<Partida> partidas = new ArrayList<>();
		String read = "select * from partida ";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read);
			ResultSet rs = pst.executeQuery();
			// O laço sera executado enquanto houver jogadores
			while (rs.next()) {
				// variaveis de apoio que recebem os dados do banco
				String idPartida= rs.getString(1);
				String data= rs.getString(2);
				String horario= rs.getString(3);
				// armazenando no arraylist
				partidas.add(new Partida(idPartida,data , horario));
			}
			con.close();
			return partidas;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	// CRUD UPDATE
	// Selecionar o Jogador
	public void selecionarPartida(Partida partida) {
		String read2 = "select * from partida where idPartida= ?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read2);
			pst.setString(1, partida.getIdPartida());
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				// Setar as variaveis javabeans
				partida.setIdPartida(rs.getString("idPartida"));
				partida.setData(rs.getString("data"));
				partida.setHorario(rs.getString("horario"));
				partida.setPontuacao(rs.getString(4));
				partida.setIdCampeonato(rs.getInt("idCampeonato"));
				partida.setIdLiga(rs.getInt("idLiga"));
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// Editar o Jogador
	public void alterarPartida(Partida partida) {
		String create = "update partida set data=?, horario=? where idPartida=?";

		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(create);
			pst.setString(1, partida.getData());
			pst.setString(2, partida.getHorario());

			// id que vai ser usado para selecionar o jogador
			pst.setString(3, partida.getIdPartida());
			// executando o update e finalizando a conexão
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// CRUD DELETE
	public void deletarPartida(Partida partida) {
		String delete = "delete from partida where idPartida=?";

		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(delete);
			pst.setString(1, partida.getIdPartida());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void atualizarTime1(Partida partida) {
		String update = "update partida set idTime = ? where idPartida = ?";
	
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(update);
			pst.setString(1, partida.getTime1().getIdTime());
			pst.setString(2,partida.getIdPartida());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	public void atualizarTime2(Partida partida) {
		String update = "update partida set idTime2 = ? where idPartida = ?";
	
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(update);
			pst.setString(1, partida.getTime2().getIdTime());
			pst.setString(2,partida.getIdPartida());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void atualizarEsporte(Partida partida) {
		String update = "update partida set idEsporte = ? where idPartida = ?";
	
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(update);
			pst.setString(1, partida.getEsporte().getIdEsporte());
			pst.setString(2,partida.getIdPartida());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void atualizarLocal(Partida partida) {
		String update = "update partida set idLocal = ? where idPartida = ?";
	
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(update);
			pst.setString(1, partida.getLocal().getIdLocal());
			pst.setString(2,partida.getIdPartida());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	public void atualizarCampeoanato(Partida partida) {
		String update = "update partida set idCampeonato = ? where idPartida = ?";
	
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(update);
			pst.setInt(1, partida.getIdCampeonato());
			pst.setString(2,partida.getIdPartida());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	public void atualizarLiga(Partida partida) {
		String update = "update partida set idLiga = ? where idPartida = ?";
	
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(update);
			pst.setInt(1, partida.getIdLiga());
			pst.setString(2,partida.getIdPartida());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void selecionarTime1(Partida partida) {
		String select = "select (idTime) from partida where idPartida = ?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(select);
			pst.setString(1, partida.getIdPartida());
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				// Setar as variaveis javabeans
				partida.getTime1().setIdTime(rs.getString("idTime"));
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	public void selecionarTime2(Partida partida) {
		String select = "select (idTime2) from partida where idPartida = ?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(select);
			pst.setString(1, partida.getIdPartida());
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				// Setar as variaveis javabeans
				partida.getTime2().setIdTime(rs.getString("idTime2"));
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	public void selecionarEsporte(Partida partida) {
		String select = "select (idEsporte) from partida where idPartida = ?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(select);
			pst.setString(1, partida.getIdPartida());
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				// Setar as variaveis javabeans
				partida.getEsporte().setIdEsporte(rs.getString("idEsporte"));
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	public void selecionarLocal(Partida partida) {
		String select = "select (idLocal) from partida where idPartida = ?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(select);
			pst.setString(1, partida.getIdPartida());
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				// Setar as variaveis javabeans
				partida.getLocal().setIdLocal(rs.getString("idLocal"));
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	public void recuperarId(Partida partida) {
		String select = "select (idPartida) from partida where data = ?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(select);
			pst.setString(1, partida.getData());
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				// Setar as variaveis javabeans
				partida.setIdPartida(rs.getString("idPartida"));
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	public void atualizarPontuacao(Partida partida) {
		String update = "update partida set pontosTime1=?, pontosTime2=? where idPartida = ?";
		
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(update);
			pst.setInt(1, partida.getPontosTime1());
			pst.setInt(2,partida.getPontosTime2());
			pst.setString(3,partida.getIdPartida());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	
	public void organizarPartida(Partida partida) {
		String selectTime = "select * from `partida` as p inner join `time` as t on p.idTime = ? and t.idTime = ?";
		String selectEsporte = "select * from `partida` as p inner join `esporte` as e on p.idEsporte = ? and e.idEsporte = ?";
		String selectLocal = "select * from `partida` as p inner join `local` as l on p.idLocal = ? and l.idLocal = ?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(selectTime);
			pst.setString(1, partida.getTime1().getIdTime());
			pst.setString(2, partida.getTime1().getIdTime());
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				partida.getTime1().setIdTime(rs.getString(11));
				partida.getTime1().setNome(rs.getString(12));
				partida.getTime1().setQntJogadores(rs.getInt(13));
				partida.getTime1().setIdEsporte(rs.getString(14));
				partida.getTime1().setVitorias(rs.getInt(15));
				partida.getTime1().setDerrotas(rs.getInt(16));
				partida.getTime1().setEmpates(rs.getInt(17));
			}
			pst = con.prepareStatement(selectTime);
			pst.setString(1, partida.getTime2().getIdTime());
			pst.setString(2, partida.getTime2().getIdTime());
			rs = pst.executeQuery();
			
			while(rs.next()) {
				partida.getTime2().setIdTime(rs.getString(11));
				partida.getTime2().setNome(rs.getString(12));
				partida.getTime2().setQntJogadores(rs.getInt(13));
				partida.getTime2().setIdEsporte(rs.getString(14));
				partida.getTime2().setVitorias(rs.getInt(15));
				partida.getTime2().setDerrotas(rs.getInt(16));
				partida.getTime2().setEmpates(rs.getInt(17));
			}
			pst = con.prepareStatement(selectEsporte);
			pst.setString(1, partida.getEsporte().getIdEsporte());
			pst.setString(2, partida.getEsporte().getIdEsporte());
			rs = pst.executeQuery();
			
			while(rs.next()) {
				partida.getEsporte().setIdEsporte(rs.getString(11));
				partida.getEsporte().setNome(rs.getString(12));
				partida.getEsporte().setDuracao(rs.getString(13));
				partida.getEsporte().setDescricao(rs.getString(14));
			}
			pst = con.prepareStatement(selectLocal);
			pst.setString(1, partida.getLocal().getIdLocal());
			pst.setString(2, partida.getLocal().getIdLocal());
			rs = pst.executeQuery();
			
			while(rs.next()) {
				partida.getLocal().setIdLocal(rs.getString(11));
				partida.getLocal().setCidade(rs.getString(12));
				partida.getLocal().setBairro(rs.getString(13));
				partida.getLocal().setRua(rs.getString(14));
				partida.getLocal().setTipo(rs.getString(15));
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
