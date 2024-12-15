package edu.bbte.idde.vcim2315.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

public class ConfigurationFactory {
    private static final Logger LOG = LoggerFactory.getLogger(ConfigurationFactory.class);
    private static final String CONFIG_FILE = "/application";

    private static MainConfiguration mainConfiguration = new MainConfiguration();

    static {
        StringBuilder sb = new StringBuilder();
        sb.append(CONFIG_FILE);

        String profile = System.getenv("PROFILE");

        LOG.info("Determined profile: {}", profile);
        if (profile != null && !profile.isBlank()) {
            sb.append('-').append(profile);
        }

        sb.append(".json");
        ObjectMapper objectMapper = new ObjectMapper();

        try (InputStream inputStream = ConfigurationFactory.class.getResourceAsStream(sb.toString())) {
            mainConfiguration = objectMapper.readValue(inputStream, MainConfiguration.class);
            LOG.info("Read following configuration: {}", mainConfiguration);
        } catch (IOException e) {
            LOG.error("Error loading configuration", e);
        }
    }

    public static MainConfiguration getMainConfiguration() {
        return mainConfiguration;
    }

    public static JdbcConfiguration getJdbcConfiguration() {
        return mainConfiguration.getJdbcConfiguration();
    }


    public static DaoConfiguration getDaoConfiguration() {
        return mainConfiguration.getDaoConfiguration();
    }
}
