package ifpr.pgua.eic.trabalhofinal.models.entities;

import java.time.LocalDate;

public class Estoque {
    
    private int id;
    private String nome;
    private int quantidade;
    private LocalDate dataValidade;
    
    public Estoque(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }
    public Estoque(String nome, int quantidade, LocalDate dataValidade) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.dataValidade = dataValidade;
    }
    public Estoque(int id, String nome, int quantidade, LocalDate dataValidade) {
        this.id = id;
        this.nome = nome;
        this.quantidade = quantidade;
        this.dataValidade = dataValidade;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    public LocalDate getDataValidade() {
        return dataValidade;
    }
    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }
    @Override
    public String toString() {
        return "Estoque [id=" + id + ", nome=" + nome + ", quantidade=" + quantidade + ", dataValidade=" + dataValidade
                + "]";
    }
    
}
