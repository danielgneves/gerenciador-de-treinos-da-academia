package me.daniel.academia.aplicacao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import me.daniel.academia.dao.TreinoDAO;
import me.daniel.academia.model.Exercicio;
import me.daniel.academia.model.Treino;

public class Main {

	public static void main(String[] args) {

		TreinoDAO treinoDao = new TreinoDAO();
		List<Exercicio> exercicios = new ArrayList<Exercicio>();
		Scanner scan = new Scanner(System.in);

		int opcao = 0;

		while (opcao != 6) {

			System.out.println("1. Adicionar treino");
			System.out.println("2. Listar treinos");
			System.out.println("3. Atualizar treino");
			System.out.println("4. Atualizar exercício");
			System.out.println("5. Deletar treino");
			System.out.println("6. Sair");

			opcao = scan.nextInt();

			switch (opcao) {
			case 1: // adicioanr treino
				cadastrarTreino(scan, exercicios, treinoDao);
				break;

			case 2: // listar treinos
				exibirInfo(treinoDao);
				break;

			case 3: // atualizar treino
				atualizarTreino(scan, treinoDao);
				break;

			case 4: // atualizar exercicio
				atualizarExercicio(scan, treinoDao);
				break;

			case 5: // deletar treino por id
				System.out.println("Informe o ID do treino que deseja remover");
				treinoDao.deleteById(scan.nextInt());
				break;

			case 6: // sair
				System.out.println("Programa finalizado!");
				break;

			default:
				System.out.println("Opção inválida!");

			}

		}

	}

	public static void cadastrarTreino(Scanner scan, List<Exercicio> exercicios, TreinoDAO treinoDao) {

		Treino treino = new Treino();
		System.out.println("Insira as informações abaixo");

		System.out.println("Nome do treino: ");
		scan.nextLine();
		treino.setNome(scan.nextLine());
		System.out.println("Tipo: ");
		treino.setTipo(scan.nextLine());
		System.out.println("Data (YYYY/MM/DD): ");
		treino.setDataTreino(scan.nextLine());
		System.out.println();

		int opc = 0;
		while (opc != 2) {

			System.out.println("1. Inserir exercício");
			System.out.println("2. Sair");
			opc = scan.nextInt();

			switch (opc) {
			case 1:

				Exercicio ex = new Exercicio();
				scan.nextLine();
				System.out.println("Nome do exercicio:");
				ex.setNomeExercicio(scan.nextLine());
				System.out.println("Repetições:");
				ex.setRepeticoes(scan.nextInt());
				System.out.println("Series:");
				ex.setSeries(scan.nextInt());
				System.out.println("Carga");
				ex.setCarga(scan.nextInt());
				exercicios.add(ex);
				break;

			case 2:
				System.out.println("Retornando ao menu principal.");
				break;
			default:
				System.out.println("Opção inválida!");
			}

		}

		treino.setExercicios(exercicios);
		treinoDao.adicionarTreino(treino);

	}

	public static void exibirInfo(TreinoDAO treinoDao) {

		for (Treino t : treinoDao.getTreinos()) {

			System.out.println("ID: " + t.getId() + "; Nome: " + t.getNome() + "; Tipo: " + t.getTipo() + "; Data: "
					+ t.getDataTreino());

			for (Exercicio ex : t.getExercicios()) {
				System.out.println(ex);
			}

			System.out.println();

		}

	}

	public static void atualizarTreino(Scanner scan, TreinoDAO treinoDao) {

		Treino treino = new Treino();
		System.out.println("Informe o ID do treino que deseja atualizar");
		treino.setId(scan.nextInt());
		scan.nextLine();
		System.out.println("Nome do treino:");
		treino.setNome(scan.nextLine());
		System.out.println("Tipo de treino:");
		treino.setTipo(scan.nextLine());
		System.out.println("Data do treino:");
		treino.setDataTreino(scan.nextLine());
		System.out.println();

		System.out.println(
				"Nome: " + treino.getNome() + "; Tipo: " + treino.getTipo() + "; Data: " + treino.getDataTreino());
		int opc;
		System.out.println("Deseja salvar as informações acima? \n1. Sim \n2. Não");
		opc = scan.nextInt();

		while (opc != 1 && opc != 2) {
			System.out.println("Opção inválida, tente novamente.");
			opc = scan.nextInt();
		}

		if (opc == 1) {
			treinoDao.updateTreino(treino);
			System.out.println("Treino atualizado com sucesso!");
		} else {
			System.out.println("Informações descartadas.");
		}
		System.out.println();

	}

	public static void atualizarExercicio(Scanner scan, TreinoDAO treinoDao) {

		Exercicio ex = new Exercicio();
		System.out.println("Insira o ID do exercício que será atualizado:");
		ex.setId(scan.nextInt());
		System.out.println("Nome do exercicio:");
		scan.nextLine();
		ex.setNomeExercicio(scan.nextLine());
		System.out.println("Repetições:");
		ex.setRepeticoes(scan.nextInt());
		System.out.println("Series:");
		ex.setSeries(scan.nextInt());
		System.out.println("Carga:");
		ex.setCarga(scan.nextInt());

		System.out.println("ID: " + ex.getId() + "; Nome: " + ex.getNomeExercicio() + "; Repetições: "
				+ ex.getRepeticoes() + "; Séries: " + ex.getSeries() + "; Carga: " + ex.getCarga());
		System.out.println("Deseja salvar as informações acima? \n1. Sim \n2. Não");
		int opc = scan.nextInt();

		while (opc != 1 && opc != 2) {
			System.out.println("Opção inválida, tente novamente.");
			opc = scan.nextInt();
		}

		if (opc == 1) {
			treinoDao.updateExercicio(ex);
			System.out.println("Exercício atualizado com sucesso.");
		} else {
			System.out.println("Informações descartadas.");
		}
		System.out.println();

	}
}
