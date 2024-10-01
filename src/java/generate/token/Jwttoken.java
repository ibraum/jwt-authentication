/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package generate.token;

import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.JWT;
import entity.User;
import jakarta.enterprise.context.RequestScoped;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

/**
 *
 * @author kondoibrahim
 */
@RequestScoped
public class Jwttoken {
    private static final long EXPIRATION_DATE = 12000;
    private static Properties prop = new Properties();
    
    
    public static String getTokenWithJavaJwt (String username) throws IOException{
        
            String filepath = "/home/kondoibrahim/Bureau/coding/java/pratice/basicauthentification/jwtauthentification/src/application.properties";
        
            FileInputStream file = new FileInputStream(filepath);
            prop.load(file);
            file.close();
            String secret = prop.getProperty("jwt.secret");
            
            Algorithm algo = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("auth0")
                    .withClaim("username", username)
                    .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_DATE))
                    .sign(algo);
            
            return token;
        
        
    }
    
        public static String getConnection (String username) {
            return "you are connected as " + username;
        }
}
