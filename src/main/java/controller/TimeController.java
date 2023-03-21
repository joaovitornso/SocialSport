package controller;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.DAO;
import model.Esporte;
import model.EsporteDAO;
import model.Jogador;
import model.Time;
import model.TimeDAO;

@WebServlet(urlPatterns = { "/time","/encaminharLista", "/insert2", "/select2", "/update2", "/delete2", "/verTime","/expulsarJogador"})
public class TimeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	TimeDAO daoTime = new TimeDAO();
	Time time = new Time();
	EsporteDAO esporteDao = new EsporteDAO();
	Esporte esporte = new Esporte();
	DAO jogadorDao = new DAO();
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getServletPath();
		System.out.println(action);
		if (action.equals("/time")) {
			times(request, response);
		}else if (action.equals("/encaminharLista")) {
			encaminharEsporte(request, response);
		} else if (action.equals("/insert2")) {
			novoTime(request, response);
		} else if (action.equals("/select2")) {
			listarTimes(request, response);
		} else if (action.equals("/update2")) {
			editarTime(request, response);
		} else if (action.equals("/delete2")) {
			deletarTime(request, response);
		} else if (action.equals("/verTime")) {
			verTime(request, response);
		} else if (action.equals("/expulsarJogador")) {
			expulsarJogador(request, response);
		}  else {
			response.sendRedirect("index.html");
		}
	}

	// Listar Times
	protected void times(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Criando um objeto que ira recber os dados JavaBeans
		ArrayList<Time> lista = daoTime.listarTimes();

		// Encaminhar a lista ao documento time.jsp
		request.setAttribute("times", lista);
		RequestDispatcher rd = request.getRequestDispatcher("jsp/time.jsp");
		rd.forward(request, response);// encaminha o objeto lista ao documento time.jsp
	}
	
	// ENcaminhando lista de esportes para o arquivo novoTime
	protected void encaminharEsporte(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Criando um objeto que ira recber os dados JavaBeans
		ArrayList<Esporte> listaEsporte = esporteDao.listarEsportes();

		// Encaminhar a lista ao documento time.jsp
		request.setAttribute("esportes", listaEsporte);
		RequestDispatcher rd = request.getRequestDispatcher("jsp/novoTime.jsp");
		rd.forward(request, response);// encaminha o objeto lista ao documento time.jsp
	}

	// Novo time
	protected void novoTime(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// setar as variáveis JavaBeans
		time.setNome(request.getParameter("nome"));
		time.setQntJogadores(Integer.parseInt(request.getParameter("qntJogadores")));
		
		String esporteTime = (request.getParameter("esporteTime"));
		
		time.setIdEsporte(esporteTime);
		// invocar o metodo inserirJogador passando o objeto jogador
		daoTime.inserirTime(time);
		// redirecionar para o documento time.jsp
		response.sendRedirect("time");
	}

	// Editar jogador
	protected void listarTimes(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Recebimento do id do time que será editado
		String idTime = request.getParameter("idTime");
		// Setar a variável JavaBeans
		time.setIdTime(idTime);
		// Executar o método selecionarJogador
		daoTime.selecionarTime(time);

		// Setar os atributos do formulário com o conteúdo JavaBeans
		request.setAttribute("idTime", time.getIdTime());
		request.setAttribute("nome", time.getNome());
		request.setAttribute("qntJogadores", time.getQntJogadores());

		// Ecaminhar ao documento editar.jsp
		RequestDispatcher rd = request.getRequestDispatcher("jsp/editarTime.jsp");
		rd.forward(request, response);
	}

	protected void editarTime(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Setar as variáveis JavaBeans
		time.setIdTime(request.getParameter("idTime"));
		time.setNome(request.getParameter("nome"));
		time.setQntJogadores(Integer.parseInt(request.getParameter("qntJogadores")));
		// executar o método alterarJogador
		daoTime.alterarTime(time);
		// redirecionar para o documento jogador.jsp (atualizando as alterações)
		response.sendRedirect("time");
	}

	// Deletar um time
	protected void deletarTime(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idTime = request.getParameter("idTime");
		
		Time time = new Time();
		
		time.setIdTime(idTime);
		
		daoTime.selecionarTime(time);
		
		if(time.getIdEsporte() != null) {
			System.out.println("Time não pode ser deletado pois já está em um time");
			RequestDispatcher rd = request.getRequestDispatcher("erro.html");
			rd.forward(request, response);
		}else {
			System.out.println(time.getIdEsporte());
			System.out.println("Time deletado com sucesso ");
			daoTime.deletarTime(time);
		}

		response.sendRedirect("time");
	}
	//Ver os jogadores que estao alocados em um time especifico
	protected void verTime(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idTime = request.getParameter("idTime");
		time.setIdTime(idTime);
		
		daoTime.verTime(time);
		
		request.setAttribute("time", time);
		
		RequestDispatcher rd = request.getRequestDispatcher("jsp/jogadoresTime.jsp");
		rd.forward(request, response);
		
	}
	protected void expulsarJogador(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idJogador = request.getParameter("idJogador");
		
		System.out.println(idJogador);
		
		Jogador jogador = new Jogador();
		
		jogador.setIdjogador(idJogador);
		
		jogadorDao.selecionarJogador(jogador);
		
		jogador.expulsarJogador(idJogador);
		
		response.sendRedirect("time");
	}
}
