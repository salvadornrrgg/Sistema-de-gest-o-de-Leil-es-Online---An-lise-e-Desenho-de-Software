package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import project.Artigo;
import project.Leilao;
import project.Utilizador;

class LeilaoTest {

    private Leilao leilao;
    private Utilizador vendedor;
    private Utilizador comprador;
    private Artigo artigo;

    // O @BeforeEach prepara o sistema com dados limpos antes de cada teste.
    @BeforeEach
    void setUp() {
        vendedor = new Utilizador(1, "VendAna", "hash", "ana@ciencias.pt", "Lisboa");
        comprador = new Utilizador(2, "CompJoao", "hash", "joao@ciencias.pt", "Porto");
        artigo = new Artigo(100, "Artigo Teste", "Descricao", "Novo");
        
        // Estado inicial será "Criado"
        leilao = new Leilao(1, vendedor, artigo, "Leilao Teste", "Leilao de prova");
    }

    /**
     * Teste Black-box: Verifica o estado inicial e a configuração.
     * Valida que o leilão transita do estado "Criado" para "Configurado" quando
     * recebe os parâmetros financeiros corretos.
     */
    @Test
    void testConfigurarLeilao() {
        // Execução
        leilao.configurar("Novo Titulo", "Nova Desc", LocalDateTime.now().plusDays(2), 50.0, 100.0, "Eletrónica");
        
        // Verificação
        assertEquals("Configurado", leilao.getEstado(), "O estado deve avançar para 'Configurado'.");
        assertEquals(50.0, leilao.getValorAtual(), 0.001, "O valor atual deve arrancar igual ao valor inicial.");
    }

    /**
     * Teste Glass-box (Caixa Branca): Testa o caminho lógico em que o leilão não está ativo.
     * Cobre o primeiro 'if' do método licitar(), garantindo que a estrutura interna bloqueia
     * licitações prematuras (ainda no estado "Criado").
     */
    @Test
    void testLicitarLeilaoNaoAtivo() {
        // Execução
        boolean resultado = leilao.licitar(comprador, 100.0);
        
        // Verificação
        assertFalse(resultado, "Não deve ser possível licitar num leilão que não está ativo.");
    }

    /**
     * Teste Glass-box (Caixa Branca): Testa o caminho lógico em que a data de fim já passou.
     * Cobre especificamente a condição (if) relacionada com a verificação de tempo limite.
     */
    @Test
    void testLicitarLeilaoExpirado() {
        // Preparação: Configuramos a data de fim para ONTEM (minusDays(1))
        leilao.configurar("Titulo", "Desc", LocalDateTime.now().minusDays(1), 50.0, 100.0, "Cat");
        leilao.publicitar(); // Muda para "Ativo"
        
        // Execução
        boolean resultado = leilao.licitar(comprador, 100.0);
        
        // Verificação
        assertFalse(resultado, "O sistema não deve aceitar licitações após o prazo ter expirado.");
    }

    /**
     * Teste Black-box (Caixa Preta): Testa a regra de negócio de licitar abaixo do preço atual.
     * Foca-se nos inputs e outputs da regra de negócio de sobreposição de valores.
     */
    @Test
    void testLicitarValorInsuficiente() {
        // Preparação
        leilao.configurar("Titulo", "Desc", LocalDateTime.now().plusDays(2), 50.0, 100.0, "Cat");
        leilao.publicitar();
        
        // Execução: Tenta licitar 40€ num leilão que começou a 50€
        boolean resultado = leilao.licitar(comprador, 40.0);
        
        // Verificação
        assertFalse(resultado, "A licitação tem de ser recusada por ter um valor inferior ao atual.");
    }

    /**
     * Teste de Caminho Feliz (Black-box): Garante que uma licitação válida atualiza os dados.
     * Testa o cenário de sucesso onde todas as regras são cumpridas.
     */
    @Test
    void testLicitarComSucesso() {
        // Preparação
        leilao.configurar("Titulo", "Desc", LocalDateTime.now().plusDays(2), 50.0, 100.0, "Cat");
        leilao.publicitar();
        
        // Execução
        boolean resultado = leilao.licitar(comprador, 75.0);
        
        // Verificação
        assertTrue(resultado, "A licitação válida deve ser aceite.");
        assertEquals(75.0, leilao.getValorAtual(), 0.001, "O valor atual do leilão tem de ser atualizado.");
        assertEquals(comprador, leilao.getVencedor(), "O comprador tem de ficar registado como o vencedor atual.");
    }
}