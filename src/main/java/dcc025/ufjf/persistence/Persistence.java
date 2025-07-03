package dcc025.ufjf.persistence;

import java.util.List;

/**
 *
 * @author Joao Paulo
 * Interface para persistencia de tipo generico
 */
public interface Persistence<T> {

    String DIRECTORY = "data";
    public void save(List<T> itens);
    public List<T> findAll();

}
