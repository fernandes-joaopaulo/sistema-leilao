package dcc025.ufjf.interfaces;

import dcc025.ufjf.sistema.leilao.Item;
import dcc025.ufjf.sistema.leilao.Lance;
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
    private Usuario usuario;

    private JPanel painelPrincipal;
    private JPanel painelItens;
    private JPanel painelSuperior;

    public VisualizarLeilao(Leilao leilao, Usuario usuario) {
        this.leilao = leilao;
        this.usuario = usuario;

        setTitle("Detalhes do Leilão");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        painelPrincipal = new JPanel(new BorderLayout(20, 20));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Painel superior com informações básicas
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        JLabel labelCodigo = new JLabel("Código do Leilão: " + leilao.getCodigo());
        JLabel labelDataInicio = new JLabel("Data de Início: " + formatoData.format(leilao.getInicio()));

        painelSuperior = new JPanel();
        painelSuperior.setLayout(new BoxLayout(painelSuperior, BoxLayout.Y_AXIS));
        painelSuperior.add(labelCodigo);
        painelSuperior.add(Box.createVerticalStrut(10));
        painelSuperior.add(labelDataInicio);

        painelPrincipal.add(painelSuperior, BorderLayout.NORTH);

        carregaLista();
    }

    public void carregaLista() {

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

            if (usuario instanceof Participante) {
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
            double valorLance;
            do {
                String valorLanceStr = JOptionPane.showInputDialog("Digite o valor do lance: ");
                valorLance = Double.parseDouble(valorLanceStr);
                if (valorLance < item.getLanceMinimo()) {
                    JOptionPane.showMessageDialog(null, "Lance deve ser maior ou igual ao lance mínimo!");
                }
            } while (valorLance < item.getLanceMinimo());

            Participante p = (Participante) usuario;
            Lance lance = new Lance(valorLance, p);
            for (Item it : leilao.getItens()) {
                if (it.equals(item)) {
                    it.adicionarLance(lance);
                }
            }
            leilao.salvarExistente();

            dispose();
            VisualizarLeilao tela = new VisualizarLeilao(leilao, usuario);  //atualiza a tela
            tela.setVisible(true);
        });

        return botaoLance;
    }

}
