package dcc025.ufjf.sistema.leilao;

import dcc025.ufjf.persistence.LeilaoPersistence;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.List;

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
    private List<Item> itens;
    private final Date inicio;
    private Date fim;
    private boolean ativo;
    
    public Leilao(Leiloeiro leiloeiro, List<Item> itens){
        this.codigo = codAtual++;
        this.leiloeiro = leiloeiro;
        this.participantes = new HashMap<>();
        this.itens = itens;
        this.inicio = new Date();
        this.ativo = true;
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
    public List<Item> getItens() {
        return itens;
    }

    /**
     * @param itens the itens to set
     */
    public void setItens(List<Item> itens) {
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
    
    public void salvar(){
        LeilaoPersistence lp = new LeilaoPersistence();
        lp.add(this);
    }
    
    public void salvarExistente(){
        LeilaoPersistence lp = new LeilaoPersistence();
        System.out.println(this.getItens().getFirst().getMaiorLance());
        lp.save(this);
    }

    /**
     * @return the ativo
     */
    public boolean isAtivo() {
        return ativo;
    }

    /**
     * @param ativo the ativo to set
     */
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

}
