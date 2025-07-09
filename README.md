# 🚗 Sistema de Controle de Estacionamento

Este é um sistema backend desenvolvido em Java com Spring Boot, responsável por controlar entradas e saídas de veículos em um estacionamento, calcular tarifas e registrar o histórico de uso.

## 📌 Funcionalidades

- Cadastro de veículos
- Registro de entrada e saída
- Cálculo automático do valor a ser pago
- Listagem de veículos estacionados no momento
- Histórico completo de registros

## 🔧 Tecnologias utilizadas

- Java 17
- Spring Boot
- Spring Data JPA
- MySQL
- Maven
- Postman (para testes)

## 💻 Como rodar o projeto

### Pré-requisitos

- Java 17 ou superior
- MySQL rodando localmente
- Git instalado
- Postman (ou outra ferramenta de testes de API)

### Passos

1. Clone o projeto:
   ```bash
   git clone https://github.com/SEU_USUARIO/controle-estacionamento.git
   cd controle-estacionamento

2. Configure o application.properties com os dados do seu banco MySQL:

  ```bash
  spring.datasource.url=jdbc:mysql://localhost:3306/estacionamento
  spring.datasource.username=root
  spring.datasource.password=sua_senha
  spring.jpa.hibernate.ddl-auto=update
```

3. Rode a aplicação:
  ./mvnw spring-boot:run

4.Acesse os endpoints usando o Postman:

POST /veiculos → cadastrar veículo

GET /veiculos → listar veículos

POST /estacionamento/entrada?placa=ABC1234

POST /estacionamento/saida?placa=ABC1234

GET /estacionamento/ativos

GET /estacionamento/historico

📝 Exemplo de JSON para cadastro de veículo


{
  "placa": "ABC1234",
  "modelo": "Civic",
  "cor": "Preto"
}
