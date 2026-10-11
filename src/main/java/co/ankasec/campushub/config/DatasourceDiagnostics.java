package co.ankasec.campushub.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DatasourceDiagnostics implements ApplicationRunner {

    private static final Logger log = LoggerFactory.getLogger(DatasourceDiagnostics.class);

    @Value("${spring.datasource.url}")
    private String datasourceUrl;

    @Value("${spring.datasource.username}")
    private String datasourceUsername;

    @Override
    public void run(ApplicationArguments args) {
        String host = datasourceUrl;
        int schemeEnd = host.indexOf("://");
        if (schemeEnd >= 0) {
            host = host.substring(schemeEnd + 3);
        }
        int slash = host.indexOf('/');
        if (slash >= 0) {
            host = host.substring(0, slash);
        }
        int at = host.lastIndexOf('@');
        if (at >= 0) {
            host = host.substring(at + 1);
        }

        log.info("DB host: {}", host);
        log.info("DB username: {}", datasourceUsername);
        log.info("DB url uses pooler: {}", host.contains("pooler"));
        log.info("DB url has sslmode: {}", datasourceUrl.contains("sslmode="));
    }
}
