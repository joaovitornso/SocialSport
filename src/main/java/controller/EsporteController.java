package controller;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Esporte;
import model.EsporteDAO;

@WebServlet(urlPatterns = { "/esporte", "/selectEsporte", "/updateEsporte", "/verTimes" })
public class EsporteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	EsporteDAO dao = new EsporteDAO();
	Esporte esporte = new Esporte();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		System.out.println(action);

		if (action.equals("/esporte")) {
			esportes(request, response);
		} else if (action.equals("/selectEsporte")) {
			listarEsportes(request, response);
		} else if (action.equals("/updateEsporte")) {
			editarEsporte(request, response);
		} else if (action.equals("/verTimes")) {
			verTimes(request, response);
		} else {
			response.sendRedirect("index.html");
		}
	}

	protected void esportes(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ArrayList<Esporte> lista = dao.listarEsportes();

		request.setAttribute("esportes", lista);
		RequestDispatcher rd = request.getRequestDispatcher("jsp/esporte.jsp");
		rd.forward(request, response);// encaminha o objeto lista ao documento esporte.jsp
	}

	protected void listarEsportes(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Recebimento do id do esporte que será editado
		String idEsporte = request.getParameter("idEsporte");
		// Setar a variável JavaBeans
		esporte.setIdEsporte(idEsporte);
		// Executar o método selecionarJogador
		dao.selecionarEsporte(esporte);

		// Setar os atributos do formulário com o conteúdo JavaBeans
		request.setAttribute("idEsporte", esporte.getIdEsporte());
		request.setAttribute("nome", esporte.getNome());
		request.setAttribute("duracao", esporte.getDuracao());
		request.setAttribute("descricao", esporte.getDescricao());

		// Ecaminhar ao documento editar.jsp
		RequestDispatcher rd = request.getRequestDispatcher("jsp/editarEsporte.jsp");
		rd.forward(request, response);
	}

	protected void editarEsporte(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Setar as variáveis JavaBeans
		esporte.setIdEsporte(request.getParameter("idEsporte"));
		esporte.setNome(request.getParameter("nome"));
		esporte.setDuracao(request.getParameter("duracao"));
		esporte.setDescricao(request.getParameter("descricao"));

		dao.alterarEsporte(esporte);
		// redirecionar para o documento jogador.jsp (atualizando as alterações)
		response.sendRedirect("esporte");
	}
	protected void verTimes(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idEsporte = request.getParameter("idEsporte");
		
		esporte.setIdEsporte(idEsporte);
		
		dao.verTimes(esporte);
		
		request.setAttribute("esporte", esporte);
		
		RequestDispatcher rd = request.getRequestDispatcher("jsp/TimesEsportes.jsp");
		rd.forward(request, response);
		
		
	}
}
