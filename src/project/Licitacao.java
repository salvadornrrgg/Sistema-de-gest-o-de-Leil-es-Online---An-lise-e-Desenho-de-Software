package project;
import java.time.LocalDateTime;

public class Licitacao {
	
	private int idLicitacao;
	private double valor;
	private LocalDateTime dataHora;
	private String estado;
	private Utilizador licitante;
	
	public Licitacao(int idLicitacao, double valor, Utilizador licitante) {
		this.idLicitacao = idLicitacao;
		this.valor = valor;
		this.licitante = licitante;
		this.dataHora = LocalDateTime.now();
		this.estado = "Ativa";
	}
	
	public double getValor() {
		return this.valor;
	}
	
	
	public Utilizador getLicitante() {
		return this.licitante; 
	}
	
	public void anular() {
		this.estado = "Anulada";
	}
	
	public LocalDateTime getDataHora() {
		return this.dataHora;
	}
	
	public String getEstado() {
		return this.estado;
	}
	
	public int getIdLicitacao() {
	    return this.idLicitacao;
	}
	

}
