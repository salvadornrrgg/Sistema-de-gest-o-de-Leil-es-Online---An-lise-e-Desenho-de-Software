package project;
import java.util.List;
import java.time.LocalDateTime;

/**
 * Representa um ator do sistema, podendo assumir o papel de comprador (licitante) ou vendedor.
 * <p>
 * Esta classe mantém os dados de perfil do utilizador e atua como o ponto de entrada 
 * (Controlador) para as principais operações do sistema, refletindo as ações que uma pessoa
 * real faria na interface (criar, configurar e licitar em leilões).
 * </p>
 * @author Salvador Gonçalves, Miguel Sousa, Daniel Santos, Tomás Farinha / Grupo 75
 * @version 1.0
 */
public class Utilizador {
	
	private int idUtilizador;
	private String username;
	private String passwordHash;
	private String email;
	private String morada;
	
	/**
	 * Cria um novo utilizador no sistema com os seus dados de perfil.
	 * @param idUtilizador O identificador numérico único do utilizador.
	 * @param username O nome de utilizador (login).
	 * @param passwordHash A palavra-passe encriptada por questões de segurança.
	 * @param email O endereço de correio eletrónico de contacto.
	 * @param morada A morada física do utilizador para efeitos de faturação ou envio.
	 */
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

