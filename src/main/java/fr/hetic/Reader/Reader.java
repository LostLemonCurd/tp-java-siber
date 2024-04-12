package fr.hetic.Reader;

import fr.hetic.Arguments.Arguments;
import fr.hetic.Calculators.CalculatorTwoLeRetour;
import fr.hetic.FileHandler.FileHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;

import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import static fr.hetic.PrintUtil.PrintUtil.log;

public class Reader {
    @Autowired
    private MethodInvokingFactoryBean fileReader;

    public static void invokeStartProcessing(String args) throws FileNotFoundException {
        CalculatorTwoLeRetour.startProcessing(args);
    }

    public void fileReader(String filePath) throws FileNotFoundException {
        CalculatorTwoLeRetour.startProcessing(filePath);
    }



    public void dbReader(String jdbcUrl, String username, String password) {
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

    public void setFileReader(MethodInvokingFactoryBean fileReader) {
    }
}
