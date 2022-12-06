package view;

/*
 * @autor Maylis
*/

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.Screen;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets; 
import javafx.geometry.Rectangle2D;
import javafx.geometry.Pos;

import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.paint.Color; 
import javafx.scene.text.Font;

import javafx.scene.layout.HBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ProgressBar;

import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.collections.*;


import java.io.FileInputStream;
import java.io.InputStream;

//public class Gamescene {
    
//    public Gamescene(Stage stage, int player, double screenWidth, double screenHeight, String board[][]){ // + Player l_player  

    public class Gamescene extends Application {
        private Stage stage;
        private Group root;
        private double screenWidth,
                       screenHeight;
                       
        @Override
        public void start(Stage stage) throws Exception {
		// ------------- Edition du stage ----------------------

        StackPane stackgame = new StackPane();
		Scene game = new Scene(stackgame);

        stage.setTitle("Asteroyds");		// nom de la fenetre
        stage.setScene(game);				// spécifie la scene a utiliser
        stage.setFullScreen(true);			// met en plein écran
        stage.show();
       
        screenWidth = game.getWidth();		// largeur de la scene
        screenHeight = game.getHeight();	// longueur de la scene


        // ---------------Création du menu game --------------------
		
		// Background creation
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        
        stage.setFullScreen(true);
        stage.setX(bounds.getMinX());
        stage.setY(bounds.getMinY());
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());

        //StackPane back = new StackPane();
        stackgame.setStyle("-fx-background-image: url(" +
                        "'https://www.pngall.com/wp-content/uploads/2016/07/Space-PNG-Pic.png'" +"); " +
                        "-fx-background-size: cover;" );
        //stackgame.getChildren().add(stackgame);

        // Sérapartion des espaces
        BorderPane pane_game = new BorderPane();
        Group root_game = new Group();
		VBox container_game = new VBox();
        HBox infos = new HBox();


        stackgame.getChildren().add(pane_game);
        stackgame.getChildren().add(root_game);
        container_game.setPadding(new Insets(20));
        container_game.setSpacing(16);
        container_game.setPrefWidth(screenWidth*0.26);
        container_game.setPrefHeight(screenHeight);

        BackgroundFill background_fill = new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY);
		Background background = new Background(background_fill);
		container_game.setBackground(background);  

        BackgroundFill bgfill_info = new BackgroundFill(Color.DIMGREY, CornerRadii.EMPTY, Insets.EMPTY);
		Background bg_info = new Background(bgfill_info);
		infos.setBackground(bg_info);
        infos.setPrefHeight(screenHeight*0.005);
        infos.setPadding(new Insets(20));
        infos.setSpacing(40);


        pane_game.setCenter(root_game);
        pane_game.setRight(container_game);
        pane_game.setTop(infos);

        /*View viewtest = new View(stage, root_game, screenWidth, screenHeight);
        try{
            viewtest.displayGameBoard(new String[][]{ // board
                {"", "", "", " ", " ", " ", " ", "", "", " ", " ", " ", " ", "", "", ""},
                {"", "", " ", "portal-red-21-1/", " ", "asteroyd-white-5", " ", "audience_pod", " ", " ", "portal-white-23-2", " ", " ", "", "", ""},
                {"", "", " ", " ", " ", " ", " ", " ", " ", "asteroyd-white-6", " ", " ", "asteroyd-white_red-15", " ", "", ""},
                {"", "", " ", "asteroyd-red-2", " ", "asteroyd-blue-11", " ", " ", " ", " ", "asteroyd-red-3", " ", " ", "", "", ""},
                {"", " ", " ", " ", " ", " ", " ", " ", "audience_pod", " ", " ", " ", "asteroyd-white_blue-17", " ", " ", ""},
                {" ", " ", "asteroyd-white_red-13", " ", "asteroyd-white_blue-20", " ", " ", "launchpad", "launchpad/space_ship-Red-2/space_ship-Blue-6", " ", "asteroyd-blue-10", " ", " ", " ", " ", ""},
                {" ", "audience_pod", " ", " ", " ", " ", " ", "launchpad", " ", "launchpad", " ", " ", " ", " ", "audience_pod", " "},
                {" ", " ", "asteroyd-white_red-14", " ", "asteroyd-blue-9", " ", " ", "launchpad/space_ship-Green-4", "launchpad", " ", " ", " ", "asteroyd-white_red-16", " ", " ", ""},
                {"", " ", " ", "asteroyd-white-8", " ", " ", " ", " ", "audience_pod", " ", " ", "asteroyd-red-4", " ", " ", " ", ""},
                {"", "", " ", " ", "asteroyd-red-1", " ", " ", " ", " ", "asteroyd-blue-12", " ", " ", " ", "", "", ""},
                {"", "", " ", "portal-white-24-3", " ", " ", "asteroyd-white_blue-19", " ", " ", " ", " ", " ", "asteroyd-white_blue-18", " ", "", ""},
                {"", "", " ", " ", " ", " ", " ", "audience_pod", " ", "asteroyd-white-7", " ", "portal-red-22-4", " ", "", "", ""},
                {"", "", "", " ", " ", " ", " ", "", "", " ", " ", " ", " ", "", "", ""}
            });
        } catch (Exception e) {
            game.setFill(Color.BLACK);
        } */

        // Label indiquant le nom / numéro du Joueur
		Label name_player = new Label("Nom Joueur"); // TO DO = getname de l'objet i de la liste des joueurs
		name_player.setFont(new Font("cmmi10", 24)); 
		name_player.setTextFill(Color.web("#FFF8DC")); // Couleur du joueur
        container_game.getChildren().add(name_player);
        Label ready_player = new Label("Are you ready ?"); 
		ready_player.setFont(new Font("cmmi10", 24)); 
		ready_player.setTextFill(Color.web("#FFF8DC"));
        container_game.getChildren().add(ready_player);

        // Check box du mode simplifié
        CheckBox c_mode = new CheckBox("Simple Mode");
        Label l1 = new Label();
        EventHandler<ActionEvent> eventSM = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e){
                if (c_mode.isSelected())
                    l1.setText(" selected "); 		// TO DO = mode simplifié
                else
                    l1.setText(" not selected ");
                }
            };
        c_mode.setOnAction(eventSM);
        infos.getChildren().addAll(c_mode, l1);

        // Bouton Exit = retourne au main menu
        Button b_exit = new Button("Back to main menu");
        EventHandler<ActionEvent> eventE = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
				// Changement de scene + réinitialisation des valeurs ?
				//select();
               	//s.setScene(sceneselect);
            } 
        };
		b_exit.setOnAction(eventE);
        infos.getChildren().add(b_exit);

        // Affichage des infos sur les joueurs en haut
        String l_playertest[] = {"J1", "J2", "J3"};
        //while (i <= l_playertest.length ){ // ou i > 6) 
            HBox inf_p1 = new HBox();
            inf_p1.setBackground(new Background(new BackgroundFill(Color.ORANGE, CornerRadii.EMPTY, Insets.EMPTY)));  // l_player[i].getspaceship().getcolor();
            Label relicsp1 = new Label("3");    // l_player[i].getrelics()
            Label lifep1 = new Label("4");    // l_player[i].getspaceship().getStructurePoints();
            inf_p1.getChildren().addAll(relicsp1, lifep1);
            infos.getChildren().add(inf_p1);
            // TO DO = set la taille de la hbox et si possible l'alligner à droite
    

        // Set up de l'interface et des boutons après le ready
			HBox haut_page = new HBox();
			// voir si dimensions conviennent
			haut_page.setPadding(new Insets(15,20, 10,10));
			haut_page.setSpacing(30);

				GridPane liste_ast = new GridPane();
				liste_ast.setHgap(25);
				liste_ast.setVgap(15);

                //liste_ast.add(ShapeConstructor.newImage("red_asteroyd", 10, 10, 0, 0, 0), 0, 0, 1, 1);
                //liste_ast.add(ShapeConstructor.newImage("red_asteroyd", 10, 10, 0, 0, 0), 0, 0, 1, 1);
                //liste_ast.add(ShapeConstructor.newImage("white_asteroyd", 10, 10, 0, 0, 0), 0, 0, 1, 1);
                //liste_ast.add(ShapeConstructor.newImage("blue_asteroyd", 10, 10, 0, 0, 0), 0, 0, 1, 1);


				// Ajout des directions
				Label dir_red = new Label("3");  // fonction random number soit ((int) Math.random()*6) +1 = a calculer avant de rentrer dans la boucle
				dir_red.setFont(new Font("cmmi10", 20)); 
		        dir_red.setTextFill(Color.web("#FFF8DC"));
                Label dir_white = new Label("4"); 
				dir_white.setFont(new Font("cmmi10", 20)); 
		        dir_white.setTextFill(Color.web("#FFF8DC"));
                Label dir_blue = new Label("1");
                dir_blue.setFont(new Font("cmmi10", 20)); 
                dir_blue.setTextFill(Color.web("#FFF8DC"));

				liste_ast.add(dir_red, 1, 0, 1, 1);            
				//liste_ast.add(ShapeConstructor.newImage("red_asteroyd", screenHeight, maxHeight, centerX, centerY)"", 1, 1, 1, 1);
				liste_ast.add(dir_blue, 1, 2, 1, 1);
                liste_ast.add(dir_red, 1, 0, 1, 1);
				liste_ast.add(dir_white, 1, 1, 1, 1);
				liste_ast.add(dir_blue, 1, 2, 1, 1);

                haut_page.getChildren().add(liste_ast);



            Label timer = new Label("50");
            timer.setFont(new Font("cmmi10", 24)); 
	    	timer.setTextFill(Color.web("#FFF8DC"));
            haut_page.getChildren().add(timer);

            // création de la barre de chargement, aussi dépendante du temps
			ProgressBar progressBar = new ProgressBar(0);
            /*Timeline timeline = new timeline(
					new Keyframe(Duration.seconds(1), e -> {
						if(time.getcurrentsec() == 0){
							// on prend toutes les valeurs de move = mises en liste = vers le controller
							//switch de scene vers joueur suivant
							// + réinit le timer ?
						}
						time.oneSecPassed();
						timer.setText(time.getcurrentsec());
						progressBar.setProgress(progressBar.getProgress()+ 100/ 50 );// temps choisi
				}));
            */

            GridPane liste_move = new GridPane();
            liste_move.setHgap(25);
            liste_move.setVgap(15);

            // Combobox de choix des mouvements
            String listmove[] = {"Move Forward", "Move right", "Move left", "Turn around"};
            
            // Utiliser le Control creator
            ComboBox<String> cb1 = new ComboBox<String>(FXCollections.observableArrayList(listmove));
            ComboBox<String> cb2 = new ComboBox<String>(FXCollections.observableArrayList(listmove));
            ComboBox<String> cb3 = new ComboBox<String>(FXCollections.observableArrayList(listmove));
            ComboBox<String> cb4 = new ComboBox<String>(FXCollections.observableArrayList(listmove));
            ComboBox<String> cb5 = new ComboBox<String>(FXCollections.observableArrayList(listmove));
            ComboBox<String> cb6 = new ComboBox<String>(FXCollections.observableArrayList(listmove));
            // leur mettre des events


            liste_move.add(cb1, 1, 0, 1, 1); // liste_move.add(cb_move, 0, 0, 1, 1);
            liste_move.add(cb2, 1, 1, 1, 1); // liste_move.add(cb_move, 0, 1, 1, 1);
            liste_move.add(cb3, 1, 2, 1, 1); // liste_move.add(cb_move, 0, 2, 1, 1);
            liste_move.add(cb4, 1, 3, 1, 1); // liste_move.add(cb_move, 0, 3, 1, 1);
            liste_move.add(cb5, 1, 4, 1, 1); // liste_move.add(cb_move, 0, 4, 1, 1);
            liste_move.add(cb6, 1, 5, 1, 1); // liste_move.add(cb_move, 0, 5, 1, 1);
        
            // Bouton Ready = passe au joueur suivant ou passe au début de la partie
            Button b_ready = new Button("Ready ?");
            EventHandler<ActionEvent> eventR = new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent eventRed) {
                    // On supprime l'interface précédente
                    container_game.getChildren().remove(name_player);
                    container_game.getChildren().remove(b_ready);
                    container_game.getChildren().remove(ready_player);

                    container_game.getChildren().add(haut_page);
                    container_game.getChildren().add(progressBar);
                    container_game.getChildren().add(liste_move);

                    //container_game.getChildren().add(liste_move);

                    // On la remplace par les nouveaux boutons
                    // note au timer à bien lancer a ce moment ci
                    //container_game.getChildren().add(haut_page);
                    //container_game.getChildren().add(progressBar);
                    //container_game.getChildren().add(liste_move);


                    // incrémente de 1 la boucle
                    // créer un objet player / vaisseau avec les valeurs données
                    //start(game);
                }
            };
        b_ready.setOnAction(eventR);
        container_game.getChildren().add(b_ready);

        



    }    
}

            /* 
			// création de la barre de chargement, aussi dépendante du temps
			ProgressBar progressBar = new ProgressBar(0);
			progressBar.setAlignment(Pos.CENTER);

				Timeline timeline = new timeline(
					new Keyframe(Duration.seconds(1), e -> {
						if(time.getcurrentsec() == 0){
							// on prend toutes les valeurs de move = mises en liste = vers le controller
							//switch de scene vers joueur suivant
							// + réinit le timer ?
						}
						time.oneSecPassed();
						timer.setText(time.getcurrentsec());
						progressBar.setProgress(progressBar.getProgress()+ 100/ 50 );// temps choisi
				}));
            
    }
}
*/