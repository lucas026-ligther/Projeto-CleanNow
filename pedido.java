import java.time.LocalDateTime;
import java.util.List;

public class pedido {
    private String id;
    private Cliente cliente;
    private List<Servico> servicos;
    private String status;
    private LocalDateTime dataColeta;
    private LocalDateTime dataEntrega;
    private LocalDateTime dataPrevisao;
    private String enderecoColeta;
    private String observacoes;
    private double valorTotal;

    public void atualizarStatus(String novoStatus){
        this.status = novoStatus;
    }
}
