package dao; 
import java.io.FileNotFoundException; 
import java.io.IOException; 
import java.util.Properties; 
/** 
* Класс формирования свойств соединения с БД по конфигурационному 
файлу 
*/ 
public class ConnectionProperty { 
// Имя конфигурационного файла 

public static final String CONFIG_NAME = "config.properties"; 
// Свойства конфигурации 
public static final Properties PROPERTY_COFIG = new Properties(); 
public ConnectionProperty() throws FileNotFoundException, 
IOException { 
ClassLoader classLoader = getClass().getClassLoader(); 
PROPERTY_COFIG.load(classLoader.getResourceAsStream("config/" + 
CONFIG_NAME)); 
if (PROPERTY_COFIG.isEmpty()) {
    System.err.println("Файл config.properties не был загружен!");
}
} 
// Получить значение параметра из конфигурации по имени свойства 
public static String getProperty(String property) { 
return PROPERTY_COFIG.getProperty(property); 
} 


} 

