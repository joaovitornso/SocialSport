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
import model.Jogador;

/**
 * Servlet implementation class AdmController
 */
@WebServlet(urlPatterns = {"/selecionarJogador","/aplicarPunicao"})
public class AdmController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DAO jogadoresDao = new DAO();
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		System.out.println(action);
		if (action.equals("/selecionarJogador")) {
			selecionarJogador(request, response);
		}else if (action.equals("/aplicarPunicao")) {
			aplicarPunicao(request, response);
		}else {
			response.sendRedirect("index.html");
		}
	}

	protected void selecionarJogador(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<Jogador> listaJogadores = jogadoresDao.listarJogadores();
		
		request.setAttribute("listaJogadores", listaJogadores);
		RequestDispatcher rd = request.getRequestDispatcher("jsp/selecionarJogador.jsp");
		rd.forward(request, response);
	}
	
	protected void aplicarPunicao(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idJogador= (request.getParameter("jogadorPunido"));
		
		Jogador jogadorPunido = new Jogador();
		
		jogadorPunido.setIdjogador(idJogador);
		
		jogadoresDao.selecionarJogador(jogadorPunido);
		
		int somaPunicoes = jogadorPunido.getNmrPunicoes() + 1;
		jogadorPunido.aplicarPunicao(idJogador, somaPunicoes);
		
		if(somaPunicoes >= 5) {
			jogadorPunido.expulsarJogador(idJogador);
		}
		RequestDispatcher rd = request.getRequestDispatcher("jsp/admin.jsp");
		rd.forward(request, response);
		
	}
	
	
}
