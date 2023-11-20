package ifpr.pgua.eic.trabalhofinal.models.daos;


import com.github.hugoperlin.results.Resultado;
import ifpr.pgua.eic.trabalhofinal.models.entities.User;

/**
 * UserDAO
 */
public interface UserDAO {

    Resultado criar(User user);
    Resultado logar(String nome);
    Resultado listar();
}