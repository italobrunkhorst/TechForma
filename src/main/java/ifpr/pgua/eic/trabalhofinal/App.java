package ifpr.pgua.eic.trabalhofinal;

import ifpr.pgua.eic.trabalhofinal.controllers.CadastroCliente;
import ifpr.pgua.eic.trabalhofinal.controllers.CadastroCompra;
import ifpr.pgua.eic.trabalhofinal.controllers.CadastroEstoque;
import ifpr.pgua.eic.trabalhofinal.controllers.CadastroUser;
import ifpr.pgua.eic.trabalhofinal.controllers.ListarCliente;
import ifpr.pgua.eic.trabalhofinal.controllers.ListarCompra;
import ifpr.pgua.eic.trabalhofinal.controllers.ListarEstoque;
import ifpr.pgua.eic.trabalhofinal.controllers.ListarUser;
import ifpr.pgua.eic.trabalhofinal.controllers.LoginUser;
import ifpr.pgua.eic.trabalhofinal.controllers.Principal;
import ifpr.pgua.eic.trabalhofinal.models.daos.ClienteDAO;
import ifpr.pgua.eic.trabalhofinal.models.daos.CompraDAO;
import ifpr.pgua.eic.trabalhofinal.models.daos.EstoqueDAO;
import ifpr.pgua.eic.trabalhofinal.models.daos.FabricaConexoes;
import ifpr.pgua.eic.trabalhofinal.models.daos.JDBCClienteDAO;
import ifpr.pgua.eic.trabalhofinal.models.daos.JDBCCompraDAO;
import ifpr.pgua.eic.trabalhofinal.models.daos.JDBCEstoqueDAO;
import io.github.hugoperlin.navigatorfx.BaseAppNavigator;
import io.github.hugoperlin.navigatorfx.ScreenRegistryFXML;
import ifpr.pgua.eic.trabalhofinal.models.daos.JDBCUserDAO;
import ifpr.pgua.eic.trabalhofinal.models.daos.UserDAO;
import ifpr.pgua.eic.trabalhofinal.models.repositories.RepositorioCliente;
import ifpr.pgua.eic.trabalhofinal.models.repositories.RepositorioCompra;
import ifpr.pgua.eic.trabalhofinal.models.repositories.RepositorioEstoque;
import ifpr.pgua.eic.trabalhofinal.models.repositories.RepositorioUser;

public class App extends BaseAppNavigator{

    private UserDAO userDAO = new JDBCUserDAO(FabricaConexoes.getInstance());
    private RepositorioUser repositorioUser = new RepositorioUser(userDAO);
    private ClienteDAO clienteDAO = new JDBCClienteDAO(FabricaConexoes.getInstance());
    private RepositorioCliente repositorioCliente = new RepositorioCliente(clienteDAO);
    private EstoqueDAO estoqueDAO = new JDBCEstoqueDAO(FabricaConexoes.getInstance());
    private RepositorioEstoque repositorioEstoque = new RepositorioEstoque(estoqueDAO);
    private CompraDAO compraDAO = new JDBCCompraDAO(FabricaConexoes.getInstance());
    private RepositorioCompra repositorioCompra = new RepositorioCompra(compraDAO);
    
    public static void main(String[] args) {
        launch();
    }

    @Override
    public String getHome() {
        // TODO Auto-generated method stub
        return "PRINCIPAL";
    }

    @Override
    public String getAppTitle() {
        // TODO Auto-generated method stub
        return "Formula Animal";
    }

    @Override
    public void registrarTelas(){
        registraTela("PRINCIPAL", new ScreenRegistryFXML(App.class, "principal.fxml", o->new Principal()));
        registraTela("CADASTROUSER", new ScreenRegistryFXML(App.class, "cadastrar_user.fxml", o->new CadastroUser(repositorioUser)));
        registraTela("LOGINUSER", new ScreenRegistryFXML(App.class, "logar_user.fxml", o->new LoginUser(repositorioUser)));
        registraTela("SECUNDARIA", new ScreenRegistryFXML(App.class, "secundaria.fxml", o->new Principal()));
        registraTela("LISTARUSER", new ScreenRegistryFXML(App.class, "listar_user.fxml", o->new ListarUser(repositorioUser)));
        registraTela("CADASTROCLIENTE", new ScreenRegistryFXML(App.class, "cadastrar_cliente.fxml", o->new CadastroCliente(repositorioCliente)));
        registraTela("LISTARCLIENTE", new ScreenRegistryFXML(App.class, "listar_cliente.fxml", o->new ListarCliente(repositorioCliente)));
        registraTela("CADASTROESTOQUE", new ScreenRegistryFXML(App.class, "cadastrar_estoque.fxml", o->new CadastroEstoque(repositorioEstoque)));
        registraTela("LISTARESTOQUE", new ScreenRegistryFXML(App.class, "listar_estoque.fxml", o->new ListarEstoque(repositorioEstoque)));
        registraTela("CADASTROCOMPRAR", new ScreenRegistryFXML(App.class, "cadastrar_compra.fxml", o->new CadastroCompra(repositorioCompra, repositorioCliente, repositorioEstoque)));
        registraTela("LISTARCOMPRA", new ScreenRegistryFXML(App.class, "listar_compra.fxml", o->new ListarCompra(repositorioCompra)));

    }

}
