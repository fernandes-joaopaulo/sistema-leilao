package dcc025.ufjf.interfaces;

import dcc025.ufjf.sistema.leilao.Participante;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Joao Paulo 
 * Tela de menu dos participantes de leiloes
 */
public class MenuParticipante extends JFrame {

    private Participante participante;

    private JButton botaoVisualizarLeiloes;
    private JButton botaoLogout;

    public MenuParticipante(Participante participante) {
        this.participante = participante;

        setTitle("Menu - Participante");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JLabel saudacao = new JLabel("Olá, " + participante.getNome() + "!");
        saudacao.setAlignmentX(Component.CENTER_ALIGNMENT);
        saudacao.setFont(new Font("Arial", Font.BOLD, 20));

        botaoVisualizarLeiloes = new JButton("Visualizar Leilões Ativos");
        botaoVisualizarLeiloes.setMaximumSize(new Dimension(250, 40));
        botaoVisualizarLeiloes.setAlignmentX(Component.CENTER_ALIGNMENT);

        botaoLogout = new JButton("Sair");
        botaoLogout.setMaximumSize(new Dimension(250, 40));
        botaoLogout.setAlignmentX(Component.CENTER_ALIGNMENT);

        painelPrincipal.add(saudacao);
        painelPrincipal.add(Box.createVerticalStrut(40));
        painelPrincipal.add(botaoVisualizarLeiloes);
        painelPrincipal.add(Box.createVerticalStrut(20));
        painelPrincipal.add(botaoLogout);

        add(painelPrincipal);
        
        // Ações dos botões
        botaoVisualizarLeiloes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Exibe tela de gerenciamento dos leiloes ativos
                GerenciarLeiloes telaGerenciar = new GerenciarLeiloes(participante);
                telaGerenciar.setVisible(true);
            }
        });
        
        // Aqui você pode adicionar os ActionListeners para cada botão conforme as telas forem criadas.
        botaoLogout.addActionListener(e -> {
            dispose();
            //Redirecionar para a tela de login
            TelaLogin tela = new TelaLogin();
            tela.getFrame().setVisible(true);
        });
    }
}
