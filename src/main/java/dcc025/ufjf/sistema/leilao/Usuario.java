package dcc025.ufjf.sistema.leilao;

import dcc025.ufjf.auxiliar.Email;
import dcc025.ufjf.auxiliar.ValidaCPF;
import dcc025.ufjf.exceptions.CPFException;
import java.util.List;

/**
 *
 * @author Joao Paulo
 * Classe pai que define a estrutura de todos os usuários do sistema
 */
public abstract class Usuario {
    
    private final int id;
    private static int idAtual = 0;
    private final String nome;
    private String cpf;
    private Email email;
    private String senha;
        
    public Usuario(String nome, String cpf, Email email, String senha) throws CPFException{
        this.id = idAtual++;
        this.nome = nome;
        setCpf(cpf);
        this.email = email;
        this.senha = senha;
    }
    
    public abstract List<Integer> getLeiloesAtivos();

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

    /**
     * @param cpf the cpf to set
     */
    public void setCpf(String cpf) throws CPFException {
        if(!ValidaCPF.isCPF(cpf))
            throw new CPFException();
        this.cpf = cpf;
    }
   
}
