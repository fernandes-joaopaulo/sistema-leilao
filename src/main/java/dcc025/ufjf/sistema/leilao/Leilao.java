package dcc025.ufjf.sistema.leilao;

import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

/**
 *
 * @author Joao Paulo
 * Define a classe de um leilão no sistema
 */
public class Leilao {
    
    private final int codigo;
    private static int codAtual = 0;
    private final Leiloeiro leiloeiro;
    private Map<Integer, Participante> participantes;
    private Set<Item> itens;
    private final Date inicio;
    private Date fim;
    
    public Leilao(Leiloeiro leiloeiro, Set<Item> itens){
        this.codigo = codAtual++;
        this.leiloeiro = leiloeiro;
        this.participantes = new HashMap<>();
        this.itens = new HashSet<>();
        this.inicio = new Date();
    }

    /**
     * @return the codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * @return the leiloeiro
     */
    public Leiloeiro getLeiloeiro() {
        return leiloeiro;
    }

    /**
     * @return the participantes
     */
    public Map<Integer,Participante> getParticipantes() {
        return participantes;
    }

    /**
     * @param participantes the participantes to set
     */
    public void setParticipantes(Map<Integer, Participante> participantes) {
        this.participantes = participantes;
    }

    /**
     * @return the itens
     */
    public Set<Item> getItens() {
        return itens;
    }

    /**
     * @param itens the itens to set
     */
    public void setItens(Set<Item> itens) {
        this.itens = itens;
    }

    /**
     * @return the inicio
     */
    public Date getInicio() {
        return inicio;
    }

    /**
     * @return the fim
     */
    public Date getFim() {
        return fim;
    }

    /**
     * @param fim the fim to set
     */
    public void setFim(Date fim) {
        this.fim = fim;
    }
    
}
