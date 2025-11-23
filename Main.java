import Lavanderia.Cliente;
import Lavanderia.Servico;
import Lavanderia.Pedido;
import java.util.Date;

public class CleanNowSystem{
    public static void main(String[] args){
System.out.println("===Sistema Clean Now===");

        Lavanderia.Cliente cliente1 = new Cliente( "João", "00001", "quadra XX, casa YY", "1199999-9999");
        Lavanderia.Servico lavagem = new Servico("001", "Lavagem", "Uma lavagem simples", 30.00, "Entrega em uma hora");
        Lavanderia.Servico secagem = new Servico("002", "Secagem", "Secagem basica de roupas", 15.00, "Entrega em meia hora");

Pedido pedido1 = new Pedido(cliente1, secagem, new Date());

        System.out.println("Clientes:" +cliente1.getNome());
        System.out.println("Serviço:" + secagem.getTipo());
        System.out.println("Valor: R$" +pedido1.getValorTotal());
        System.out.println("Status:" +pedido1.getStatus());

        pedido1.atualizarStatus("COLETADO");
    }
}
