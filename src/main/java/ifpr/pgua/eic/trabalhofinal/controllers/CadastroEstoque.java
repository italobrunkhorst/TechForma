package ifpr.pgua.eic.trabalhofinal.controllers;

import java.time.LocalDate;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.trabalhofinal.App;
import ifpr.pgua.eic.trabalhofinal.models.repositories.RepositorioEstoque;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class CadastroEstoque {
    
    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfQuantidade;

    @FXML
    private DatePicker datePicker;

    private RepositorioEstoque repositorio;

    public CadastroEstoque(RepositorioEstoque repositorio){
        this.repositorio = repositorio;
    }

    @FXML
    public void cadastrar(ActionEvent event){
        
        String nome = tfNome.getText();
        int quantidade = Integer.parseInt(tfQuantidade.getText());
        LocalDate dataValidade = datePicker.getValue();

        Resultado resultado = repositorio.cadastrarEstoque(nome, quantidade, dataValidade);

        Alert alert;
        if(resultado.foiErro()){
            alert = new Alert(AlertType.ERROR, resultado.getMsg());
        }else{
            alert = new Alert(AlertType.INFORMATION, resultado.getMsg());
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
        tfQuantidade.clear();
        datePicker.setValue(null);
    }
}
