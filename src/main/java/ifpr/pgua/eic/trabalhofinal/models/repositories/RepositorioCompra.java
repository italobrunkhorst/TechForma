package ifpr.pgua.eic.trabalhofinal.models.repositories;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.trabalhofinal.models.daos.CompraDAO;
import ifpr.pgua.eic.trabalhofinal.models.entities.Cliente;
import ifpr.pgua.eic.trabalhofinal.models.entities.Compra;
import ifpr.pgua.eic.trabalhofinal.models.entities.Estoque;

public class RepositorioCompra {
    
    private CompraDAO dao;

    public RepositorioCompra(CompraDAO dao){
        this.dao = dao;
    }

    public Resultado cadastrarComprar(Cliente cliente, Estoque estoque){

        Compra compra = new Compra(cliente, estoque);
        return dao.criar(compra);
    }

    public Resultado listarCompra(){
        return dao.listar();
    }

    public Resultado deletarCompra(int id){
        return dao.deletar(id);
    }
}
