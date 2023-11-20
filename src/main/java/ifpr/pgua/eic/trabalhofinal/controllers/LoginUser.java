package ifpr.pgua.eic.trabalhofinal.controllers;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.trabalhofinal.App;
import ifpr.pgua.eic.trabalhofinal.models.repositories.RepositorioUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginUser {

    @FXML
    private TextField tfNomeLogin;

    @FXML
    private PasswordField pfSenhaLogin;

    private RepositorioUser repositorio;

    public LoginUser(RepositorioUser repositorio){
        this.repositorio = repositorio;
    }
    
     @FXML
    public void logar(ActionEvent event){
        String nome = tfNomeLogin.getText();
        String senha = pfSenhaLogin.getText();

        Resultado resultado = repositorio.autenticarUser(nome, senha);

        // Se a autenticação for bem-sucedida, você pode adicionar lógica adicional aqui
        if (!resultado.foiErro()) {
            // Faça algo após o login bem-sucedido
            App.pushScreen("SECUNDARIA");;  // Por exemplo, voltar para a tela anterior
        }
        
        Alert alert;
        
        if(resultado.foiErro()){
            alert = new Alert(AlertType.ERROR, resultado.getMsg());
        }else{
            alert = new Alert(AlertType.INFORMATION, resultado.getMsg());
        }

        alert.showAndWait();

    }

    @FXML
    void cancelar(ActionEvent event) {
        App.popScreen();
    }
}
