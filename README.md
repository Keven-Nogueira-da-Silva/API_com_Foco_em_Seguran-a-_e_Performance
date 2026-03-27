# 💳 Payment Gateway API - Spring Boot

Este projeto é uma API REST robusta para um **Gateway de Pagamentos**, desenvolvida com foco em segurança de dados, performance e arquitetura limpa. O sistema permite o gerenciamento de transações financeiras com diferentes níveis de acesso (RBAC) e isolamento total entre usuários.

## 🛠 Tecnologias Utilizadas

* **Java 17**
* **Spring Boot 3**
* **Spring Security & JWT** (Autenticação e Autorização)
* **Spring Data JPA** (Persistência de dados)
* **PostgreSQL** (Banco de dados relacional)
* **Bean Validation** (Validação de entrada de dados)
* **Maven** (Gerenciador de dependências)

## 🚀 Funcionalidades Principais

* **Autenticação JWT:** Proteção de endpoints via tokens seguros.
* **Controle de Acesso (RBAC):** Diferenciação entre usuários `ADMIN` e `USER`.
* **Isolamento de Dados:** * O usuário comum (`USER`) visualiza apenas as suas próprias transações.
    * O administrador (`ADMIN`) possui visão global de todas as transações do sistema.
* **Validação de Negócio:** Bloqueio automático de transações com valores negativos ou campos obrigatórios ausentes.
* **Paginação de Dados:** Consultas otimizadas para performance em grandes volumes de dados.
* **Projeções com DTOs:** Exposição apenas dos dados necessários, protegendo a integridade das entidades do banco.

## 🏗 Arquitetura do Projeto

<img width="2468" height="1240" alt="image" src="https://github.com/user-attachments/assets/1962276b-8053-4748-8931-0c4a20502429" />

O projeto segue a arquitetura em camadas para facilitar a manutenção e escalabilidade:

1.  **Controller:** Exposição dos endpoints REST.
2.  **DTO (Data Transfer Object):** Entrada e saída de dados otimizada.
3.  **Service:** Concentra as regras de negócio e lógica de segurança.
4.  **Repository:** Interface de comunicação com o banco de dados via queries customizadas.
5.  **Security/Infra:** Configurações de filtros JWT e tratadores de exceções globais.



## 📌 Principais Endpoints

### Autenticação
* `POST /auth/login` - Realiza login e retorna o token JWT.
* `POST /auth/register` - Cadastro de novos usuários.

### Transações
* `POST /transacoes` - Cadastra uma nova transação (Requer Token).
* `GET /transacoes?status=CONCLUIDA` - Lista transações filtradas e paginadas (Requer Token).

<img width="1600" height="876" alt="image" src="https://github.com/user-attachments/assets/a462e76b-d22a-4e65-8f64-5a0638a8c5c4" />


## 🛡 Validações Implementadas

O sistema utiliza **Bean Validation** para garantir que:
* O `valor` seja sempre positivo (mínimo de 0.01).
* O `status` e `valor` sejam campos obrigatórios no envio.
* Erros de validação retornem um JSON amigável com o campo e a mensagem de erro específica.

---
Desenvolvido por mim como parte dos estudos em desenvolvimento Backend com Java e Spring Boot.
