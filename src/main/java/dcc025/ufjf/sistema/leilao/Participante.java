package dcc025.ufjf.sistema.leilao;

import dcc025.ufjf.auxiliar.Email;

/**
 *
 * @author Joao Paulo Classe que herda de Usuario e define o participante do
 * leilão
 */
public class Participante extends Usuario {

    public Participante(String nome, String cpf, Email email, String senha) {
        super(nome, cpf, email, senha);
    }

    public void darLance() {

    }
}
