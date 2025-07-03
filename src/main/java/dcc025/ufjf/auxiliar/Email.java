package dcc025.ufjf.auxiliar;

import dcc025.ufjf.exceptions.EmailException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Joao Paulo
 * Define a estrutura do e-mail do usuário
 */
public class Email {

    private String email;
    private String login;
    private String dominio;


    public Email(String email) throws EmailException {
        setEmail(email);
    }

    private boolean isValidEmail(String email) {
        String emailRegex = 
          "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws EmailException {
        if(! isValidEmail(email))
            throw new EmailException();

        this.email = email;
        String[] partes = email.split("@");

        this.login = partes[0];
        this.dominio = partes[1];
    }

    public String getLogin() {
        return login;
    }

    public String getDominio() {
        return dominio;
    }

}

