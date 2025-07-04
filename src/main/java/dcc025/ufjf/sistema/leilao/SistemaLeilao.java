package dcc025.ufjf.sistema.leilao;

import dcc025.ufjf.interfaces.TelaLogin;

/**
 *
 * @author Joao Paulo
 * Classe principal de execução do sistema
 */
public class SistemaLeilao {
    
    public static void main(String[] args) {
        TelaLogin tela = new TelaLogin();
        tela.getFrame().setVisible(true);
    }
}
