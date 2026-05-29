package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import project.Artigo;
import project.CatalogoDeLeiloes;
import project.Leilao;
import project.Utilizador;

class CatalogoDeLeiloesTest {

    private CatalogoDeLeiloes catalogo;
    private Leilao leilao;

    // Inicializa um catálogo vazio e um leilão de teste antes de cada método.
    @BeforeEach
    void setUp() {
        catalogo = new CatalogoDeLeiloes();
        
        Utilizador vendedor = new Utilizador(1, "Ana", "hash", "ana@ciencias.pt", "Lisboa");
        Artigo artigo = new Artigo(100, "Artigo", "Descricao", "Novo");
        leilao = new Leilao(10, vendedor, artigo, "Leilao Teste", "Descricao");
    }

    /**
     * Teste Black-box: Verifica o comportamento esperado das funções de guardar e procurar.
     * Insere um leilão válido e garante que o sistema consegue recuperá-lo usando o seu ID,
     * validando o fluxo normal de dados.
     */
    @Test
    void testAdicionarEProcurarLeilao() {
        // Execução
        catalogo.adicionarLeilao(leilao);
        Leilao encontrado = catalogo.getLeilao(10);
        
        // Verificação
        assertNotNull(encontrado, "O leilão devia ter sido encontrado no catálogo.");
        assertEquals(10, encontrado.getIdLeilao(), "O ID do leilão encontrado deve ser igual ao inserido.");
    }

    /**
     * Teste Glass-box: Cobre a condição de segurança if(leilao != null).
     * Garante que a estrutura de dados interna não é corrompida por tentativas de 
     * guardar referências nulas.
     */
    @Test
    void testAdicionarLeilaoNulo() {
        // Execução
        catalogo.adicionarLeilao(null);
        
        // Verificação
        assertNull(catalogo.getLeilao(10), "O catálogo não deve conter nada se tentarmos adicionar um nulo.");
    }

    /**
     * Teste Black-box: Verifica o comportamento de remoção de dados.
     * Simula o fim do ciclo de vida de um leilão (quando este é encerrado e deve 
     * sair da lista de ativos).
     */
    @Test
    void testRemoverLeilao() {
        // Preparação extra e Execução
        catalogo.adicionarLeilao(leilao);
        catalogo.removerLeilao(10);
        
        // Verificação
        Leilao encontrado = catalogo.getLeilao(10);
        assertNull(encontrado, "O leilão não devia existir no catálogo após ser removido.");
    }
}