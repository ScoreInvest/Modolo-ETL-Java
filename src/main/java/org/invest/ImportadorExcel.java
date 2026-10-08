
package org.invest;

import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class ImportadorExcel {

    private final JdbcTemplate jdbcTemplate;

    public ImportadorExcel(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void importar(List<List<Object>> dados) {

        String sql = """
            INSERT INTO cotacoes (
                data_cotacao,
                ticker,
                preco_abertura,
                preco_fechamento,
                preco_maximo,
                preco_minimo,
                volume
            )
            VALUES (?, ?, ?, ?, ?, ?, ?)
            """;

        for (int i = 1; i < dados.size(); i++) {

            List<Object> linha = dados.get(i);

            if (linha.size() < 7) {
                throw new IllegalArgumentException(
                        "Linha " + (i + 1) +
                                " possui menos de 7 colunas."
                );
            }

            LocalDate data = LocalDate.parse(
                    linha.get(0).toString().trim()
            );

            String ticker = linha.get(1).toString().trim();

            BigDecimal abertura = decimal(linha.get(2));
            BigDecimal fechamento = decimal(linha.get(3));
            BigDecimal maxima = decimal(linha.get(4));
            BigDecimal minima = decimal(linha.get(5));
            BigDecimal volume = decimal(linha.get(6));

            jdbcTemplate.update(
                    sql,
                    Date.valueOf(data),
                    ticker,
                    abertura,
                    fechamento,
                    maxima,
                    minima,
                    volume
            );

            System.out.println(
                    "Cotação importada: " + ticker + " - " + data
            );
        }

        System.out.println("Importação concluída!");
    }

    private BigDecimal decimal(Object valor) {

        String texto = valor.toString()
                .trim()
                .replace(",", ".");

        return new BigDecimal(texto);
    }
}