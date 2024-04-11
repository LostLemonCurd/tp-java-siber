package fr.hetic;
import fr.hetic.Calculators.CalculatorTwoLeRetour;

import java.io.FileNotFoundException;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static fr.hetic.PrintUtil.PrintUtil.log;

public class Main {

    private static final String resultPath = "/Users/lounisord/Desktop/Cours/MT4/tp-java-siber/src/main/java/fr/hetic/results/test.txt";


    public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException {
        // CalculatorTwoLeRetour.startProcessing(args[0]);

        String jdbcUrl = "jdbc:postgresql://SG-hetic-mt4-java-5275-pgsql-master.servers.mongodirector.com:5432/TP";
        String username = "etudiant";
        String password = "MT4@hetic2324";

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            System.out.println("Connection to PostGreSQL done");

            try (Statement statement = connection.createStatement()) {
                // Execute a query to select all records from a table (replace "tableName" with the actual table name)
                String query = "SELECT * FROM LIGNE";
                try (ResultSet resultSet = statement.executeQuery(query)) {
                    // Iterate through the result set and process each row
                    while (resultSet.next()) {
                        int id = resultSet.getInt("ID");
                        int param1 = resultSet.getInt("PARAM1");
                        int param2 = resultSet.getInt("PARAM2");
                        String operator = resultSet.getString("OPERATEUR");
                        int index = resultSet.getInt("INDEX");
                        int fichierId = resultSet.getInt("FICHIER_ID");

                        System.out.println(id + " " + param1 + " " + param2 + " " + operator + " " + index + " " + fichierId);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to load MySQL JDBC driver", e);
        }
    }
}
