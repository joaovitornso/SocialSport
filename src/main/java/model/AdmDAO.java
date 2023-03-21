package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdmDAO {
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
	// BUSCAR O ADMINISTRADOR NO BANCO
	public Administrador buscarAdmNome(String nome) {
		String select2 = "select * from administrador where nome=?";
		Administrador admin = null;
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(select2);
			pst.setString(1, nome);

			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				String id = rs.getString("idAdministrador");
				String senha = rs.getString("senha");

				admin = new Administrador();
				admin.setIdAdministrador(id);
				admin.setNome(nome);
				admin.setSenha(senha);

			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return admin;
	}
}
