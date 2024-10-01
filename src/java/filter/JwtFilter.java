/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package filter;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Provider
public class JwtFilter implements ContainerRequestFilter {

    private static final String SECRET_KEY = "mysecretkey";
    private Properties prop = new Properties();

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException{
        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        String path = requestContext.getUriInfo().getPath();
        
        String filepath = "/home/kondoibrahim/Bureau/coding/java/pratice/basicauthentification/jwtauthentification/src/application.properties";
        
        FileInputStream file = new FileInputStream(filepath);
        prop.load(file);
        file.close();
        
        String secret = prop.getProperty("jwt.secret");
        
        if(authorizationHeader == null || path.equals("/tokenjavaawt") ){
            return;
        }

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
            return;
        }

        String token = authorizationHeader.substring("Bearer".length()).trim();

        try {
            Algorithm algorithm = Algorithm.HMAC256(prop.getProperty("jwt.secret"));
            JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("auth0")
                .build();
            DecodedJWT jwt = verifier.verify(token);

            String username = jwt.getSubject();
            requestContext.setProperty("username", username);
            System.out.println("username ------------------------------ " + username);

        } catch (Exception e) {
            e.printStackTrace();
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }
}
