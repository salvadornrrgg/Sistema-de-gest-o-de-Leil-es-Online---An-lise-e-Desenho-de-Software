package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import project.Artigo;
import project.CatalogoDeLeiloes;
import project.GestorLeilao;
import project.HistoricoLeiloes;
import project.Leilao;
import project.Utilizador;

class GestorLeilaoTest {

    private CatalogoDeLeiloes catalogo;
    private HistoricoLeiloes historico;
    private GestorLeilao gestor;
    private Leilao leilao;

    // Prepara o ambiente instanciando as bases de dados em memória e o controlador.
    @BeforeEach
    void setUp() {
        catalogo = new CatalogoDeLeiloes();
        historico = new HistoricoLeiloes();
        gestor = new GestorLeilao(catalogo, historico);
        
        Utilizador vendedor = new Utilizador(1, "Ana", "hash", "ana@ciencias.pt", "Lisboa");
        Utilizador comprador = new Utilizador(2, "Joao", "hash", "joao@ciencias.pt", "Porto");
        Artigo artigo = new Artigo(100, "Artigo", "Desc", "Novo");
        
        leilao = new Leilao(10, vendedor, artigo, "Leilao Teste", "Descricao");
        
        // Simular um leilão ativo com um vencedor
        leilao.configurar("Titulo", "Desc", LocalDateTime.now().plusDays(1), 50.0, 100.0, "Cat");
        leilao.publicitar();
        leilao.licitar(comprador, 60.0); // João passa a ser o vencedor
        
        catalogo.adicionarLeilao(leilao);
    }

    /**
     * Teste Black-box: Testa o processo completo de fecho de um leilão.
     * Foca-se nas saídas esperadas deste caso de uso: o leilão tem de mudar de estado,
     * sair do catálogo ativo e passar a constar no histórico de quem participou.
     */
    @Test
    void testEncerrarLeilaoComSucesso() {
        // Execução
        gestor.encerrarLeilao(10);
        
        // Verificação
        assertEquals("Encerrado", leilao.getEstado(), "O estado do leilão deve ser 'Encerrado'.");
        assertNull(catalogo.getLeilao(10), "O leilão deve ter sido removido do catálogo de ativos.");
        assertEquals(1, historico.consultarHistorico(2).size(), "O leilão deve constar no histórico do João (vencedor).");
    }

    /**
     * Teste Glass-box: Cobre a condição else (if l != null) do método encerrarLeilao.
     * Garante que o sistema não "rebenta" (NullPointerException) ao tentar fechar 
     * um ID que não existe na memória.
     */
    @Test
    void testEncerrarLeilaoInexistente() {
        // Execução com ID falso
        gestor.encerrarLeilao(999);
        
        // Verificação
        assertEquals(0, historico.consultarHistorico(1).size(), "O histórico deve continuar vazio, pois o leilão não existia.");
    }
    
    
    /**
     * Teste Glass-box: Cobre o cenário de encerramento de um leilão sem vencedor.
     * Garante que o GestorLeilao processa corretamente a notificação alternativa
     * e arquiva o leilão na mesma.
     */
    @Test
    void testEncerrarLeilaoSemVencedor() {
        // Criamos um leilão extra no catálogo sem adicionar nenhuma licitação
        Utilizador vendedorExtra = new Utilizador(99, "VendedorIsolado", "hash", "email@pt", "Local");
        project.Leilao leilaoVazio = vendedorExtra.criarLeilao(999, "Artigo", "Desc", 99, "Cat", "Desc", "Novo", new java.util.ArrayList<>());
        catalogo.adicionarLeilao(leilaoVazio);
        
        // Executamos o encerramento (o que vai acionar o nosso novo "else")
        gestor.encerrarLeilao(999);
        
        // Verificamos se foi arquivado na mesma
        assertNull(catalogo.getLeilao(999), "O leilão deve ter saído do catálogo.");
        assertNotNull(historico.getLeilao(999), "O leilão vazio deve estar no histórico.");
    }
}