package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Model.ModelGame;




public class GameDAO {

	private final String INSERT = "INSERT INTO tbGame(descricao, valor) VALUES (?,?)";
	private final String UPDATE = "UPDATE tbGame SET descricao =?, valor=? WHERE idGame=?";
	private final String DELETE = "DELETE FROM tbGame WHERE idGame =?";
	private final String LIST = "SELECT * FROM tbGame";
	private final String LISTBYID = "SELECT * FROM tbGame WHERE idGame=?";

	public boolean inserir(ModelGame game)throws SQLException {
		if (game != null) {
			Connection conn = null;
			try {
				conn = Conexao.getConexao();
				PreparedStatement pstm;
				pstm = conn.prepareStatement(INSERT);

				pstm.setString(1, game.getDescricao());
				pstm.setString(2, game.getValor());
				
				

				pstm.execute();
				JOptionPane.showMessageDialog(null, "Game cadastrado com sucesso");
				Conexao.fechaConexao(conn, pstm);
				return true;

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Erro ao cadastrar game " + e.getMessage());
				return false;
			}
		} else {
			System.out.println("O game enviado por par�metro est� vazio");
			return false;
		}
	}

	public void atualizar(ModelGame game) throws SQLException{
		if (game != null) {
			Connection conn = null;
			try {
				conn = Conexao.getConexao();
				PreparedStatement pstm;
				pstm = conn.prepareStatement(UPDATE);

				pstm.setString(1, game.getDescricao());
				pstm.setString(2, game.getValor());
				pstm.setInt(3, game.getIdGame());
				

				pstm.execute();
				JOptionPane.showMessageDialog(null, "Game alterado com sucesso");
				Conexao.fechaConexao(conn);

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Erro ao atualizar game" + e.getMessage());
			}
		} else {
			JOptionPane.showMessageDialog(null, "O game enviado por par�metro est� vazio");
		}


	}

	public void remover(int idGame) {
		Connection conn = null;
		try {
			conn = Conexao.getConexao();
			PreparedStatement pstm;
			pstm = conn.prepareStatement(DELETE);

			pstm.setInt(1, idGame);

			pstm.execute();
			Conexao.fechaConexao(conn, pstm);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao realizar opera��o" + e.getMessage());
		}
	}

	public List<ModelGame> getGames() {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		ArrayList<ModelGame> games = new ArrayList<ModelGame>();
		try {
			conn = Conexao.getConexao();
			pstm = conn.prepareStatement(LIST);
			rs = pstm.executeQuery();
			while (rs.next()) {
				ModelGame game = new ModelGame();

				
				game.setDescricao(rs.getString("descricao"));
				game.setValor(rs.getString("valor"));
				game.setIdGame(Integer.parseInt(rs.getString("idGame")));
				games.add(game);
			}
			Conexao.fechaConexao(conn, pstm, rs);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao listar games" + e.getMessage());
		}
		return games;
	}

	public ModelGame getGameById(int idGame) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		ModelGame game = new ModelGame();
		try {
			conn = Conexao.getConexao();
			pstm = conn.prepareStatement(LISTBYID);
			pstm.setInt(1, idGame);
			rs = pstm.executeQuery();
			while (rs.next()) {
				game.setIdGame(rs.getInt("idGame"));
				game.setDescricao(rs.getString("descricao"));
				game.setValor(rs.getString("valor"));
			}
			Conexao.fechaConexao(conn, pstm, rs);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao listar games" + e.getMessage());
		}
		return game;
	}
}

