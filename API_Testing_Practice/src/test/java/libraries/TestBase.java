package libraries;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class TestBase {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestBase.class);
    public static Properties prop;
    public static String ENV;

    static {
        prop = new Properties();
        try {
            ENV = System.getProperty("env");
            if (ENV == null || ENV.isEmpty()) {
                ENV = "qa";
            }
            LOGGER.info("***** Running in Environment: {}", ENV);

            String fileName = "api_config.properties";
            LOGGER.info("***** Environment configs loading :{}", fileName);

            InputStream ip = TestBase.class
                    .getClassLoader()
                    .getResourceAsStream(fileName);

            if (ip == null) {
                throw new RuntimeException("File not found in classpath: " + fileName);
            }

            prop.load(ip);

        } catch (Exception e) {
            LOGGER.error("Exception", e);
            throw new RuntimeException(e);
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
