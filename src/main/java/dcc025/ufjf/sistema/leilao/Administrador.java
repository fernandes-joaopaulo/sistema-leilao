package dcc025.ufjf.sistema.leilao;

import dcc025.ufjf.auxiliar.Email;
import dcc025.ufjf.exceptions.CPFException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Joao Paulo
 * Classe que herda de Usuario e define o administrador do sistema
 */
public class Administrador extends Usuario{
    
    public Administrador(String nome, String cpf, Email email, String senha) throws CPFException{
        super(nome, cpf, email, senha);
    }
    
    public List<Integer> getLeiloesAtivos(){
        List<Integer> ativos = new ArrayList<>();
        return ativos;
    }
}
