package DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Model.ModelFuncionario;

public class FuncionarioDAO {
	
	private Connection conn ;
	
	private final String INSERT = "INSERT INTO tbFuncionario (nomeFuncionario, cpfFuncionario, loginFuncionario, senhaFuncionario) values (?,?,?,?)";
	private final String UPDATE = "UPDATE tbFuncionario SET nomeFuncionario=?, cpfFuncionario=?, loginFuncionario=?, senhaFuncionario=?";
	private final String DELETE = "DELETE FROM tbFuncionario WHERE idFuncionario=?";
	private final String LIST = "SELECT idFuncionario,nomeFuncionario, cpfFuncionario, loginFuncionario, senhaFuncionario FROM tbFuncionario";
	private final String LISTBYID = "SELECT idFuncionario,nomeFuncionario, cpfFuncionario, loginFuncionario, senhaFuncionario FROM tbFuncionario WHERE idFuncionario=?";
	
	public boolean cadastroFunc(ModelFuncionario funcionario) throws SQLException{
		try {
			conn = Conexao.getConexao();
			PreparedStatement pstm = 
					conn.prepareStatement(INSERT);
			pstm.setString(1, funcionario.getNome());
			pstm.setString(2, funcionario.getCPF());
			pstm.setString(3, funcionario.getLogin());
			pstm.setString(4, funcionario.getSenha());
			pstm.execute();
			Conexao.fechaConexao(conn,pstm);
			JOptionPane.showMessageDialog(null,"Funcionario cadastrado com sucesso");
			return true;
		} catch (Exception e) {
			System.out.println("ERRO: "+e.getMessage());
			return false;
		}
		
	}
	
	public void atualizar(ModelFuncionario funcionario) {
		if (funcionario != null) { Connection conn = null;
		
			try {
				conn = Conexao.getConexao();
				PreparedStatement pstm =
						conn.prepareStatement(UPDATE);
				pstm.setString(1, funcionario.getNome());
				pstm.setString(2, funcionario.getCPF());
				pstm.setString(3, funcionario.getLogin());
				pstm.setString(4, funcionario.getSenha());
				pstm.execute();
				JOptionPane.showMessageDialog(null, "Funcionario alterado com sucesso");
				Conexao.fechaConexao(conn);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Erro ao atualizar funcionario no banco de" + "dados " + e.getMessage());
				}
		}
			else {
				JOptionPane.showMessageDialog(null, "O funcionario enviado por par�metro est� vazio");
			}
		}
	
	public void remover(ModelFuncionario funcionario) {
		Connection conn = null;
		try {
			conn = Conexao.getConexao();
			PreparedStatement pstm =
					conn.prepareStatement(DELETE);
			pstm.setString(1, funcionario.getCPF());
			pstm.execute();
			Conexao.fechaConexao(conn, pstm);
		}
			catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Erro ao excluir contato do banco de" + "dados " + e.getMessage());
			}
		} 
	
	public List<ModelFuncionario> getFuncionarios() {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		ArrayList<ModelFuncionario> funcionarios = new ArrayList<ModelFuncionario>();
		try {
			conn = Conexao.getConexao();
			pstm = conn.prepareStatement(LIST);
			rs = pstm.executeQuery();
			while (rs.next()) { 
				ModelFuncionario funcionario = new ModelFuncionario();
				funcionario.setIdFuncionario(Integer.parseInt(rs.getString("idFuncionario")));
				funcionario.setNome(rs.getString("nomeFuncionario"));
				funcionario.setCPF(rs.getString("cpfFuncionario"));
				funcionario.setLogin(rs.getString("loginFuncionario"));
				funcionario.setSenha(rs.getString("senhaFuncionario"));
				funcionarios.add(funcionario);
			}
			Conexao.fechaConexao(conn, pstm, rs);
			}
			catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Erro ao listar contatos" + e.getMessage());
			}
		return funcionarios;
		}
	
	
          
      
}
	
	
		
	

		
	


