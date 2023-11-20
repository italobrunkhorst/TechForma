package ifpr.pgua.eic.trabalhofinal.controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.trabalhofinal.App;
import ifpr.pgua.eic.trabalhofinal.models.entities.Estoque;
import ifpr.pgua.eic.trabalhofinal.models.repositories.RepositorioEstoque;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ListarEstoque implements Initializable {

    @FXML
    private TableView<Estoque> tbEstoque;

    @FXML
    private TableColumn<Estoque, String> tbcId;

    @FXML
    private TableColumn<Estoque, String> tbcNome;

    @FXML
    private TableColumn<Estoque, String> tbcQuantidade;

    @FXML
    private TableColumn<Estoque, LocalDate> tbcData;

    private RepositorioEstoque repositorio;

    public ListarEstoque(RepositorioEstoque repositorio){
        this.repositorio = repositorio;
    }

    @FXML
    void voltar(ActionEvent event) {
        App.popScreen();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
        tbcId.setCellValueFactory(celula->new SimpleStringProperty(celula.getValue().getId()+""));
        tbcNome.setCellValueFactory(celula->new SimpleStringProperty(celula.getValue().getNome()));
        tbcQuantidade.setCellValueFactory(celula->new SimpleStringProperty(celula.getValue().getQuantidade()+""));
        tbcData.setCellValueFactory(celula->{LocalDate data = celula.getValue().getDataValidade();
            return new SimpleObjectProperty<LocalDate>(data);
        });

        Resultado resultado = repositorio.listarEstoque();

        if(resultado.foiErro()){
            Alert alert = new Alert(AlertType.ERROR, resultado.getMsg());
            alert.showAndWait();
            return;
        }

        List<Estoque> lista = (List)resultado.comoSucesso().getObj();
        tbEstoque.getItems().addAll(lista);
    }

    @FXML
    void deletarEstoque(ActionEvent event){
        Estoque estoqueSelecionado = tbEstoque.getSelectionModel().getSelectedItem();

        if (estoqueSelecionado == null) {
            Alert alert = new Alert(AlertType.WARNING, "Selecione um cliente para deletar!");
            alert.showAndWait();
            return;
        }

        int idEstoque = estoqueSelecionado.getId();
        Resultado resultado = repositorio.deletarEstoque(idEstoque);

        Alert alert;
        if (resultado.foiErro()) {
            alert = new Alert(AlertType.ERROR, resultado.getMsg());
        } else {
            alert = new Alert(AlertType.INFORMATION, resultado.getMsg());
            tbEstoque.getItems().remove(estoqueSelecionado); // Remove o cliente da tabela
        }

        alert.showAndWait();
    }
    
}
