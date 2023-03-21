package model;

import java.util.ArrayList;

public class Time {
	private String idTime;
	private String nome;
	private int qntJogadores;
	private ArrayList<Jogador> jogadores;
	private String idEsporte;
	private int idCampeonato;
	private int vitorias;
	private int derrotas;
	private int empates;
	private int idLiga;
	private double receita;

	
	public Time(String idTime, String nome, int qntJogadores, String idEsporte, int idCampeonato, int vitorias,
			int derrotas, int empates, int idLiga) {
		super();
		this.idTime = idTime;
		this.nome = nome;
		this.qntJogadores = qntJogadores;
		this.idEsporte = idEsporte;
		this.idCampeonato = idCampeonato;
		this.vitorias = vitorias;
		this.derrotas = derrotas;
		this.empates = empates;
		this.idLiga = idLiga;
	}

	public Time(String idTime, String nome, int qntJogadores, ArrayList<Jogador> jogadores, String idEsporte,
			int idCampeonato, int vitorias, int derrotas, int empates) {
		super();
		this.idTime = idTime;
		this.nome = nome;
		this.qntJogadores = qntJogadores;
		this.jogadores = jogadores;
		this.idEsporte = idEsporte;
		this.idCampeonato = idCampeonato;
		this.vitorias = vitorias;
		this.derrotas = derrotas;
		this.empates = empates;
	}

	public Time(String idTime, String nome, int qntJogadores, ArrayList<Jogador> jogadores, String idEsporte,
			int vitorias, int derrotas, int empates) {
		super();
		this.idTime = idTime;
		this.nome = nome;
		this.qntJogadores = qntJogadores;
		this.jogadores = jogadores;
		this.idEsporte = idEsporte;
		this.vitorias = vitorias;
		this.derrotas = derrotas;
		this.empates = empates;
	}

	public Time(String idTime, String nome, int qntJogadores, ArrayList<Jogador> jogadores, String idEsporte) {
		super();
		this.idTime = idTime;
		this.nome = nome;
		this.qntJogadores = qntJogadores;
		this.jogadores = jogadores;
		this.idEsporte = idEsporte;
	}

	public Time(String idTime, String nome, int qntJogadores) {
		super();
		this.idTime = idTime;
		this.nome = nome;
		this.qntJogadores = qntJogadores;
	}

	public Time(String idTime, String nome, int qntJogadores, String idEsporte, int vitorias, int derrotas,
			int empates) {
		super();
		this.idTime = idTime;
		this.nome = nome;
		this.qntJogadores = qntJogadores;
		this.idEsporte = idEsporte;
		this.vitorias = vitorias;
		this.derrotas = derrotas;
		this.empates = empates;
	}
	
	/*
	 * public Time(String idTime, int derrotas) { this.idTime = idTime;
	 * this.derrotas = derrotas; }
	 */
	/*
	 * public Time(String idTime, String nome, int qntJogadores, ArrayList<Jogador>
	 * jogadores) { super(); this.idTime = idTime; this.nome = nome;
	 * this.qntJogadores = qntJogadores; this.jogadores = jogadores; }
	 */

	
	public Time(String idTime, String nome, String idEsporte, int vitorias, int derrotas, int empates) {
		super();
		this.idTime = idTime;
		this.nome = nome;
		this.idEsporte = idEsporte;
		this.vitorias = vitorias;
		this.derrotas = derrotas;
		this.empates = empates;
	}

	public Time(String idTime, String nome, int qntJogadores, String idEsporte, int idCampeonato, int vitorias,
			int derrotas, int empates, int idLiga, double receita) {
		super();
		this.idTime = idTime;
		this.nome = nome;
		this.qntJogadores = qntJogadores;
		this.idEsporte = idEsporte;
		this.idCampeonato = idCampeonato;
		this.vitorias = vitorias;
		this.derrotas = derrotas;
		this.empates = empates;
		this.idLiga = idLiga;
		this.receita = receita;
	}

	public Time(String idTime, String nome, int qntJogadores, int vitorias, int derrotas, int empates) {
		super();
		this.idTime = idTime;
		this.nome = nome;
		this.qntJogadores = qntJogadores;
		this.vitorias = vitorias;
		this.derrotas = derrotas;
		this.empates = empates;
	}
	public int verificarQntJogadores(String idTime) {
		this.setIdTime(idTime);
		new TimeDAO().qntdJogadoresTime(this);
		return this.getQntJogadores();
	}

	public void ganharPartida(String idTime, int vitorias) {
		Time time = new Time();
		time.setIdTime(idTime);
		time.setVitorias(vitorias);
		new TimeDAO().ganharPartida(time);
	}

	public void perderPartida(String idTime, int derrotas) {
		Time time = new Time();
		time.setIdTime(idTime);
		time.setDerrotas(derrotas);
		new TimeDAO().perderPartida(time);
	}

	public void empatarPartida(String idTime, int empates) {
		Time time = new Time();
		time.setIdTime(idTime);
		time.setEmpates(empates);
		new TimeDAO().empatarPartida(time);
	}
	
	public int selecionarCampionato(int idCampeonato) {
		this.setIdCampeonato(idCampeonato);
		new TimeDAO().selecionarCampeonato(this);
		return this.getIdCampeonato();
	}
	public void inscreverCampeonato(String idTime, int idCampeonato) {
		this.setIdTime(idTime);
		this.setIdCampeonato(idCampeonato);
		new TimeDAO().inscreverCampeonato(this);
	}
	public void inscreverLiga(String idTime, int idLiga) {
		this.setIdTime(idTime);
		this.setIdLiga(idLiga);
		new TimeDAO().inscreverLiga(this);
	}
	
	public void receberPremiacao(String idTime,double premio) {
		this.setIdTime(idTime);
		new TimeDAO().selecionarTime(this);
		this.setReceita(this.getReceita() + premio);
		new TimeDAO().receberPremio(this);
	}
	
	public int getIdCampeonato() {
		return idCampeonato;
	}

	public void setIdCampeonato(int idCampeonato) {
		this.idCampeonato = idCampeonato;
	}

	public int getVitorias() {
		return vitorias;
	}

	public void setVitorias(int vitorias) {
		this.vitorias = vitorias;
	}

	public int getDerrotas() {
		return derrotas;
	}

	public void setDerrotas(int derrotas) {
		this.derrotas = derrotas;
	}

	public int getEmpates() {
		return empates;
	}

	public void setEmpates(int empates) {
		this.empates = empates;
	}

	public String getIdEsporte() {
		return idEsporte;
	}

	public void setIdEsporte(String idEsporte) {
		this.idEsporte = idEsporte;
	}

	public ArrayList<Jogador> getJogadores() {
		return jogadores;
	}

	public void setJogadores(ArrayList<Jogador> jogadores) {
		this.jogadores = jogadores;
	}

	public Time() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Time(String idTime2, String nome2, double receita2) {
		this.idTime = idTime2;
		this.nome = nome2;
		this.receita = receita2;
	}

	public String getIdTime() {
		return idTime;
	}

	public void setIdTime(String idTime) {
		this.idTime = idTime;
	}

	public int getQntJogadores() {
		return qntJogadores;
	}

	public void setQntJogadores(int qntJogadores) {
		this.qntJogadores = qntJogadores;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdLiga() {
		return idLiga;
	}

	public void setIdLiga(int idLiga) {
		this.idLiga = idLiga;
	}

	public double getReceita() {
		return receita;
	}

	public void setReceita(double receita) {
		this.receita = receita;
	}
	
	

}
