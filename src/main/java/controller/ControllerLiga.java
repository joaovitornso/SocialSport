package controller;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Liga;
import model.Esporte;
import model.EsporteDAO;
import model.Liga;
import model.LigaDAO;
import model.Local;
import model.LocalDAO;
import model.Partida;
import model.PartidaDAO;
import model.Time;
import model.TimeDAO;

@WebServlet(urlPatterns = { "/liga", "/insertLiga", "/selectLiga", "/updateLiga", "/selecionarTimesLiga",
		"/selecionarLiga", "/efetivarLiga", "/realizarLiga", "/selecionarEsporteLocalLiga",
		"/organizarPrimeiraPartidaLiga", "/organizarSegundaPartidaLiga", "/organizarTerceiraPartidaLiga",
		"/organizarQuintaPartidaLiga", "/organizarSextaPartidaLiga", "/organizarPartidaFinalLiga" })
public class ControllerLiga extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Liga liga = new Liga();
	LigaDAO ligaDao = new LigaDAO();
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
		String action = request.getServletPath();
		System.out.println(action);

		if (action.equals("/liga")) {
			ligas(request, response);
		} else if (action.equals("/insertLiga")) {
			novaLiga(request, response);
		} else if (action.equals("/selectLiga")) {
			listarLiga(request, response);
		} else if (action.equals("/updateLiga")) {
			editarLiga(request, response);
		} else if (action.equals("/deleteLiga")) {
			deletarLiga(request, response);
		} else if (action.equals("/selecionarTimesLiga")) {
			selecionarTimes(request, response);
		} else if (action.equals("/selecionarEsporteLocalLiga")) {
			selecionarEsporteLocalLiga(request, response);
		} else if (action.equals("/efetivarLiga")) {
			efetivarLiga(request, response);
		} else if (action.equals("/realizarLiga")) {
			realizarLiga(request, response);
		} else if (action.equals("/selecionarLiga")) {
			selecionarLiga(request, response);
		} else if (action.equals("/organizarPrimeiraPartidaLiga")) {
			organizarPrimeiraPartida(request, response);
		} else if (action.equals("/organizarSegundaPartidaLiga")) {
			organizarSegundaPartida(request, response);
		} else if (action.equals("/organizarTerceiraPartidaLiga")) {
			organizarTerceiraPartida(request, response);
		} else if (action.equals("/organizarQuartaPartidaLiga")) {
			organizarQuartaPartida(request, response);
		} else if (action.equals("/organizarQuintaPartidaLiga")) {
			organizarQuintaPartida(request, response);
		} else if (action.equals("/organizarSextaPartidaLiga")) {
			organizarSextaPartida(request, response);
		} else if (action.equals("/organizarPartidaFinalLiga")) {
			organizarPartidaFinal(request, response);
		} else {
			response.sendRedirect("index.html");
		}

	}

	protected void ligas(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ArrayList<Liga> listaLigas = liga.ligas();

		request.setAttribute("ligas", listaLigas);
		RequestDispatcher rd = request.getRequestDispatcher("jsp/liga.jsp");
		rd.forward(request, response);// encaminha o objeto lista ao documento partida.js
	}

	protected void novaLiga(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		liga.criar(request.getParameter("nome"), request.getParameter("medalha"), request.getParameter("trofeu"));

		// liga.inserirLiga(liga);

		response.sendRedirect("liga");
	}

	protected void listarLiga(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int idLiga = Integer.parseInt(request.getParameter("idLiga"));
		// Executar o método selecionarCampeonnato
		liga.listarCompeticao(idLiga);

		// Setar os atributos do formulário com o conteúdo de Liga.java
		request.setAttribute("idLiga", liga.getId());
		request.setAttribute("nome", liga.getNome());
		request.setAttribute("medalha", liga.getMedalhas());
		request.setAttribute("trofeu", liga.getTrofeu());
		// encaminhar ao documento editarLiga.jsp
		RequestDispatcher rd = request.getRequestDispatcher("jsp/editarLiga.jsp");
		rd.forward(request, response);

	}

	protected void editarLiga(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		liga.editarCompeticao(Integer.parseInt(request.getParameter("idLiga")), request.getParameter("nome"),
				request.getParameter("medalhas"), request.getParameter("trofeu"));

		response.sendRedirect("liga");

	}

	protected void deletarLiga(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Recebimento do id do liga a ser excluído (validador.js)
		int idLiga = Integer.parseInt(request.getParameter("idLiga"));
		// Setar a variável idLiga Liga.java
		/*
		 * liga.setId(idLiga);
		 */
		liga.deletarCompeticao(idLiga);
		// executar o método deletarLiga (LigaDAO) passando o objeto
		// liga
		/*
		 * ligaDao.deletarCampeoanto(idLiga);
		 */ // redirecionar para o documento liga.jsp (atualizando as alterações)
		response.sendRedirect("liga");
	}

	protected void selecionarEsporteLocalLiga(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int idLiga = Integer.parseInt(request.getParameter("idLiga"));
		System.out.println(idLiga);
		ArrayList<Esporte> listaEsporte = esporteDao.listarEsportes();

		ArrayList<Local> listaLocais = localDao.listarLocais();

		request.setAttribute("listaEsportes", listaEsporte);
		request.setAttribute("listaLocal", listaLocais);
		request.setAttribute("idLiga", idLiga);
		RequestDispatcher rd = request.getRequestDispatcher("jsp/selecionarEsporteLocalLiga.jsp");
		rd.forward(request, response);
	}

	protected void selecionarTimes(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int idLiga = Integer.parseInt(request.getParameter("idLiga"));
		String idEsporte = (request.getParameter("esporteLiga"));
		String idLocal = (request.getParameter("localLiga"));

		Liga liga = new Liga();
		liga.setId(idLiga);
		liga.selecionarCompeticao(idLiga);
//		
		String horarioLiga = request.getParameter("horarioLiga");
		String dataLiga = request.getParameter("dataLiga");

		String horarioLiga2 = request.getParameter("horarioLiga2");
		String dataLiga2 = request.getParameter("dataLiga2");
		String horarioLiga3 = request.getParameter("horarioLiga3");
		String dataLiga3 = request.getParameter("dataLiga3");
		String horarioLiga4 = request.getParameter("horarioLiga4");
		String dataLiga4 = request.getParameter("dataLiga4");
		String horarioLiga5 = request.getParameter("horarioLiga5");
		String dataLiga5 = request.getParameter("dataLiga5");
		String horarioLiga6 = request.getParameter("horarioLiga6");
		String dataLiga6 = request.getParameter("dataLiga6");

		String horarioLigaFinal = request.getParameter("horarioLigaFinal");
		String dataLigaFinal = request.getParameter("dataLigaFinal");

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
		partidaAux.novaPartidaCompeticao(partidaAux, dataLiga, horarioLiga, idEsporte, idLocal);

		System.out.println(partidaAux.getEsporte().getIdEsporte());
		// partida.setEsporte(esporte);
//		partida.setLocal(local);

		// Criando a segunda partida da liga no banco de dados
		Partida partida2 = new Partida();

		partida2.setEsporte(esporte);
		partida2.setLocal(local);

		partida2.novaPartidaCompeticao(partida2, dataLiga2, horarioLiga2, idEsporte, idLocal);

		// Criando a terceira partida da liga no banco de dados
		Partida partida3 = new Partida();

		partida3.setEsporte(esporte);
		partida3.setLocal(local);

		partida3.novaPartidaCompeticao(partida3, dataLiga3, horarioLiga3, idEsporte, idLocal);

		// Criando a quarta partida da liga no banco de dados
		Partida partida4 = new Partida();

		partida4.setEsporte(esporte);
		partida4.setLocal(local);

		partida4.novaPartidaCompeticao(partida4, dataLiga4, horarioLiga4, idEsporte, idLocal);

		// Criando a quinta partida da liga no banco de dados
		Partida partida5 = new Partida();

		partida5.setEsporte(esporte);
		partida5.setLocal(local);

		partida5.novaPartidaCompeticao(partida5, dataLiga5, horarioLiga5, idEsporte, idLocal);

		// Criando a sexta partida da liga no banco de dados
		Partida partida6 = new Partida();

		partida6.setEsporte(esporte);
		partida6.setLocal(local);

		partida6.novaPartidaCompeticao(partida6, dataLiga6, horarioLiga6, idEsporte, idLocal);

		// Criando a partida final no banco de dados
		Partida partidaFinal = new Partida();

		partidaFinal.setEsporte(esporte);
		partidaFinal.setLocal(local);

		partidaFinal.novaPartidaCompeticao(partidaFinal, dataLigaFinal, horarioLigaFinal, idEsporte, idLocal);

		// Recuperando o id das partidas atraves das datas
		String idPartida = partidaAux.recuperarId(dataLiga);
		String idPartida2 = partida2.recuperarId(dataLiga2);
		String idPartida3 = partida3.recuperarId(dataLiga3);
		String idPartida4 = partida4.recuperarId(dataLiga4);
		String idPartida5 = partida5.recuperarId(dataLiga5);
		String idPartida6 = partida6.recuperarId(dataLiga6);
		String idPartidaFinal = partidaFinal.recuperarId(dataLigaFinal);

		// setando o id do liga na partida
		partidaAux.atualizarLiga(idPartida, idLiga);
		partida2.atualizarLiga(idPartida2, idLiga);
		partida3.atualizarLiga(idPartida3, idLiga);
		partida4.atualizarLiga(idPartida4, idLiga);
		partida5.atualizarLiga(idPartida5, idLiga);
		partida6.atualizarLiga(idPartida6, idLiga);
		partidaFinal.atualizarLiga(idPartidaFinal, idLiga);
//
		System.out.println("id camp-1 partida 1 : " + partidaAux.getIdLiga());
		System.out.println("id camp-1: partida 2 : " + partida2.getIdLiga());
		System.out.println("id camp-Fina1: partida Final : " + partidaFinal.getIdLiga());

////		
//		liga.setEsporte(esporte);
//		liga.setLocal(local);
//		liga.atualizarEsporte(idLiga, idEsporte);
//		liga.atualizarLocal(idLiga, idLocal);
		esporteDao.verTimes(esporte);
//
		request.setAttribute("esporteTimes", esporte.getTimes());
		request.setAttribute("idLiga", idLiga);
		/* request.setAttribute("idEsporte", idEsporte); */
//		request.setAttribute("idLocal", idLocal);
		RequestDispatcher rd = request.getRequestDispatcher("jsp/selecionarTimesLiga.jsp");
		rd.forward(request, response);
	}

	protected void efetivarLiga(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int idLiga = Integer.parseInt(request.getParameter("idLiga"));

		Liga liga = new Liga();

		liga.selecionarCompeticao(idLiga);

		String idTime1 = request.getParameter("time1");
		String idTime2 = request.getParameter("time2");
		String idTime3 = request.getParameter("time3");
		String idTime4 = request.getParameter("time4");
		String idTime5 = request.getParameter("time5");
		String idTime6 = request.getParameter("time6");
		String idTime7 = request.getParameter("time7");
		String idTime8 = request.getParameter("time8");

		// Pegando os horario da pagina

		Time time1 = new Time();
		Time time2 = new Time();
		Time time3 = new Time();
		Time time4 = new Time();
		Time time5 = new Time();
		Time time6 = new Time();
		Time time7 = new Time();
		Time time8 = new Time();

		time1.setIdTime(idTime1);
		time2.setIdTime(idTime2);
		time3.setIdTime(idTime3);
		time4.setIdTime(idTime4);
		time5.setIdTime(idTime4);
		time6.setIdTime(idTime5);
		time7.setIdTime(idTime6);
		time8.setIdTime(idTime7);
		time8.setIdTime(idTime8);

		timeDao.selecionarTime(time1);
		timeDao.selecionarTime(time2);
		timeDao.selecionarTime(time3);
		timeDao.selecionarTime(time4);
		timeDao.selecionarTime(time5);
		timeDao.selecionarTime(time6);
		timeDao.selecionarTime(time7);
		timeDao.selecionarTime(time8);

		time1.inscreverLiga(idTime1, idLiga);
		time2.inscreverLiga(idTime2, idLiga);
		time3.inscreverLiga(idTime3, idLiga);
		time4.inscreverLiga(idTime4, idLiga);
		time5.inscreverLiga(idTime5, idLiga);
		time6.inscreverLiga(idTime6, idLiga);
		time7.inscreverLiga(idTime7, idLiga);
		time8.inscreverLiga(idTime8, idLiga);

		liga.selecionarPartidas(idLiga);

		String idPartida1 = liga.getPartidas().get(0).getIdPartida();
		String idPartida2 = liga.getPartidas().get(1).getIdPartida();
		String idPartida3 = liga.getPartidas().get(3).getIdPartida();
		String idPartida4 = liga.getPartidas().get(4).getIdPartida();

		liga.getPartidas().get(0).setTime1(time1);
		liga.getPartidas().get(0).setTime2(time2);
		liga.getPartidas().get(1).setTime1(time3);
		liga.getPartidas().get(1).setTime2(time4);
		liga.getPartidas().get(2).setTime1(time5);
		liga.getPartidas().get(2).setTime2(time6);
		liga.getPartidas().get(3).setTime1(time7);
		liga.getPartidas().get(3).setTime2(time8);

		liga.getPartidas().get(0).atualizarTime1(idPartida1, idTime1);
		liga.getPartidas().get(0).atualizarTime2(idPartida1, idTime2);

		liga.getPartidas().get(1).atualizarTime1(idPartida2, idTime3);
		liga.getPartidas().get(1).atualizarTime2(idPartida2, idTime4);

		liga.getPartidas().get(2).atualizarTime2(idPartida3, idTime5);
		liga.getPartidas().get(2).atualizarTime2(idPartida3, idTime6);

		liga.getPartidas().get(3).atualizarTime2(idPartida4, idTime7);
		liga.getPartidas().get(3).atualizarTime2(idPartida4, idTime8);

		////////////////////////////////////////////////////////////////
		ArrayList<Time> timesLiga = new ArrayList<Time>();
		timesLiga.add(time1);
		timesLiga.add(time2);
		timesLiga.add(time3);
		timesLiga.add(time4);
		timesLiga.add(time5);
		timesLiga.add(time6);
		timesLiga.add(time7);
		timesLiga.add(time8);
//		
		liga.setTimes(timesLiga);

//		liga.selecionarTime();
//		Liga liga = new Liga();
//		
//		liga.setarTimes(time1,time2,time3,time4);
//		

//		for(int i =0; i< liga.getTimes().size(); i++) {
//			System.out.println("id - " + liga.getTimes().get(i).getIdTime());
//			System.out.println("nome - " + liga.getTimes().get(i).getNome());
		request.setAttribute("liga", liga);
		RequestDispatcher rd = request.getRequestDispatcher("jsp/exibirDadosLiga.jsp");
		rd.forward(request, response);
//		}
	}

//	protected void verPartidas(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		String idPartida = request.getParameter("idPartida");
//		partida.setIdPartida(idPartida);
//
//		// Teste. Não está funcionando
//		// ligaDao.verLiga(liga);/// não funciona teste
//
//		request.setAttribute("liga", liga);
//
//		RequestDispatcher rd = request.getRequestDispatcher("jsp/partidasCampeonado.jsp");
//		rd.forward(request, response);
//
//	}
	protected void selecionarLiga(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<Liga> listaLigas = liga.ligas();

		request.setAttribute("ligas", listaLigas);
		RequestDispatcher rd = request.getRequestDispatcher("jsp/selecionarLiga.jsp");
		rd.forward(request, response);// encaminha o objeto lista ao documento partida.js

	}

	protected void realizarLiga(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int idLiga = Integer.parseInt(request.getParameter("ligaRealizada"));
		request.setAttribute("idLiga", idLiga);
		RequestDispatcher rd = request.getRequestDispatcher("jsp/programarPartidasLiga.jsp");
		rd.forward(request, response);
	}

	protected void organizarPrimeiraPartida(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int idLiga = Integer.parseInt(request.getParameter("idLiga"));

		Liga liga = new Liga();

		liga.selecionarCompeticao(idLiga);

		liga.selecionarTimesCompeticao(idLiga);

		liga.selecionarPartidas(idLiga);

		String idPartida = liga.getPartidas().get(0).getIdPartida();

		String idTime1 = liga.getTimes().get(0).getIdTime();
		String idTime2 = liga.getTimes().get(1).getIdTime();

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
		int idLiga = Integer.parseInt(request.getParameter("idLiga"));

		Liga liga = new Liga();

		liga.selecionarCompeticao(idLiga);

		liga.selecionarTimesCompeticao(idLiga);

		liga.selecionarPartidas(idLiga);

		String idPartida2 = liga.getPartidas().get(1).getIdPartida();
		String idTime3 = liga.getTimes().get(2).getIdTime();
		String idTime4 = liga.getTimes().get(3).getIdTime();

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

	protected void organizarTerceiraPartida(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int idLiga = Integer.parseInt(request.getParameter("idLiga"));

		Liga liga = new Liga();

		liga.selecionarCompeticao(idLiga);

		liga.selecionarTimesCompeticao(idLiga);

		liga.selecionarPartidas(idLiga);

		String idPartida3 = liga.getPartidas().get(2).getIdPartida();
		String idTime5 = liga.getTimes().get(4).getIdTime();
		String idTime6 = liga.getTimes().get(5).getIdTime();

		Time time5 = new Time();
		Time time6 = new Time();

		time5.setIdTime(idTime5);
		time6.setIdTime(idTime6);

		timeDao.selecionarTime(time5);
		timeDao.selecionarTime(time6);

		timeDao.verTime(time5);
		timeDao.verTime(time6);

		Partida partida3 = new Partida();

		partida3.selecionarPartida(idPartida3);

		partida3.setTime1(time5);
		partida3.setTime2(time6);

		request.setAttribute("jogadoresTime1", time5.getJogadores());
		request.setAttribute("jogadoresTime2", time6.getJogadores());
		request.setAttribute("partida", partida3);

		RequestDispatcher rd = request.getRequestDispatcher("jsp/escalarJogadores.jsp");
		rd.forward(request, response);
	}

	protected void organizarQuartaPartida(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int idLiga = Integer.parseInt(request.getParameter("idLiga"));

		Liga liga = new Liga();

		liga.selecionarCompeticao(idLiga);

		liga.selecionarTimesCompeticao(idLiga);

		liga.selecionarPartidas(idLiga);

		String idPartida4 = liga.getPartidas().get(3).getIdPartida();
		String idTime7 = liga.getTimes().get(6).getIdTime();
		String idTime8 = liga.getTimes().get(7).getIdTime();

		Time time7 = new Time();
		Time time8 = new Time();

		time7.setIdTime(idTime7);
		time8.setIdTime(idTime8);

		timeDao.selecionarTime(time7);
		timeDao.selecionarTime(time8);

		timeDao.verTime(time7);
		timeDao.verTime(time8);

		Partida partida4 = new Partida();

		partida4.selecionarPartida(idPartida4);

		partida4.setTime1(time7);
		partida4.setTime2(time8);

		request.setAttribute("jogadoresTime1", time7.getJogadores());
		request.setAttribute("jogadoresTime2", time8.getJogadores());
		request.setAttribute("partida", partida4);

		RequestDispatcher rd = request.getRequestDispatcher("jsp/escalarJogadores.jsp");
		rd.forward(request, response);
	}

	protected void organizarQuintaPartida(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int idLiga = Integer.parseInt(request.getParameter("idLiga"));

		Liga liga = new Liga();

		liga.selecionarCompeticao(idLiga);
		liga.selecionarPartidas(idLiga);

		int pontosTime1 = liga.getPartidas().get(0).getPontosTime1();
		int pontosTime2 = liga.getPartidas().get(0).getPontosTime2();

		int pontosTime3 = liga.getPartidas().get(1).getPontosTime1();
		int pontosTime4 = liga.getPartidas().get(1).getPontosTime2();

		if (pontosTime1 == 0 && pontosTime2 == 0 && pontosTime3 == 0 && pontosTime4 == 0) {
			RequestDispatcher rd = request.getRequestDispatcher("erro.html");
			rd.forward(request, response);
		} else {
			RequestDispatcher rd;
			if (pontosTime1 > pontosTime2 && pontosTime3 > pontosTime4) {
				// MANDANDO O TIME1 (VENCEDOR DA PRIMEIRA PARTIDA)
				Time time = new Time();
				String idPartida = liga.getPartidas().get(0).getIdPartida();
				System.out.println(idPartida);
				liga.getPartidas().get(0).setTime1(time);
				liga.getPartidas().get(0).selecionarTime1(idPartida);
				String idTime1 = liga.getPartidas().get(0).getTime1().getIdTime();
				time.setIdTime(idTime1);
				timeDao.selecionarTime(time);

				System.out.println("Vencedor partida 1= " + idTime1);
				// MANDANDO O TIME 3 (VENCEDOR DA PARTIDA 2)

				Time time2 = new Time();
				String idPartida2 = liga.getPartidas().get(1).getIdPartida();
				System.out.println(idPartida2);
				liga.getPartidas().get(1).setTime1(time2);
				liga.getPartidas().get(1).selecionarTime1(idPartida2);
				String idTime3 = liga.getPartidas().get(1).getTime1().getIdTime();
				System.out.println("Vencedor partida 2 " + idTime3);
				time2.setIdTime(idTime3);
				timeDao.selecionarTime(time2);

				Partida QuintaPartida = new Partida();
				String idQuintaPartida = liga.getPartidas().get(4).getIdPartida();

				QuintaPartida = liga.getPartidas().get(4).selecionarPartida(idQuintaPartida);

				QuintaPartida.setTime1(time);
				QuintaPartida.atualizarTime1(idQuintaPartida, idTime1);

				QuintaPartida.setTime2(time2);
				System.out.println("idTime: " + idTime3 + " " + idTime1);
				QuintaPartida.atualizarTime2(idQuintaPartida, idTime3);

				liga.selecionarTimesCompeticao(idLiga);

				ArrayList<Time> timesLiga = new ArrayList<Time>();

				timesLiga.add(time);
				timesLiga.add(time2);

				liga.setTimes(timesLiga);

				time = liga.getTimes().get(0);
				time2 = liga.getTimes().get(1);

				timeDao.verTime(time);
				timeDao.verTime(time2);
				request.setAttribute("partida", QuintaPartida);
				request.setAttribute("jogadoresTime1", time.getJogadores());
				request.setAttribute("jogadoresTime2", time2.getJogadores());
				rd = request.getRequestDispatcher("jsp/escalarJogadores.jsp");

			} else if (pontosTime1 > pontosTime2 && pontosTime4 > pontosTime3) {
				Time time = new Time();
				String idPartida = liga.getPartidas().get(0).getIdPartida();
				System.out.println(idPartida);
				liga.getPartidas().get(0).setTime1(time);
				liga.getPartidas().get(0).selecionarTime1(idPartida);
				String idTime1 = liga.getPartidas().get(0).getTime1().getIdTime();
				time.setIdTime(idTime1);
				timeDao.selecionarTime(time);

				Time time2 = new Time();
				String idPartida2 = liga.getPartidas().get(1).getIdPartida();
				System.out.println(idPartida);
				liga.getPartidas().get(1).setTime2(time2);
				liga.getPartidas().get(1).selecionarTime2(idPartida2);
				String idTime4 = liga.getPartidas().get(1).getTime2().getIdTime();
				time2.setIdTime(idTime4);
				timeDao.selecionarTime(time2);
				System.out.println("Vencedor partida 2" + idTime4);

				Partida QuintaPartida = new Partida();
				String idQuintaPartida = liga.getPartidas().get(4).getIdPartida();

				QuintaPartida = liga.getPartidas().get(4).selecionarPartida(idQuintaPartida);

				QuintaPartida.setTime1(time);
				QuintaPartida.atualizarTime1(idQuintaPartida, idTime1);

				QuintaPartida.setTime2(time2);
				System.out.println("idTime: " + idTime4 + " " + idTime1);
				QuintaPartida.atualizarTime2(idQuintaPartida, idTime4);

				liga.selecionarTimesCompeticao(idLiga);

				ArrayList<Time> timesLiga = new ArrayList<Time>();

				timesLiga.add(time);
				timesLiga.add(time2);

				liga.setTimes(timesLiga);

				time = liga.getTimes().get(0);
				time2 = liga.getTimes().get(1);

				timeDao.verTime(time);
				timeDao.verTime(time2);
				request.setAttribute("partida", QuintaPartida);
				request.setAttribute("jogadoresTime1", time.getJogadores());
				request.setAttribute("jogadoresTime2", time2.getJogadores());
				rd = request.getRequestDispatcher("jsp/escalarJogadores.jsp");
			} else if (pontosTime2 > pontosTime1 && pontosTime3 > pontosTime4) {
				Time time = new Time();
				String idPartida = liga.getPartidas().get(0).getIdPartida();
				System.out.println(idPartida);
				liga.getPartidas().get(0).setTime2(time);
				liga.getPartidas().get(0).selecionarTime2(idPartida);
				String idTime2 = liga.getPartidas().get(0).getTime2().getIdTime();
				time.setIdTime(idTime2);
				timeDao.selecionarTime(time);

				Time time2 = new Time();
				String idPartida2 = liga.getPartidas().get(1).getIdPartida();
				System.out.println("idSegunda Partida : " + idPartida);
				liga.getPartidas().get(1).setTime1(time2);
				liga.getPartidas().get(1).selecionarTime1(idPartida2);
				String idTime3 = liga.getPartidas().get(1).getTime1().getIdTime();
				time2.setIdTime(idTime3);
				timeDao.selecionarTime(time2);

				Partida QuintaPartida = new Partida();
				String idQuintaPartida = liga.getPartidas().get(4).getIdPartida();

				QuintaPartida = liga.getPartidas().get(4).selecionarPartida(idQuintaPartida);

				QuintaPartida.setTime1(time);
				QuintaPartida.atualizarTime1(idQuintaPartida, idTime2);

				QuintaPartida.setTime2(time2);
				System.out.println("idTime: " + idTime3 + " " + idTime2);
				QuintaPartida.atualizarTime2(idQuintaPartida, idTime3);

				liga.selecionarTimesCompeticao(idLiga);

				ArrayList<Time> timesLiga = new ArrayList<Time>();

				timesLiga.add(time);
				timesLiga.add(time2);

				liga.setTimes(timesLiga);

				time = liga.getTimes().get(0);
				time2 = liga.getTimes().get(1);

				timeDao.verTime(time);
				timeDao.verTime(time2);
				request.setAttribute("partida", QuintaPartida);
				request.setAttribute("jogadoresTime1", time.getJogadores());
				request.setAttribute("jogadoresTime2", time2.getJogadores());
				rd = request.getRequestDispatcher("jsp/escalarJogadores.jsp");

			} else if (pontosTime2 > pontosTime1 && pontosTime4 > pontosTime3) {

				Time time = new Time();
				String idPartida = liga.getPartidas().get(0).getIdPartida();
				System.out.println(idPartida);
				liga.getPartidas().get(0).setTime2(time);
				liga.getPartidas().get(0).selecionarTime2(idPartida);
				String idTime2 = liga.getPartidas().get(0).getTime2().getIdTime();
				time.setIdTime(idTime2);
				timeDao.selecionarTime(time);

				// MANDANDO O TIME 4 (VENCEDOR DA PARTIDA 2)

				Time time2 = new Time();
				String idPartida2 = liga.getPartidas().get(1).getIdPartida();
				System.out.println("idSegunda Partida : " + idPartida);
				liga.getPartidas().get(1).setTime2(time2);
				liga.getPartidas().get(1).selecionarTime2(idPartida2);
				String idTime4 = liga.getPartidas().get(1).getTime2().getIdTime();
				time2.setIdTime(idTime4);
				timeDao.selecionarTime(time2);
				System.out.println("Vencedor partida 2" + idTime4);

				Partida QuintaPartida = new Partida();
				String idQuintaPartida = liga.getPartidas().get(4).getIdPartida();

				QuintaPartida = liga.getPartidas().get(4).selecionarPartida(idQuintaPartida);

				QuintaPartida.setTime1(time);
				QuintaPartida.atualizarTime1(idQuintaPartida, idTime2);

				QuintaPartida.setTime2(time2);
				System.out.println("idTime: " + idTime4 + " " + idTime2);
				QuintaPartida.atualizarTime2(idQuintaPartida, idTime4);

				liga.selecionarTimesCompeticao(idLiga);

				ArrayList<Time> timesLiga = new ArrayList<Time>();

				timesLiga.add(time);
				timesLiga.add(time2);

				liga.setTimes(timesLiga);

				time = liga.getTimes().get(0);
				time2 = liga.getTimes().get(1);

				timeDao.verTime(time);
				timeDao.verTime(time2);
				request.setAttribute("partida", QuintaPartida);
				request.setAttribute("jogadoresTime1", time.getJogadores());
				request.setAttribute("jogadoresTime2", time2.getJogadores());
				rd = request.getRequestDispatcher("jsp/escalarJogadores.jsp");

			} else {
				rd = null;
			}
			rd.forward(request, response);
		}
	}

	protected void organizarSextaPartida(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int idLiga = Integer.parseInt(request.getParameter("idLiga"));

		Liga liga = new Liga();

		liga.selecionarCompeticao(idLiga);
		liga.selecionarPartidas(idLiga);

		int pontosTime5 = liga.getPartidas().get(2).getPontosTime1();
		int pontosTime6 = liga.getPartidas().get(2).getPontosTime2();

		int pontosTime7 = liga.getPartidas().get(3).getPontosTime1();
		int pontosTime8 = liga.getPartidas().get(3).getPontosTime2();

		if (pontosTime5 == 0 && pontosTime6 == 0 && pontosTime7 == 0 && pontosTime8 == 0) {
			RequestDispatcher rd = request.getRequestDispatcher("erro.html");
			rd.forward(request, response);
		} else {
			RequestDispatcher rd;
			if (pontosTime5 > pontosTime6 && pontosTime7 > pontosTime8) {
				// MANDANDO O TIME1 (VENCEDOR DA PRIMEIRA PARTIDA)
				Time time = new Time();
				String idPartida3 = liga.getPartidas().get(2).getIdPartida();
				System.out.println(idPartida3);
				liga.getPartidas().get(2).setTime1(time);
				liga.getPartidas().get(2).selecionarTime1(idPartida3);
				String idTime5 = liga.getPartidas().get(2).getTime1().getIdTime();
				time.setIdTime(idTime5);
				timeDao.selecionarTime(time);

				System.out.println("Vencedor partida 1= " + idTime5);
				// MANDANDO O TIME 3 (VENCEDOR DA PARTIDA 2)

				Time time2 = new Time();
				String idPartida4 = liga.getPartidas().get(3).getIdPartida();
				System.out.println(idPartida4);
				liga.getPartidas().get(3).setTime1(time2);
				liga.getPartidas().get(3).selecionarTime1(idPartida4);
				String idTime7 = liga.getPartidas().get(3).getTime1().getIdTime();
				System.out.println("Vencedor partida 2 " + idTime7);
				time2.setIdTime(idTime7);
				timeDao.selecionarTime(time2);

				Partida SextaPartida = new Partida();
				String idSextaPartida = liga.getPartidas().get(5).getIdPartida();

				SextaPartida = liga.getPartidas().get(5).selecionarPartida(idSextaPartida);

				SextaPartida.setTime1(time);
				SextaPartida.atualizarTime1(idSextaPartida, idTime5);

				SextaPartida.setTime2(time2);
				System.out.println("idTime: " + idTime7 + " " + idTime5);
				SextaPartida.atualizarTime2(idSextaPartida, idTime7);

				liga.selecionarTimesCompeticao(idLiga);

				ArrayList<Time> timesLiga = new ArrayList<Time>();

				timesLiga.add(time);
				timesLiga.add(time2);

				liga.setTimes(timesLiga);

				time = liga.getTimes().get(0);
				time2 = liga.getTimes().get(1);

				timeDao.verTime(time);
				timeDao.verTime(time2);
				request.setAttribute("partida", SextaPartida);
				request.setAttribute("jogadoresTime1", time.getJogadores());
				request.setAttribute("jogadoresTime2", time2.getJogadores());
				rd = request.getRequestDispatcher("jsp/escalarJogadores.jsp");

			} else if (pontosTime5 > pontosTime6 && pontosTime8 > pontosTime7) {
				Time time = new Time();
				String idPartida3 = liga.getPartidas().get(2).getIdPartida();
				System.out.println(idPartida3);
				liga.getPartidas().get(2).setTime1(time);
				liga.getPartidas().get(2).selecionarTime1(idPartida3);
				String idTime5 = liga.getPartidas().get(2).getTime1().getIdTime();
				time.setIdTime(idTime5);
				timeDao.selecionarTime(time);

				Time time2 = new Time();
				String idPartida4 = liga.getPartidas().get(3).getIdPartida();
				System.out.println(idPartida4);
				liga.getPartidas().get(3).setTime2(time2);
				liga.getPartidas().get(3).selecionarTime2(idPartida4);
				String idTime8 = liga.getPartidas().get(3).getTime2().getIdTime();
				time2.setIdTime(idTime8);
				timeDao.selecionarTime(time2);
				System.out.println("Vencedor partida 2" + idTime8);

				Partida SextaPartida = new Partida();
				String idSextaPartida = liga.getPartidas().get(5).getIdPartida();

				SextaPartida = liga.getPartidas().get(5).selecionarPartida(idSextaPartida);

				SextaPartida.setTime1(time);
				SextaPartida.atualizarTime1(idSextaPartida, idTime5);

				SextaPartida.setTime2(time2);
				System.out.println("idTime: " + idTime8 + " " + idTime5);
				SextaPartida.atualizarTime2(idSextaPartida, idTime8);

				liga.selecionarTimesCompeticao(idLiga);

				ArrayList<Time> timesLiga = new ArrayList<Time>();

				timesLiga.add(time);
				timesLiga.add(time2);

				liga.setTimes(timesLiga);

				time = liga.getTimes().get(0);
				time2 = liga.getTimes().get(1);

				timeDao.verTime(time);
				timeDao.verTime(time2);
				request.setAttribute("partida", SextaPartida);
				request.setAttribute("jogadoresTime1", time.getJogadores());
				request.setAttribute("jogadoresTime2", time2.getJogadores());
				rd = request.getRequestDispatcher("jsp/escalarJogadores.jsp");
			} else if (pontosTime6 > pontosTime5 && pontosTime7 > pontosTime8) {
				Time time = new Time();
				String idPartida3 = liga.getPartidas().get(2).getIdPartida();
				System.out.println(idPartida3);
				liga.getPartidas().get(2).setTime2(time);
				liga.getPartidas().get(2).selecionarTime2(idPartida3);
				String idTime6 = liga.getPartidas().get(2).getTime2().getIdTime();
				time.setIdTime(idTime6);
				timeDao.selecionarTime(time);

				Time time2 = new Time();
				String idPartida4 = liga.getPartidas().get(3).getIdPartida();
				System.out.println("idSegunda Partida : " + idPartida4);
				liga.getPartidas().get(3).setTime1(time2);
				liga.getPartidas().get(3).selecionarTime1(idPartida4);
				String idTime7 = liga.getPartidas().get(3).getTime1().getIdTime();
				time2.setIdTime(idTime7);
				timeDao.selecionarTime(time2);

				Partida SextaPartida = new Partida();
				String idSextaPartida = liga.getPartidas().get(5).getIdPartida();

				SextaPartida = liga.getPartidas().get(5).selecionarPartida(idSextaPartida);

				SextaPartida.setTime1(time);
				SextaPartida.atualizarTime1(idSextaPartida, idTime6);

				SextaPartida.setTime2(time2);
				System.out.println("idTime: " + idTime7 + " " + idTime6);
				SextaPartida.atualizarTime2(idSextaPartida, idTime7);

				liga.selecionarTimesCompeticao(idLiga);

				ArrayList<Time> timesLiga = new ArrayList<Time>();

				timesLiga.add(time);
				timesLiga.add(time2);

				liga.setTimes(timesLiga);

				time = liga.getTimes().get(0);
				time2 = liga.getTimes().get(1);

				timeDao.verTime(time);
				timeDao.verTime(time2);
				request.setAttribute("partida", SextaPartida);
				request.setAttribute("jogadoresTime1", time.getJogadores());
				request.setAttribute("jogadoresTime2", time2.getJogadores());
				rd = request.getRequestDispatcher("jsp/escalarJogadores.jsp");

			} else if (pontosTime6 > pontosTime5 && pontosTime8 > pontosTime7) {

				Time time = new Time();
				String idPartida3 = liga.getPartidas().get(2).getIdPartida();
				System.out.println(idPartida3);
				liga.getPartidas().get(2).setTime2(time);
				liga.getPartidas().get(2).selecionarTime2(idPartida3);
				String idTime6 = liga.getPartidas().get(2).getTime2().getIdTime();
				time.setIdTime(idTime6);
				timeDao.selecionarTime(time);

				// MANDANDO O TIME 4 (VENCEDOR DA PARTIDA 2)

				Time time2 = new Time();
				String idPartida4 = liga.getPartidas().get(3).getIdPartida();
				System.out.println("idSegunda Partida : " + idPartida4);
				liga.getPartidas().get(3).setTime2(time2);
				liga.getPartidas().get(3).selecionarTime2(idPartida4);
				String idTime8 = liga.getPartidas().get(3).getTime2().getIdTime();
				time2.setIdTime(idTime8);
				timeDao.selecionarTime(time2);
				System.out.println("Vencedor partida 2" + idTime8);

				Partida SextaPartida = new Partida();
				String idSextaPartida = liga.getPartidas().get(5).getIdPartida();

				SextaPartida = liga.getPartidas().get(5).selecionarPartida(idSextaPartida);

				SextaPartida.setTime1(time);
				SextaPartida.atualizarTime1(idSextaPartida, idTime6);

				SextaPartida.setTime2(time2);
				System.out.println("idTime: " + idTime8 + " " + idTime6);
				SextaPartida.atualizarTime2(idSextaPartida, idTime8);

				liga.selecionarTimesCompeticao(idLiga);

				ArrayList<Time> timesLiga = new ArrayList<Time>();

				timesLiga.add(time);
				timesLiga.add(time2);

				liga.setTimes(timesLiga);

				time = liga.getTimes().get(0);
				time2 = liga.getTimes().get(1);

				timeDao.verTime(time);
				timeDao.verTime(time2);
				request.setAttribute("partida", SextaPartida);
				request.setAttribute("jogadoresTime1", time.getJogadores());
				request.setAttribute("jogadoresTime2", time2.getJogadores());
				rd = request.getRequestDispatcher("jsp/escalarJogadores.jsp");

			} else {
				rd = null;
			}
			rd.forward(request, response);
		}
	}

	protected void organizarPartidaFinal(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int idLiga = Integer.parseInt(request.getParameter("idLiga"));
		System.out.println("IdLiga" + idLiga);

		Liga liga = new Liga();

		liga.selecionarCompeticao(idLiga);

		// selecionar as partidas do liga

		liga.selecionarPartidas(idLiga);
		// selecionar as pontuações dos times no liga // PARTIDA 1
		int pontosTime1 = liga.getPartidas().get(4).getPontosTime1();
		int pontosTime2 = liga.getPartidas().get(4).getPontosTime2();

		System.out.println("pontos time 1: " + pontosTime1 + " Pontos time 2: " + pontosTime2);

		// PARTIDA2
		int pontosTime3 = liga.getPartidas().get(5).getPontosTime1();
		int pontosTime4 = liga.getPartidas().get(5).getPontosTime2();

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
				String idPartida5 = liga.getPartidas().get(4).getIdPartida();
				System.out.println(idPartida5);
				liga.getPartidas().get(4).setTime1(time);
				liga.getPartidas().get(4).selecionarTime1(idPartida5);
				String idTime1 = liga.getPartidas().get(4).getTime1().getIdTime();
				time.setIdTime(idTime1);
				timeDao.selecionarTime(time);

				System.out.println("Vencedor partida 1= " + idTime1);
				// MANDANDO O TIME 3 (VENCEDOR DA PARTIDA 2)

				Time time2 = new Time();
				String idPartida6 = liga.getPartidas().get(5).getIdPartida();
				System.out.println(idPartida6);
				liga.getPartidas().get(5).setTime1(time2);
				liga.getPartidas().get(5).selecionarTime1(idPartida6);
				String idTime3 = liga.getPartidas().get(5).getTime1().getIdTime();
				System.out.println("Vencedor partida 2 " + idTime3);
				time2.setIdTime(idTime3);
				timeDao.selecionarTime(time2);

				// Setar o time no banco de dados
				///////////////////////////////////////////////
				// SELECIONAR A PARTIDA FINAL DO CAMPEONATO

				Partida partidaFinal = new Partida();
				String idPartidaFinal = liga.getPartidas().get(6).getIdPartida();

				System.out.println("id da partida final: " + idPartidaFinal);
				partidaFinal = liga.getPartidas().get(6).selecionarPartida(idPartidaFinal);

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
				liga.selecionarTimesCompeticao(idLiga);

				ArrayList<Time> timesLiga = new ArrayList<Time>();
				timesLiga.add(time);
				timesLiga.add(time2);

				liga.setTimes(timesLiga);

				System.out.println("idLiga time 1 da Partida Final :" + liga.getTimes().get(0).getNome());
				System.out.println("idLiga time 2 da Partida Final :" + liga.getTimes().get(1).getNome());

				time = liga.getTimes().get(0);
				time2 = liga.getTimes().get(1);

				timeDao.verTime(time);
				timeDao.verTime(time2);

				// MANDANDO OS TIMES VENCEDORES
//			request.setAttribute("liga", liga);
				request.setAttribute("partida", partidaFinal);
				request.setAttribute("jogadoresTime1", time.getJogadores());
				request.setAttribute("jogadoresTime2", time2.getJogadores());
				rd = request.getRequestDispatcher("jsp/escalarJogadores.jsp");

			} else if (pontosTime1 > pontosTime2 && pontosTime4 > pontosTime3) {
				// MANDANDO O TIME1 (VENCEDOR DA PRIMEIRA PARTIDA)
				Time time = new Time();
				String idPartida5 = liga.getPartidas().get(4).getIdPartida();
				System.out.println(idPartida5);
				liga.getPartidas().get(4).setTime1(time);
				liga.getPartidas().get(4).selecionarTime1(idPartida5);
				String idTime1 = liga.getPartidas().get(4).getTime1().getIdTime();
				time.setIdTime(idTime1);
				timeDao.selecionarTime(time);

				// MANDANDO O TIME 4 (VENCEDOR DA PARTIDA 2)

				Time time2 = new Time();
				String idPartida6 = liga.getPartidas().get(5).getIdPartida();
				liga.getPartidas().get(5).setTime2(time2);
				liga.getPartidas().get(5).selecionarTime2(idPartida6);
				String idTime4 = liga.getPartidas().get(5).getTime2().getIdTime();
				time2.setIdTime(idTime4);
				timeDao.selecionarTime(time2);
				System.out.println("Vencedor partida 2" + idTime4);

				/////////////////////////////////////////////////////////////////////////
				Partida partidaFinal = new Partida();
				String idPartidaFinal = liga.getPartidas().get(6).getIdPartida();

				System.out.println("id da partida final: " + idPartidaFinal);
				partidaFinal = liga.getPartidas().get(6).selecionarPartida(idPartidaFinal);

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
				liga.selecionarTimesCompeticao(idLiga);

				ArrayList<Time> timesLiga = new ArrayList<Time>();
				timesLiga.add(time);
				timesLiga.add(time2);

				liga.setTimes(timesLiga);

				System.out.println("idLiga time 1 da Partida Final :" + liga.getTimes().get(0).getNome());
				System.out.println("idLiga time 2 da Partida Final :" + liga.getTimes().get(1).getNome());

				time = liga.getTimes().get(0);
				time2 = liga.getTimes().get(1);

				timeDao.verTime(time);
				timeDao.verTime(time2);

				// MANDANDO OS TIMES VENCEDORES
//			request.setAttribute("liga", liga);
				request.setAttribute("partida", partidaFinal);
				request.setAttribute("jogadoresTime1", time.getJogadores());
				request.setAttribute("jogadoresTime2", time2.getJogadores());
				rd = request.getRequestDispatcher("jsp/escalarJogadores.jsp");

				// MANDANDO OS TIMES VENCEDORES
//			request.setAttribute("liga", liga);
//			request.setAttribute("TimeVencedorPartida1", time);
//			request.setAttribute("TimeVencedorPartida2", time2);
//			rd = request.getRequestDispatcher("jsp/exibirPartidaFinal.jsp");

			} else if (pontosTime2 > pontosTime1 && pontosTime3 > pontosTime4) {
				// MANDANDO O TIME2 (VENCEDOR DA PRIMEIRA PARTIDA)
				Time time = new Time();
				String idPartida5 = liga.getPartidas().get(4).getIdPartida();
				System.out.println(idPartida5);
				liga.getPartidas().get(4).setTime2(time);
				liga.getPartidas().get(4).selecionarTime2(idPartida5);
				String idTime2 = liga.getPartidas().get(4).getTime2().getIdTime();
				time.setIdTime(idTime2);
				timeDao.selecionarTime(time);

				// MANDANDO O TIME 3 (VENCEDOR DA PARTIDA 2)

				Time time2 = new Time();
				String idPartida6 = liga.getPartidas().get(5).getIdPartida();
				liga.getPartidas().get(5).setTime1(time2);
				liga.getPartidas().get(5).selecionarTime1(idPartida6);
				String idTime3 = liga.getPartidas().get(5).getTime1().getIdTime();
				time2.setIdTime(idTime3);
				timeDao.selecionarTime(time2);
				System.out.println("Vencedor partida 2" + idTime3);

				// MANDANDO OS TIMES VENCEDORES
				/////////////////////////////////////////////////////////////////////////
				Partida partidaFinal = new Partida();
				String idPartidaFinal = liga.getPartidas().get(6).getIdPartida();

				System.out.println("id da partida final: " + idPartidaFinal);
				partidaFinal = liga.getPartidas().get(6).selecionarPartida(idPartidaFinal);

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
				liga.selecionarTimesCompeticao(idLiga);

				ArrayList<Time> timesLiga = new ArrayList<Time>();
				timesLiga.add(time);
				timesLiga.add(time2);

				liga.setTimes(timesLiga);

				System.out.println("idLiga time 1 da Partida Final :" + liga.getTimes().get(0).getNome());
				System.out.println("idLiga time 2 da Partida Final :" + liga.getTimes().get(1).getNome());

				time = liga.getTimes().get(0);
				time2 = liga.getTimes().get(1);

				timeDao.verTime(time);
				timeDao.verTime(time2);

				// MANDANDO OS TIMES VENCEDORES
//			request.setAttribute("liga", liga);
				request.setAttribute("partida", partidaFinal);
				request.setAttribute("jogadoresTime1", time.getJogadores());
				request.setAttribute("jogadoresTime2", time2.getJogadores());
				rd = request.getRequestDispatcher("jsp/escalarJogadores.jsp");

			} else if (pontosTime2 > pontosTime1 && pontosTime4 > pontosTime3) { // VENCEDOR PARTIDA 1 TIME 2 E VENCEDOR
																					// PARTIDA 2 TIME 4
				// MANDANDO O TIME2 (VENCEDOR DA PRIMEIRA PARTIDA)
				Time time = new Time();
				String idPartida5 = liga.getPartidas().get(4).getIdPartida();
				System.out.println(idPartida5);
				liga.getPartidas().get(4).setTime2(time);
				liga.getPartidas().get(4).selecionarTime2(idPartida5);
				String idTime2 = liga.getPartidas().get(4).getTime2().getIdTime();
				time.setIdTime(idTime2);
				timeDao.selecionarTime(time);

				// MANDANDO O TIME 4 (VENCEDOR DA PARTIDA 2)

				Time time2 = new Time();
				String idPartida2 = liga.getPartidas().get(5).getIdPartida();
				liga.getPartidas().get(5).setTime2(time2);
				liga.getPartidas().get(5).selecionarTime2(idPartida2);
				String idTime4 = liga.getPartidas().get(5).getTime2().getIdTime();
				time2.setIdTime(idTime4);
				timeDao.selecionarTime(time2);
				System.out.println("Vencedor partida 2" + idTime4);

				// MANDANDO OS TIMES VENCEDORES
				// MANDANDO OS TIMES VENCEDORES
				/////////////////////////////////////////////////////////////////////////
				Partida partidaFinal = new Partida();
				String idPartidaFinal = liga.getPartidas().get(6).getIdPartida();

				System.out.println("id da partida final: " + idPartidaFinal);
				partidaFinal = liga.getPartidas().get(6).selecionarPartida(idPartidaFinal);

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
				liga.selecionarTimesCompeticao(idLiga);

				ArrayList<Time> timesLiga = new ArrayList<Time>();
				timesLiga.add(time);
				timesLiga.add(time2);

				liga.setTimes(timesLiga);

				System.out.println("idLiga time 1 da Partida Final :" + liga.getTimes().get(0).getNome());
				System.out.println("idLiga time 2 da Partida Final :" + liga.getTimes().get(1).getNome());

				time = liga.getTimes().get(0);
				time2 = liga.getTimes().get(1);

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
		int idLiga = Integer.parseInt(request.getParameter("idLiga"));

		Liga liga = new Liga();

		liga.selecionarCompeticao(idLiga);

		// selecionar as partidas do liga

		liga.selecionarPartidas(idLiga);
		// selecionar as pontuações dos times no liga // PARTIDA 1
		int pontosTime1 = liga.getPartidas().get(2).getPontosTime1();
		int pontosTime2 = liga.getPartidas().get(2).getPontosTime2();

		RequestDispatcher rd;
		if (pontosTime1 > pontosTime2) {

			Time time = new Time();
			String idPartida = liga.getPartidas().get(6).getIdPartida();
			liga.getPartidas().get(6).setTime1(time);
			liga.getPartidas().get(6).selecionarTime1(idPartida);
			String idTime1 = liga.getPartidas().get(6).getTime1().getIdTime();
			time.setIdTime(idTime1);
			timeDao.selecionarTime(time);

			liga.aplicarPremiacao(idLiga, time);

			System.out.println("ID : " + idTime1);

			request.setAttribute("timeCampeao", time);
			rd = request.getRequestDispatcher("jsp/campeaoLiga.jsp");

		} else if (pontosTime2 > pontosTime1) {
			Time time = new Time();
			String idPartida = liga.getPartidas().get(6).getIdPartida();
			liga.getPartidas().get(6).setTime2(time);
			liga.getPartidas().get(6).selecionarTime2(idPartida);
			String idTime2 = liga.getPartidas().get(6).getTime2().getIdTime();
			time.setIdTime(idTime2);
			timeDao.selecionarTime(time);

			System.out.println("ID : " + idTime2);

			request.setAttribute("timeCampeao", time);
			rd = request.getRequestDispatcher("jsp/campeaoLiga.jsp");

			request.setAttribute("campeao", time);
			rd = request.getRequestDispatcher("jsp/campeaoLiga.jsp");
		} else {
			rd = request.getRequestDispatcher("erro.html");
		}
		rd.forward(request, response);
	}

}
