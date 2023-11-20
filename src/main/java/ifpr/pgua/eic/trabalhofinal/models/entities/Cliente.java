package ifpr.pgua.eic.trabalhofinal.models.entities;

public class Cliente {
    
    private int id;
    private String nome;
    private String cpf;
    private int telefone;
    private String email;
    private String porcentagem;
    public Cliente(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }
    public Cliente(String nome, String cpf, int telefone, String email, String porcentagem) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
        this.porcentagem = porcentagem;
    }
    public Cliente(int id, String nome, String cpf, int telefone, String email, String porcentagem) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
        this.porcentagem = porcentagem;
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
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public int getTelefone() {
        return telefone;
    }
    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPorcentagem() {
        return porcentagem;
    }
    public void setPorcentagem(String porcentagem) {
        this.porcentagem = porcentagem;
    }
    @Override
    public String toString() {
        return "Cliente [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", telefone=" + telefone + ", email=" + email
                + ", porcentagem=" + porcentagem + "]";
    }
    

    
}
