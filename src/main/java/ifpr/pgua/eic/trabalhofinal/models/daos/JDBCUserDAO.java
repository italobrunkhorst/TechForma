package ifpr.pgua.eic.trabalhofinal.models.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.trabalhofinal.models.entities.User;

public class JDBCUserDAO implements UserDAO{
    
    private FabricaConexoes fabrica;

    public JDBCUserDAO(FabricaConexoes fabrica){
        this.fabrica = fabrica;
    }

    @Override
    public Resultado criar(User user) {
        
        try (Connection con = fabrica.getConnection()){
            
            PreparedStatement pstm = con.prepareStatement("INSERT INTO users(nome, email, matricula, senha, confirmasenha) VALUES (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

            pstm.setString(1, user.getNome());
            pstm.setString(2, user.getEmail());
            pstm.setInt(3, user.getMatricula());
            pstm.setString(4, user.getSenha());
            pstm.setString(5, user.getConfirmasenha());

            int ret =pstm.executeUpdate();

            if (ret == 1) {
                ResultSet rs = pstm.getGeneratedKeys();
                rs.next();
                int id = rs.getInt(1);

                user.setId(id);

                return Resultado.sucesso("User Cadastrado!", user);
            }
            return Resultado.erro("Erro no JDBCUser");
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado logar(String nome) {
        try (Connection con = fabrica.getConnection()){
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM users WHERE nome = ?");
            pstm.setString(1, nome);

            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String email = rs.getString("email");
                int matricula = rs.getInt("matricula");
                String senha = rs.getString("senha");
                String confirmasenha = rs.getString("confirmasenha");

                User user = new User(id, nome, email, matricula, senha, confirmasenha);
                return Resultado.sucesso("Usuário encontrado", user);
            } else {
                return Resultado.erro("Usuário não encontrado");
            }
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado listar() {
        try (Connection con = fabrica.getConnection()){
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM users");

            ResultSet rs = pstm.executeQuery();

            ArrayList<User> lista = new ArrayList<>();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                int matricula = rs.getInt("matricula");
                String senha = rs.getString("senha");
                String confirmasenha = rs.getString("confirmasenha"); 

                User user = new User(id, nome, email, matricula, senha, confirmasenha);
                lista.add(user);
            }
            return Resultado.sucesso("Lista de Usúarios", lista);
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }
}
