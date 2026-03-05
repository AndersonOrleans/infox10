package br.com.infox.telas;

import java.sql.*;
import br.com.infox.dal.ModuloConexao;

import java.awt.EventQueue;
import java.awt.Cursor;

import javax.swing.JInternalFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class TelaUsuario extends JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    private static final long serialVersionUID = 1L;
    private JTextField textUsuId;
    private JTextField textUsoLogin;
    private JTextField textUsoFone;
    private JTextField textUsuNome;
    private JComboBox<String> cboUsoPerfil;
    private JTextField txtUsoSenha;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TelaUsuario frame = new TelaUsuario();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public TelaUsuario() {

        conexao = ModuloConexao.conector();

        getContentPane().setForeground(new Color(0, 0, 0));

        JLabel lblNewLabel = new JLabel("* Id:");
        lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 15));

        JLabel lblNewLabel_1 = new JLabel("* Nome:");
        lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 15));

        JLabel lblNewLabel_2 = new JLabel("* Login");
        lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 15));

        JLabel lblNewLabel_3 = new JLabel("* Senha:");
        lblNewLabel_3.setFont(new Font("Arial", Font.PLAIN, 15));

        JLabel lblNewLabel_4 = new JLabel("* Perfil");
        lblNewLabel_4.setFont(new Font("Arial", Font.PLAIN, 15));

        textUsuId = new JTextField();
        textUsuId.setColumns(10);

        // =========================
        // Adiciona DocumentListener para limpar campos ao apagar ID
        textUsuId.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                checarIdVazio();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                checarIdVazio();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                checarIdVazio();
            }

            private void checarIdVazio() {
                if (textUsuId.getText().isEmpty()) {
                    textUsuNome.setText(null);
                    textUsoFone.setText(null);
                    textUsoLogin.setText(null);
                    txtUsoSenha.setText(null);
                    cboUsoPerfil.setSelectedIndex(0); // reseta o combo
                }
            }
        });
        // =========================

        textUsoLogin = new JTextField();
        textUsoLogin.setColumns(10);

        cboUsoPerfil = new JComboBox<>();
        cboUsoPerfil.setFont(new Font("Tahoma", Font.PLAIN, 13));
        cboUsoPerfil.setForeground(new Color(0, 0, 0));
        cboUsoPerfil.setModel(new DefaultComboBoxModel<>(new String[]{"admin", "user"}));

        JLabel lblNewLabel_5 = new JLabel("Fone:");
        lblNewLabel_5.setFont(new Font("Arial", Font.PLAIN, 14));

        textUsoFone = new JTextField();
        textUsoFone.setColumns(10);

        textUsuNome = new JTextField();
        textUsuNome.setColumns(10);

        JButton btnUsuCreate = new JButton("");
        btnUsuCreate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                adicionar();
            }
        });
        btnUsuCreate.setToolTipText("Adicionar");
        btnUsuCreate.setIcon(new ImageIcon("C:\\Users\\ander\\OneDrive\\Área de Trabalho\\Projeto infox\\Imgens\\create.png"));
        btnUsuCreate.setFont(new Font("Tahoma", Font.PLAIN, 15));

        JButton btnUsuRead = new JButton("");
        btnUsuRead.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                consultar();
            }
        });
        btnUsuRead.setToolTipText("Consultar");
        btnUsuRead.setIcon(new ImageIcon("C:\\Users\\ander\\OneDrive\\Área de Trabalho\\Projeto infox\\Imgens\\read.png"));

        JButton btnUsuUpdate = new JButton("");
        btnUsuUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                alterar();
            }
        });
        btnUsuUpdate.setToolTipText("Alterar");
        btnUsuUpdate.setIcon(new ImageIcon("C:\\Users\\ander\\OneDrive\\Área de Trabalho\\Projeto infox\\Imgens\\update.png"));

        JButton btnUsuDelete = new JButton("");
        btnUsuDelete.setToolTipText("Remover");
        btnUsuDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                remover();
            }
        });
        btnUsuDelete.setIcon(new ImageIcon("C:\\Users\\ander\\OneDrive\\Área de Trabalho\\Projeto infox\\Imgens\\delete.png"));

        txtUsoSenha = new JTextField();
        txtUsoSenha.setColumns(10);

        JLabel lblNewLabel_6 = new JLabel("* Campos obrigatórios");
        lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNewLabel_6.setForeground(new Color(255, 0, 0));

        JLabel lblNewLabel_7 = new JLabel("Adicionar");
        lblNewLabel_7.setFont(new Font("Arial", Font.PLAIN, 13));

        JLabel lblNewLabel_8 = new JLabel("Pesquisar");
        lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 13));

        JLabel lblNewLabel_9 = new JLabel("Alterar");
        lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 13));

        JLabel lblNewLabel_10 = new JLabel("Deletar");
        lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 13));

        GroupLayout groupLayout = new GroupLayout(getContentPane());
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addGap(33)
                                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
                                                                .addComponent(lblNewLabel_5)
                                                                .addComponent(lblNewLabel_1)
                                                                .addComponent(lblNewLabel)))
                                                .addGap(18)
                                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
                                                        .addComponent(textUsuId, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                                .addGroup(groupLayout.createSequentialGroup()
                                                                        .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
                                                                                .addComponent(txtUsoSenha, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                                                                                .addComponent(textUsoFone, GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE))
                                                                        .addGap(18)
                                                                        .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
                                                                                .addComponent(lblNewLabel_2)
                                                                                .addComponent(lblNewLabel_4))
                                                                        .addPreferredGap(ComponentPlacement.RELATED)
                                                                        .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                                                .addComponent(cboUsoPerfil, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(textUsoLogin, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)))
                                                                .addComponent(textUsuNome, GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE))))
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addGap(60)
                                                .addComponent(btnUsuCreate)
                                                .addGap(54)
                                                .addComponent(btnUsuRead)
                                                .addGap(56)
                                                .addComponent(btnUsuUpdate)
                                                .addGap(59)
                                                .addComponent(btnUsuDelete))
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addGap(21)
                                                .addComponent(lblNewLabel_6))
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addGap(81)
                                                .addComponent(lblNewLabel_7)
                                                .addGap(105)
                                                .addComponent(lblNewLabel_8)
                                                .addGap(103)
                                                .addComponent(lblNewLabel_9)
                                                .addGap(116)
                                                .addComponent(lblNewLabel_10)))
                                .addContainerGap(173, Short.MAX_VALUE))
        );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGap(21)
                                .addComponent(lblNewLabel_6)
                                .addGap(27)
                                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(textUsuId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblNewLabel))
                                .addGap(32)
                                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblNewLabel_1)
                                        .addComponent(textUsuNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(41)
                                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(textUsoLogin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(textUsoFone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblNewLabel_2)
                                        .addComponent(lblNewLabel_5))
                                .addGap(41)
                                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblNewLabel_3)
                                        .addComponent(txtUsoSenha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblNewLabel_4)
                                        .addComponent(cboUsoPerfil, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(120)
                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                        .addComponent(btnUsuRead)
                                        .addComponent(btnUsuCreate)
                                        .addComponent(btnUsuDelete)
                                        .addComponent(btnUsuUpdate))
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                        .addComponent(lblNewLabel_7)
                                        .addComponent(lblNewLabel_8)
                                        .addComponent(lblNewLabel_9)
                                        .addComponent(lblNewLabel_10))
                                .addGap(147))
        );

        getContentPane().setLayout(groupLayout);
        setClosable(true);
        setTitle("SUL TECH - Usuários");
        setBounds(10, 11, 806, 657);

        btnUsuCreate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnUsuRead.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnUsuUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnUsuDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

    }

    private void consultar() {
        if (textUsuId.getText().isEmpty()) {
            textUsuNome.setText(null);
            textUsoFone.setText(null);
            textUsoLogin.setText(null);
            txtUsoSenha.setText(null);
            cboUsoPerfil.setSelectedIndex(0);
            return;
        }

        String sql = "select * from tbusuarios where iduser=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, textUsuId.getText());
            rs = pst.executeQuery();
            if (rs.next()) {
                textUsuNome.setText(rs.getString(2));
                textUsoFone.setText(rs.getString(3));
                textUsoLogin.setText(rs.getString(4));
                txtUsoSenha.setText(rs.getString(5));
                cboUsoPerfil.setSelectedItem(rs.getString(6));
            } else {
                JOptionPane.showMessageDialog(null, "Usuário não cadastrado");
                textUsuNome.setText(null);
                textUsoFone.setText(null);
                textUsoLogin.setText(null);
                txtUsoSenha.setText(null);
                cboUsoPerfil.setSelectedIndex(0);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void adicionar() {
        String sql = "insert into tbusuarios (iduser, usuario, fone, login, senha, perfil) values (?, ?, ?, ?, ?, ?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, textUsuId.getText());
            pst.setString(2, textUsuNome.getText());
            pst.setString(3, textUsoFone.getText());
            pst.setString(4, textUsoLogin.getText());
            pst.setString(5, txtUsoSenha.getText());
            pst.setString(6, cboUsoPerfil.getSelectedItem().toString());

            if (textUsuId.getText().isEmpty() ||
                    textUsuNome.getText().isEmpty() ||
                    textUsoLogin.getText().isEmpty() ||
                    txtUsoSenha.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios!");
            } else {

                int adicionado = pst.executeUpdate();

                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso");
                    textUsuId.setText(null);
                    textUsuNome.setText(null);
                    textUsoFone.setText(null);
                    textUsoLogin.setText(null);
                    txtUsoSenha.setText(null);
                }

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void alterar() {
        String sql = "update tbusuarios set usuario=?, fone=?, login=?, senha=?, perfil=? where iduser=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, textUsuNome.getText());
            pst.setString(2, textUsoFone.getText());
            pst.setString(3, textUsoLogin.getText());
            pst.setString(4, txtUsoSenha.getText());
            pst.setString(5, cboUsoPerfil.getSelectedItem().toString());
            pst.setString(6, textUsuId.getText());

            if (textUsuId.getText().isEmpty() ||
                    textUsuNome.getText().isEmpty() ||
                    textUsoLogin.getText().isEmpty() ||
                    txtUsoSenha.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios!");
            } else {

                int adicionado = pst.executeUpdate();

                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Dados do usuário alterados com sucesso");
                    textUsuId.setText(null);
                    textUsuNome.setText(null);
                    textUsoFone.setText(null);
                    textUsoLogin.setText(null);
                    txtUsoSenha.setText(null);
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
                "Tem certeza que deseja remover este usuário?",
                "Atenção",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcoes,
                opcoes[1] // padrão = NÃO
        );

        if (confirma == 0) {

            String SQL = "delete from tbusuarios where iduser = ?";

            try {
                pst = conexao.prepareStatement(SQL);
                pst.setString(1, textUsuId.getText());

                int apagado = pst.executeUpdate();

                if (apagado > 0) {
                    JOptionPane.showMessageDialog(null, "Usuário removido com sucesso");

                    textUsuId.setText(null);
                    textUsuNome.setText(null);
                    textUsoFone.setText(null);
                    textUsoLogin.setText(null);
                    txtUsoSenha.setText(null);
                    cboUsoPerfil.setSelectedIndex(0);
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

}