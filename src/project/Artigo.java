package project;
import java.util.ArrayList; 
import java.util.List;

/**
 * Representa um artigo físico ou digital que será colocado em leilão.
 * <p>
 * Esta classe armazena todos os detalhes descritivos do produto, 
 * como o seu título, descrição, condição de uso e uma galeria de fotografias.
 * </p>
 * @author Salvador Gonçalves, Miguel Sousa, Daniel Santos, Tomás Farinha / Grupo 75
 * @version 1.0
 */

public class Artigo {
	
	private int idArtigo;
	private String tituloArtigo;
	private String descricaoArtigo;
	private String condicao;
	private List<String> fotos;

	/**
	 * Construtor base para criar um Artigo sem fotografias iniciais.
	 * A lista de fotografias é inicializada vazia.
	 * @param idArtigo O identificador único do artigo no sistema.
	 * @param tituloArtigo O nome ou título curto do artigo.
	 * @param descricaoArtigo A descrição detalhada das características do artigo.
	 * @param condicao O estado de conservação do artigo, "Novo", "Usado", "Como Novo".
	 */
	
	public Artigo(int idArtigo, String tituloArtigo, String descricaoArtigo, String condicao) {
		this.idArtigo = idArtigo;
        this.tituloArtigo = tituloArtigo;
        this.descricaoArtigo = descricaoArtigo;
        this.condicao = condicao;
        this.fotos = new ArrayList<>();
	}
	
	/**
	 * Construtor completo para criar um Artigo já com uma lista de fotografias.
	 * Se a lista de fotos passada for nula, inicializa uma lista vazia por segurança.
	 * @param idArtigo O identificador único do artigo no sistema.
	 * @param tituloArtigo O nome ou título curto do artigo.
	 * @param descricaoArtigo A descrição detalhada das características do artigo.
	 * @param condicao O estado de conservação do artigo (ex: "Novo", "Usado").
	 * @param fotos Uma lista contendo os URLs ou caminhos das fotografias do artigo.
	 */
	
	public Artigo(int idArtigo, String tituloArtigo, String descricaoArtigo, String condicao, List<String> fotos) {
        this.idArtigo = idArtigo;
        this.tituloArtigo = tituloArtigo;
        this.descricaoArtigo = descricaoArtigo;
        this.condicao = condicao;
        this.fotos = fotos != null ? fotos : new ArrayList<>();
    }
	
	/**
	 * Adiciona uma nova fotografia à galeria do artigo.
	 * O método valida se o URL fornecido não é nulo nem vazio antes de o adicionar.
	 * @param url O caminho (link ou diretório local) da fotografia a adicionar.
	 */
	public void adicionarFoto(String url ) {
		if (url != null && !url.trim().isEmpty()) {
            this.fotos.add(url);
        }
	}
	
	/**
	 * Obtém o identificador único do artigo.
	 * @return O ID do artigo.
	 */
	public int getIdArtigo() { 
		return idArtigo; 
	}
	
	/**
	 * Obtém o título do artigo.
	 * @return O título do artigo.
	 */
    public String getTitulo() {
    	return tituloArtigo; 
    }
    
    /**
     * Obtém a descrição detalhada do artigo.
     * @return A descrição do artigo.
     */
    public String getDescricao() { 
    	return descricaoArtigo; 
    }
    
    /**
     * Obtém o estado de conservação atual do artigo.
     * @return A condição do artigo.
     */
    public String getCondicao() { 
    	return condicao; 
    }
    
    /**
     * Obtém a lista com todas as fotografias associadas ao artigo.
     * @return Uma lista de Strings contendo os URLs das fotos.
     */
    public List<String> getFotos() { 
    	return fotos; 
    }
    
}
