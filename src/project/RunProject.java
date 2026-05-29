package project;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 * Executa a aplicação simulando o comportamento do sistema.
 * <p>
 * Funciona como o ponto de entrada principal para testar o fluxo completo
 * dos casos de uso (criar, configurar, publicitar, licitar e encerrar leilões).
 * </p>
 * @author Salvador Gonçalves, Miguel Sousa, Daniel Santos, Tomás Farinha / Grupo 75
 * @version 1.0
 */
public class RunProject {
	/**
     * Construtor por omissão.
     * Como esta classe apenas executa o método main, não precisa de ser instanciada.
     */
    public RunProject() {
    }
    
    /**
     * Inicia a execução da aplicação de simulação.
     *
     * @param args Argumentos da linha de comandos (não utilizados neste projeto).
     * @throws FileNotFoundException Caso não seja possível criar ou aceder ao ficheiro de output.
     */
	    
    public static void main(String[] args) throws FileNotFoundException {
        execute();
    }

    /**
     * Executa uma simulação em particular, instanciando as classes e testando as regras de negócio.
     *
     * @throws FileNotFoundException Caso não seja possível escrever no ficheiro "output.txt".
     */
    private static void execute() throws FileNotFoundException {
        PrintWriter writer = new PrintWriter("./out/output.txt");

        System.out.println(" INÍCIO DO SISTEMA DE GESTÃO DE LEILÕES ONLINE \n");

        // Prepara-se o sistema
        CatalogoDeLeiloes catalogo = new CatalogoDeLeiloes();
        HistoricoLeiloes historico = new HistoricoLeiloes();
        GestorLeilao gestor = new GestorLeilao(catalogo, historico);

        // Criam-se os utilizadores
        Utilizador vendedor = new Utilizador(1, "VendedoraAna", "hash123", "ana@ciencias.pt", "Lisboa");
        Utilizador comprador1 = new Utilizador(2, "JoaoFCUL", "hash456", "joao@ciencias.pt", "Porto");
        Utilizador comprador2 = new Utilizador(3, "MariaCadi", "hash789", "maria@ciencias.pt", "Faro");

        System.out.println("Utilizadores criados com sucesso!");

        // UC01: CRIAR LEILÃO 
        System.out.println("\n[UC01] Criar Leilão...");
        Artigo art1 = new Artigo(101, "Portátil Gaming", "Portátil super rápido", "Usado");
        Leilao leilao1 = new Leilao(1001, vendedor, art1, "Leilão de Portátil", "Aproveite a oportunidade, o portátil apenas foi usado um ano!");
        catalogo.adicionarLeilao(leilao1);
        System.out.println("> Leilão '" + art1.getTitulo() + "' criado e adicionado ao catálogo.");

        // UC02: CONFIGURAR LEILÃO
        System.out.println("\n[UC02] Configurar Leilão...");
        // Atualizado: Agora passamos o Título e a Descrição como definimos na arquitetura!
        leilao1.configurar("Leilão de Portátil", "Aproveite a oportunidade!", LocalDateTime.now().plusDays(1), 450.0, 1000.0, "Informática");
        System.out.println("> Leilão configurado. Valor Inicial: 450.0€");

        // UC03: PUBLICITAR LEILÃO 
        System.out.println("\n[UC03] Publicitar Leilão...");
        leilao1.publicitar();
        System.out.println("> Estado do Leilão: " + leilao1.getEstado());

        // UC04: LICITAR EM LEILÃO 
        System.out.println("\n[UC04] Licitar Leilão...");
        
        System.out.println("- João tenta licitar 300€ (Abaixo do inicial):");
        leilao1.licitar(comprador1, 300.0); // O Information Expert bloqueia isto
        
        System.out.println("\n- João tenta licitar 550€ (Válido):");
        leilao1.licitar(comprador1, 550.0); 
        
        System.out.println("\n- Maria tenta licitar 500€ (Abaixo do valor atual do João):");
        leilao1.licitar(comprador2, 500.0); 
        
        System.out.println("\n- Maria tenta licitar 650€ (Válido):");
        leilao1.licitar(comprador2, 650.0);

        // UC05: ENCERRAR LEILÃO
        System.out.println("\n[UC05] Encerrar Leilão...");
        // O Gestor é quem vai fechar e arquivar o leilão
        gestor.encerrarLeilao(1001);

        // Verificar Histórico
        System.out.println("\n[VERIFICAÇÃO] Histórico de Utilizadores...");
        System.out.println("Leilões no histórico do João (perdeu): " + historico.consultarHistorico(2).size());
        System.out.println("Leilões no histórico da Maria (ganhou): " + historico.consultarHistorico(3).size());
        System.out.println("Leilões no histórico da Ana (vendeu): " + historico.consultarHistorico(1).size());

        System.out.println("\n FIM DA SIMULAÇÃO ");

        writer.println("Simulação concluída com sucesso. Verifica a consola para ver os detalhes da execução!");
        writer.close();

    }
}