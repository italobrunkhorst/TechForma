package ifpr.pgua.eic.trabalhofinal.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.trabalhofinal.App;
import ifpr.pgua.eic.trabalhofinal.models.entities.Cliente;
import ifpr.pgua.eic.trabalhofinal.models.repositories.RepositorioCliente;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ListarCliente implements Initializable{

    @FXML
    private TableView<Cliente> tbCliente;

    @FXML
    private TableColumn<Cliente, String> tbcId;

    @FXML
    private TableColumn<Cliente, String> tbcNome;

    @FXML
    private TableColumn<Cliente, String> tbcCpf;

    @FXML
    private TableColumn<Cliente, String> tbcTelefone;

    @FXML
    private TableColumn<Cliente, String> tbcEmail;

    @FXML
    private TableColumn<Cliente, String> tbcPorcentagem;

    @FXML
    private TextField tfNomeDetalhes;

    @FXML
    private TextField tfCpfDetalhes;

    @FXML
    private TextField tfTelefoneDetalhes;

    @FXML
    private TextField tfEmailDetalhes;

    @FXML
    private TextField tfPorcentagemDetalhes;

    private Cliente clienteSelecionado;

    private RepositorioCliente repositorio;

    public ListarCliente(RepositorioCliente repositorio){
        this.repositorio = repositorio;
    }

    @FXML
    void deletarCliente(ActionEvent event) {
        Cliente clienteSelecionado = tbCliente.getSelectionModel().getSelectedItem();

        if (clienteSelecionado == null) {
            Alert alert = new Alert(AlertType.WARNING, "Selecione um cliente para deletar!");
            alert.showAndWait();
            return;
        }

        int idCliente = clienteSelecionado.getId();
        Resultado resultado = repositorio.deletarCliente(idCliente);

        Alert alert;
        if (resultado.foiErro()) {
            alert = new Alert(AlertType.ERROR, resultado.getMsg());
        } else {
            alert = new Alert(AlertType.INFORMATION, resultado.getMsg());
            tbCliente.getItems().remove(clienteSelecionado); // Remove o cliente da tabela
        }

        alert.showAndWait();
    }

    private void preencherCamposTexto(Cliente cliente) {
        tfNomeDetalhes.setText(cliente.getNome());
        tfCpfDetalhes.setText(cliente.getCpf());
        tfTelefoneDetalhes.setText(String.valueOf(cliente.getTelefone()));
        tfEmailDetalhes.setText(cliente.getEmail());
        tfPorcentagemDetalhes.setText(cliente.getPorcentagem());
    }

    @FXML
    void atualizarCliente(ActionEvent event) {
        if (clienteSelecionado != null) {
            // Obter os dados atualizados dos campos do formulário
            String nome = tfNomeDetalhes.getText();
            String cpf = tfCpfDetalhes.getText();
            int telefone = Integer.parseInt(tfTelefoneDetalhes.getText());
            String email = tfEmailDetalhes.getText();
            String porcentagem = tfPorcentagemDetalhes.getText();
    
            // Criar um novo objeto Cliente com os dados atualizados
            Cliente clienteAtualizado = new Cliente(clienteSelecionado.getId(), nome, cpf, telefone, email, porcentagem);
    
            // Chamar o método de atualização no RepositorioCliente
            Resultado resultado = repositorio.alterarCliente(clienteAtualizado);
    
            // Exibir uma mensagem de sucesso ou erro
            Alert alert;
            if (resultado.foiErro()) {
                alert = new Alert(Alert.AlertType.ERROR, resultado.getMsg());
            } else {
                alert = new Alert(Alert.AlertType.INFORMATION, resultado.getMsg());
                // Atualizar a TableView após a atualização
                Resultado resultadoListar = repositorio.listarCliente();
                if (resultadoListar.foiSucesso()) {
                    tbCliente.getItems().clear();
                    List<Cliente> lista = (List<Cliente>) resultadoListar.comoSucesso().getObj();
                    tbCliente.getItems().addAll(lista);
                }
            }
    
            alert.showAndWait();
        }
    }


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        tbCliente.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                preencherCamposTexto(newValue);
                clienteSelecionado = newValue;
            }
        });
        
        tbcId.setCellValueFactory(celula->new SimpleStringProperty(celula.getValue().getId()+""));
        tbcNome.setCellValueFactory(celula->new SimpleStringProperty(celula.getValue().getNome()));
        tbcCpf.setCellValueFactory(celula->new SimpleStringProperty(celula.getValue().getCpf()));
        tbcTelefone.setCellValueFactory(celula->new SimpleStringProperty(celula.getValue().getTelefone()+""));
        tbcEmail.setCellValueFactory(celula->new SimpleStringProperty(celula.getValue().getEmail()));
        tbcPorcentagem.setCellValueFactory(celula->new SimpleStringProperty(celula.getValue().getPorcentagem()));

        Resultado resultado = repositorio.listarCliente();

        if(resultado.foiErro()){
            Alert alert = new Alert(AlertType.ERROR, resultado.getMsg());
            alert.showAndWait();
            return;
        }
        
        List<Cliente> lista = (List)resultado.comoSucesso().getObj();
        tbCliente.getItems().addAll(lista);
    }
    
    @FXML
    void voltar(ActionEvent event) {
        App.popScreen();
    }
}
