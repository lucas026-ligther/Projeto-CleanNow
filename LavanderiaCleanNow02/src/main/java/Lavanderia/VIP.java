package Lavanderia;

public class VIP {
    private String id;
    private String nome;
    private String beneficios;
    private double mensalidade;

    public VIP(String id, String nome, String beneficios, double mensalidade) {
        this.id = id;
        this.nome = nome;
        this.beneficios = beneficios;
        this.mensalidade = mensalidade;
    }

    @Override
    public String toString() {
        return "VIP{id='" + id + "', nome='" + nome + "', beneficios='" + beneficios + "', mensalidade=" + mensalidade + "}";
    }
}
