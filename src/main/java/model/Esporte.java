package model;

import java.util.ArrayList;

public class Esporte {
	private String idEsporte;
	private String nome;
	private String duracao;
	private String descricao;
	private ArrayList<Time> times;
	
	public Esporte(String idEsporte, String nome, String duracao, String descricao, ArrayList<Time> times) {
		super();
		this.idEsporte = idEsporte;
		this.nome = nome;
		this.duracao = duracao;
		this.descricao = descricao;
		this.times = times;
	}
	public Esporte(String idEsporte, String nome, String duracao, String descricao) {
		super();
		this.idEsporte = idEsporte;
		this.nome = nome;
		this.duracao = duracao;
		this.descricao = descricao;
	}
	public Esporte() {
		super();
		// TODO Auto-generated constructor stub
	}
		
	public ArrayList<Time> getTimes() {
		return times;
	}
	public void setTimes(ArrayList<Time> times) {
		this.times = times;
	}
	public String getIdEsporte() {
		return idEsporte;
	}
	public void setIdEsporte(String idEsporte) {
		this.idEsporte = idEsporte;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDuracao() {
		return duracao;
	}
	public void setDuracao(String duracao) {
		this.duracao = duracao;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
