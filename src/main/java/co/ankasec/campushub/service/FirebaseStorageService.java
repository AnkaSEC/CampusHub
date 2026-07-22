package co.ankasec.campushub.service;

import com.google.cloud.storage.Blob;
import com.google.firebase.cloud.StorageClient;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Service
public class FirebaseStorageService {

    public String uploadPdf(MultipartFile file) throws IOException {
        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

        Blob blob = StorageClient.getInstance().bucket().create(
                "verifications/" + fileName,
                file.getInputStream(),
                file.getContentType()
        );

        return String.format("https://firebasestorage.googleapis.com/v0/b/%s/o/%s?alt=media",
                blob.getBucket(),
                URLEncoder.encode("verifications/" + fileName, StandardCharsets.UTF_8));
    }
}