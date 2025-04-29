package com.nohana;

import java.sql.SQLException;

import com.nohana.config.ConnectionConfig;
import com.nohana.config.migration.MigrationStrategy;
import com.nohana.view.Menu;

public class App {
    public static void main(String[] args) {
        try (var conn = ConnectionConfig.getConnection()) {
            MigrationStrategy migration = new MigrationStrategy(conn);
            migration.execute();
            new Menu().execute();
            
        } catch (SQLException ex) { 
            ex.printStackTrace();
        }
    }
}
