package dcc025.ufjf.sistema.leilao;

import dcc025.ufjf.interfaces.TelaLogin;

import java.util.Map;
import java.util.HashMap;

/**
 *
 * @author Joao Paulo
 * Classe principal de execução do sistema
 */
public class SistemaLeilao {

    private Map<Integer, Usuario> usuarios;
    private Map<Integer, Leilao> leiloes;
    
    public SistemaLeilao(){
        usuarios = new HashMap<>();
        leiloes = new HashMap<>();
    }
    
    public static void main(String[] args) {
        TelaLogin tela = new TelaLogin();
        tela.getFrame().setVisible(true);
    }
}
