API de Registro de Matrículas

Este projeto é uma API desenvolvida com a finalidade de estudo das tecnologias Java utilizando o Spring Boot 3, Spring Security, Spring Data JPA e Swagger. A API permite a criação, leitura, atualização e exclusão (CRUD) de registros de cursos, alunos e matrículas.

Funcionalidades:

- Gerenciamento de Alunos: Cadastro, consulta, atualização e exclusão de alunos.
- Gerenciamento de Cursos: Cadastro, consulta, atualização e exclusão de cursos.
- Matrículas: Cadastro, consulta e atualização de matrículas de alunos em cursos.
- Autenticação e Autorização: Utilização de JWT para autenticação. Regras de acesso são definidas por roles (ADMIN e USER).
- Documentação da API: A API está documentada utilizando o Swagger.

Tecnologias Utilizadas:

- Spring Boot 3: Framework principal para construção da API.
- Spring Security: Implementação de autenticação e autorização utilizando JWT.
- Spring Data JPA: Integração com o banco de dados e manipulação de entidades.
- Swagger: Ferramenta para documentação da API.

Requisitos:

- Java 17 ou superior
- Maven para gerenciamento de dependências
- Banco de Dados MySQL 

Configuração do Projeto:

Clonar o Repositório:

- Git Clone: https://github.com/RenatoGuii/apirest-registration.git

Configurar o Banco de Dados:
- Atualize o arquivo application.properties com as configurações do seu banco de dados.

Executar a Aplicação:
- Rode o arquivo RegistrationAplication.java

Documentação da API:
- http://localhost:8080/swagger-ui.html (Link do Swagger UI acessada de maneira local após iniciar a aplicação localmente)
- A documentação da API foi gerada utilizando o Swagger e pode ser acessada no ambiente de desenvolvimento:

- Autenticação na Documentação: Para testar as requisições protegidas, você pode se autenticar diretamente no Swagger UI (ou use outras plataformas de consumo de API se preferir)

- Primeiro, se registre naAPI usando o endpoint /auth/register.
- Execute o login na API usando o endpoint /auth/login e obtenha o token JWT.
- No Swagger UI, clique no ícone de cadeado e insira o token obtido no formato Bearer <seu-token>.
- Agora você pode acessar os endpoints protegidos.

Endpoints:

Estudantes:

- POST /student: (ADMIN) Cria um novo estudante.
- GET /student/{id}: (USER) Obtém os detalhes de um estudante específico.
- GET /student: (USER) Lista todos os estudantes com paginação.
- PUT /student/{id}: (ADMIN) Atualiza os dados de um estudante.
- DELETE /student/{id}: (ADMIN) Deleta um estudante.

Cursos:

- POST /course: (ADMIN) Cria um novo curso.
- GET /course/{id}: (USER) Obtém os detalhes de um curso específico.
- GET /course: (USER) Lista todos os cursos com paginação.
- PUT /course/{id}: (ADMIN) Atualiza os dados de um curso.
- DELETE /course/{id}: (ADMIN) Deleta um curso.

Matrículas:

- POST /registration: (ADMIN) Cria uma nova matrícula.
- GET /registration/{id}: (USER) Obtém os detalhes de uma matrícula específica.
- GET /registration: (USER) Lista todas as matrículas com paginação e filtragem.
- DELETE /registration/{id}: (ADMIN) Deleta uma matrícula.

Considerações Finais:

- Este projeto foi desenvolvido com o intuito de praticar e implementar conceitos de segurança e boas práticas de desenvolvimento com Spring. Sinta-se à vontade para contribuir ou sugerir melhorias.