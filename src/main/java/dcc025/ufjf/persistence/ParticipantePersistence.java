package dcc025.ufjf.persistence;

import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import dcc025.ufjf.sistema.leilao.Participante;

/**
 *
 * @author Joao Paulo 
 * Persistencia dos participantes cadastrados do sistema
 */
public class ParticipantePersistence implements Persistence<Participante> {

    private static final String PATH = DIRECTORY + File.separator + "participantes.json";

    @Override
    public void save(List<Participante> itens) {
        Gson gson = new Gson();
        String json = gson.toJson(itens);

        File diretorio = new File(DIRECTORY);
        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }

        Arquivo.salva(PATH, json);
    }

    @Override
    public List<Participante> findAll() {
        Gson gson = new Gson();

        String json = Arquivo.le(PATH);

        List<Participante> participantes = new ArrayList<>();
        if (!json.trim().equals("")) {

            Type tipoLista = new TypeToken<List<Participante>>() {
            }.getType();
            participantes = gson.fromJson(json, tipoLista);

            if (participantes == null) {
                participantes = new ArrayList<>();
            }
        }

        return participantes;
    }

    @Override
    public void add(Participante participante) {
        List<Participante> participantes = findAll();
        participantes.add(participante);
        save(participantes);
    }

    @Override
    public void save(Participante participante) {
        List<Participante> participantes = findAll();
        for (int i = 0; i < participantes.size(); i++) {
            if (participantes.get(i).getId() == participante.getId()) {
                participantes.set(i, participante);
                break;
            }
        }

        save(participantes);
    }
}
