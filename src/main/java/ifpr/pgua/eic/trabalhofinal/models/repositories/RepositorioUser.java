package ifpr.pgua.eic.trabalhofinal.models.repositories;

import java.util.ArrayList;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.trabalhofinal.models.daos.UserDAO;
import ifpr.pgua.eic.trabalhofinal.models.entities.User;

public class RepositorioUser {
    
    private ArrayList<User> users;

    private UserDAO dao;

    public RepositorioUser(UserDAO dao){
        users = new ArrayList<>();
        this.dao = dao;
    }

    public Resultado cadastrarUser(String nome, String email, int matricula, String senha, String confirmasenha){
        if(nome.isEmpty() || nome.isBlank()){
            return Resultado.erro("Nome inválido!");
        }
        if(email.isEmpty() || email.isBlank()){
            return Resultado.erro("Email inválido!");
        }
        if(senha.isEmpty() || senha.isBlank()){
            return Resultado.erro("Senha inválido!");
        }
        if(confirmasenha.isEmpty() || confirmasenha.isBlank() || !confirmasenha.equals(senha)){
            return Resultado.erro("Senha inválido!");
        }

        User user = new User(nome, email, matricula, senha, confirmasenha);

        return dao.criar(user);
    }

    public Resultado autenticarUser(String nome, String senha){
        Resultado resultadoBusca = dao.logar(nome);

        if (resultadoBusca.foiErro()) {
            return resultadoBusca;  // Usuário não encontrado
        }

        User user = (User) resultadoBusca.comoSucesso().getObj();

        if (user == null || !user.getSenha().equals(senha)) {
            return Resultado.erro("Credenciais inválidas");  // Senha incorreta
        }

        return Resultado.sucesso("Usuário autenticado com sucesso", user);
    }

    public Resultado listarUser(){
        return dao.listar();
    }
}
