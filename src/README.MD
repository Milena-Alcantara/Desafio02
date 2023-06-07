### ESTRELAS  APRENDIZ - DESAFIO 02 JAVA OOP

- O desafio consite em criar um sistema de Vendas, sendo possível:
- Cadastrar um cliente;
- Cadastrar um vendedor;
- Simular a compra/venda de um produto;
- Cadastrar uma venda;
- Cadastrar uma compra;
- Listar clientes;
- Listar vendedores;

<hr>

### Sistema de Vendas - D'Ouros Bolos

- Comecei criando a classe Menu que permite a escolha entre _Cliente_ ou _Vendedor_ ;
- Entrando como Cliente, é exibido o Menu específico da Classe Cliente que permite:

1 - Cadastrar-se;

2- Realizar compra;

3- Visulizar histórico de compras;

4- Voltar ao Menu anterior.


- Entrando como cliente, é sorteado um vendedor já cadastrado em sistema para realizar o atendimento.
- Para conseguir efetuar o cadastro, é necessário a entrada correta dos dados como Nome, E-mail, e CPF, pois o método contém verificações garantindo o formato específico para o dado a ser recebido.
- Para conseguir realizar uma compra, é necessário ter um cadastro, sendo assim o cliente primeiro realiza o seu login com seu e-mail único, e constando o mesmo no sistema, é exibido os produtos dispóniveis.
Esses produtos são gerados da Classe Produto com seus nomes, códigos e valores.
- Ao finalizar sua compra, é exibido os produtos que foram escolhidos novamente, o valor total e é feito o cadastro dessa compra já associando ao CPF do cliente.
 O cadastro da venda também acontece nesse momento, porém associando a venda ao e-mail do vendedor que foi sorteado e realizou o atendimento.
- Para visualizar o histórico de compras, o cliente informa o CPF e é feito um verificação se este CPF está associado a alguma compra. Caso esteja, é exibido os produtos comprados, a data da compra, e o valor pago.
- Por fim, a cada final de método, o Menu do cliente é chamado permitindo fazer uma nova operação ou sair do programa.




- Entrando como Vendedor, é exibido o Menu específico da Classe Vendedor que permite:

1 - Cadastrar-se;

2- Consultar Vendas;

3- Verificar lista de clientes;

4- Verificar lista de vendedores;

5- Voltar ao Menu anterior.

- Assim como em cliente, no cadastro há verificações para garantir que os dados sejam passados de forma válida. Ambos não permitem e-mails e cpfs repetidos em cadastro.
- No método consultar vendas, primeiramente é solicitado o login do vendedor através do seu e-mail e logo após é ofertada ao usuário duas possibilidades:

1- Consultar por e-mail:

- Permite a visualização do histórico através de uma pesquisa de associção do e-mail do vendedor á venda realizada.

2- Consulta geral:

- Mostra uma lista de vendas com dados básicos para um parâmetro geral das vendas.


- No método consultar lista de clientes, é visulizado uma lista dos clientes cadastrados e seus dados.
- No método consultar lista de vendedores, é visulizado uma lista dos vendedores cadastrados e seus dados.
- Por fim, a cada final de método, o Menu do vendedor é chamado permitindo fazer uma nova operação ou sair do programa.

<hr>

