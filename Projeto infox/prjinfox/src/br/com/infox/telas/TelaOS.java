package br.com.infox.telas;

import java.awt.EventQueue;
import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.TitledBorder;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.sql.*;
import br.com.infox.dal.ModuloConexao;
import net.proteanit.sql.DbUtils;


public class TelaOS extends JInternalFrame {
	
	Connection conexao = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	

    private static final long serialVersionUID = 1L;
    private JTextField txtOs;
    private JTextField txtData;
    private JRadioButton rbtOrc;
    private JRadioButton rbtOs;
    private JTextField txtCliPesquisar;
    private JTextField txtCliId;
    private JTable tabelaCliente;
    private JTextField txtOsEquip;
    private JTextField txtOsDef;
    private JTextField txtOsServ;
    private JTextField txtOsTec;
    private JTextField txtOsValor;
    private String tipo;
    private JComboBox cboOsSit;
    private JLabel btnOsAdicionar;
    private JScrollPane tblClientes;
    private JLabel btnOsAlterar;
    private JLabel btnOsExcluir;
    private JButton btnOsImprimir;
    private JLabel btnOsPesquisar;
    private JLabel lblNewLabel_8;
    private JLabel lblNewLabel_9;
    private JLabel lblNewLabel_10;
    private JLabel lblNewLabel_11;
    private JLabel lblNewLabel_12;
  

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TelaOS frame = new TelaOS();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
            }
        });
    }

    public TelaOS() {
    	
    	conexao = ModuloConexao.conector();
    	 	
        setTitle("SUL TECH - OS");
        setClosable(true);      
        setIconifiable(false);  
        setMaximizable(false);  
        setResizable(false);   

        setBounds(10, 11, 828, 678);

        JPanel panel = new JPanel();
        panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));

        JLabel lblNewLabel_2 = new JLabel("Situação");
        lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 12));
        
  
        cboOsSit = new JComboBox();
        cboOsSit.setFont(new Font("Arial", Font.PLAIN, 12));
        cboOsSit.setModel(new DefaultComboBoxModel(new String[] {" ", "Entrega OK", "Orçamento REPROVADO", "Aguardando Aprovação", "Aguardando peças", "Abandonado pelo cliente", "Na bancada", "Retornou"}));

        JPanel painelCliente = new JPanel();
        painelCliente.setBorder(new TitledBorder(
                new EtchedBorder(EtchedBorder.LOWERED),
                "Cliente",
                TitledBorder.LEADING,
                TitledBorder.TOP,
                null,
                new Color(0, 0, 0)));

        // ---------------- CAMPOS CLIENTE ----------------

        txtCliPesquisar = new JTextField();
        txtCliPesquisar.setColumns(10);
        
        txtCliPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                pesquisar_cliente();
            }
        });
        
        
        JLabel lblBusca = new JLabel("");
        lblBusca.setIcon(new ImageIcon("C:\\Users\\ander\\OneDrive\\Área de Trabalho\\Projeto infox\\Imgens\\search.png"));

        JLabel lblId = new JLabel("* Id");
        lblId.setFont(new Font("Arial", Font.PLAIN, 14));

        txtCliId = new JTextField();
        txtCliId.setEditable(false);
        txtCliId.setColumns(10);

        // ---------------- TABELA CLIENTE ----------------

        tabelaCliente = new JTable();
        tabelaCliente.setModel(new DefaultTableModel(
                new Object[][] {},
                new String[] { "Id", "Nome", "Fone" }
        ));
        
        tabelaCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                setar_cliente();
            }
        });

        
        tabelaCliente.getColumnModel().getColumn(0).setPreferredWidth(10); 
        tabelaCliente.getColumnModel().getColumn(1).setPreferredWidth(200); 
        tabelaCliente.getColumnModel().getColumn(2).setPreferredWidth(100); 

        tblClientes = new JScrollPane(tabelaCliente);

        // ---------------- LAYOUT PAINEL CLIENTE ----------------

        GroupLayout gl_painelCliente = new GroupLayout(painelCliente);
        gl_painelCliente.setHorizontalGroup(
        	gl_painelCliente.createParallelGroup(Alignment.TRAILING)
        		.addGroup(gl_painelCliente.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(gl_painelCliente.createParallelGroup(Alignment.TRAILING)
        				.addComponent(tblClientes, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
        				.addGroup(gl_painelCliente.createSequentialGroup()
        					.addComponent(txtCliPesquisar, GroupLayout.PREFERRED_SIZE, 235, GroupLayout.PREFERRED_SIZE)
        					.addGap(18)
        					.addComponent(lblBusca)
        					.addGap(40)
        					.addComponent(lblId)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(txtCliId, GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)))
        			.addContainerGap())
        );
        gl_painelCliente.setVerticalGroup(
        	gl_painelCliente.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_painelCliente.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(gl_painelCliente.createParallelGroup(Alignment.TRAILING)
        				.addGroup(gl_painelCliente.createParallelGroup(Alignment.LEADING)
        					.addGroup(gl_painelCliente.createSequentialGroup()
        						.addGap(12)
        						.addComponent(txtCliPesquisar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        					.addComponent(lblBusca, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
        				.addGroup(gl_painelCliente.createParallelGroup(Alignment.BASELINE)
        					.addComponent(txtCliId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        					.addComponent(lblId)))
        			.addGap(18)
        			.addComponent(tblClientes, GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
        			.addContainerGap())
        );
        painelCliente.setLayout(gl_painelCliente);

        // ---------------- PAINEL OS ----------------

        JLabel lblNewLabel = new JLabel("N° OS");
        lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel lblNewLabel_1 = new JLabel("Data");
        lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 14));

        txtOs = new JTextField();
        txtOs.setEditable(false);
        txtOs.setColumns(10);

        txtData = new JTextField();
        txtData.setEditable(false);
        txtData.setColumns(10);

        rbtOrc = new JRadioButton("Orçamento");
        rbtOrc.setFont(new Font("Arial", Font.PLAIN, 14));
        
        rbtOs = new JRadioButton("Ordem de Serviço");
        rbtOs.setFont(new Font("Arial", Font.PLAIN, 14));
        
        rbtOrc.setSelected(true);
        tipo = "Orçamento";
        
        rbtOrc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtOrcActionPerformed(evt);
            }
        });

        rbtOs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtOsActionPerformed(evt);
            }
        });
        
        

        ButtonGroup grupo = new ButtonGroup();
        grupo.add(rbtOrc);
        grupo.add(rbtOs);

        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
        	gl_panel.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panel.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
        				.addComponent(txtOs, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
        				.addComponent(rbtOrc)
        				.addComponent(lblNewLabel))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
        				.addGroup(gl_panel.createSequentialGroup()
        					.addGap(18)
        					.addComponent(rbtOs))
        				.addGroup(gl_panel.createSequentialGroup()
        					.addGap(17)
        					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
        						.addComponent(txtData, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
        						.addComponent(lblNewLabel_1))))
        			.addGap(16))
        );
        gl_panel.setVerticalGroup(
        	gl_panel.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panel.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblNewLabel_1)
        				.addComponent(lblNewLabel))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
        				.addComponent(txtData, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(txtOs, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
        				.addComponent(rbtOs)
        				.addComponent(rbtOrc))
        			.addContainerGap(87, Short.MAX_VALUE))
        );
        panel.setLayout(gl_panel);
        
        JLabel lblNewLabel_3 = new JLabel("* Equipamento");
        lblNewLabel_3.setFont(new Font("Arial", Font.PLAIN, 12));
        
        JLabel lblNewLabel_4 = new JLabel("* Defeito");
        lblNewLabel_4.setFont(new Font("Arial", Font.PLAIN, 12));
        
        txtOsEquip = new JTextField();
        txtOsEquip.setColumns(10);
        
        txtOsDef = new JTextField();
        txtOsDef.setColumns(10);
        
        btnOsImprimir = new JButton("");
        btnOsImprimir.setEnabled(false);
        btnOsImprimir.setToolTipText("Imprimir OS");
        btnOsImprimir.setIcon(new ImageIcon("C:\\Users\\ander\\OneDrive\\Área de Trabalho\\Projeto infox\\Imgens\\print.png"));
        
        btnOsAdicionar = new JLabel("");
        btnOsAdicionar.setIcon(new ImageIcon("C:\\Users\\ander\\OneDrive\\Área de Trabalho\\Projeto infox\\Imgens\\create.png"));

        btnOsAdicionar.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                emitir_os();
            }
        });
        
        btnOsPesquisar = new JLabel("");
        btnOsPesquisar.setIcon(new ImageIcon("C:\\Users\\ander\\OneDrive\\Área de Trabalho\\Projeto infox\\Imgens\\read.png"));
        
        btnOsPesquisar.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                pesquisar_os();
            }
        });
        
        btnOsAlterar = new JLabel("");
        btnOsAlterar.setEnabled(false);
        btnOsAlterar.setIcon(new ImageIcon("C:\\Users\\ander\\OneDrive\\Área de Trabalho\\Projeto infox\\Imgens\\update.png"));
        
        btnOsAlterar.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                alterar_os();
            }
        });
        
        btnOsExcluir = new JLabel("");
        btnOsExcluir.setEnabled(false);
        btnOsExcluir.setIcon(new ImageIcon("C:\\Users\\ander\\OneDrive\\Área de Trabalho\\Projeto infox\\Imgens\\delete.png"));
        
        btnOsExcluir.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                excluir_os();
            }
        });
        
        JLabel lblNewLabel_5 = new JLabel("Serviço");
        lblNewLabel_5.setFont(new Font("Arial", Font.PLAIN, 12));
        
        txtOsServ = new JTextField();
        txtOsServ.setColumns(10);
        
        JLabel lblNewLabel_6 = new JLabel("Técnico");
        lblNewLabel_6.setFont(new Font("Arial", Font.PLAIN, 12));
        
        txtOsTec = new JTextField();
        txtOsTec.setColumns(10);
        
        JLabel lblNewLabel_7 = new JLabel("Valor Total");
        lblNewLabel_7.setFont(new Font("Arial", Font.PLAIN, 12));
        
        txtOsValor = new JTextField();
        txtOsValor.setText("0");
        txtOsValor.setColumns(10);
        
        lblNewLabel_8 = new JLabel("Adicionar");
        lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 14));
        
        lblNewLabel_9 = new JLabel("Pesquisar");
        lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 14));
        
        lblNewLabel_10 = new JLabel("Editar");
        lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 14));
        
        lblNewLabel_11 = new JLabel("Excluir");
        lblNewLabel_11.setFont(new Font("Tahoma", Font.PLAIN, 14));
        
        lblNewLabel_12 = new JLabel("Imprimir");
        lblNewLabel_12.setFont(new Font("Tahoma", Font.PLAIN, 14));

        // ---------------- LAYOUT PRINCIPAL ----------------

        GroupLayout groupLayout = new GroupLayout(getContentPane());
        groupLayout.setHorizontalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addGroup(groupLayout.createSequentialGroup()
        					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        						.addGroup(groupLayout.createSequentialGroup()
        							.addComponent(lblNewLabel_2)
        							.addPreferredGap(ComponentPlacement.RELATED)
        							.addComponent(cboOsSit, 0, 226, Short.MAX_VALUE))
        						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 278, GroupLayout.PREFERRED_SIZE))
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(painelCliente, GroupLayout.DEFAULT_SIZE, 508, Short.MAX_VALUE)
        					.addContainerGap())
        				.addGroup(groupLayout.createSequentialGroup()
        					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
        							.addComponent(lblNewLabel_3)
        							.addComponent(lblNewLabel_4)
        							.addComponent(lblNewLabel_6)
        							.addComponent(lblNewLabel_5))
        						.addGroup(groupLayout.createSequentialGroup()
        							.addGap(26)
        							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        								.addComponent(lblNewLabel_8)
        								.addComponent(btnOsAdicionar))))
        					.addGap(1)
        					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        						.addGroup(groupLayout.createSequentialGroup()
        							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        								.addComponent(txtOsServ, GroupLayout.PREFERRED_SIZE, 539, GroupLayout.PREFERRED_SIZE)
        								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
        									.addComponent(txtOsDef, Alignment.LEADING)
        									.addComponent(txtOsEquip, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 539, Short.MAX_VALUE))
        								.addGroup(groupLayout.createSequentialGroup()
        									.addComponent(txtOsTec, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE)
        									.addGap(98)
        									.addComponent(lblNewLabel_7)
        									.addPreferredGap(ComponentPlacement.UNRELATED)
        									.addComponent(txtOsValor, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE))))
        						.addGroup(groupLayout.createSequentialGroup()
        							.addGap(83)
        							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        								.addComponent(btnOsPesquisar)
        								.addComponent(lblNewLabel_9, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE))
        							.addPreferredGap(ComponentPlacement.RELATED, 103, Short.MAX_VALUE)
        							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
        								.addGroup(groupLayout.createSequentialGroup()
        									.addGap(10)
        									.addComponent(lblNewLabel_10, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        								.addComponent(btnOsAlterar, Alignment.TRAILING))
        							.addGap(99)
        							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        								.addComponent(btnOsExcluir)
        								.addGroup(groupLayout.createSequentialGroup()
        									.addGap(10)
        									.addComponent(lblNewLabel_11, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)))
        							.addGap(61)))
        					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        						.addGroup(groupLayout.createSequentialGroup()
        							.addGap(13)
        							.addComponent(btnOsImprimir))
        						.addGroup(groupLayout.createSequentialGroup()
        							.addGap(40)
        							.addComponent(lblNewLabel_12, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)))
        					.addGap(54))))
        );
        groupLayout.setVerticalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
        				.addGroup(groupLayout.createSequentialGroup()
        					.addGap(20)
        					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(cboOsSit, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        						.addComponent(lblNewLabel_2)))
        				.addGroup(groupLayout.createSequentialGroup()
        					.addContainerGap()
        					.addComponent(painelCliente, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        			.addGap(27)
        			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblNewLabel_3)
        				.addComponent(txtOsEquip, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addGroup(groupLayout.createSequentialGroup()
        					.addGap(26)
        					.addComponent(lblNewLabel_4))
        				.addGroup(groupLayout.createSequentialGroup()
        					.addGap(18)
        					.addComponent(txtOsDef, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)))
        			.addGap(30)
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addComponent(txtOsServ, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
        				.addComponent(lblNewLabel_5))
        			.addGap(29)
        			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblNewLabel_6)
        				.addComponent(txtOsTec, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(lblNewLabel_7)
        				.addComponent(txtOsValor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addGap(58)
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addGroup(groupLayout.createSequentialGroup()
        					.addComponent(btnOsPesquisar)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(lblNewLabel_9, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
        				.addGroup(groupLayout.createSequentialGroup()
        					.addComponent(btnOsAdicionar)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(lblNewLabel_8))
        				.addGroup(groupLayout.createSequentialGroup()
        					.addComponent(btnOsAlterar)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(lblNewLabel_10, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
        				.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
        					.addGroup(groupLayout.createSequentialGroup()
        						.addComponent(btnOsImprimir)
        						.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        						.addComponent(lblNewLabel_12, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
        					.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
        						.addComponent(btnOsExcluir)
        						.addPreferredGap(ComponentPlacement.UNRELATED)
        						.addComponent(lblNewLabel_11, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))))
        			.addGap(36))
        );
        getContentPane().setLayout(groupLayout);
    }
    
    // ------------------------------------------------------------------------------------------------------------------------------------------------// 
    
    private void pesquisar_cliente() {

        if (txtCliPesquisar.getText().trim().isEmpty()) {
            
            tabelaCliente.setModel(new DefaultTableModel(
                    new Object[][] {},
                    new String[] { "Id", "Nome", "Fone" }
            ));

            tabelaCliente.setDefaultEditor(Object.class, null); 
            txtCliId.setText(null);
            return;
        }

        String sql = "select idcli as ID, nomecli as Nome, fonecli as Fone from tbclientes where nomecli like ?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtCliPesquisar.getText() + "%");
            rs = pst.executeQuery();

            tabelaCliente.setModel(DbUtils.resultSetToTableModel(rs));
            tabelaCliente.setDefaultEditor(Object.class, null); // 🔒 bloqueia edição

            tabelaCliente.getColumnModel().getColumn(0).setPreferredWidth(50);   
            tabelaCliente.getColumnModel().getColumn(1).setPreferredWidth(250); 
            tabelaCliente.getColumnModel().getColumn(2).setPreferredWidth(120);
            tabelaCliente.getTableHeader().setResizingAllowed(false);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    
    private void setar_cliente() {

        int setar = tabelaCliente.getSelectedRow();

        if (setar != -1) {

            txtCliId.setText(
                    tabelaCliente.getModel().getValueAt(setar, 0).toString()
            );
        }
    }
    
    private void rbtOrcActionPerformed(java.awt.event.ActionEvent evt) {
        tipo = "Orçamento";
    }

    private void rbtOsActionPerformed(java.awt.event.ActionEvent evt) {
        tipo = "OS";
    }

    // ================= MÉTODO EMITIR OS =================

    private void emitir_os () {

        String sql = "insert into tbos (tipo, situacao, equipamento, defeito, servico, tecnico, valor, idcli) values (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, tipo);
            pst.setString(2, cboOsSit.getSelectedItem().toString());
            pst.setString(3, txtOsEquip.getText());
            pst.setString(4, txtOsDef.getText());
            pst.setString(5, txtOsServ.getText());
            pst.setString(6, txtOsTec.getText());
            pst.setString(7, txtOsValor.getText().replace(",", "."));
            pst.setString(8, txtCliId.getText());

            if ((txtCliId.getText().isEmpty()) ||
                (txtOsEquip.getText().isEmpty()) ||
                (txtOsDef.getText().isEmpty())) {

                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");

            } else {

                int adicionado = pst.executeUpdate();

                if(adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "OS emitida com sucesso");
                    limpar_campos();   
                    btnOsAdicionar.setEnabled(false);
                    btnOsPesquisar.setEnabled(false); // Chat, nesta parte está dando erro
                    btnOsImprimir.setEnabled(true);
                    
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    
    private void pesquisar_os() {

        String num_os = JOptionPane.showInputDialog("Número da OS");

        if (num_os == null || num_os.trim().isEmpty()) {
            return;
        }

        int numero;

        try {
            numero = Integer.parseInt(num_os); 
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "OS Inválida");
            return;
        }

        String sql = "select * from tbos where os = ?";

        try {

            pst = conexao.prepareStatement(sql);
            pst.setInt(1, numero);
            rs = pst.executeQuery();

            if (rs.next()) {

                txtOs.setText(rs.getString(1));

                // Corrigindo a data (coluna 2)
                java.sql.Date dataSql = rs.getDate(2);
                if (dataSql != null) {
                    java.text.SimpleDateFormat formato = new java.text.SimpleDateFormat("dd/MM/yyyy");
                    txtData.setText(formato.format(dataSql));
                }

                String rbtTipo = rs.getString(3);

                if (rbtTipo.equals("OS")) {
                    rbtOs.setSelected(true);
                    tipo = "OS";
                } else {
                    rbtOrc.setSelected(true);
                    tipo = "Orçamento";
                }

                cboOsSit.setSelectedItem(rs.getString(4));
                txtOsEquip.setText(rs.getString(5));
                txtOsDef.setText(rs.getString(6));
                txtOsServ.setText(rs.getString(7));
                txtOsTec.setText(rs.getString(8));
                txtOsValor.setText(rs.getString(9));
                txtCliId.setText(rs.getString(10));

                // Ajustes da interface
                btnOsAdicionar.setEnabled(false);
                btnOsPesquisar.setEnabled(false);
                txtCliPesquisar.setEditable(false);
                tabelaCliente.setEnabled(false);
                
                btnOsAlterar.setEnabled(true);
                btnOsExcluir.setEnabled(true);
                btnOsImprimir.setEnabled(true);

            }
             else {
                JOptionPane.showMessageDialog(null, "OS não cadastrada");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            
        }
    }
    
    private void alterar_os() {

        String sql = "update tbos set tipo=?, situacao=?, equipamento=?, defeito=?, servico=?, tecnico=?, valor=? where os=?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, tipo);
            pst.setString(2, cboOsSit.getSelectedItem().toString());
            pst.setString(3, txtOsEquip.getText());
            pst.setString(4, txtOsDef.getText());
            pst.setString(5, txtOsServ.getText());
            pst.setString(6, txtOsTec.getText());
            pst.setString(7, txtOsValor.getText().replace(",", "."));
            pst.setString(8, txtOs.getText());

            if ((txtCliId.getText().isEmpty()) ||
                (txtOsEquip.getText().isEmpty()) ||
                (txtOsDef.getText().isEmpty())) {

                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");

            } else {

                int adicionado = pst.executeUpdate();

                if(adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "OS Alterada com sucesso");
                    limpar_campos();
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void limpar_campos() {
    	
        txtOs.setText(null);
        txtData.setText(null);
        txtCliId.setText(null);
        txtCliPesquisar.setText(null);

        ((DefaultTableModel) tabelaCliente.getModel()).setRowCount(0);

        txtOsEquip.setText(null);
        txtOsDef.setText(null);
        txtOsServ.setText(null);
        txtOsTec.setText(null);
        txtOsValor.setText(null);
        

        cboOsSit.setSelectedIndex(0); 
        rbtOrc.setSelected(true);     
        tipo = "Orçamento";         

        btnOsAdicionar.setEnabled(true);
        btnOsPesquisar.setEnabled(true);
        txtCliPesquisar.setEditable(true); 
        tabelaCliente.setEnabled(true);
        
        btnOsAlterar.setEnabled(false); 
        btnOsExcluir.setEnabled(false); 
        btnOsImprimir.setEnabled(false); 
    }
    
    private void excluir_os() {

        if (txtOs.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, 
                    "Selecione uma OS para excluir!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);
            txtOs.requestFocus();
            return; 
        }

        Object[] options = {"Sim", "Não"};

        int confirma = JOptionPane.showOptionDialog(
                null,
                "Tem certeza que deseja excluir esta OS?",
                "Atenção",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE,
                null,
                options,
                options[1]
        );

        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "delete from tbos where os=?";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtOs.getText());
                int apagado = pst.executeUpdate();

                if (apagado > 0) {
                    JOptionPane.showMessageDialog(null, "OS excluída com sucesso");
                    limpar_campos();
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }
    
}
    
    
    







