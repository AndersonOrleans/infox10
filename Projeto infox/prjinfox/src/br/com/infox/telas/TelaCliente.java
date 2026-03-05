package br.com.infox.telas;

import java.awt.EventQueue;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.sql.*;
import br.com.infox.dal.ModuloConexao;
import net.proteanit.sql.DbUtils;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class TelaCliente extends JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    private static final long serialVersionUID = 1L;
    private JTextField txtCliPesquisar;
    private JTextField txtCliNome;
    private JTextField txtCliEndereco;
    private JTextField txtCliFone;
    private JTextField txtCliEmail;
    private JTable tblClientes;
    private JTextField txtIdCli;
    private JLabel btnAdicionar;

    public TelaCliente() {
    	
    	setClosable(true);
    	setIconifiable(false);
    	setMaximizable(false);
    	setResizable(false); 

        conexao = ModuloConexao.conector();

        setTitle("SUL TECH - Clientes");
        setBounds(10, 11, 806, 657);

        btnAdicionar = new JLabel("");
        btnAdicionar.setIcon(new ImageIcon("C:\\Users\\ander\\OneDrive\\Área de Trabalho\\Projeto infox\\Imgens\\create.png"));
        btnAdicionar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                adicionar();
            }
        });

        JLabel btnAlterar = new JLabel("");
        btnAlterar.setIcon(new ImageIcon("C:\\Users\\ander\\OneDrive\\Área de Trabalho\\Projeto infox\\Imgens\\update.png"));

        btnAlterar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                alterar();
            }
        });

        JLabel btnRemover = new JLabel("");
        btnRemover.setIcon(new ImageIcon("C:\\Users\\ander\\OneDrive\\Área de Trabalho\\Projeto infox\\Imgens\\delete.png"));
        
        btnRemover.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                remover();
            }
        });

        JLabel lblObrigatorio = new JLabel("* Campo obrigatório");
        lblObrigatorio.setForeground(new Color(255, 0, 0));
        lblObrigatorio.setFont(new Font("Arial", Font.PLAIN, 13));

        txtCliPesquisar = new JTextField();
        txtCliPesquisar.setColumns(10);

        txtCliPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                pesquisar_cliente();
            }
        });

        JLabel lblPesquisarIcon = new JLabel("");
        lblPesquisarIcon.setIcon(new ImageIcon("C:\\Users\\ander\\OneDrive\\Área de Trabalho\\Projeto infox\\Imgens\\search.png"));

        JLabel lblNome = new JLabel("* Nome:");
        lblNome.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel lblEndereco = new JLabel("Endereço:");
        lblEndereco.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel lblTelefone = new JLabel("* Telefone:");
        lblTelefone.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel lblEmail = new JLabel("E-mail:");
        lblEmail.setFont(new Font("Arial", Font.PLAIN, 14));

        txtCliNome = new JTextField();
        txtCliEndereco = new JTextField();
        txtCliFone = new JTextField();
        txtCliEmail = new JTextField();

        tblClientes = new JTable();

        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome do cliente", "Endereço", "Telefone", "E-mail"
            }
        ));

        tblClientes.getTableHeader().setReorderingAllowed(false);

        JScrollPane scrollPane = new JScrollPane(tblClientes);
        
        tblClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                setar_campos();
            }
        });
        
        JLabel lblNewLabel = new JLabel("Id Cliente:");
        lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        
        txtIdCli = new JTextField();
        txtIdCli.setEditable(false);
        txtIdCli.setColumns(10);
        
        JLabel lblNewLabel_1 = new JLabel("Adicionar");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        
        JLabel lblNewLabel_1_1 = new JLabel("Editar");
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        
        JLabel lblNewLabel_1_1_1 = new JLabel("Excluir");
        lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));

        GroupLayout groupLayout = new GroupLayout(getContentPane());
        groupLayout.setHorizontalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGap(40)
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addGroup(groupLayout.createSequentialGroup()
        					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 710, GroupLayout.PREFERRED_SIZE)
        						.addGroup(groupLayout.createSequentialGroup()
        							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
        									.addComponent(lblNome)
        									.addComponent(lblEndereco)
        									.addComponent(lblEmail)
        									.addComponent(lblTelefone)
        									.addComponent(lblNewLabel))
        								.addGroup(groupLayout.createSequentialGroup()
        									.addGap(48)
        									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        										.addComponent(lblNewLabel_1)
        										.addComponent(btnAdicionar))))
        							.addPreferredGap(ComponentPlacement.RELATED)
        							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        								.addComponent(txtIdCli, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
        								.addComponent(txtCliFone, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
        								.addComponent(txtCliEndereco, 637, 637, 637)
        								.addComponent(txtCliNome, GroupLayout.DEFAULT_SIZE, 637, Short.MAX_VALUE)
        								.addGroup(groupLayout.createSequentialGroup()
        									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
        										.addComponent(txtCliEmail, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
        										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        											.addGroup(groupLayout.createSequentialGroup()
        												.addGap(10)
        												.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
        											.addComponent(btnAlterar)))
        									.addGap(215)
        									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        										.addGroup(groupLayout.createSequentialGroup()
        											.addComponent(btnRemover)
        											.addGap(108))
        										.addGroup(groupLayout.createSequentialGroup()
        											.addGap(10)
        											.addComponent(lblNewLabel_1_1_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))))
        					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        				.addGroup(groupLayout.createSequentialGroup()
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(txtCliPesquisar, GroupLayout.PREFERRED_SIZE, 359, GroupLayout.PREFERRED_SIZE)
        					.addGap(18)
        					.addComponent(lblPesquisarIcon)
        					.addPreferredGap(ComponentPlacement.RELATED, 188, Short.MAX_VALUE)
        					.addComponent(lblObrigatorio)
        					.addGap(53))))
        );
        groupLayout.setVerticalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addContainerGap(17, Short.MAX_VALUE)
        			.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
        				.addGroup(groupLayout.createSequentialGroup()
        					.addComponent(lblObrigatorio)
        					.addGap(26))
        				.addGroup(groupLayout.createSequentialGroup()
        					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
        						.addComponent(txtCliPesquisar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        						.addComponent(lblPesquisarIcon))
        					.addGap(18)))
        			.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
        			.addGap(56)
        			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblNewLabel)
        				.addComponent(txtIdCli, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addGap(18)
        			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(txtCliNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(lblNome))
        			.addGap(18)
        			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(txtCliEndereco, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(lblEndereco))
        			.addGap(18)
        			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(txtCliFone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(lblTelefone))
        			.addGap(18)
        			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblEmail)
        				.addComponent(txtCliEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addGap(59)
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addComponent(btnRemover)
        				.addComponent(btnAdicionar)
        				.addComponent(btnAlterar))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
        				.addComponent(lblNewLabel_1)
        				.addComponent(lblNewLabel_1_1_1, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
        			.addGap(92))
        );

        getContentPane().setLayout(groupLayout);
    }

    private void adicionar() {
        String sql = "insert into tbclientes (nomeCli, endCli, foneCli, emailCli) values (?, ?, ?, ?)";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtCliNome.getText());
            pst.setString(2, txtCliEndereco.getText());
            pst.setString(3, txtCliFone.getText());
            pst.setString(4, txtCliEmail.getText());

            if (txtCliNome.getText().isEmpty() || txtCliFone.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios!");
            } else {
            	
                int adicionado = pst.executeUpdate();
                
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Dados do cliente adicionado com sucesso");
                    txtCliNome.setText(null);
                    txtCliEndereco.setText(null);
                    txtCliFone.setText(null);
                    txtCliEmail.setText(null);
                    txtIdCli.setText(null);
                    pesquisar_cliente(); 
                }
                
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void pesquisar_cliente() {
    	
    	if (txtCliPesquisar.getText().isEmpty()) {
    	    txtCliNome.setText(null);
    	    txtCliEndereco.setText(null);
    	    txtCliFone.setText(null);
    	    txtCliEmail.setText(null);
    	    txtIdCli.setText(null);
   	    
    	    tblClientes.setModel(new javax.swing.table.DefaultTableModel(
    	        new Object[][] {},
    	        new String[]{"ID", "Nome do cliente", "Endereço", "Telefone", "E-mail"}
    	    ));
    	    tblClientes.getTableHeader().setReorderingAllowed(false);

    	    btnAdicionar.setEnabled(true);

    	    return;
    	}
    	
        String sql = "select idCli as ID, " + "nomeCli as 'Nome do cliente', " + "endCli as Endereço, " + "foneCli as Telefone, " + "emailCli as 'E-mail' " + "from tbclientes where nomeCli like ?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtCliPesquisar.getText() + "%");
            rs = pst.executeQuery();

            tblClientes.setModel(DbUtils.resultSetToTableModel(rs));

            tblClientes.getColumnModel().getColumn(0).setPreferredWidth(40);
            tblClientes.getColumnModel().getColumn(1).setPreferredWidth(200);
            tblClientes.getColumnModel().getColumn(2).setPreferredWidth(200);
            tblClientes.getColumnModel().getColumn(3).setPreferredWidth(120);
            tblClientes.getColumnModel().getColumn(4).setPreferredWidth(200);

            tblClientes.setRowHeight(25);
            tblClientes.setDefaultEditor(Object.class, null);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void setar_campos() {

        int setar = tblClientes.getSelectedRow();

        if (setar != -1) {

            txtIdCli.setText(
                    tblClientes.getModel().getValueAt(setar, 0).toString()
            );

            txtCliNome.setText(
                    tblClientes.getModel().getValueAt(setar, 1).toString()
            );

            txtCliEndereco.setText(
                    tblClientes.getModel().getValueAt(setar, 2).toString()
            );

            txtCliFone.setText(
                    tblClientes.getModel().getValueAt(setar, 3).toString()
            );

            txtCliEmail.setText(
                    tblClientes.getModel().getValueAt(setar, 4).toString()
            );

            btnAdicionar.setEnabled(false);
        }
    }
    
    private void alterar () {
        String sql ="update tbclientes set nomeCli=?, endCli=?, foneCli=?, emailCli=? where idCli=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtCliNome.getText());
            pst.setString(2, txtCliEndereco.getText());
            pst.setString(3, txtCliFone.getText());
            pst.setString(4, txtCliEmail.getText());
            pst.setString(5, txtIdCli.getText());
            
            if (txtCliNome.getText().isEmpty() || txtCliFone.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios!");
            } else {
                int adicionado = pst.executeUpdate();
                
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Dados do cliente alterados com sucesso");

                    txtCliNome.setText(null);
                    txtCliEndereco.setText(null);
                    txtCliFone.setText(null);
                    txtCliEmail.setText(null);
                    txtIdCli.setText(null);

                    txtCliPesquisar.setText(null);
                    
                    tblClientes.setModel(new javax.swing.table.DefaultTableModel(
                        new Object[][] {},
                        new String[] {"ID", "Nome do cliente", "Endereço", "Telefone", "E-mail"}
                    ));
                    tblClientes.getTableHeader().setReorderingAllowed(false);
         
                    btnAdicionar.setEnabled(true);
                }
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
  
    
    private void remover() {
        
        Object[] opcoes = {"SIM", "NÃO"};

        int confirma = JOptionPane.showOptionDialog(
                null,
                "Tem certeza que deseja remover este cliente?",
                "Atenção",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcoes,
                opcoes[1]
        );

        if (confirma == 0) {

            String SQL = "delete from tbclientes where idCli = ?";

            try {
                pst = conexao.prepareStatement(SQL);
                pst.setString(1, txtIdCli.getText());

                int apagado = pst.executeUpdate();

                if (apagado > 0) {

                    JOptionPane.showMessageDialog(null, "Cliente removido com sucesso");

                    
                    txtCliNome.setText(null);
                    txtCliEndereco.setText(null);
                    txtCliFone.setText(null);
                    txtCliEmail.setText(null);
                    txtIdCli.setText(null);
                    txtCliPesquisar.setText(null); 
                    btnAdicionar.setEnabled(true);

                    tblClientes.setModel(new javax.swing.table.DefaultTableModel(
                            new Object[][] {

                            },
                            new String[]{
                                    "ID", "Nome do cliente", "Endereço", "Telefone", "E-mail"
                            }
                    ));

                    tblClientes.getTableHeader().setReorderingAllowed(false);
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        
    }
}







