package dcc025.ufjf.sistema.leilao;

import java.util.Date;

/**
 *
 * @author Joao Paulo
 * Define o lance dado por um participante de um leilão
 */
public class Lance {
    
    private final double valor;
    private final Participante participante;
    private final Date data;
    
    public Lance(double valor, Participante participante){
        this.valor = valor;
        this.participante = participante;
        this.data = new Date();
    }
    
    /**
     * @return the valor
     */
    public double getValor() {
        return valor;
    }

    /**
     * @return the participante
     */
    public Participante getParticipante() {
        return participante;
    }

    /**
     * @return the data
     */
    public Date getData() {
        return data;
    }
    
}
