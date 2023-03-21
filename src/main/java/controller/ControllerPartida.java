package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Campeonato;
import model.Esporte;
import model.EsporteDAO;
import model.Jogador;
import model.Local;
import model.LocalDAO;
import model.Partida;
import model.PartidaDAO;
import model.Time;
import model.TimeDAO;

@WebServlet(urlPatterns = { "/partida", "/insertPartida", "/selectPartida", "/updatePartida", "/deletePartida",
		"/setarEsporte", "/setarOrganizacao", "/selecionarTimes", "/escalarJogadores", "/validar", "/organizarPartida",
		"/contagemGols" })
public class ControllerPartida extends HttpServlet {
	private static final long serialVersionUID = 1L;

	PartidaDAO dao = new PartidaDAO();
	Partida partida = new Partida();
	EsporteDAO esporteDao = new EsporteDAO();
	TimeDAO timeDao = new TimeDAO();
	LocalDAO localDao = new LocalDAO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getServletPath();
		System.out.println(action);

		if (action.equals("/partida")) {
			partidas(request, response);
		} else if (action.equals("/insertPartida")) {
			novaPartida(request, response);
		} else if (action.equals("/selectPartida")) {
			listarPartidas(request, response);
		} else if (action.equals("/updatePartida")) {
			editarPartida(request, response);
		} else if (action.equals("/deletePartida")) {
			deletarPartida(request, response);
		} else if (action.equals("/setarEsporte")) {
			setarEsporte(request, response);
		} else if (action.equals("/setarOrganizacao")) {
			setarOrganizacao(request, response);
		} else if (action.equals("/selecionarTimes")) {
			selecionarTimes(request, response);
		} else if (action.equals("/escalarJogadores")) {
			escalarJogadores(request, response);
		} else if (action.equals("/validar")) {
			validarEscalacao(request, response);
		} else if (action.equals("/organizarPartida")) {
			organizarPartida(request, response);
		} else if (action.equals("/contagemGols")) {
			contagemGols(request, response);
		} else {
			response.sendRedirect("index.html");
		}
	}

	protected void partidas(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ArrayList<Partida> lista = dao.listarPartidas();

		request.setAttribute("partidas", lista);
		RequestDispatcher rd = request.getRequestDispatcher("jsp/partida.jsp");
		rd.forward(request, response);// encaminha o objeto lista ao documento partida.jsp
	}

	// Novo partida
	protected void novaPartida(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		partida.novaPartida(request.getParameter("data"), request.getParameter("horario"));
		response.sendRedirect("partida");
	}

	// Editar jogador
	protected void listarPartidas(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Recebimento do id do partida que será editado
		String idPartida = request.getParameter("idPartida");
		// Setar a variável JavaBeans
		partida.setIdPartida(idPartida);
		// Executar o método selecionarJogador
		dao.selecionarPartida(partida);

		// Setar os atributos do formulário com o conteúdo JavaBeans
		request.setAttribute("idPartida", partida.getIdPartida());
		request.setAttribute("data", partida.getData());
		request.setAttribute("horario", partida.getHorario());
		request.setAttribute("pontuacao", partida.getPontuacao());

		// Ecaminhar ao documento editar.jsp
		RequestDispatcher rd = request.getRequestDispatcher("jsp/editarPartida.jsp");
		rd.forward(request, response);
	}

	protected void editarPartida(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Setar as variáveis JavaBeans
		partida.setIdPartida(request.getParameter("idPartida"));
		partida.setData(request.getParameter("data"));
		partida.setHorario(request.getParameter("horario"));
		partida.setPontuacao(request.getParameter("pontuacao"));

		dao.alterarPartida(partida);
		// redirecionar para o documento jogador.jsp (atualizando as alterações)
		response.sendRedirect("partida");
	}

	protected void deletarPartida(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Recebimento do id do time a ser excluido (validado,js)
		String idPartida = request.getParameter("idPartida");

		partida.setIdPartida(idPartida);

		dao.deletarPartida(partida);

		response.sendRedirect("partida");
	}

	protected void setarEsporte(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idPartida = (request.getParameter("idPartida"));

		ArrayList<Esporte> listaEsporte = esporteDao.listarEsportes();

		ArrayList<Local> listaLocais = localDao.listarLocais();

		request.setAttribute("listaEsportes", listaEsporte);
		request.setAttribute("listaLocal", listaLocais);
		request.setAttribute("idPartida", idPartida);
		RequestDispatcher rd = request.getRequestDispatcher("jsp/setarEsporteLocal.jsp");
		rd.forward(request, response);

	}

	protected void selecionarTimes(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idEsporte = (request.getParameter("esporteTime"));
		String idLocal = (request.getParameter("local"));
		String idPartida = (request.getParameter("idPartida"));

		Esporte esporte = new Esporte();

		Local local = new Local();

		esporte.setIdEsporte(idEsporte);

		local.setIdLocal(idLocal);

		localDao.selecionarLocal(local);

		esporteDao.selecionarEsporte(esporte);

		Partida partida = new Partida();

		partida.selecionarPartida(idPartida);
		partida.setIdPartida(idPartida);
		partida.setEsporte(esporte);
		partida.setLocal(local);
		partida.atualizarEsporte(idPartida, idEsporte);
		partida.atualizarLocal(idPartida, idLocal);

		esporteDao.verTimes(esporte);

		request.setAttribute("esporteTimes", esporte.getTimes());
		/* request.setAttribute("idEsporte", idEsporte); */
		request.setAttribute("idPartida", idPartida);
//		request.setAttribute("idLocal", idLocal);
		RequestDispatcher rd = request.getRequestDispatcher("jsp/selecionarTimesPartida.jsp");
		rd.forward(request, response);

	}

	protected void setarOrganizacao(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Criando um objeto que ira recber os dados JavaBeans
		ArrayList<Time> listaTime = timeDao.listarTimes();
		ArrayList<Time> listaTime2 = timeDao.listarTimes();
		ArrayList<Esporte> listaEsporte = esporteDao.listarEsportes();
		ArrayList<Partida> listaPartida = dao.listarPartidas();
		//////////////

		// Encaminhar as listas
		request.setAttribute("esportes", listaEsporte);
		request.setAttribute("times", listaTime);
		request.setAttribute("times2", listaTime2);
		request.setAttribute("partidas", listaPartida);
		RequestDispatcher rd = request.getRequestDispatcher("jsp/organizarPartida.jsp");
		rd.forward(request, response);// encaminha o objeto lista ao documento time.jsp
	}

	protected void escalarJogadores(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idPartida = (request.getParameter("idPartida"));
		System.out.println("teste" + idPartida);
//		String idEsporte = (request.getParameter("idEsporte"));
//		String idLocal = (request.getParameter("idLocal"));

//		System.out.println(idPartida);
//		System.out.println(idEsporte);
//		System.out.println(idLocal);

		String idTime1 = (request.getParameter("time1"));
		String idTime2 = (request.getParameter("time2"));
		Time time1 = new Time();
		Time time2 = new Time();

		time1.setIdTime(idTime1);
		time2.setIdTime(idTime2);

		timeDao.selecionarTime(time1);
		timeDao.selecionarTime(time2);

		timeDao.verTime(time1);
		timeDao.verTime(time2);

		Partida partida = new Partida();
//		partida.setIdPartida(idPartida);

		partida.selecionarPartida(idPartida);
		partida.setTime1(time1);
		partida.setTime2(time2);
		partida.atualizarTime1(idPartida, idTime1);
		partida.atualizarTime2(idPartida, idTime2);

//		request.setAttribute("time1", time1);
//		request.setAttribute("time2", time2);
		request.setAttribute("jogadoresTime1", time1.getJogadores());
		request.setAttribute("jogadoresTime2", time2.getJogadores());

		System.out.println(time1.getJogadores().get(0).getIdjogador());
		System.out.println(time1.getJogadores().get(1).getIdjogador());
		System.out.println(time1.getJogadores().get(2).getIdjogador());

		request.setAttribute("partida", partida);
//		request.setAttribute("esporteTime", idEsporte);
//		request.setAttribute("idLocal", idLocal);
		RequestDispatcher rd = request.getRequestDispatcher("jsp/escalarJogadores.jsp");
		rd.forward(request, response);
	}

	protected void validarEscalacao(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idPartida = (request.getParameter("idPartida"));
		System.out.println(idPartida);

		/*
		 * String idEsporteTime = (request.getParameter("idEsporteTime")); String
		 * idLocal = (request.getParameter("idLocal"));
		 * 
		 * String idTime1 = (request.getParameter("time1")); String idTime2 =
		 * (request.getParameter("time2"));
		 */
		Partida partida = new Partida();
//		Local local = new Local();
//		Time time1 = new Time();
//		Time time2 = new Time();
//		
//		local.setIdLocal(idLocal);
//		time1.setIdTime(idTime1);
//		time2.setIdTime(idTime2);

		partida.selecionarPartida(idPartida);

		Time time1 = new Time();
		Time time2 = new Time();
		Local local = new Local();
		Esporte esporte = new Esporte();

		partida.setTime1(time1);
		partida.setTime2(time2);
		partida.setEsporte(esporte);
		partida.setLocal(local);

		String idTime1 = partida.selecionarTime1(idPartida);
		String idTime2 = partida.selecionarTime2(idPartida);
		String idEsporte = partida.selecionarEsporte(idPartida);
		String idLocal = partida.selecionarLocal(idPartida);

		System.out.println(idTime1);
		System.out.println(idTime2);

		time1.setIdTime(idTime1);
		time2.setIdTime(idTime2);
		local.setIdLocal(idLocal);
		esporte.setIdEsporte(idEsporte);

		timeDao.selecionarTime(time1);
		timeDao.selecionarTime(time2);
		esporteDao.selecionarEsporte(esporte);
		localDao.selecionarLocal(local);

		partida.setTime1(time1);
		partida.setTime2(time2);
		partida.setEsporte(esporte);
		partida.setLocal(local);

//		partida.organizarPartida(idPartida, idTime1, idTime2, idEsporte, idLocal);

		System.out.println(partida.getTime1().getNome());
		System.out.println(partida.getTime2().getNome());
//		partida.setTime2(time2);
//		partida.setEsporte(esporte);
//		partida.setLocal(local);

//		dao.organizarPartida(partida);

//		System.out.println(partida.getTime1().getIdTime());
//		System.out.println(partida.getLocal().getIdLocal());
//		System.out.println(partida.getTime2().getIdTime());
//		System.out.println(partida.getEsporte().getIdEsporte());
//		localDao.selecionarLocal(local);
//		timeDao.selecionarTime(time1);
//		timeDao.selecionarTime(time2);

//		Esporte esporte = new Esporte();
//		esporte.setIdEsporte(idEsporteTime);
//		esporteDao.selecionarEsporte(esporte);

		String[] jogadorTime1 = (request.getParameterValues("playersTime1"));
		String[] jogadorTime2 = (request.getParameterValues("playersTime2"));

		for (int i = 0; i < jogadorTime1.length; i++) {
			System.out.println(jogadorTime1);
		}

		int selecionadosTime1 = 0;
		int selecionadosTime2 = 0;

		List<Jogador> jogadorestime1 = new ArrayList<>();
		List<Jogador> jogadorestime2 = new ArrayList<>();

		for (String id : jogadorTime2) {
			Jogador jogadores2 = new Jogador();
			System.out.println("Time2: " + id);
			jogadores2.setIdjogador(id);
			jogadores2.selecionarJogador(id);
			jogadorestime2.add(jogadores2);
			selecionadosTime2++;
		}

		for (String id : jogadorTime1) {
			Jogador jogadores1 = new Jogador();
			System.out.println("Time1: " + id);
			jogadores1.setIdjogador(id);
			jogadores1.selecionarJogador(id);
			jogadorestime1.add(selecionadosTime1, jogadores1);
			selecionadosTime1++;
		}
		for (int i = 0; i < jogadorestime1.size(); i++) {
			System.out.println("ID - :" + jogadorestime1.get(i).getIdjogador());
			System.out.println("Nome - :" + jogadorestime1.get(i).getNome());
		}
		System.out.println("Selecionados Time 1: " + selecionadosTime1);

//		for (String id : jogadorTime2) {
//			Jogador jogadores2 = new Jogador();
//			System.out.println("Time2: " + id);
//			jogadores2.setIdjogador(id);
//			jogadores2.selecionarJogador(id);
//			jogadorestime2.add(jogadores2);
//			selecionadosTime2++;
//		}
		for (int i = 0; i < jogadorestime2.size(); i++) {
			System.out.println("ID - :" + jogadorestime2.get(i).getIdjogador());
			System.out.println("Nome - :" + jogadorestime2.get(i).getNome());
		}
		System.out.println("Selecionados Time 2: " + selecionadosTime2);
		if (partida.getEsporte().getNome().equals("volei")) {
			RequestDispatcher rd;
			if (selecionadosTime1 != 6) {
				System.out.println(
						"Não é possível jogar um partida de volei. Precisa-se que 6 jogadores sejam selecionados");
				rd = request.getRequestDispatcher("erro.html");
			} else if (selecionadosTime2 != 6) {
				System.out.println(
						"Não é possível jogar um partida de volei. Precisa-se que 6 jogadores sejam selecionados");
				rd = request.getRequestDispatcher("erro.html");
			} else {
//				request.setAttribute("time1", time1 );
//				request.setAttribute("time2", time2 );
				request.setAttribute("partida", partida);
//				request.setAttribute("esporte", esporte);
//				request.setAttribute("local", local);
				request.setAttribute("jogadoresTime1", jogadorestime1);
				request.setAttribute("jogadoresTime2", jogadorestime2);
				rd = request.getRequestDispatcher("jsp/exibicao.jsp");
			}
			rd.forward(request, response);

		} else if (partida.getEsporte().getNome().equals("futsal")) {
			RequestDispatcher rd;
			if (selecionadosTime1 != 5) {
				System.out.println(
						"Não é possível jogar um partida de futsal. Precisa-se que 5 jogadores sejam selecionados");
				rd = request.getRequestDispatcher("erro.html");
			} else if (selecionadosTime2 != 5) {
				System.out.println(
						"Não é possível jogar um partida de futsal. Precisa-se que 5 jogadores sejam selecionados");
				rd = request.getRequestDispatcher("erro.html");
			} else {
//				request.setAttribute("time1", time1 );
//				request.setAttribute("time2", time2 );
				request.setAttribute("partida", partida);
//				request.setAttribute("esporte", esporte);
//				request.setAttribute("local", local);
				request.setAttribute("jogadoresTime1", jogadorestime1);
				request.setAttribute("jogadoresTime2", jogadorestime2);
				rd = request.getRequestDispatcher("jsp/exibicao.jsp");
			}
			rd.forward(request, response);
		}
	}

	protected void organizarPartida(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		////////////////////////////////////////////////////////
		// Recebimento do id do partida que será exibido

		partida.selecionarPartida(request.getParameter("partida"));

		/////////////////////////////////////////////////////////

		// Pegando os ids dos dois times e do esporte
		String time1 = (request.getParameter("time1"));
		String time2 = (request.getParameter("time2"));
		String esporte = (request.getParameter("esporte"));

		////////////////////////////////////////////////////////

		// Instanciando objetos pra serem usados na partida
		Time timePri = new Time();
		Time timeSeg = new Time();
		Esporte esportePartida = new Esporte();

		///////////////////////////////////////////////////////

		// Setando os ids como os escolhidos pelos usuarios
		timePri.setIdTime(time1);
		timeSeg.setIdTime(time2);
		esportePartida.setIdEsporte(esporte);

		//////////////////////////////////////////////////////

		// Buscar os dois times e o esporte no banco
		timeDao.selecionarTime(timePri);
		timeDao.selecionarTime(timeSeg);
		esporteDao.selecionarEsporte(esportePartida);

		partida.setTime1(timePri);
		partida.setTime2(timeSeg);
		partida.setEsporte(esportePartida);

		dao.organizarPartida(partida);

		request.setAttribute("partida", partida);
		RequestDispatcher rd = request.getRequestDispatcher("jsp/exibicao.jsp");
		rd.forward(request, response);

	}

	protected void contagemGols(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int golsCasa = Integer.parseInt(request.getParameter("golsCasa"));
		int golsFora = Integer.parseInt(request.getParameter("golsFora"));

		String idTimeCasa = request.getParameter("timeCasa");
		String idTimeFora = request.getParameter("timeFora");

		Time timeCasa = new Time();
		Time timeFora = new Time();

		timeCasa.setIdTime(idTimeCasa);
		timeFora.setIdTime(idTimeFora);

		timeDao.selecionarTime(timeCasa);
		timeDao.selecionarTime(timeFora);

		///////////////////////////////////

		if (golsCasa > golsFora) {
			int somaVitorias = timeCasa.getVitorias() + 1;
			timeCasa.ganharPartida(idTimeCasa, somaVitorias);
			///////////////////////////////////////////////
			int somaDerrotas = timeFora.getDerrotas() + 1;
			timeFora.perderPartida(idTimeFora, somaDerrotas);

		}
		if (golsFora > golsCasa) {

			int somaVitorias = timeFora.getVitorias() + 1;
			timeFora.ganharPartida(idTimeFora, somaVitorias);
			///////////////////////////////////////////////
			int somaDerrotas = timeCasa.getDerrotas() + 1;
			timeCasa.perderPartida(idTimeCasa, somaDerrotas);
		}
		if (golsCasa == golsFora) {
			int empateCasa = timeCasa.getEmpates() + 1;
			timeCasa.empatarPartida(idTimeCasa, empateCasa);
			///////////////////////////////////////////////
			int empateFora = timeFora.getEmpates() + 1;
			timeFora.empatarPartida(idTimeFora, empateFora);
		}

		//////////////////////////////////////////////////////////////
		// Receber o valor do id da partida

		// Criar um objeto partida
		Partida partida = new Partida();

		String idPartida = request.getParameter("idPartida");

		System.out.println(idPartida);

		
		partida.atualizarPontuacao(idPartida, golsCasa, golsFora);

		partida.selecionarPartida(idPartida);	
		
//		int idCampeonato = partida.getIdCampeonato();
//		
//		Campeonato campeonato = new Campeonato();
//		
//		campeonato.selecionarCompeticao(idCampeonato);
//		System.out.println(campeonato.getId());
//		System.out.println(campeonato.getNome());
//		
		//PARTIDA FINAL
//		campeonato.selecionarPartidas(idCampeonato);
//		if(campeonato.getPartidas().get(2).getPontosTime1() != 0 && campeonato.getPartidas().get(2).getPontosTime2() != 0) {
//			int pontosTime1 = campeonato.getPartidas().get(2).getPontosTime1();
//			int pontosTime2 = campeonato.getPartidas().get(2).getPontosTime2();
//		
//			RequestDispatcher rd;
//			if(pontosTime1 > pontosTime2) {
//				Time time = campeonato.getPartidas().get(2).getTime1();
//				System.out.println("id" + time.getIdTime());
//				
//				request.setAttribute("timeCampeoao",time);
//				rd = request.getRequestDispatcher("jsp/campeao.jsp");
//			}else if(pontosTime2 > pontosTime1){
//				Time time = campeonato.getPartidas().get(2).getTime2();
//				System.out.println("id" + time.getIdTime());
//				request.setAttribute("timeCampeoao",time);
//				rd = request.getRequestDispatcher("jsp/campeao.jsp");
//			}else {
//				rd = request.getRequestDispatcher("erro.html");
//			}
//			rd.forward(request, response);
//		
//		}
		
		
		// Verificar se a Partida vem de um campeonato ou não
		RequestDispatcher rd;
		if ((timeCasa.getIdCampeonato() == 0 && timeFora.getIdCampeonato() == 0 )&& (timeCasa.getIdLiga() == 0
				&& timeFora.getIdLiga() == 0)) {

			if (timeCasa.getIdCampeonato() != 0 && timeCasa.getIdCampeonato() == timeFora.getIdCampeonato()) {
				System.out.println("teste id campeonato" + timeCasa.getIdCampeonato());
				request.setAttribute("idCampeonato", timeCasa.getIdCampeonato());
				rd = request.getRequestDispatcher("jsp/programarPartidas.jsp");
			} else if (timeCasa.getIdLiga() != 0 && timeCasa.getIdLiga() == timeFora.getIdLiga()) {
				System.out.println("teste id liga" + timeCasa.getIdLiga());
				request.setAttribute("idliga", timeCasa.getIdLiga());
				rd = request.getRequestDispatcher("jsp/programarPartidasLiga.jsp");
			} else {
				rd = request.getRequestDispatcher("partida");
				System.out.println(timeCasa.getIdCampeonato());
			}
		} else if (timeCasa.getIdCampeonato() != 0 && timeFora.getIdCampeonato() != 0 && timeCasa.getIdLiga() == 0
				&& timeFora.getIdLiga() == 0) {

			if (timeCasa.getIdCampeonato() != 0 && timeCasa.getIdCampeonato() == timeFora.getIdCampeonato()) {
				System.out.println("teste id campeonato" + timeCasa.getIdCampeonato());
				request.setAttribute("idCampeonato", timeCasa.getIdCampeonato());
				rd = request.getRequestDispatcher("jsp/programarPartidas.jsp");
			} else {
				rd = request.getRequestDispatcher("jsp/programarPartidasCampeonato.jsp");
				System.out.println(timeCasa.getIdCampeonato());
			}
		} else if (timeCasa.getIdCampeonato() == 0 && timeFora.getIdCampeonato() == 0 && timeCasa.getIdLiga() != 0
				&& timeFora.getIdLiga() != 0) {

			if (timeCasa.getIdLiga() != 0 && timeCasa.getIdLiga() == timeFora.getIdLiga()) {
				System.out.println("teste id liga" + timeCasa.getIdLiga());
				request.setAttribute("idliga", timeCasa.getIdLiga());
				rd = request.getRequestDispatcher("jsp/programarPartidasLiga.jsp");
			} else {
				rd = request.getRequestDispatcher("jsp/programarPartidasLiga.jsp");
				System.out.println(timeCasa.getIdCampeonato());
			}
		} else {
			rd = request.getRequestDispatcher("partida");
		}
		rd.forward(request, response);
	}
}
