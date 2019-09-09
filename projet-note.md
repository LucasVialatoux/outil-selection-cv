# Rendu du Mini Projet "Selection de CV"

**Votre travail devra être rendu sous forme d’un projet déposé sur la Forge Lyon 1, au plus tard le dimanche 22 septembre 2019 à 23h59.**

Le mini-projet noté est le fil rouge de tous les TPs. Vous commencez à
travailler sur la base de code au [lab1](lab1-java/), et vous ajoutez
des fonctionnalités tout en améliorant la qualité du code dans la
suite.

Les consignes ci-dessous sont à respecter **impérativement** pour le
rendu.

## Rapport

Votre rendu incluera un rapport, au format PDF, qui doit comprendre
obligatoirement :

* une présentation globale du projet (rapide : ne répétez pas
  l'énoncé),

* Une section « design patterns », donnant une motivation des choix
  d’architecture (et des patterns choisis), et leur explication en
  s’aidant de diagrammes appropriés et adaptés au degré de précision
  et au type d’explication. Donc des diagrammes de classe, mais pas
  que cela, et pas de plats de spaghettis générés automatiquement
  représentant tout le code.
  
* Une section « éthique » où vous expliquerez les choix que vous avez
  fait en terme de stratégie de sélection de CV : comment vous
  assurez-vous que vous choisissez les bons CV, et que vous ne ratez
  pas de canidat intéressant (en particulier, pourrait-on accuser
  votre algorithme de discrimination ?) ? L'algorithme peut-il être
  mis en défaut dans des cas où un humain aurait fait un meilleur
  travail ? Si oui, donnez des exemples et justifiez.
  
* Une section « tests » où vous décrirez les tests manuels que vous
  avez réalisés.

## Qualité du code

### Style

Assurez-vous que votre programme respecte toujours le style imposé
(`mvn test`, qui doit lancer checkstyle).

### Design-pattern

Assurez-vous d'avoir appliqué toutes les consignes du
[lab3](lab3-patterns/).

### Tests et intégration continue

Vérifiez que l'intégration continue mise en place au
[lab2](lab2-tools/) fonctionne toujours.

Les tests automatisés tels que décrits au [lab5](lab5-tests/) doivent
être lancés automatiquement par `mvn tests`, et doivent tous passer
avec succès.

### Portabilité

Clonez, compilez et exécutez votre code **sur une machine vierge**
(c'est-à-dire sur laquelle vous n'avez installé aucune dépendance, ni
configuré le compte utilisateur de façon particulière). Une grande
partie du barème est liée à l'exécution de votre travail. Il est
important que nous arrivions à l'exécuter **directement**. "Ça marche
chez moi" n'est pas une excuse et une démo *a posteriori* ne permet
pas de remonter une note de TP.

## Projet Forge et TOMUSS

Les projets seront rendus en binômes. La date limite est indiquée sur
la page d'accueil du cours.

**Ajoutez les utilisateurs @thomas.bonis, @fadam, @LIONEL.MEDINI,
@matthieu.moy, @fabien.rico, @vgallet avec le niveau de privilège
"reporter" à votre projet sur la forge**

Dans la feuille TOMUSS du cours, indiquez l'URL de votre projet (qui
doit ressembler à `https://forge.univ-lyon1.fr/<login>/m1if01-2019`). Il
faut impérativement :

* **Que la commande `git clone <url>` fonctionne.**

* **Que les deux membres du binôme entrent exactement la même URL
  dans TOMUSS**

Pensez à remplir dès à présent TOMUSS indiquant votre URL.
Le dépot ne sera relevé qu’après la date de rendu.

Votre dépôt sur la Forge devra contenir :

-   un fichier `README.md` (ou `.txt`) à la racine du projet. Ne
    conservez pas celui du dépôt qui vous est fourni. Le `README.md`
    doit mentionner l'emplacement (répertoire) de la dernière version du projet et
    du rapport.
-   un fichier maven (`pom.xml`) pour le build du projet
-   les sources (fichiers Java)
-   la documentation javadoc de vos classes
-   les fichiers natifs de votre modélisation UML (indiquez quel outil a
    été utilisé)
-   le rapport en PDF (6 pages maximum, format libre).

## Barême indicatif (sur 30, remis sur 20)

-   Réalisation et exécution :
    -   Compilation Maven (1 pts)
    -   Code qui tourne directement sur l’ordinateur de l’évaluateur (1 pts)
    -   Qualité du code (checkstyle, commentaires pertinents, ...) (2 pts)
    -   Structure globale du code, utilisation de Packages (0,5 pts)
    -   Fichier .gitignore correct (aucun fichier "untracked" après un
        "mvn test") (1 pts)
    -   Interface (UI) propre (1 pts)
    -   Stratégies de sélection de CV imposées (2 pts)
    -   Stratégies additionnelles (1 pt)
    -   Prise en compte de l'expérience professionnelle (1 pt)
	-   Modification de l'interface (1 pt)
    -   Tests (3 pts)
    -   Intégration continue opérationnelle (gitlab affiche "Commit:
        passed" sur le dernier commit) (1 pt)
    -   Patterns mis en oeuvre (6 pts)
    -   En cas de non-respect des consignes, malus pouvant aller
        jusqu'à -5 points :
		-   `git clone` qui ne fonctionne pas pour les correcteurs
            (mauvaise URL dans TOMUSS, mauvais droits sur la forge)
        -   Présence de README.md avec le bon contenu
        -   Non-respect des consignes de rendu via TOMUSS
        -   Absence de fichier pom.xml ou .gitlab-ci.yml
-   Rapport et modélisation : 9 points
    -   Partie « design patterns » (6pts)
	-   Partie « éthique » (3pts)
	-   Partie « tests » (1pts)
	-   Qualité globale des explications (3pts)
    -   Les points suivants entrainent des malus (jusqu’à -5 pts)
        -   Contenu et forme (voir ci-dessus)
        -   Orthographe
