package Lavanderia;

public class Servico {

    private String id;
    private String nome;
    private String descricao;
    private double preco;
    private int prazoHoras;

    public Servico(String id, String nome, String descricao, double preco, int prazoHoras) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.prazoHoras = prazoHoras;
    }

    public String getNome() { return nome; }
    public double getPreco() { return preco; }
    public String getTipo() { return nome; }
}


