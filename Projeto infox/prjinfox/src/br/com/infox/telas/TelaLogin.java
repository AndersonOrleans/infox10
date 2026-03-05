package br.com.infox.telas;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import java.sql.*;
import br.com.infox.dal.ModuloConexao;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaLogin extends JFrame {
	
	Connection conexao = null;
	PreparedStatement pst = null;
	ResultSet rs = null;

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField txtSenha;
	private JLabel lblStatus;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaLogin frame = new TelaLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
					
				}
			}
		});
	}
	
	// Inicio do método logar:
	public void logar() {

	    String sql ="select * from tbusuarios where login=? and senha=?";
	    
	    try {
	        pst = conexao.prepareStatement(sql);
	        pst.setString(1, txtUsuario.getText());
	        pst.setString(2, new String(txtSenha.getPassword()));
	        rs = pst.executeQuery();
	        
	        if(rs.next()) {
	            
	            String perfil = rs.getString(6);
	            
	            TelaPrincipal principal = new TelaPrincipal();
	            principal.setVisible(true);	            
	            TelaPrincipal.lblUsuario.setText(rs.getString(2));

	            if(perfil.equals("admin")) {	           
	                TelaPrincipal.lblUsuario.setForeground(java.awt.Color.RED);
	                
	                TelaPrincipal.menRel.setEnabled(true);
	                TelaPrincipal.menCadUsu.setEnabled(true);
	            } else {	               
	                TelaPrincipal.lblUsuario.setForeground(java.awt.Color.BLACK);
	            }

	            this.dispose();
	        } else {
	            JOptionPane.showMessageDialog(null, "usuário e/ou senha inválido(s)");
	        }
	        
	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(null, e);
	    }
	}

	// Fim do método logar.

	public TelaLogin() {
		setTitle("SUL TECH - Login");
		
		conexao = ModuloConexao.conector();
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Aqui é a largura e altura da tela de Login:// 
		setSize(320, 180);
		setLocationRelativeTo(null);
		// ------------------------------------------//
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Usuário:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblNewLabel_1 = new JLabel("Senha:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtUsuario = new JTextField();
		txtUsuario.setColumns(10);
		
		txtSenha = new JPasswordField();
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        logar();
		    }
		});

		
		JLabel lblStatus = new JLabel("");
		lblStatus.setIcon(new ImageIcon("C:\\Users\\ander\\OneDrive\\Área de Trabalho\\Projeto infox\\Imgens\\dberror.png"));
		lblStatus.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		conexao = ModuloConexao.conector();
		
		if (conexao != null) {
			lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/dbok.png")));
		} else {
			lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/dberror.png")));
		}
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(25)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addComponent(lblStatus)
							.addComponent(lblNewLabel_1)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addComponent(txtSenha, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
							.addComponent(txtUsuario, 160, 160, 160))
						.addComponent(btnLogin))
					.addContainerGap(242, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(24)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(txtUsuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtSenha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblStatus)
						.addComponent(btnLogin))
					.addContainerGap(158, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);

	}
}
