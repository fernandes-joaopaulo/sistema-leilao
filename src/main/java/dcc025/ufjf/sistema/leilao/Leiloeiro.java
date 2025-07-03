package dcc025.ufjf.sistema.leilao;

import java.util.Map;
import java.util.HashMap;

import dcc025.ufjf.auxiliar.Email;

/**
 *
 * @author Joao Paulo Classe que herda de Usuario e define o responsável pelo
 * leilão
 */
public class Leiloeiro extends Usuario {

    private Map<Integer, Leilao> leiloesAtivos;
    
    public Leiloeiro(String nome, String cpf, Email email, String senha) {
        super(nome, cpf, email, senha);
        this.leiloesAtivos = new HashMap<>();
    }
    
    public Map<Integer, Leilao> getLeiloesAtivos(){
        return this.leiloesAtivos;
    }
    
    public void adicionarLeilao(int codigo, Leilao leilao){
        leiloesAtivos.put(codigo, leilao);
    }
    
    public void encerrarLeilao(int codigo){
        leiloesAtivos.remove(codigo);
    }
}
