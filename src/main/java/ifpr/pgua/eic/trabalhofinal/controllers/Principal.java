package ifpr.pgua.eic.trabalhofinal.controllers;

import ifpr.pgua.eic.trabalhofinal.App;
import javafx.fxml.FXML;

public class Principal {

    @FXML
    private void principal(){
        App.pushScreen("PRINCIPAL");
    }
    
    @FXML
    private void cadastrarUser(){
        App.pushScreen("CADASTROUSER");
    }

    @FXML
    private void logaUser(){
        App.pushScreen("LOGINUSER");
    }

    @FXML
    private void secundaria(){
        App.pushScreen("SECUNDARIA");
    }

    @FXML
    private void listarUser(){
        App.pushScreen("LISTARUSER");
    }

    @FXML
    private void cadastrarCliente(){
        App.pushScreen("CADASTROCLIENTE");
    }

    @FXML
    private void listarCliente(){
        App.pushScreen("LISTARCLIENTE");
    }

    @FXML
    private void cadastrarEstoque(){
        App.pushScreen("CADASTROESTOQUE");
    }

    @FXML
    private void listarEstoque(){
        App.pushScreen("LISTARESTOQUE");
    }

    @FXML
    private void cadastrarComprar(){
        App.pushScreen("CADASTROCOMPRAR");
    }

    @FXML
    private void listarCompra(){
        App.pushScreen("LISTARCOMPRA");
    }
}
