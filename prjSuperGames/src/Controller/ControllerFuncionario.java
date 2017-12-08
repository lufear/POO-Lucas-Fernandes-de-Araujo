package Controller;

import java.sql.SQLException;

import DAO.FuncionarioDAO;
import Model.ModelFuncionario;

public class ControllerFuncionario {
	
	public void CadastroFunc(ModelFuncionario func){
		FuncionarioDAO funcDAO = new FuncionarioDAO();
		try {
			funcDAO.cadastroFunc(func);
		} catch (SQLException e) {
			System.out.println("Erro" + e.getMessage());
		}
	}

}
