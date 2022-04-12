package rifqimuhammadaziz;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesApp {
    public static void main(String[] args) throws FileNotFoundException {

        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src/main/java/rifqimuhammadaziz/themes/application.properties"));
            String host = properties.getProperty("database.host");
            System.out.println(host);
        } catch (IOException e) {
            e.printStackTrace();
        }


//        Properties properties = new Properties();
//        properties.put("testing.name", "Rifqi Muhammad Aziz");
//        try {
//            properties.store(new FileOutputStream("testing.properties"), "error");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
