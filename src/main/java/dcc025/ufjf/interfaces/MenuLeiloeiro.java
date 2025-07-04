package dcc025.ufjf.interfaces;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dcc025.ufjf.sistema.leilao.Leiloeiro;

/**
 *
 * @author Joao Paulo 
 * Menu específico para usuário do tipo Leiloeiro
 */

public class MenuLeiloeiro {

    private JFrame frame;
    private JLabel saudacaoLabel;
    private JButton botaoGerenciar;
    private JButton botaoCriar;
    private JButton botaoSair;
    private Leiloeiro leiloeiro;

    public MenuLeiloeiro(Leiloeiro leiloeiro) {
        
        this.leiloeiro = leiloeiro;
        frame = new JFrame();
        frame.setTitle("Menu Leiloeiro");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Centraliza a tela

        // Saudação personalizada
        saudacaoLabel = new JLabel("Olá, " + this.leiloeiro.getNome() + "!");
        saudacaoLabel.setFont(new Font("Arial", Font.BOLD, 26));
        saudacaoLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Botões
        botaoGerenciar = new JButton("Gerenciar Leilões");
        botaoGerenciar.setPreferredSize(new Dimension(200, 60));
        botaoGerenciar.setFont(new Font("Arial", Font.PLAIN, 18));

        botaoCriar = new JButton("Criar Leilão");
        botaoCriar.setPreferredSize(new Dimension(200, 60));
        botaoCriar.setFont(new Font("Arial", Font.PLAIN, 18));

        botaoSair = new JButton("Sair");
        botaoSair.setPreferredSize(new Dimension(150, 50));
        botaoSair.setFont(new Font("Arial", Font.PLAIN, 16));

        // Painel para botões
        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 30)); // Espaçamento entre botões
        painelBotoes.add(botaoGerenciar);
        painelBotoes.add(botaoCriar);

        // Painel para o botão de sair
        JPanel painelSair = new JPanel();
        painelSair.setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 20));
        painelSair.add(botaoSair);

        // Layout principal
        frame.setLayout(new BorderLayout(20, 20));
        frame.add(saudacaoLabel, BorderLayout.NORTH);
        frame.add(painelBotoes, BorderLayout.CENTER);
        frame.add(painelSair, BorderLayout.SOUTH);

        // Ações dos botões
        botaoGerenciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Exibe tela de gerenciamento dos leiloes ativos
                GerenciarLeiloes telaGerenciar = new GerenciarLeiloes(leiloeiro);
                telaGerenciar.setVisible(true);
                getFrame().dispose();
            }
        });

        botaoCriar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Exibe tela de criação do leilão
                CriarLeilao telaCriar = new CriarLeilao(leiloeiro);
                telaCriar.setVisible(true);
                getFrame().dispose();
            }
        });
        
        botaoSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirmacao = JOptionPane.showConfirmDialog(null, "Deseja realmente sair?", "Logout", JOptionPane.YES_NO_OPTION);
                if (confirmacao == JOptionPane.YES_OPTION) {
                    frame.dispose(); // Fecha a janela atual
                    
                    //Redireciona para a tela de login
                    TelaLogin tela = new TelaLogin();
                    tela.getFrame().setVisible(true);
                }
            }
        });
        
    }

    public JFrame getFrame() {
        return this.frame;
    }
}
