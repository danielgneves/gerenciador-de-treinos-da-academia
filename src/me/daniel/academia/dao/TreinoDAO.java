package me.daniel.academia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.daniel.academia.factory.ConnectionFactory;
import me.daniel.academia.model.Exercicio;
import me.daniel.academia.model.Treino;

public class TreinoDAO {

	// Create
	public void adicionarTreino(Treino treino) {

		String sql = "INSERT INTO treinos(nome, tipo, data) VALUES (?, ?, ?)";

		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {

			// Tabela treino
			conn = ConnectionFactory.conectar();
			pstm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			pstm.setString(1, treino.getNome());
			pstm.setString(2, treino.getTipo());
			pstm.setString(3, treino.getDataTreino());

			// recupera o ID da ultima linha inserida
			int rowsAffected = pstm.executeUpdate();
			int id = 0;
			if (rowsAffected > 0) {
				rs = pstm.getGeneratedKeys();

				if (rs.next()) {
					id = rs.getInt(1);
				}
			}

			// Tabela exercicios
			if (id != 0) { // verifica se alguma tupla foi adicionada

				sql = "INSERT INTO exercicios(nome_exercicio, series, repeticoes, carga, treino_id) VALUES (?, ?, ?, ?, ?)";
				pstm = conn.prepareStatement(sql);

				for (Exercicio c : treino.getExercicios()) {
					pstm.setString(1, c.getNomeExercicio());
					pstm.setInt(2, c.getSeries());
					pstm.setInt(3, c.getRepeticoes());
					pstm.setInt(4, c.getCarga());
					pstm.setInt(5, id);
					pstm.execute();
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			// Fechar as conexões caso estejam abertas
			try {

				if (conn != null)
					conn.close();

				if (pstm != null)
					pstm.close();

				if (rs != null)
					rs.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

	// Read
	public List<Treino> getTreinos() {

		Map<Integer, Treino> treinosMap = new HashMap<>();

		// tabela treinos
		String sql = "SELECT * FROM treinos T LEFT JOIN exercicios e ON t.id = e.treino_id ORDER BY t.id";
		// String sql = "SELECT * FROM treinos, exercicios WHERE id = treino_id";
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {

			conn = ConnectionFactory.conectar();
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();

			while (rs.next()) {

				int treinoId = rs.getInt("t.id");

				// Se o treino já existe no mapa, reutilize-o
				Treino treino = treinosMap.get(treinoId);
				if (treino == null) {
					treino = new Treino();
					treino.setId(treinoId);
					treino.setNome(rs.getString("nome"));
					treino.setTipo(rs.getString("tipo"));
					treino.setDataTreino(rs.getString("data"));

					treinosMap.put(treinoId, treino);
				}

				int exercicioId = rs.getInt("e.id");
				if (exercicioId > 0) {
					Exercicio exercicio = new Exercicio();
					exercicio.setId(exercicioId);
					exercicio.setNomeExercicio(rs.getString("nome_exercicio"));
					exercicio.setRepeticoes(rs.getInt("repeticoes"));
					exercicio.setSeries(rs.getInt("series"));
					exercicio.setCarga(rs.getInt("carga"));
					exercicio.setTreinoId(treinoId);

					treino.adicionarExercicio(exercicio);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {

				if (conn != null)
					conn.close();

				if (pstm != null)
					pstm.close();

				if (rs != null)
					rs.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return new ArrayList<>(treinosMap.values());

	}

	// Update
	public void updateTreino(Treino treino) {

		String sql = "UPDATE treinos SET nome = ?, tipo = ?, data = ? WHERE id = ?";

		Connection conn = null;
		PreparedStatement pstm = null;

		try {

			conn = ConnectionFactory.conectar();
			pstm = conn.prepareStatement(sql);

			// Adicionar os valores para atualizar
			pstm.setString(1, treino.getNome());
			pstm.setString(2, treino.getTipo());
			pstm.setString(3, treino.getDataTreino());
			pstm.setInt(4, treino.getId());

			pstm.execute();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {

				if (conn != null)
					conn.close();

				if (pstm != null)
					pstm.close();

			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}

	}

	public void updateExercicio(Exercicio exercicio) {

		String sql = "UPDATE exercicios SET nome_exercicio = ?, series = ?, repeticoes = ?, carga = ? WHERE id = ?";

		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			
			conn = ConnectionFactory.conectar();
			pstm = conn.prepareStatement(sql);
			
			pstm.setString(1, exercicio.getNomeExercicio());
			pstm.setInt(2, exercicio.getSeries());
			pstm.setInt(3, exercicio.getRepeticoes());
			pstm.setInt(4, exercicio.getCarga());
			pstm.setInt(5, exercicio.getId());
			
			pstm.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Delete
	public void deleteById(int id) {
		String sql = "DELETE FROM treinos WHERE id = ?";

		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			conn = ConnectionFactory.conectar();

			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id);
			pstm.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {

				if (conn != null)
					conn.close();

				if (pstm != null)
					pstm.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}

}
