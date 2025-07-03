package dcc025.ufjf.sistema.leilao;

import dcc025.ufjf.auxiliar.Email;
import java.util.Map;
import java.util.HashMap;

/**
 *
 * @author Joao Paulo
 * Classe que herda de Usuario e define o administrador do sistema
 */
public class Administrador extends Usuario{
    
    public Administrador(String nome, String cpf, Email email, String senha){
        super(nome, cpf, email, senha);
    }
    
    public Map<Integer, Leilao> getLeiloesAtivos(){
        Map<Integer, Leilao> ativos = new HashMap<>();
        return ativos;
    }
}
