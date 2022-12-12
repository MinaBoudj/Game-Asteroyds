# POO22_384J_A



## ASTEROYDS

## Description

Ce projet, reprenant le jeu de société éponyme, consiste à gérer une course spatiale où les joueurs programment les trajectoires de leur vaisseau en essayant d’anticiper les mouvements aléatoires des astéroïdes.

Les dés sont lancés : rouge/bleu/blanc. Ces dés indiquent la direction que prendront les astéroïdes de la (ou des) couleur(s) correspondante(s) quand ils se déplaceront. Puis on lance le minuteur, dont la durée dépend de la difficultée choisie.
Les joueurs peuvent alors programmer chacun leur tour jusqu’à 6 mouvements parmi les 4 possibilités suivantes : tout droit, demi-tour, avancer à gauche, avancer à droite.

Dès que le temps est fini, les joueurs arrêtent de programmer leurs mouvements.
On déplace les astéroïdes selon les résultats indiqués pas les dés en suivant leur ordre de priorité. Dès qu'un astéroïde rentre en collision avec un quelconque objet ou le bord du plateau, celui-ci stop immédiatement son déplacement.  
Les astéroïdes ont des déplacements propres à leur couleur :
- Les blancs se déplacent simplement d'une case dans la direction blanche ;
- Les rouge sont plus rapides, ils se déplacent de deux cases dans la direction rouge ;
- Les bleus sont plus lourds, ils se déplacent d'une case dans la direction bleue, mais peuvent pousser un astéroïde qui gènerait ce déplacement, mais seulement si ce dernier a une case libre derrière lui ;
- Les blancs et rouges se déplacent d'abord comme un astéroïde rouge puis comme un astéroïde blanc, même si leur déplacement en rouge a été stoppé ;
- Les blancs et bleus se déplacent d'abord comme un astéroïde blanc puis comme un astéroïde bleu, même si leur déplacement en blanc a été stoppé.
En outre, si un astéroïde rentre en collision avec un vaisseau, le vaisseau perd immédiatement 1 point de structure (attention à ne pas finir son déplacement au milieu d'un amas d'astéroïdes...).

On déplace ensuite les vaisseaux des joueurs selon la liste de leurs mouvements.
Si, au cours de son déplacement, un vaisseau entre en collision avec une capsule de spectateurs, un astéroide (portails exclus) ou le bord du plateau, sa progression est stoppée, et il perd immédiatement 2 points de structure.
Si un vaisseau perd tous ses points de structure, il est détruit et le joueur qui le contrôle perd la partie (même s'il a réussi à atteindre les 4 portails).
Un vaisseau peut se rendre sur une case où se trouve déjà un autre vaisseau, peu importe l'orientation. Il se peut donc que les images des vaisseaux se retrouvent superposées, mais rassurez-vous votre vaisseau n'a pas disparu ! (à moins que ses points de structure n'aient atteind 0)   
Dès qu’un joueur passe les 4 portails avec un vaisseau en un seul morceau, il gagne la partie.
Si plusieurs joueurs atteignent leur quatrième portail lors du même tour de jeu, c'est celui dont le vaisseau a le plus de points de stucture qui remporte la partie. En cas d'égalité, les joueurs sont considérés comme ex aequo.

Pour plus de précisions sur les règles, vous pouvez suivre le tutoriel suivant : https://www.youtube.com/watch?v=3JEBWf5B7CA.


## Compilation et exécution du projet 
Pour pouvoir lancer le projet, il est nécessaire de suivre les étapes suivantes :

- **Etape 0** : Cloner le projet sur sa machine... évidemment !
- **Etape 1** : Télécharger le module javafx correspondant à la version de sa machine, en choisissant le type JDK, à l'adresse suivante : https://gluonhq.com/products/javafx/.
- **Etape 2** : Copier le contenu du répertoire *lib* du dossier téléchargé dans le répertoire *lib* du dossier du projet.
- **Etape 2-bis** : Pour les utilisateurs de Windows, il faut aussi copier le répertoire *bin* dans le dossier du projet.
- **Etape 3** : Ouvrir un terminal et se rendre dans le dossier du projet.
- **Etape 4** : Compiler les fichiers à l'aide des commandes suivantes (à exécuter dans l'ordre) :

        javac -d bin --module-path "./lib" --add-modules javafx.base,javafx.graphics,javafx.controls src/Modele/*.java
        javac -d bin --module-path "./lib" --add-modules javafx.base,javafx.graphics,javafx.controls src/view/*.java
        javac -d bin -cp bin --module-path "./lib" --add-modules javafx.base,javafx.graphics,javafx.controls src/controller/*.java

- **Etape 5** : Enfin, il ne reste plus qu'à exécuter la commande suivante pour lancer le programme :

        java -cp bin --module-path "./lib" --add-modules javafx.base,javafx.graphics,javafx.controls controller/Main


**/!\\** Il est possible que lors de l'exécution le programme plante, car la taille maximale des textures prises en charge par l'accélération matérielle de JavaFX, qui est définie dans le pilote graphique, serait dépassée. **/!\\**   
Dans ce cas, pas d'inquiétude ! Il suffit de lancer la commande suivante avant d'exécuter le programme (non testée sous Linux) :

        set JAVA_TOOL_OPTIONS=-Dprism.maxvram=8G

## Personnaliser son plateau de jeu
Il est possible de créer et d'ajouter son propre plateau de jeu, mais en respectant certaines contraintes bien précises :

- Il est vivement conseillé de ne **jamais** modifier ni supprimer le fichier *Classic.txt*, afin de **toujours** avoir un plateau de jeu valide, qui sert aussi d'exemple de syntaxe à suivre pour en créer de nouveaux.
- Le plateau de jeu doit être scripté dans un fichier au format *.txt* et être placé dans le répertoire *res/gameboards* du dossier du projet.
- La première ligne du fichier doit comporter le nombre de colonnes du plateau, suivi de la lettre *x*, puis du nombre de lignes.
- La seconde ligne doit comporter le nombre total d'astéroïdes (portails compris) que votre plateau contiendra.
- La troisième ligne doit comprter le nombre total de plateformes de lancement que votre plateau contiendra. Il doit y en avoir au moins 1.
- La quatrième ligne est ignorée pour plus de lisibilité. Vous pouvez donc y noter ce qui vous passe par la tête... ou rien du tout.
- Ensuite commence la création du plateau, où chaque ligne correspond à une suite d'éléments consécutifs de même type. Chaque paramètre doit être séparé par un tiret. Les paramètres sont obligatoires (sauf précision) et doivent suivre l'ordre suivant :
  1. Un nombre indiquant la quantité d'éléments consécutifs de même type.
  2. Le type de l'objet parmi les suivants :
     - *asteroyd* pour un asteroïde,
     - *portal* pour un portail,
     - *empty* pour une case vide,
     - *launchpad* pour une plateforme de lancement,
     - *audience_pod* pour une cpasule de spectateurs,
     - *null* pour une case interdite.
  3. La couleur de l'objet, uniquement pour les types *portal* et *asteroyd* :
     - Pour le type *portal* :
       - *red* pour rouge,
       - *white* pour blanc.
     - Pour le type *asteroyd* :
       - *red* pour rouge,
       - *white* pour blanc,
       - *blue* pour bleu,
       - *white_red* pour blanc et rouge,
       - *white_blue* pour blanc et bleu.
  4. La valeur de la relique associée, uniquement pour le type *portal*, comprise dans l'intervalle ⟦1;4⟧.
  Par exemple la ligne *4-asteroyd-red* créera 4 asteroïdes rouges consécutifs sur la ligne du plateau.
- Le caractère **-**, seul sur une ligne du fichier, indique qu'on commence une nouvelle ligne du plateau.
- Entre 2 **-** seuls, la somme des nombres débutants les lignes (voir (1)) doit être égale au nombre de colonnes indiqué au départ.
- Le nombre de groupes d'objets (un groupe comprenant tout ce qui est situé entre 2 **-**) doit être égal au nombre de lignes indiqué au départ.
- Sans modification du code existant, aucun écart aux règles précédentes ne permettra le chargement du plateau.
- Pour finir, il est préférable de s'appuyer sur le fichier *Classic.txt* pour créer un nouveau plateau.
        

## Supports utilisés
La liste des supports utilisés dans le projet :
- [java](https://www.java.com/fr/) : java 17 2021-09-14 LTS
- [javaFx](https://openjfx.io) : javafx-sdk-11.0.2
- [VisualStudioCode](https://code.visualstudio.com) : Version : 1.73.1 (Universal)


## Auteurs 
- ABBACI Juba
- ACKERMANN Matéo
- BOUDJEDIR Amina
- BOUILLON Maylis
- MOGUE GEGOUE Bebeine