package dcc025.ufjf.interfaces;

import dcc025.ufjf.sistema.leilao.Leilao;
import dcc025.ufjf.sistema.leilao.Leiloeiro;
import dcc025.ufjf.sistema.leilao.Item;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.util.*;

/**
 *
 * @author Joao Paulo 
 * Tela de criação de um leilao por um leiloeiro
 */
public class CriarLeilao extends JFrame {

    private JTextField campoTitulo;
    private JTextField campoDescricao;
    private JTextField campoFotoURL;
    private JTextField campoLanceMinimo;

    private DefaultListModel<String> listaItensModel;
    private JList<String> listaItens;

    private JButton botaoAdicionarItem;
    private JButton botaoCriarLeilao;
    private JButton botaoRemoverItem;
    private JButton botaoEditarItem;
    private JButton botaoSelecionarImagem;

    private Set<Item> itens;

    private Leiloeiro leiloeiro;

    private Item itemSelecionado = null;

    public CriarLeilao(Leiloeiro leiloeiro) {
        this.leiloeiro = leiloeiro;
        this.itens = new HashSet<>();

        setTitle("Criar Leilão");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Componentes da tela
        JLabel labelTitulo = new JLabel("Título do Item:");
        campoTitulo = new JTextField(20);

        JLabel labelDescricao = new JLabel("Descrição do Item:");
        campoDescricao = new JTextField(20);

        JLabel labelFotoURL = new JLabel("URL da Foto:");
        campoFotoURL = new JTextField(20);

        JLabel labelLanceMinimo = new JLabel("Lance Mínimo:");
        campoLanceMinimo = new JTextField(10);

        botaoSelecionarImagem = new JButton("Selecionar Imagem");
        botaoAdicionarItem = new JButton("Adicionar Item");
        botaoRemoverItem = new JButton("Remover Item");
        botaoEditarItem = new JButton("Editar Item");
        botaoCriarLeilao = new JButton("Criar Leilão");

        listaItensModel = new DefaultListModel<>();
        listaItens = new JList<>(listaItensModel);
        JScrollPane scrollItens = new JScrollPane(listaItens);

        // Painel para entrada dos itens
        JPanel painelItens = new JPanel(new GridLayout(6, 2, 10, 10));
        painelItens.add(labelTitulo);
        painelItens.add(campoTitulo);
        painelItens.add(labelDescricao);
        painelItens.add(campoDescricao);
        painelItens.add(new JLabel("Imagem do item:"));
        painelItens.add(botaoSelecionarImagem);
        painelItens.add(new JLabel("Caminho da imagem:"));
        painelItens.add(campoFotoURL);
        painelItens.add(labelLanceMinimo);
        painelItens.add(campoLanceMinimo);
        painelItens.add(new JLabel()); // Espaço vazio
        painelItens.add(botaoAdicionarItem);

        // Painel para botões de manipulação da lista
        JPanel painelLista = new JPanel(new BorderLayout(10, 10));
        painelLista.add(new JLabel("Itens Adicionados:"), BorderLayout.NORTH);
        painelLista.add(scrollItens, BorderLayout.CENTER);

        JPanel painelBotoesLista = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        painelBotoesLista.add(botaoRemoverItem);
        painelBotoesLista.add(botaoEditarItem);

        painelLista.add(painelBotoesLista, BorderLayout.SOUTH);

        // Layout principal
        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BorderLayout(20, 20));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        painelPrincipal.add(painelItens, BorderLayout.NORTH);
        painelPrincipal.add(painelLista, BorderLayout.CENTER);
        painelPrincipal.add(botaoCriarLeilao, BorderLayout.SOUTH);

        add(painelPrincipal);

        // Evento de selecionar imagem
        botaoSelecionarImagem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                //Restringe as extensoes do arquivo
                FileNameExtensionFilter filtro = new FileNameExtensionFilter("Imagens", "jpg", "jpeg", "png", "gif");
                fileChooser.setFileFilter(filtro);

                int resultado = fileChooser.showOpenDialog(null);

                if (resultado == JFileChooser.APPROVE_OPTION) {
                    File arquivoSelecionado = fileChooser.getSelectedFile();
                    String caminhoImagem = arquivoSelecionado.getAbsolutePath();
                    campoFotoURL.setText(caminhoImagem);
                }
            }
        });

        // Evento de adicionar item
        botaoAdicionarItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String titulo = campoTitulo.getText().trim();
                String descricao = campoDescricao.getText().trim();
                String fotoURL = campoFotoURL.getText().trim();
                String lanceMinimoTexto = campoLanceMinimo.getText().trim();

                if (titulo.isEmpty() || descricao.isEmpty() || fotoURL.isEmpty() || lanceMinimoTexto.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Preencha todos os campos do item.");
                    return;
                }

                try {
                    double lanceMinimo = Double.parseDouble(lanceMinimoTexto);

                    if (itemSelecionado == null) {
                        // Novo item
                        Item item = new Item(titulo, descricao, fotoURL, lanceMinimo);
                        itens.add(item);
                        listaItensModel.addElement(titulo);
                    } else {
                        // Editar item existente
                        itens.remove(itemSelecionado);
                        Item novoItem = new Item(titulo, descricao, fotoURL, lanceMinimo);
                        itens.add(novoItem);

                        int index = listaItens.getSelectedIndex();
                        listaItensModel.set(index, titulo);

                        itemSelecionado = null; // Limpar seleção
                    }

                    // Limpar campos
                    campoTitulo.setText("");
                    campoDescricao.setText("");
                    campoFotoURL.setText("");
                    campoLanceMinimo.setText("");
                    listaItens.clearSelection();

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Lance mínimo inválido.");
                }
            }
        });

        // Evento de remover item
        botaoRemoverItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int indexSelecionado = listaItens.getSelectedIndex();
                if (indexSelecionado != -1) {
                    String tituloSelecionado = listaItens.getSelectedValue();

                    // Encontrar e remover o item
                    Item itemParaRemover = null;
                    for (Item item : itens) {
                        if (item.getTitulo().equals(tituloSelecionado)) {
                            itemParaRemover = item;
                            break;
                        }
                    }

                    if (itemParaRemover != null) {
                        itens.remove(itemParaRemover);
                        listaItensModel.remove(indexSelecionado);
                        JOptionPane.showMessageDialog(null, "Item removido com sucesso.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione um item para remover.");
                }
            }
        });

        // Evento de editar item
        botaoEditarItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int indexSelecionado = listaItens.getSelectedIndex();
                if (indexSelecionado != -1) {
                    String tituloSelecionado = listaItens.getSelectedValue();

                    for (Item item : itens) {
                        if (item.getTitulo().equals(tituloSelecionado)) {
                            // Carregar dados do item para os campos
                            campoTitulo.setText(item.getTitulo());
                            campoDescricao.setText(item.getDescricao());
                            campoFotoURL.setText(item.getFoto_URL());
                            campoLanceMinimo.setText(String.valueOf(item.getLanceMinimo()));
                            itemSelecionado = item;
                            break;
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione um item para editar.");
                }
            }
        });

        // Evento de criar leilão
        botaoCriarLeilao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (itens.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Adicione pelo menos um item.");
                    return;
                }
                Leilao leilao = new Leilao(leiloeiro, itens);
                leiloeiro.adicionarLeilao(leilao.getCodigo(), leilao);
                JOptionPane.showMessageDialog(null, "Leilão criado com sucesso! Código: " + leilao.getCodigo());
                dispose();
            }
        });
    }
}
