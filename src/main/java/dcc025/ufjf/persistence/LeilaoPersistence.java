package dcc025.ufjf.persistence;

import com.google.gson.reflect.TypeToken;
import dcc025.ufjf.sistema.leilao.Leilao;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;

/**
 *
 * @author Joao Paulo
 * Persistencia dos leiloes cadastrados do sistema
 */
public class LeilaoPersistence implements Persistence<Leilao> {
    
    private static final String PATH = DIRECTORY+ File.separator +"leiloes.json";
    @Override
    public void save(List<Leilao> itens) {
        Gson gson = new Gson();
        String json = gson.toJson(itens);

        File diretorio = new File(DIRECTORY);
        if(!diretorio.exists())
            diretorio.mkdirs();

        Arquivo.salva(PATH, json);


    }

    @Override
    public List<Leilao> findAll() {
        Gson gson = new Gson();

        String json = Arquivo.le(PATH);

        List<Leilao> leiloes = new ArrayList<>();
        if(!json.trim().equals("")) {

        Type tipoLista = new TypeToken<List<Leilao>>() {
            }.getType();
        leiloes = gson.fromJson(json, tipoLista);

            if (leiloes == null)
                leiloes = new ArrayList<>();
        }

        return leiloes;
    }


}
