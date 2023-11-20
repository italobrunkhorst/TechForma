package ifpr.pgua.eic.trabalhofinal.models.repositories;

import java.time.LocalDate;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.trabalhofinal.models.daos.EstoqueDAO;
import ifpr.pgua.eic.trabalhofinal.models.entities.Estoque;

public class RepositorioEstoque {
    
    private EstoqueDAO dao;

    public RepositorioEstoque(EstoqueDAO dao){
        this.dao = dao;
    }

    public Resultado cadastrarEstoque(String nome, int quantidade, LocalDate dataValidade){

        if(nome.isEmpty() || nome.isBlank()){
            return Resultado.erro("Nome inválido!");
        }
        if (quantidade < 0) {
            return Resultado.erro("Quantidade inválida!");
        }
        if (dataValidade.isBefore(LocalDate.now())) {
            return Resultado.erro("Data invalida");
        }

        Estoque estoque = new Estoque(nome, quantidade, dataValidade);
        return dao.criar(estoque);
    }

    public Resultado listarEstoque(){
        return dao.listar();
    }

    public Resultado deletarEstoque(int id){
        return dao.deletar(id);
    }
}
