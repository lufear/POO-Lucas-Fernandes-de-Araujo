package View;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;




	public class MenuCliente extends JFrame{
			
		JLabel imagem= new JLabel("Bem vindo � loja Super Games");
		ImageIcon fundo = new ImageIcon(getClass().getResource("preloader-logo.png"));

		JMenuBar barra = new JMenuBar();		
		
		JMenu comprar = new JMenu("Comprar");
		JMenu consultas = new JMenu("Perfil");
		JMenu outros = new JMenu("Outros");
		
		
		JMenuItem comGame = new JMenuItem("Comprar Game");	
		JMenuItem pfCliente = new JMenuItem("Perfil de Cliente");	
		JMenuItem sair = new JMenuItem("Sair");
		
		
		
		
		
		public MenuCliente(){
			
			setTitle("SuperGames");
			setResizable(false);
			Container janela = this.getContentPane();
			setSize(600, 400);
			setLocationRelativeTo(janela);
			
			imagem.setIcon(fundo);
			janela.add(imagem);
			
			this.setJMenuBar(barra);
			
			barra.add(comprar);			
			comprar.add(comGame);
			
			barra.add(consultas);
			consultas.add(pfCliente);	
			
			barra.add(outros);
			outros.add(sair);			
						
			ComGame cog = new ComGame();
			comGame.addActionListener(cog);
			
			pfCliente pfc = new pfCliente();
			pfCliente.addActionListener(pfc);
			
			fechar f = new fechar();
			sair.addActionListener(f);
		
			
			this.setVisible(true);
		}
		
		
		private class ComGame implements ActionListener{

			public void actionPerformed(ActionEvent arg0) {

				getContentPane().removeAll();
				ComprarGame comGame=new ComprarGame();
		        comGame.setSize(600,400);   
		        getContentPane().repaint();
				getContentPane().revalidate();
				
				
			}
			
		}
		
		private class pfCliente implements ActionListener{

			public void actionPerformed(ActionEvent arg0) {

				getContentPane().removeAll();
				ConsultaCliente conCli=new ConsultaCliente();
		        conCli.setSize(600,400);   
				getContentPane().repaint();
				getContentPane().revalidate();
				
				
			}
			
		}
		
		
		
		private class fechar implements ActionListener{

			public void actionPerformed(ActionEvent arg0) {
				
				System.exit(0);		
				
			}
			
		}	
		

	}

