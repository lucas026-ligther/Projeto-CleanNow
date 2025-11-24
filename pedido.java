package Lavanderia;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private String id;
    private Cliente cliente;
    private List<Servico> servicos;
    private String status;
    private LocalDateTime dataColeta;
    private LocalDateTime dataEntrega;
    private String enderecoColeta;
    private double valorTotal;
    private Pagamento pagamento;
    private Pacote pacote;
    private PlanoAssinatura planoAssinatura;

    public void adicionarPacote(Pacote pacote) {
        this.pacote = pacote;
        calcularValor();
    }

    public Pedido(String id, Cliente cliente) {
        this.id = id;
        this.cliente = cliente;
        this.servicos = new ArrayList<>();
        this.status = "AGENDADO";
        this.valorTotal = 0.0;
        cliente.addPedidoAoHistorico(this);
    }

    public String getStatus() { return status; }
    public double getValorTotal() { return valorTotal; }

    public void adicionarServico(Servico servico) {
        servicos.add(servico);
        calcularValor();
    }

    private void calcularValor() {
        double total = 0;

        for (Servico s : servicos) {
            total += s.getPreco();
        }
        if (pacote != null) {
            total = pacote.getPreco() + total;
        }

        if (cliente.isVip()) total *= 0.9; // 10% off
        if (planoAssinatura != null) {
            total *= (1 - planoAssinatura.getDesconto());
        }
        valorTotal = total;
    }

    public void atualizarStatus(String novoStatus) {
        status = novoStatus;
        System.out.println("Pedido " + id + " atualizado para: " + novoStatus);
    }

    public void agendarColeta(String endereco, LocalDateTime data) {
        this.enderecoColeta = endereco;
        this.dataColeta = data;
    }

    public void agendarEntrega(LocalDateTime data) {
        this.dataEntrega = data;
    }

    public void realizarPagamento(String metodo) {
        pagamento = new Pagamento(valorTotal, metodo);
        pagamento.confirmar();
    }

    public Pagamento getPagamento() {
        return pagamento;
    }
}

