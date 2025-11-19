public class Servico {
    private String id;
    private String nome;
    private String descricao;
    private double preco;
    private int prazoEntrega;
    private boolean disponivel;

    public Servico(String id, String nome, String descricao, double preco, int prazoEntrega) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.prazoEntrega = prazoEntrega;
    }
}
