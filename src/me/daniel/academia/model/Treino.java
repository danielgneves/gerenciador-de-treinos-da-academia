package me.daniel.academia.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Treino {

	private int id;
	private String nome; // Peito bíceps, Costas tríceps, Pernas Ombros
	private String tipo; // Hipertrofia, força, resistência
	private String dataTreino; // YYYY/MM/DD
	private List<Exercicio> exercicios;

	public Treino(int id, String nome, String tipo, String dataTreino) {
		super();
		this.id = id;
		this.nome = nome;
		this.tipo = tipo;
		this.dataTreino = dataTreino;
	}

	public Treino() {
		super();
		this.exercicios = new ArrayList<>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDataTreino() {
		return dataTreino;
	}

	public void setDataTreino(String dataTreino) {
		this.dataTreino = dataTreino;
	}

	public List<Exercicio> getExercicios() {
		return exercicios;
	}

	public void setExercicios(List<Exercicio> exercicios) {
		this.exercicios = exercicios;
	}

	public void adicionarExercicio(Exercicio exercicio) {
		exercicios.add(exercicio);
	}

	@Override
	public String toString() {
		return "Treino [id=" + id + ", nome=" + nome + ", tipo=" + tipo + ", dataTreino=" + dataTreino
				+ ", \nexercicios=" + exercicios + "]";
	}

}
