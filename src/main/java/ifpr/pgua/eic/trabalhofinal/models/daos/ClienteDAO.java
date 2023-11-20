package ifpr.pgua.eic.trabalhofinal.models.daos;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.trabalhofinal.models.entities.Cliente;

public interface ClienteDAO {

    Resultado criar(Cliente cliente);
    Resultado listar();
    Resultado atualizar(int id, Cliente novo);
    Resultado deletar(int id);
    
}
