package View;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Controller.ControllerGame;
import DAO.GameDAO;
import Model.ModelGame;


public class EditarGame extends JFrame {

	private DefaultTableModel modelo = new DefaultTableModel();
	private JPanel painelFundo;
	private JButton btSalvar;
	private JButton btLimpar;
	private JLabel lbDescricao;
	private JLabel lbValor;
	private JLabel lbId;
	private JTextField txDescricao;
	private JTextField txId;
	private JTextField txValor;
	ModelGame game;
	private int linhaSelecionada;

	public EditarGame(DefaultTableModel md, int idGame, int linha) {
		super("Games");
		criaJanela();
		modelo = md;
		GameDAO dao = new GameDAO();
		game = dao.getGameById(idGame);
		txId.setText(Integer.toString(game.getIdGame()));
		txDescricao.setText(game.getDescricao());
		txValor.setText(game.getValor());
		linhaSelecionada = linha;  
	}

	public void criaJanela() {
		btSalvar = new JButton("Salvar");
		btLimpar = new JButton("Limpar");
		lbDescricao = new JLabel("         Descricao.:   ");
		lbValor = new JLabel("         Valor.:   ");
		lbId = new JLabel("         Id.:   ");
		txDescricao = new JTextField();
		txValor = new JTextField();
		txId = new JTextField();
		txId.setEditable(false);

		painelFundo = new JPanel();
		painelFundo.setLayout(new GridLayout(5, 2, 2, 4));
		painelFundo.add(lbId);
		painelFundo.add(txId);
		painelFundo.add(lbDescricao);
		painelFundo.add(txDescricao);
		painelFundo.add(lbValor);
		painelFundo.add(txValor);
		painelFundo.add(btLimpar);
		painelFundo.add(btSalvar);

		getContentPane().add(painelFundo);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(300, 150);
		setVisible(true);

		btSalvar.addActionListener(new EditarGame.BtSalvarListener());
		btLimpar.addActionListener(new EditarGame.BtLimparListener());
	}

	private class BtSalvarListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if(txDescricao.getText().equals("") || txValor.getText().equals("")){
				JOptionPane.showMessageDialog(null,"Preencha todos os campos");
			}
			else{
			ModelGame game = new ModelGame();
			game.setIdGame(Integer.parseInt(txId.getText()));
			game.setDescricao(txDescricao.getText());
			game.setValor(txValor.getText());

			ControllerGame cg = new ControllerGame();
			cg.editarGame(game);
			modelo.removeRow(linhaSelecionada);
			modelo.addRow(new Object[]{game.getIdGame(), game.getDescricao(), game.getValor()});
			setVisible(true);
			}
		}
	}

	private class BtLimparListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			txDescricao.setText("");
			txValor.setText("");
		}
	}
}
