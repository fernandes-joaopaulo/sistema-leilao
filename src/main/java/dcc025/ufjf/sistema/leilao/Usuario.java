package dcc025.ufjf.sistema.leilao;

import dcc025.ufjf.auxiliar.Email;
import java.util.Map;

/**
 *
 * @author Joao Paulo
 * Classe pai que define a estrutura de todos os usuários do sistema
 */
public abstract class Usuario {
    
    private final int id;
    private static int idAtual = 0;
    private final String nome;
    private final String cpf;
    private Email email;
    private String senha;
        
    public Usuario(String nome, String cpf, Email email, String senha){
        this.id = idAtual++;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
    }
    
    public abstract Map<Integer, Leilao> getLeiloesAtivos();

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @return the cpf
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * @return the email
     */
    public Email getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(Email email) {
        this.email = email;
    }

    /**
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }
   
}
