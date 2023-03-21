package model;

import java.util.ArrayList;

public class Local {
	private String idLocal;
	private String rua;
	private String bairro ;
	private String cidade;
	private String tipo;
	private String idPartida;
	
	public Local(String rua, String bairro, String cidade, String tipo) {
		super();
		this.rua = rua;
		this.bairro = bairro;
		this.cidade = cidade;
		this.tipo = tipo;
	}
	
	
	public Local(String idLocal, String rua, String bairro, String cidade, String tipo, String idPartida) {
		super();
		this.idLocal = idLocal;
		this.rua = rua;
		this.bairro = bairro;
		this.cidade = cidade;
		this.tipo = tipo;
		this.idPartida = idPartida;
	}


	public Local(String idLocal, String rua, String bairro, String cidade, String tipo) {
		super();
		this.idLocal = idLocal;
		this.rua = rua;
		this.bairro = bairro;
		this.cidade = cidade;
		this.tipo = tipo;
	}
	public Local() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<Local> locais(){
		ArrayList<Local> lista = new ArrayList<>();
		lista = new LocalDAO().listarLocais();
		return lista;
	}
	
	public Local novoLocal(String rua, String bairro, String cidade, String tipo) {
		Local local =  new Local(rua,bairro,cidade,tipo);
		new LocalDAO().inserirLocal(local);
		return local;
	}
	
	public Local listarLocais(String idLocal) {
		this.setIdLocal(idLocal);
		new LocalDAO().selecionarLocal(this);
		return this;
	}
	
	public void editarLocal(String idLocal,String rua,String bairro, String cidade, String tipo ) {
		Local local = new Local(idLocal,rua,bairro,cidade,tipo);
		new LocalDAO().alterarLocal(local);
	}
	
	public void deletarLocal(String idLocal) {
		this.setIdLocal(idLocal);
		new LocalDAO().deletarLocal(this);
	}
	
	public String getIdPartida() {
		return idPartida;
	}
	public void setIdPartida(String idPartida) {
		this.idPartida = idPartida;
	}
	public String getIdLocal() {
		return idLocal;
	}
	public void setIdLocal(String idLocal) {
		this.idLocal = idLocal;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
}
