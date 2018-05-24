package rsweb.pageObjects;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
public class PropertyReader {
    private Properties properties;
    private final String propertyFilePath= "configs//rsConfigurtion.properties";


    public PropertyReader(){
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(propertyFilePath));
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
        }
    }
    public String getUrl(){
        String url;
        url=properties.getProperty("url");
        if(url != null) return url;
        else throw new RuntimeException("url not specified in the rsConfiguration.properties file.");
    }
    public String getChromeDriver(){
        String chromeDriver;
        chromeDriver=properties.getProperty("chromeDriverPath");
        if(chromeDriver != null) return chromeDriver;
        else throw new RuntimeException("chromeDriver not specified in the rsConfiguration.properties file.");
    }
    public String getProductsToOrder(){
        String productsWithQnty;
        productsWithQnty=properties.getProperty("productsToOrderWithQuantity");
        if(productsWithQnty != null) return productsWithQnty;
        else throw new RuntimeException("chromeDriver not specified in the rsConfiguration.properties file.");
    }
    public Integer getImplicitWait(){
        Integer implicitWait;
        implicitWait=Integer.parseInt(properties.getProperty("implicitWait"));
        if(implicitWait != null) return implicitWait;
        else throw new RuntimeException("implicitWait not specified in the rsConfiguration.properties file.");
    }
    public String getReportConfigPath(){
        String reportConfigPath = properties.getProperty("reportConfigPath");
        if(reportConfigPath!= null) return reportConfigPath;
        else throw new RuntimeException("Report Config Path not specified in the Configuration.properties file for the Key:reportConfigPath");
    }
    public String getsearchFilterkey(){
        String searchFilterkey = properties.getProperty("searchFilterkey");
        if(searchFilterkey!= null) return searchFilterkey;
        else throw new RuntimeException("searchFiterkey not specified in the Configuration.properties file for the Key:reportConfigPath");
    }
    public String getUserEmil(){
        String email = properties.getProperty("useremail");
        if(email!= null) return email;
        else throw new RuntimeException("email not specified in the Configuration.properties file for the Key:reportConfigPath");
    }

}
