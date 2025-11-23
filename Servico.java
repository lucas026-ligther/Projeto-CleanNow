package Lavanderia;
import java.util.Objects;

public class Servico {
    private String id;
    private String nome;
    private String descricao;
    private double preço;
    private int prazoEntregaHoras;
    private boolean disponivel;

    public Servico(String id, String nome, String descricao, double preço, int prazoEntregaHoras) {
        this.id = Objects.requireNonNull(id);
        this.nome = Objects.requireNonNull(nome);
        this.descricao = descricao;
        this.preço = preço;
        this.prazoEntregaHoras = prazoEntregaHoras;
        this.disponivel = true;
    }
    public String getId() { return id; }
    public String getNome() { return nome; }
    public String getDescricao() { return descricao; }
    public double getPreco() { return preço; }
    public int getPrazoEntregaHoras() { return prazoEntregaHoras; }
    public boolean isDisponivel() { return disponivel; }

    public String getTipo(){return nome;}

    public String toString() {
        return "Servico{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", preco=" + preço +
                ", prazoEntregaHoras=" + prazoEntregaHoras +
                '}';
    }
}

