package libraries;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.util.Properties;

public class TestBase {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestBase.class);
    public static Properties prop;

    static {
        prop = new Properties();
        FileInputStream ip;
        try {
            String fileName = "api_config.properties";
            LOGGER.info("***** Environment configs loading :{}", fileName);
            ip = new FileInputStream("src\\test\\resources\\" + fileName);
            prop.load(ip);
        } catch (Exception e) {
            LOGGER.error("Exception", e);
            System.exit(1);
        }
    }

    public static String getProperty(String key) {
        return prop.getProperty(key);
    }

    public static String reqresBaseUrl(){
        return getProperty("ReqresBaseUrl").trim();
    }

    public static String fakeApiRequestActivitiesUrl(){
        return getProperty("FakeRestAPIBaseUrl").trim();
    }
}
