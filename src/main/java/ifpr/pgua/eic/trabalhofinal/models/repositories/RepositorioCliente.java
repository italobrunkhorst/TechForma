package ifpr.pgua.eic.trabalhofinal.models.repositories;

import java.util.ArrayList;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.trabalhofinal.models.daos.ClienteDAO;
import ifpr.pgua.eic.trabalhofinal.models.entities.Cliente;

public class RepositorioCliente {
    
    private ClienteDAO dao;

    public RepositorioCliente(ClienteDAO dao){
        this.dao = dao;
    }

    public Resultado cadastrarCliente(String nome, String cpf, int telefone, String email, String porcentagem){
        
        if(nome.isEmpty() || nome.isBlank()){
            return Resultado.erro("Nome inválido!");
        }
        if(cpf.isEmpty() || cpf.isBlank()){
            return Resultado.erro("Cpf inválido!");
        }
        if(telefone < 0){
            return Resultado.erro("Telefone inválido!");
        }
        if(email.isEmpty() || email.isBlank()){
            return Resultado.erro("Email inválido!");
        }
        if(porcentagem.isEmpty() || porcentagem.isBlank()){
            return Resultado.erro("Porcentagem inválido!");
        }

        Cliente cliente = new Cliente(nome, cpf, telefone, email, porcentagem);

        return dao.criar(cliente);
    }

    public Resultado alterarCliente(Cliente cliente) {
        try {
            Resultado resultado = dao.atualizar(cliente.getId(), cliente);
            if (resultado.foiSucesso()) {
                return Resultado.sucesso("Cliente atualizado com sucesso", cliente);
            } else {
                return Resultado.erro("Erro ao atualizar cliente: " + resultado.getMsg());
            }
        } catch (Exception e) {
            return Resultado.erro("Erro interno ao atualizar cliente: " + e.getMessage());
        }
    }

    public Resultado deletarCliente(int id) {
        return dao.deletar(id);
    }

    public Resultado listarCliente(){
        return dao.listar();
    }
}
