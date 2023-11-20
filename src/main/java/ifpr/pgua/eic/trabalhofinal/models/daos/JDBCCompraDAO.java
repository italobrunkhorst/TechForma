package ifpr.pgua.eic.trabalhofinal.models.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.trabalhofinal.models.entities.Cliente;
import ifpr.pgua.eic.trabalhofinal.models.entities.Compra;
import ifpr.pgua.eic.trabalhofinal.models.entities.Estoque;
import ifpr.pgua.eic.trabalhofinal.utils.DButils;

public class JDBCCompraDAO implements CompraDAO{

    private FabricaConexoes fabrica;

    public JDBCCompraDAO(FabricaConexoes fabrica){
        this.fabrica = fabrica;
    }
    @Override
    public Resultado criar(Compra compra) {
        try (Connection con = fabrica.getConnection()){
            PreparedStatement pstm = con.prepareStatement("INSERT INTO compras(cliente, estoque) VALUES (?,?)",Statement.RETURN_GENERATED_KEYS);

            pstm.setInt(1, compra.getCliente().getId());
            pstm.setInt(2, compra.getEstoque().getId());

            int ret = pstm.executeUpdate();

            if (ret == 1) {
                int id = DButils.getLastId(pstm);

                compra.setId(id);

                return Resultado.sucesso("Comprar realizada", compra);
            }
            return Resultado.erro("Erro no JDBCCompra");
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado listar() {
        try (Connection con = fabrica.getConnection()){
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM compras");
            ResultSet rs = pstm.executeQuery();

            List<Compra> lista = new ArrayList<>();

            while (rs.next()) {
                int id = rs.getInt("id");
                int idCliente = rs.getInt("cliente");
                int idEstoque = rs.getInt("estoque");

                Cliente cliente = obterClienteId(idCliente);
                Estoque estoque = obterEstoqueId(idEstoque);

                Compra compra = new Compra(id, cliente, estoque);
                lista.add(compra);
            }
            return Resultado.sucesso("Lista de compras obtida com sucesso", lista);
            
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado deletar(int id) {
        try (Connection con = fabrica.getConnection()) {
            PreparedStatement pstm = con.prepareStatement("DELETE FROM compras WHERE id = ?");
            pstm.setInt(1, id);
    
            int ret = pstm.executeUpdate();
    
            if (ret == 1) {
                return Resultado.sucesso("Compra excluída com sucesso", id);
            }
            return Resultado.erro("Compra não encontrada");
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }
    @Override
    public Cliente obterClienteId(int idCliente) {
        try (Connection con = fabrica.getConnection()){
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM clientes WHERE id = ?");
            pstm.setInt(1, idCliente);
            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                String nome = rs.getString("nome");

                return new Cliente(idCliente, nome);
            }else {
                // Cliente não encontrado
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public Estoque obterEstoqueId(int idEstoque) {
        try (Connection con = fabrica.getConnection()){
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM estoques WHERE id =?");
            pstm.setInt(1, idEstoque);
            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                String nome = rs.getString("nome");

                return new Estoque(idEstoque, nome);

            }else {
                // Cliente não encontrado
                return null;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
