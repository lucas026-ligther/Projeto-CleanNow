package Lavanderia;

public class PlanoAssinatura {
    private String id;
    private String nome;
    private int servicosInclusos;
    private double mensalidade;
    private double desconto;
    private boolean ativo;

    public PlanoAssinatura(String id,String nome, int servicosInclusos, double mensalidade, double desconto, boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.servicosInclusos = servicosInclusos;
        this.mensalidade = mensalidade;
        this.desconto = desconto;
        this.ativo = true;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public int getServicosInclusos() {
        return servicosInclusos;
    }
    public void setServicosInclusos(int servicosInclusos) {
        this.servicosInclusos = servicosInclusos;
    }
    public double getMensalidade() {
        return mensalidade;
    }
    public void setMensalidade(double mensalidade) {
        this.mensalidade = mensalidade;
    }
    public double getDesconto() {
        return desconto;
    }
    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }
    public boolean isAtivo() {
        return ativo;
    }
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public double calcularEconomiaMensal(double gastoMedio) {
        return (gastoMedio * this.desconto) -  mensalidade;
    }
}
