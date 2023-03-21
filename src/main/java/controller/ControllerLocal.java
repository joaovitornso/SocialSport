package controller;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Local;
import model.LocalDAO;


@WebServlet(urlPatterns = {"/local","/insertLocal","/selectLocal","/updateLocal","/deleteLocal"})
public class ControllerLocal extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	LocalDAO dao = new LocalDAO();
	Local local = new Local();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getServletPath();
		System.out.println(action);
		
		if (action.equals("/local")) {
			locais(request, response);
		}else if(action.equals("/insertLocal")){
			novoLocal(request, response);
		}else if(action.equals("/selectLocal")){
			listarLocais(request, response);
		}else if(action.equals("/updateLocal")){
			editarLocal(request, response);
		}else if(action.equals("/deleteLocal")){
			deletarLocal(request, response);
		}else {
			response.sendRedirect("index.html");
		}
	}
	protected void locais(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ArrayList<Local> lista = local.locais();
		
		request.setAttribute("locais", lista);
		RequestDispatcher rd = request.getRequestDispatcher("jsp/local.jsp");
		rd.forward(request, response);// encaminha o objeto lista ao documento local.jsp
	}
	// Novo local
	protected void novoLocal(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		local.novoLocal(request.getParameter("rua"),request.getParameter("bairro"),request.getParameter("cidade"),request.getParameter("tipo"));
		
		response.sendRedirect("local");
	}
	// Editar jogador
	protected void listarLocais(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Recebimento do id do local que será editado
		String idLocal= request.getParameter("idLocal");
		// Setar a variável JavaBeans
		local.listarLocais(idLocal);

		// Setar os atributos do formulário com o conteúdo JavaBeans
		request.setAttribute("idLocal", local.getIdLocal());
		request.setAttribute("rua", local.getRua());
		request.setAttribute("bairro", local.getBairro());
		request.setAttribute("cidade", local.getCidade());
		request.setAttribute("tipo", local.getTipo());

		// Ecaminhar ao documento editar.jsp
		RequestDispatcher rd = request.getRequestDispatcher("jsp/editarLocal.jsp");
		rd.forward(request, response);
	}
	protected void editarLocal(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		local.editarLocal(request.getParameter("idLocal"), request.getParameter("rua"), request.getParameter("bairro"), request.getParameter("cidade"), request.getParameter("tipo"));
		// redirecionar para o documento jogador.jsp (atualizando as alterações)
		response.sendRedirect("local");
	}
	protected void deletarLocal(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Recebimento do id do time a ser excluido (validado,js)
		String idLocal = request.getParameter("idLocal");
		
		local.deletarLocal(idLocal);

		response.sendRedirect("local");
	}
}
