
package org.invest;

import java.io.IOException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

public class Main {

    public static void main(String[] args) throws IOException {

        // Não deixa o LOG4j2 mostrar a mensagem interna no console.
        System.setProperty("log4j2.statusLoggerLevel", "OFF");

        String caminho = "cotacoes.xlsx";
        Log.registrar("INICIO", "-", "Sistema iniciado");

        // Lê o Excel
        LeitorExcel leitor = new LeitorExcel(caminho);
        List<List<Object>> dados = leitor.getDados();
        Log.registrar("LEITURA", "-",
                "Arquivo " + caminho + " lido: " + (dados.size() - 1) + " linha(s) de dados");

        // Cria a conexão
        Conexao conexao = new Conexao();
        JdbcTemplate jdbcTemplate = conexao.conectar();
        Log.registrar("CONEXAO", "scoreinvest", "Conexão com o banco configurada");

        // Importa os dados
        ImportadorExcel importador = new ImportadorExcel(jdbcTemplate);
        importador.importar(dados);

        Log.registrar("FIM", "-", "Sistema encerrado");
    }
}