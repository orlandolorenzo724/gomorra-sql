package co.aurasphere.gomorrasql.utils;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;


@SuppressWarnings("ALL")
public class PropertiesUtils {

    public static String getConnectionString() {
        Yaml yaml = new Yaml();
        try (InputStream inputStream = PropertiesUtils.class
                .getClassLoader()
                .getResourceAsStream("connection.yml")) {

            if (inputStream == null) {
                throw new IllegalArgumentException("File not found: connection.yml");
            }

            // Load the YAML content into a Map
            Map<String, Object> config = yaml.load(inputStream);

            Map<String, Object> connection = (Map<String, Object>) config.get("connection");

            String host = (String) connection.get("host");
            int port = (int) connection.get("port");
            String username = (String) connection.get("username");
            String password = (String) connection.get("password");
            String database = (String) connection.get("database");

            if (isConfigurationNotValid(host, port, username, password, database)) {
                throw new IllegalArgumentException("Invalid connection configuration in connection.yml");
            }

            return String.format(
                    "jdbc:postgresql://%s:%d/%s?user=%s&password=%s",
                    host, port, database, username, password
            );
        } catch (Exception e) {
            throw new RuntimeException("Error reading connection.yml", e);
        }
    }

    private static boolean isConfigurationNotValid(String host, int port, String username, String password, String database) {
        if (host == null || host.isEmpty() ||
                username == null || username.isEmpty() ||
                password == null || password.isEmpty() ||
                database == null || database.isEmpty()) {
            return true;
        }

        if (port <= 0 || port > 65535) {
            return true;
        }

        return false;
    }
}
