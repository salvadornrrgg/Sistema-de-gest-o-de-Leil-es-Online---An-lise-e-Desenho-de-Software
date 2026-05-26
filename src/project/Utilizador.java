package project;
import java.util.List;
import java.time.LocalDateTime;

public class Utilizador {
	
	private int idUtilizador;
	private String username;
	private String passwordHash;
	private String email;
	private String morada;
	
	public Utilizador(int idUtilizador, String username, String passwordHash, String email, String morada) {
		this.idUtilizador = idUtilizador;
		this.username = username;
		this.passwordHash = passwordHash;
		this.email = email;
		this.morada = morada;
	}
	
	public void criarLeilao(String tituloLeilao, String descricaoLeilao, String tituloArtigo, String descricaoArtigo, String condicao, List<String> fotos) {
		
	}
	
	
	public void configurarLeilao(int idLeilao, String tituloLeilao, String descricaoLeilao, LocalDateTime dataFim, double valorInicial, double precoCompreJa, String categoria) {
		; 
	}
	
	public void publicitarLeilao(int idLeilao) {
		;
	}
	
	public void efetuarLicitacao(int idLeilao, double valor) {
		;
	}
	
	public void notificarDispFormulario(Leilao leilao) {
		;
	}
	
	
	public int getIdUtilizador() { return idUtilizador; }
	public String getUsername() { return username; }
	public String getEmail() { return email; }

	
}

