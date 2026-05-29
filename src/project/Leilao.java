package project;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa um leilão no sistema, sendo a entidade central (Information Expert) 
 * responsável por gerir o ciclo de vida da venda de um artigo.
 * <p>
 * Esta classe gere as transições de estado (Criado, Configurado, Ativo, Encerrado)
 * e aplica as regras de negócio críticas, como a validação de licitações, verificação de prazos
 * e a manutenção do histórico interno de ofertas.
 * </p>
 * @author Salvador Gonçalves, Miguel Sousa, Daniel Santos, Tomás Farinha / Grupo 75
 * @version 1.0
 */

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
	
	/**
	 * UC01 - Cria um novo leilão em estado inicial ("Criado").
	 * @param idLeilao O identificador único numérico do leilão.
	 * @param vendedor O utilizador que está a colocar o artigo à venda.
	 * @param artigo O artigo físico ou digital que será leiloado.
	 * @param tituloLeilao O título atrativo ou resumido do leilão.
	 * @param descricaoLeilao As regras, termos ou descrição geral associada à venda.
	 */
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
	
	/**
	 * UC02 - Configura os parâmetros financeiros e de tempo do leilão.
	 * Apenas leilões no estado "Criado" podem ser configurados. Se a operação tiver sucesso,
	 * o estado avança para "Configurado".
	 * @param tituloLeilao O novo título do leilão que quero substituir.
	 * @param descricaoLeilao A nova descrição do leilão que quero substituir.
	 * @param dataFim A data e hora exata em que o leilão deixa de aceitar licitações.
	 * @param valorInicial O montante base pelo qual as licitações devem começar.
	 * @param precoCompreJa O valor opcional que permite a um comprador fechar imediatamente o leilão.
	 * @param categoria A categoria onde o leilão se insere (ex: Tecnologia, Automóveis).
	 */
	public void configurar(String tituloLeilao, String descricaoLeilao, LocalDateTime dataFim, double valorInicial, double precoCompreJa, String categoria) {
        if (this.estado.equals("Criado")) {
            this.tituloLeilao = tituloLeilao;
            this.descricaoLeilao = descricaoLeilao;
            this.dataFim = dataFim;
            this.valorInicial = valorInicial;
            this.precoCompreJa = precoCompreJa;
            this.categoria = categoria;
            this.valorAtual = valorInicial; 
            this.estado = "Configurado";
        }
    }
	
	/**
	 * UC03 - Publicita e abre o leilão ao público.
	 * Muda o estado do leilão de "Configurado" para "Ativo", registando a data e hora
	 * de início. A partir deste momento, o leilão aceita licitações.
	 */
	public void publicitar() {
        if (this.estado.equals("Configurado")) {
            this.dataInicio = LocalDateTime.now();
            this.estado = "Ativo";
        }
    }

	/**
	 * UC04 - Processa uma tentativa de licitação por parte de um utilizador.
	 * <p>
	 * O método atua como Especialista de Informação, validando três regras fundamentais:
	 * 1. O leilão tem de estar no estado "Ativo".
	 * 2. O prazo (dataFim) não pode ter sido ultrapassado.
	 * 3. O valor oferecido tem de ser estritamente superior ao valor atual em vigor.
	 * </p>
	 * @param licitante O utilizador que deseja fazer a oferta.
	 * @param valor O montante monetário oferecido na licitação.
	 * @return true se a licitação for válida e registada, false se for rejeitada pelas regras de negócio.
	 */
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
    
    /**
	 * UC05 - Altera o estado do leilão para encerrado.
	 * Este método é chamado pelo GestorLeilao quando se conclui o processo de fecho.
	 */
    public void fechar() {
        this.estado = "Encerrado";
    }
    
    /** 
     * Obtém o identificador do leilão.
     * @return O identificador único do leilão. 
     */    
    public int getIdLeilao() {
    	return idLeilao; 
    }
    
    /** 
     * Obtém o vendedor do leilão.
     * @return O utilizador que atua como vendedor neste leilão. 
     */    
    public Utilizador getVendedor() {
    	return vendedor; 
    }
    
    /** 
     * Obtém o vencedor atual do leilão.
     * @return O utilizador que venceu, ou que lidera atualmente o leilão. 
     */
    public Utilizador getVencedor() { 
    	return vencedor; 
    }
    
    /** 
     * Obtém o estado do leilão.
     * @return O estado atual do ciclo de vida do leilão (ex: Ativo, Encerrado). 
     */
    public String getEstado() { 
    	return estado; 
    }
    
    /**
     * Obtém o valor atual do leilão.
     * @return O valor monetário mais alto licitado até ao momento. 
     */
    public double getValorAtual() {
    	return valorAtual; 
    }
    
    /**
     * Obtém a data de fim do leilão.
     * @return A data e hora de término planeada para o leilão. 
     */
    public LocalDateTime getDataFim() { 
    	return dataFim; 
    }
    
}
