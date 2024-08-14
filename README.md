<h1>API de Registro de Matrículas</h1>

Este projeto é uma API desenvolvida com a finalidade de estudo das tecnologias Java utilizando o Spring Boot 3, Spring Security, Spring Data JPA e Swagger. A API permite a criação, leitura, atualização e exclusão (CRUD) de registros de cursos, alunos e matrículas.

<h2>Funcionalidades:</h2>

- Gerenciamento de Alunos: Cadastro, consulta, atualização e exclusão de alunos.
- Gerenciamento de Cursos: Cadastro, consulta, atualização e exclusão de cursos.
- Matrículas: Cadastro, consulta e atualização de matrículas de alunos em cursos.
- Autenticação e Autorização: Utilização de JWT para autenticação. Regras de acesso são definidas por roles (ADMIN e USER).
- Documentação da API: A API está documentada utilizando o Swagger.

<h2>Tecnologias Utilizadas:</h2>

- Spring Boot 3: Framework principal para construção da API.
- Spring Security: Implementação de autenticação e autorização utilizando JWT.
- Spring Data JPA: Integração com o banco de dados e manipulação de entidades.
- Swagger: Ferramenta para documentação da API.

<h2>Requisitos:</h2>

- Java 17 ou superior
- Maven para gerenciamento de dependências
- Banco de Dados MySQL 

<h2>Configuração do Projeto:</h2>

**Clonar o Repositório:**

- Git Clone: https://github.com/RenatoGuii/apirest-registration.git

**Configurar o Banco de Dados:**
- Atualize o arquivo application.properties com as configurações do seu banco de dados.

**Executar a Aplicação:**
- Rode o arquivo RegistrationAplication.java

<h2>Documentação da API:</h2>

- Link do Swagger UI acessada de maneira local após iniciar a aplicação localmente:
- Link: http://localhost:8080/swagger-ui.html

- A documentação da API foi gerada utilizando o Swagger e pode ser acessada no ambiente de desenvolvimento:

- Autenticação na Documentação: Para testar as requisições protegidas, você pode se autenticar diretamente no Swagger UI (ou use outras plataformas de consumo de API se preferir)

- Primeiro, se registre na API usando o endpoint /auth/register.

- Execute o login na API usando o endpoint /auth/login e obtenha o token JWT.

- No Swagger UI, clique no ícone de cadeado e insira o token obtido no formato Bearer <seu-token>.

- Agora você pode acessar os endpoints protegidos.

<h2>Endpoints:</h2>

**Estudantes:**

- POST /student: (_ADMIN_) Cria um novo estudante.
- GET /student/{id}: (_USER_) Obtém os detalhes de um estudante específico.
- GET /student: (_USER_) Lista todos os estudantes com paginação.
- PUT /student/{id}: (_ADMIN_) Atualiza os dados de um estudante.
- DELETE /student/{id}: (_ADMIN_) Deleta um estudante.

**Cursos:**

- POST /course: (_ADMIN_) Cria um novo curso.
- GET /course/{id}: (_USER_) Obtém os detalhes de um curso específico.
- GET /course: (_USER_) Lista todos os cursos com paginação.
- PUT /course/{id}: (_ADMIN_) Atualiza os dados de um curso.
- DELETE /course/{id}: (_ADMIN_) Deleta um curso.

**Matrículas:**

- POST /registration: (_ADMIN_) Cria uma nova matrícula.
- GET /registration/{id}: (_USER_) Obtém os detalhes de uma matrícula específica.
- GET /registration: (_USER_) Lista todas as matrículas com paginação e filtragem.
- DELETE /registration/{id}: (_ADMIN_) Deleta uma matrícula.

<h2>Considerações Finais:</h2>

- Este projeto foi desenvolvido com o intuito de praticar e implementar conceitos de segurança e boas práticas de desenvolvimento com Spring. Sinta-se à vontade para contribuir ou sugerir melhorias.
