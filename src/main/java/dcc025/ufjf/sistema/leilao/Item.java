package dcc025.ufjf.sistema.leilao;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Joao Paulo
 * Define o item apresentado em um leilão
 */
public class Item {
    
    private final String titulo;
    private final String descricao;
    private final String foto_URL;
    private final double lanceMinimo;
    private List<Lance> lances;
    private boolean arrematado;
    
    public Item(String titulo, String descricao, String foto_URL, double lanceMinimo){
        this.titulo = titulo;
        this.descricao = descricao;
        this.foto_URL = foto_URL;
        this.lanceMinimo = lanceMinimo;
        this.lances = new ArrayList<>();
        this.arrematado = false;
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @return the foto_URL
     */
    public String getFoto_URL() {
        return foto_URL;
    }

    /**
     * @return the lanceMinimo
     */
    public double getLanceMinimo() {
        return lanceMinimo;
    }

    /**
     * @return the lances
     */
    public List<Lance> getLances() {
        return lances;
    }

    /**
     * @param lances the lances to set
     */
    public void setLances(List<Lance> lances) {
        this.lances = lances;
    }

    /**
     * @return the arrematado
     */
    public boolean isArrematado() {
        return arrematado;
    }

    /**
     * @param arrematado the arrematado to set
     */
    public void setArrematado(boolean arrematado) {
        this.arrematado = arrematado;
    }
    
}
