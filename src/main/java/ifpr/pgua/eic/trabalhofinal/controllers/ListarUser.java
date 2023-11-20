package ifpr.pgua.eic.trabalhofinal.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.trabalhofinal.App;
import ifpr.pgua.eic.trabalhofinal.models.entities.User;
import ifpr.pgua.eic.trabalhofinal.models.repositories.RepositorioUser;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ListarUser implements Initializable {

    @FXML
    private TableView<User> tbUser;

    @FXML
    private TableColumn<User, String> tbcId;

    @FXML
    private TableColumn<User, String> tbcNome;

    @FXML
    private TableColumn<User, String> tbcEmail;

    @FXML
    private TableColumn<User, String> tbcMatricula;

    @FXML
    private TableColumn<User, String> tbcSenha;

    @FXML
    private TableColumn<User, String> tbcConfirmasenha;

    private RepositorioUser repositorio;

    public ListarUser(RepositorioUser repositorio){
        this.repositorio = repositorio;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
        tbcId.setCellValueFactory(celula->new SimpleStringProperty(celula.getValue().getId()+""));
        tbcNome.setCellValueFactory(celula->new SimpleStringProperty(celula.getValue().getNome()));
        tbcEmail.setCellValueFactory(celula->new SimpleStringProperty(celula.getValue().getEmail()));
        tbcMatricula.setCellValueFactory(celula->new SimpleStringProperty(celula.getValue().getMatricula()+""));
        //tbcSenha.setCellValueFactory(celula->new SimpleStringProperty(celula.getValue().getSenha()));
        //tbcConfirmasenha.setCellValueFactory(celula->new SimpleStringProperty(celula.getValue().getConfirmasenha()));

        Resultado resultado = repositorio.listarUser();

        if(resultado.foiErro()){
            Alert alert = new Alert(AlertType.ERROR, resultado.getMsg());
            alert.showAndWait();
            return;
        }
    
        List<User> lista = (List)resultado.comoSucesso().getObj();
        tbUser.getItems().addAll(lista);
        
    }

    @FXML
    void voltar(ActionEvent event) {
        App.popScreen();
    }
    
}
