package project;
import java.time.LocalDateTime;

/**
 * Representa uma oferta monetária (licitação) feita por um utilizador num leilão.
 * <p>
 * Regista o valor oferecido, o utilizador que fez a oferta (licitante) e a data/hora exata
 * em que a licitação ocorreu. Controla também o estado da licitação (ex: Ativa, Anulada).
 * </p>
 * @author Salvador Gonçalves, Miguel Sousa, Daniel Santos, Tomás Farinha / Grupo 75
 * @version 1.0
 */

public class Licitacao {
	
	private int idLicitacao;
	private double valor;
	private LocalDateTime dataHora;
	private String estado;
	private Utilizador licitante;
	
	/**
	 * Cria uma nova licitação com o valor e o licitante especificados.
	 * A data e hora são registadas automaticamente no momento exato da criação e o estado inicial é definido como "Ativa".
	 * @param idLicitacao O identificador único numérico da licitação.
	 * @param valor O montante monetário oferecido.
	 * @param licitante O utilizador (comprador) que está a fazer a oferta.
	 */
	public Licitacao(int idLicitacao, double valor, Utilizador licitante) {
		this.idLicitacao = idLicitacao;
		this.valor = valor;
		this.licitante = licitante;
		this.dataHora = LocalDateTime.now();
		this.estado = "Ativa";
	}
	
	/**
	 * Obtém o valor da licitação.
	 * @return O valor monetário desta licitação. 
	 */
	public double getValor() {
		return this.valor;
	}
	
	/**
	 * Obtém o utilizador licitante.
	 * @return O utilizador que realizou esta licitação. 
	 */
	public Utilizador getLicitante() {
		return this.licitante; 
	}
	
	/**
	 * Altera o estado da licitação para "Anulada".
	 * Pode ser usado caso uma oferta precise de ser invalidada pelo sistema ou por um gestor.
	 */
	public void anular() {
		this.estado = "Anulada";
	}
	
	/**
	 * Obtém a data e hora da licitação.
	 * @return A data e hora exatas em que a licitação foi registada pelo sistema. 
	 */
	public LocalDateTime getDataHora() {
		return this.dataHora;
	}
	
	/**
	 * Obtém o estado da licitação.
	 * @return O estado atual da licitação (ex: Ativa, Anulada). 
	 */
	public String getEstado() {
		return this.estado;
	}
	
	/**
	 * Obtém o identificador da licitação.
	 * @return O identificador único numérico desta licitação. 
	 */
	public int getIdLicitacao() {
	    return this.idLicitacao;
	}
	

}
