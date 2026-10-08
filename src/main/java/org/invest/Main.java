
package org.invest;

import java.io.IOException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

public class Main {

    public static void main(String[] args) throws IOException {

        String caminho = "cotacoes.xlsx";

        // Lê o Excel
        LeitorExcel leitor = new LeitorExcel(caminho);

        List<List<Object>> dados = leitor.getDados();

        // Cria a conexão
        Conexao conexao = new Conexao();
        JdbcTemplate jdbcTemplate = conexao.conectar();

        // Importa os dados
        ImportadorExcel importador =
                new ImportadorExcel(jdbcTemplate);

        importador.importar(dados);
    }
}