package com.nohana.config.migration;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.SQLException;

import com.nohana.config.ConnectionConfig;

import liquibase.Liquibase;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MigrationStrategy {
    private final Connection conn;

    public void execute() {
        // Altera a saída padrão do sistema devido a aplicação ser console. Do contrário não é necessário
        // Salvando a saída padrão
        PrintStream originalOut = System.out;
        PrintStream originalErr = System.err;
        try {
            // Alterando a saída para o arquivo "liquibase.log"
            FileOutputStream fos = new FileOutputStream("liquibase.log");
            System.setOut(new PrintStream(fos));
            System.setErr(new PrintStream(fos));

            // Executando as migrações
            executeMigration();
        } catch (IOException | SQLException | LiquibaseException e) {
            e.printStackTrace();
        }
        // Restaurando a saída original
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    private void executeMigration() throws IOException, SQLException, LiquibaseException {
        Connection conn = ConnectionConfig.getConnection();
        JdbcConnection jdbcConnection = new JdbcConnection(conn);
        Liquibase liquibase = new Liquibase(
                "db/changelog/db.changelog-master.yml",
                new ClassLoaderResourceAccessor(),
                jdbcConnection);
        liquibase.update();
    }
}
