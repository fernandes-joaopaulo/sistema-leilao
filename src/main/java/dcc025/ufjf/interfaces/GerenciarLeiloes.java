package dcc025.ufjf.interfaces;

import dcc025.ufjf.sistema.leilao.Leilao;
import dcc025.ufjf.sistema.leilao.Leiloeiro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Map;

/**
 *
 * @author Joao Paulo 
 * Tela de gerenciamento dos leiloes ativos
 */
public class GerenciarLeiloes extends JFrame {

    private Leiloeiro leiloeiro;

    private DefaultListModel<String> listaLeiloesModel;
    private JList<String> listaLeiloes;

    private JButton botaoVisualizar;
    private JButton botaoEncerrar;
    private JButton botaoVoltar;

    public GerenciarLeiloes(Leiloeiro leiloeiro) {
        this.leiloeiro = leiloeiro;

        setTitle("Gerenciar Leilões");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Lista de leilões
        listaLeiloesModel = new DefaultListModel<>();
        listaLeiloes = new JList<>(listaLeiloesModel);
        JScrollPane scrollLeiloes = new JScrollPane(listaLeiloes);

        carregarLeiloes();

        // Botões (sem lógica)
        botaoVisualizar = new JButton("Visualizar Leilão");
        botaoEncerrar = new JButton("Encerrar Leilão");
        botaoVoltar = new JButton("Voltar ao Menu");

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        painelBotoes.add(botaoVisualizar);
        painelBotoes.add(botaoEncerrar);
        painelBotoes.add(botaoVoltar);

        // Layout principal
        JPanel painelPrincipal = new JPanel(new BorderLayout(20, 20));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        painelPrincipal.add(new JLabel("Leilões Ativos:"), BorderLayout.NORTH);
        painelPrincipal.add(scrollLeiloes, BorderLayout.CENTER);
        painelPrincipal.add(painelBotoes, BorderLayout.SOUTH);

        add(painelPrincipal);

        botaoVisualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int indexSelecionado = listaLeiloes.getSelectedIndex();
                if (indexSelecionado != -1) {
                    String textoSelecionado = listaLeiloesModel.getElementAt(indexSelecionado);
                    int codigoLeilao = Integer.parseInt(textoSelecionado.split(" ")[1]);
                    Leilao leilao = leiloeiro.getLeiloesAtivos().get(codigoLeilao);
                    System.out.println(leilao.getItens());
                    VisualizarLeilao telaVisualizar = new VisualizarLeilao(leilao);
                    telaVisualizar.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione um leilão para encerrar.");
                }
            }
        });

        botaoEncerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int indexSelecionado = listaLeiloes.getSelectedIndex();
                if (indexSelecionado != -1) {
                    String textoSelecionado = listaLeiloesModel.getElementAt(indexSelecionado);
                    int codigoLeilao = Integer.parseInt(textoSelecionado.split(" ")[1]);

                    leiloeiro.encerrarLeilao(codigoLeilao);
                    listaLeiloesModel.remove(indexSelecionado);

                    JOptionPane.showMessageDialog(null, "Leilão encerrado com sucesso.");
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione um leilão para encerrar.");
                }
            }
        });

        botaoVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    // Carregar os leilões ativos no formato solicitado
    private void carregarLeiloes() {
        Map<Integer, Leilao> leiloesAtivos = leiloeiro.getLeiloesAtivos();
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");

        listaLeiloesModel.clear();
        for (Leilao leilao : leiloesAtivos.values()) {
            String dataInicio = formatoData.format(leilao.getInicio());
            String descricao = "Código: " + leilao.getCodigo() + " / Data de início: " + dataInicio;

            listaLeiloesModel.addElement(descricao);
        }
    }
}
