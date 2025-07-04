package dcc025.ufjf.persistence;

import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import dcc025.ufjf.sistema.leilao.Leiloeiro;

/**
 *
 * @author Joao Paulo 
 * Persistencia dos leiloes cadastrados do sistema
 */
public class LeiloeiroPersistence implements Persistence<Leiloeiro> {

    private static final String PATH = DIRECTORY + File.separator + "leiloeiros.json";

    @Override
    public void save(List<Leiloeiro> itens) {
        Gson gson = new Gson();
        String json = gson.toJson(itens);

        File diretorio = new File(DIRECTORY);
        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }

        Arquivo.salva(PATH, json);
    }

    @Override
    public List<Leiloeiro> findAll() {
        Gson gson = new Gson();

        String json = Arquivo.le(PATH);

        List<Leiloeiro> leiloeiros = new ArrayList<>();
        if (!json.trim().equals("")) {

            Type tipoLista = new TypeToken<List<Leiloeiro>>() {
            }.getType();
            leiloeiros = gson.fromJson(json, tipoLista);

            if (leiloeiros == null) {
                leiloeiros = new ArrayList<>();
            }
        }

        return leiloeiros;
    }

    @Override
    public void add(Leiloeiro leiloeiro) {
        List<Leiloeiro> leiloeiros = findAll();
        leiloeiros.add(leiloeiro);
        save(leiloeiros);
    }

    @Override
    public void save(Leiloeiro leiloeiro) {
        List<Leiloeiro> leiloeiros = findAll();
        for (int i = 0; i < leiloeiros.size(); i++) {
            if (leiloeiros.get(i).getId() == leiloeiro.getId()) {
                leiloeiros.set(i, leiloeiro);
                break;
            }
        }
        save(leiloeiros);
    }
}
