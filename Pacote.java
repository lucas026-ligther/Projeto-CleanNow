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
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public List<Servico> getServicosInclusos() {
        return servicosInclusos;
    }
    public void setServicosInclusos(List<Servico> servicosInclusos) {
        this.servicosInclusos = servicosInclusos;
    }
    public double getPreco() {
        return preco;
    }
    public void setPreco(double preco) {
        this.preco = preco;
    }
    public boolean isVip() {
        return isVip;
    }
    public void setVip(boolean vip) {
        isVip = vip;
    }
    public int getPrioridade() {
        return prioridade;
    }
    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }

    public void addServico(Servico servico) {
        this.servicosInclusos.add(servico);
    }
    public void removeServico(Servico servico) {
        this.servicosInclusos.remove(servico);
    }

    public double caucularEconomia(){
double totalIndividual = 0;
for(Servico servico : servicosInclusos){
    totalIndividual += servico.getPreco();
}
return totalIndividual - preco;
    }
}
