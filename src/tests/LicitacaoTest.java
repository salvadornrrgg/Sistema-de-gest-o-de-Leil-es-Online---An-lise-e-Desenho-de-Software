package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import project.Licitacao;
import project.Utilizador;

class LicitacaoTest {

    private Utilizador comprador;
    private Licitacao licitacao;

    // Inicializa os objetos necessários antes de cada teste.
    @BeforeEach
    void setUp() {
        comprador = new Utilizador(2, "Joao", "hash", "joao@ciencias.pt", "Porto");
        licitacao = new Licitacao(1, 150.0, comprador);
    }

    /**
     * Teste Black-box: Valida a criação de uma licitação e as suas regras de negócio iniciais.
     * Verifica se os valores monetários e o estado automático ("Ativa") são atribuídos 
     * corretamente sem olhar para a implementação interna de tempo.
     */
    @Test
    void testCriacaoLicitacao() {
        // Verificação
        assertEquals(1, licitacao.getIdLicitacao(), "O ID da licitação deve ser 1.");
        assertEquals(150.0, licitacao.getValor(), 0.001, "O valor da licitação deve ser 150.0.");
        assertEquals(comprador, licitacao.getLicitante(), "O licitante deve ser o João.");
        assertEquals("Ativa", licitacao.getEstado(), "O estado inicial deve ser 'Ativa'.");
        assertNotNull(licitacao.getDataHora(), "A data e hora devem ser registadas automaticamente.");
    }

    /**
     * Teste Glass-box: Testa a alteração forçada de estado de uma licitação.
     * Garante que o método anular() acede e modifica diretamente a variável de estado 
     * interna para "Anulada", cobrindo esse fluxo específico.
     */
    @Test
    void testAnularLicitacao() {
        // Execução
        licitacao.anular();
        
        // Verificação
        assertEquals("Anulada", licitacao.getEstado(), "O estado da licitação após ser anulada deve ser 'Anulada'.");
    }
}