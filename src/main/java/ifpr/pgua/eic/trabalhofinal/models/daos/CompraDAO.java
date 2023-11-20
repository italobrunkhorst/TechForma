package ifpr.pgua.eic.trabalhofinal.models.daos;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.trabalhofinal.models.entities.Cliente;
import ifpr.pgua.eic.trabalhofinal.models.entities.Compra;
import ifpr.pgua.eic.trabalhofinal.models.entities.Estoque;

public interface CompraDAO {
    Resultado criar(Compra compra);
    Resultado listar();
    Resultado deletar(int id);
    Cliente obterClienteId(int idCliente);
    Estoque obterEstoqueId(int idEstoque);
}
