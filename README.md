# ✔ Sistema de Venda de Jogos Online

## Situação de Aprendizagem do Curso Técnico em Desenvolvimento de Sistemas
Este repositório tem por objetivo compartilhar a aplicação desenvolvida durante a situação de aprendizagem proposta nos dois primeiros semestres do curso.

### Sistema de Gerenciamento de Jogos e Usuários
Foi proposto criar uma aplicação utilizando a arquitetura DAO e integrado ao banco de dados MySQL, onde cada aluno pode escolher o tema onde queria desenvolver. Criei um sistema básico para facilitar a venda e gerenciamento de jogos online, no qual os usuários podem adicionar jogos ao carrinho, realizar compras e acessar informações detalhadas dos produtos.


### Tecnologias Utilizadas
[![My Skills](https://skillicons.dev/icons?i=java,mysql)](https://skillicons.dev)

## Informações de Configuração

### Requisitos de Aplicativos Instalados
- **MySQL:** É necessário ter o MySQL instalado e configurado para gerenciar o banco de dados. [Link para download](https://dev.mysql.com/downloads/)
- **Java JDK:** Certifique-se de ter o JDK 8 ou superior instalado no seu sistema. [Link para download](https://www.oracle.com/java/technologies/javase-downloads.html)
- **IDE de sua preferência:** Utilize qualquer IDE compatível com projetos Java, como Eclipse, NetBeans ou IntelliJ. (Recomenda-se NetBeans)

### Passos para Configuração
1. **Banco de Dados:**
   - Certifique-se de ter o MySQL em execução.
   - Crie o banco de dados executando o script fornecido no arquivo `createDB.sql` ou diretamente pelo MySQL Workbench.
   - Nome do banco: `vendajogos`.

2. **Configuração de Conexão:**
   - No projeto, o arquivo de conexão `ConexaoBanco.java` deve ser configurado com as credenciais corretas do banco de dados:
     ```java
     public Connection getConnection(){
         try {
             return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/vendajogos", "usuario", "senha");
         } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Erro na conexao com banco: " + e.getMessage());
             return null;
         }
     }
     ```

3. **Execução do Sistema:**
   - Importe o projeto em sua IDE.
   - Certifique-se de que todas as bibliotecas necessárias (como JDBC) estejam configuradas.
   - Compile e execute o sistema para iniciar a interface gráfica do usuário.
