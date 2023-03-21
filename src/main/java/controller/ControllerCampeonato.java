package controller;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Campeonato;
import model.CampeonatoDAO;
import model.Esporte;
import model.EsporteDAO;
import model.Local;
import model.LocalDAO;
import model.Partida;
import model.PartidaDAO;
import model.Time;
import model.TimeDAO;

/**
 * Servlet implementation class ControllerCampeonato
 */
@WebServlet(urlPatterns = { "/campeonato", "/insertCampeonato", "/selectCampeonato", "/updateCampeonato",
		"/deleteCampeonato", "/selecionarEsporteLocalCampeonato", "/selecionarTimesCampeonato", "/efetivarCampeonato",
		"/selecionarCampeonato", "/realizarCampeonato", "/organizarPrimeiraPartida", "/organizarSegundaPartida",
		"/organizarPartidaFinal", "/aplicarPremiacao" })
public class ControllerCampeonato extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CampeonatoDAO campeonatoDao = new CampeonatoDAO();
	Campeonato campeonato = new Campeonato();
	Partida partida = new Partida();
	PartidaDAO partidaDao = new PartidaDAO();
	Time time = new Time();
	TimeDAO timeDao = new TimeDAO();
	Local local = new Local();
	LocalDAO localDao = new LocalDAO();
	Esporte esporte = new Esporte();
	EsporteDAO esporteDao = new EsporteDAO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getServletPath();
		System.out.println(action);

		if (action.equals("/campeonato")) {
			campeonatos(request, response);
		} else if (action.equals("/insertCampeonato")) {
			novoCampeonato(request, response);
		} else if (action.equals("/selectCampeonato")) {
			listarCampeonato(request, response);
		} else if (action.equals("/updateCampeonato")) {
			editarCampeonato(request, response);
		} else if (action.equals("/deleteCampeonato")) {
			deletarCampeonato(request, response);
		} else if (action.equals("/selecionarTimesCampeonato")) {
			selecionarTimes(request, response);
		} else if (action.equals("/selecionarEsporteLocalCampeonato")) {
			selecionarEsporteLocalCampeonato(request, response);
		} else if (action.equals("/efetivarCampeonato")) {
			efetivarCampeonato(request, response);
		} else if (action.equals("/selecionarCampeonato")) {
			selecionarCampeonato(request, response);
		} else if (action.equals("/realizarCampeonato")) {
			realizarCampeonato(request, response);
		} else if (action.equals("/organizarPrimeiraPartida")) {
			organizarPrimeiraPartida(request, response);
		} else if (action.equals("/organizarSegundaPartida")) {
			organizarSegundaPartida(request, response);
		} else if (action.equals("/organizarPartidaFinal")) {
			organizarPartidaFinal(request, response);
		} else if (action.equals("/aplicarPremiacao")) {
			aplicarPremiacao(request, response);
		} else {
			response.sendRedirect("index.html");
		}

	}

	// Listar campeonatos
	protected void campeonatos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		response.sendRedirect("jsp/campeonato.jsp");

		ArrayList<Campeonato> listaCampeonatos = campeonato.campeonatos();

		request.setAttribute("campeonatos", listaCampeonatos);
		RequestDispatcher rd = request.getRequestDispatcher("jsp/campeonato.jsp");
		rd.forward(request, response);// encaminha o objeto lista ao documento partida.js

	}

	// Novo campeonato
	protected void novoCampeonato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		campeonato.criar(request.getParameter("nome"), request.getParameter("medalha"));

		response.sendRedirect("campeonato");

	}

	// Editar CampeonatoS
	protected void listarCampeonato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Recebimento do id do campeonato que será editado
		int idCampeonato = Integer.parseInt(request.getParameter("idCampeonato"));
		// Executar o método selecionarCampeonnato
		campeonato.listarCompeticao(idCampeonato);

		// Setar os atributos do formulário com o conteúdo de Campeonato.java
		request.setAttribute("idCampeonato", campeonato.getId());
		request.setAttribute("nome", campeonato.getNome());
		request.setAttribute("medalha", campeonato.getMedalhas());
		// encaminhar ao documento editarCampeonato.jsp
		RequestDispatcher rd = request.getRequestDispatcher("jsp/editarCampeonato.jsp");
		rd.forward(request, response);
	}

	protected void editarCampeonato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * // Setar as variáveis Campeonato.java
		 * campeonato.setId(Integer.parseInt(request.getParameter("idCampeonato")));
		 * campeonato.setNome(request.getParameter("nome"));
		 * campeonato.setMedalhas(request.getParameter("medalha")); // executar o método
		 * atualizarCampeonato campeonatoDao.atualizarCampeonato(campeonato);
		 */
		campeonato.editarCompeticao(Integer.parseInt(request.getParameter("idCampeonato")),
				request.getParameter("nome"), request.getParameter("medalhas"));

		// redirecionar para o documento campeonato.jsp (atualizando as alterações)
		response.sendRedirect("campeonato");
	}

	protected void deletarCampeonato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Recebimento do id do campeonato a ser excluído (validador.js)
		int idCampeonato = Integer.parseInt(request.getParameter("idCampeonato"));
		// Setar a variável idCampeonato Campeonato.java
		/*
		 * campeonato.setId(idCampeonato);
		 */
		campeonato.deletarCompeticao(idCampeonato);
		// executar o método deletarCampeonato (CampeonatoDAO) passando o objeto
		// campeonato
		/*
		 * campeonatoDao.deletarCampeoanto(idCampeonato);
		 */ // redirecionar para o documento campeonato.jsp (atualizando as alterações)
		response.sendRedirect("campeonato");

	}

	protected void selecionarEsporteLocalCampeonato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int idCampeonato = Integer.parseInt(request.getParameter("idCampeonato"));
		System.out.println(idCampeonato);
		ArrayList<Esporte> listaEsporte = esporteDao.listarEsportes();

		ArrayList<Local> listaLocais = localDao.listarLocais();

		request.setAttribute("listaEsportes", listaEsporte);
		request.setAttribute("listaLocal", listaLocais);
		request.setAttribute("idCampeonato", idCampeonato);
		RequestDispatcher rd = request.getRequestDispatcher("jsp/selecionarEsporteLocalCampeonato.jsp");
		rd.forward(request, response);
	}

	protected void selecionarTimes(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int idCampeonato = Integer.parseInt(request.getParameter("idCampeonato"));
		String idEsporte = (request.getParameter("esporteCampeonato"));
		String idLocal = (request.getParameter("localCampeonato"));
		
		System.out.println("1: " + idLocal);
		System.out.println("2: " + idEsporte);
		Campeonato campeonato = new Campeonato();
		campeonato.setId(idCampeonato);
		campeonato.selecionarCompeticao(idCampeonato);
//		
		String horarioCampeonato = request.getParameter("horarioCampeonato");
		String dataCampeonato = request.getParameter("dataCampeonato");

		String horarioCampeonato2 = request.getParameter("horarioCampeonato2");
		String dataCampeonato2 = request.getParameter("dataCampeonato2");

		String horarioCampeonatoFinal = request.getParameter("horarioCampeonatoFinal");
		String dataCampeonatoFinal = request.getParameter("dataCampeonatoFinal");

		System.out.println( "3: "+ horarioCampeonato);
		Partida partidaAux = new Partida();
		// Criando partida com esses horarios
		Esporte esporte = new Esporte();
//
		Local local = new Local();
//
		esporte.setIdEsporte(idEsporte);
//
		local.setIdLocal(idLocal);
//
		localDao.selecionarLocal(local);
		esporteDao.selecionarEsporte(esporte);
		System.out.println("idEsporte:" + esporte.getIdEsporte());
		System.out.println("idLocal:" + local.getIdLocal());
		// Setar o esporte e o local na partida pra conseguir pegar o id

		partidaAux.setEsporte(esporte);
		partidaAux.setLocal(local);
		partidaAux.novaPartidaCompeticao(partidaAux, dataCampeonato, horarioCampeonato, idEsporte, idLocal);

		System.out.println(partidaAux.getEsporte().getIdEsporte());
		// partida.setEsporte(esporte);
//		partida.setLocal(local);

		Partida partida2 = new Partida();

		partida2.setEsporte(esporte);
		partida2.setLocal(local);

		partida2.novaPartidaCompeticao(partida2, dataCampeonato2, horarioCampeonato2, idEsporte, idLocal);

		Partida partidaFinal = new Partida();

		partidaFinal.setEsporte(esporte);
		partidaFinal.setLocal(local);

		partidaFinal.novaPartidaCompeticao(partidaFinal, dataCampeonatoFinal, horarioCampeonatoFinal, idEsporte,
				idLocal);

		// Recuperando o id das partidas atraves das datas
		String idPartida = partidaAux.recuperarId(dataCampeonato);
		String idPartida2 = partida2.recuperarId(dataCampeonato2);
		String idPartidaFinal = partidaFinal.recuperarId(dataCampeonatoFinal);

		System.out.println("data part 1:" + partidaAux.getData());
		System.out.println("ID part 1: " + partidaAux.getIdPartida());
		System.out.println("data part 2: " + partida2.getData());
		System.out.println("ID part 2: " + partida2.getIdPartida());
		System.out.println("data part Final: " + partidaFinal.getData());
		System.out.println("ID part Final: " + partidaFinal.getIdPartida());

		// setando o id do campeonato na partida
		partidaAux.atualizarCampeonato(idPartida, idCampeonato);
		partida2.atualizarCampeonato(idPartida2, idCampeonato);
		partidaFinal.atualizarCampeonato(idPartidaFinal, idCampeonato);

		System.out.println("id camp-1 partida 1 : " + partidaAux.getIdCampeonato());
		System.out.println("id camp-1: partida 2 : " + partida2.getIdCampeonato());
		System.out.println("id camp-Fina1: partida Final : " + partidaFinal.getIdCampeonato());

////		
//		campeonato.setEsporte(esporte);
//		campeonato.setLocal(local);
//		campeonato.atualizarEsporte(idCampeonato, idEsporte);
//		campeonato.atualizarLocal(idCampeonato, idLocal);
		esporteDao.verTimes(esporte);
//
		request.setAttribute("esporteTimes", esporte.getTimes());
		request.setAttribute("idCampeonato", idCampeonato);
		/* request.setAttribute("idEsporte", idEsporte); */
//		request.setAttribute("idLocal", idLocal);
		RequestDispatcher rd = request.getRequestDispatcher("jsp/selecionarTimesCampeonato.jsp");
		rd.forward(request, response);
	}

	// efetivar o campeonato e mandar para o banco de dados
	protected void efetivarCampeonato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int idCampeonato = Integer.parseInt(request.getParameter("idCampeonato"));

		Campeonato campeonato = new Campeonato();

		campeonato.selecionarCompeticao(idCampeonato);

		String idTime1 = request.getParameter("time1");
		String idTime2 = request.getParameter("time2");
		String idTime3 = request.getParameter("time3");
		String idTime4 = request.getParameter("time4");

		// Pegando os horario da pagina

		Time time1 = new Time();
		Time time2 = new Time();
		Time time3 = new Time();
		Time time4 = new Time();

		time1.setIdTime(idTime1);
		time2.setIdTime(idTime2);
		time3.setIdTime(idTime3);
		time4.setIdTime(idTime4);

		timeDao.selecionarTime(time1);
		timeDao.selecionarTime(time2);
		timeDao.selecionarTime(time3);
		timeDao.selecionarTime(time4);

		time1.inscreverCampeonato(idTime1, idCampeonato);
		time1.inscreverCampeonato(idTime2, idCampeonato);
		time1.inscreverCampeonato(idTime3, idCampeonato);
		time1.inscreverCampeonato(idTime4, idCampeonato);

		campeonato.selecionarPartidas(idCampeonato);

		String idPartida1 = campeonato.getPartidas().get(0).getIdPartida();
		String idPartida2 = campeonato.getPartidas().get(1).getIdPartida();

		campeonato.getPartidas().get(0).setTime1(time1);
		campeonato.getPartidas().get(0).setTime2(time2);

		campeonato.getPartidas().get(1).setTime1(time3);
		campeonato.getPartidas().get(1).setTime2(time4);

		campeonato.getPartidas().get(0).atualizarTime1(idPartida1, idTime1);
		campeonato.getPartidas().get(0).atualizarTime2(idPartida1, idTime2);

		campeonato.getPartidas().get(1).atualizarTime1(idPartida2, idTime3);
		campeonato.getPartidas().get(1).atualizarTime2(idPartida2, idTime4);

//		campeonato.selecionarTime();
//		Campeonato campeonato = new Campeonato();
//		
//		campeonato.setarTimes(time1,time2,time3,time4);
//		
		ArrayList<Time> timesCampeonato = new ArrayList<Time>();
		timesCampeonato.add(time1);
		timesCampeonato.add(time2);
		timesCampeonato.add(time3);
		timesCampeonato.add(time4);
//		
		campeonato.setTimes(timesCampeonato);

//		for(int i =0; i< campeonato.getTimes().size(); i++) {
//			System.out.println("id - " + campeonato.getTimes().get(i).getIdTime());
//			System.out.println("nome - " + campeonato.getTimes().get(i).getNome());
		request.setAttribute("campeonato", campeonato);
		RequestDispatcher rd = request.getRequestDispatcher("jsp/exibirDadosCampeonato.jsp");
		rd.forward(request, response);
//		}
	}

	// Ver os partidas que estao alocados em um campeonato especifico
//	protected void verPartidas(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		String idPartida = request.getParameter("idPartida");
//		partida.setIdPartida(idPartida);
//
//		// Teste. Não está funcionando
//		// campeonatoDao.verCampeonato(campeonato);/// não funciona teste
//
//		request.setAttribute("campeonato", campeonato);
//
//		RequestDispatcher rd = request.getRequestDispatcher("jsp/partidasCampeonado.jsp");
//		rd.forward(request, response);

//	}

	protected void selecionarCampeonato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ArrayList<Campeonato> listaCampeonatos = campeonatoDao.listarCampeonatos();

		request.setAttribute("campeonatos", listaCampeonatos);
		RequestDispatcher rd = request.getRequestDispatcher("jsp/selecionarCampeonato.jsp");
		rd.forward(request, response);// encaminha o objeto lista ao documento partida.js

	}

	protected void realizarCampeonato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int idCampeonato = Integer.parseInt(request.getParameter("campeonatoRealizado"));

		System.out.println(idCampeonato);

//		Campeonato campeonato = new Campeonato();
//		
//		campeonato.selecionarCampeonato(idCampeonato);
//
//		campeonato.selecionarTimesCampeonato(idCampeonato);
//		
//		campeonato.selecionarPartidas(idCampeonato);
//		

		// PARTIDA 1
//		String idPartida = campeonato.getPartidas().get(0).getIdPartida();
//		String idTime1 = campeonato.getTimes().get(0).getIdTime();
//		String idTime2 = campeonato.getTimes().get(1).getIdTime();
//		
//		Time time1 = new Time();
//		Time time2 = new Time();
//		
//		time1.setIdTime(idTime1);
//		time2.setIdTime(idTime2);
//	
//		timeDao.selecionarTime(time1);
//		timeDao.selecionarTime(time2);
//		
//		timeDao.verTime(time1);
//		timeDao.verTime(time2);
//		
//		Partida partida = new Partida();
//		
//		partida.selecionarPartida(idPartida);
//		
//		partida.setTime1(time1);
//		partida.setTime2(time2);

		// PARTIDA 2

//		String idPartida2 = campeonato.getPartidas().get(1).getIdPartida();
//		String idTime3 = campeonato.getTimes().get(2).getIdTime();
//		String idTime4 = campeonato.getTimes().get(3).getIdTime();
//		
//		Time time3 = new Time();
//		Time time4 = new Time();
//		
//		time3.setIdTime(idTime3);
//		time4.setIdTime(idTime4);
//	
//		timeDao.selecionarTime(time3);
//		timeDao.selecionarTime(time4);
//		
//		timeDao.verTime(time3);
//		timeDao.verTime(time4);
//		
//		Partida partida2 = new Partida();
//		
//		partida2.selecionarPartida(idPartida2);
//		
//		partida2.setTime1(time3);
//		partida2.setTime2(time4);

		request.setAttribute("idCampeonato", idCampeonato);
		RequestDispatcher rd = request.getRequestDispatcher("jsp/programarPartidas.jsp");
		rd.forward(request, response);
	}

	protected void organizarPrimeiraPartida(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int idCampeonato = Integer.parseInt(request.getParameter("idCampeonato"));

		Campeonato campeonato = new Campeonato();

		campeonato.selecionarCompeticao(idCampeonato);

		campeonato.selecionarTimesCompeticao(idCampeonato);

		campeonato.selecionarPartidas(idCampeonato);

		String idPartida = campeonato.getPartidas().get(0).getIdPartida();

		System.out.println("id: " + idCampeonato);
		System.out.println("ID TIME 1 = " + campeonato.getTimes().get(0).getIdTime());
		System.out.println("ID TIME 2 = " + campeonato.getTimes().get(1).getIdTime());
		String idTime1 = campeonato.getTimes().get(0).getIdTime();
		String idTime2 = campeonato.getTimes().get(1).getIdTime();

		Time time1 = new Time();
		Time time2 = new Time();

		time1.setIdTime(idTime1);
		time2.setIdTime(idTime2);

		timeDao.selecionarTime(time1);
		timeDao.selecionarTime(time2);

		timeDao.verTime(time1);
		timeDao.verTime(time2);

		Partida partida = new Partida();

		partida.selecionarPartida(idPartida);

		partida.setTime1(time1);
		partida.setTime2(time2);

		request.setAttribute("jogadoresTime1", time1.getJogadores());
		request.setAttribute("jogadoresTime2", time2.getJogadores());
		request.setAttribute("partida", partida);

		RequestDispatcher rd = request.getRequestDispatcher("jsp/escalarJogadores.jsp");
		rd.forward(request, response);

	}

	protected void organizarSegundaPartida(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int idCampeonato = Integer.parseInt(request.getParameter("idCampeonato"));

		Campeonato campeonato = new Campeonato();

		campeonato.selecionarCompeticao(idCampeonato);

		campeonato.selecionarTimesCompeticao(idCampeonato);

		campeonato.selecionarPartidas(idCampeonato);

		String idPartida2 = campeonato.getPartidas().get(1).getIdPartida();
		String idTime3 = campeonato.getTimes().get(2).getIdTime();
		String idTime4 = campeonato.getTimes().get(3).getIdTime();

		Time time3 = new Time();
		Time time4 = new Time();

		time3.setIdTime(idTime3);
		time4.setIdTime(idTime4);

		timeDao.selecionarTime(time3);
		timeDao.selecionarTime(time4);

		timeDao.verTime(time3);
		timeDao.verTime(time4);

		Partida partida2 = new Partida();

		partida2.selecionarPartida(idPartida2);

		partida2.setTime1(time3);
		partida2.setTime2(time4);

		request.setAttribute("jogadoresTime1", time3.getJogadores());
		request.setAttribute("jogadoresTime2", time4.getJogadores());
		request.setAttribute("partida", partida2);

		RequestDispatcher rd = request.getRequestDispatcher("jsp/escalarJogadores.jsp");
		rd.forward(request, response);
	}

	protected void organizarPartidaFinal(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int idCampeonato = Integer.parseInt(request.getParameter("idCampeonato"));
		System.out.println("IdCampeonato" + idCampeonato);

		Campeonato campeonato = new Campeonato();

		campeonato.selecionarCompeticao(idCampeonato);

		// selecionar as partidas do campeonato

		campeonato.selecionarPartidas(idCampeonato);
		// selecionar as pontuações dos times no campeonato // PARTIDA 1
		int pontosTime1 = campeonato.getPartidas().get(0).getPontosTime1();
		int pontosTime2 = campeonato.getPartidas().get(0).getPontosTime2();

		System.out.println("pontos time 1: " + pontosTime1 + " Pontos time 2: " + pontosTime2);

		// PARTIDA2
		int pontosTime3 = campeonato.getPartidas().get(1).getPontosTime1();
		int pontosTime4 = campeonato.getPartidas().get(1).getPontosTime2();

		System.out.println("pontos time 3: " + pontosTime3 + " Pontos time 4: " + pontosTime4);

		// SE O VENCEDOR DA PRIMEIRA PARTIDA FOR O TIME 1 E O DA SEGUNDA FOR O 3
		// SE O VENCEDOR DA PRIMEIRA PARTIDA FOR O TIME 1 E O DA SEGUNDA FOR O 4
		// SE O VENCEDOR DA PRIMEIRA PARTIDA FOR O TIME 2 E O DA SEGUNDA FOR O 3
		// SE O VENCEDOR DA PRIMEIRA PARTIDA FOR O TIME 2 E O DA SEGUNDA FOR O 4
		if (pontosTime1 == 0 && pontosTime2 == 0 && pontosTime3 == 0 && pontosTime4 == 0) {

			RequestDispatcher rd = request.getRequestDispatcher("erro.html");
			rd.forward(request, response);
		} else {
			RequestDispatcher rd;
			if (pontosTime1 > pontosTime2 && pontosTime3 > pontosTime4) {
				// MANDANDO O TIME1 (VENCEDOR DA PRIMEIRA PARTIDA)
				Time time = new Time();
				String idPartida = campeonato.getPartidas().get(0).getIdPartida();
				System.out.println(idPartida);
				campeonato.getPartidas().get(0).setTime1(time);
				campeonato.getPartidas().get(0).selecionarTime1(idPartida);
				String idTime1 = campeonato.getPartidas().get(0).getTime1().getIdTime();
				time.setIdTime(idTime1);
				timeDao.selecionarTime(time);

				System.out.println("Vencedor partida 1= " + idTime1);
				// MANDANDO O TIME 3 (VENCEDOR DA PARTIDA 2)

				Time time2 = new Time();
				String idPartida2 = campeonato.getPartidas().get(1).getIdPartida();
				System.out.println(idPartida2);
				campeonato.getPartidas().get(1).setTime1(time2);
				campeonato.getPartidas().get(1).selecionarTime1(idPartida2);
				String idTime3 = campeonato.getPartidas().get(1).getTime1().getIdTime();
				System.out.println("Vencedor partida 2 " + idTime3);
				time2.setIdTime(idTime3);
				timeDao.selecionarTime(time2);

				// Setar o time no banco de dados
				///////////////////////////////////////////////
				// SELECIONAR A PARTIDA FINAL DO CAMPEONATO

				Partida partidaFinal = new Partida();
				String idPartidaFinal = campeonato.getPartidas().get(2).getIdPartida();

				System.out.println("id da partida final: " + idPartidaFinal);
				partidaFinal = campeonato.getPartidas().get(2).selecionarPartida(idPartidaFinal);

				// ATUALIZAR O TIME 1 DA ULTIMA PARTIDA
				// String idPartidaFinal = partidaFinal.getIdPartida();
				partidaFinal.setTime1(time);
				partidaFinal.atualizarTime1(idPartidaFinal, idTime1);

				// ATUALIZAAR O TIME 2 DA ULTIMA PARTIDA
				partidaFinal.setTime2(time2);
				System.out.println("idTime: " + idTime3 + " " + idTime1);
				partidaFinal.atualizarTime2(idPartidaFinal, idTime3);

				// Selecionando os jogadores dos dois times

				// SETANDO OS TIMES NO ARRAY LIST DE TIMES DO CAMPEONATO
				campeonato.selecionarTimesCompeticao(idCampeonato);

				ArrayList<Time> timesCampeonato = new ArrayList<Time>();
				timesCampeonato.add(time);
				timesCampeonato.add(time2);

				campeonato.setTimes(timesCampeonato);

				System.out.println("idCampeonato time 1 da Partida Final :" + campeonato.getTimes().get(0).getNome());
				System.out.println("idCampeonato time 2 da Partida Final :" + campeonato.getTimes().get(1).getNome());

				time = campeonato.getTimes().get(0);
				time2 = campeonato.getTimes().get(1);

				timeDao.verTime(time);
				timeDao.verTime(time2);

				// MANDANDO OS TIMES VENCEDORES
//			request.setAttribute("campeonato", campeonato);
				request.setAttribute("partida", partidaFinal);
				request.setAttribute("jogadoresTime1", time.getJogadores());
				request.setAttribute("jogadoresTime2", time2.getJogadores());
				rd = request.getRequestDispatcher("jsp/escalarJogadores.jsp");

			} else if (pontosTime1 > pontosTime2 && pontosTime4 > pontosTime3) {
				// MANDANDO O TIME1 (VENCEDOR DA PRIMEIRA PARTIDA)
				Time time = new Time();
				String idPartida = campeonato.getPartidas().get(0).getIdPartida();
				System.out.println(idPartida);
				campeonato.getPartidas().get(0).setTime1(time);
				campeonato.getPartidas().get(0).selecionarTime1(idPartida);
				String idTime1 = campeonato.getPartidas().get(0).getTime1().getIdTime();
				time.setIdTime(idTime1);
				timeDao.selecionarTime(time);

				// MANDANDO O TIME 4 (VENCEDOR DA PARTIDA 2)

				Time time2 = new Time();
				String idPartida2 = campeonato.getPartidas().get(1).getIdPartida();
				System.out.println(idPartida);
				campeonato.getPartidas().get(1).setTime2(time2);
				campeonato.getPartidas().get(1).selecionarTime2(idPartida2);
				String idTime4 = campeonato.getPartidas().get(1).getTime2().getIdTime();
				time2.setIdTime(idTime4);
				timeDao.selecionarTime(time2);
				System.out.println("Vencedor partida 2" + idTime4);

				/////////////////////////////////////////////////////////////////////////
				Partida partidaFinal = new Partida();
				String idPartidaFinal = campeonato.getPartidas().get(2).getIdPartida();

				System.out.println("id da partida final: " + idPartidaFinal);
				partidaFinal = campeonato.getPartidas().get(2).selecionarPartida(idPartidaFinal);

				// ATUALIZAR O TIME 1 DA ULTIMA PARTIDA
				// String idPartidaFinal = partidaFinal.getIdPartida();
				partidaFinal.setTime1(time);
				partidaFinal.atualizarTime1(idPartidaFinal, idTime1);

				// ATUALIZAAR O TIME 2 DA ULTIMA PARTIDA
				partidaFinal.setTime2(time2);
				partidaFinal.atualizarTime2(idPartidaFinal, idTime4);

				System.out.println("idTime: " + idTime4 + " " + idTime1);

				// Selecionando os jogadores dos dois times

				// SETANDO OS TIMES NO ARRAY LIST DE TIMES DO CAMPEONATO
				campeonato.selecionarTimesCompeticao(idCampeonato);

				ArrayList<Time> timesCampeonato = new ArrayList<Time>();
				timesCampeonato.add(time);
				timesCampeonato.add(time2);

				campeonato.setTimes(timesCampeonato);

				System.out.println("idCampeonato time 1 da Partida Final :" + campeonato.getTimes().get(0).getNome());
				System.out.println("idCampeonato time 2 da Partida Final :" + campeonato.getTimes().get(1).getNome());

				time = campeonato.getTimes().get(0);
				time2 = campeonato.getTimes().get(1);

				timeDao.verTime(time);
				timeDao.verTime(time2);

				// MANDANDO OS TIMES VENCEDORES
//			request.setAttribute("campeonato", campeonato);
				request.setAttribute("partida", partidaFinal);
				request.setAttribute("jogadoresTime1", time.getJogadores());
				request.setAttribute("jogadoresTime2", time2.getJogadores());
				rd = request.getRequestDispatcher("jsp/escalarJogadores.jsp");

				// MANDANDO OS TIMES VENCEDORES
//			request.setAttribute("campeonato", campeonato);
//			request.setAttribute("TimeVencedorPartida1", time);
//			request.setAttribute("TimeVencedorPartida2", time2);
//			rd = request.getRequestDispatcher("jsp/exibirPartidaFinal.jsp");

			} else if (pontosTime2 > pontosTime1 && pontosTime3 > pontosTime4) {
				// MANDANDO O TIME2 (VENCEDOR DA PRIMEIRA PARTIDA)
				Time time = new Time();
				String idPartida = campeonato.getPartidas().get(0).getIdPartida();
				System.out.println(idPartida);
				campeonato.getPartidas().get(0).setTime2(time);
				campeonato.getPartidas().get(0).selecionarTime2(idPartida);
				String idTime2 = campeonato.getPartidas().get(0).getTime2().getIdTime();
				time.setIdTime(idTime2);
				timeDao.selecionarTime(time);

				// MANDANDO O TIME 3 (VENCEDOR DA PARTIDA 2)

				Time time2 = new Time();
				String idPartida2 = campeonato.getPartidas().get(1).getIdPartida();
				System.out.println("idSegunda Partida : " + idPartida);
				campeonato.getPartidas().get(1).setTime1(time2);
				campeonato.getPartidas().get(1).selecionarTime1(idPartida2);
				String idTime3 = campeonato.getPartidas().get(1).getTime1().getIdTime();
				time2.setIdTime(idTime3);
				timeDao.selecionarTime(time2);
				System.out.println("Vencedor partida 2" + idTime3);

				// MANDANDO OS TIMES VENCEDORES
				/////////////////////////////////////////////////////////////////////////
				Partida partidaFinal = new Partida();
				String idPartidaFinal = campeonato.getPartidas().get(2).getIdPartida();

				System.out.println("id da partida final: " + idPartidaFinal);
				partidaFinal = campeonato.getPartidas().get(2).selecionarPartida(idPartidaFinal);

				// ATUALIZAR O TIME 1 DA ULTIMA PARTIDA
				// String idPartidaFinal = partidaFinal.getIdPartida();
				partidaFinal.setTime1(time);
				partidaFinal.atualizarTime1(idPartidaFinal, idTime2);

				// ATUALIZAAR O TIME 2 DA ULTIMA PARTIDA
				partidaFinal.setTime2(time2);
				partidaFinal.atualizarTime2(idPartidaFinal, idTime3);

				System.out.println("idTime: " + idTime3 + " " + idTime2);

				// Selecionando os jogadores dos dois times

				// SETANDO OS TIMES NO ARRAY LIST DE TIMES DO CAMPEONATO
				campeonato.selecionarTimesCompeticao(idCampeonato);

				ArrayList<Time> timesCampeonato = new ArrayList<Time>();
				timesCampeonato.add(time);
				timesCampeonato.add(time2);

				campeonato.setTimes(timesCampeonato);

				System.out.println("idCampeonato time 1 da Partida Final :" + campeonato.getTimes().get(0).getNome());
				System.out.println("idCampeonato time 2 da Partida Final :" + campeonato.getTimes().get(1).getNome());

				time = campeonato.getTimes().get(0);
				time2 = campeonato.getTimes().get(1);

				timeDao.verTime(time);
				timeDao.verTime(time2);

				// MANDANDO OS TIMES VENCEDORES
//			request.setAttribute("campeonato", campeonato);
				request.setAttribute("partida", partidaFinal);
				request.setAttribute("jogadoresTime1", time.getJogadores());
				request.setAttribute("jogadoresTime2", time2.getJogadores());
				rd = request.getRequestDispatcher("jsp/escalarJogadores.jsp");

			} else if (pontosTime2 > pontosTime1 && pontosTime4 > pontosTime3) { // VENCEDOR PARTIDA 1 TIME 2 E VENCEDOR
																					// PARTIDA 2 TIME 4
				// MANDANDO O TIME2 (VENCEDOR DA PRIMEIRA PARTIDA)
				Time time = new Time();
				String idPartida = campeonato.getPartidas().get(0).getIdPartida();
				System.out.println(idPartida);
				campeonato.getPartidas().get(0).setTime2(time);
				campeonato.getPartidas().get(0).selecionarTime2(idPartida);
				String idTime2 = campeonato.getPartidas().get(0).getTime2().getIdTime();
				time.setIdTime(idTime2);
				timeDao.selecionarTime(time);

				// MANDANDO O TIME 4 (VENCEDOR DA PARTIDA 2)

				Time time2 = new Time();
				String idPartida2 = campeonato.getPartidas().get(1).getIdPartida();
				System.out.println("idSegunda Partida : " + idPartida);
				campeonato.getPartidas().get(1).setTime2(time2);
				campeonato.getPartidas().get(1).selecionarTime2(idPartida2);
				String idTime4 = campeonato.getPartidas().get(1).getTime2().getIdTime();
				time2.setIdTime(idTime4);
				timeDao.selecionarTime(time2);
				System.out.println("Vencedor partida 2" + idTime4);

				// MANDANDO OS TIMES VENCEDORES
				// MANDANDO OS TIMES VENCEDORES
				/////////////////////////////////////////////////////////////////////////
				Partida partidaFinal = new Partida();
				String idPartidaFinal = campeonato.getPartidas().get(2).getIdPartida();

				System.out.println("id da partida final: " + idPartidaFinal);
				partidaFinal = campeonato.getPartidas().get(2).selecionarPartida(idPartidaFinal);

				// ATUALIZAR O TIME 1 DA ULTIMA PARTIDA
				// String idPartidaFinal = partidaFinal.getIdPartida();
				partidaFinal.setTime1(time);
				partidaFinal.atualizarTime1(idPartidaFinal, idTime2);

				// ATUALIZAAR O TIME 2 DA ULTIMA PARTIDA
				partidaFinal.setTime2(time2);
				partidaFinal.atualizarTime2(idPartidaFinal, idTime4);

				System.out.println("idTime: " + idTime4 + " " + idTime2);

				// Selecionando os jogadores dos dois times

				// SETANDO OS TIMES NO ARRAY LIST DE TIMES DO CAMPEONATO
				campeonato.selecionarTimesCompeticao(idCampeonato);

				ArrayList<Time> timesCampeonato = new ArrayList<Time>();
				timesCampeonato.add(time);
				timesCampeonato.add(time2);

				campeonato.setTimes(timesCampeonato);

				System.out.println("idCampeonato time 1 da Partida Final :" + campeonato.getTimes().get(0).getNome());
				System.out.println("idCampeonato time 2 da Partida Final :" + campeonato.getTimes().get(1).getNome());

				time = campeonato.getTimes().get(0);
				time2 = campeonato.getTimes().get(1);

				timeDao.verTime(time);
				timeDao.verTime(time2);

				// MANDANDO OS TIMES VENCEDORES
				request.setAttribute("partida", partidaFinal);

				request.setAttribute("jogadoresTime1", time.getJogadores());
				request.setAttribute("jogadoresTime2", time2.getJogadores());
				rd = request.getRequestDispatcher("jsp/escalarJogadores.jsp");

			} else {
				rd = null;
			}
			rd.forward(request, response);

		}
	}

	protected void aplicarPremiacao(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int idCampeonato = Integer.parseInt(request.getParameter("idCampeonato"));

		Campeonato campeonato = new Campeonato();

		campeonato.selecionarCompeticao(idCampeonato);

		// selecionar as partidas do campeonato

		campeonato.selecionarPartidas(idCampeonato);
		// selecionar as pontuações dos times no campeonato // PARTIDA 1
		int pontosTime1 = campeonato.getPartidas().get(2).getPontosTime1();
		int pontosTime2 = campeonato.getPartidas().get(2).getPontosTime2();

		RequestDispatcher rd;
		if (pontosTime1 > pontosTime2) {

			Time time = new Time();
			String idPartida = campeonato.getPartidas().get(2).getIdPartida();
			campeonato.getPartidas().get(2).setTime1(time);
			campeonato.getPartidas().get(2).selecionarTime1(idPartida);
			String idTime1 = campeonato.getPartidas().get(2).getTime1().getIdTime();
			time.setIdTime(idTime1);
			timeDao.selecionarTime(time);
			
			campeonato.aplicarPremiacao(idCampeonato, time);
			
			System.out.println("ID : " + idTime1);

			request.setAttribute("timeCampeao", time);
			rd = request.getRequestDispatcher("jsp/campeao.jsp");

		} else if (pontosTime2 > pontosTime1) {
			Time time = new Time();
			String idPartida = campeonato.getPartidas().get(2).getIdPartida();
			campeonato.getPartidas().get(2).setTime2(time);
			campeonato.getPartidas().get(2).selecionarTime2(idPartida);
			String idTime2 = campeonato.getPartidas().get(2).getTime2().getIdTime();
			time.setIdTime(idTime2);
			timeDao.selecionarTime(time);

			System.out.println("ID : " + idTime2);

			request.setAttribute("timeCampeao", time);
			rd = request.getRequestDispatcher("jsp/campeao.jsp");

			request.setAttribute("campeao", time);
			rd = request.getRequestDispatcher("jsp/campeao.jsp");
		} else {
			rd = request.getRequestDispatcher("erro.html");
		}
		rd.forward(request, response);
	}

}