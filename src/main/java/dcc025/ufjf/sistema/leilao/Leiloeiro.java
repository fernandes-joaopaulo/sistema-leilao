package dcc025.ufjf.sistema.leilao;

import dcc025.ufjf.auxiliar.Email;
import dcc025.ufjf.exceptions.CPFException;
import dcc025.ufjf.persistence.LeilaoPersistence;
import dcc025.ufjf.persistence.LeiloeiroPersistence;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Joao Paulo 
 * Classe que herda de Usuario e define o responsável pelo
 * leilão
 */
public class Leiloeiro extends Usuario {

    private List<Integer> leiloesAtivos;

    public Leiloeiro(String nome, String cpf, Email email, String senha) throws CPFException {
        super(nome, cpf, email, senha);
        this.leiloesAtivos = new ArrayList<>();
    }

    public List<Integer> getLeiloesAtivos() {
        return leiloesAtivos;
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

    public void adicionarLeilao(int codigo) {
        leiloesAtivos.add(codigo);
        salvar();
    }

    public void encerrarLeilao(int codigo) {
        LeilaoPersistence lp = new LeilaoPersistence();
        List<Leilao> leiloes = lp.findAll();
        for (int l1 : leiloesAtivos) {
            for (Leilao l2 : leiloes) {
                if (l1 == l2.getCodigo()) {
                    l2.setAtivo(false);
                    l2.salvar();
                }
            }
        }
        salvar();
    }

    public void salvar() {
        LeiloeiroPersistence lp = new LeiloeiroPersistence();
        lp.save(this);
    }

    public void salvarNovo() {
        LeiloeiroPersistence lp = new LeiloeiroPersistence();
        lp.add(this);
    }
}
