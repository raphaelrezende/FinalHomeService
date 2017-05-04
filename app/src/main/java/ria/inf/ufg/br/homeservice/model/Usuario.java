package ria.inf.ufg.br.homeservice.model;

/**
 * Created by raphael on 02/05/17.
 */

public class Usuario {

    private int id;
    private String email;
    private String senha;
    private String nome;
    private String cidade;

    public Usuario(String email, String senha, String nome, String cidade) {
        this.email = email;
        this.senha = senha;
        this.nome = nome;
        this.cidade = cidade;
    }

    public Usuario() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
