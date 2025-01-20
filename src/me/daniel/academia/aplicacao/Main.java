package me.daniel.academia.aplicacao;

import java.util.ArrayList;
import java.util.List;

import me.daniel.academia.dao.TreinoDAO;
import me.daniel.academia.model.Exercicio;
import me.daniel.academia.model.Treino;

public class Main {

	public static void main(String[] args) {

		TreinoDAO treinoDao = new TreinoDAO();

		List<Exercicio> exercicios = new ArrayList<Exercicio>();

		Exercicio ex1 = new Exercicio();
		ex1.setNomeExercicio("Remada convergente pronada");
		ex1.setRepeticoes(12);
		ex1.setSeries(4);
		ex1.setCarga(25);
		exercicios.add(ex1);

		Exercicio ex2 = new Exercicio();
		ex2.setNomeExercicio("Remada baixa");
		ex2.setRepeticoes(12);
		ex2.setSeries(4);
		ex2.setCarga(40);
		exercicios.add(ex2);

		Exercicio ex3 = new Exercicio();
		ex3.setNomeExercicio("Puxada supinada");
		ex3.setRepeticoes(12);
		ex3.setSeries(4);
		ex3.setCarga(50);
		exercicios.add(ex3);

		/*
		 * for (Exercicio e : exercicios) { System.out.println(e.getNomeExercicio());
		 * System.out.println(e.getRepeticoes()); System.out.println(e.getSeries());
		 * System.out.println(e.getCarga()); System.out.println(); }
		 */

		Treino treino = new Treino();
		treino.setNome("Treino B");
		treino.setTipo("Hipertrofia");
		treino.setDataTreino("2025/01/22");
		treino.setExercicios(exercicios);

		//treinoDao.adicionarTreino(treino);
		treino.setId(4);
		treinoDao.updateTreino(treino);
		
		exibirInfo(treinoDao);

	}

	public static void exibirInfo(TreinoDAO treinoDao) {

		for (Treino t : treinoDao.getTreinos()) {

			System.out.println("Nome: " + t.getNome() + "; Tipo: " + t.getTipo() + "; Data: " + t.getDataTreino());

			for (Exercicio ex : t.getExercicios()) {
				System.out.println(ex);
			}

			System.out.println();

		}

	}

}
