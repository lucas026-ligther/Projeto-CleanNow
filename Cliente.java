package Lavanderia;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Cliente {

    private String nome;
    private String id;
    private String endereco;
    private String telefone;
    private List<String> preferencias;
    private List<Pedido> historicoPedidos;
    private LocalDateTime dataCadastro;
    private boolean vip;
    private PlanoAssinatura planoAssinatura;

    public Cliente(String nome, String id, String endereco, String telefone) {
        this.nome = nome;
        this.id = id;
        this.endereco = endereco;
        this.telefone = telefone;
        this.preferencias = new ArrayList<>();
        this.historicoPedidos = new ArrayList<>();
        this.dataCadastro = LocalDateTime.now();
        this.vip = false;
    }

    public String getNome() { return nome; }
    public String getEndereco() { return endereco; }
    public String getTelefone() { return telefone; }
    public boolean isVip() { return vip; }
    public void setVip(boolean vip) { this.vip = vip; }

    public void addPreferencia(String p) {
        preferencias.add(p);
    }

    public List<String> getPreferencias() {
        return preferencias;
    }

    public void addPedidoAoHistorico(Pedido pedido) {
        historicoPedidos.add(pedido);
    }

    public List<Pedido> getHistoricoPedidos() {
        return historicoPedidos;
    }
    public void assinarPlano(PlanoAssinatura planoAssinatura) {
        this.planoAssinatura = planoAssinatura;
        this.vip = true;
    }
}
