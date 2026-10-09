package org.invest;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Log {

    private static final DateTimeFormatter FORMATADOR =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    private static final String ARQUIVO = "log_banco.txt";

    public static void registrar(String acao, String tabela, String detalhes) {
        String linha = String.format("[%s] %s | tabela: %s | %s",
                LocalDateTime.now().format(FORMATADOR), acao, tabela, detalhes);

        System.out.println(linha);

        try (PrintWriter writer = new PrintWriter(new FileWriter(ARQUIVO, true))) {
            writer.println(linha);
        } catch (IOException e) {
            System.out.println("Erro ao gravar o log: " + e.getMessage());
        }
    }
}