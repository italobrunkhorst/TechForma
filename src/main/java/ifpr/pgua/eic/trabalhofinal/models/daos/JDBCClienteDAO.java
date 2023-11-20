package ifpr.pgua.eic.trabalhofinal.models.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.trabalhofinal.models.entities.Cliente;

public class JDBCClienteDAO implements ClienteDAO{

    private FabricaConexoes fabrica;

    public JDBCClienteDAO(FabricaConexoes fabrica){
        this.fabrica = fabrica;
    }

    @Override
    public Resultado criar(Cliente cliente) {
        try (Connection con = fabrica.getConnection()){
            
            PreparedStatement pstm = con.prepareStatement("INSERT INTO clientes(nome, cpf, telefone, email, porcentagem) VALUES (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

            pstm.setString(1, cliente.getNome());
            pstm.setString(2, cliente.getCpf());
            pstm.setInt(3, cliente.getTelefone());
            pstm.setString(4, cliente.getEmail());
            pstm.setString(5, cliente.getPorcentagem());

            int ret = pstm.executeUpdate();

            if (ret == 1) {
                ResultSet rs = pstm.getGeneratedKeys();
                rs.next();
                int id = rs.getInt(1); 

                cliente.setId(id);

                return Resultado.sucesso("Cliente Cadastrado", cliente);
            }
            return Resultado.erro("Erro no JDBCCliente");
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado listar() {
        try (Connection con = fabrica.getConnection()){
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM clientes");
            
            ResultSet rs = pstm.executeQuery();

            ArrayList<Cliente> lista = new ArrayList<>();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");
                int telefone = rs.getInt("telefone");
                String email = rs.getString("email");
                String porcentagem = rs.getString("porcentagem");

                Cliente cliente = new Cliente(id, nome, cpf, telefone, email, porcentagem);
                lista.add(cliente);
            }
            return Resultado.sucesso("Lista de Clientes", lista);
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
        
    }

    @Override
    public Resultado atualizar(int id, Cliente novo) {
        try (Connection con = fabrica.getConnection()){
            PreparedStatement pstm = con.prepareStatement("UPDATE clientes SET nome=?, cpf=?, telefone=?, email=?, porcentagem=?  WHERE id=?");

            pstm.setString(1, novo.getNome());
            pstm.setString(2, novo.getCpf());
            pstm.setInt(3, novo.getTelefone());
            pstm.setString(4, novo.getEmail());
            pstm.setString(5, novo.getPorcentagem());
            pstm.setInt(6, id);

            int ret = pstm.executeUpdate();

            if (ret == 1) {
                return Resultado.sucesso("Cliente atualizado com sucesso", novo);
            } else {
                return Resultado.erro("Cliente com o ID " + id + " não encontrado");
            }
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado deletar(int id) {
        try (Connection con = fabrica.getConnection()){
            PreparedStatement pstm = con.prepareStatement("DELETE FROM clientes WHERE id = ?");
            pstm.setInt(1, id);
    
            int ret = pstm.executeUpdate();
    
            if (ret == 1) {
                return Resultado.sucesso("Cliente deletado com sucesso", ret);
            } else {
                return Resultado.erro("Cliente com o ID " + id + " não encontrado");
            }
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }
    
}
