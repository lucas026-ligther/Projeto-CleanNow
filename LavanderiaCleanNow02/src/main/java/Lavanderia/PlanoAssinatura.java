package Lavanderia;

public class PlanoAssinatura {
    private String id;
    private String nome;
    private int servicosInclusos;
    private double mensalidade;
    private double desconto; // ex: 0.10 = 10%
    private boolean ativo;

    public PlanoAssinatura(String id, double mensalidade, double desconto) {
        this.id = id;
        this.nome = nome;
        this.servicosInclusos = servicosInclusos;
        this.mensalidade = mensalidade;
        this.desconto = desconto;
        this.ativo = true;
    }

    public String getId() { return id; }
    public double getMensalidade() { return mensalidade; }
    public double getDesconto() { return desconto; }
    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }

    public double calcularEconomiaMensal(double gastoMedio) {
        double comDesconto = gastoMedio * (1 - this.desconto);
        return gastoMedio - (comDesconto + mensalidade);
    }

    @Override
    public String toString() {
        return nome + " (desconto=" + (desconto*100) + "%, mensalidade=R$" + mensalidade + ")";
    }
}
