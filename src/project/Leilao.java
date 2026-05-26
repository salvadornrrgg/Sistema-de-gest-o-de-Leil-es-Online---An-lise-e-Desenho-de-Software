package project;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Leilao {
	
	private int idLeilao;
	private Utilizador vendedor;
    private Artigo artigo;
    private List<Licitacao> licitacoes;
	private String tituloLeilao;
	private String descricaoLeilao;
	private LocalDateTime dataInicio;
	private LocalDateTime dataFim;
	private double valorInicial;
	private double precoCompreJa;
	private double valorAtual;
	private String categoria;
	private String estado;
	private Utilizador vencedor;
	
	// UC01 - Criar Leilao
	public Leilao(int idLeilao, Utilizador vendedor, Artigo artigo, String tituloLeilao, String descricaoLeilao) {
		this.idLeilao = idLeilao;
		this.vendedor = vendedor;
        this.artigo = artigo;
        this.tituloLeilao = tituloLeilao;
        this.descricaoLeilao = descricaoLeilao;
        this.estado = "Criado";
        this.licitacoes = new ArrayList<>();
        this.valorAtual = 0.0;
	}
	
	// UC02 - Configurar Leilao	
	public void configurar(LocalDateTime dataFim, double valorInicial, double precoCompreJa, String categoria) {
        if (this.estado.equals("Criado")) {
            this.dataFim = dataFim;
            this.valorInicial = valorInicial;
            this.precoCompreJa = precoCompreJa;
            this.categoria = categoria;
            this.valorAtual = valorInicial; 
            this.estado = "Configurado";
        }
    }
	
	// UC03 - Publicitar Leilao	
	public void publicitar() {
        if (this.estado.equals("Configurado")) {
            this.dataInicio = LocalDateTime.now();
            this.estado = "Ativo";
        }
    }

	// UC04 - Licitar em Leilao	
    public boolean licitar(Utilizador licitante, double valor) {

    	if (!this.estado.equals("Ativo")) {
            System.out.println("Erro: O leilão não está ativo.");
            return false;
        }
        
        if (LocalDateTime.now().isAfter(this.dataFim)) {
            System.out.println("Erro: O leilão já expirou.");
            return false;
        }

        if (valor <= this.valorAtual) {
            System.out.println("Erro: O valor tem de ser superior à licitação atual (" + this.valorAtual + ").");
            return false;
        }

        Licitacao novaLicitacao = new Licitacao(this.licitacoes.size() + 1, valor, licitante);
        this.licitacoes.add(novaLicitacao);
        
        this.valorAtual = valor;
        this.vencedor = licitante;
        
        System.out.println("Sucesso: " + licitante.getUsername() + " licitou " + valor + "€");
        return true;
    }
    
	// UC05 - Encerrar Leilao	
    public void fechar() {
        this.estado = "Encerrado";
    }
    
    
    public int getIdLeilao() { return idLeilao; }
    public Utilizador getVendedor() { return vendedor; }
    public Utilizador getVencedor() { return vencedor; }
    public String getEstado() { return estado; }
    public double getValorAtual() { return valorAtual; }
    public LocalDateTime getDataFim() { return dataFim; }
}
