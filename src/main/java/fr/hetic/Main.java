package fr.hetic;


import fr.hetic.Reader.DatabaseReader;
import fr.hetic.Reader.DirectoryReader;
import fr.hetic.Reader.Reader;
import fr.hetic.Reader.ReaderStrategy;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

import static fr.hetic.PrintUtil.PrintUtil.log;


public class Main {

    public final String resultPath = "/Users/lounisord/Desktop/Cours/MT4/tp-java-siber/src/main/java/fr/hetic/inputs";


    public static void main(String[] args) throws IOException {

        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        DirectoryReader directoryReader = (DirectoryReader) context.getBean("directoryReader");
        DatabaseReader databaseReader = (DatabaseReader) context.getBean("databaseReader");


        final Properties properties = new Properties();
        properties.load(new FileInputStream("/Users/lounisord/Desktop/Cours/MT4/tp-java-siber/src/main/resources/application.properties"));
        String source = properties.getProperty("data.source");

        if (Objects.equals(source, "FILE")) {

            // ReaderStrategy directoryReader = new DirectoryReader(resultPath);
            directoryReader.read();
        } else if (Objects.equals(source, "JDBC")) {
 /*           String jdbcUrl = properties.getProperty("db.jdbcUrl");
            String username = properties.getProperty("db.username");
            String password = properties.getProperty("db.password");
*/
            databaseReader.read();
        } else {
            throw new IOException("Unsupported source: " + source);
        }
    }

}
