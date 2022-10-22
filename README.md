# Java_Spring_Boot
Ceci est l'application de la formation Spring Boot proposer par OpenClassroom : https://openclassrooms.com/fr/courses/6900101-creez-une-application-java-avec-spring-boot

## Identifiez pourquoi et quand utiliser Spring Boot
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

## Découvrez les étapes clés de tout projet Spring Boot

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















