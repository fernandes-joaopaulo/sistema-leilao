package dcc025.ufjf.interfaces;

import dcc025.ufjf.persistence.LeilaoPersistence;
import dcc025.ufjf.sistema.leilao.Leilao;
import dcc025.ufjf.sistema.leilao.Leiloeiro;
import dcc025.ufjf.sistema.leilao.Participante;
import dcc025.ufjf.sistema.leilao.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Joao Paulo Tela de gerenciamento dos leiloes ativos
 */
public class GerenciarLeiloes extends JFrame {

    private Usuario usuario;
    private List<Leilao> leiloesAtivos;

    private DefaultListModel<String> listaLeiloesModel;
    private JList<String> listaLeiloes;

    public GerenciarLeiloes(Usuario usuario) {
        this.usuario = usuario;
        this.leiloesAtivos = new ArrayList<>();

        setTitle("Gerenciar Leilões");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Lista de leilões
        listaLeiloesModel = new DefaultListModel<>();
        listaLeiloes = new JList<>(listaLeiloesModel);
        JScrollPane scrollLeiloes = new JScrollPane(listaLeiloes);

        // Botões
        JButton botaoVisualizar = new JButton("Visualizar Leilão");
        JButton botaoEncerrar = new JButton("Encerrar Leilão");
        JButton botaoVoltar = new JButton("Voltar ao Menu");
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        painelBotoes.add(botaoVisualizar);
        if (usuario instanceof Leiloeiro) {
            painelBotoes.add(botaoEncerrar);
        }
        painelBotoes.add(botaoVoltar);

        // Layout principal
        JPanel painelPrincipal = new JPanel(new BorderLayout(20, 20));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        painelPrincipal.add(new JLabel("Leilões Ativos:"), BorderLayout.NORTH);
        painelPrincipal.add(scrollLeiloes, BorderLayout.CENTER);
        painelPrincipal.add(painelBotoes, BorderLayout.SOUTH);

        add(painelPrincipal);
        carregaLista();

        //Eventos
        botaoVisualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int indexSelecionado = listaLeiloes.getSelectedIndex();
                if (indexSelecionado != -1) {
                    String textoSelecionado = listaLeiloesModel.getElementAt(indexSelecionado);
                    int codigoLeilao = Integer.parseInt(textoSelecionado.split(" ")[1]);
                    carregaLeilao(codigoLeilao);
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione um leilão para encerrar.");
                }
            }
        });

        botaoEncerrar.addActionListener((ActionEvent e) -> {
            if (usuario instanceof Leiloeiro) {
                encerrarLeilao((Leiloeiro) usuario);
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
    private void carregaLista() {
        if (usuario instanceof Leiloeiro) {
            Leiloeiro leiloeiro = (Leiloeiro) usuario;
            this.leiloesAtivos = leiloeiro.carregaLeiloesAtivos();
        } else if (usuario instanceof Participante) {
            LeilaoPersistence lp = new LeilaoPersistence();
            List<Leilao> todosLeiloes = lp.findAll();

            for (Leilao leilao : todosLeiloes) {
                if (leilao.isAtivo()) {
                    leiloesAtivos.add(leilao);
                }
            }
        }

        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");

        listaLeiloesModel.clear();
        if(!leiloesAtivos.isEmpty())
            for (Leilao leilao : leiloesAtivos) {
                String dataInicio = formatoData.format(leilao.getInicio());
                String descricao = "Código: " + leilao.getCodigo() + " / Data de início: " + dataInicio;

                listaLeiloesModel.addElement(descricao);
            }
    }

    public void carregaLeilao(int codigoLeilao) {
        Leilao leilao;
        for (Leilao l : leiloesAtivos) {
            if (l.getCodigo() == codigoLeilao) {
                leilao = l;
                VisualizarLeilao telaVisualizar = new VisualizarLeilao(leilao, usuario);
                telaVisualizar.setVisible(true);
            }
        }
    }

    private void encerrarLeilao(Leiloeiro leiloeiro) {
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
}
