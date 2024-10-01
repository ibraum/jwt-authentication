# MISE EN PLACE D'UNE AUTHENTIFICATION AVEC JWT (Json Web Token) en JAVA EE


## Etapes
- Téléchargement des bibliothèques nécéssaires
    - java-jwt 4.3.0

- Mise en place de deux fonctions gérants les tokens et celui retournant un message de confirmation de connexion. fichier `Jwttoken.java` situé dans le package `generate.token`

- Implémentation de deux endpoints qui utilises respectivement les deux méthodes. fichier `Test.java` situé dans le package `jwt`

- Mise en place du filtrage de token :

    - Exclusion du endpoint générant le token

    - Vérification de la variable `authorization` du header

    - Vérification de la signature du token

## Technologies
- Java 21
- CDI (Context Dependency and Injection)
- java-awt

## Thanks
    @IBRAUM