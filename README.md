# a-mei

Esse projeto foi desenvolvido para o trabalho de conclusão de curso de Análise e Desenvolvimento de Sistemas

O software a-mei tem como objetivo facilitar a vida dos Micro Empreendedores Individuais, oferecendo funcionalidades como cadastro de clientes, geração de orçamentos, ordens de serviço, auxílio na emissão de notas fiscais, e também formas de visualização de dados.

# Tecnologias

Esse projeto é dividido em dois repositórios (Front-end e Back-end)
[Repositório Front-end]([https://www.geeksforgeeks.org/](https://github.com/freitasDavi/amei-fe)https://github.com/freitasDavi/amei-fe)
[Repositório Back-end]([https://www.geeksforgeeks.org/](https://github.com/freitasDavi/amei-fe)https://github.com/freitasDavi/amei-be)

- Spring Boot
- Spring Security
- PostgreSQL
- Swagger
- QueryDSL
- Stripe
- CommonsCSV

# Como rodar esse projeto:

## 1📦 Como baixar o projeto

   Clonar os repositórios
   
    $ git clone https://github.com/freitasDavi/amei-be.git
   
    # Instalar o java

    # Instalar as dependencias
  
    $ mvn install

---

## 2. Configurar application.properties
Preencher com as seguintes variáveis

- spring.datasource.url="URL_BANCO"
- spring.datasource.username="USERNAME"
- spring.datasource.password="SENHA"

## 3 Inicializar a aplicação
    $ mvn spring-boot:run


Projeto desenvolvido por Davi Freitas da Silva, Gabriel Favarin, Guilherme Hahn e Luiza Miguel.
