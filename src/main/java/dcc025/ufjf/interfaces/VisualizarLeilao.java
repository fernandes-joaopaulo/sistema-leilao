package dcc025.ufjf.interfaces;

import dcc025.ufjf.sistema.leilao.Item;
import dcc025.ufjf.sistema.leilao.Leilao;
import dcc025.ufjf.sistema.leilao.Usuario;
import dcc025.ufjf.sistema.leilao.Participante;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;

/**
 *
 * @author Joao Paulo 
 * Tela de visualização de um leilão
 */
public class VisualizarLeilao extends JFrame {

    private Leilao leilao;

    public VisualizarLeilao(Leilao leilao, Usuario usuario) {
        this.leilao = leilao;

        setTitle("Detalhes do Leilão");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painelPrincipal = new JPanel(new BorderLayout(20, 20));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Painel superior com informações básicas
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        JLabel labelCodigo = new JLabel("Código do Leilão: " + leilao.getCodigo());
        JLabel labelDataInicio = new JLabel("Data de Início: " + formatoData.format(leilao.getInicio()));

        JPanel painelSuperior = new JPanel();
        painelSuperior.setLayout(new BoxLayout(painelSuperior, BoxLayout.Y_AXIS));
        painelSuperior.add(labelCodigo);
        painelSuperior.add(Box.createVerticalStrut(10));
        painelSuperior.add(labelDataInicio);

        painelPrincipal.add(painelSuperior, BorderLayout.NORTH);

        // Painel central com os itens
        JPanel painelItens = new JPanel();
        painelItens.setLayout(new BoxLayout(painelItens, BoxLayout.Y_AXIS));

        for (Item item : leilao.getItens()) {
            JPanel painelItem = new JPanel(new BorderLayout(10, 10));
            painelItem.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            painelItem.setMaximumSize(new Dimension(700, 200));

            // Exibir imagem
            JLabel labelImagem = new JLabel();
            if (item.getFoto_URL() != null && !item.getFoto_URL().isEmpty()) {
                ImageIcon imagem = new ImageIcon(item.getFoto_URL());
                Image imagemRedimensionada = imagem.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
                labelImagem.setIcon(new ImageIcon(imagemRedimensionada));
            }

            // Descrição do item
            String descricaoItem = "<html><b>Título:</b> " + item.getTitulo()
                    + "<br><b>Descrição:</b> " + item.getDescricao()
                    + "<br><b>Lance Mínimo:</b> R$ " + item.getLanceMinimo()
                    + "<br><b>Maior Lance:</b> " + item.getMaiorLance() + "</html>";
            JLabel labelDescricao = new JLabel(descricaoItem);

            JPanel painelDescricaoEBotao = new JPanel(new BorderLayout(10, 10));
            painelDescricaoEBotao.add(labelDescricao, BorderLayout.CENTER);
            
            if(usuario instanceof Participante){
                // Cria botão de lance
                JButton botaoLance = criaBotaoLance(item);
                painelDescricaoEBotao.add(botaoLance, BorderLayout.EAST);
            }

            painelItem.add(labelImagem, BorderLayout.WEST);
            painelItem.add(painelDescricaoEBotao, BorderLayout.CENTER);

            painelItens.add(painelItem);
            painelItens.add(Box.createVerticalStrut(10));
        }

        JScrollPane scrollItens = new JScrollPane(painelItens);
        painelPrincipal.add(scrollItens, BorderLayout.CENTER);

        // Botão de fechar
        JButton botaoFechar = new JButton("Fechar");
        botaoFechar.addActionListener(e -> dispose());

        JPanel painelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelInferior.add(botaoFechar);

        painelPrincipal.add(painelInferior, BorderLayout.SOUTH);

        add(painelPrincipal);
    }
    
    /**
     * Cria o botão de dar lance para um item específico.
     */
    private JButton criaBotaoLance(Item item) {
        JButton botaoLance = new JButton("Dar Lance");

        botaoLance.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Função de dar lance ainda não implementada para: " + item.getTitulo());
            // Aqui você pode abrir uma nova tela para digitar o valor do lance
        });

        return botaoLance;
    }
}