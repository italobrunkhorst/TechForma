package ifpr.pgua.eic.trabalhofinal.models.daos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.trabalhofinal.models.entities.Estoque;

public class JDBCEstoqueDAO implements EstoqueDAO {

    private FabricaConexoes fabrica;

    public JDBCEstoqueDAO(FabricaConexoes fabrica){
        this.fabrica = fabrica;
    }

    @Override
    public Resultado criar(Estoque estoque) {
        try (Connection con = fabrica.getConnection()){
            
            PreparedStatement pstm = con.prepareStatement("INSERT INTO estoques(nome, quantidade, dataValidade) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
            
            pstm.setString(1, estoque.getNome());
            pstm.setInt(2, estoque.getQuantidade());
            pstm.setDate(3, Date.valueOf(estoque.getDataValidade()));

            int ret = pstm.executeUpdate();

            if (ret == 1) {
                ResultSet rs = pstm.getGeneratedKeys();
                rs.next();
                int id = rs.getInt(1);

                estoque.setId(id);

                return Resultado.sucesso("Produto Cadastrado", estoque);
            }
            return Resultado.erro("Erro na JDBCESTOQUE");

        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado listar() {
        try (Connection con = fabrica.getConnection()){
            
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM estoques");

            ResultSet rs = pstm.executeQuery();

            ArrayList<Estoque> lista = new ArrayList<>();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                int quantidade = rs.getInt("quantidade");
                LocalDate dataValidade = rs.getDate("dataValidade").toLocalDate();

                Estoque estoque = new Estoque(id, nome, quantidade, dataValidade);
                lista.add(estoque);
            }
            return Resultado.sucesso("Produtos listados", lista);
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado deletar(int id) {
        try (Connection con = fabrica.getConnection()){
            PreparedStatement pstm = con.prepareStatement("DELETE FROM estoques WHERE id = ?");
            pstm.setInt(1, id);
    
            int ret = pstm.executeUpdate();
    
            if (ret == 1) {
                return Resultado.sucesso("Produto deletado com sucesso", ret);
            } else {
                return Resultado.erro("Produto com o ID " + id + " não encontrado");
            }
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }
    
}
