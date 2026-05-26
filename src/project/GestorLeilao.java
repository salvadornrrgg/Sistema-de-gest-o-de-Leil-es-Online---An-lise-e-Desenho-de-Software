package project;

public class GestorLeilao {
	
	private int idGestor;
	private CatalogoDeLeiloes catalogoLeiloes;
	private HistoricoLeiloes historicoLeiloes;
	
	
	public GestorLeilao(CatalogoDeLeiloes catalogoLeiloes, HistoricoLeiloes historicoLeiloes) {
		this.catalogoLeiloes = catalogoLeiloes;
		this.historicoLeiloes = historicoLeiloes;
	}
	
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
	
	public int getIdGestor() {
	    return this.idGestor;
	}
}



