package fr.hetic;

import fr.hetic.Arguments.Arguments;
import fr.hetic.Calculators.CalculatorTwoLeRetour;
import fr.hetic.FileHandler.FileHandler;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final String resultPath = "/Users/lounisord/Desktop/Cours/MT4/tp-java-siber/src/main/java/fr/hetic/inputs";


    public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException {
        CalculatorTwoLeRetour.startProcessing(resultPath);
        dataBaseProcessing();

    }

    public static void dataBaseProcessing() {
        String jdbcUrl = "jdbc:postgresql://SG-hetic-mt4-java-5275-pgsql-master.servers.mongodirector.com:5432/TP";
        String username = "etudiant";
        String password = "MT4@hetic2324";

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

            try (Statement statement = connection.createStatement()) {
                String query = "SELECT * FROM LIGNE AS l INNER JOIN FICHIER AS f ON l.FICHIER_ID = f.ID WHERE f.TYPE='OP' ORDER BY f.NOM, l.POSITION ASC";

                try (ResultSet resultSet = statement.executeQuery(query)) {
                    List<String[]> data = new ArrayList<>();

                    while (resultSet.next()) {

                        String param1 = resultSet.getString("PARAM1");
                        String param2 = resultSet.getString("PARAM2");
                        String operator = resultSet.getString("OPERATEUR");
                        String position = resultSet.getString("POSITION");
                        String fileName = resultSet.getString("NOM");

                        String[] rowData = {param1, param2, operator, fileName, position};
                        data.add(rowData);
                    }
                    List<Arguments> operationsData = FileHandler.extractDataFromSQL(data);
                    FileHandler.fileCreation(operationsData);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to load MySQL JDBC driver", e);
        }
    }
}
