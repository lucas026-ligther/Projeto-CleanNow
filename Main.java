package Lavanderia;

import java.time.LocalDateTime;

public class CleanNowSystem {
    public static void main(String[] args) {

        System.out.println("=== Sistema CleanNow ===\n");

        Cliente cliente1 = new Cliente("João", "00001", "quadra XX, casa YY", "1199999-9999");

        cliente1.addPreferencia("Lavagem rápida");
        cliente1.addPreferencia("Temperatura média");

        Servico lavagem = new Servico("001", "Lavagem", "Uma lavagem simples", 30.00, 1);
        Servico secagem = new Servico("002", "Secagem", "Secagem básica", 15.00, 1);

        Pedido pedido1 = new Pedido("P001", cliente1);
        pedido1.adicionarServico(lavagem);
        pedido1.adicionarServico(secagem);

        pedido1.agendarColeta(cliente1.getEndereco(), LocalDateTime.now().plusHours(1));
        pedido1.agendarEntrega(LocalDateTime.now().plusHours(5));

        pedido1.realizarPagamento("Cartão");

        System.out.println("Cliente: " + cliente1.getNome());
        System.out.println("Serviços no pedido: Lavagem, Secagem");
        System.out.println("Valor total: R$ " + pedido1.getValorTotal());
        System.out.println("Método de Pagamento: " + pedido1.getPagamento().getMetodo());
        System.out.println("Pagamento confirmado: " + pedido1.getPagamento().isConfirmado());
        System.out.println("Status inicial: " + pedido1.getStatus());

        pedido1.atualizarStatus("COLETADO");
        pedido1.atualizarStatus("EM PROCESSAMENTO");
        pedido1.atualizarStatus("PRONTO");
        pedido1.atualizarStatus("ENTREGUE");

        System.out.println("\nHistórico de pedidos do cliente:");
        for (Pedido p : cliente1.getHistoricoPedidos()) {
            System.out.println("- Pedido " + p.getValorTotal() + " | Status final: " + p.getStatus());
        }
    }
}
