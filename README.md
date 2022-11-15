# POO22_384J_A



## Name : Asteroyds

## Cahier des charges

Ce projet, reprenant un jeu de société, consiste à gérer une course spatiale où les joueurs programment les trajectoires de leur vaisseau en essayant d’anticiper les mouvements aléatoires des astéroïdes.
Les dés sont lancés : rouge/bleu/blanc. Ces dés indiquent la direction que prendront les astéroïdes rouges, blancs et bleus quand ils se déplaceront. Puis on lance le chronomètre de 20 secondes.
Les joueurs peuvent alors programmer chacun leur tour jusqu’à 6 mouvements parmi les 4 possibilités suivantes : tout droit, demi-tour, avancer à gauche, avancer à droite.
Dès que le temps est fini, les joueurs arrêtent de programmer leurs mouvements.
On déplace les astéroïdes selon les résultats indiqués pas les dés.
Dans l’orde du tour on déplace les vaisseaux des joueurs selon la liste de leurs mouvements.
Si, au cours de son déplacement, un vaisseau entre en collision avec un objet ou avec le bord du plateau, sa progression est stoppée, et le joueur encaisse des dommages. S’il perd tous ses points de structure, le vaisseau est détruit.
Dès qu’un joueur passe les quatre portes, il a gagné la partie.

## Description générale

Un *jeu* possède une liste de 6 joueurs, une liste d'astéroïdes, un plateau de jeu de 16x13 cases, une durée et liste de valeurs que peuvent prendre les 3 dès. [Un joueur] est représenté par un nom, un vaisseau de sa couleur (qu’il aura choisi préalablement) , une liste de reliques et une liste de mouvements. Un vaisseau est un
2
VisualObject qui possède une couleur et des points de structure qui diminuent lorsqu’il rentre en collision avec un objet. Un VisualObject (abstact) a une position ,une image et une orientation.
Une Position possède deux coordonnées x et y.
Une case (abstract ) est un VisualObject de forme hexagonale, qui peut contenir un ou plusieurs vaisseaux. Elle possède une liste de vaisseaux et un booléen qui indique si la case peut contenir des vaisseaux.
Une Capsule de spectateur est un type de case qui ne bouge pas et qui ne peut pas accueillir de vaisseau.
Une Plateforme est un type de case qui ne bouge pas et d’où part un vaisseau.
Une Case vide est le seul type de case où peuvent se déplacer les autres types de case. Ce type est ainsi remplacé par le nouveau et une case vide est placée là ou était précédemment l’objet. Il peut accueillir des vaisseau.
Un Astéroïde (abstract) est un type de case avec une couleur. Les astéroïdes rouges, astéroïdes bleus et astéroïdes blancs sont des astéroïdes avec une couleur fixe.
Un portail blanc est un astéroïde blanc avec des reliques, et où un vaisseau peut se déplacer.
Un portail rouge est un astéroïde rouge avec des reliques, et où un vaisseau peut se déplacer.
Un WhiteBleuAsteroyd est un astéroïde bleu qui a les déplacements d’un astéroïde bleu et blanc cumulés.
Un WhiteRedAsteroyd est un astéroïde rouge qui a les déplacements d’un astéroïde bleu et blanc cumulés.


## Usage
Un jeu à jouer entre amis.

## Support Utiliser
- Java
- Visual Studio Code
- JavaFx


## Authors 
-ABBACI Juba
-ACKERMANN Matéo
-BOUDJEDIR Amina
-MOGUE GEGOUE Bebeine

## Project status
L2 programation oriente objet