package dcc025.ufjf.interfaces;

import dcc025.ufjf.sistema.leilao.Administrador;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Joao Paulo
 * Tela de menu do administrador
 */
public class MenuAdministrador extends JFrame {

    private JButton botaoCadastrarUsuario;
    private JButton botaoSair;

    public MenuAdministrador() {

        setTitle("Menu - Administrador");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JLabel saudacao = new JLabel("Olá, administrador!");
        saudacao.setAlignmentX(Component.CENTER_ALIGNMENT);
        saudacao.setFont(new Font("Arial", Font.BOLD, 20));

        botaoCadastrarUsuario = new JButton("Cadastrar Usuário");
        botaoCadastrarUsuario.setMaximumSize(new Dimension(250, 40));
        botaoCadastrarUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);

        botaoSair = new JButton("Sair");
        botaoSair.setMaximumSize(new Dimension(250, 40));
        botaoSair.setAlignmentX(Component.CENTER_ALIGNMENT);

        painelPrincipal.add(saudacao);
        painelPrincipal.add(Box.createVerticalStrut(40));
        painelPrincipal.add(botaoCadastrarUsuario);
        painelPrincipal.add(Box.createVerticalStrut(20));
        painelPrincipal.add(botaoSair);

        add(painelPrincipal);

        botaoCadastrarUsuario.addActionListener(e -> {
            Cadastro tela = new Cadastro();
            tela.setVisible(true);
        });
        
        // Lógica para sair
        botaoSair.addActionListener(e -> {
            dispose();
            // Redireciona para a tela de login
            TelaLogin tela = new TelaLogin();
            tela.getFrame().setVisible(true);
        });
    }

}