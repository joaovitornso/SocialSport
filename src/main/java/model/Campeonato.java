package model;

import java.util.ArrayList;

public class Campeonato {
	private int id;
	private String nome;
	private String medalhas;
	private ArrayList<Partida> partidas;
	private ArrayList<Time> times;
	private Esporte esporte;
	private Local local;
	private Time timeCampeao;
	// OBS: Escolher o id de dois times e setar em uma partida;

	public Campeonato() {
	}

	public Campeonato(int id, String medalhas) {
		this.id = id;
		this.medalhas = medalhas;
	}

	public Campeonato(int idCompeticao, String nome, String medalhas) {
		super();
		this.id = idCompeticao;
		this.nome = nome;
		this.medalhas = medalhas;
	}
	public Campeonato( String nome, String medalhas) {
		super();
		this.nome = nome;
		this.medalhas = medalhas;
	}

//
//	public void atualizarEsporte(int idCampeonato, String idEsporte) {
//		this.setId(idCampeonato);
//		this.getEsporte().setIdEsporte(idEsporte);
//		new CampeonatoDAO().atualizarEsporte(this);
//	}
//
//	public void atualizarLocal(int idCampeonato, String idLocal) {
//		this.setId(idCampeonato);
//		this.getLocal().setIdLocal(idLocal);
//		new CampeonatoDAO().atualizarLocal(this);
//	}
	public Campeonato criar(String nome, String medalha) {
		Campeonato campeonato = new Campeonato(nome,medalha);
		new CampeonatoDAO().inserirCampeonato(campeonato);
		return campeonato;
	}
	
	
	public ArrayList<Campeonato> campeonatos(){
		ArrayList<Campeonato> lista = new ArrayList<>();
		lista = new CampeonatoDAO().listarCampeonatos();
		return lista;
	}
	
	public Campeonato listarCompeticao(int idCompeticao) {
		this.setId(idCompeticao);
		new CampeonatoDAO().selecionarCompeticao(this);
		return this;
	}
	public void editarCompeticao(int idCampeonato, String nome, String medalhas ) {
		Campeonato campeonato = new Campeonato(idCampeonato, nome, medalhas);
		new CampeonatoDAO().atualizarCampeonato(campeonato);
	}
	public void deletarCompeticao(int idCampeonato) {
		this.setId(idCampeonato);
		new CampeonatoDAO().deletarCampeonato(idCampeonato);;
	}
	
	public Campeonato selecionarCompeticao(int idCampeonato) {
		this.setId(idCampeonato);
		new CampeonatoDAO().selecionarCompeticao(this);
		return this;
	}
	public Campeonato selecionarTimesCompeticao(int idCampeonato) {
		this.setId(idCampeonato);
		new CampeonatoDAO().selecionarTimesCompeticao(this);
		return this;
	}
	public Campeonato selecionarPartidas(int idCampeonato) {
		this.setId(idCampeonato);
		new CampeonatoDAO().selecionarPartidas(this);
		return this;
	}
	public void aplicarPremiacao(int idCampeonato, Time time) {
		double premio;
		String idTime = time.getIdTime();
		
		Campeonato campeonato = new Campeonato();
		campeonato.selecionarCompeticao(idCampeonato);
		campeonato.setId(idCampeonato);
		campeonato.setTimeCampeao(time);
		
		if(campeonato.getMedalhas().equals("bronze")) {
			premio = 200.00;
			time.receberPremiacao(idTime,premio);
		}
		else if(campeonato.getMedalhas().equals("prata")) {
			premio = 500.00;
			time.receberPremiacao(idTime, premio);
		}else{
			premio = 1000.00;
			time.receberPremiacao(idTime,premio);		
		}
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMedalhas() {
		return medalhas;
	}

	public void setMedalhas(String medalhas) {
		this.medalhas = medalhas;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public ArrayList<Partida> getPartidas() {
		return partidas;
	}

	public void setPartidas(ArrayList<Partida> partidas) {
		this.partidas = partidas;
	}

	public ArrayList<Time> getTimes() {
		return times;
	}

	public void setTimes(ArrayList<Time> times) {
		this.times = times;
	}

	public Esporte getEsporte() {
		return esporte;
	}

	public void setEsporte(Esporte esporte) {
		this.esporte = esporte;
	}

	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}

	public Time getTimeCampeao() {
		return timeCampeao;
	}

	public void setTimeCampeao(Time timeCampeao) {
		this.timeCampeao = timeCampeao;
	}
	

}