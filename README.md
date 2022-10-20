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












