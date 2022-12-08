# POO22_384J_A



## ASTEROYDS

## Description

Ce projet, reprenant le jeu de société éponyme, consiste à gérer une course spatiale où les joueurs programment les trajectoires de leur vaisseau en essayant d’anticiper les mouvements aléatoires des astéroïdes.  
Les dés sont lancés : rouge/bleu/blanc. Ces dés indiquent la direction que prendront les astéroïdes de la (ou des) couleur correspondante quand ils se déplaceront. Puis on lance le minuteur dont la durée dépend de la difficultée choisie.
Les joueurs peuvent alors programmer chacun leur tour jusqu’à 6 mouvements parmi les 4 possibilités suivantes : tout droit, demi-tour, avancer à gauche, avancer à droite.  
Dès que le temps est fini, les joueurs arrêtent de programmer leurs mouvements.
On déplace les astéroïdes selon les résultats indiqués pas les dés en suivant leur ordre de priorité. Dès qu'un astéroïde rentre en collision avec un quelconque objet ou le bord du plateau, celui-ci stop immédiatement son déplacement.

Les astéroïdes ont des déplacements propres à leur couleur :
- Les blancs se déplacent simplement d'une case dans la direction blanche ;
- Les rouge sont plus rapides, ils se déplacent de deux cases dans la direction rouge ;
- Les bleus sont plus lourds, ils se déplacent d'une case dans la direction bleu mais peuvent pousser un astéroîde qui gènerait ce déplacement si ce dernier a une case libre derrière lui ;
- Les blancs et rouges se déplacent d'abord comme un astéroïde rouge puis comme un astéroïde blanc, même si leur déplacement en rouge a été stoppé ;
- Les blancs et bleus se déplacent d'abord comme un astéroïde blanc puis comme un astéroïde bleu, même si leur déplacement en blanc a été stoppé.
En outre, si un astéroïde rentre en collision avec un vaisseau, le vaisseau perd immédiatement 1 point de structure (attention à ne pas finir son déplacement au milieu d'un amas d'astéroïdes...).

On déplace ensuite les vaisseaux des joueurs selon la liste de leurs mouvements.
Si, au cours de son déplacement, un vaisseau entre en collision avec un quelconque objet (autre qu'un autre vaisseau, un portail ou une plateforme de lancement), ou avec le bord du plateau, sa progression est stoppée, et il perd immédiatement 2 points de structure.
Si un vaisseau perd tous ses points de structure, il est détruit et le joueur qui le contrôle perd la partie (même s'il a réussi à atteindre les 4 portails).  
Dès qu’un joueur passe les 4 portails avec un vaisseau en un seul morceau, il gagne la partie.
Si plusieurs joueurs atteignent les 4 portails lors du même tour de jeu, c'est celui dont le vaisseau a le plus de points de stucture qui remporte la partie. En cas d'égalité, les joueurs sont considérés comme ex aequo.

Pour plus de précisions sur les règles vous pouvez suivre le tutoriel suivant : https://www.youtube.com/watch?v=3JEBWf5B7CA.


## Compilation et exécution du projet 
Pour pouvoir lancer le projet, il est nécessaire de suivre les étapes suivantes :

- **Etape 0** : Cloner le projet sur sa machine... évidemment !
- **Etape 1** : Télécharger le module javafx correspondant à la version de sa machine en choisissant le type JDK à l'adresse suivante : https://gluonhq.com/products/javafx/.
- **Etape 2** : Copier le contenu du répertoire *lib* du dossier téléchargé dans le répertoire *lib* du dossier du projet.
- **Etape 2-bis** : Pour les utilisateurs de Windows, il faut aussi copier le répertoire *bin* dans le dossier du projet.
- **Etape 4** : Ouvrir un terminal et se rendre dans le dossier du projet.
- **Etape 5** : Compiler les fichiers à l'aide de la commande suivante :

        javac -d bin --module-path "./lib" --add-modules javafx.base,javafx.graphics src/controller/*.java

- **Etape 6** : Enfin, il ne reste plus qu'à exécuter la commande suivante pour lancer le programme :

        java -cp bin --module-path "./lib" --add-modules javafx.base,javafx.graphics,javafx.controls controller/Main


**/!\\** Il est possible que lors de l'exécution le programme plante car la taille maximale des textures prises en charge par l'accélération matérielle de JavaFX, qui est définie dans le pilote graphique, est dépassée. **/!\\**   
Dans ce cas pas d'inquiétude, il suffit de lancer la commande suivante avant dexécuter le programme (non testée sous Linux) :

        set JAVA_TOOL_OPTIONS=-Dprism.maxvram=8G

## Personnaliser son plateau de jeu
Il est possible de créer et ajouter son propre plateau de jeu mais en respectant certaines contraintes bien précises :

- Il est vivement conseillé de ne **jamais** modifier ni supprimer le fichier *Classic.txt* afin de **toujours** avoir un plateau de jeu valide qui sert aussi d'exemple de la syntaxe à suivre pour en créer d'autres ;
- Le plateau de jeu doit être scripter dans un fichier au format *.txt* et être placé dans le répertoire *res/gameboards* du dossier du projet ;
- La première ligne du fichier doit comporter le nombre de colonnes du plateau suivi de la lettre *x* suivie du nombre de lignes ;
- La seconde ligne doit comporter le nombre total d'astéroïdes (portails compris) que votre plateau contiendra ;
- La troisième ligne est ignorée pour plus de lisibilité vous pouvez donc y noter ce qui vous passe par la tête... ou rien du tout ;
- Ensuite commence la création du plateau :
        - Chaque ligne doit correspond à un type d'objet à savoir 

## Supports utilisés
une liste de technologies utiliser dans le projet:
- [java](https://www.java.com/fr/) : java 17 2021-09-14 LTS
- [javaFx](https://openjfx.io) : javafx-sdk-11.0.2
- [VisualStudioCode](https://code.visualstudio.com) : Version : 1.73.1 (Universal)


## Auteurs 
- ABBACI Juba
- ACKERMANN Matéo
- BOUDJEDIR Amina
- BOUILLON Maylis
- MOGUE GEGOUE Bebeine