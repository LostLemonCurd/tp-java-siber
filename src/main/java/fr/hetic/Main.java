package fr.hetic;


import fr.hetic.Reader.DatabaseReader;
import fr.hetic.Reader.DirectoryReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;


public class Main {
    public static void main(String[] args) throws IOException {

        // Definition of beans
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        DirectoryReader directoryReader = (DirectoryReader) context.getBean("directoryReader");
        DatabaseReader databaseReader = (DatabaseReader) context.getBean("databaseReader");

        // Determining the correct Reader to use
        final Properties properties = new Properties();
        properties.load(new FileInputStream("/Users/lounisord/Desktop/Cours/MT4/tp-java-siber/src/main/resources/application.properties"));
        String source = properties.getProperty("data.source");

        if (Objects.equals(source, "FILE")) {
            directoryReader.read();
        } else if (Objects.equals(source, "JDBC")) {
            databaseReader.read();
        } else {
            throw new IOException("Unsupported source: " + source);
        }
    }

}
