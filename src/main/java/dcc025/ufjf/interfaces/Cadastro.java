package dcc025.ufjf.interfaces;

import dcc025.ufjf.auxiliar.Email;
import dcc025.ufjf.exceptions.CPFException;
import dcc025.ufjf.exceptions.EmailException;
import dcc025.ufjf.sistema.leilao.Administrador;
import dcc025.ufjf.sistema.leilao.Leiloeiro;
import dcc025.ufjf.sistema.leilao.Participante;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Joao Paulo 
 * Cria tela de cadastro do usuário
 */
public class Cadastro extends JFrame {

    private JTextField campoNome;
    private JTextField campoCpf;
    private JTextField campoEmail;
    private JPasswordField campoSenha;
    private JComboBox<String> comboTipoUsuario;

    private JButton botaoCadastrar;
    private JButton botaoVoltar;

    public Cadastro() {

        setTitle("Cadastro de Usuário");
        setSize(500, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JLabel titulo = new JLabel("Cadastrar Novo Usuário");
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));

        campoNome = new JTextField();
        campoCpf = new JTextField();
        campoEmail = new JTextField();
        campoSenha = new JPasswordField();

        comboTipoUsuario = new JComboBox<>(new String[]{"Participante", "Leiloeiro"});

        botaoCadastrar = new JButton("Cadastrar");
        botaoVoltar = new JButton("Voltar");

        painelPrincipal.add(titulo);
        painelPrincipal.add(Box.createVerticalStrut(20));
        painelPrincipal.add(new JLabel("Nome:"));
        painelPrincipal.add(campoNome);
        painelPrincipal.add(Box.createVerticalStrut(10));
        painelPrincipal.add(new JLabel("CPF:"));
        painelPrincipal.add(campoCpf);
        painelPrincipal.add(Box.createVerticalStrut(10));
        painelPrincipal.add(new JLabel("Email:"));
        painelPrincipal.add(campoEmail);
        painelPrincipal.add(Box.createVerticalStrut(10));
        painelPrincipal.add(new JLabel("Senha:"));
        painelPrincipal.add(campoSenha);
        painelPrincipal.add(Box.createVerticalStrut(10));
        painelPrincipal.add(new JLabel("Tipo de Usuário:"));
        painelPrincipal.add(comboTipoUsuario);
        painelPrincipal.add(Box.createVerticalStrut(20));
        painelPrincipal.add(botaoCadastrar);
        painelPrincipal.add(Box.createVerticalStrut(10));
        painelPrincipal.add(botaoVoltar);

        add(painelPrincipal);

        botaoCadastrar.addActionListener(e -> cadastrarUsuario());
        botaoVoltar.addActionListener(e -> dispose());
    }

    public void cadastrarUsuario() {

        try {
            String nome = campoNome.getText().trim();
            String cpf = campoCpf.getText().trim();
            String emailStr = campoEmail.getText().trim();
            String senha = new String(campoSenha.getPassword()).trim();
            String tipoUsuario = (String) comboTipoUsuario.getSelectedItem();

            if (nome.isEmpty() || cpf.isEmpty() || emailStr.isEmpty() || senha.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos.");
                return;
            }
            Email email = new Email(emailStr);

            if (tipoUsuario.equals("Participante")) {
                Participante novoUsuario = new Participante(nome, cpf, email, senha);
                novoUsuario.salvarNovo(); //salva usuario em disco
            } else if (tipoUsuario.equals("Leiloeiro")){
                Leiloeiro novoUsuario = new Leiloeiro(nome, cpf, email, senha);
                novoUsuario.salvarNovo();   //salva leiloeiro em disco
            }

            JOptionPane.showMessageDialog(this, "Usuário cadastrado com sucesso!");
            dispose();
        } catch (EmailException | CPFException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }

        // Limpa os campos após cadastro
        campoNome.setText("");
        campoCpf.setText("");
        campoEmail.setText("");
        campoSenha.setText("");
    }
    
}
