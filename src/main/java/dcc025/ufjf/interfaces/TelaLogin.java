package dcc025.ufjf.interfaces;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import dcc025.ufjf.auxiliar.Email;
import dcc025.ufjf.exceptions.EmailException;
import dcc025.ufjf.persistence.LeiloeiroPersistence;
import dcc025.ufjf.persistence.ParticipantePersistence;
import dcc025.ufjf.sistema.leilao.Leiloeiro;
import dcc025.ufjf.sistema.leilao.Participante;

/**
 *
 * @author Joao Paulo 
 * Cria tela de login do usuário
 */
public class TelaLogin {

    private final JFrame frame;
    private final JTextField campoEmail;
    private final JPasswordField campoSenha;
    private final JButton botaoLogin;

    public TelaLogin() {
        frame = new JFrame();
        frame.setTitle("Login");
        frame.setSize(350, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Centraliza a frame

        // Criação dos componentes
        JLabel labelEmail = new JLabel("Email:");
        JLabel labelSenha = new JLabel("Senha:");

        campoEmail = new JTextField(20);
        campoSenha = new JPasswordField(20);
        botaoLogin = new JButton("Login");

        // Painel para organizar os componentes
        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(3, 2, 10, 10)); // 3 linhas, 2 colunas, espaçamento

        painel.add(labelEmail);
        painel.add(campoEmail);
        painel.add(labelSenha);
        painel.add(campoSenha);
        painel.add(new JLabel()); // Espaço vazio
        painel.add(botaoLogin);

        frame.add(painel);

        // Evento de clique no botão
        botaoLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Email email = new Email(campoEmail.getText());
                    String senha = new String(campoSenha.getPassword());
                    
                    logar(email, senha);
                }
                catch(EmailException ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
    }
    
    public JFrame getFrame(){
        return this.frame;
    }
    
    public void logar(Email email, String senha){
       
            LeiloeiroPersistence lp = new LeiloeiroPersistence();
            List<Leiloeiro> leiloeiros = lp.findAll();
            
            ParticipantePersistence pp = new ParticipantePersistence();
            List<Participante> participantes = pp.findAll();
            
            boolean logou = false;
            for(Leiloeiro l : leiloeiros){
                if(l.getEmail().getEmail().equals(email.getEmail()) && l.getSenha().equals(senha)){
                    MenuLeiloeiro menu = new MenuLeiloeiro(l);
                    menu.getFrame().setVisible(true);
                    this.frame.dispose();
                    logou = true;
                }
            }

            for(Participante p : participantes){
                if(p.getEmail().getEmail().equals(email.getEmail()) && p.getSenha().equals(senha)){
                    MenuParticipante menu = new MenuParticipante(p);
                    menu.setVisible(true);
                    this.frame.dispose();
                    logou = true;
                }
            }
            
            if(email.getEmail().equals("admin@email.com") && senha.equals("admin")){
                MenuAdministrador menu = new MenuAdministrador();
                menu.setVisible(true);
                this.frame.dispose();
                logou = true;
            }
            
            if(!logou)
                JOptionPane.showMessageDialog(null, "Email ou senha inválidos!");
    }
}
