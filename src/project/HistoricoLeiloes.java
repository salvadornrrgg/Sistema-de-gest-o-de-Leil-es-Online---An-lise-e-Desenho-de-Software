package project;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class HistoricoLeiloes {

    private Map<Integer, Leilao> leiloesEncerrados;
    private Map<Integer, Leilao> leiloesCancelados;

    public HistoricoLeiloes() {
        this.leiloesEncerrados = new HashMap<>();
        this.leiloesCancelados = new HashMap<>();
    }
    
    public void arquivar(Leilao leilao) {
        if (leilao != null) {
            this.leiloesEncerrados.put(leilao.getIdLeilao(), leilao);
        }
    }
    
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
}
