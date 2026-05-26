package project;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Executa a aplicação
 * @author ...
 */
public class RunProject {

    /**
     * Inicia a execução da aplicação e/ou de testes scriptados
     *
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        execute();
    }

    /**
     * Uma execução em particular
     *
     * @throws FileNotFoundException
     */
    private static void execute() throws FileNotFoundException {
        PrintWriter writer = new PrintWriter("./out/output.txt");

        // ...

        writer.close();
    };
}
