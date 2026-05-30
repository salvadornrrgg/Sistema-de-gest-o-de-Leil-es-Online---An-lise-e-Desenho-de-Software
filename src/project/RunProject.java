package project;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Executa a aplicação simulando o comportamento do sistema de leilões.
 * <p>
 * Contém um menu inicial que permite ao avaliador escolher entre a execução
 * de testes scriptados (automáticos) ou um teste interativo (manual),
 * cumprindo na íntegra os requisitos do enunciado.
 * </p>
 * @author Salvador Gonçalves, Miguel Sousa, Daniel Santos, Tomás Farinha / Grupo 75
 * @version 1.0
 */
public class RunProject {

    /**
     * Construtor por omissão.
     */
    public RunProject() {
    }

    /**
     * Inicia a execução da aplicação e apresenta o menu de escolha ao utilizador.
     *
     * @param args Argumentos da linha de comandos.
     * @throws FileNotFoundException Caso não seja possível escrever no ficheiro.
     */
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("==================================================");
        System.out.println("    SISTEMA DE GESTÃO DE LEILÕES ONLINE (G75)     ");
        System.out.println("==================================================");
        System.out.println("Escolha o modo de execução:");
        System.out.println("1 - Testes Scriptados (4 Cenários Automáticos)");
        System.out.println("2 - Teste Interativo (Modo Manual)");
        System.out.print("> Opção: ");
        
        int opcao = 0;
        if (scanner.hasNextInt()) {
            opcao = scanner.nextInt();
        }

        if (opcao == 1) {
            executarScriptados();
        } else if (opcao == 2) {
            executarInterativo(scanner);
        } else {
            System.out.println("Opção inválida. A encerrar o sistema.");
        }
        
        scanner.close();
    }

    /**
     * Executa a simulação scriptada (sem interação) exigida pelo enunciado.
     * @throws FileNotFoundException Caso não seja possível aceder ao output.txt.
     */
    private static void executarScriptados() throws FileNotFoundException {
        PrintWriter writer = new PrintWriter("./out/output.txt");

        log("\n>>> A INICIAR MODO SCRIPTADO...\n", writer);

        CatalogoDeLeiloes catalogo = new CatalogoDeLeiloes();
        HistoricoLeiloes historico = new HistoricoLeiloes();
        GestorLeilao gestor = new GestorLeilao(catalogo, historico);

        Utilizador ana = new Utilizador(1, "Ana", "hash", "ana@ciencias.pt", "Lisboa");
        Utilizador joao = new Utilizador(2, "Joao", "hash", "joao@ciencias.pt", "Porto");
        Utilizador maria = new Utilizador(3, "Maria", "hash", "maria@ciencias.pt", "Faro");

        // --- CENÁRIO 1: O CAMINHO FELIZ ---
        log("--- CENÁRIO 1: O CAMINHO FELIZ ---", writer);
        List<String> fotos = new ArrayList<>();
        fotos.add("foto1.jpg");
        
        Leilao leilao1 = ana.criarLeilao(1001, "Portátil Gaming", "Usado 1 ano", 101, "Asus ROG", "I7", "Usado", fotos);
        catalogo.adicionarLeilao(leilao1);
        ana.configurarLeilao(leilao1, "Portátil Gaming", "Como novo", LocalDateTime.now().plusDays(2), 500.0, 1000.0, "Informática");
        ana.publicitarLeilao(leilao1);
        
        log("> Leilão do Portátil Gaming Publicitado. Valor Base: 500.0€", writer);
        log("> João e Maria disputam o leilão. Ana é a vendedora", writer);
        joao.efetuarLicitacao(leilao1, 550.0);
        maria.efetuarLicitacao(leilao1, 600.0);
        joao.efetuarLicitacao(leilao1, 750.0);
        
        gestor.encerrarLeilao(1001);
        log("> Resultado: Leilão Encerrado. Vencedor: " + leilao1.getVencedor().getUsername() + "\n", writer);

        // --- CENÁRIO 2: Regras de Negócio ---
        log("--- CENÁRIO 2: REGRAS DE NEGÓCIO ---", writer);
        Leilao leilao2 = joao.criarLeilao(1002, "Bicicleta", "BTT", 102, "BTT", "Nova", "Novo", new ArrayList<>());
        catalogo.adicionarLeilao(leilao2);
        joao.configurarLeilao(leilao2, "Bicicleta", "Desconto", LocalDateTime.now().plusDays(1), 200.0, 400.0, "Desporto");
        
        log("> Tentativa de licitação antes de publicitar:", writer);
        maria.efetuarLicitacao(leilao2, 250.0); // Bloqueado
        
        joao.publicitarLeilao(leilao2);
        log("\n> Tentativa de licitação de 150€, abaixo do valor base de 200€:", writer);
        maria.efetuarLicitacao(leilao2, 150.0); // Bloqueado
        log("- Leilão continua ativo e sem vencedor.\n", writer);

     // --- CENÁRIO 3: LEILÃO SEM LICITAÇÕES ---
        log("--- CENÁRIO 3: LEILÃO SEM LICITAÇÕES ---", writer);
        Leilao leilao3 = maria.criarLeilao(1003, "Tapete", "Sala", 103, "Tapete", "Lã", "Usado", new ArrayList<>());
        catalogo.adicionarLeilao(leilao3);
        
        log("> Maria configura o leilão com uma data já expirada (há 1 minuto) para testes:", writer);
        maria.configurarLeilao(leilao3, "Tapete", "Barato", LocalDateTime.now().minusMinutes(1), 50.0, 100.0, "Casa");
        maria.publicitarLeilao(leilao3);
        
        log("> Ana tenta licitar mas o tempo já esgotou", writer);
        ana.efetuarLicitacao(leilao3, 60.0); // O sistema bloqueia e avisa a Ana
        
        log("\n> O sistema encerra o leilão e avisa a vendedora da falta de interessados:", writer);
        gestor.encerrarLeilao(1003); // O sistema avisa a Maria (vendedora)
        
        log("> Resultado: Leilão Encerrado sem vencedores.\n", writer);
        
        
        // --- CENÁRIO 4: EMPATE E LIMITES ---
        log("--- CENÁRIO 4: EMPATE E LIMITES ---", writer);
        Leilao leilao4 = ana.criarLeilao(1004, "Relógio", "Vintage", 104, "Relógio", "Ouro", "Usado", new ArrayList<>());
        catalogo.adicionarLeilao(leilao4);
        ana.configurarLeilao(leilao4, "Relógio", "Raro", LocalDateTime.now().plusDays(3), 1000.0, 5000.0, "Moda");
        ana.publicitarLeilao(leilao4);
        
        log("> João licita 1500.0€", writer);
        joao.efetuarLicitacao(leilao4, 1500.0);
        
        log("> Maria tenta empatar licitando exatamente 1500.0€:", writer);
        maria.efetuarLicitacao(leilao4, 1500.0); // Bloqueado
        
        log("> Maria percebe o erro e licita mais 1 cêntimo (1500.01€):", writer);
        maria.efetuarLicitacao(leilao4, 1500.01); // Sucesso
        
        gestor.encerrarLeilao(1004);
        log("> Resultado: Leilão 1004 Encerrado. Vencedor: " + leilao4.getVencedor().getUsername() + " com " + leilao4.getValorAtual() + "€\n", writer);

        log(">>> FIM DA SIMULAÇÃO SCRIPTADA <<<", writer);
        writer.close();
    }

    /**
     * Método auxiliar para escrever texto em simultâneo na consola e no ficheiro de output.
     * @param mensagem A mensagem a ser impressa e guardada.
     * @param writer O PrintWriter encarregue de gravar o ficheiro.
     */
    private static void log(String mensagem, PrintWriter writer) {
        System.out.println(mensagem);
        writer.println(mensagem);
    }
    

    /**
     * Executa a simulação interativa com um adversário virtual (Bot).
     * O leilão termina quando o utilizador digita 0, ou quando ultrapassa
     * o limite de orçamento do Bot.
     * @param scanner O scanner para ler o input do teclado.
     */
    private static void executarInterativo(Scanner scanner) {
        System.out.println("\n>>> A INICIAR MODO INTERATIVO...\n");
        
        Utilizador sistemaVendedor = new Utilizador(99, "Admin", "hash", "admin@sys.pt", "Lisboa");
        Leilao leilao = sistemaVendedor.criarLeilao(999, "Consola Retro", "Anos 90", 99, "Consola", "16-bit", "Usado", new ArrayList<>());
        sistemaVendedor.configurarLeilao(leilao, "Consola Retro", "Rara", LocalDateTime.now().plusDays(1), 100.0, 500.0, "Jogos");
        sistemaVendedor.publicitarLeilao(leilao);
        
        System.out.println("Bem-vindo ao Leilão de uma 'Consola Retro'!");
        System.out.println("O valor base atual é de: " + leilao.getValorAtual() + "€");
        
        System.out.print("Introduza o seu nome de utilizador: ");
        String nome = scanner.next();
        Utilizador utilizadorReal = new Utilizador(100, nome, "hash", "user@sys.pt", "Local");
        Utilizador botAdversario = new Utilizador(101, "JoaoBot", "hash", "bot@sys.pt", "Sistema");
        
        System.out.println("\n> O 'JoaoBot' também está a participar neste leilão! Prepare-se.");

        while (true) {
            System.out.print("\nIntroduza o valor que deseja licitar (ou digite 0 para desistir): ");
            
            if (scanner.hasNextDouble()) {
                double valorLicitacao = scanner.nextDouble();
                
                if (valorLicitacao == 0) {
                    System.out.println("\n> Você desistiu do leilão.");
                    break;
                }
                
                System.out.println("A processar a sua licitação de " + valorLicitacao + "€...");
                boolean sucesso = leilao.licitar(utilizadorReal, valorLicitacao);
                
                if (sucesso) {
                    System.out.println("Parabéns, " + nome + "! A sua oferta foi aceite.");
                    
                    // Lógica do Bot, Se a oferta for igual ou superior a 200€, o Bot desiste.
                    if (valorLicitacao >= 200.0) {
                        System.out.println(">>> O 'JoaoBot' achou esse valor demasiado alto e abandonou o leilão!");
                        break; 
                    } else {
                        // O Bot cobre a tua oferta com mais 10€
                        double contraProposta = valorLicitacao + 10.0;
                        System.out.println(">>> O 'JoaoBot' não desiste e cobre a sua oferta com " + contraProposta + "€!");
                        leilao.licitar(botAdversario, contraProposta);
                    }
                    
                } else {
                    System.out.println("Atenção: A licitação foi recusada! O valor tem de ser superior a " + leilao.getValorAtual() + "€.");
                }
                
            } else {
                System.out.println("Erro: Valor numérico inválido. Certifique-se que inseriu apenas números.");
                scanner.next(); 
            }
        }
        
        String nomeVencedor = (leilao.getVencedor() != null) ? leilao.getVencedor().getUsername() : "Sem vencedor";
        System.out.println("\n==================================================");
        System.out.println(" RESULTADO FINAL DO LEILÃO DA CONSOLA RETRO");
        System.out.println(" Vencedor: " + nomeVencedor);
        System.out.println(" Valor Final Pago: " + leilao.getValorAtual() + "€");
        System.out.println("==================================================");
        
        System.out.println("\n>>> FIM DO MODO INTERATIVO <<<");
    }
}