package co.ankasec.campushub.helper;

import org.springframework.stereotype.Component;

@Component
public class JwtUtils {


    public boolean validateToken(String token) {
        return token != null && !token.isBlank();
    }

    public String getEmailFromToken(String token) {
        return token;
    }
}