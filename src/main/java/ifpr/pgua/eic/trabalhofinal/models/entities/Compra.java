package ifpr.pgua.eic.trabalhofinal.models.entities;

public class Compra {
    
    private int id;
    private Cliente cliente;
    private Estoque estoque;
    
    public Compra(Cliente cliente, Estoque estoque) {
        this.cliente = cliente;
        this.estoque = estoque;
    }

    public Compra(int id, Cliente cliente, Estoque estoque) {
        this.id = id;
        this.cliente = cliente;
        this.estoque = estoque;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Estoque getEstoque() {
        return estoque;
    }

    public void setEstoque(Estoque estoque) {
        this.estoque = estoque;
    }

    @Override
    public String toString() {
        return "Compra [id=" + id + ", cliente=" + cliente.getNome() + ", estoque=" + estoque.getNome() + "]";
    }

    
}
