package controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.AdmDAO;
import model.Administrador;

@WebServlet(name = "ControllerLogin", urlPatterns = {"/ControllerLogin"})
public class ControllerLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	AdmDAO dao = new AdmDAO();
	Administrador admin = new Administrador();
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Processar o login
		String nome = request.getParameter("nome");
		String senha = request.getParameter("senha");

		System.out.println(nome);
		System.out.println(senha);

		admin = dao.buscarAdmNome(nome);

		boolean loginSucesso = false;

		if (admin != null && admin.getSenha().equals(senha)) {
			loginSucesso = true;
		}

		RequestDispatcher rd;
		if (loginSucesso) {

			HttpSession session = request.getSession();
			session.setAttribute("admLogado", admin);

			rd = request.getRequestDispatcher("jsp/admin.jsp");
			rd.forward(request, response);

		} else {
			// Informar ao usuário que deu erro

			request.setAttribute("erro", "Nome/senha incorretos");
			
			rd = request.getRequestDispatcher("jsp/login.jsp");
			rd.forward(request, response);

		}
	}

}
