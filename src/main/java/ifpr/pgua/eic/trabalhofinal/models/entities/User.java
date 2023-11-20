package ifpr.pgua.eic.trabalhofinal.models.entities;

public class User {
    
    private int id;
    private String nome;
    private String email;
    private int matricula;
    private String senha;
    private String confirmasenha;
    public User(int matricula, String senha) {
        this.matricula = matricula;
        this.senha = senha;
    }
    public User(String nome, String email, int matricula, String senha, String confirmasenha) {
        this.nome = nome;
        this.email = email;
        this.matricula = matricula;
        this.senha = senha;
        this.confirmasenha = confirmasenha;
    }
    public User(int id, String nome, String email, int matricula, String senha, String confirmasenha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.matricula = matricula;
        this.senha = senha;
        this.confirmasenha = confirmasenha;
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
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public int getMatricula() {
        return matricula;
    }
    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public String getConfirmasenha() {
        return confirmasenha;
    }
    public void setConfirmasenha(String confirmasenha) {
        this.confirmasenha = confirmasenha;
    }
    

    
}
