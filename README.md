# Sistema de Gestão de Leilões Online 

## Sobre o Projeto
O sistema simula a camada de domínio e a lógica de negócio de uma plataforma de leilões online. O sistema atua como um intermediário neutro, permitindo que utilizadores autenticados adicionem artigos para leilão e participem em leilões de terceiros através de licitações dinâmicas.

O foco principal do projeto foi a aplicação rigorosa de boas práticas de Engenharia de Software, seguindo a metodologia **Unified Process (UP)** e a correta atribuição de responsabilidades baseada nos padrões **GRASP**.

### Principais Funcionalidades
* **Gestão de Leilões e Artigos:** Criação e configuração de leilões com parâmetros temporais e monetários (ex: valor inicial, preço "Compre Já").
* **Motor de Licitações:** Validação em tempo real de lances com base no estado do leilão e no valor atual.
* **Encerramento Automático:** Gestão do ciclo de vida do leilão e apuramento do vencedor.
* **Sistema de Reputação:** Notificação e geração de avaliações pós-venda entre comprador e vendedor.

## Arquitetura e Padrões de Desenho (GRASP)
O desenho orientado a objetos foi estruturado garantindo Alta Coesão e Baixo Acoplamento:
* **Information Expert:** A classe `Leilao` gere autonomamente o seu estado, validações e histórico de licitações. Os catálogos (`CatalogoDeLeiloes` e `HistoricoLeiloes`) gerem a persistência em memória através de `HashMaps` (O(1)).
* **Controller:** A classe `Utilizador` atua como controlador de sessão para operações iniciadas pelo ator.
* **Pure Fabrication:** A classe `GestorLeilao` foi criada artificialmente para orquestrar o complexo processo de encerramento de negócios e notificações pós-venda, sem sobrecarregar as entidades de domínio.
* **Creator:** Delegação correta da instanciação de objetos (ex: `Leilao` instancia as suas `Licitacao`).

## Como Executar

A aplicação não requer base de dados externa (persistência volátil em memória) e interage via consola.

1. Clone este repositório ou importe o projeto para a sua IDE (recomendado: **Eclipse**).
2. Navegue até à pasta principal do código fonte: `src/project/`.
3. Localize e execute o ficheiro `RunProject.java` (`Run As > Java Application`).

### Modos de Funcionamento
Ao iniciar, o menu apresenta duas opções principais:
* **Opção 1 (Testes Scriptados):** Correção automática de 4 cenários (Caminho Feliz, Regras de Negócio/Erros, Leilão sem licitações e Empates/Limites).
* **Opção 2 (Teste Interativo):** O avaliador pode testar as regras inserindo lances manualmente na consola contra um adversário virtual (o `JoaoBot`).

## Testes e Qualidade de Código
* O projeto inclui testes unitários desenvolvidos com **JUnit 5**.
* A cobertura de código foi validada utilizando a ferramenta **JaCoCo**, atingindo **100% de cobertura** nas classes críticas da camada de domínio.



---
*Projeto desenvolvido para fins académicos no âmbito da Licenciatura em Tecnologias da Informação.*
