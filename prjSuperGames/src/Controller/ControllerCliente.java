package Controller;

import java.sql.SQLException;

import DAO.ClienteDAO;
import Model.ModelCliente;

public class ControllerCliente {
	
	public void cadastroCli(ModelCliente cli){
		ClienteDAO cliDAO= new ClienteDAO();
		try {
			cliDAO.cadastroCli(cli);
		} catch (SQLException e) {
			System.out.println("Erro" + e.getMessage());
		}
	
	}
	public void editarCli(ModelCliente cli){
		ClienteDAO cliDao = new ClienteDAO();
		try {			
			cliDao.atualizar(cli);
		} catch (SQLException e) {
			System.out.println("Erro" + e.getMessage());
		}
	
	}
}
