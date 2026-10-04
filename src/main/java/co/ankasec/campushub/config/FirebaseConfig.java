package co.ankasec.campushub.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Configuration
public class FirebaseConfig {

    @Value("${app.firebase.storage-bucket}")
    private String storageBucket;

    @Value("${app.firebase.credentials-json:}")
    private String credentialsJson;

    @PostConstruct
    public void initialize() {
        try (InputStream serviceAccount = resolveCredentials()) {
            if (serviceAccount == null) {
                System.err.println("Firebase henüz konfigüre edilmedi: credentials bulunamadı");
                return;
            }

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setStorageBucket(storageBucket)
                    .build();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
            }
        } catch (Exception e) {
            System.err.println("Firebase henüz konfigüre edilmedi: " + e.getMessage());
        }
    }

    private InputStream resolveCredentials() throws Exception {
        if (credentialsJson != null && !credentialsJson.isBlank()) {
            return new ByteArrayInputStream(credentialsJson.getBytes(StandardCharsets.UTF_8));
        }

        ClassPathResource resource = new ClassPathResource("serviceAccountKey.json");
        if (resource.exists()) {
            return resource.getInputStream();
        }
        return null;
    }
}