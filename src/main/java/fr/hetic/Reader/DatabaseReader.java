package fr.hetic.Reader;

import fr.hetic.Arguments.Arguments;
import fr.hetic.FileHandler.FileHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseReader implements ReaderStrategy {
    private final String jdbcUrl;
    private final String username;
    private final String password;

    public DatabaseReader(String jdbcUrl, String username, String password) {
        this.jdbcUrl = jdbcUrl;
        this.username = username;
        this.password = password;
    }

    @Override
    public void read() {
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
                    String destinationPath = "/Users/lounisord/Desktop/Cours/MT4/tp-java-siber/src/main/java/fr/hetic/results/";
                    FileHandler.fileCreation(operationsData, destinationPath);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to load MySQL JDBC driver", e);
        }
    }
}
