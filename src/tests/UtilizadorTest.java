package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import project.Leilao;
import project.Utilizador;

class UtilizadorTest {

    private Utilizador utilizador;
    private Leilao leilao;

    // O @BeforeEach prepara o ambiente de testes antes de cada método.
    @BeforeEach
    void setUp() {
        // Preparação
        utilizador = new Utilizador(1, "Maria", "hash", "maria@ciencias.pt", "Faro");
        leilao = utilizador.criarLeilao(10, "Titulo", "Desc", 100, "Artigo", "Desc", "Novo", new ArrayList<>());
    }

    /**
     * Teste Black-box: Verifica a instanciação correta do objeto e os seus getters.
     * Foca-se em garantir que os inputs fornecidos no construtor são devolvidos exatamente 
     * com o mesmo valor, validando a integridade dos dados do perfil.
     */
    @Test
    void testCriacaoUtilizador() {
        // Verificação
        assertEquals(1, utilizador.getIdUtilizador(), "O ID deve ser 1.");
        assertEquals("Maria", utilizador.getUsername(), "O username deve ser Maria.");
        assertEquals("maria@ciencias.pt", utilizador.getEmail(), "O email deve ser maria@ciencias.pt");
    }

    /**
     * Teste Glass-box: Avalia a delegação do Controlador na criação de um leilão.
     * Cobre o caminho em que o Utilizador instancia corretamente as entidades Artigo e Leilao,
     * garantindo que a associação bidirecional (o vendedor do leilão é este utilizador) funciona.
     */
    @Test
    void testCriarLeilao() {
        // Verificação
        assertNotNull(leilao, "O leilão não pode ser nulo.");
        assertEquals(utilizador, leilao.getVendedor(), "O vendedor do leilão criado tem de ser o próprio utilizador.");
        assertEquals("Criado", leilao.getEstado(), "O estado inicial do leilão deve ser 'Criado'.");
    }
    
    
    /**
     * Teste Glass-box: Cobre os métodos de delegação do Controlador.
     * Garante que o Utilizador consegue reencaminhar corretamente os pedidos
     * de configurar, publicitar e licitar para o Especialista de Informação (Leilao).
     */
    @Test
    void testDelegacaoDeOperacoes() {
        // Cobre o configurarLeilao
        utilizador.configurarLeilao(leilao, "T", "D", java.time.LocalDateTime.now().plusDays(1), 10.0, 100.0, "Cat");
        assertEquals("Configurado", leilao.getEstado(), "O utilizador deve conseguir delegar a configuração.");

        // Cobre o publicitarLeilao
        utilizador.publicitarLeilao(leilao);
        assertEquals("Ativo", leilao.getEstado(), "O utilizador deve conseguir delegar a publicitação.");

        // Cobre o efetuarLicitacao
        utilizador.efetuarLicitacao(leilao, 20.0);
        assertEquals(20.0, leilao.getValorAtual(), 0.001, "O utilizador deve conseguir delegar a licitação.");

        // Cobre o notificarDispFormulario (apenas imprime na consola, mas passa na linha de código)
        utilizador.notificarDispFormulario(leilao);
    }
}