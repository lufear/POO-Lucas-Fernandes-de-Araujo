package View;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


import DAO.FuncionarioDAO;
import Model.ModelFuncionario;


public class ConsultaFuncionario extends JFrame {

	private JPanel painelFundo;
	private JPanel painelBotoes;
	private JTable tabela;
	private JScrollPane barraRolagem;
	private JButton btExcluir;
	private JButton btEditar;
	private DefaultTableModel modelo = new DefaultTableModel();

	public ConsultaFuncionario() {
		
		criaJTable();
		criaJanela();
	}

	public void criaJanela() {
		setTitle("Consulta de Funcionario");
		painelBotoes = new JPanel();
		barraRolagem = new JScrollPane(tabela);
		painelFundo = new JPanel();
		painelFundo.setLayout(new BorderLayout());
		painelFundo.add(BorderLayout.CENTER, barraRolagem);
		painelFundo.add(BorderLayout.SOUTH, painelBotoes);

		getContentPane().add(painelFundo);
		
		setSize(500, 320);
		setVisible(true);
	}

	private void criaJTable() {
		tabela = new JTable(modelo);
		modelo.addColumn("Id");
		modelo.addColumn("Nome");
		modelo.addColumn("CPF");
		modelo.addColumn("Login");
		tabela.getColumnModel().getColumn(0).setPreferredWidth(100);
		tabela.getColumnModel().getColumn(0).setPreferredWidth(100);
		tabela.getColumnModel().getColumn(0).setPreferredWidth(100);
		tabela.getColumnModel().getColumn(0).setPreferredWidth(100);
		pesquisar(modelo);
	}

	public static void pesquisar(DefaultTableModel modelo) {
		modelo.setNumRows(0);
		FuncionarioDAO funcDao = new FuncionarioDAO();

		for (ModelFuncionario func : funcDao.getFuncionarios()) {
			modelo.addRow(new Object[]{func.getIdFuncionario(), func.getNome(), func.getCPF(), func.getLogin()});
		}
	}


	

	
	

	
}
