package Lavanderia;

import java.time.LocalDateTime;
import java.util.Objects;

public class Servico {
    private String id;
    private String nome;
    private String descricao;
    private double preco;
    private boolean disponivel;
    private LocalDateTime dataEntrega;

    public Servico(String id, String nome, String descricao, double preco, int prazoHoras) {
        this.id = Objects.requireNonNull(id);
        this.nome = Objects.requireNonNull(nome);
        this.descricao = descricao;
        this.preco = preco;
        this.disponivel = true;
        this.dataEntrega = LocalDateTime.now().plusHours(Math.max(0, prazoHoras));
    }

    public String getId() { return id; }
    public String getNome() { return nome; }
    public String getDescricao() { return descricao; }
    public double getPreco() { return preco; }
    public boolean isDisponivel() { return disponivel; }
    public LocalDateTime getDataEntrega() { return dataEntrega; }

    @Override
    public String toString() {
        return nome + " (id=" + id + ", R$" + preco + ")";
    }
}



