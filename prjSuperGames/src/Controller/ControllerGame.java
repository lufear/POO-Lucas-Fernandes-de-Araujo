package Controller;

import java.sql.SQLException;

import DAO.GameDAO;
import Model.ModelGame;

public class ControllerGame {
	
	public void cadastroGame(ModelGame game){
		GameDAO gameDAO= new GameDAO();
		try {
			gameDAO.inserir(game);
		} catch (SQLException e) {
			System.out.println("Erro" + e.getMessage());
			}
	
	}
	public void editarGame(ModelGame game){
		GameDAO dao = new GameDAO();
		try {
			dao.atualizar(game);
		} catch (SQLException e) {
				System.out.println("Erro" + e.getMessage());
			}
	}

}
