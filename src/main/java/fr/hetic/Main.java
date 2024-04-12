package fr.hetic;


import fr.hetic.Reader.Reader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

import static fr.hetic.PrintUtil.PrintUtil.log;


public class Main {

    private static final String resultPath = "/Users/lounisord/Desktop/Cours/MT4/tp-java-siber/src/main/java/fr/hetic/inputs";


    public static void main(String[] args) throws IOException, ClassNotFoundException {

        final Properties properties = new Properties();
        properties.load(new FileInputStream("/Users/lounisord/Desktop/Cours/MT4/tp-java-siber/src/main/resources/application.properties"));
        System.out.println(properties.getProperty("data.source"));
        String source = properties.getProperty("data.source");
        if (Objects.equals(source, "FILE")) {
            Reader.fileReader(resultPath);
        } else if (Objects.equals(source, "JDBC")) {
            String jdbcUrl = properties.getProperty("db.jdbcUrl");
            String username = properties.getProperty("db.username");
            String password = properties.getProperty("db.password");
            Reader.dbReader(jdbcUrl, username, password);
        } else {
            throw new IOException("Unsupported source: " + source);
        }
    }

}
