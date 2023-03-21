package model;

public class Partida {

	private String idPartida;
	private String data;
	private String horario;
	private String pontuacao;
	private Time time1;
	private Time time2;
	private Esporte esporte;
	private Local local;
	private int pontosTime1;
	private int pontosTime2;
	private int idCampeonato;
	private int idLiga;

	public Partida() {

	}
	
	public Partida(String idPartida, String data, String horario, int pontosTime1, int pontosTime2) {
		super();
		this.idPartida = idPartida;
		this.data = data;
		this.horario = horario;
		this.pontosTime1 = pontosTime1;
		this.pontosTime2 = pontosTime2;
	}

	public Partida(String idPartida, String data, String horario, String pontuacao, Time time1, Time time2,
			Esporte esporte, Local local, int pontosTime1, int pontosTime2, int idCampeonato) {
		super();
		this.idPartida = idPartida;
		this.data = data;
		this.horario = horario;
		this.pontuacao = pontuacao;
		this.time1 = time1;
		this.time2 = time2;
		this.esporte = esporte;
		this.local = local;
		this.pontosTime1 = pontosTime1;
		this.pontosTime2 = pontosTime2;
		this.idCampeonato = idCampeonato;
	}
	public Partida(String idPartida, String data, String horario, String pontuacao, Time time1, Time time2,
			Esporte esporte, Local local, int pontosTime1, int pontosTime2, int idCampeonato, int idLiga) {
		super();
		this.idPartida = idPartida;
		this.data = data;
		this.horario = horario;
		this.pontuacao = pontuacao;
		this.time1 = time1;
		this.time2 = time2;
		this.esporte = esporte;
		this.local = local;
		this.pontosTime1 = pontosTime1;
		this.pontosTime2 = pontosTime2;
		this.idCampeonato = idCampeonato;
		this.idLiga = idLiga;
	}

	public Partida(String idPartida, String data, String horario, String pontuacao, Time time1, Time time2,
			Esporte esporte, Local local, int pontosTime1, int pontosTime2) {
		super();
		this.idPartida = idPartida;
		this.data = data;
		this.horario = horario;
		this.pontuacao = pontuacao;
		this.time1 = time1;
		this.time2 = time2;
		this.esporte = esporte;
		this.local = local;
		this.pontosTime1 = pontosTime1;
		this.pontosTime2 = pontosTime2;
	}
	
	public Partida(Esporte esporte) {
		this.esporte = esporte;
	}

	public Partida(String id, String data, String horario) {
		super();
		this.idPartida = id;
		this.data = data;
		this.horario = horario;
	}
	public Partida( String data, String horario) {
		super();
		this.data = data;
		this.horario = horario;
	}
	
	public Partida novaPartida(String data, String horario) {
		Partida partida = new Partida(data,horario);
		new PartidaDAO().inserirPartida(partida);
		return partida;
	}
	
	public Partida novaPartidaCompeticao(Partida partida,String data, String horario, String idEsporte, String idLocal) {
		partida.setData(data);
		partida.setHorario(horario);
		partida.getEsporte().setIdEsporte(idEsporte);
		partida.getLocal().setIdLocal(idLocal);
		new PartidaDAO().inserirPartidaCompeticao(partida);
		return partida;
	}

	public Partida selecionarPartida(String idPartida) {
		this.setIdPartida(idPartida);
		new PartidaDAO().selecionarPartida(this);
		return this;
	}
	public String selecionarTime1(String idPartida) {
		this.setIdPartida(idPartida);
		new PartidaDAO().selecionarTime1(this);
		return this.getTime1().getIdTime();
	}
	public String selecionarTime2(String idPartida) {
		this.setIdPartida(idPartida);
		new PartidaDAO().selecionarTime2(this);
		return this.getTime2().getIdTime();
	}
	public String selecionarEsporte(String idPartida) {
		this.setIdPartida(idPartida);
		new PartidaDAO().selecionarEsporte(this);
		return this.getEsporte().getIdEsporte();
	}
	public String selecionarLocal(String idPartida) {
		this.setIdPartida(idPartida);
		new PartidaDAO().selecionarLocal(this);
		return this.getLocal().getIdLocal();
	}
	public void atualizarLiga(String idPartida, int idLiga) {
		this.setIdPartida(idPartida);
		this.setIdLiga(idLiga);
		new PartidaDAO().atualizarLiga(this);
	}
	public void atualizarCampeonato(String idPartida, int idCampeonato) {
		this.setIdPartida(idPartida);
		this.setIdCampeonato(idCampeonato);
		new PartidaDAO().atualizarCampeoanato(this);
	}
	public void atualizarTime1(String idPartida,String idTime1) {
		this.setIdPartida(idPartida);
		this.getTime1().setIdTime(idTime1);
		new PartidaDAO().atualizarTime1(this);
	}
	public void atualizarTime2(String idPartida,String idTime2) {
		this.setIdPartida(idPartida);
		this.getTime2().setIdTime(idTime2);
		new PartidaDAO().atualizarTime2(this);
	}
	public void atualizarEsporte(String idPartida,String idEsporte) {
		this.setIdPartida(idPartida);
		this.getEsporte().setIdEsporte(idEsporte);
		new PartidaDAO().atualizarEsporte(this);
	}
	public void atualizarLocal(String idPartida,String idLocal) {
		this.setIdPartida(idPartida);
		this.getLocal().setIdLocal(idLocal);
		new PartidaDAO().atualizarLocal(this);
	}
	public String recuperarId(String dataCampeonato) {
		this.setData(dataCampeonato);
		new PartidaDAO().recuperarId(this);
		
		return this.getIdPartida();
	}
	public Partida organizarPartida(String idPartida, String idTime1, String idTime2, String idEsporte, String idLocal) {
		this.setIdPartida(idPartida);
		this.getTime1().setIdTime(idTime1);
		this.getTime2().setIdTime(idTime2);
		this.getEsporte().setIdEsporte(idEsporte);
		this.getLocal().setIdLocal(idLocal);
		
		new PartidaDAO().organizarPartida(this);
		
		return this;
		
	}
	public void atualizarPontuacao(String idPartida, int potosTime1, int pontosTime2) {
		this.setPontosTime1(potosTime1);
		this.setPontosTime2(pontosTime2);
		this.setIdPartida(idPartida);
		
		new PartidaDAO().atualizarPontuacao(this);
	}
	
	

	public int getIdLiga() {
		return idLiga;
	}

	public void setIdLiga(int idLiga) {
		this.idLiga = idLiga;
	}

	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}

	public int getPontosTime1() {
		return pontosTime1;
	}

	public void setPontosTime1(int pontosTime1) {
		this.pontosTime1 = pontosTime1;
	}

	public int getPontosTime2() {
		return pontosTime2;
	}

	public void setPontosTime2(int pontosTime2) {
		this.pontosTime2 = pontosTime2;
	}

	public String getIdPartida() {
		return idPartida;
	}

	public void setIdPartida(String idPartida) {
		this.idPartida = idPartida;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public String getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao(String pontuacao) {
		this.pontuacao = pontuacao;
	}

	public Time getTime1() {
		return time1;
	}

	public void setTime1(Time time1) {
		this.time1 = time1;
	}

	public Time getTime2() {
		return time2;
	}

	public void setTime2(Time time2) {
		this.time2 = time2;
	}

	public Esporte getEsporte() {
		return esporte;
	}

	public void setEsporte(Esporte esporte) {
		this.esporte = esporte;
	}

	public int getIdCampeonato() {
		return idCampeonato;
	}

	public void setIdCampeonato(int idCampeonato) {
		this.idCampeonato = idCampeonato;
	}
	

}