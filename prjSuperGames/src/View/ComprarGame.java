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

import DAO.GameDAO;
import Model.ModelGame;


public class ComprarGame extends JFrame {

	private JPanel painelFundo;
	private JPanel painelBotoes;
	private JTable tabela;
	private JScrollPane barraRolagem;
	private JButton btComprar;
	private DefaultTableModel modelo = new DefaultTableModel();

	public ComprarGame() {
		
		criaJTable();
		criaJanela();
	}

	public void criaJanela() {
		btComprar = new JButton("Comprar");
		painelBotoes = new JPanel();
		barraRolagem = new JScrollPane(tabela);
		painelFundo = new JPanel();
		painelFundo.setLayout(new BorderLayout());
		painelFundo.add(BorderLayout.CENTER, barraRolagem);
		painelBotoes.add(btComprar);
		painelFundo.add(BorderLayout.SOUTH, painelBotoes);

		getContentPane().add(painelFundo);
		
		setSize(500, 320);
		setVisible(true);
		
		btComprar.addActionListener(new BtComprarListener());
	}

	private void criaJTable() {
		tabela = new JTable(modelo);
		modelo.addColumn("Id");
		modelo.addColumn("Descrição");
		modelo.addColumn("Valor");
		tabela.getColumnModel().getColumn(0).setPreferredWidth(10);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(120);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(80);
		pesquisar(modelo);
	}

	public static void pesquisar(DefaultTableModel modelo) {
		modelo.setNumRows(0);
		GameDAO gameDao = new GameDAO();

		for (ModelGame game : gameDao.getGames()) {
			modelo.addRow(new Object[]{game.getIdGame(), game.getDescricao(), game.getValor()});
		}
	}


	private class BtComprarListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			int linhaSelecionada = -1;
			linhaSelecionada = tabela.getSelectedRow();
			if (linhaSelecionada >= 0) {
				int idGame = (int) tabela.getValueAt(linhaSelecionada, 0);
				GameDAO gameDao = new GameDAO();
				gameDao.remover(idGame);
				modelo.removeRow(linhaSelecionada);
				JOptionPane.showMessageDialog(null, "Compra realizada com sucesso.");
			} else {
				JOptionPane.showMessageDialog(null, "É necesário selecionar um game.");
			}
		}
	}

	
}
