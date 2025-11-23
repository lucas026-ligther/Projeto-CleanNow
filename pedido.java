import Lavanderia.Cliente;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Pedido {
    private final String id;
    private Lavanderia.Cliente cliente;
    private List<Lavanderia.Servico> servicos;
    private String status;
    private LocalDateTime dataColeta;
    private LocalDateTime dataEntrega;
    private String enderecoColeta;
    private String observacoes;
    private double valorTotal;

    public Pedido(String id, Lavanderia.Cliente cliente, List<Lavanderia.Servico> servicos, String status, String enderecoColeta, String observacoes, double valorTotal) {
        this.id = Objects.requireNonNull(id, "ID não pode ser nulo");
        this.cliente = Objects.requireNonNull(cliente, "Cliente não pode ser nulo");;
        this.servicos = Objects.requireNonNull(servicos, "Os serviços não podem ser nulos");;
        this.enderecoColeta = Objects.requireNonNull(enderecoColeta, "O endereço de coleta não pode ser nulo");;
        this.observacoes = observacoes != null ? observacoes : "";
        this.status = "AGENDADO";
        this.valorTotal = calcularValorTotal();
    }
    public String getId() {
        return id;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public List<Lavanderia.Servico> getServicos() {
        return List.copyOf(servicos);
    }
    public String getStatus() {
        return status;
    }
    public LocalDateTime getDataColeta() {
        return dataColeta;
    }
    public LocalDateTime getDataEntrega() {
        return dataEntrega;
    }
    public String getEnderecoColeta() {
        return enderecoColeta;
    }
    public String getObservacoes() {
        return observacoes;
    }
    public double getValorTotal() {
        return valorTotal;
    }
    public void setCliente(Cliente cliente) {
        this.cliente =  Objects.requireNonNull(cliente, "Cliente não pode ser nulo");
        this.valorTotal = calcularValorTotal();
    }
    public void setServicos(List<Lavanderia.Servico> servicos) {
        this.servicos = Objects.requireNonNull(servicos, "Os serviços não podem ser nulos");;
    }
    public void setStatus(String status) {
        if (status == null || status.trim().isEmpty()) {
            throw new IllegalArgumentException("O status nao pode ser vazio ou nulo");
        }
        this.status = status;
    }
    public void setDataColeta(LocalDateTime dataColeta) {
        if (dataColeta == null || dataColeta.isBefore(LocalDateTime.now())){
            throw new IllegalArgumentException("A data de coleta não pode ser no passado");
        };
        this.dataColeta = dataColeta;
    }
    public void setDataEntrega(LocalDateTime dataEntrega) {
        if ( dataEntrega == null && dataColeta != null && dataEntrega.isBefore(dataColeta)){
            throw new IllegalArgumentException("A data de entrega deve ser após a data de coleta");
        }
        this.dataEntrega = dataEntrega;
    }
    public void setEnderecoColeta(String enderecoColeta) {
        this.enderecoColeta = Objects.requireNonNull(enderecoColeta, "O endereço da coleta não pode ser nulo");;
    }
    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes != null ? observacoes : "";
    }

    public void atualizarStatus(String novoStatus){
        this.status = novoStatus;
        System.out.println("Pedido" + id + "atualizado para " + novoStatus +"!");
    }

    private double calcularValorTotal(){
        if (servicos == null || servicos.isEmpty()){
            return 0.0;
        }
        double valorBase = servicos.stream(); //MODIFICAR ESSA PARTE
        .mapToDouble(Servico::getValor)
                .sum();

        if(Cliente.isVip()){
            return valorBase * 0.9;
        }
        return valorBase;
    }
    public void adicionarServico(Servico servico){
        Objects.requireNonNull(servico,"Os serviços não podem ser nulos");
                this.servicos.add(servico);
        this.valorTotal += calcularValorTotal();
    }
    public void removerServico(Servico servico){
        this.servicos.remove(servico);
        this.valorTotal -= calcularValorTotal();
    }
}

