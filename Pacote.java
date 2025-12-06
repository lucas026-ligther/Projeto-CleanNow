package Lavanderia;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Pacote {
    private int id;
    private String nome;
    private String descricao;
    private List<Servico> servicosInclusos;
    private double preco;
    private boolean isVip;
    private int prioridade;
    private LocalDateTime data;

    public Pacote(int id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.servicosInclusos = new ArrayList<>();
        this.isVip = false;
        this.prioridade = 1;
        this.data = LocalDateTime.now();
    }

    public double getPreco() { return preco; }
    public void setPreco(double preco) { this.preco = preco; }
    public void addServico(Servico servico) { this.servicosInclusos.add(servico); }
    public void removeServico(Servico servico) { this.servicosInclusos.remove(servico); }

    public double calcularEconomia() {
        double totalIndividual = 0;
        for (Servico servico : servicosInclusos) totalIndividual += servico.getPreco();
        return totalIndividual - preco;
    }

    @Override
    public String toString() {
        return "Pacote{" + nome + ", preco=R$" + preco + "}";
    }
}
