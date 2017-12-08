package View;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Controller.ControllerGame;
import Model.ModelGame;



public class CadastroGame extends JFrame{
	
	

	JLabel lblDescricao;
	JLabel lblValor;	
	
		
	JTextField txtDescricao;
	JTextField txtValor;
		
		
	JButton btnCadastrar;
	JButton btnCancelar;
		
	
	
	
	public CadastroGame(){
		
		this.setTitle("Cadastro de Game");
		this.setSize(600,400);
		this.setResizable(false);
		
		this.getContentPane().setBackground(new Color (255,255,255));
		
		Container janela = this.getContentPane();
		setLocationRelativeTo(janela);
		janela.setLayout(null);
		
		lblDescricao = new JLabel();
		lblDescricao.setText("Descricao:");
		lblDescricao.setBounds(15,15,70,20);
		this.add(lblDescricao);
				
		
		lblValor = new JLabel();
		lblValor.setText("Valor:");
		lblValor.setBounds(15,45,70,20);
		this.add(lblValor);
		
		
		txtDescricao = new JTextField();
		txtDescricao.setBounds(80,15,100,20);
		this.add(txtDescricao);
		
		txtValor = new JTextField();
		txtValor.setBounds(80,45,100,20);
		this.add(txtValor);
		
				
			
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
			
			if(txtDescricao.getText().equals("") || txtValor.getText().equals("")){
				JOptionPane.showMessageDialog(null,"Preencha todos os campos");
			}
			else{
			ModelGame game = new ModelGame();
			game.setDescricao(txtDescricao.getText());
			game.setValor(txtValor.getText());
			
			
			ControllerGame cg= new ControllerGame();
			cg.cadastroGame(game);
			txtDescricao.setText("");
			txtValor.setText("");
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
