package br.com.infox.telas;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;

public class TelaSobre extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                TelaSobre frame = new TelaSobre();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public TelaSobre() {

        setTitle("SUL TECH - Sobre");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // não fecha o sistema
        setSize(570, 250); // largura e altura
        setLocationRelativeTo(null); // centraliza na tela
        setResizable(false); // impede redimensionar

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        // Título principal
        JLabel lblTitulo = new JLabel("SUL TECH – Sistema de Ordem de Serviço");
        lblTitulo.setForeground(new Color(0, 64, 0));
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setBounds(40, 20, 400, 25);
        contentPane.add(lblTitulo);

        // Descrição
        JLabel lblDescricao = new JLabel("Sistema para controle de Ordem de Serviços");
        lblDescricao.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblDescricao.setHorizontalAlignment(SwingConstants.CENTER);
        lblDescricao.setBounds(-19, 56, 459, 20);
        contentPane.add(lblDescricao);

        // Desenvolvedor
        JLabel lblAutor = new JLabel("Desenvolvido por Anderson Orleans, Gabriely Clemente e Guilherme Feijor");
        lblAutor.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblAutor.setHorizontalAlignment(SwingConstants.CENTER);
        lblAutor.setBounds(66, 76, 465, 20);
        contentPane.add(lblAutor);

        // Rodapé
        JLabel lblAno = new JLabel("Projeto acadêmico - 2026");
        lblAno.setFont(new Font("Tahoma", Font.ITALIC, 12));
        lblAno.setHorizontalAlignment(SwingConstants.CENTER);
        lblAno.setBounds(66, 100, 156, 20);
        contentPane.add(lblAno);
    }
}
