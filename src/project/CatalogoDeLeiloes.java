package project;
import java.util.HashMap;
import java.util.Map;

/**
 * Gere a coleção de leilões ativos no sistema.
 * <p>
 * Esta classe atua como uma base de dados em memória, utilizando um Map
 * para associar o ID único de cada leilão ao respetivo objeto Leilao.
 * </p>
 * @author Salvador Gonçalves, Miguel Sousa, Daniel Santos, Tomás Farinha / Grupo 75
 * @version 1.0
 */

public class CatalogoDeLeiloes {
	
	private Map<Integer, Leilao> leiloesAtivos;
	
	/**
	 * Construtor por omissão.
	 * Inicializa o catálogo de leilões com um Map vazio.
	 */
	public CatalogoDeLeiloes() {
		this.leiloesAtivos = new HashMap<>();
	}
	
	/**
	 * Adiciona um novo leilão à lista de leilões ativos.
	 * O método verifica se o objeto passado não é nulo antes de o guardar,
	 * utilizando o ID do leilão como chave de pesquisa rápida.
	 * @param leilao O objeto Leilao que acabou de ser criado e que se pretende guardar.
	 */
	public void adicionarLeilao(Leilao leilao) {
		if(leilao != null) {
			this.leiloesAtivos.put(leilao.getIdLeilao(), leilao);
		}
	}
	
	/**
	 * Procura um leilão específico no catálogo através do seu ID.
	 * @param idLeilao O identificador único numérico do leilão que se quer pesquisar.
	 * @return O objeto Leilao correspondente ao ID fornecido, ou nulo (null) caso o leilão não exista no catálogo.
	 */
	public Leilao getLeilao(int idLeilao) {
		return this.leiloesAtivos.get(idLeilao); 
	}
	
	/**
	 * Remove um leilão do catálogo de leilões ativos.
	 * Esta operação é geralmente chamada quando um leilão termina o seu prazo (encerrado) 
	 * ou é cancelado, deixando assim de estar disponível para pesquisa pública.
	 * @param idLeilao O identificador numérico do leilão a ser removido.
	 */
	public void removerLeilao(int idLeilao) {
		this.leiloesAtivos.remove(idLeilao);
	}
	
	
}


