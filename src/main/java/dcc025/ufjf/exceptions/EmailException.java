package dcc025.ufjf.exceptions;

/**
 *
 * @author Joao Paulo
 * Cria exceção para e-mail
 */
public class EmailException extends Exception {

    public EmailException() {
        super("E-mail inválido!");
    }
}

