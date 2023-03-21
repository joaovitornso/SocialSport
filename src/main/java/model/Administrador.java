package model;

public class Administrador {
	private String idAdministrador;
	private String nome;
	private String senha;
	
	public Administrador(String idAdministrador, String nome, String senha) {
		super();
		this.idAdministrador = idAdministrador;
		this.nome = nome;
		this.senha = senha;
	}
	
	public Administrador() {
		super();
	}
	public String getIdAdministrador() {
		return idAdministrador;
	}
	public void setIdAdministrador(String idAdministrador) {
		this.idAdministrador = idAdministrador;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
}
