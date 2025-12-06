package Lavanderia;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cliente {

    private String nome;
    private String id;
    private String endereco;
    private String telefone;
    private transient List<Pedido> historicoPedidos;
    private LocalDateTime dataCadastro;
    private boolean vip;
    private PlanoAssinatura planoAssinatura;

    public Cliente(String nome, String id) {
        this.nome = Objects.requireNonNull(nome);
        this.id = Objects.requireNonNull(id);
        this.endereco = endereco;
        this.telefone = telefone;
        this.historicoPedidos = new ArrayList<>();
        this.dataCadastro = LocalDateTime.now();
        this.vip = false;
        this.planoAssinatura = null;
    }

    public String getNome() { return nome; }
    public String getId() { return id; }
    public String getEndereco() { return endereco; }
    public String getTelefone() { return telefone; }
    public boolean isVip() { return vip; }
    public PlanoAssinatura getPlanoAssinatura() { return planoAssinatura; }
    public void setPlanoAssinatura(PlanoAssinatura planoAssinatura) {
        this.planoAssinatura = planoAssinatura;
    }
    public List<Pedido> getHistoricoPedidos() {
        if (historicoPedidos == null) historicoPedidos = new ArrayList<>();
        return historicoPedidos;
    }
    public LocalDateTime getDataCadastro() { return dataCadastro; }
    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public void setVip(boolean vip) { this.vip = vip; }

    public void addPedidoAoHistorico(Pedido pedido) {
        if (historicoPedidos == null) historicoPedidos = new ArrayList<>();
        historicoPedidos.add(pedido);
    }

    public void assinarPlano(PlanoAssinatura planoAssinatura) {
        this.planoAssinatura = planoAssinatura;
        this.vip = true;
    }
    public void turnVip() { this.vip = true; }
    public void removeVip() { this.vip = false; }

    @Override
    public String toString() {
        return "Cliente{id='" + id + "', nome='" + nome + "', endereco='" + endereco + "', telefone='" + telefone + "', vip=" + vip + "}";
    }
}
