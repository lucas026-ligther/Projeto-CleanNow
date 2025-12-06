package Lavanderia;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Pedido {

    private final String id;

    private transient Cliente cliente;
    private transient List<Servico> servicos;

    private String clienteId;
    private List<String> servicoIds;

    private StatusPedido status;
    private LocalDateTime dataColeta;
    private LocalDateTime dataEntrega;
    private String enderecoColeta;
    private double valorTotal;
    private Pacote pacote;
    private PlanoAssinatura planoAssinatura;
    private transient Pagamento pagamento;

    public Pedido(String id, Cliente cliente, String enderecoColeta) {
        this.id = Objects.requireNonNull(id);
        this.cliente = cliente;
        this.clienteId = cliente != null ? cliente.getId() : null;
        this.servicos = new ArrayList<>();
        this.servicoIds = new ArrayList<>();
        this.status = StatusPedido.AGUARDANDO_PAGAMENTO;
        this.dataColeta = LocalDateTime.now();
        this.enderecoColeta = enderecoColeta;
        this.valorTotal = 0.0;
        this.pacote = null;
        this.planoAssinatura = null;
        this.pagamento = null;
        if (cliente != null) cliente.addPedidoAoHistorico(this);
    }

    public String getId() { return id; }
    public String getClienteId() { return clienteId; }
    public void setClienteId(String clienteId) { this.clienteId = clienteId; }
    public List<String> getServicoIds() { return servicoIds; }
    public void setServicoIds(List<String> servicoIds) { this.servicoIds = servicoIds; }

    public void setClienteReference(Cliente c) {
        this.cliente = c;
        this.clienteId = c != null ? c.getId() : null;
    }
    public void setServicosReference(List<Servico> servicosRef) {
        this.servicos = servicosRef;
        this.servicoIds = new ArrayList<>();
        if (servicosRef != null) for (Servico s : servicosRef) this.servicoIds.add(s.getId());
    }

    public Cliente getCliente() { return cliente; }
    public List<Servico> getServicos() { return servicos; }
    public double getValorTotal() { return valorTotal; }
    public StatusPedido getStatus() { return status; }

    public void adicionarServico(Servico servico) {
        if (servico == null) return;
        if (servicos == null) servicos = new ArrayList<>();
        servicos.add(servico);
        if (servicoIds == null) servicoIds = new ArrayList<>();
        servicoIds.add(servico.getId());
        calcularValor();
    }

    public void adicionarPacote(Pacote pacote) {
        this.pacote = pacote;
        calcularValor();
    }

    public void setPlanoAssinatura(PlanoAssinatura plano) {
        this.planoAssinatura = plano;
        calcularValor();
    }

    public void calcularValor() {
        double total = 0.0;
        if (servicos != null) {
            for (Servico s : servicos) if (s != null) total += s.getPreco();
        }
        if (this.pacote != null) total += this.pacote.getPreco();

        if (cliente != null && cliente.isVip()) total -= total * 0.10; // 10% off

        PlanoAssinatura plano = (this.planoAssinatura != null) ? this.planoAssinatura : (cliente != null ? cliente.getPlanoAssinatura() : null);
        if (plano != null && plano.isAtivo()) total -= total * plano.getDesconto();

        if (total < 0) total = 0;
        this.valorTotal = Math.round(total * 100.0) / 100.0;
    }

    public boolean pagarPedido(String metodo) {
        if (this.status == StatusPedido.PAGO) {
            System.out.println("Pedido já está pago.");
            return false;
        }
        calcularValor();
        if (this.valorTotal <= 0) {
            System.out.println("Valor inválido, não é possível pagar.");
            return false;
        }
        this.pagamento = new Pagamento(this.valorTotal, metodo);
        this.pagamento.confirmar();
        this.status = StatusPedido.PAGO;
        this.dataEntrega = LocalDateTime.now().plusDays(3);
        return true;
    }

    public void atualizarStatus(StatusPedido novo) {
        this.status = novo;
    }

    @Override
    public String toString() {
        String clienteStr = cliente != null ? cliente.getNome() + " (id=" + cliente.getId() + ")" : "clienteId=" + clienteId;
        int itens = servicos != null ? servicos.size() : (servicoIds != null ? servicoIds.size() : 0);
        return "Pedido{id=" + id + ", cliente=" + clienteStr + ", itens=" + itens + ", valor=R$" + valorTotal + ", status=" + status + "}";
    }
}
