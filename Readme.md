# Projet Java J2EE

Vous trouverez sur ce repository Github le contenu de notre projet de Java J2EE réaliser à l'aide de Spring.

# Lancement

## Java 

Afin de pouvoir développer et d'exécuter ce programme, vous devez avoir préalablement installé [Java JDK](https://www.oracle.com/java/technologies/downloads/).
## Intellij IDEA

Le projet n'a été testé uniquement en utilisant l'IDE [Jetbrains Intellij IDEA](https://www.jetbrains.com/idea/)

Une fois Intellij IDEA installé, vous devez fournir à Intellij IDEA l'emplacement d'installation du JDK Java s'il ne le reconnait pas automatiquement. 


### Installation des plugins

Pour que le projet fonctionne correctement, vous devez installer plusieurs plugins Intellij IDEA comme le plugin `lombok` et `MapStruct Support`


### Ouverture du projet
Une fois Intellij IDEA démarrer, cliquez sur `Ouvrir` :
![enter image description here](https://zupimages.net/up/22/47/6e2z.png)

Quand le projet sera ouvert, il faudra tout d'abord lancer la commande Maven

    mvn clean install -DskipTests

![enter image description here](https://zupimages.net/up/22/47/7kvv.png)


## MySQL

### Installation du conteneur Docker

Afin de correctement fonctionner, ce projet doit être connecté à une base de données MySQL. Pour des raisons de simplicité, nous avons utilisé un conteneur Docker stockant notre base de données grâce à cette [image](https://hub.docker.com/_/mysql).

Vous pouvez ensuite lancer la commande pour démarrer le conteneur docker contenant la base de données :

    docker run --name some-mysql -e MYSQL_ROOT_PASSWORD=my-secret-pw -d mysql:tag

### Modification des informations de connexion
Une fois le conteneur démarré,  vous devez vous rendre à nouveau dans Intellij IDEA dans le but d'y modifier le fichier `application.properties` pour modifier les différentes informations de connexion à la base de données. Ainsi, vous devez modifier :

    spring.datasource.url= jdbc:mysql://IP:PORT/DB  
    spring.datasource.username = USER  
    spring.datasource.password = PASSWORD

![enter image description here](https://zupimages.net/up/22/47/p6u2.png)

Lorsque toutes les étapes précédentes sont réalisées, vous pouvez lancer le programme :
![enter image description here](https://zupimages.net/up/22/47/bhig.png)


## Ouverture de l'API

Le projet lancera automatiquement l'API sur le port `8080`

**Voici les liens utiles pour accéder à l'API :**
[localhost:8080](http://localhost:8080)
[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html#/)
