package project;
import java.util.HashMap;
import java.util.Map;

public class CatalogoDeLeiloes {
	
	private Map<Integer, Leilao> leiloesAtivos;
	
	
	public CatalogoDeLeiloes() {
		this.leiloesAtivos = new HashMap<>();
	}
	
	public void adicionarLeilao(Leilao leilao) {
		if(leilao != null) {
			this.leiloesAtivos.put(leilao.getIdLeilao(), leilao);
		}
	}
	
	
	public Leilao getLeilao(int idLeilao) {
		return this.leiloesAtivos.get(idLeilao); 
	}
	
	public void removerLeilao(int idLeilao) {
		this.leiloesAtivos.remove(idLeilao);
	}
	
	
}


