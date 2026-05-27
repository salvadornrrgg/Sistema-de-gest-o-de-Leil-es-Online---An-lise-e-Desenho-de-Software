package project;

/**
 * Controlador (Pure Fabrication) responsável por orquestrar o encerramento e processamento de leilões.
 * <p>
 * O gestor atua como intermediário entre o catálogo de leilões ativos e o histórico,
 * garantindo que as regras de negócio de encerramento (alteração de estado, notificações 
 * aos utilizadores e transferência entre listas) são cumpridas rigorosamente.
 * </p>
 * @author Salvador Gonçalves, Miguel Sousa, Daniel Santos, Tomás Farinha / Grupo 75
 * @version 1.0
 */

public class GestorLeilao {
	
	private int idGestor;
	private CatalogoDeLeiloes catalogoLeiloes;
	private HistoricoLeiloes historicoLeiloes;
	
	/**
	 * Construtor do Gestor de Leilão.
	 * @param catalogoLeiloes A instância do catálogo onde os leilões ativos estão guardados.
	 * @param historicoLeiloes A instância do histórico para onde os leilões encerrados vão ser movidos.
	 */
	public GestorLeilao(CatalogoDeLeiloes catalogoLeiloes, HistoricoLeiloes historicoLeiloes) {
		this.catalogoLeiloes = catalogoLeiloes;
		this.historicoLeiloes = historicoLeiloes;
	}
	
	/**
	 * Executa o processo completo de encerramento de um leilão.
	 * <p>
	 * A sequência de operações inclui: procurar o leilão no catálogo, fechá-lo,
	 * identificar e notificar o vencedor e o vendedor para disponibilizar o formulário
	 * de avaliação, remover o leilão dos ativos e arquivá-lo no histórico.
	 * </p>
	 * @param idLeilao O identificador numérico único do leilão que deve ser encerrado.
	 */
	public void encerrarLeilao(int idLeilao) {
				Leilao l = this.catalogoLeiloes.getLeilao(idLeilao);
				
				if (l != null) {
					l.fechar();
					
					Utilizador vencedor = l.getVencedor();
					Utilizador vendedor = l.getVendedor();
					
					if (vencedor != null) {
						vencedor.notificarDispFormulario(l);
					}
					if (vendedor != null) {
						vendedor.notificarDispFormulario(l);
					}
					
					this.catalogoLeiloes.removerLeilao(idLeilao);
					
					this.historicoLeiloes.arquivar(l);
					
					System.out.println("Leilão " + idLeilao + " encerrado e arquivado com sucesso!");
				} else {
					System.out.println("Erro: Leilão " + idLeilao + " não encontrado no catálogo.");
				}
	}
	
	/**
	 * Obtém o identificador único deste gestor.
	 * @return O ID do gestor.
	 */
	public int getIdGestor() {
	    return this.idGestor;
	}
}



