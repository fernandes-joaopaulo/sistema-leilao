package dcc025.ufjf.sistema.leilao;

import dcc025.ufjf.auxiliar.Email;
import dcc025.ufjf.exceptions.CPFException;
import dcc025.ufjf.persistence.LeilaoPersistence;
import dcc025.ufjf.persistence.ParticipantePersistence;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Joao Paulo Classe que herda de Usuario e define o participante do
 * leilão
 */
public class Participante extends Usuario {

    private List<Integer> leiloesAtivos;
    
    public Participante(String nome, String cpf, Email email, String senha) throws CPFException {
        super(nome, cpf, email, senha);
        this.leiloesAtivos = new ArrayList<>();
    }

    public List<Integer> getLeiloesAtivos(){
        return leiloesAtivos;
    }
    
    public void adicionarLeilao(int codigo){
        leiloesAtivos.add(codigo);
    }
    
    public void salvarNovo(){
        ParticipantePersistence pp = new ParticipantePersistence();
        pp.add(this);
    }
    
    public void salvar(){
        ParticipantePersistence pp = new ParticipantePersistence();
        pp.save(this);
    }
    
    public List<Leilao> carregaLeiloesAtivos() {
        LeilaoPersistence lp = new LeilaoPersistence();
        List<Leilao> ativos = new ArrayList<>();
        List<Leilao> leiloes = lp.findAll();
        for (int l1 : leiloesAtivos) {
            for (Leilao l2 : leiloes) {
                if (l1 == l2.getCodigo() && l2.isAtivo()) {
                    ativos.add(l2);
                }
            }
        }
        return ativos;
    }
}
