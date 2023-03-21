package model;

public class Jogador {
	private String idjogador;
	private String nome;
	private String nmrCamisa;
	private String idade;
	private String idTime;
	private int nmrPunicoes;
	
	public Jogador(String idjogador, String nome, String nmrCamisa, String idade) {
		super();
		this.idjogador = idjogador;
		this.nome = nome;
		this.nmrCamisa = nmrCamisa;
		this.idade = idade;
	}
	public Jogador(String idjogador, String nome, String nmrCamisa, String idade, String idTime) {
		super();
		this.idjogador = idjogador;
		this.nome = nome;
		this.nmrCamisa = nmrCamisa;
		this.idade = idade;
		this.idTime = idTime;
	}
	public Jogador() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public Jogador(String idjogador, String nome, String nmrCamisa, String idade,String idTime, int nmrPunicoes) {
		super();
		this.idjogador = idjogador;
		this.nome = nome;
		this.nmrCamisa = nmrCamisa;
		this.idade = idade;
		this.idTime = idTime;
		this.nmrPunicoes = nmrPunicoes;
	}
	
	
	public void aplicarPunicao(String idJogador, int nmrPunicoes) {
		Jogador jogador = new Jogador();
		jogador.setIdjogador(idJogador);
		jogador.setNmrPunicoes(nmrPunicoes);
		new DAO().aplicarPunicao(jogador);
	}
	public void expulsarJogador(String idJogador) {
		this.setIdjogador(idJogador);
		new DAO().expulsarJogador(this);
	}
	public void selecionarJogador(String idJogador) {
		this.setIdjogador(idJogador);
		new DAO().selecionarJogador(this);
	}
	public int getNmrPunicoes() {
		return nmrPunicoes;
	}
	public void setNmrPunicoes(int nmrPunicoes) {
		this.nmrPunicoes = nmrPunicoes;
	}
	public String getIdTime() {
		return idTime;
	}
	public void setIdTime(String idTime) {
		this.idTime = idTime;
	}
	public String getIdade() {
		return idade;
	}
	public void setIdade(String idade) {
		this.idade = idade;
	}
	public String getIdjogador() {
		return idjogador;
	}
	public void setIdjogador(String idjogador) {
		this.idjogador = idjogador;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getNmrCamisa() {
		return nmrCamisa;
	}
	public void setNmrCamisa(String nmrCamisa) {
		this.nmrCamisa = nmrCamisa;
	}
}
