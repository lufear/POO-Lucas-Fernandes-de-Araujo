package View;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import Controller.ControllerCliente;
import DAO.ClienteDAO;
import Model.ModelCliente;



public class EditarCliente extends JFrame {

	private DefaultTableModel modelo = new DefaultTableModel();
	private JPanel painelFundo;
	private JButton btSalvar;
	private JButton btLimpar;
	private JLabel lbNome;
	private JLabel lbCPF;
	private JLabel lbTel;
	private JLabel lbEmail;
	private JLabel lbLogin;
	private JLabel lbSenha;	
	private JLabel lbId;
	private JTextField txNome;
	private JFormattedTextField txCPF;	
	private MaskFormatter mfCPF;
	
	
	private JTextField txTel;
	private JTextField txEmail;
	private JTextField txLogin;
	private JPasswordField txSenha;
	private JTextField txId;
	ModelCliente cliente;
	private int linhaSelecionada;

	public EditarCliente(DefaultTableModel md, int idCliente, int linha) {
		super("Clientes");
		criaJanela();
		modelo = md;
		ClienteDAO cliDao = new ClienteDAO();
		cliente = cliDao.getClienteById(idCliente);
		txId.setText(Integer.toString(cliente.getIdCliente()));
		txNome.setText(cliente.getNome());
		txCPF.setText(cliente.getCPF());
		txTel.setText(cliente.getTel());
		txEmail.setText(cliente.getEmail());
		linhaSelecionada = linha;  
	}

	public void criaJanela() {
		btSalvar = new JButton("Salvar");
		btLimpar = new JButton("Limpar");
		lbNome = new JLabel("         Nome.:   ");
		lbCPF = new JLabel("         CPF.:   ");
		lbTel = new JLabel("         Tel.:   ");
		lbEmail = new JLabel("         Email.:   ");
		lbLogin = new JLabel("         Login.:   ");
		lbSenha = new JLabel("         Senha.:   ");
		lbId = new JLabel("         Id.:   ");
		txNome= new JTextField();
		
		try{
			
			mfCPF = new MaskFormatter("###.###.###-##");
			mfCPF.setPlaceholderCharacter('_');
			
			
		}catch(ParseException excp){}
		
		txCPF= new JFormattedTextField(mfCPF);
		
		txTel= new JTextField();
		txEmail= new JTextField();
		txLogin = new JTextField();
		txSenha = new JPasswordField();
		txId= new JTextField();
		txId.setEditable(false);
		
		painelFundo = new JPanel();
		painelFundo.setLayout(new GridLayout(9, 2, 2, 4));
		painelFundo.add(lbId);
		painelFundo.add(txId);
		painelFundo.add(lbNome);
		painelFundo.add(txNome);
		painelFundo.add(lbCPF);
		painelFundo.add(txCPF);
		painelFundo.add(lbTel);
		painelFundo.add(txTel);
		painelFundo.add(lbEmail);
		painelFundo.add(txEmail);
		painelFundo.add(lbLogin);
		painelFundo.add(txLogin);
		painelFundo.add(lbSenha);
		painelFundo.add(txSenha);
		painelFundo.add(btLimpar);
		painelFundo.add(btSalvar);

		getContentPane().add(painelFundo);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(600, 300);
		setVisible(true);

		btSalvar.addActionListener(new EditarCliente.BtSalvarListener());
		btLimpar.addActionListener(new EditarCliente.BtLimparListener());
	}

	private class BtSalvarListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if(txCPF.getText().equals("") || txTel.getText().equals("") || txNome.getText().equals("") || txEmail.getText().equals("") || txLogin.getText().equals("") || txSenha.getText().equals("")){
				JOptionPane.showMessageDialog(null,"Preencha todos os campos");
			}
			else{
			ModelCliente cli = new ModelCliente();
			cli.setIdCliente(Integer.parseInt(txId.getText()));
			cli.setCPF(txCPF.getText());
			cli.setTel(txTel.getText());
			cli.setNome(txNome.getText());
			cli.setEmail(txEmail.getText());
			cli.setLogin(txLogin.getText());
			cli.setSenha(txSenha.getText());

			ControllerCliente cc = new ControllerCliente();
			cc.editarCli(cli);
			modelo.removeRow(linhaSelecionada);
			modelo.addRow(new Object[]{cli.getIdCliente(), cli.getNome(), cli.getCPF(), cli.getTel(), cli.getEmail(), cli.getLogin(), cli.getSenha()});
			setVisible(true);
			}
		}
	}

	private class BtLimparListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			txNome.setText("");
			txCPF.setText("");
			txTel.setText("");
			txEmail.setText("");
			txLogin.setText("");
			txSenha.setText("");
		}
	}
}
