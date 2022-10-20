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

![composantsSpring](readMeIMG\composantsSpring.png)




































