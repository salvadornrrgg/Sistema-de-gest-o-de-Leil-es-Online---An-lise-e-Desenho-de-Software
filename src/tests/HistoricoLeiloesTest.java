package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import project.Artigo;
import project.HistoricoLeiloes;
import project.Leilao;
import project.Utilizador;

class HistoricoLeiloesTest {

    private HistoricoLeiloes historico;
    private Leilao leilao;
    private Utilizador vendedor;
    private Utilizador vencedor;
    private Utilizador espetador;

    // Prepara o ambiente com vários utilizadores para testar os filtros do histórico.
    @BeforeEach
    void setUp() {
        historico = new HistoricoLeiloes();
        
        vendedor = new Utilizador(1, "Ana", "hash", "ana@ciencias.pt", "Lisboa");
        vencedor = new Utilizador(2, "Joao", "hash", "joao@ciencias.pt", "Porto");
        espetador = new Utilizador(3, "Carlos", "hash", "carlos@ciencias.pt", "Faro");
        
        Artigo artigo = new Artigo(100, "Artigo", "Desc", "Novo");
        leilao = new Leilao(10, vendedor, artigo, "Leilao Teste", "Descricao");
        
        // Simulamos o ciclo do leilão até o João o vencer
        leilao.configurar("Titulo", "Desc", LocalDateTime.now().plusDays(1), 50.0, 100.0, "Cat");
        leilao.publicitar();
        leilao.licitar(vencedor, 60.0);
        leilao.fechar();
    }

    /**
     * Teste Black-box: Verifica as regras de negócio da consulta de histórico.
     * Valida que tanto o vendedor como o vencedor têm acesso ao leilão encerrado,
     * enquanto que um utilizador alheio à transação recebe uma lista vazia.
     */
    @Test
    void testArquivarEConsultarHistorico() {
        // Execução
        historico.arquivar(leilao);
        
        // Verificação - O Vendedor
        List<Leilao> histVendedor = historico.consultarHistorico(1);
        assertEquals(1, histVendedor.size(), "O vendedor deve ter 1 leilão no histórico.");
        assertTrue(histVendedor.contains(leilao), "O histórico do vendedor deve conter este leilão exato.");
        
        // Verificação - O Vencedor
        List<Leilao> histVencedor = historico.consultarHistorico(2);
        assertEquals(1, histVencedor.size(), "O vencedor deve ver o leilão que ganhou no histórico.");
        
        // Verificação - O Espetador (não participou)
        List<Leilao> histEspetador = historico.consultarHistorico(3);
        assertEquals(0, histEspetador.size(), "Um utilizador que não participou deve ter o histórico vazio.");
    }

    /**
     * Teste Glass-box: Cobre a condição estrutural if(leilao != null).
     * Garante a robustez do histórico perante tentativas de arquivar dados nulos.
     */
    @Test
    void testArquivarLeilaoNulo() {
        // Execução
        historico.arquivar(null);
        
        // Verificação
        assertEquals(0, historico.consultarHistorico(1).size(), "O sistema não deve guardar nada se o input for nulo.");
    }
}