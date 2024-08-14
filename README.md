#BACK-END COM FRAMEWORKS (SPRING) PROF. WELLINGTON DE OLIVEIRA

# exercício de fixação sts4 e postman | loja de roupas

este repositório refere-se a um sistema de gerenciamento de vendas uma loja de roupas,
proposto pelo [professor](https://github.com/wellingtonfoz/), com o objetivo de exercitar
conteúdos vistos em sala de aula acerca de desenvolvimento back-end com spring

## requisitos

### iniciais
- o sistema deverá ser capaz de cadastrar, alterar, deletar e consultar um ou todos os clientes, funcionários, produtos e vendas ✅
- para cada cliente, deverá ser armazenado nome, email, telefone, idade e endereço ✅
- para cada funcionário, deverá ser armazenado nome, email, telefone, idade, endereço e função ✅
- para cada produto, deverá ser armazenado o nome, descrição, preço ✅
- para cada venda, deverá ser armazenado o cliente, o funcionário, os produtos vendidos, um campo de observações da venda e o valor total da venda ✅
- o valor total da venda não deverá ser enviado para salvar, mas sim calculado internamente pelo próprio sistema com base na soma dos preços de todos 
  os produtos vinculados à venda. o sistema deverá calcular o valor total dos produtos e vincular à venda antes de persistir ✅
- uma venda deve conter um único cliente e um único funcionário vinculado. tanto o cliente quanto o funcionário podem estar vinculados a várias vendas ✅
- uma venda pode conter vários produtos e um produto pode ser vinculado a várias vendas ✅

### filtragem
- o sistema deve ser capaz de listar vendas filtradas por parte do nome do cliente ✅
- o sistema deve ser capaz de listar vendas filtradas por parte do nome do funcionário ✅
- o sistema deve ser capaz de listar os 10 produtos mais caros do cadastro ✅
- o sistema deve ser capaz de listar os clientes com idade entre 18 e 35 anos ✅
- o sistema deve ser capaz de listar as 10 vendas com totais mais altos ✅

### validation
- a observação da venda é um campo obrigatório ✅
- uma venda pode ou não ter um funcionário vinculado, mas sempre precisa ter um cliente vinculado ✅
- em uma venda a lista de produtos associados jamais pode estar vazia ✅
- o nome do produto é campo obrigatório, já a descrição não ✅
- as idades dos clientes e funcionários não podem ser negativas ✅
- o endereço é um campo obrigatório ✅
- o e-mail do cliente/funcionário deve ser válido ⏳
- o cpf do cliente/funcionário deve ser válido ⏳
- o cep do cliente deve seguir o padrão de cep brasileiro ⏳
- o nome do cliente deve conter pelo menos 2 palavras e um espaço ⏳
- os telefones devem seguir o padrão (XX) XXXX-XXXX ou (XX) XXXXX-XXXX ⏳
- ao salvar uma venda, caso o cliente seja menor de 18 anos, o valor total máximo deve ser de 500 reais. caso o valor esteja acima, não permita a persistência ⏳
