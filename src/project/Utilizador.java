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
	
	/**
	 * UC01 - Inicia o processo de criação de um novo leilão no sistema.
	 * Cria internamente o Artigo e o Leilão associado a este utilizador (vendedor).
	 * @param idLeilao O ID do novo leilão.
	 * @param tituloLeilao O título do leilão.
	 * @param descricaoLeilao A descrição geral das regras do leilão.
	 * @param idArtigo O ID do artigo a ser vendido.
	 * @param tituloArtigo O nome do artigo a ser vendido.
	 * @param descricaoArtigo As características do artigo.
	 * @param condicao O estado de conservação do artigo (ex: Novo, Usado).
	 * @param fotos Lista de URLs contendo as fotografias do artigo.
	 * @return A nova instância de Leilao criada.
	 */
	public Leilao criarLeilao(int idLeilao, String tituloLeilao, String descricaoLeilao, int idArtigo, String tituloArtigo, String descricaoArtigo, String condicao, List<String> fotos) {
		Artigo novoArtigo = new Artigo(idArtigo, tituloArtigo, descricaoArtigo, condicao, fotos);
		return new Leilao(idLeilao, this, novoArtigo, tituloLeilao, descricaoLeilao);
	}
	
	/**
	 * UC02 - Configura os parâmetros de negócio de um leilão previamente criado.
	 * Delega a ação diretamente para a classe Leilao, permitindo alterar o título e descrição.
	 * @param leilao O objeto do leilão a configurar.
	 * @param tituloLeilao O novo título do leilão (ou o mesmo se não alterar).
	 * @param descricaoLeilao A nova descrição do leilão.
	 * @param dataFim A data e hora limite para aceitar licitações.
	 * @param valorInicial O montante inicial de licitação.
	 * @param precoCompreJa O valor de compra imediata.
	 * @param categoria A categoria do leilão.
	 */
	public void configurarLeilao(Leilao leilao, String tituloLeilao, String descricaoLeilao, LocalDateTime dataFim, double valorInicial, double precoCompreJa, String categoria) {
		if (leilao != null) {
			leilao.configurar(tituloLeilao, descricaoLeilao, dataFim, valorInicial, precoCompreJa, categoria);
		}
	}
	
	/**
	 * UC03 - Publicita um leilão, tornando-o ativo e visível para a comunidade.
	 * Delega a ação para o próprio leilão.
	 * @param leilao O objeto do leilão a publicitar.
	 */
	public void publicitarLeilao(Leilao leilao) {
		if (leilao != null) {
			leilao.publicitar();
		}
	}
	
	/**
	 * UC04 - Submete uma nova licitação num leilão ativo.
	 * Delega a regra de negócio para o Especialista de Informação (Leilao).
	 * @param leilao O objeto do leilão onde se pretende licitar.
	 * @param valor O montante oferecido.
	 */
	public void efetuarLicitacao(Leilao leilao, double valor) {
		if (leilao != null) {
			leilao.licitar(this, valor);
		}
	}
	
	/**
	 * UC05 - Recebe a notificação do sistema indicando que o leilão terminou
	 * e que o formulário de avaliação da transação está disponível.
	 * @param leilao O leilão que acabou de ser encerrado.
	 */
	public void notificarDispFormulario(Leilao leilao) {
		if (leilao != null) {
			System.out.println("Notificação para [" + this.username + "]: O leilão ID " + leilao.getIdLeilao() + " foi encerrado. Por favor, preencha o formulário de avaliação de transação.");
		}
	}
	
	/**Obtém o identificador do utilizador.
	 * @return O identificador único numérico do utilizador. 
	 */
	public int getIdUtilizador() { return idUtilizador; }
	
	/**
	 * Obtém o nome de utilizador.
	 * @return O nome de utilizador (username). 
	 */
	public String getUsername() { return username; }
	
	/**
	 * Obtém o endereço de email.
	 * @return O endereço de correio eletrónico associado à conta. 
	 */
	public String getEmail() { return email; }

}

