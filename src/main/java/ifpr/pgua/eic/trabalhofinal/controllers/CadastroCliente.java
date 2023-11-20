package ifpr.pgua.eic.trabalhofinal.controllers;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.trabalhofinal.App;
import ifpr.pgua.eic.trabalhofinal.models.repositories.RepositorioCliente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

public class CadastroCliente {
    
    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfCpf;

    @FXML
    private TextField tfTelefone;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfPorcentagem;

    private RepositorioCliente repositorio;

    public CadastroCliente(RepositorioCliente repositorio){
        this.repositorio = repositorio;
    }

    @FXML
    public void cadastrar(ActionEvent event){
        String nome = tfNome.getText();
        String cpf = tfCpf.getText();
        String stelefone = tfTelefone.getText();
        String email = tfEmail.getText();
        String porcentagem = tfPorcentagem.getText();

        String msg="";

        int telefone = 0;
        try {
            telefone = Integer.valueOf(stelefone);
        } catch (NumberFormatException e) {
            msg = "Telefone inválida!";
        }

        Resultado resultado = repositorio.cadastrarCliente(nome, cpf, telefone, email, porcentagem);

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
    private void limpar(){
        tfNome.clear();
        tfCpf.clear();
        tfTelefone.clear();
        tfEmail.clear();
        tfPorcentagem.clear();
    }
}
