package ifpr.pgua.eic.trabalhofinal.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.trabalhofinal.App;
import ifpr.pgua.eic.trabalhofinal.models.entities.Cliente;
import ifpr.pgua.eic.trabalhofinal.models.entities.Estoque;
import ifpr.pgua.eic.trabalhofinal.models.repositories.RepositorioCliente;
import ifpr.pgua.eic.trabalhofinal.models.repositories.RepositorioCompra;
import ifpr.pgua.eic.trabalhofinal.models.repositories.RepositorioEstoque;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;

public class CadastroCompra implements Initializable{
    
    @FXML
    private ComboBox<Cliente> cbCliente;

    @FXML
    private ComboBox<Estoque> cbEstoque;

    private RepositorioCompra repositorio;
    private RepositorioCliente repositorioCliente;
    private RepositorioEstoque repositorioEstoque;

    public CadastroCompra(RepositorioCompra repositorio, RepositorioCliente repositorioCliente, RepositorioEstoque repositorioEstoque){
        this.repositorio = repositorio;
        this.repositorioCliente = repositorioCliente;
        this.repositorioEstoque = repositorioEstoque;
    }

    @FXML
    void cadastrar(ActionEvent event){
        Cliente cliente = cbCliente.getValue();
        Estoque estoque = cbEstoque.getValue();

        Resultado resultado = repositorio.cadastrarComprar(cliente, estoque);

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

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
        Resultado r1 = repositorioCliente.listarCliente();

        if(r1.foiSucesso()){
            List<Cliente> list = (List)r1.comoSucesso().getObj();
            cbCliente.getItems().addAll(list);
        }else{
            Alert alert = new Alert(AlertType.ERROR,r1.getMsg());
            alert.showAndWait();
        }

        Resultado r2 = repositorioEstoque.listarEstoque();

        if(r2.foiSucesso()){
            List<Estoque> list = (List)r2.comoSucesso().getObj();
            cbEstoque.getItems().addAll(list);
        }else{
            Alert alert = new Alert(AlertType.ERROR,r2.getMsg());
            alert.showAndWait();
        }        
    }
}
