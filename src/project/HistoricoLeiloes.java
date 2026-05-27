package project;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

/**
 * Arquiva e permite a consulta de leilões que já foram encerrados ou cancelados.
 * <p>
 * Funciona como o registo histórico do sistema, garantindo que a informação de transações
 * passadas não se perde e pode ser consultada pelos utilizadores envolvidos.
 * </p>
 * @author Salvador Gonçalves, Miguel Sousa, Daniel Santos, Tomás Farinha / Grupo 75
 * @version 1.0
 */

public class HistoricoLeiloes {

    private Map<Integer, Leilao> leiloesEncerrados;
    private Map<Integer, Leilao> leiloesCancelados;
    
    /**
     * Construtor por omissão do Histórico de Leilões.
     * Inicializa as estruturas de dados (Maps) que vão guardar os leilões separados pelo seu estado final.
     */
    public HistoricoLeiloes() {
        this.leiloesEncerrados = new HashMap<>();
        this.leiloesCancelados = new HashMap<>();
    }
    
    /**
    * Guarda um leilão recém-encerrado no histórico.
    * O leilão passa a ser indexado pelo seu ID para consultas futuras.
    * @param leilao O objeto Leilao que terminou e deve ser arquivado.
    */
    public void arquivar(Leilao leilao) {
        if (leilao != null) {
            this.leiloesEncerrados.put(leilao.getIdLeilao(), leilao);
        }
    }
    
    /**
     * Pesquisa e devolve todo o histórico de leilões associado a um determinado utilizador.
     * <p>
     * O método percorre os registos e seleciona os leilões em que o utilizador 
     * participou com sucesso, ou seja, onde foi o vendedor do artigo ou o vencedor da licitação final.
     * </p>
     * @param idUser O identificador único numérico do utilizador a pesquisar.
     * @return Uma lista (List) contendo todos os leilões encerrados onde o utilizador interveio (como vendedor ou vencedor).
     */
    public List<Leilao> consultarHistorico(int idUser) {
    	List<Leilao> historicoUtilizador = new ArrayList<>(); 
    	
    	for (Leilao l : this.leiloesEncerrados.values()) {
            if ((l.getVendedor() != null && l.getVendedor().getIdUtilizador() == idUser) ||
                (l.getVencedor() != null && l.getVencedor().getIdUtilizador() == idUser)) {
                historicoUtilizador.add(l);
            }
        }
        
        return historicoUtilizador;
    }
    
}
