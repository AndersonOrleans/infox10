package br.com.infox.telas;

import java.awt.EventQueue;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;

public class TelaPrincipal extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;
    private JPanel desktop;
    private JLabel lblData;
    public static JLabel lblUsuario;

    public static JMenuItem menCadUsu;
    public static JMenu menRel;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
        	
            try {
                TelaPrincipal frame = new TelaPrincipal();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public TelaPrincipal() {

        setResizable(false);
        setTitle("SUL TECH - Tela principal");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setBounds(100, 100, 1208, 782);

        // ------------------- MENU -------------------
        JMenuBar Menu = new JMenuBar();
        setJMenuBar(Menu);

        JMenu menCad = new JMenu("Cadastro");
        Menu.add(menCad);

        JMenuItem menCadCli = new JMenuItem("Cliente");
        menCadCli.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.ALT_DOWN_MASK));
        menCad.add(menCadCli);
        menCadCli.addActionListener(evt -> abrirTelaCliente());
        
        JMenuItem menCadOs = new JMenuItem("OS");
        menCadOs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.ALT_DOWN_MASK));
        menCad.add(menCadOs);
        menCadOs.addActionListener(evt -> menCadOSActionPerformed(evt));
        
        menCadUsu = new JMenuItem("Usuários");
        menCadUsu.setEnabled(false);
        menCadUsu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.ALT_DOWN_MASK));
        menCad.add(menCadUsu);

        menCadUsu.addActionListener(evt -> abrirTelaUsuario());

        menRel = new JMenu("Relatório");
        menRel.setEnabled(false);
        Menu.add(menRel);

        JMenuItem menRelSer = new JMenuItem("Serviços");
        menRelSer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.ALT_DOWN_MASK));
        menRel.add(menRelSer);

        JMenu menAju = new JMenu("Ajuda");
        Menu.add(menAju);

        JMenuItem menAjuSob = new JMenuItem("Sobre");
        menAjuSob.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, InputEvent.ALT_DOWN_MASK));
        menAju.add(menAjuSob);

        menAjuSob.addActionListener(evt -> MenAjuSobActionPerformed(evt));

        JMenu menOpc = new JMenu("Opções");
        Menu.add(menOpc);

        JMenuItem menOpcSai = new JMenuItem("Sair");
        menOpcSai.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_DOWN_MASK));
        menOpc.add(menOpcSai);

        menOpcSai.addActionListener(evt -> MenOpcSaiActionPerformed(evt));

        // ------------------- CONTENT -------------------
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        desktop = new JPanel(); // 🔥 AGORA É O ATRIBUTO
        desktop.setBounds(10, 11, 840, 693);
        desktop.setBackground(new Color(223, 223, 223));
        contentPane.add(desktop);
        desktop.setLayout(null);

        lblData = new JLabel("Data");
        lblData.setFont(new Font("Calibri", Font.BOLD, 20));
        lblData.setBounds(904, 161, 200, 25);
        contentPane.add(lblData);

        lblUsuario = new JLabel("Usuário");
        lblUsuario.setFont(new Font("Calibri", Font.BOLD, 20));
        lblUsuario.setBounds(904, 93, 234, 34);
        contentPane.add(lblUsuario);

        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {

                Object[] opcoes = {"Sim", "Não"};

                int confirmar = JOptionPane.showOptionDialog(
                        null,
                        "Tem certeza que deseja finalizar este programa?",
                        "Encerrar Sistema",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        opcoes,
                        opcoes[1]
                );

                if (confirmar == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }

            @Override
            public void windowActivated(WindowEvent evt) {
                Date data = new Date();
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                lblData.setText(formato.format(data));
            }
        });
    }

   
    private void abrirTelaUsuario() {
        TelaUsuario usuario = new TelaUsuario();
        usuario.setVisible(true);
        desktop.add(usuario);
    }

    private void MenOpcSaiActionPerformed(java.awt.event.ActionEvent evt) {

        Object[] opcoes = { "Sim", "Não" };

        int sair = JOptionPane.showOptionDialog(
                null,
                "Tem certeza que deseja sair?",
                "Atenção",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE,
                null,
                opcoes,
                opcoes[0]);

        if (sair == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    private void MenAjuSobActionPerformed(java.awt.event.ActionEvent evt) {
        TelaSobre sobre = new TelaSobre();
        sobre.setVisible(true);
    }
    
    private void abrirTelaCliente() {
        TelaCliente cliente = new TelaCliente();
        desktop.add(cliente);
        cliente.setVisible(true);
    }
    
    private void menCadOSActionPerformed(java.awt.event.ActionEvent evt) {
        TelaOS os = new TelaOS();
        desktop.add(os);      
        os.setVisible(true);  
    }
    
    
}







