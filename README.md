# Java_Spring_Boot
Ceci est l'application de la formation Spring Boot proposer par OpenClassroom : https://openclassrooms.com/fr/courses/6900101-creez-une-application-java-avec-spring-boot

## Partie 1 - Identifiez pourquoi et quand utiliser Spring Boot
### Explorez les solutions apportées par Spring

Quelle est notre problématique de base ? Construire une application qui :
1. Permet de répondre aux besoins fonctionnels actuels.
2. Permettra de répondre aux futurs besoins.
3. Et cela de façon efficiente !

> Un développement **efficient** est un développement **efficace**, c’est-à-dire qui atteint les objectifs du projet mais en plus **à moindre coût**, avec **moins de ressources** humaines et en **moins de temps** ! 

#### Implémentez un code évolutif grâce au pattern Dependency Injection
> Comment être capable de répondre aux besoins futurs si on ne les connaît pas encore ?
Notre application doit être **évolutive**. Notre code doit avoir la capacité d’évoluer en impactant le moins possible l’existant. Et comment réussir ? Il nous faut **réduire les dépendances** au sein de notre code

##### Identifiez pourquoi les dépendances peuvent être problématiques
> Quel est le problème avec les dépendances ? Pourquoi faut-il les réduire ?

Illustrons à l’aide de l’exemple d’un objet Command et d’un objet Product : mon objet Command nécessite un objet Product. Il y a donc une dépendance entre les deux.

C’est l’objet Command qui crée l’objet Product. Donc si l’objet Product est modifié, l’objet Command doit s’adapter lui aussi ! Il existe une **dépendance forte** entre les 2 objets. Le code suivant illustre cela :
```java
package com.openclassrooms.sb.sample;

public class Command {

    private Product product;

    public Command() {
        this.product = new Product(“something”);
    }

}
```

Si l’implémentation de Product change, alors le constructeur de Command sera impacté.

Et si on veut utiliser une autre implémentation de la classe Product, à chaque changement il faudra toucher à la classe Command.

Résultat : bye bye l’évolutivité ! À l’échelle de centaines, voire de milliers de lignes de code, notre application sera extrêmement difficile à maintenir dans le temps ! 🙈

##### Découvrez la solution : Dependency Injection
Heureusement pour nous, un design pattern existe face à ce problème : *Dependency Injection*.

> Les design patterns, ou patrons de conception, sont des solutions standard aux problématiques récurrentes de développement.

Rappelons notre problématique : l’objet Command a une dépendance forte avec l’objet Product, car il doit s’occuper de sa création.

Mais imaginons maintenant que le processus de création de l’objet Product et son affectation dans l’objet Command soient **délégués à une tierce partie**. Voilà que, par magie (ou presque), l’objet Command n’a plus à s’occuper de l’objet Product, et ce dernier ne se préoccupe d’ailleurs pas non plus de l’objet Command. 

Ce que je viens d’énoncer est le principe du **design pattern Dependency Injection** !

Regardons le code :

```java
package com.openclassrooms.sb.sample;

public class Command {

    private Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Command() { }

}
```

La différence à noter est que le *“new Product()”* a disparu. La classe Command ne s’occupe plus de l’instanciation. C’est notre fameuse tierce partie qui fera le nécessaire, et insérera l’objet instancié dans l’objet Command grâce à la méthode setProduct(), et cela de façon transparente.

Finie la dépendance forte ! À l’échelle de centaines, voire de milliers de lignes de code, nous obtenons un programme dont les objets peuvent évoluer bien plus facilement !

##### Implémentez le pattern Dependency Injection avec Spring Framework
> Comment mettre en place cette tierce partie magique ? Dois-je la développer ?

Non, l’hiver est passé, le printemps est là ! Spring Framework s’occupe de tout grâce à son **IoC container** ! Ce dernier est aussi appelé le ***contexte Spring***. Il vous permettra de créer des objets dynamiquement, et de les injecter dans d’autres objets. De plus, on pourra facilement modifier l’implémentation d’un objet, avec quasiment zéro impact sur les objets qui utilisent ce dernier.

> IoC est le sigle de *Inversion of Control*. Cette expression indique un principe de programmation qui correspond au fait de déléguer à un framework le flux de construction et d’appels des objets.

Les mécanismes de l’**IoC container** rendront **votre code évolutif, performant et robuste** ! Vous ne serez pas simplement efficace, vous serez efficient ! Durant ce cours, vous aurez l’occasion de découvrir comment utiliser le contexte Spring pour bâtir de solides applications Java !

#### Soyez plus performant grâce à Spring Framework
Parmi nos problématiques en tant que développeur, il y a celle d’être **performant** dans le développement de notre projet. Cette performance peut être liée au **temps** qu’on passe à développer, mais aussi à la **qualité** du code produit. Et pour cela, Spring Framework nous offre un bel avantage : **la configuration**.

Une des particularités du framework est d’encourager (voire de forcer) les développeurs à implémenter certaines parties du code via de la configuration et non du développement.

Prenons un exemple. Pour vous connecter à une base de données en Java, vous avez besoin de :
- charger un driver de base de données (le driver MySQL, par exemple) ;
- créer différents objets (comme *java.sql.Connection*) ;
- manipuler tous ces objets dans le bon ordre ;
- gérer les exceptions, etc. 

Bref, rien que de l’écrire, ça m’a fatigué !  C’est pas vraiment complexe, mais c’est fastidieux !

Mais Spring Framework va nous permettre de résoudre tout ça en **quelques lignes** ! Non pas en quelques lignes de code, mais de **configuration** ! Pour cela, il nous suffit d’utiliser les **bons composants Spring** (j’y reviendrai dans le prochain chapitre) et le bon fichier de configuration :

spring.datasource.url=jdbc:mysql://localhost/test

spring.datasource.username=dbuser

spring.datasource.password=dbpass

spring.datasource.driver-class-name=com.mysql.jdbc.Driver

Voilà, c’est réglé : au sein de l’IoC container, tous les objets ont été créés, et on peut interagir avec notre base de données !

Qu’en dites-vous ? N’est-ce pas beaucoup plus performant ? ;-)

Et ce n’est qu’un exemple parmi tant d’autres !

#### Découvrez les autres avantages de Spring
Ne partez pas si vite ! Ce n’est pas fini, Spring Framework est également un **outil très puissant** car il simplifie certaines actions, indispensables pour répondre à nos besoins actuels, que nous aurions beaucoup de mal à réaliser par nous-mêmes. Par exemple :
- Interagir avec une base de données.
- Traiter des requêtes HTTP et écrire des réponses HTTP.
- Exécuter des traitements par lots (batch).
- Gérer la sécurité de l’application.
- Etc.

Il ne se limite donc pas à nous fournir l’IoC container, il répond quasiment à **tous nos besoins techniques**, ce qui augmente aussi notre performance !

> Est-ce vraiment possible qu’un framework soit si complet ? 🤩

**Oui !** Tout d’abord car aujourd’hui une grande part des projets sont des applications construites avec les **technologies web**. Et Spring est très fort dans ce domaine ! Ensuite, car il est suffisamment ouvert pour permettre d’inclure d’autres **librairies** qui seraient nécessaires afin de compenser ses manques.

#### En résumé
- En tant que développeurs, nous sommes face à de nombreuses problématiques : l’évolutivité, les performances, etc.
- Spring Framework nous offre des **solutions concrètes** à ces problématiques :
    - l’**IoC container** pour l’implémentation du pattern **Dependency Injection** ;
    - la capacité de **configurer** plutôt que de développer ;
    - **des composants** pour gérer une multitude d’aspects, comme par exemple interagir avec des bases de données, ou traiter des requêtes HTTP.

### Découvrez le framework Spring
Spring est un **framework**, c’est-à-dire un **cadre de travail existant** que les développeurs peuvent utiliser. Imaginez que vous vouliez réaménager votre cuisine, allez-vous construire de zéro le moindre tuyau, meuble ou accessoire ? 🤔 Absolument pas ! Ce serait une perte de temps, et peut-être même de qualité. 

La solution ? Direction les magasins spécialisés pour acheter le nécessaire. Mais attention ! Vous restez responsable de choisir les bons meubles, de les monter, de faire en sorte que tout s’assemble pour obtenir un résultat final homogène.

Spring Framework, c’est un peu comme un **grand magasin spécialisé** : il y a de nombreuses choses à trouver, et après, à nous de faire l’assemblage. Faisons un tour des rayons ensemble ! 🧐

![composantsSpring](/readMeIMG/composantsSpring.png)

#### Spring Core
Ce composant est **la base de l’écosystème** Spring. 

Il contient le **“core container”** (ce qui permet l’injection de dépendances vue précédemment), mais il contient également **Spring MVC** qui permet de faire du web et de Data Access qui fournit des éléments fondamentaux pour la communication avec les bases de données.

> Pour avoir tout le détail, suivez la [documentation officielle](https://spring.io/projects/spring-framework) sur Spring Core.

#### Spring Data
Ce composant permet de **communiquer avec de nombreux types de bases de données**. Par exemple, il offre la capacité de communiquer avec une base de données en implémentant uniquement des interfaces grâce à des conventions de nommage : bluffant !

> Pour avoir tout le détail, suivez la [documentation officielle](https://spring.io/projects/spring-data) sur Spring Data.

#### Spring Security
Pensez-vous que la sécurité soit un élément essentiel d’une application ? Moi, oui ! Et des millions de développeurs partagent ce point de vue. C’est pour ça que ce composant est l’un des plus critiques de Spring Framework, bien qu’il soit aussi l’un des plus complexes.
Il permet de gérer **l’authentification**, **l’autorisation**, mais aussi la **sécurité des API**.

> Pour avoir tout le détail, suivez la [documentation officielle](https://spring.io/projects/spring-security) sur Spring Security. 

#### Spring Cloud
Avez-vous entendu parler de **l’architecture microservice** ? Si ce n’est pas le cas, ne vous inquiétez pas, mais cela va venir très vite car c’est le modèle d'architecture le plus prisé actuellement. Et pour répondre aux contraintes de cette architecture logicielle, Spring Framework fournit Spring Cloud. 

> Pour avoir tout le détail, suivez la [documentation officielle](https://spring.io/projects/spring-cloud) sur Spring Cloud.

#### Spring Boot
C’est un composant très particulier de Spring Framework, dans la mesure où il nous permet de mettre en œuvre tous les autres. Ce cours vous montrera comment tirer profit de la puissance de Spring Boot, et de ses avantages qui sont :
- l'**autoconfiguration** automatique de Spring ;
- des **starters de dépendances** ;
- des **endpoints Actuator** pour fournir des données sur l’application.

>Pour avoir tout le détail, vous pouvez suivre la [documentation officielle](https://spring.io/projects/spring-boot) sur Spring Boot. 
>
>Et ce n’est pas tout ! Pour explorer tous les autres projets, vous pouvez vous rendre sur [cette page](https://spring.io/projects).

#### En résumé
- Spring propose de **nombreux composants** pour répondre aux besoins des développeurs !
- L’un des plus utiles est **Spring Boot**, car il permet de mettre en œuvre les autres composants de Spring avec facilité, notamment grâce **aux starters de dépendances** et à **l’autoconfiguration**.

### Identifiez les avantages de Spring Boot

#### Simplifiez votre projet avec Spring Boot
Vous l’avez noté, Spring est un écosystème avec un grand E ! À tel point que parfois ce framework peut même sembler trop rigide, trop encombrant ou trop complexe, il faut le reconnaître. De plus, il contient de nombreux composants, et ces derniers ne s’utilisent pas de façon exclusive : dans la très grande majorité des projets, vous devrez utiliser **plusieurs composants de Spring simultanément**. Par voie de conséquence, l’intégration de plusieurs composants Spring pour un même projet ajoute de la complexité. **Complexité qui sera croissante** plus le projet prendra de l’importance !

Non non, ne fuyez pas !😅 Rassurez-vous, une solution existe ! Nous pouvons tirer profit de tous les avantages de Spring sans y perdre notre latin !

> Comment faire ? 😱

Je vous en ai déjà brièvement parlé au chapitre précédent, il s’agit d’utiliser **Spring Boot** ! Ce composant de Spring a été créé pour nous aider à utiliser Spring Framework. C’est un composant **au service des autres composants**.

Illustrons l’idée. Vous vous souvenez peut-être de la comparaison que j’ai déjà utilisée : Spring Framework est comme un grand magasin spécialisé de meubles, où un composant est tel un meuble que l’on peut acheter.

Eh bien Spring Boot, c’est comme des gammes de meubles qui nous sont proposées. Lorsqu’on achète un meuble, il est certain que les autres meubles de la même gamme iront ensemble (c’est pour ça que c’est une gamme !), et cela évite les surprises lors de l’association des meubles.

Là, c’est pareil : Spring Boot nous met à disposition les bons composants, nous permettant ainsi de les faire fonctionner ensemble.

Et cela contribuera à la **simplification** de notre projet !

#### À vous de jouer !
Jouons au jeu des 7 différences ! Je vous propose de comparer deux projets Java qui utilisent Spring Framework. L’un utilise le composant Spring Boot, l’autre non.

La consigne est simple : **Quelles différences pouvez-vous observer ?**

Projet **[sans](https://github.com/OpenClassrooms-Student-Center/creez-une-application-spring-boot/tree/master/webwithoutsb)** Spring Boot;

Projet **[avec](https://github.com/OpenClassrooms-Student-Center/creez-une-application-spring-boot/tree/master/webwithsb)** Spring Boot.

Alors, qu’avez-vous noté ?

Je vous décris les différences entre ces deux projets, en mettant en avant les avantages de Spring Boot ✅.
| **Répertoires**    | **Sans Spring Boot**                                         | **Avec Spring Boot**                                         |
| :----------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| src/main/java      | 4 classes Java : <br />   1. HomeController.java<br />   2. User.java<br />   3. UserRepository.java<br />   4. **JpaConfig.java** : c’est moi qui ai créé cette classe… 😅 | 4 classes Java :<br />   1. HomeController.java<br />   2. User.java<br />   3. UserRepository.java<br />   4. **WebwithsbApplication.java** : c’est une classe créée automatiquement par Spring Boot, plus de perte de temps ! ✅ |
| src/main/resources | - **contextFront.xml** : configuration manuelle du scanning et du viewresolver<br /> - **META-INFO/persistence.xml** : contient la configuration de la BDD | - **Pas besoin de contextFront** : Spring Boot s’en occupe ! ✅<br /> - **application.properties** : contient la configuration de la BDD. Est plus simple à aborder que XML. <br /> - ✅**template/home.html** : rendu HTML à fournir. |
| src/main/webapp    | **2 fichiers** :<br /> - **template/home.jsp** : rendu HTML à fournir<br /> - **web.xml** : fournit de la configuration pour la gestion des servlets | Ce dossier **n’existe pas** ! Spring Boot n’a pas besoin de tout ça ! ✅ |
| fichiers pom.xml   | **8** dépendances<br />+ **2** dépendances dans le dependencyManagement pour les versions | **5** dépendances **sans avoir défini les versions**         |

👉 Moins de fichiers, plus d’automatisation… Bref, c’est une victoire très nette de Spring Boot, qui nous simplifie la vie !

Et cerise sur le gâteau : pour le déploiement, le projet avec Spring Boot se lance avec une simple commande, alors que le projet sans Spring Boot me demande d’installer un serveur web (comme Tomcat) pour le déployer et le démarrer.

Vraiment, jusqu’au bout, Spring Boot nous simplifie les choses ! 🤩

#### Les avantages de Spring Boot
Reprenons notre souffle après cet exercice, et analysons les avantages de Spring Boot.

##### Avantage n° 1 : optimisation de la gestion des dépendances
Spring Boot nous fournit des **starters**, qui correspondent à un ensemble de dépendances homogénéisées (associations, versions). On peut les comparer à des **kits de dépendances**.
[Les Starters](https://user.oc-static.com/upload/2020/11/10/1605004979024_image3.png)

Nul besoin de définir les versions des dépendances explicitement dans le pom.xml : Maven les déduit grâce à la version de Spring Boot utilisée.

Dans ce cours, nous allons apprendre à choisir les bons starters en fonction du besoin.

##### Avantage n° 2 : l’autoconfiguration
C’est peut-être l’avantage le plus important de Spring Boot ! L’exercice précédent l’a révélé : avec Spring Boot, il y a beaucoup moins de configuration (concernant la gestion des servlets, le chargement du contexte Spring, la connexion à la base de données). Ce n’est pas un hasard. L’utilisation de Spring Boot, et l’annotation @SpringBootApplication placée au niveau de la classe principale de notre projet (celle générée automatiquement), déclenchent automatiquement de nombreuses opérations en background qui nous sont nécessaires.

Le développeur peut alors **se concentrer sur le code métier** au lieu de passer un temps fou à configurer le framework qu’il utilise.

Au fur et à mesure du cours, je vous en dirai plus sur les opérations que Spring Boot réalise en background.

##### Avantage n° 3 : la gestion des propriétés
Spring Boot permet de **gérer les propriétés au sein d’un programme en toute simplicité**.

Dans l’exercice, vous avez pu voir le fichier **applications.properties**. Les informations qui étaient saisies ont été prises en compte par certaines classes, sans que nous ayons besoin d’agir. Ce fichier est **l’un des éléments clés** pour la gestion des propriétés de notre programme.

Mais cela ne se limite pas à ce simple fichier ; par exemple, il est facilement possible de récupérer même des variables d’environnement système, et de les fournir à nos classes.

Dans ce cours, nous découvrirons comment tirer profit de la capacité de Spring Boot à gérer les propriétés.

##### Avantage n° 4 : le monitoring et la gestion du programme
Je ne souhaite pas rentrer dans le détail ici, mais sachez que **Spring Boot Actuator** correspond à une fonctionnalité de Spring Boot qui permet de **monitorer et de manager notre programme** pendant qu’il est en cours d’exécution.

Par exemple, grâce aux **endpoints Actuator**, on peut modifier une propriété en live, et le programme en tiendra compte sans qu’on ait besoin de le redémarrer. Très utile !

##### Avantage n° 5 : le déploiement
Quel est l’artefact produit par un projet Spring Boot ? Un simple fichier JAR. 

Que faut-il pour exécuter un JAR ? Vous le savez, une JRE, et c’est tout !
> Mais comment est-ce possible pour une application web qui a forcément besoin d’un serveur de conteneur web ?

Un projet Spring Boot contient un tomcat embarqué au sein même du JAR généré. Le projet web peut donc être déployé au sein de ce tomcat embarqué.

Conclusion, exécuter notre projet Spring Boot, quelles que soient ses fonctionnalités, est très simple ! Et cela permet de coupler facilement nos projets Spring Boot avec d’autres outils comme Docker.

#### En résumé
- Spring Boot œuvre pour la **simplification** du développement de nos projets avec Spring Framework.
- La gestion des dépendances est simplifiée grâce aux **starters** qui **regroupent plusieurs dépendances** et **homogénéisent les versions**.
- L’**autoconfiguration** permet de **se concentrer sur le code métier**, et simplifie énormément la mise en œuvre des composants Spring qui sont utilisés.
- La gestion efficace des propriétés rend notre application **configurable**.
- Spring Boot **Actuator** permet de **monitorer** et **gérer** une application **pendant son exécution**.
- Le déploiement de l’application est facilité par la génération d’un JAR, et pour les projets web, un **tomcat est embarqué**.

## Partie 2 - Découvrez les étapes clés de tout projet Spring Boot

### Créez votre projet
Dans cette partie de cours, nous allons ensemble découvrir les étapes clés pour tout projet Spring Boot. Cette partie est construite comme une sorte de mode d’emploi, auquel vous pouvez vous référer quelle que soit l’application que vous réalisez !

Créons ensemble le fameux **“Hello World”** avec Spring Boot !

Tout d’abord, voici le plan d’action pour tout projet Spring Boot :
1. Créer le projet, c’est-à-dire générer la structure minimale.
2. Structurer et configurer le projet.
3. Écrire le code.
4. Tester et déployer.

Chaque étape du plan d’action correspond à un chapitre de cette partie du cours. Et les 2 parties de cours suivantes sont construites sur la base des mêmes étapes.

C’est parti pour la première étape : **créons notre projet !** 😎

#### Découvrez les starters
> On commence par quoi ?

La première étape implique de générer la base de votre projet. Vous devez savoir qu’on ne commence pas sur une feuille blanche. Spring Boot nous fournit une base de travail que l’on peut nommer la **structure minimale**. On enrichira ensuite cette structure minimale en fonction des besoins de notre projet.

Pour obtenir cette structure minimale, il y a plusieurs solutions que l’on explorera dans la suite du chapitre. Mais avant de foncer tête baissée, sachez que Spring Boot va vous demander un certain nombre d’informations, comme :
- la version de Java ;
- Maven ou Gradle ;
- la version de Spring Boot ;
- des informations sur le projet (groupId, artifactId, nom du package) ;
- les dépendances.

Pour les premiers éléments, ce ne sera pas difficile ; ça pourrait par contre le devenir pour le choix des dépendances, car Spring Boot utilise quelque chose de nouveau : **les starters de dépendances**.

J’ai déjà eu l’occasion dans la partie précédente de vous en parler, mais un rappel ne fera pas de mal.

Spring Framework se découpe en de nombreux composants ; les utiliser implique de renseigner les bonnes dépendances pour notre projet. Ce n’est pas facile, car il faut savoir quelle dépendance est nécessaire à quelle autre dépendance, s’il y a des incompatibilités, et quelles sont les versions à utiliser.

Pour résoudre cette problématique, Spring Boot nous offre les starters de dépendances qui sont des kits de dépendances (vous vous souvenez de mon exemple avec les gammes de meubles, voilà !  ).

Par exemple, le starter **spring-boot-starter-data-jpa** va vous apporter différents JAR pour utiliser Spring et JPA, afin de communiquer avec une base de données.

Tous les starters sont préfixés par “spring-boot-starter”. Voici quelques exemples de starters :
- spring-boot-starter-core ;
- spring-boot-starter-data-jpa ;
- spring-boot-starter-security ;
- spring-boot-starter-test ;
- spring-boot-starter-web.

> Comment choisir les bons starters pour mon projet ?

Normalement, la **description du starter** est suffisante pour identifier si ce dernier est ce dont vous avez besoin ou non. Sans oublier que la documentation officielle de Spring saura toujours vous guider vers le bon starter en fonction de vos besoins.

Il n’y rien de plus à savoir sur la question, je crois qu’il est temps de pratiquer !

Il existe deux façons de créer un projet : avec Spring Initializr et avec Spring Tool suite. Nous allons aborder ces deux approches ensemble !

#### Créez votre projet avec Spring Initializr

Voyons comment utiliser le site web [Spring Initializr](https://start.spring.io/).

Récapitulons les étapes :
1. J’ai laissé par défaut Project / Language et Spring Boot.
2. J’ai saisi les informations suivantes dans Project Metadata :
   - Group: com.openclassrooms.
   - Artifact: HelloWorld.
   - Name: HelloWorld.
   - Description: Hello World project with Spring Boot.
   - Package name: com.openclassrooms.helloworld.
   - Packaging: Jar.
   - Java: 8.

Aucune dépendance à ajouter, car le starter “**spring-boot-starter**”, qui contient toutes les fonctionnalités de base, est ajouté par défaut. J’ai cliqué sur “Generate”, et une archive ZIP avec le projet a été téléchargée. Après avoir dézippé l’archive, j’ai tout simplement importé ce projet existant dans mon IDE.

Voilà, notre première étape est finie ! 

#### Créez votre projet avec Spring Tool Suite
Une deuxième façon d'atteindre le même résultat correspond à l’utilisation de l’outil Spring Tool Suite, téléchargeable [ici](https://spring.io/tools).

Je vous conseille **Spring Tools 4 for Eclipse**. Il est téléchargeable sur Linux, macOS et Windows. Vous obtenez un JAR qui, une fois exécuté, va créer un répertoire qui aura un nom du style “sts-4.7.1.RELEASE”. Au sein de ce répertoire, vous pouvez lancer l’exécutable “SpringToolSuite4.exe”.

> Spring Tools 4 for Eclipse est un outil développé sur la base de l’IDE Eclipse. Les habitués de cet IDE ne seront donc pas dépaysés !

Avez-vous remarqué qu’on retrouve les mêmes étapes, mais cette fois à travers un outil installé sur votre poste de travail, et non via un site web ?

> Mais pourquoi utiliser Spring Tool Suite et non Spring Initializr, qui ne demande aucun outil supplémentaire ?

Pour la simple raison que STS (Spring Tool Suite) nous offre une fonctionnalité supplémentaire : le “**Boot Dashboard**”, qui permet de gérer plus précisément le **cycle de vie de l’exécution de l’application**. Et comme STS est tout simplement un Eclipse customisé, on peut directement développer notre projet au sein de l’outil. Assez pratique, finalement. 

Pour finir cette partie, je vous encourage à jeter un œil au fichier pom.xml (à la racine de la structure du projet). Vous y retrouverez tous les éléments saisis lors de la génération de votre projet. En voici d’ailleurs 2 extraits :

Extrait de la description du projet :
```java
<groupId>com.openclassrooms</groupId>
<artifactId>helloworld</artifactId>
<version>0.0.1-SNAPSHOT</version>
<name>helloworld</name>
<description>Hello World project with Spring Boot</description>
```

Extrait des dépendances du projet :
```java
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter</artifactId>
    </dependency>
    ….
</dependencies>
```

#### En résumé
- La première étape pour utiliser Spring Boot est de **créer la structure minimale**.
- On obtient la structure minimale en fournissant plusieurs informations, dont **les starters de dépendances**.
- Un projet Spring Boot peut être créé via **Spring Initializr** ou bien via **Spring Tool Suite**.

### Observez la structure minimale
#### Explorez la structure minimale
Voici le projet à travers la vue Package Explorer de STS. Qu'observez-vous? Comment les sources sont-elles organisées ?

Voici quelques points à noter :
- Nous retrouvons les fichiers liés à Maven (pom.xml, mvnw et mvnw.cmd).
- Nos sources sont organisées suivant le schéma standard :
    - src/main/java : contient les packages et les classes Java ;
    - src/main/resources : contient les fichiers ressources, tels que les fichiers de propriétés ou des templates (HTML, par exemple) ;
    - src/test/java : contient les classes Java de test.
- Comme tout projet Java/Maven, on retrouve également la JRE et les Maven Dependencies.

J’attire également votre attention sur le fait que cette structure minimale ne contient pas que des répertoires et des packages : il y a aussi deux classes Java et un fichier Propriétés.

Le fichier de propriétés “application.properties” est créé par défaut, mais il est vide ; j’y reviendrai plus tard, promis !

Pour ce qui de la classe HelloWorldApplicationTests.java, je ne souhaite pas m’y arrêter non plus à cette étape ; sachez juste pour le moment qu’elle vérifie que le contexte Spring se lance bien comme attendu.

Maintenant, parlons de HelloWorldApplication.java, qui est la **classe principale** de votre projet. On y retrouve 2 choses importantes :
1. La méthode main dont je ne vous ferai pas l’offense de vous expliquer son rôle en Java !
2. L’annotation @SpringBootApplication qui est critique !

> S T O P ! Annotation, tu dis ?

Vous avez raison, je vais vous expliquer ce qu’est une annotation, et son utilité au sein de Spring.

#### Découvrez le rôle des annotations et leurs avantages
Nous avons vu ensemble que Spring Framework fournit l’IoC container, dans la partie 1 du cours. L’IoC container va instancier pour vous des classes, puis si nécessaire les injecter dans d’autres instances de classe. Pour qu’une classe soit manipulée par l’IoC container, **il est nécessaire de l’indiquer explicitement à Spring**. Comment ?

La première solution est l’utilisation de **fichiers XML** au sein desquels on décrit les classes que Spring doit gérer ; voici un exemple des plus simples :
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE beans PUBLIC "-//SPRING//DTD BEAN 2.0//EN"
    "http://www.springframework.org/dtd/spring-beans-2.0.dtd">

<beans>
    <bean id="myBean" class="com.openclassrooms.BeanImpl"/>
</beans>
```

La classe com.openclassrooms.BeanImpl (qui a été créée préalablement, évidemment) sera ainsi prise en compte par l’IoC container.

> Cette solution est l’une des plus anciennes façons de faire. Aujourd’hui, elle est de moins en moins utilisée, de par sa complexité.

La seconde solution est l’utilisation des annotations.

Une annotation, c’est-à-dire **@[nom de l’annotation]**, peut être ajoutée à une classe, une méthode, un attribut. **L’annotation influe sur le comportement du programme** car elle fournit des métadonnées lors de la compilation ; ces mêmes métadonnées seront utilisées lors de l’exécution.

Depuis la version 2.5 de Spring, de nombreuses annotations sont fournies, dont le but est de :
1. Permettre à l’IoC container d’**utiliser nos classes**.
2. **Influer sur le comportement** de Spring.

Voici quelques exemples d’annotations Spring :
- @Component
- @Autowired
- @Qualifier

> Il n’y a pas que Spring qui utilise les annotations. Il en existe au sein même de Java, et d’autres frameworks utilisent aussi ce puissant outil.

Si on reprend l’exemple précédent, voici la correspondance en annotation :
```java
package com.openclassrooms;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier(“myBean”)
public class BeanImpl {
    // TO DO
}
```

L’annotation @Component permet de déclarer la classe BeanImpl auprès de Spring, comme étant un bean à avoir dans l’IoC container.

L’annotation @Qualifier permet de donner un nom à ce bean, en l'occurrence “myBean” (si cette annotation n’est pas définie, le nom par défaut est le nom de la classe).

Qu’en dites-vous ? C’est plus sympa, non ? Nul besoin de manipuler du XML, tout se passe dans le Java !

> En réalité, il n’y a pas vraiment une meilleure façon de faire, les 2 sont valides. La configuration via XML est la façon historique de faire, et on la retrouve encore sur de nombreux projets. Tandis que la configuration par annotations est ce qui s’utilise de plus en plus, notamment par la démocratisation de Spring Boot.

#### Utilisez l’annotation @SpringBootApplication
Revenons maintenant à nos moutons ! Ou plutôt à **l’annotation @SpringBootApplication**. À quoi sert-elle ?

Comme je vous l’ai expliqué, elle va permettre à l’IoC container de manipuler la classe HelloWorldApplication.java. Elle permettra également d’influer sur le comportement de Spring.

@SpringBootApplication est un composé de 3 autres annotations :
1. **@SpringBootConfiguration** : la classe sera utilisée comme une classe de configuration (on reviendra sur cette notion plus tard).
2. **@EnableAutoConfiguration** : active la fameuse fonctionnalité d’autoconfiguration de Spring Boot, que je vous ai tant vantée.
3. **@ComponentScan** : active le “**scanning**” de classes dans le package de la classe et dans ses sous-packages. Sans cette annotation, l’IoC container ne tiendra pas compte de vos classes, même si vous avez ajouté une annotation sur celles-ci. 

OK, on y voit plus clair maintenant ! **En résumé, cette classe, c’est ce qui déclenche toute la mécanique interne de Spring Boot et des composants Spring associés**. Et tout ça en moins de 15 lignes de code.

Nous voilà au bout de l’analyse de la structure minimale d’un projet Spring Boot, et quelle conclusion tirons-nous ?

Oui, nous avons une base, c’est vrai, mais il y a des trous !
1. Au sein de src/main/java, nous avons **un seul package**. Et vu qu’un développeur apprend à regrouper les classes par sous-ensemble, nous allons devoir **créer des sous-packages** pour nos différentes classes à venir.
2. Le fichier applications.properties est vide ! Le pauvre, il doit se sentir bien inutile... Rassurez-vous, on va vite lui donner un rôle à jouer !

Remplissons ces trous pour pouvoir aller de l’avant !

#### En résumé
- La structure minimale n’est pas suffisante, il faut l’enrichir.
- Les **annotations sont un pilier** au sein de Spring Boot pour tirer profit de l’IoC container.
- L’annotation **@SpringBootApplication** est la concaténation de plusieurs annotations, et son objectif est de déclencher toute la mécanique interne de Spring.

### Structurez et configurez votre projet
#### Structurez vos packages
> Comment structurer notre package si on ne connaît pas encore les classes qui seront produites ?

Bonne question, je vois que vous êtes réfléchi et que vous avez appris à ne pas foncer tête baissée ! Bravo !

La réponse se veut très simple : **les bonnes pratiques** !

Premièrement, gardons à l’esprit que Spring Boot est particulièrement utilisé dans le contexte d’application web (même si ça ne se limite pas à cela).

Deuxièmement, la majorité des applications ont la nécessité d'interagir avec des données externes (par exemple une base de données, un autre programme, ou même le système de fichiers).

De ces différents besoins, une architecture en couches a émergé, avec un rôle pour chaque couche :
- **couche Controller** : gestion des interactions entre l’utilisateur de l’application et l’application ;
- **couche Service** : implémentation des traitements métiers spécifiques à l’application ;
- **couche Repository** : interaction avec les sources de données externes ;
- **couche Model** : implémentation des objets métiers qui seront manipulés par les autres couches.

[Représentation visuelle de l'architecture en couches](https://user.oc-static.com/upload/2020/11/10/1605015466847_image11.png)

Cette architecture standard correspondra à la majorité de vos projets, et vous la retrouvez très fréquemment.

> D’autres packages peuvent être parfois nécessaires, par exemple un nommé “security” pour les classes dédiées à la sécurité, ou “configuration” pour des classes gérant les propriétés. Dans ce cas, cela s'ajoute à la structure existante.

Pour être franc, on n'a pas besoin de tous ces packages pour un simple Hello World. Mais dans les parties du cours suivantes, vous les verrez en action.

Dans notre cas, contentons-nous de créer les packages service et model.

#### Complétez le fichier applications.properties
L’étape suivante consiste à définir quelques informations de base pour notre application, à travers les propriétés.

Mais avant, laissez-moi vous en apprendre un peu plus sur Spring et la gestion des propriétés. Je ne vous apprends rien en vous disant qu’une application doit être **paramétrable**, c’est-à-dire que son comportement peut changer en fonction des paramètres fournis.

Pour rendre paramétrable une application, elle doit donc être capable de lire ces paramètres. Mais où sont-ils ? Qui les définit ? Comment les lire ?

Ils sont dans des “**sources de propriétés**” (property sources), et sont définis par les gestionnaires de ces sources.

Là où Spring Boot nous intéresse, c’est qu’il est capable de lire ces sources de propriétés (sans interaction de notre part), et de rendre les propriétés disponibles sous forme de beans au sein du contexte Spring.

Parmi les sources de propriétés, il y a :
- les propriétés de la JVM ;
- les variables d’environnements du système d’exploitation ;
- les arguments passés par la ligne de commande ;
- les fichiers .properties ou .yml (comme *application.properties*).

Vous aurez l’occasion de manipuler ces différentes sources de propriétés, mais pour le Hello World, on va se contenter de rajouter quelques informations au fichier applications.properties.

> Comment fait-on pour connaître les propriétés existantes, vu que application.properties est vide ?

Pour en savoir plus sur les propriétés de Spring, vous pouvez lire la [documentation de Spring](https://docs.spring.io/spring-boot/docs/current/reference/html/appendix-application-properties.html).

D’accord, je vous aide, mettons en place 2 propriétés :
1. spring.application.name : permet de donner un nom à notre application Spring Boot.
2. logging.level.[package] : permet d’indiquer le log level pour un package donné.

> Besoin d’en savoir plus sur les loggers ? Vous pouvez suivre le cours “Débuggez votre application Java”, et particulièrement le chapitre [“Faites des rapports avec un logger, des niveaux de log, et l’API SLF4J standard”](https://openclassrooms.com/fr/courses/6692416-debuggez-votre-application-java/6915582-faites-des-rapports-avec-un-logger-des-niveaux-de-log-et-l-api-slf4j-standard).

Voilà le résultat de mon fichier applications.properties :
```java
spring.application.name=HelloWorld

logging.level.org.springframework=error
```

Quelques explications :
- Ici, le nom est arbitraire ; à vrai dire, cela n’aura pas d’impact sur le déroulement de l’application pour notre Hello World, c’est surtout informatif. 
- `logging.level.org.springframework=ERROR` : les classes du package org.springframework affichent uniquement les logs de niveau ERROR dans la console (autrement dit, on n’est pas pollué par plein d’informations).

C’est tout ! Nous n’avons rien à faire d’autre, car dans le monde magique de Spring Boot, les propriétés sont prises en compte automatiquement sans que le développeur ait d’autres actions à réaliser !

#### En résumé
- Je vous conseille de suivre les bonnes pratiques pour structurer vos packages, et de suivre **un modèle en couches**.
    - En l’occurrence, nous avons opté pour 4 couches : Controller, Service, Repository et Model.
    - L’approche en couches permet une meilleure évolution et une meilleure maintenabilité du code.
- Spring Boot a la très grande capacité de savoir lire des **sources de propriétés**, et le fichier applications.properties en est la démonstration. Pour configurer rapidement et efficacement votre application, ajoutez vos propriétés au fichier applications.properties.

### Écrivez votre premier Hello World
#### Identifiez où et comment écrire votre code
Le moment tant attendu pour nous développeurs est arrivé ! Nous allons **C-O-D-E-R** !

Pour rappel, nous avons créé notre projet, puis nous l’avons structuré et configuré. Nous sommes maintenant à l’étape 3 : nous allons écrire le code métier, c’est-à-dire les traitements fonctionnels attendus. Pour réussir cette étape, nous allons devoir nous concentrer sur les beans que Spring doit gérer.

> Pour rappel, un **bean est une classe au sein du contexte Spring** (l’IoC container).

> Quels sont les besoins fonctionnels pour notre application HelloWorld ?

Rien de plus simple, afficher le texte “Hello World!” dans la console.

> De quoi avons-nous besoin pour atteindre cet objectif ?

**Savoir écrire dans la console !**

En Java, pour afficher du texte dans la console, la fonction **System.out.println()** permet de le faire. Mais où va-t-on placer notre code ?

Reprenons notre classe principale :
```java
package com.test.helloworld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelloworldApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloworldApplication.class, args);
	}

}
```

La méthode main sera théoriquement là où on écrirait notre code dans un programme Java simple. Mais en l'occurrence, cette dernière contient l’instruction  `SpringApplication.run(HelloWorldApplication.class, args);`. Cette instruction permet de démarrer notre application, et ce n’est pas une bonne pratique d’ajouter autre chose dans la méthode main.

> Oui, mais où met-on notre code, alors ?

Spring Boot fournit une interface nommée “**CommandLineRunner**”. En implémentant cette interface, la classe sera obligée de déclarer la méthode `public void run(String... args) throws Exception`. À partir de là, si la classe est un bean (c’est-à-dire chargée dans le contexte Spring), Spring Boot exécutera la méthode run à l’exécution du programme.

Vous pourriez :
- soit modifier la classe HelloWorldApplication afin qu’elle implémente CommandLineRunner et la méthode run, avec comme corps de méthode un “System.out.prinln(“Hello World!”)” ;
- soit créer une nouvelle classe qui implémente CommandLineRunner, la méthode run (même corps de méthode), et qui aura une annotation @Component (au-dessus du nom de la classe).

#### À vous de jouer
Je vous laisse la main, essayez d’implémenter votre premier HelloWorld !

Pour tester le résultat, si vous utilisez STS via le Boot dashboard, vous pouvez démarrer l’application.

Commencez par builder votre application via Maven :
- clic droit sur le nom du projet dans Package Explorer ;
- Run as ;
- Maven install.

Puis lancez l’application via le Boot dashboard :
- sous local, sélectionnez votre projet HelloWorld ;
- cliquez sur la première icône du menu.

Voici une solution que je vous propose :
```java
package com.test.helloworld;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelloworldApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(HelloworldApplication.class, args);
	}
	
	public void run(String... args) throws Exception{
		System.out.println("Hello World!");
	}

}
```

#### Manipulez les beans
> Et les packages service et model, à quoi servent-ils ?

Nous ne nous sommes pas servi de ces packages car ils n’étaient pas utiles pour notre premier Hello World, mais ils le seront pour la suite ! À titre d'entraînement et pour vous faire découvrir d’autres notions importantes, je vous propose d’écrire quelques classes supplémentaires :
- une classe **HelloWorld.java** qui contient un attribut nommé **value** de type **String**. Cette classe fait office d’objet métier, et doit être dans le **package model**. L’attribut value doit contenir **le texte “Hello World!”**. On ajoute également une méthode **toString** à cette classe, qui doit retourner le contenu de l’attribut value ;
- une classe **BusinessService.java** qui contient une méthode dont le prototype est “**public HelloWorld getHelloWorld()**”. Cette méthode doit instancier un objet HelloWorld, et le retourner. Attention, la classe BusinessService.java doit être déclarée **en tant que bean dans le context Spring**. J’en appelle à votre mémoire : comment fait-on ?! Via une annotation, bien sûr : **@Component** fera l’affaire.

Modifions également du code existant :
- La classe HelloWorldApplication doit être complétée par un nouvel attribut : “**private BusinessService bs;**”. Ce dernier sera annoté** @Autowired**.
- Ensuite, la méthode run doit être modifiée afin qu’elle contienne le code suivant :
```java
HelloWorld hw = bs.getHelloWorld();
System.out.println(hw);
```

Tout d’abord, on récupère un objet HelloWorld grâce au BusinessService, puis on transmet l’objet HelloWord à la méthode System.out.println. Lors de l’exécution de cette dernière, la méthode toString() de l’objet HelloWorld sera appelée, et le texte contenu dans l’attribut value s’affichera.

> Quelque chose m’échappe, l’attribut bs n’est jamais instancié dans ce code ; comment se fait-il qu’on puisse l’utiliser ?

C’est grâce à l’IoC container de Spring ! Rappelez-vous, je vous ai appris dans la première partie le concept de **l’injection de dépendances**. En mettant l’annotation @Autowired sur l’attribut bs, **Spring va chercher au sein de son contexte s’il existe un bean de type BusinessService**. 

✅ S’il le trouve, il va alors instancier la classe de ce bean et **injecter cette instance dans l’attribut**. 

❌ S’il ne trouve pas de bean de ce type, Spring génère une erreur.

Résultat : nul besoin de gérer l’instanciation du BusinessService, Spring s’en occupe pour nous. :-)

#### En résumé
- Au sein d’une application Spring Boot, écrire du code implique de **définir les beans** utilisés par Spring.
- On peut exécuter du code grâce à l’implémentation de l’interface **CommandLineRunner** et de la méthode **run**.
- Pour qu’une classe soit **déclarée en tant que bean**, on l’annote **@Component**.
- Pour qu’un bean **soit injecté** dans un attribut, on annote l’attribut **@Autowired**.

### Découvrez Spring Boot Test et déployez votre projet
#### Abordez la dernière étape : les tests et le déploiement
C’est ici la dernière ligne droite, et je sens l’adrénaline monter! 😃

Pour conclure notre application HelloWorld, il nous reste 2 choses à faire :
1. Tester notre application.
2. Déployer notre application.

Pas besoin de vous expliquer que les tests sont utiles, je suis convaincu que vous avez déjà conscience de leur importance.

> Si vous n’êtes pas à l’aise avec les tests en Java, je vous conseille d’aller regarder le cours [Testez votre code Java pour réaliser des applications de qualité](https://openclassrooms.com/fr/courses/6100311-testez-votre-code-java-pour-realiser-des-applications-de-qualite).

“Déployer” revient à mettre en route notre application. Autrement dit, il s’agit de **passer de l’environnement de développement à l’environnement de production**.

Voyons tout ça dans le détail, c’est parti !

#### Découvrez Spring Boot Test
Je vous en ai parlé précédemment, lors de la création de la structure minimale du projet, une classe de test a été créée, à savoir **HelloWorldApplicationTests.java**. Allons la regarder de plus près ;-) !
```java
package com.openclassrooms.helloworld;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HelloworldApplicationTests {

    @Test
    void contextLoads() { }

}
```

Plutôt simple, n’est-ce pas ? Presque étonnant, d’ailleurs ! Expliquons.

**@SpringBootTest** est une annotation fournie par Spring Boot. Elle permet **lors de l’exécution des tests d’initialiser le contexte Spring**. Les beans de notre application peuvent alors être utilisés.

Rappelons qu’un test s’exécute de façon unitaire, presque comme une application à part entière. Par défaut, notre test n’a donc aucune connaissance du contexte Spring. Dans le cas d’une application Spring Boot, c’est un vrai problème !

Mais le problème est résolu grâce à l’annotation @SpringBootTest.

La méthode contextLoads est **annotée @Test** (annotation qui provient de JUnit ; quand je vous disais que d’autres frameworks utilisent les annotations 😉), et n’a pas de contenu.

> Pourquoi n'est elle pas contenu ?

Tout simplement parce que son unique objectif est de **vérifier que le contexte Spring se charge bien**.

Sans méthode de tests (c’est-à-dire sans méthode avec @Test), notre classe de test ne peut être exécutée, même si elle est annotée @SpringBootTest. Pour parer à cela, Spring Boot génère une méthode vide annotée @Test, et qui sera donc toujours success pour JUnit (car elle est vide).

Ainsi, lors de l’exécution de cette méthode, le contexte Spring sera chargé, et si ce dernier rencontre une erreur, alors l’exécution de la classe de test retournera une erreur.

Très bien ! Maintenant, testons notre HelloWorld !

> D’accord, mais on teste quoi ?

Le but de ce cours n’est pas de vous expliquer les méthodologies de test. Mais rappelez-vous toujours qu’au sein d’un projet, on va **tester les traitements métiers** pour s’assurer qu’ils correspondent bien aux attendus.

Dans notre cas, le traitement métier est représenté par la méthode getHelloWorld() de la classe BusinessService.

Donc, nous allons tester cette méthode.

Je vous laisse essayer, voici quelques indices :
1. Ajoutez une nouvelle méthode de test.
2. Injectez une instance de BusinessService dans la classe de test.
3. Vérifiez que l’attribut “value” de l’objet HelloWorld contient bien le texte “Hello World!”.

Ensuite, il ne vous reste plus qu’à faire un clic droit sur la classe, “Run As”, “JUnit Test”.

```java
package com.test.helloworld;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.test.helloworld.service.BusinessService;

@SpringBootTest
class HelloworldApplicationTests {

	@Autowired
	private BusinessService bs; 

	@Test
	void contextLoads() {
	}
	
	@Test
	public void testGetHelloWorld() {
		String expected = "Hello World!";
		
		String result = bs.getHelloWorld().getValue();
		
		assertEquals(expected, result);
	}

}
```

Analysons :
- Lignes 14/15 : j’injecte une instance de BusinessService dans un attribut nommé bs. À noter que sans l’annotation @SpringBootTest, cela échouera car sans contexte Spring, impossible de faire de l’injection de dépendances.
- Lignes 21/22 : j’écris ma méthode, sans oublier d’annoter @Test.
- Ligne 23 : je définis le résultat attendu pour la valeur “Hello World!”.
- Ligne 25 : je récupère, à travers l’instance du BusinessService, un objet HelloWorld, puis j’appelle la méthode getValue et affecte le résultat dans une variable nommée result.
- Ligne 27 : grâce à assertEquals, je compare les 2 variables. Si elles sont égales, le test réussit, sinon il échoue.

Allez ! Passons au déploiement !

#### Découvrez les méthodes de déploiement
Notre objectif est désormais de déployer et ainsi d’exécuter notre application.

> Le déploiement correspond aux étapes qui permettent de passer de l’environnement de développement à l’environnement d’exécution.

L’environnement de développement correspond généralement à notre IDE. Dans le cas de ce cours, on utilise STS.

L'environnement d'exécution peut varier. Il peut s'agit d'un environnement de tests ou d'un environnement de production. Notons que l’environnement de développement peut également être un environnement d’exécution.

> Quel prérequis doit avoir un environnement d’exécution ?
Je suis sûr que vous connaissez la réponse : tout simplement une JRE, qui permet ainsi l’exécution d’un programme Java.

> Même si c’est une application Spring, la simple JRE suffit ?
Tout à fait, et c’est une très bonne nouvelle ! Rappelons que parmi les avantages de Spring Boot, il y a sa facilité de déploiement car le JAR qui résulte de la compilation embarque tout.

Par exemple, même le serveur Tomcat qui permet d’exécuter une application web est embarqué. Nul besoin d’installer un serveur Tomcat, il est déjà là !

Récapitulons les méthodes employées :
- À travers l’**IDE** avec “Run As”, “Spring Boot App”.
- Avec **le goal Maven** `spring-boot:run`.
- **En exécutant le JAR** grâce à la commande `java -jar`.

Nous sommes bien d’accord, le résultat est le même ! Gardons à l’esprit que dans un contexte professionnel, on livrera généralement un JAR, et c’est ce dernier qui sera exécuté.

Les méthodes de déploiement et d’exécution via l’IDE et Maven sont surtout utiles pour les développeurs.

#### En résumé
- Spring Boot fournit une **annotation @SpringBootTest qui permet de charger le contexte Spring** lors de l’exécution des tests.
- Déployer une application Spring Boot est très facile, car l’artefact qui en résulte est un **simple JAR** où tout est embarqué.
- Il existe plusieurs méthodes pour exécuter notre application Spring Boot :
    - Via l’IDE directement.
    - Grâce à Maven et au goal spring-boot:run.
    - En exécutant la commande java -jar.

## Partie 3 - Créez une API avec Spring Boot
### Créez l'API avec les bons starters
Bienvenue dans la troisième partie du cours sur Spring Boot !

> Que vas-tu nous apprendre, vu que l’on connaît désormais chaque étape pour programmer une application avec Spring Boot ?

Tututu, ne vous emballez pas, il y a tout à apprendre encore ! C’est vrai, je vous ai appris à vous servir du marteau pour planter un clou, mais avez-vous pour autant réalisé un ouvrage digne de ce nom ? Je ne crois pas. 

C’est justement l’objectif de la partie 3 et de la partie 4 de ce cours : réaliser des applications avec Spring Boot, qui vont bien au-delà d’un simple Hello World ; c’est-à-dire **construire un projet digne de ce nom**.

Je vous propose un deal pour chaque étape, je vous donne quelques indices, vous essayez puis on corrige.

Autant dire que vous n’allez pas vous ennuyer ! 

Prêt ?! Que la force soit avec vous ! 

#### Plongez-vous dans votre nouvelle mission
[Logo HR Association](https://user.oc-static.com/upload/2020/11/11/1605073055986_18.png)

HR Association est une entreprise qui souhaite offrir un service de gestion d’employés aux petites entreprises.

L’idée est d’offrir une suite d’outils numériques (logiciel, application web, voire application mobile) prête à l’emploi.

Voici l’architecture imaginée :

[Architecture des composants logiciels de HR Association](https://user.oc-static.com/upload/2020/11/11/16050731499577_image9.png)

Pour lancer ce projet, HR Association souhaite avant tout mettre à disposition une API qui permettra à toutes les autres applications d’accéder aux mêmes données.

Vous allez réaliser une **API**. Rappelons-le, une API est un programme qui a pour vocation de **communiquer avec d’autres programmes**. L’idée étant de gérer des **employés**, l’API devra donc offrir un **CRUD** (Create, Read, Update, Delete) pour les données des employés.

Les données seront dans une **base de données H2** (pour ceux qui sont un peu initiés aux bases de données, n’hésitez pas à faire du MySQL, PostgreSQL ou autre).

> H2 est une base de données relationnelle Java très légère, qui par défaut fonctionne en “in memory”. Cela signifie qu’au démarrage du programme, la structure de la base est construite ; et lorsque le programme s’arrête, le contenu de la base de données est supprimé. On ne conserve donc pas les modifications apportées d’une exécution à l’autre du programme.

Notre API devra donc **exposer des endpoints** correspondant aux actions du CRUD, et **communiquer avec la base de données** pour récupérer ou modifier les informations des employés. À noter que l’API sera de type **REST**.

> Besoin de découvrir les API REST ? Par ici avec le cours [Adoptez les API REST pour vos projets web](https://openclassrooms.com/fr/courses/6573181-adoptez-les-api-rest-pour-vos-projets-web).

#### À vous de jouer
Vous souvenez-vous de la première étape ? **Créer la structure minimale du projet en définissant les bons starters** !

Prenez en compte les besoins techniques de l’application, cherchez les starters dans la liste existante, et **regardez avec soin les descriptions** pour identifier ceux qui vous sont nécessaires ! On se retrouve dans quelques minutes pour la correction.

#### Correction : identifiez les bons starters pour créer l'API
Quelques explications :
- Pour les “Project Metadata”, je suis resté simple :
    - group: com.openclassrooms (un standard) ;
    - artifact: api (on “appelle un chat un chat”, n’est-ce pas ?).
- Pour les dépendances :
    - **Spring Web** : comme la description l’indique, permet de faire du RESTful, ce qui correspond à notre API pour exposer des endpoints.
    - **Lombok** : vous ne pouviez pas le deviner, c’est une librarie pour optimiser certaines classes, je vous en parle un peu plus loin dans le chapitre.
    - **H2 Database** : comme on dit, “C'est comme le Port-Salut, c'est écrit dessus” ; on veut faire du H2, alors on prend cette dépendance.
    - **Spring Data JPA** : permet de gérer la persistance des données avec la base de données ; peut-être le plus difficile à identifier pour vous si vous n’avez jamais fait de persistance de données avec Spring.

#### En résumé 
- Pour implémenter une API qui communique avec une base de données, 3 éléments sont essentiels :
    - Le starter web qui permettra d’exposer les endpoints.
    - Un starter pour gérer la persistance des données (comme Spring Data JPA).
    - La dépendance pour le driver de la base de données concernée (par ex. H2 Database ou MySQL Driver).

### Configurez et structurez votre API avec des packages
Notre structure minimale étant prête, il nous faut désormais :
- Structurer avec des packages, comme nous l’avons vu dans la partie 2.
- Configurer notre application. Vous allez pouvoir vous plonger dans la configuration de la base de données H2.

#### À vous de jouer !
Je vous propose de passer à l’action : à vous de structurer et de configurer l’application.

> Comment faire pour configurer l’accès à la base de données?

Pour configurer la **base de données H2**, il existe plusieurs méthodes possibles, que vous pourrez découvrir grâce à une simple recherche sur le Web.

Cependant, je vous encourage à laisser le **comportement par défaut** qui implique du coup zéro configuration (partisans du moindre effort, bonjour ! ), et à ajouter uniquement la **propriété pour activer la console** de visualisation de la base de données (je vous laisse chercher sur le Web ).

> Comment faire pour insérer des données dans la base de données ?

Au-delà de la configuration de H2, il s’agit aussi de fournir la structure de la base de données et des données. Pour vous aider, voici un fichier nommé [data.sql](https://github.com/OpenClassrooms-Student-Center/HR-Association/blob/master/api/src/main/resources/data.sql) qui contient la structure qui sera utilisée, ainsi que quelques données. 

Il s’agit d’une **unique table nommée Employees, avec 5 colonnes**. Ce fichier est à placer dans le répertoire **src/main/resources**. 

Et rappelez-vous la puissance de Spring Boot : il sera pris en compte automatiquement sans que vous ayez quoi que ce soit à faire ! De ce fait, **le script SQL sera exécuté au démarrage de l’application**, et votre base de données contiendra la table et les données.

Vous connaissez désormais la musique, on se retrouve juste en dessous pour une correction !

#### Correction
##### Créez des packages
Certainement le plus facile de cet exercice, voici tout simplement le résultat avec une capture d’écran :
<!-- insérer image -->

##### Définissez les propriétés
Maintenant, parlons des propriétés et donc du fichier applications.properties : avez-vous repris les propriétés vues dans la partie 2 ? Avez-vous trouvé la propriété pour activer la console H2 ?

Et voici mon résultat :
```java
#Global configuration
spring.application.name=api

#Tomcat configuration
server.port=9000

#Log level configuration
logging.level.root=ERROR
logging.level.com.test=INFO
logging.level.org.springframework.boot.autoconfigure.h2=INFO
logging.level.org.springframework.boot.web.embedded.tomcat=INFO

#H2 Configuration
spring.h2.console.enabled=true
```

- spring.application.name=api : pour définir un nom à l’application ;
- server.port=9000 : pour ne pas être sur le port par défaut 8080 ;
- logging.level :
    - root=ERROR : par défaut, seules les traces en ERROR s’affichent. L’idée est simple : réduire les affichages dans la console de toutes les "3rd party",
    - com.openclassrooms=INFO : pour ce qui est de notre code, on est en INFO pour avoir du détail,
    - org.springframework.boot.autoconfigure.h2=INFO : permet de voir dans la console l’URL jdbc de la base H2,
    - org.springframework.boot.web.embedded.tomcat : permet de voir dans la console le port utilisé par Tomcat au démarrage ;
- spring.h2.console.enabled=true : correspond à la propriété pour activité de la console H2. 

Concernant la console H2, une fois l’application démarrée, vous pouvez aller sur l’URL “http://localhost:9000/h2-console”. Une fenêtre de login s’ouvre, et il est nécessaire d’indiquer l’**URL Jdbc** (qui change à chaque démarrage de l’application).

Dans votre console, vous aurez une ligne qui doit ressembler à la suivante :

`H2 console available at '/h2-console'. Database available at 'jdbc:h2:mem:9c5afa97-0c51-4c33-8578-c2872e4d2a25'` 

Récupérez l’URL JDBC (en l'occurrence jdbc:h2:mem:9c5afa97-0c51-4c33-8578-c2872e4d2a25'), saisissez dans le formulaire comme ci-dessous, puis “Connect”. Le username par défaut est bien “sa”, et le password par défaut est vide.

Une fois connecté, vous pouvez consulter le contenu de votre table.

Nous voilà prêts pour la 3e étape : la structure de packages est créée, la configuration est en place, et la base de données est fonctionnelle !

C’est l’heure d’écrire le code !

> Envie de découvrir la configuration pour une base de données MySQL ? Je vous invite à lire la [documentation de Spring](https://spring.io/guides/gs/accessing-data-mysql/) (en anglais), notamment la section "Create the application.properties File".

#### En résumé
- Les besoins techniques du projet vont influer sur votre configuration.
- La structure des packages reste le standard : controller / service / repository / model.
- Grâce à Spring Boot, la mise en œuvre de la base de données requiert 0 ligne de configuration, si ce n’est pour activer la console H2.

### Créez un contrôleur REST pour gérer vos données
Dans ce chapitre, oublions le mode exercice/correction, je ne veux pas vous faire chercher des heures et des heures si vous n’avez jamais eu l’occasion de développer une API REST avec Spring. Je vous propose donc de vous montrer **pas à pas comment arriver au résultat final**, et je vous invite à appliquer les étapes en même temps que moi.

#### Modélisez la table en entité Java
Première chose à faire, afin de manipuler les données persistées dans la base de données, nous allons créer une classe Java qui est une entité. Cela signifie que **la classe représente la table**. Chaque ligne de la table correspondra à une instance de la classe. Créons la classe **Employee** dans le package com.openclassrooms.api.model.

Voici la classe en question :
```java
package com.test.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    private String mail;

    private String password;

}
```
Arrêtons-nous sur ses spécificités :
- @Data est une annotation Lombok. Nul besoin d’ajouter les getters et les setters. La librairie Lombok s’en charge pour nous. Très utile pour alléger le code.
- @Entity est une annotation qui indique que la classe correspond à une table de la base de données.
- @Table(name=”employees”) indique le nom de la table associée.
- L’attribut id correspond à la clé primaire de la table, et est donc annoté @Id. D’autre part, comme l’id est auto-incrémenté, j’ai ajouté l’annotation @GeneratedValue(strategy = GenerationType.IDENTITY).
- Enfin, firstName et lastName sont annotés avec @Column pour faire le lien avec le nom du champ de la table.

> Je n’ai pas eu besoin de mettre cette annotation pour mail et password, car le nom du champ de la table est identique.

#### Implémentez la communication entre l’application et la base de données
Une fois que l’entité est créée, nous devons implémenter le code qui déclenche les actions pour communiquer avec la base de données. Bien évidemment, ce code se servira de notre classe entité.

Le principe est simple, notre code fait une requête à la base de données, et le résultat nous est retourné sous forme d’instances de l’objet Employee.

> Quel est ce code à implémenter ?

**Une interface !**

> Mais une interface ne contient pas de code, comment peut-elle exécuter des requêtes ?

C’est là toute la puissance du **composant Spring Data JPA** ! Il nous permet d’exécuter des requêtes SQL, sans avoir besoin de les écrire.

Dans le package com.test.api.repository, créez une **interface** nommée **EmployeeRepository**. Le code sera le suivant :
```java
package com.test.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.test.api.model.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

}
```

Comme à notre habitude, expliquons : @Repository est une annotation Spring pour indiquer que la classe est un bean, et que son rôle est de communiquer avec une source de données (en l'occurrence la base de données).
> En réalité, **@Repository** est une spécialisation de **@Component**. Tout comme @Component, elle permet de déclarer auprès de Spring qu’une classe est un bean à exploiter. Par son nom, on fournit au développeur une indication sémantique sur le rôle de la classe. Vous pourriez cependant utiliser l’annotation @Component, et cela fonctionnerait très bien, mais il est recommandé d’utiliser les annotations qui offrent cette valeur sémantique supplémentaire.

CrudRepository est une interface fournie par Spring. Elle fournit des méthodes pour manipuler votre entité. Elle utilise la généricité pour que son code soit applicable à n’importe quelle entité, d’où la syntaxe “CrudRepository**<Employee, Long>**” (je ne vous fais pas un cours sur la généricité, cela fait partie de vos acquis de développeur Java )

> La classe entité fournie doit être annotée @Entity, sinon Spring retournera une erreur.

Ainsi, vous pouvez utiliser les méthodes définies par l’interface CrudRepository. Pour en avoir la liste complète, rendez-vous sur la [documentation](https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/CrudRepository.html). Je vous en dis plus dans la partie qui suit !

Et… c’est tout ! Génial, non ? Une dizaine de lignes de code uniquement pour cette étape.

#### Créez un service métier
Si nous analysons la structure créée, nous avons tiré profit des couches model et repository, mais rien n’a été fait dans les autres couches. Il reste donc “service” et “controller” à implémenter.

La couche “service” est dédiée au “**métier**”. C’est-à-dire appliquer des traitements dictés par les règles fonctionnelles de l’application. Imaginez par exemple que votre application doive sauvegarder tous les noms des employés en majuscules, mais que le nom nous arrive en minuscules.

À quel endroit de notre code allons-nous effectuer l’opération de mise en majuscules ? Vous l’avez compris, dans la couche “métier”.

Allez, j’en profite pour vous faire un récapitulatif de l’objectif de chaque couche :

| **Couche** | **Objectif**                                  |
| ---------- | --------------------------------------------- |
| controller | Réceptionner la requête et fournir la réponse |
| service    | Exécuter les traitements métiers              |
| repository | Communiquer avec la source de données         |
| model      | Contenir les objets métiers                   |

Ainsi, lorsqu’une requête est réceptionnée, la couche controller délègue les traitements métiers à exécuter. La couche service pourra ensuite faire appel à la couche repository.

> Et quels sont nos traitements métiers ?

Eh bien pour l’instant, on n'en a pas.  Cependant, la couche service est également un pont entre le controller et le repository. De ce fait, nous allons créer une classe EmployeeService dont voici le code :
```java
package com.test.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.test.api.model.Employee;
import com.test.api.repository.EmployeeRepository;

import lombok.Data;

@Data
@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Optional<Employee> getEmployee(final Long id) {
        return employeeRepository.findById(id);
    }

    public Iterable<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    public void deleteEmployee(final Long id) {
        employeeRepository.deleteById(id);
    }

    public Employee saveEmployee(Employee employee) {
        Employee savedEmployee = employeeRepository.save(employee);
        return savedEmployee;
    }

}
```

Vous noterez avec intérêt que chaque méthode a pour unique objectif d’appeler une méthode de l'employeeRepository.

Un petit zoom sur l’annotation **@Service** : tout comme l’annotation @Repository, c’est une **spécialisation de @Component**. Son rôle est donc le même, mais son nom a une valeur sémantique pour ceux qui lisent votre code. Pour le reste, ce code devrait être à votre portée.

Passons maintenant à la couche controller !

#### Exposez les endpoints REST
Nous arrivons à la dernière étape. Nous sommes en train de développer une API, c’est-à-dire une application qui va communiquer avec d’autres applications. Pour rendre cela possible, il est obligatoire de fournir aux applications qui voudront communiquer avec nous un **moyen** de le faire. 

> Notre application est comme une maison. Si on n'y met aucune porte, impossible d’y accéder ! Les portes de notre application sont ce qu’on appelle des **endpoints**. Un endpoint est associé à une URL. Lorsqu'on appelle cette URL, on reçoit une réponse, et cet échange se fait en **HTTP**.

De plus, on va suivre le modèle **REST** (par exemple pour le format des URL). 

> Comment créer ces fameux endpoints ? Comment l'associer à une URL ? Dois-je écrire des réponses HTTP ? Comment suivre le modèle REST ? 

Souvenez-vous que l’un des avantages de Spring et Spring Boot est de vous fournir les composants logiciels qui vous évitent de faire du code complexe ! Le starter “spring-boot-starter-web” nous fournit justement tout le nécessaire pour créer un endpoint. Laissez-moi vous donner la recette. Il faut :
- une classe Java annotée **@RestController** ;
- que les méthodes de la classe soient annotées. Chaque méthode annotée devient alors un endpoint grâce aux annotations **@GetMapping**, **@PostMapping**, **@PatchMapping**, **@PutMapping**, **@DeleteMapping**, **@RequestMapping**.

Et… c’est tout ! Encore une fois, la simplicité est de rigueur. Regardons cela en code :

```java
package com.test.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.api.model.Employee;
import com.test.api.service.EmployeeService;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
    * Read - Get all employees
    * @return - An Iterable object of Employee full filled
    */
    @GetMapping("/employees")
    public Iterable<Employee> getEmployees() {
        return employeeService.getEmployees();
    }

}
```

C’est l’heure des explications !

Premièrement, @RestController atteint 2 objectifs :
1. Comme @Component, elle permet d’indiquer à Spring que cette classe est un bean.
2. Elle indique à Spring d’**insérer le retour de la méthode au format JSON dans le corps de la réponse HTTP**. Grâce à ce deuxième point, les applications qui vont communiquer avec votre API accéderont au résultat de leur requête en p**arsant la réponse HTTP**.

Deuxièmement, j’ai injecté une instance d'EmployeeService. Cela permettra d’appeler les méthodes pour communiquer avec la base de données.

Troisièmement, j’ai créé une méthode getEmployees() annotée @GetMapping(“/employees”).

Cela signifie que **les requêtes HTTP de type GET à l’URL /employees** exécuteront le code de cette méthode. Et ce code est tout simple : il s’agit d’appeler la méthode getEmployees() du service, ce dernier appellera la méthode findAll() du repository, et nous obtiendrons ainsi tous les employés enregistrés en base de données.

> C'est tout ?

Eh oui, c’est tout.  Il est vrai que je vous ai fait implémenter le cas le plus simple. Mais notez comment Spring Boot fait tout dans l’ombre pour vous, vous ne vous êtes pas préoccupé de la conversion de votre code Java en JSON, ni de comment réceptionner une requête HTTP, et encore moins de comment écrire une réponse HTTP !

> Et si on veut ajouter un nouvel employé ? Ou supprimer un employé ?

Bonne question, c’est là où les autres annotations vont vous aider :
| **Annotation**  | **Type HTTP** | **Objectif**                                                 |
| --------------- | ------------- | ------------------------------------------------------------ |
| @GetMapping     | GET           | Pour la **lecture** de données.                              |
| @PostMapping    | POST          | Pour **l’envoi** de données. Cela sera utilisé par exemple pour créer un nouvel élément. |
| @PatchMapping   | PATCH         | Pour **la mise à jour partielle** de l’élément envoyé.       |
| @PutMapping     | PUT           | Pour **le remplacement complet** de l’élément envoyé.        |
| @DeleteMapping  | DELETE        | Pour la **suppression** de l’élément envoyé.                 |
| @RequestMapping |               | Intègre tous les types HTTP. Le type souhaité est indiqué comme attribut de l’annotation. Exemple :<br />@RequestMapping(method = RequestMethod.GET) |

Nous avons ici tout le nécessaire pour implémenter la création d’un nouvel employé ou sa suppression, par exemple. Je ne vais pas tout vous montrer dans le détail, car ce cours n’en finirait plus, sinon. 

Je vous ai tout de même mis à disposition ce repository avec toutes les actions implémentées : [par ici](https://github.com/OpenClassrooms-Student-Center/HR-Association/tree/master/api). Allons maintenant vérifier si notre implémentation est valide !

#### En résumé
- Notre entité du model est modélisée, et @Entity est l’annotation obligatoire !
- La communication aux données s’effectue via une classe annotée @Repository.
- La classe annotée @Service se charge des traitements métiers.
- Nos controllers @RestController nous permettent de définir des URL et le code à exécuter quand ces dernières sont requêtées.

### Testez votre API avec Spring Boot
Ce chapitre apportera une conclusion à cette partie de cours. Notre objectif est de tester les controllers ; or, ces derniers seront appelés à travers des URL par les programmes qui communiqueront avec.

La question est donc : comment simuler un appel de ce genre ?

C’est là où Spring Boot entre une nouvelle fois en jeu : il nous permet d’exécuter des requêtes sur notre controller. La clé de tout cela est l’annotation **@WebMvcTest**. Je vous propose de passer à l’action !
#### Rédigez des tests unitaires avec @WebMvcTest
Je ne vais pas vous abandonner à votre sort pour cette grande première avec les tests et Spring Boot. Laissez-moi vous guider.

Commencez par observer le code suivant :
```java
package com.test.api;


import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.test.api.controller.EmployeeController;
import com.test.api.service.EmployeeService;

@WebMvcTest(controllers = EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Test
    public void testGetEmployees() throws Exception {
        mockMvc.perform(get("/employees"))
            .andExpect(status().isOk());
    }

}
```

Quelques explications :
- **@WebMvcTest(controllers = EmployeeController.class)** déclenche le mécanisme permettant de tester les controllers. On indique également le ou les controllers concernés.
- L’attribut **mockMvc** est un autre élément important. Il permet d’appeler la méthode “perform” qui déclenche la requête.
- L’attribut employeeService est annoté **@MockBean**. Il est obligatoire, car la méthode du controller exécutée par l’appel de “/employees” utilise cette classe.

> Encore une fois, je vous redirige vers le cours sur [les tests](https://openclassrooms.com/fr/courses/6100311-testez-votre-code-java-pour-realiser-des-applications-de-qualite) si la notion de mock vous échappe. Pour rappel, un mock est un objet qui se substitue à un autre objet.
- La méthode perform prend en paramètre l’instruction **get(“/employees”)**. On exécute donc une requête GET sur l’URL /employees.
- Ensuite, l’instruction **.andExpect(status().isOk())** indique que nous attendons une réponse HTTP 200. 

L’utilisation de @WebMvcTest permet d’écrire des **tests unitaires** sur le controller. Autrement dit, on vérifie uniquement le code exécuté dans la méthode du controller en mockant les appels au service.

#### Rédigez des tests d’intégration avec @SpringBootTest
Pour écrire des **tests d’intégration**, on peut utiliser l’annotation @SpringBootTest que je vous ai déjà présentée.

> Rappelons que les tests unitaires ont pour vocation à tester uniquement le contenu d’une méthode, alors que les tests d’intégration impliquent de tester plus largement une fonctionnalité.

```java
package com.test.api;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetEmployees() throws Exception {
        mockMvc.perform(get("/employees"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].firstName", is("Laurent")));
    }

}
```

Le code est très similaire, mais les différences sont les suivantes :
- Les annotations **@SpringBootTest** et **@AutoConfigureMockMvc** permettent de charger le contexte Spring et de réaliser des requêtes sur le controller.
- L’attribut annoté @MockBean a disparu, plus besoin d’un mock pour EmployeeService, car ce dernier a été injecté grâce à @SpringBootTest.
- En plus de vérifier si le statut vaut 200, on vérifie le contenu retourné grâce à `jsonPath("$[0].firstName", is("Laurent"))`. 
    - $ pointe sur la racine de la structure JSON.
    - [0] indique qu’on veut vérifier le premier élément de la liste.
    - firstName désigne l’attribut qu’on veut consulter.
- is(“Laurent”) est ce que l’on attend comme résultat.

Pour finir ce chapitre pour le moins riche, je vous demande de déployer votre projet. Je vous laisse réaliser cette tâche qui n’a rien de différent par rapport à ce que vous avez déjà fait dans la partie 2 de ce cours. 

#### En résumé 
- Des tests unitaires sur nos controllers REST sont exécutables grâce à @WebMvcTest.
- Des tests d’intégration sont exécutables grâce à @SpringBootTest et @AutoConfigureMockMvc.
- La procédure de déploiement ne subit aucun impact, c’est toujours la même !

## Créez une application web avec Spring Boot
### Créez l'application web avec les bons starters
Bienvenue dans la quatrième et dernière partie de cours. Nous allons ensemble relever un dernier défi : **réaliser une application web avec Spring Boot** !

#### Plongez-vous dans votre nouvelle mission
Votre mission, si vous l’acceptez, est d’aider HR Association dans la création de sa suite d’outils de gestion des employés à destination des petites entreprises.

Voici l’architecture imaginée :
[Architecture des composants logiciels de HR Association](./readMeIMG/image1.png)

Lors de la partie 3 de ce cours, nous avons réalisé l’API qui sera exploitée par les différents clients.

Aujourd’hui, l’objectif est de développer une application web qui permettra de :
- Visualiser les employés.
- Ajouter un nouvel employé.
- Modifier un employé.
- Supprimer un employé.

Voici un visuel :
[Page d’accueil du système de gestion des employés](./readMeIMG/accueil.png)

Techniquement, nous allons évidemment utiliser **Spring Boot**, et je vous apprendrai comment renvoyer une page HTML et comment communiquer avec une API. Ce sera passionnant !

Allons-y ! 

#### À vous de jouer !
Je vous laisse réaliser la première étape, à savoir **créer la structure minimale** de ce projet. Utilisez **Spring Initializr** ou bien **STS**, le résultat sera le même !

L’important est que vous choisissiez bien vos **starters** en fonction de ce que vous souhaitez réaliser. Cela ne sera pas facile pour vous, mais en parcourant attentivement la liste des starters et en lisant leur description, vous devriez pouvoir vous approcher du bon résultat.

Pour les “**Project Metadata**”, je suis resté simple, vous avez toute latitude pour saisir les données de votre choix.

Pour les “**Dependencies**”, c’est ici que ça se complique !

Je pense que **Spring Web** a été une évidence pour vous ! Je vous félicite d’avoir mis le doigt dessus. Sachez que cela ne nous permettra pas uniquement de fournir une page HTML à afficher. Ce starter contient également le code pour communiquer avec une API.

Lorsque vous avez parcouru les “**Template Engines**”, vous avez certainement noté les mentions du HTML, et cela vous a peut-être donné envie de choisir un de ces starters. Si c’est le cas, vous avez eu raison !

En plus de Spring Web, utilisons **Thymeleaf** qui est l’un des **moteurs de template** (template engine) les plus couramment utilisés.

En deux mots, un moteur de template HTML va nous **aider à formater la page HTML** que nous voulons renvoyer.

Mon dernier starter, Lombok, est une librairie qui permet d’optimiser certaines classes. Par exemple, grâce à Lombok, en ajoutant l’annotation @Data à une classe, je n’ai plus besoin d’écrire les getters et setters, magique !

#### En résumé
- Pour implémenter une application web qui communique avec une API, 2 éléments sont essentiels :
    - Le starter **Spring Web** qui permettra de renvoyer des pages HTML et communiquer avec l’API.
    - Un **moteur de template** comme Thymeleaf qui permet de formater le HTML.

### Configurez et structurez votre projet avec des packages
Notre structure minimale étant prête, il nous faut désormais :
- Structurer avec des packages.
- Configurer notre application.

#### À vous de jouer !
Pour la structure de packages, je vous encourage à reprendre ce que je vous ai montré dans la [partie 2](https://openclassrooms.com/fr/courses/6900101-creez-une-application-java-avec-spring-boot/7077993-structurez-et-configurez-votre-projet). Comme indiqué, c’est une organisation standard qui s’applique très bien aux projets web.

Pour la configuration, vous pouvez également reprendre ce qu’on avait vu ensemble précédemment concernant le fichier application.properties. On reste sur une configuration standard.

Je vous laisse la main, essayez de votre côté, on se retrouve dans quelques minutes pour débriefer ces étapes.

#### Correction
##### Créez les packages
Certainement le plus facile de cet exercice, voici tout simplement la capture d’écran du résultat.

> Cette structure en couches est utilisable **quel que soit l’objectif fonctionnel**. Nous avons pu l’utiliser pour le HelloWorld de la [**partie 2**](https://openclassrooms.com/fr/courses/6900101-creez-une-application-java-avec-spring-boot/7077993-structurez-et-configurez-votre-projet), l’API de la [**partie 3**](https://openclassrooms.com/fr/courses/6900101-creez-une-application-java-avec-spring-boot/7078013-configurez-et-structurez-votre-api-avec-des-packages), et désormais pour cette application web.

##### Définissez les propriétés
Regardons maintenant le résultat de la configuration du fichier application.properties.

Et voici mon résultat en image :
```java
#Global configuration
spring.application.name=webapp

 #Tomcat configuration
 server.port=9001
 
 #Log level configuration
 logging.level.root=ERROR
 logging.level.com.openclassrooms=INFO
 logging.level.org.springframework.boot.web.embedded.tomcat=INFO
```

- spring.application.name=webapp : pour définir un nom à l’application ;
- server.port=9001 : pour ne pas être sur le port par défaut 8080, ni sur le port 9000 qui est utilisé par l’API (Cf. partie 3 chapitre 2) ;
- logging.level :
    - root=ERROR : par défaut, seules les traces en ERROR s’affichent. L’idée est simple : réduire les affichages dans la console de toutes les "3rd party",
    - com.openclassrooms=INFO : pour ce qui est de notre code, on est en INFO pour avoir du détail,
    - org.springframework.boot.web.embedded.tomcat : permet de voir dans la console le port utilisé par Tomcat au démarrage.

#### Allez plus loin dans la gestion des propriétés
Je souhaite saisir l’opportunité de ce chapitre pour vous apprendre à créer de la configuration **custom**. Jusqu’à présent, je vous ai montré comment configurer votre application en fonction de propriétés existantes.

> Mais comment faire si on souhaite créer de nouvelles propriétés ?

Rassurez-vous, Spring Boot s’occupe de tout ! (Comme d’hab )

Dans la partie 2 du cours, chapitre 2, j’ai eu l’occasion de vous apprendre que Spring Boot lit **nos sources de propriétés**, et rend disponible les propriétés via des beans.

Le fichier application.properties correspond à l’une de ces sources de propriétés.

Jusqu’à présent, nous avons saisi des valeurs pour des propriétés existantes. Propriétés utiles à des classes de Spring qu’on ne manipule pas. Cependant, si je souhaite ajouter une nouvelle propriété, comment y accéder dans mon code ?

##### Étape 1 : Créez la nouvelle propriété.

J’ai commencé par ajouter ma nouvelle propriété dans mon fichier application.properties (pour rappel, ce fichier est ma source de propriétés).
```
com.test.webapp.apiUrl=http://localhost:9003
```

##### Étape 2 : Créez le bean associé.

J’ai ensuite créé une nouvelle classe nommée CustomProperties. Je l’ai annotée avec :

@Configuration : permet de déclarer la classe en tant que bean de configuration.

@ConfigurationProperties(prefix = “com.openclassrooms.webapp”) : demande à Spring de charger les properties qui commencent par “com.openclassrooms.webapp” au sein des attributs de la classe.

@Data : génère automatiquement getter/setter pour les attributs de classe.

J’ai conclu cette classe en ajoutant l’attribut apiUrl.

```java
package com.test.webapp;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.*;

@Data
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix="com.test.webapp")
public class CustomProperties {

	private String apiUrl;
}
```

##### Étape 3 : Utilisez les propriétés dans le code.

Pour la démonstration, j’ai modifié la classe WebappApplication, afin qu’elle implémente un CommandLineRunner. Ainsi, dans la méthode run, j’ai voulu vérifier que j’accédais bien à la propriété, en affichant cette dernière dans la console.

Le point clé à retenir est l’injection du bean CustomProperties, comme vous pouvez le voir aux lignes 16/17 ci-dessous.

Il est ensuite facile d’accéder à la propriété, comme le montre la ligne 25.

```java
package com.test.webapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebappApplication implements CommandLineRunner {
	
	@Autowired
	private CustomProperties props;

	public static void main(String[] args) {
		SpringApplication.run(WebappApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(props.getApiUrl());
		
	}

}
```

> Cette étape 3 servant uniquement à démontrer que les étapes précédentes sont valides, je vais revenir à la version initiale de la classe WebappApplication pour la suite du cours.

> Il est également courant de créer un nouveau package nommé "configuration" pour mettre les classes associées. N’hésitez pas à le faire.

#### En résumé
- La structure des packages reste le standard : **controller/service/repository/model**.
- Le fichier **application.properties** est ma source de propriétés.
- Je peux créer des propriétés et les manipuler dans mon code, notamment grâce à l’annotation **@ConfigurationProperties**.

### Écrivez votre code 
#### Modélisez l’objet Employee
L’objectif du code de ce projet sera de :
1. Communiquer avec l’API REST pour récupérer ou modifier les données des employés.
2. Appliquer des traitements métiers spécifiques à l’application web.
3. Afficher les pages web permettant de lister les employés, créer un nouvel employé, modifier un employé existant et supprimer un employé existant.

Les bonnes pratiques de programmation nous amènent à avoir une approche MVC pour ce type d’application : Modèle - Vue - Contrôleur.
[Modèle MVC](./readMeIMG/mvc.png)

En quelques mots, on découpe notre code en 2 pôles : le code “Modèle” permet de décrire les données, le code “Vue” permet d’afficher les données ; le code “Contrôleur” traite les requêtes en interrogeant le “Modèle” et en demandant à la “Vue” de s’afficher. Si on reprend le schéma, voici donc les étapes :
1. Le contrôleur reçoit une demande d'un utilisateur, envoyée depuis le navigateur.
2. Le contrôleur demande les données au modèle.
3. Le contrôleur transmet les données à la vue.
4. La vue affiche le résultat sur le navigateur.

> Pour en savoir plus, vous pouvez consulter le cours [Écrivez du code maintenable](https://openclassrooms.com/fr/courses/6810956-ecrivez-du-code-java-maintenable).

Pour suivre ce principe de programmation, nous devons identifier la ou les données que nous manipulons. En l'occurrence, la donnée que nous manipulons est un employé ! 

D’ailleurs, une observation du code de l’API nous confirme cela : la classe `com.test.api.controller.EmployeeController` contient des méthodes dont le type retour est un objet `Employee` ou bien `Iterable<Employee>`.

#### À vous de jouer 
À vous de jouer, écrivez une classe Java de type POJO qui modélise un employé.

```java
package com.test.webapp.model;

import lombok.Data;

@Data
public class Employee {

    private Long id;

    private String firstName;

    private String lastName;

    private String mail;

    private String password;
}
```

Quelle simplicité, n’est-ce pas ?!

Je me suis inspiré de la classe com.openclassrooms.api.model.Employee. J’ai donc mes 5 attributs mis à disposition par l’API :
- id ;
- firstName ;
- lastName ;
- mail ;
- password.

L’annotation `@Data` permet de générer automatiquement les getters et setters pour chaque attribut.

> Ce n’est pas une règle absolue d’avoir l’objet similaire entre la webapp et l’API. On pourrait avoir un objet partiel côté webapp (sans password par exemple, pour éviter que cette donnée soit côté web).

Notre classe Employee étant prête, on peut s’intéresser à la communication entre l’application web et l’API.

#### Implémentez la communication entre l’application web et l’API REST
Pour ceux qui ont suivi la partie 3 de ce cours, vous avez certainement à l’esprit que la couche “repository” a permis de communiquer avec la base de données. Dans cette situation, la base de données correspondait à **notre source de données**.

Mais pour l’application web, quelle est la source de ces données ? Le contexte de cette application vous l’a fait comprendre, c’est bien évidemment l’**API** !

Résultat, notre objectif va être de faire communiquer l’application web avec l’API REST.

> Comment réussir ?

Le starter Spring Web nous fournit tout le code nécessaire pour cela (décidément, Spring Boot ne nous déçoit jamais ! ). Nous allons nous servir de la classe **RestTemplate**.

> Suite à l’avènement de la programmation réactive, l’utilisation de l’objet RestTemplate va progressivement être remplacée par l’utilisation des classes du module Spring Webflux. Ainsi, vous trouverez sur le Web des notices “Deprecated” pour RestTemplate. Pas de panique, RestTemplate reste une façon valide de communiquer avec une API en synchrone, et c’est très bien pour nous débutants. Laissons la programmation réactive de côté pour le moment.

Vous ne pouvez pas deviner l’utilisation de RestTemplate, et je n’ai pas envie que vous passiez des heures à chercher ; alors laissez-moi vous montrer directement comment nous en servir :
```java
package com.test.webapp.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.test.webapp.CustomProperties;
import com.test.webapp.model.Employee;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EmployeeProxy {

    @Autowired
    private CustomProperties props;

    /**
    * Get all employees
    * @return An iterable of all employees
    */

    public Iterable<Employee> getEmployees() {
        String baseApiUrl = props.getApiUrl();
        String getEmployeesUrl = baseApiUrl + "/employees";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<Employee>> response = restTemplate.exchange(
                getEmployeesUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Iterable<Employee>>() {}
                );

        log.debug("Get Employees call " + response.getStatusCode().toString());
        
        return response.getBody();
    }

}
```

Le concept est le suivant : RestTemplate permet d’exécuter une requête HTTP. On a donc besoin de fournir l’URL, le type de requête (GET, POST, etc.), et pour finir le type d’objet qui sera retourné.

Ce dernier point est très important, RestTemplate non seulement fait la requête à l’API mais en plus **convertit le résultat JSON en objet Java** et ça, c’est top pour nous !

Si on détaille le code, voici les explications :
- Ligne 28 : grâce à notre objet CustomProperties, on récupère l’URL de l’API.
- Ligne 29 : on complète l’URL de l’API par le path de l'endpoint à joindre.
- Ligne 31 : on instancie notre objet RestTemplate.
- Ligne 32 : on appelle la méthode exchange en transmettant :
    - l’URL ;
    - la méthode HTTP (grâce à l’enum HttpMethod) ;
    - Null en lieu et place d’un objet HttpEntity, ainsi on laisse un comportement par défaut ;
    - le type retour, ici je suis obligé d’utiliser un objet ParameterizedTypeReference car /employees renvoie un objet Iterable<Employee>. Mais si l’endpoint renvoie un objet simple, alors il suffira d’indiquer <Object>.class. 
- Ligne 41 : on récupère notre objet Iterable<Employee> grâce à la méthode getBody() de l’objet Response.

Vous pouvez tester ce code et constater la bonne récupération des données (n’oubliez pas de démarrer l’API avant) !

> Très bien, mais comment faire si on souhaite également fournir une donnée ? Par exemple, si je veux créer un employé, comment faire pour envoyer à l'API les informations du nouvel employé ?

Le code sera très similaire :
```java
public Employee createEmployee(Employee e) {
    String baseApiUrl = props.getApiUrl();
    String createEmployeeUrl = baseApiUrl + "/employee";

    RestTemplate restTemplate = new RestTemplate();
    HttpEntity<Employee> request = new HttpEntity<Employee>(e);
    ResponseEntity<Employee> response = restTemplate.exchange(
        createEmployeeUrl,
        HttpMethod.POST,
        request,
        Employee.class);

    log.debug("Create Employee call " + response.getStatusCode().toString());

    return response.getBody();
}
```

On retrouve notre objet **RestTemplate** et on fournit toujours l’URL, la méthode HTTP (cette fois POST) et le type retour (en l’occurrence Employee.class).

La grande différence correspond au nouvel objet **HttpEntity** qui, comme vous le constatez, a été instancié avec **en paramètre du constructeur l’objet Employee** (nommé e). 

Je transmets ensuite cet objet HttpEntity comme 3e paramètre de la méthode exchange (ce paramètre que nous avions mis à null précédemment).

Le tour est joué !

> Sur le repository GitHub correspondant au résultat de cette partie, vous trouverez le code pour les actions create, update et delete.

#### Créez un service métier
Le couche service a un double objectif :
- Exécuter les traitements métiers.
- Faire le relais vers la couche repository.

Souvenez-vous de ce tableau, partagé dans un précédent chapitre :
| **Couche** | **Objectif**                                  |
| ---------- | --------------------------------------------- |
| controller | Réceptionner la requête et fournir la réponse |
| service    | Exécuter les traitements métiers              |
| repository | Communiquer avec la source de données         |
| model      | Contenir les objets métiers                   |

Nous avons réalisé les couches model et repository, et la couche service est la suivante. Maintenant que nous avons contextualisé notre tâche, mettons-nous au travail.

Nous allons créer une classe nommée **EmployeeService** dans le package com.openclassrooms.webapp.service.

Bien évidemment, cette classe devra être identifiée comme étant un **bean**. L’annotation utilisée sera **@Service**, qui est une spécialisation de l’annotation @Component que vous connaissez déjà.

En ce qui concerne le contenu de la classe, nous allons écrire des méthodes qui correspondront aux services mis à disposition. Vu que nous restons dans la dynamique du CRUD, voilà un résultat possible :
```java
package com.test.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.webapp.model.Employee;
import com.test.webapp.repository.EmployeeProxy;

import lombok.Data;

@Data
@Service
public class EmployeeService {

    @Autowired
    private EmployeeProxy employeeProxy;

    public Employee getEmployee(final int id) {
        return employeeProxy.getEmployee(id);
    }

    public Iterable<Employee> getEmployees() {
        return employeeProxy.getEmployees();
    }

    public void deleteEmployee(final int id) {
        employeeProxy.deleteEmployee(id);;
    }

     public Employee saveEmployee(Employee employee) {
        Employee savedEmployee;

        // Règle de gestion : Le nom de famille doit être mis en majuscule.
        employee.setLastName(employee.getLastName().toUpperCase());

        if(employee.getId() == null) {
            // Si l'id est nul, alors c'est un nouvel employé.
            savedEmployee = employeeProxy.createEmployee(employee);
        } else {
            savedEmployee = employeeProxy.updateEmployee(employee);
        }
    
        return savedEmployee;
    }

}
```

Bien que cette classe ne soit pas complexe, analysons les points clés :
- Ligne 12 : annotation @Service, point clé comme expliqué précédemment.
- Lignes 15/16 : l’objet EmployeeProxy est injecté, ainsi cette classe pourra faire appel à la source de données à travers le proxy.
- Les méthodes getEmployees(), getEmployee(final int id), deleteEmployee(final int id) sont de simples relais.
- Lignes 30 à 44 : je vous ai simulé un traitement métier pour vous montrer la pertinence de cette couche. En l'occurrence, on met en majuscules le nom de famille. Cette règle de gestion est dictée par un besoin fonctionnel, et c’est l’endroit parfait pour modifier notre objet avant qu’il ne soit sauvegardé en base de données.

Je ne m’étends pas plus sur cette partie du code, qui revêtira toute son importance en fonction de la complexité fonctionnelle des applications que vous implémenterez.

#### Renvoyez les pages HTML
Au début de ce chapitre, je vous ai parlé de l’approche **MVC**. Pour suivre cette approche, nous avons besoin de nous pencher sur la séparation entre le c**ode “contrôleur”** (traiter les requêtes entrantes) et le **code “vue”** (construction du résultat visuel à fournir à l’utilisateur).

> Comment renvoyer des pages HTML avec Spring Boot ?

C’est la question à laquelle nous allons maintenant répondre !

Lors du choix des starters, nous avons bien évidemment sélectionné **Spring Web**, mais également le moteur de template **Thymeleaf**. 

Il nous faut apprendre à :
- Utiliser Spring Web pour traiter une requête qui nous parvient.
- Utiliser Spring Web pour renvoyer une réponse HTML.
- Formater notre réponse HTML avec Thymeleaf.

Vous vous en doutez, Spring Boot va encore entrer en action pour nous simplifier la tâche.

Quel est notre point de départ ?

Un utilisateur va utiliser son navigateur web pour visualiser une page web de notre application. Pour cela, l’utilisateur saisit une URL.

Notre application web doit donc déclencher un traitement différent en fonction de l’URL saisie (par exemple, un appel à l’URL http://www.monsite.com/ doit afficher la page d’accueil, tandis qu’un appel à http://www.monsite.com/contact doit afficher la page de contact).

C’est ici que les classes de type ‘controller’ vont entrer en jeu. Pour chaque URL, nous allons pouvoir implémenter une méthode qui contiendra le code à exécuter.

Ces méthodes devront :
1. Récupérer les données en entrée (s’il y en a).
2. Faire appel aux traitements métiers (en l'occurrence, communiquer avec la couche service).

Retourner une vue HTML.

Mais attention, pour qu’une méthode d’un ‘controller’ puisse retourner une page HTML, il faut que cette dernière existe.

> Mais où écrire notre code HTML ? Pas dans des classes Java, tout de même ?

Vous avez raison, pas dans une classe Java ! Nous allons écrire le HTML dans des **fichiers .html** que l’on nomme également les **templates**. Et Spring Boot avait tout prévu, regardez de plus près votre structure de projet :

Il y a ici un répertoire **Templates** qui a pour vocation à contenir vos fichiers HTML. Voici les étapes à suivre :
- Ajoutons un fichier **home.html** qui servira de page d’accueil. Je reviendrai sur ce fichier un peu après. Notez que cela correspond à écrire le code ‘Vue’ de notre concept MVC.
- Créons une classe nommée **EmployeeController** dans le package controller.
- Annotons la classe EmployeeController afin qu’elle soit détectée comme un bean, en utilisant l’annotation **@Controller**. De nouveau c’est une spécialisation de @Component.

Je vous ai indiqué précédemment que lors de l’appel d’une URL, une des méthodes du controller est exécutée. Mais comment savoir quelle méthode utiliser ?

De nouveau grâce à une annotation. Voici un exemple :
```java
@Controller
public class EmployeeController {

    @GetMapping("/")
    public String home() {
        return "home";
    }

}
```

Le concept est le suivant :
1. L’annotation spécifie le type de requête HTTP et l’URL correspondante.
2. Le texte “home” retourné correspond au nom du fichier HTML.

C’est là que la magie de Spring Boot opère ! À l’appel de l’URL racine (via le type GET, qui est utilisé par défaut dans les navigateurs) de mon application web, la méthode home() sera **automatiquement exécutée**, et Spring **renverra automatiquement une réponse** HTTP contenant dans son corps (donc le body HTTP) le contenu du fichier home.html. Tadaa !

#### À vous de jouer !
Petit exercice pour vous :
1. Ajoutez un fichier home.html dans le répertoire Template. 
2. Mettez un petit bout de HTML dedans (qu’importe). 
3. Créez la classe EmployeeController avec le code ci-dessus.
4. Exécutez votre webapp.
5. Allez sur http://localhost:9001/
6. Constatez le résultat.

Alors, pas mal, hein ?

C’est un bon début, mais nous avons besoin d’aller plus loin ! Vous devez certainement vous poser les questions suivantes :

> Comment afficher dans le HTML une donnée provenant du Java (par exemple une donnée récupérée grâce à la couche service) ?
> 
> Comment le code Java peut-il récupérer une donnée provenant de la page web source (par exemple, ce qu’un utilisateur a saisi dans un formulaire) ?

Je vais vous expliquer comment faire ! Dans les deux cas, Spring Web nous fournit des objets pour réussir, et Thymeleaf nous facilite la tâche.

> Attention, ce cours n’a pas pour vocation à vous apprendre le HTML, le CSS, Thymeleaf ou même Spring Web dans le détail. Je vais donc vous montrer un exemple simple, et je vous encourage à creuser pour aller plus loin.

#### Fournissez des données à la vue
**Premièrement** : *Comment fournir des données à la vue ?*
Spring met à disposition différentes classes pour cela. Regardez cet exemple :
```java
@GetMapping("/")
public String home(Model model) {
    Iterable<Employee> listEmployee = service.getEmployees();
    model.addAttribute("employees", listEmployee);
    
    return "home";
}
```

L’objet **Model** (org.springframework.ui.Model) a été ajouté en paramètre de la méthode home(). Grâce à cela, Spring se charge de nous fournir une instance de cet objet.

Puis, dans le corps de la méthode, j’utilise une méthode *addAttribute* qui permet d’ajouter à mon Model un objet. Le premier paramètre spécifie le **nom** (de mon choix) et le deuxième **l’objet** (ici, la liste des employés en Iterable). 

Ajouter des attributs au Model me permet d’y accéder dans la vue HTML. Comment ? Grâce à Thymeleaf.

Je souhaite mettre en lumière 2 aspects de ce code HTML :
```html
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhml" xmlns:th="http://www.thymeleaf.org">
    
    <head>
        <meta charset="UTF-8">
        <title>Employee Web Application</title>
    </head>
    <body>
        <h2 class="h2">Liste des employées</h2>
        <table>
            <thead>
                <tr>
                    <th>Prénom</th>
                    <th>Nom</th>
                </tr>
            </thead>
            <tbody>
                <tr th:if="${employees.empty}">
                    <td colspan="3">Aucun employée en base de données</td>
                </tr>
                <tr th:each="employee: ${employees}">
                    <td><span th:text="${employee.firstName}"> Prénom </span></td>
                    <td><span th:text="${employee.lastName}"> Nom </span></td>
                </tr>
            </tbody>
        </table>
    </body>
</html>
```

Les instructions **th:if** et **th:each** qui permettent respectivement d’implémenter une condition et une itération, fonctionnalités non présentes en HTML. Thymeleaf me fournit donc la capacité d’écrire un **code HTML dynamique**.

La syntaxe **\${nom de l’attribut}** permet d’accéder à un objet placé comme attribut dans le Model. Notons également que Thymeleaf comprend la programmation objet, et que la syntaxe **\${$objet.attribut}** fonctionne. C’est d’ailleurs ce que je fais pour accéder aux prénom et nom de l’employé.

Vous pouvez tester ce code ;-)

#### Récupérez une donnée provenant de la page web source
**Deuxièmement** : *Comment le code Java peut-il, à l’inverse, récupérer une donnée provenant de la page web source ?*

Voyons 2 situations : une donnée transmise par URL (donc en GET) et une donnée transmise par formulaire (donc en POST).

##### Situation n° 1 : la donnée est transmise par URL
Intéressons-nous d’abord au cas d’une donnée transmise par URL :
```java
@GetMapping("/deleteEmployee/{id}")
public ModelAndView deleteEmployee(@PathVariable("id") final int id) {
    service.deleteEmployee(id);
    return new ModelAndView("redirect:/");
}
```

La méthode **deleteEmployee** possède un paramètre nommé id de type int ; cependant le point clé est l’annotation **@PathVariable** qui signifie que ce paramètre est présent dans l’URL de requête (par exemple http://localhost:9001/deleteEmployee/1). Je peux ensuite me servir de la variable id dans le code : `service.deleteEmployee(id)`;

Côté Thymeleaf, voici le code correspondant :
```html
<a th:href="@{/deleteEmployee/{id}(id=${employee.id})}">Supprimer</a>
```

On utilise l’attribut **th:hreaf** de Thymeleaf, et la syntaxe **@{**} permet de définir une URL. Le chemin `/deleteEmployee/` est complété par l’id à fournir grâce à la syntaxe : `{id}(id==${employee.id})`.

Passons maintenant au cas d’une donnée transmise par un formulaire.

##### Situation n° 2 : la donnée est transmise par un formulaire
```java
@PostMapping("/saveEmployee")
public ModelAndView saveEmployee(@ModelAttribute Employee employee) {
    service.saveEmployee(employee);
    return new ModelAndView("redirect:/");
}
```

La méthode **saveEmployee** est annotée **@PostMapping** et non @GetMapping. Effectivement, il s’agit ici de traiter la validation d’un formulaire, et généralement les formulaires exécutent des requêtes POST.

L’autre point clé est le paramètre de la méthode `@ModelAttribute Employee employee`. @ModelAttribute est l’annotation magique. Cette annotation permet à Spring de récupérer les données saisies dans les champs du formulaire et de construire un objet Employee avec.

Côté Thymeleaf :
```html
<form method="post" th:action="@{/saveEmployee}" th:object="${employee}">
    <input type="text" th:field="*{firstName}">
    <input type="text" th:field="*{lastName}">
    <input type="text" th:field="*{mail}">
    <input type="password" th:field="*{password}">
    <button type="submit" c>Créer</button>
</form>
```

**th: object=${employee}** fait le lien avec le ModelAttribute.

**th:field** donne la correspondance avec les attributs de l’objet associé. Vous pouvez noter la syntaxe particulière : “***{firstName}**”.

Je vais m’arrêter là pour les explications pour ce chapitre. Il a été très dense, et j’ai dû vous transmettre de nombreuses notions. La majorité sont d’ailleurs à approfondir et c’est normal, ici l’objectif était de vous lancer sur les rails, et non pas de vous accompagner jusqu’au bout du chemin.

Le plus important est que vous avez pu constater comment Spring Boot **vous rend plus performant** pour réaliser une application web !

> Vous pouvez retrouver sur ce [repository GitHub](https://github.com/OpenClassrooms-Student-Center/HR-Association/tree/master/webapp) le code complet, vous retrouverez ce que je vous ai montré et quelques détails supplémentaires ! 

### En résumé
- Créer une application web avec Spring Boot correspond à suivre l’**architecture MVC** :
    - le **modèle** correspond aux classes Java qui représentent les **données** à manipuler ;
    - la **vue** correspond aux fichiers **HTML** qui seront retournés à l’utilisateur ;
    - le **contrôleur** correspond aux classes **Java** annotées @Controleur qui font du mapping d’URL (avec par exemple @GetMapping) ;
    - les couches service et repository sont utilisées par les contrôleurs pour obtenir les données à fournir à la vue.
- **RestTemplate** est l’objet clé pour **communiquer avec une API**. Non seulement il exécute des requêtes HTTP, mais en plus il transforme le résultat JSON en objet Java.

### Testez et déployez votre projet web
Amis de Spring Boot, bonjour ! (Si vous êtes arrivés à ce dernier chapitre, c’est forcément que vous êtes devenus amis. )

Pour conclure ce projet web, nous devons non seulement le **tester**, mais également le **déployer**.

Ce chapitre ne devrait pas être trop complexe pour vous, surtout si vous avez suivi la partie 3 du cours.

#### Réalisez un test d’intégration
D’ailleurs à tous ceux qui ont suivi la partie 3, je vous propose l’exercice suivant : **réaliser un test d’intégration pour l’appel de la page d’accueil** (donc URL : “/”) ! On ne vérifiera pas le contenu, mais uniquement le statut. 

Pour les autres, je vous donne la solution juste en dessous.

À travers le screencast suivant, je vais vous montrer ma façon de faire, et je vais même vous en apprendre un peu plus.  C’est parti !

C’était vraiment intéressant de réussir à tester en Java l’appel de notre contrôleur !

Je vous remets le code ici :
```java
package com.test.webapp;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {

    @Autowired
    public MockMvc mockMvc;

    @Test
    public void testGetEmployees() throws Exception {
        mockMvc.perform(get("/"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("home"))
            .andExpect(content().string(containsString("Laurent")));
    }

}
```

Les points importants sont les suivants :
- L’annotation **@SpringBootTest** permet au contexte Spring d’être chargé lors de l’exécution des tests.
- Pour exécuter une requête au contrôleur, on utilise un objet **MockMvc** ; l’attribut correspondant doit être annoté @Autowired pour l'injection de la dépendance. Et la classe doit obligatoirement être annotée **@AutoConfigureMockMvc**, afin qu’un objet MockMvc soit disponible dans le contexte Spring (ainsi il pourra être injecté dans l’attribut).
- La méthode **perform** a en paramètre l’URL à appeler. Puis il s’ensuit une suite d’instructions pour vérifier l’attendu :
    - status().isOk() : la réponse a un code de statut 200 ;
    - view().name(“home”) : le nom de vue retourné correspond au paramètre “home” ;
    - content().string(containsString("Laurent")) : le corps de la réponse contient à un moment ou à un autre le texte Laurent.
- Notons également que andDo(print()) permet au retour de l’appel d’être affiché dans la console (on y verra donc tout le HTML généré).

> À vous d’être pertinent dans ce qui est attendu dans le corps de la réponse !

#### Déployez votre application web
La dernière étape correspond au déploiement. Grâce à Spring Boot, c’est vraiment une formalité ; d’ailleurs, à vous de jouer : déployez votre application ! 

Le screencast suivant sera le dernier du cours, et je vais vous montrer comment déployer l’application web en ayant au préalable également déployé l’API.

Pour l’occasion, j’ai mis un peu de design sur les pages HTML (merci Bootstrap ). Regardons tout ça ensemble :

#### En résumé
- L’application web peut être testée grâce à @SpringBootTest.
- L’objet MockMvc permet d’exécuter des requêtes, comme le ferait un navigateur web.
- Le déploiement est facilité avec Tomcat qui est embarqué dans le JAR généré.

## Le mot de fin
L’univers Spring est immense, et il y a encore beaucoup à découvrir ; mais j’espère que cette base sera un fondement solide pour la suite de votre progression. Prenez le temps de pratiquer, et allez encore plus loin !

Voici quelques idées :

Augmentez votre capacité à utiliser Spring Web pour réaliser des applications web performantes.

Découvrez Spring Security pour la sécurisation de vos applications.

Plongez-vous dans l’architecture microservices grâce à Spring Cloud.

J’en suis convaincu, vous serez des développeurs encore plus performants et efficaces !