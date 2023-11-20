package ifpr.pgua.eic.trabalhofinal.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.trabalhofinal.App;
import ifpr.pgua.eic.trabalhofinal.models.entities.Cliente;
import ifpr.pgua.eic.trabalhofinal.models.entities.Compra;
import ifpr.pgua.eic.trabalhofinal.models.repositories.RepositorioCompra;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ListarCompra implements Initializable {

    @FXML
    private TableView<Compra> tbCompra;

    @FXML
    private TableColumn<Compra, String> tbcId;

    @FXML
    private TableColumn<Compra, String> tbcCliente;

    @FXML
    private TableColumn<Compra, String> tbcEstoque;

    private RepositorioCompra repositorioCompra;

    public ListarCompra(RepositorioCompra repositorioCompra){
        this.repositorioCompra = repositorioCompra;
    }

    @FXML
    void voltar(ActionEvent event) {
        App.popScreen();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
        tbcId.setCellValueFactory(celula->new SimpleStringProperty(celula.getValue().getId()+""));
        tbcCliente.setCellValueFactory(celula->new SimpleStringProperty(celula.getValue().getCliente().getNome()));
        tbcEstoque.setCellValueFactory(celula->new SimpleStringProperty(celula.getValue().getEstoque().getNome()));

        Resultado resultado = repositorioCompra.listarCompra();

        if(resultado.foiErro()){
            Alert alert = new Alert(AlertType.ERROR, resultado.getMsg());
            alert.showAndWait();
            return;
        }

        List<Compra> lista = (List)resultado.comoSucesso().getObj();
        tbCompra.getItems().addAll(lista);
    }

    @FXML
    void deletarCompra(ActionEvent event) {
        Compra compraSelecionado = tbCompra.getSelectionModel().getSelectedItem();

        if (compraSelecionado == null) {
            Alert alert = new Alert(AlertType.WARNING, "Selecione uma compra para deletar!");
            alert.showAndWait();
            return;
        }

        int idCompra = compraSelecionado.getId();
        Resultado resultado = repositorioCompra.deletarCompra(idCompra);

        Alert alert;
        if (resultado.foiErro()) {
            alert = new Alert(AlertType.ERROR, resultado.getMsg());
        } else {
            alert = new Alert(AlertType.INFORMATION, resultado.getMsg());
            tbCompra.getItems().remove(compraSelecionado);
        }

        alert.showAndWait();
    }
    
}
