package View;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import Controller.ControllerFuncionario;
import Model.ModelFuncionario;


public class CadastroFuncionario extends JFrame{
	
	

	JLabel lblNome;
	JLabel lblCPF;
	JLabel lblLogin;
	JLabel lblSenha;
	
		
	JTextField txtNome;
	JTextField txtLogin;
	JPasswordField txtSenha;
	
	
	JFormattedTextField txtCPF;
	
	MaskFormatter mfCPF;
	
	JButton btnCadastrar;
	JButton btnCancelar;
		
	
	
	
	public CadastroFuncionario(){
		
		this.setTitle("Cadastro Funcion�rio");
		this.setSize(600,400);
		this.setResizable(false);
		
		this.getContentPane().setBackground(new Color (255,255,255));
		
		Container janela = this.getContentPane();
		setLocationRelativeTo(janela);
		janela.setLayout(null);
		
		lblNome = new JLabel();
		lblNome.setText("Nome:");
		lblNome.setBounds(15,15,70,20);
		this.add(lblNome);
				
		
		lblCPF = new JLabel();
		lblCPF.setText("CPF:");
		lblCPF.setBounds(15,45,70,20);
		this.add(lblCPF);
		
		lblLogin = new JLabel();
		lblLogin.setText("Login:");
		lblLogin.setBounds(15,75,70,20);
		this.add(lblLogin);
		
		lblSenha = new JLabel();
		lblSenha.setText("Senha:");
		lblSenha.setBounds(15,105,70,20);
		this.add(lblSenha);
		
				
		txtNome = new JTextField();
		txtNome.setBounds(80,15,100,20);
		this.add(txtNome);
		
		txtLogin = new JTextField();
		txtLogin.setBounds(80,75,100,20);
		this.add(txtLogin);
		
		txtSenha = new JPasswordField();
		txtSenha.setBounds(80,105,100,20);
		this.add(txtSenha);
		
		
		
		

		try{
			
			mfCPF = new MaskFormatter("###.###.###-##");
			mfCPF.setPlaceholderCharacter('_');
			
			
		}catch(ParseException excp){}	
		
		txtCPF = new JFormattedTextField(mfCPF);
		txtCPF.setBounds(80,45,100,20);
		this.add(txtCPF);
		
			
		btnCadastrar = new JButton();
		btnCadastrar.setBounds(185,275,100,20);
		btnCadastrar.setText("Cadastrar");
		this.add(btnCadastrar);
		
		btnCancelar= new JButton();
		btnCancelar.setText("Cancelar");
		btnCancelar.setBounds(450,300,120,20);
		this.add(btnCancelar);
		

		EventoCadastrar ecad = new EventoCadastrar();
		btnCadastrar.addActionListener(ecad);
		
		EventoCancelar ecan = new EventoCancelar();
		btnCancelar.addActionListener(ecan);
		
				

		this.setVisible(true);
	}
	
	private class EventoCadastrar implements ActionListener{
	

		public void actionPerformed(ActionEvent arg0) {
			
			if(txtCPF.getText().equals("") || txtNome.getText().equals("") || txtLogin.getText().equals("") || txtSenha.getText().equals("")){
				JOptionPane.showMessageDialog(null,"Preencha todos os campos");
			}
			else{
			ModelFuncionario func = new ModelFuncionario();
			func.setCPF(txtCPF.getText());
			func.setLogin(txtLogin.getText());
			func.setSenha(txtSenha.getText());
			func.setNome(txtNome.getText());
			
			ControllerFuncionario cf = new ControllerFuncionario();
			cf.CadastroFunc(func);
			
			txtCPF.setText("");
			txtNome.setText("");
			txtLogin.setText("");
			txtSenha.setText("");
			}
			
			
		}
		
	}
	

	private class EventoCancelar implements ActionListener{
	
		public void actionPerformed(ActionEvent arg0) {
			
			new TelaInicial();
			dispose();
			
		}
	}
		}