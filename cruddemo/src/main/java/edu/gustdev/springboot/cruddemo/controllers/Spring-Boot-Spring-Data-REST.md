# Spring Data REST
* Spring Data REST simplificou bastante a criação de APIs RESTful ao construir sobre os repositórios do Spring Data. Antes do surgimento do Spring Data REST, a criação de endpoints REST envolvia a configuração manual de controladores, serviços e repositórios. Aqui está um resumo de como isso mudou:

## Antes do Spring Data REST
* Controladores REST: Você precisava criar controladores manualmente para cada entidade.

    ```java
    @RestController
    @RequestMapping("/api/entidades")
    public class EntidadeController {
        
        @Autowired
        private EntidadeService entidadeService;
        
        @GetMapping("/{id}")
        public ResponseEntity<Entidade> getEntidade(@PathVariable Long id) {
            Entidade entidade = entidadeService.findById(id);
            return ResponseEntity.ok(entidade);
        }
        
        // Outros métodos (POST, PUT, DELETE)
    }
    ```

* Serviços: Serviços para encapsular a lógica de negócios.

    ```java
    @Service
    public class EntidadeService {
        
        @Autowired
        private EntidadeRepository entidadeRepository;
        
        public Entidade findById(Long id) {
            return entidadeRepository.findById(id).orElse(null);
        }
        
        // Outros métodos
    }
    ```
* Repositórios: Repositórios para acesso ao banco de dados.

    ```java
    public interface EntidadeRepository extends JpaRepository<Entidade, Long> {
    }
    ```

## Depois do Spring Data REST
* Com o Spring Data REST, você pode expor automaticamente os repositórios como endpoints REST sem a necessidade de criar controladores e serviços manualmente.

* Repositórios REST: Apenas definindo o repositório, ele já é exposto como um endpoint REST.

    ```java
    @RepositoryRestResource
    public interface EntidadeRepository extends JpaRepository<Entidade, Long> {
    }
    ```
* O Spring Data REST possibilita configuração mínima: ele cuida da criação dos endpoints para operações CRUD (Create, Read, Update, Delete):
    * GET /entidades: Lista todas as entidades. (Read)
    * GET /entidades/{id}: Obtém uma entidade específica (Read)
    * POST /entidades: Cria uma nova entidade. (Create)
    * PUT /entidades/{id}: Atualiza uma entidade existente (Update).
    * DELETE /entidades/{id}: Remove uma entidade. (Delete)

* Vantagens do Spring Data REST
    * Redução de Código: Menos código boilerplate para escrever e manter.
    * Hypermedia: Suporte integrado para HATEOAS, facilitando a navegação entre recursos.
    * Customização: Possibilidade de personalizar os endpoints e adicionar lógica de negócios específica quando necessário.

## **HATEOAS (Hypermedia as the Engine of Application State)**

* **HATEOAS** (Hypermedia as the Engine of Application State) é um princípio fundamental da arquitetura REST. Ele sugere que a interação entre o cliente e o servidor deve ser guiada por hipermídia. Em outras palavras, as respostas do servidor não apenas contêm os dados solicitados, mas também links que indicam as ações possíveis a seguir.

* Quando um cliente faz uma solicitação a um servidor RESTful, a resposta não deve apenas fornecer os dados solicitados, mas também incluir links para outras operações relacionadas. Isso permite que o cliente navegue pela API de maneira dinâmica, sem precisar conhecer previamente a estrutura completa da API.

    * Por exemplo, ao solicitar informações sobre um usuário, a resposta pode incluir links para atualizar ou excluir esse usuário, ou para acessar informações relacionadas, como os pedidos feitos por esse usuário.

* **Analogia**: Pense em **HATEOAS** como um GPS para APIs.

    * Imagine que você está dirigindo em uma cidade desconhecida e precisa chegar a um destino específico. Sem um GPS, você precisaria conhecer todas as ruas e direções de antemão. Isso é como uma API **sem** HATEOAS, onde o cliente precisa conhecer todas as URLs e endpoints possíveis.

    * Com um GPS, você simplesmente insere seu destino e ele guia você passo a passo, fornecendo direções conforme você avança. Se você errar uma curva, o GPS recalcula a rota e oferece novas direções. Isso é como uma API **com** HATEOAS, onde cada resposta fornece links para as próximas ações possíveis, guiando o cliente através da navegação na API sem a necessidade de conhecimento prévio de todas as rotas possíveis.

