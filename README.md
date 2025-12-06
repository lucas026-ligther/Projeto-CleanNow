Desenvolvido em Java, o CleanNow é um sistema que simula o dia a dia de uma lavanderia. Com ele, é possível registrar novos clientes, catalogar os serviços oferecidos, criar novos pedidos, calcular os preços, aplicar os descontos, guardar os pagamentos e acompanhar o histórico. Para uma melhor organização, o código foi estruturado em diversas classes, cada uma responsável por uma parte do sistema.

Na classe Cliente, ficam guardadas as informações importantes de cada pessoa, como nome, onde mora, telefone, o que mais gosta e seus pedidos antigos. Um cliente pode ser classificado como VIP, ganhando descontos nos seus pedidos automaticamente.

A classe Servico mostra o que a lavanderia oferece, como lavar e secar roupas. Cada serviço tem um nome, uma explicação, um preço e quanto tempo leva para ficar pronto.

Na classe Pacote, juntamos vários serviços para dar um preço melhor para o cliente. Já na classe PlanoAssinatura, temos planos mensais com descontos e uma certa quantidade de serviços já inclusos.

A classe Pedido cuida de juntar o cliente, os serviços, os pacotes, a assinatura e o horário de busca e entrega da roupa. Ela calcula sozinha o preço total, coloca os descontos, vê em que pé está o pedido (se está “AGENDADO”, “COLETADO” ou “ENTREGUE”) e anota o pagamento.

Na classe Pagamento, é registrado como o cliente pagou e se a operação foi confirmada.

Para finalizar, a classe CleanNowSystem é o coração do programa. Nela, criamos um cliente, colocamos os serviços, montamos um pedido, marcamos a busca e entrega, fazemos o pagamento e mudamos o status do pedido. Também mostramos o histórico de tudo que o cliente já pediu.

Este projeto faz tudo que o trabalho pedia: cadastrar clientes, registrar serviços, dar descontos, fazer agendamentos, receber pagamentos, mostrar onde está o pedido e guardar o histórico.
