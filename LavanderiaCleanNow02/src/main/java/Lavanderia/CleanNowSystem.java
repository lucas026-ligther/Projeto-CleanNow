package Lavanderia;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class CleanNowSystem {

    public static void main(String[] args) {
        ClienteRepository clienteRepo = new ClienteRepository(new File("clientes.json"));
        ServicoRepository servicoRepo = new ServicoRepository(new File("servicos.json"));
        PedidoRepository pedidoRepo = new PedidoRepository(new File("pedidos.json"), clienteRepo, servicoRepo);

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== CleanNow System ===");
            System.out.println("1) Criar cliente");
            System.out.println("2) Listar clientes");
            System.out.println("3) Criar serviço");
            System.out.println("4) Listar serviços");
            System.out.println("5) Criar pedido");
            System.out.println("6) Listar pedidos");
            System.out.println("7) Pagar pedido");
            System.out.println("8) Atualizar status do pedido");
            System.out.println("9) Excluir pedido");
            System.out.println("10) Cadastrar Vip");
            System.out.println("0) Sair");
            System.out.print("Escolha: ");
            String opt = sc.nextLine().trim();

            switch (opt) {
                case "1" -> {
                    System.out.print("ID cliente: ");
                    String id = sc.nextLine().trim();
                    System.out.print("Nome: ");
                    String nome = sc.nextLine().trim();
                    System.out.print("Endereco: ");
                    String endereco = sc.nextLine().trim();
                    System.out.print("Telefone: ");
                    String tel = sc.nextLine().trim();
                    Cliente c = new Cliente(nome, id);
                    clienteRepo.save(c);
                    System.out.println("Cliente criado: " + c);
                }
                case "2" -> {
                    clienteRepo.findAll().forEach(System.out::println);
                }
                case "3" -> {
                    System.out.print("ID servico: ");
                    String sid = sc.nextLine().trim();
                    System.out.print("Nome servico: ");
                    String sn = sc.nextLine().trim();
                    System.out.print("Descricao: ");
                    String sd = sc.nextLine().trim();
                    System.out.print("Preco: ");
                    double sp = Double.parseDouble(sc.nextLine().trim());
                    System.out.print("Prazo (horas): ");
                    int prazo = Integer.parseInt(sc.nextLine().trim());
                    Servico s = new Servico(sid, sn, sd, sp, prazo);
                    servicoRepo.save(s);
                    System.out.println("Servico criado: " + s);
                }
                case "4" -> servicoRepo.findAll().forEach(System.out::println);
                case "5" -> {
                    System.out.print("ID pedido: ");
                    String pid = sc.nextLine().trim();
                    System.out.print("ID do cliente: ");
                    String cid = sc.nextLine().trim();
                    Optional<Cliente> oc = clienteRepo.findById(cid);
                    if (oc.isEmpty()) {
                        System.out.println("Cliente nao encontrado.");
                        break;
                    }
                    Cliente cliente = oc.get();
                    System.out.print("Endereco de coleta (enter para usar cliente): ");
                    String end = sc.nextLine().trim();
                    if (end.isBlank()) end = cliente.getEndereco();
                    Pedido p = new Pedido(pid, cliente, end);
                    System.out.println("Digite IDs de servico separados por vírgula:");
                    String linha = sc.nextLine().trim();
                    if (!linha.isBlank()) {
                        String[] partes = linha.split(",");
                        for (String sId : partes) {
                            servicoRepo.findById(sId.trim()).ifPresent(p::adicionarServico);
                        }
                    }
                    p.calcularValor();
                    pedidoRepo.save(p);
                    clienteRepo.save(cliente);
                    System.out.println("Pedido criado: " + p);
                }
                case "6" -> {
                    List<Pedido> all = pedidoRepo.findAll();
                    if (all.isEmpty()) System.out.println("Nenhum pedido.");
                    else all.forEach(System.out::println);
                }
                case "7" -> {
                    System.out.print("ID pedido a pagar: ");
                    String payId = sc.nextLine().trim();
                    Optional<Pedido> op = pedidoRepo.findById(payId);
                    if (op.isEmpty()) {
                        System.out.println("Pedido nao encontrado.");
                        break;
                    }
                    Pedido p = op.get();
                    System.out.print("Metodo de pagamento (Cartao/Pix/Dinheiro): ");
                    String metodo = sc.nextLine().trim();
                    boolean ok = p.pagarPedido(metodo);
                    if (ok) {
                        pedidoRepo.save(p);
                        System.out.println("Pagamento OK. Pedido: " + p);
                    }
                }
                case "8" -> {
                    System.out.print("ID pedido: ");
                    String pid2 = sc.nextLine().trim();
                    Optional<Pedido> op2 = pedidoRepo.findById(pid2);
                    if (op2.isEmpty()) {
                        System.out.println("Pedido nao encontrado.");
                        break;
                    }
                    Pedido p2 = op2.get();
                    System.out.println("Status atual: " + p2.getStatus());
                    System.out.println("Novos status: AGUARDANDO_PAGAMENTO, PAGO, EM_PROCESSAMENTO, PRONTO, ENTREGUE, CANCELADO");
                    System.out.print("Escolha novo status: ");
                    String novo = sc.nextLine().trim();
                    try {
                        StatusPedido snew = StatusPedido.valueOf(novo);
                        p2.atualizarStatus(snew);
                        pedidoRepo.save(p2);
                        System.out.println("Status atualizado: " + p2);
                    } catch (IllegalArgumentException ex) {
                        System.out.println("Status invalido.");
                    }
                }
                case "9" -> {
                    System.out.print("ID pedido a excluir: ");
                    String delId = sc.nextLine().trim();
                    pedidoRepo.deleteById(delId);
                    System.out.println("Se existia, removido.");
                }
                case "10" -> {
                    System.out.println("\n=== Cadastro de Cliente VIP ===");

                    System.out.print("ID do cliente: ");
                    String id = sc.nextLine().trim();

                    System.out.print("Nome do cliente: ");
                    String nome = sc.nextLine().trim();

                    System.out.println("Valor da mensalidade VIP: R$ 49.90");
                    System.out.print("Confirmar pagamento da mensalidade? (s/n): ");
                    String resp = sc.nextLine().trim().toLowerCase();

                    if (!resp.equals("s")) {
                        System.out.println("Assinatura VIP cancelada.");
                        return;
                    }
                    PlanoAssinatura planoVip =
                            new PlanoAssinatura("", 49.90, 0.10);

                    Cliente cliente = new Cliente(id, nome);
                    cliente.setVip(true);
                    cliente.setPlanoAssinatura(planoVip);

                    clienteRepo.save(cliente);

                    System.out.println("Cliente VIP cadastrado com sucesso!");
                    System.out.println("Dados: " + cliente);
                }

                    case "0" -> {
                        System.out.println("Saindo...");
                        sc.close();
                        return;
                    }
                    default -> System.out.println("Opcao invalida.");
                }
            }
        }

    }