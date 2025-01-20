package me.daniel.academia.model;

public class Exercicio {

	private int id;
	private String nomeExercicio;
	private int series;
	private int repeticoes;
	private int carga;
	private int treinoId;

	// private int treinoId;
	public Exercicio(int id, String nomeExercicio, int series, int repeticoes, int carga) {
		super();
		this.id = id;
		this.nomeExercicio = nomeExercicio;
		this.series = series;
		this.repeticoes = repeticoes;
		this.carga = carga;
	}

	public Exercicio() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNomeExercicio() {
		return nomeExercicio;
	}

	public void setNomeExercicio(String nomeExercicio) {
		this.nomeExercicio = nomeExercicio;
	}

	public int getSeries() {
		return series;
	}

	public void setSeries(int series) {
		this.series = series;
	}

	public int getRepeticoes() {
		return repeticoes;
	}

	public void setRepeticoes(int repeticoes) {
		this.repeticoes = repeticoes;
	}

	public int getCarga() {
		return carga;
	}

	public void setCarga(int carga) {
		this.carga = carga;
	}

	public int getTreinoId() {
		return treinoId;
	}

	public void setTreinoId(int treinoId) {
		this.treinoId = treinoId;
	}

	@Override
	public String toString() {
		return "Exercicio [id=" + id + ", nomeExercicio=" + nomeExercicio + ", series=" + series + ", repeticoes="
				+ repeticoes + ", carga=" + carga + "]";
	}

}
