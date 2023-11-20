package ifpr.pgua.eic.trabalhofinal.models.daos;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.trabalhofinal.models.entities.Estoque;

public interface EstoqueDAO {
    
    Resultado criar(Estoque estoque);
    Resultado listar();
    Resultado deletar(int id);
}
