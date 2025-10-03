# 📦 Sistema de Cadastro de Produtos

> Trabalho de Programação Orientada a Objetos II - Professor Marcão
> Aplicação GUI com Java Swing para gerenciamento de produtos (CRUD)

---

## 📋 Descrição do Projeto

Sistema desktop desenvolvido em Java com interface gráfica Swing para realizar operações de CRUD (Create, Read, Update, Delete) em um cadastro de produtos. A aplicação possui uma interface intuitiva com tabela de listagem e painel lateral para visualização de detalhes em tempo real.

### 🎯 Objetivo

Desenvolver uma aplicação que demonstre os conceitos de Programação Orientada a Objetos aplicados ao desenvolvimento de interfaces gráficas, incluindo:
- Encapsulamento de dados
- Separação de responsabilidades entre classes
- Manipulação de eventos
- Componentização de interface gráfica

---

## 🖼️ Funcionalidades

### ✨ Recursos Principais

- **✅ Create (Criar)**: Cadastrar novos produtos com informações detalhadas
- **✅ Read (Ler)**: Visualizar lista de produtos e detalhes individuais
- **✅ Update (Atualizar)**: Editar informações de produtos existentes
- **✅ Delete (Excluir)**: Remover produtos do sistema com confirmação
- **🔍 Busca**: Filtrar produtos por descrição ou código de barras
- **📊 Painel de Detalhes**: Visualização automática ao selecionar produtos na tabela

### 📝 Campos do Produto

- **ID**: Identificador único (gerado automaticamente)
- **Descrição**: Nome do produto (obrigatório)
- **Preço**: Valor em reais (obrigatório)
- **Validade**: Data de validade do produto
- **Categoria**: Classificação do produto
- **Fornecedor**: Nome do fornecedor
- **Estoque**: Quantidade disponível (obrigatório)
- **Código de Barras**: Código EAN/UPC
- **Observações**: Informações adicionais

---

## 🏗️ Estrutura do Projeto

```
cadastro-produtos/
│
├── CadastroProdutos.java    # Classe principal com interface de listagem
├── EdicaoProduto.java        # Janela modal para cadastro/edição
├── Produto.java              # Classe modelo (entidade)
└── README.md                 # Documentação do projeto
```

### 📦 Descrição das Classes

#### `CadastroProdutos.java`
Classe principal que gerencia a interface de listagem e operações do CRUD.

**Responsabilidades:**
- Exibir tabela com lista de produtos
- Gerenciar painel de detalhes lateral
- Controlar botões de ação (Novo, Editar, Excluir, Buscar)
- Implementar busca e filtragem
- Atualizar detalhes automaticamente ao selecionar produtos

**Principais Componentes:**
- `JTable` para listagem de produtos
- `JPanel` para exibição de detalhes
- `JButton` para ações do CRUD
- `JTextField` para busca

#### `EdicaoProduto.java`
Janela modal (JDialog) responsável pela criação e edição de produtos.

**Responsabilidades:**
- Exibir formulário de cadastro/edição
- Validar campos obrigatórios
- Salvar novos produtos ou atualizar existentes
- Comunicar-se com a tela principal para atualizar listagem

**Principais Componentes:**
- `JTextField` para campos de texto
- `JTextArea` para observações
- Validação de entrada de dados

#### `Produto.java`
Classe modelo que representa a entidade Produto.

**Responsabilidades:**
- Encapsular dados do produto
- Prover getters e setters
- Garantir integridade dos dados

**Atributos:**
```java
- int id
- String descricao
- double preco
- String validade
- String categoria
- String fornecedor
- int estoque
- String codigoBarras
- String observacoes
```

---

## 🚀 Como Executar

### Pré-requisitos

- Java JDK 8 ou superior instalado
- Variáveis de ambiente configuradas (JAVA_HOME e PATH)

### Compilação

Abra o terminal/prompt de comando na pasta do projeto e execute:

```bash
javac CadastroProdutos.java EdicaoProduto.java Produto.java
```

### Execução

Após a compilação, executar o programa:

```bash
java CadastroProdutos
```

### Exemplo Completo (PowerShell)

```powershell
# Navegue até a pasta do projeto
cd C:\Faculdade\POO II

# Compile todos os arquivos
javac CadastroProdutos.java EdicaoProduto.java Produto.java

# Execute a aplicação
java CadastroProdutos
```

---

## 💡 Como Usar

### 1️⃣ Visualizar Produtos
- A tabela central exibe todos os produtos cadastrados
- Clique em qualquer linha para ver os detalhes completos no painel lateral

### 2️⃣ Adicionar Novo Produto
1. Clique no botão **"Novo"**
2. Preencha os campos obrigatórios (marcados com *)
3. Clique em **"Gravar"** para salvar

### 3️⃣ Editar Produto
1. Selecione um produto na tabela
2. Clique no botão **"Editar"**
3. Modifique os campos desejados
4. Clique em **"Gravar"** para salvar as alterações

### 4️⃣ Excluir Produto
1. Selecione um produto na tabela
2. Clique no botão **"Excluir"**
3. Confirme a exclusão na janela de diálogo

### 5️⃣ Buscar Produto
1. Digite o termo de busca no campo de busca (descrição ou código de barras)
2. Clique no botão **"Buscar"**
3. Para ver todos os produtos novamente, limpe o campo e busque novamente

---

## 📚 Conceitos de POO Aplicados

### Encapsulamento
- Atributos privados na classe `Produto`
- Acesso controlado via getters e setters
- Ocultação de implementação interna

### Herança
- `CadastroProdutos` extends `JFrame`
- `EdicaoProduto` extends `JDialog`

### Polimorfismo
- Implementação de interfaces (`ActionListener`, `ListSelectionListener`)
- Sobrescrita de métodos (`actionPerformed`, `valueChanged`)

### Abstração
- Separação entre interface gráfica e lógica de negócio
- Classe `Produto` como abstração da entidade

### Coesão e Acoplamento
- Cada classe tem uma responsabilidade bem definida
- Baixo acoplamento entre classes
- Alta coesão interna

---

## 📊 Armazenamento de Dados

Atualmente, o sistema utiliza `ArrayList<Produto>` para armazenamento em memória. Os dados são perdidos ao fechar a aplicação.

### Possíveis Melhorias Futuras

- **Persistência em Arquivo**: Salvar dados em arquivo texto ou serializado
- **Banco de Dados**: Integração com SQLite, MySQL ou PostgreSQL
- **JSON/XML**: Exportação e importação de dados

---

## ⚠️ Validações Implementadas

- ✅ Campos obrigatórios: Descrição, Preço e Estoque
- ✅ Validação de formato numérico para Preço e Estoque
- ✅ Confirmação antes de excluir produtos
- ✅ Mensagens de erro descritivas
- ✅ Feedback visual de sucesso nas operações

---

## 📝 Licença

Este projeto foi desenvolvido para fins educacionais como parte do curso de Programação Orientada a Objetos II.

---

**Desenvolvido com ☕ e muito aprendizado em POO!**