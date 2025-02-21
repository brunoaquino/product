import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

public class JwtKeyGenerator{
    public static void main(String[] args) {
        // Gerar uma chave segura para HS512
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

        // Imprimir a chave como uma string base64
        String base64Key = java.util.Base64.getEncoder().encodeToString(key.getEncoded());
        System.out.println("Chave segura (Base64): " + base64Key);
    }

}
