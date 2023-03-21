package model;

import java.util.ArrayList;

public class Liga extends Campeonato {
	private String trofeu;

	public Liga() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Liga(int idLiga, String nome, String medalhas) {
		super(idLiga, nome, medalhas);
		// TODO Auto-generated constructor stub
	}

	public Liga(String nome, String medalhas, String trofeu) {
		super(nome, medalhas);
		this.trofeu = trofeu;
		// TODO Auto-generated constructor stub
	}

	public Liga(int idLiga, String nome, String medalhas, String trofeu) {
		super(idLiga, nome, medalhas);
		this.trofeu = trofeu;
	}

	public ArrayList<Liga> ligas() {
		ArrayList<Liga> lista = new ArrayList<>();
		lista = new LigaDAO().listarLigas();
		
		for(int i = 0; i < lista.size(); i++) {
			System.out.println("INSERT INTO liga (idLiga, nome, medalha, trofeu) values \n(" + 
		lista.get(i).getId() + ", " + lista.get(i).getNome() + ", " + lista.get(i).getMedalhas() + ", " + lista.get(i).getTrofeu() + ";");
		}
		
		return lista;
	}

	public Liga criar(String nome, String medalha, String trofeu) {
		Liga liga = new Liga(nome, medalha, trofeu);
		new LigaDAO().inserirLiga(liga);
		return liga;
	}

	public Liga listarCompeticao(int idLiga) {
		this.setId(idLiga);
		new LigaDAO().selecionarCompeticao(this);
		return this;
	}

	public void editarCompeticao(int idLiga, String nome, String medalhas, String trofeu) {
		Liga liga = new Liga(idLiga, nome, medalhas, trofeu);
		new LigaDAO().atualizarLiga(liga);
	}

	public void deletarCompeticao(int idLiga) {
		this.setId(idLiga);
		new LigaDAO().deletarLiga(idLiga);
	}

	@Override
	public Liga selecionarCompeticao(int idLiga) {
		this.setId(idLiga);
		new LigaDAO().selecionarCompeticao(this);
		return this;
	}

	@Override
	public Liga selecionarTimesCompeticao(int idLiga) {
		this.setId(idLiga);
		new LigaDAO().selecionarTimesCompeticao(this);
		return this;
	}

	@Override
	public Liga selecionarPartidas(int idLiga) {
		this.setId(idLiga);
		new LigaDAO().selecionarPartidas(this);
		return this;
	}

	@Override
	public void aplicarPremiacao(int idLiga, Time time) {
		double premio;
		String idTime = time.getIdTime();

		Liga liga = new Liga();
		liga.selecionarCompeticao(idLiga);
		liga.setId(idLiga);
		liga.setTimeCampeao(time);

		if (liga.getMedalhas().equals("bronze") && liga.getTrofeu().equals("platina")) {
			premio = 200.00 + 500.00;
			time.receberPremiacao(idTime, premio);
		} else if (liga.getMedalhas().equals("bronze") && liga.getTrofeu().equals("diamante")) {
			premio = 200.00 + 1000.00;
			time.receberPremiacao(idTime, premio);
		} else if (liga.getMedalhas().equals("bronze") && liga.getTrofeu().equals("champion")) {
			premio = 200.00 + 2000.00;
			time.receberPremiacao(idTime, premio);
		} else if (liga.getMedalhas().equals("prata") && liga.getTrofeu().equals("platina")) {
			premio = 500.00 + 500.00;
			time.receberPremiacao(idTime, premio);
		} else if (liga.getMedalhas().equals("prata") && liga.getTrofeu().equals("diamante")) {
			premio = 500.00 + 1000.00;
			time.receberPremiacao(idTime, premio);
		} else if (liga.getMedalhas().equals("prata") && liga.getTrofeu().equals("champion")) {
			premio = 500.00 + 2000.00;
			time.receberPremiacao(idTime, premio);
		} else if (liga.getMedalhas().equals("ouro") && liga.getTrofeu().equals("platina")) {
			premio = 1000.00 + 500.00;
			time.receberPremiacao(idTime, premio);
		}else if (liga.getMedalhas().equals("ouro") && liga.getTrofeu().equals("diamante")) {
			premio = 1000.00 + 1000.00;
			time.receberPremiacao(idTime, premio);
		}else {
			premio = 1000.00 + 2000.00;
			time.receberPremiacao(idTime, premio);
		}

	}

	public String getTrofeu() {
		return trofeu;
	}

	public void setTrofeu(String trofeu) {
		this.trofeu = trofeu;
	}

}
