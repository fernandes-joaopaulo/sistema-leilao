package dcc025.ufjf.sistema.leilao;

import dcc025.ufjf.auxiliar.Email;
import java.util.Map;
import java.util.HashMap;

/**
 *
 * @author Joao Paulo Classe que herda de Usuario e define o participante do
 * leilão
 */
public class Participante extends Usuario {

    private Map<Integer, Leilao> leiloesAtivos;
    
    public Participante(String nome, String cpf, Email email, String senha) {
        super(nome, cpf, email, senha);
        this.leiloesAtivos = new HashMap<>();
    }

    public Map<Integer, Leilao> getLeiloesAtivos(){
        return leiloesAtivos;
    }
    
    public void adicionarLeilao(int codigo, Leilao leilao){
        leiloesAtivos.put(codigo, leilao);
    }
    
    public void darLance() {

    }
}
