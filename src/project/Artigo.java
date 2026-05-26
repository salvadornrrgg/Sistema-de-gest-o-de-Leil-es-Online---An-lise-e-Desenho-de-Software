package project;
import java.util.ArrayList; 
import java.util.List;

public class Artigo {
	
	private int idArtigo;
	private String tituloArtigo;
	private String descricaoArtigo;
	private String condicao;
	private List<String> fotos;

	public Artigo(int idArtigo, String tituloArtigo, String descricaoArtigo, String condicao) {
		this.idArtigo = idArtigo;
        this.tituloArtigo = tituloArtigo;
        this.descricaoArtigo = descricaoArtigo;
        this.condicao = condicao;
        this.fotos = new ArrayList<>();
	}
	
	public Artigo(int idArtigo, String tituloArtigo, String descricaoArtigo, String condicao, List<String> fotos) {
        this.idArtigo = idArtigo;
        this.tituloArtigo = tituloArtigo;
        this.descricaoArtigo = descricaoArtigo;
        this.condicao = condicao;
        this.fotos = fotos != null ? fotos : new ArrayList<>();
    }
	
	public void adicionarFoto(String url ) {
		if (url != null && !url.trim().isEmpty()) {
            this.fotos.add(url);
        }
	}
	
	public int getIdArtigo() { 
		return idArtigo; 
	}
	
    public String getTitulo() {
    	return tituloArtigo; 
    }
    
    public String getDescricao() { 
    	return descricaoArtigo; 
    }
    
    public String getCondicao() { 
    	return condicao; 
    }
    
    public List<String> getFotos() { 
    	return fotos; 
    }
    
}
