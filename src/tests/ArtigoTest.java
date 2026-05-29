package tests;

// IMPORTS DO JUNIT 5 (JUPITER)
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import project.Artigo;

class ArtigoTest {

    private Artigo artigo;

    // O @BeforeEach garante que temos um artigo limpo antes de cada teste começar.
    @BeforeEach
    void setUp() {
        // Preparação
        artigo = new Artigo(100, "Bicicleta", "Bicicleta de Montanha", "Usado");
    }

    // Teste Black-box: Verifica se o construtor base guarda as informações corretamente.
    @Test
    void testCriacaoArtigo() {
        // Verificação dos dados simples (A mensagem de texto agora fica no fim!)
        assertEquals(100, artigo.getIdArtigo(), "O ID tem de ser 100.");
        assertEquals("Bicicleta", artigo.getTitulo(), "O título tem de coincidir.");
        assertEquals("Usado", artigo.getCondicao(), "A condição tem de ser Usado.");
        
        // Verificação da inicialização segura da lista
        assertNotNull(artigo.getFotos(), "A lista de fotos tem de ser inicializada.");
        assertEquals(0, artigo.getFotos().size(), "A lista de fotos tem de começar vazia.");
    }

    // Teste Black-box: Verifica o comportamento normal de adicionar uma foto válida.
    @Test
    void testAdicionarFotoValida() {
        // Execução
        artigo.adicionarFoto("http://site.com/foto.jpg");
        
        // Verificação
        assertEquals(1, artigo.getFotos().size(), "A lista deve ter agora 1 foto.");
        assertEquals("http://site.com/foto.jpg", artigo.getFotos().get(0), "O URL da foto deve ser o mesmo que foi inserido.");
    }

    // Teste Glass-box: Cobre a condição de proteção interna de strings vazias ou nulas.
    @Test
    void testAdicionarFotoInvalida() {
        // Execução com inputs corrompidos
        artigo.adicionarFoto(null); 
        artigo.adicionarFoto("");   
        artigo.adicionarFoto("   "); 
        
        // Verificação
        assertEquals(0, artigo.getFotos().size(), "O sistema tem de rejeitar fotos inválidas, a lista deve continuar a 0.");
    }

}