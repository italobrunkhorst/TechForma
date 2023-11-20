package ifpr.pgua.eic.trabalhofinal.controllers;

import com.github.hugoperlin.results.Resultado;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import ifpr.pgua.eic.trabalhofinal.App;
import ifpr.pgua.eic.trabalhofinal.models.repositories.RepositorioUser;

public class CadastroUser {
    
    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfMatricula;

    @FXML
    private PasswordField tfSenha;

    @FXML
    private PasswordField tfConfirmasenha;

    private RepositorioUser repositorio;

    public CadastroUser(RepositorioUser repositorio){
        this.repositorio = repositorio;
    }

    @FXML
    public void cadastrar(ActionEvent event){
        String nome = tfNome.getText();
        String email = tfEmail.getText();
        String smatricula = tfMatricula.getText();
        String senha = tfSenha.getText();
        String confirmasenha = tfConfirmasenha.getText();

        String msg="";

        int matricula = 0;
        try {
            matricula = Integer.valueOf(smatricula);
        } catch (NumberFormatException e) {
            msg = "Matricula inválida!";
        }

        Resultado resultado = repositorio.cadastrarUser(nome, email, matricula, senha, confirmasenha);

        Alert alert;
        
        msg = resultado.getMsg();
        if(resultado.foiErro()){
            alert = new Alert(AlertType.ERROR,msg);
        }else{
            alert = new Alert(AlertType.INFORMATION,msg);
            
        }

        alert.showAndWait();

        limpar();
    }

    @FXML
    void cancelar(ActionEvent event) {
        App.popScreen();
    }

    @FXML
    public void limpar(){
        tfNome.clear();
        tfEmail.clear();
        tfMatricula.clear();
        tfSenha.clear();
        tfConfirmasenha.clear();
    }

}
