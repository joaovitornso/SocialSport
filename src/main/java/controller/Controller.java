package controller;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.tomcat.jni.Sockaddr;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.DAO;
import model.Jogador;
import model.Time;
import model.TimeDAO;

/**
 * Servlet implementation class Controller
 */
@WebServlet(urlPatterns = { "/jogador", "/insert", "/select", "/update", "/delete", "/alocarJogador", "/alocar"})
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DAO dao = new DAO();
	TimeDAO daoTime = new TimeDAO();
	Jogador jogador = new Jogador();
	Time time = new Time();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getServletPath();
		System.out.println(action);
		if (action.equals("/jogador")) {
			jogadores(request, response);
		} else if (action.equals("/insert")) {
			novoJogador(request, response);
		} else if (action.equals("/select")) {
			listarJogador(request, response);
		} else if (action.equals("/update")) {
			editarJogador(request, response);
		} else if (action.equals("/delete")) {
			deletarJogador(request, response);
		} else if (action.equals("/alocarJogador")) {
			jogadoresTime(request, response);
		} else if (action.equals("/alocar")) {
			alocarJogador(request, response);
		} else {
			response.sendRedirect("index.html");
		}

	}

	// Listar jogadores
	protected void jogadores(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Criando um objeto que ira recber os dados JavaBeans
		ArrayList<Jogador> lista = dao.listarJogadores();

		// Encaminhar a lista ao documento jogador.jsp
		request.setAttribute("jogadores", lista);
		RequestDispatcher rd = request.getRequestDispatcher("jsp/jogador.jsp");
		rd.forward(request, response);// encaminha o objeto lista ao documento jogador.jsp

	}

	// Novo jogador
	protected void novoJogador(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// setar as variáveis JavaBeans
		
		Jogador jogador = new Jogador();
		jogador.setNome(request.getParameter("nome"));
		jogador.setIdade(request.getParameter("idade"));
		jogador.setNmrCamisa(request.getParameter("nmrCamisa"));
		// invocar o metodo inserirJogador passando o objeto jogador
		dao.inserirJogador(jogador);
		// redirecionar para o documento jogador.jsp
		response.sendRedirect("jogador");
	}

	// Editar jogador
	protected void listarJogador(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Recebimento do id do contato que será editado
		String idjogador = request.getParameter("idjogador");
		// System.out.println(idjogador); //Teste
		// Setar a variável JavaBeans
		jogador.setIdjogador(idjogador);
		// Executar o método selecionarJogador
		dao.selecionarJogador(jogador);
		// teste recebimento
//		System.out.println(jogador.getIdjogador());
//		System.out.println(jogador.getNome());
//		System.out.println(jogador.getIdade());
//		System.out.println(jogador.getSexo());
//		System.out.println(jogador.getNmrCamisa());

		// Setar os atributos do formulário com o conteúdo JavaBeans
		request.setAttribute("idjogador", jogador.getIdjogador());
		request.setAttribute("nome", jogador.getNome());
		request.setAttribute("nmrCamisa", jogador.getNmrCamisa());
		request.setAttribute("idade", jogador.getIdade());
		// Ecaminhar ao documento editar.jsp
		RequestDispatcher rd = request.getRequestDispatcher("jsp/editar.jsp");
		rd.forward(request, response);
	}

	protected void editarJogador(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// teste recebimento
//		System.out.println(request.getParameter("idjogador"));
//		System.out.println(request.getParameter("nome"));
//		System.out.println(request.getParameter("nmrCamisa"));
//		System.out.println(request.getParameter("idade"));

		// Setar as variáveis JavaBeans
		jogador.setIdjogador(request.getParameter("idjogador"));
		jogador.setNome(request.getParameter("nome"));
		jogador.setNmrCamisa(request.getParameter("nmrCamisa"));
		jogador.setIdade(request.getParameter("idade"));
		// executar o método alterarJogador
		dao.alterarJogador(jogador);
		// redirecionar para o documento jogador.jsp (atualizando as alterações)
		response.sendRedirect("jogador");
	}

	// Deletar um time
	protected void deletarJogador(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idjogador = request.getParameter("idjogador");

		Jogador jogador = new Jogador();
		
		jogador.setIdjogador(idjogador);
		
		dao.selecionarJogador(jogador);
		
		if(jogador.getIdTime() != null ) {
			System.out.println("jogador não pode ser deletado pois já está em um time");
			RequestDispatcher rd = request.getRequestDispatcher("erro.html");
			rd.forward(request, response);
		}else {
			dao.deletarJogador(jogador);
			System.out.println("jogador deletado com sucesso");
		}
		
		response.sendRedirect("jogador");
	}

	// Listar jogadores
	protected void jogadoresTime(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Criando um objeto que ira recber os dados JavaBeans
		ArrayList<Jogador> lista = dao.listarJogadores();
		ArrayList<Time> listaTime = daoTime.listarTimes();
		
		request.setAttribute("jogadores", lista);
		request.setAttribute("times", listaTime);
		RequestDispatcher rd = request.getRequestDispatcher("jsp/alocarJogador.jsp");
		rd.forward(request, response);// encaminha o objeto lista ao documento jogador.jsp

	}

	protected void alocarJogador(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idJogadorAlocado = (request.getParameter("jogadorAlocado"));
		String idTimeAlocado = (request.getParameter("timeAlocado"));
		Time time = new Time();
		Jogador jogador = new Jogador();
		jogador.setIdjogador(idJogadorAlocado);
		time.setIdTime(idTimeAlocado);
		
		dao.selecionarJogador(jogador);
		daoTime.selecionarTime(time);
		
		int qntdJogadores = time.verificarQntJogadores(idTimeAlocado);
		
		System.out.println(qntdJogadores);
		RequestDispatcher rd;
		if(jogador.getIdTime() != null || jogador.getIdTime() == time.getIdTime() || qntdJogadores >= 10) {
			rd = request.getRequestDispatcher("erro.html");
		}
		else{
			jogador.setIdTime(idTimeAlocado);
			dao.alocarJogador(jogador);
			rd = request.getRequestDispatcher("index.html");
		}
		rd.forward(request, response);

	}

}
