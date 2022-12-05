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

import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.paint.Color; 
import javafx.scene.text.Font;
import javafx.scene.effect.Glow; 

import javafx.scene.layout.HBox;
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


public class Resultsscene {
    public Resultsscene(Stage stage, double screenWidth, double screenHeight, String board[][]){  //+ Player l_player[]
   
		// ------------- Edition du stage ----------------------

        StackPane stackresults = new StackPane();
		Scene results = new Scene(stackresults);

        stage.setTitle("Asteroyds");		// nom de la fenetre
        stage.setScene(results);				// spécifie la scene a utiliser
        stage.setFullScreen(true);			// met en plein écran
        stage.show();
       
        screenWidth = results.getWidth();		// largeur de la scene
        screenHeight = results.getHeight();	// longueur de la scene


        // ---------------Création du menu results --------------------
		
		// Background creation
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        
        stage.setFullScreen(true);
        stage.setX(bounds.getMinX());
        stage.setY(bounds.getMinY());
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());

        //StackPane back = new StackPane();
        //stackresults.setStyle("-fx-background-image: url(" +
        //                "'https://www.pngall.com/wp-content/uploads/2016/07/Space-PNG-Pic.png'" +"); " +
        //                "-fx-background-size: cover;" );
        //stackgame.getChildren().add(stackgame);

        // Sérapartion des espaces
        BorderPane pane_results = new BorderPane();
		VBox container_results = new VBox();
        Group root_results = new Group();
        HBox infos = new HBox();
 
        stackresults.getChildren().add(pane_results);
        container_results.setPadding(new Insets(20));
        container_results.setSpacing(17);
        container_results.setPrefWidth(screenWidth*0.26);
        container_results.setPrefHeight(screenHeight);

        BackgroundFill background_fill = new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY);
		Background background = new Background(background_fill);
		container_results.setBackground(background);  

        BackgroundFill bgfill_info = new BackgroundFill(Color.DIMGREY, CornerRadii.EMPTY, Insets.EMPTY);
		Background bg_info = new Background(bgfill_info);
		infos.setBackground(bg_info);
        infos.setPrefHeight(screenHeight*0.005);
        infos.setPadding(new Insets(20));
        infos.setSpacing(40);

        pane_results.setCenter(root_results);
        pane_results.setRight(container_results);
        pane_results.setTop(infos);

        View viewtest = new View(stage, root_results, screenWidth, screenHeight);
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
            results.setFill(Color.BLACK);
        } 

        // Label indiquant le nom de la scene (soit results)
		Label title = new Label("Results"); // TO DO = getname de l'objet i de la liste des joueurs
		title.setFont(new Font("cmmi10", 40)); 
		title.setTextFill(Color.web("#FFF8DC"));
        title.setLayoutX(screenWidth*0.4);
		title.setLayoutY(screenWidth*0.1);
		Glow glow = new Glow();       
        glow.setLevel(0.9); 
        title.setEffect(glow);  
        

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

    

        // Set up de l'interface des resultats
        // TO DO
        String l_playertest[] = {"J1", "J2", "J3"};
        double length_lp = 3;
        //String l_relics[] = {"3", "0", "2"};
        //String l_life[] = {"5", "3", "6"};
        //Label p1 = Control_creator.Label(l_player[1], "#FFF8DC", "cmmi10", 20.0);

        // HELP, c'est horrible = pas affichage selon nb joueurs ni entrée dans le control creator .-.
        //if (length_lp >= 2) {
            Label p1 = new Label(l_playertest[1]); 
            p1.setFont(new Font("cmmi10", 20)); 
            p1.setTextFill(Color.web("#FFF8DC"));
            Label p2 = new Label(l_playertest[2]); 
            p2.setFont(new Font("cmmi10", 20)); 
            p2.setTextFill(Color.web("#FFF8DC"));
            container_results.getChildren().addAll(p1, p2);
            /* 
            if (length_lp >= 3) {
                Label p3 = new Label(l_playertest[3]); 
                p3.setFont(new Font("cmmi10", 20)); 
                p3.setTextFill(Color.web("#FFF8DC"));
                container_results.getChildren().add(p3);
/* 
                if (l_playertest.length >= 4) {
                    Label p4 = new Label(l_playertest[4]); 
                    p4.setFont(new Font("cmmi10", 20)); 
                    p4.setTextFill(Color.web("#FFF8DC"));
                    container_results.getChildren().add(p4);

                     if (l_playertest.length >= 5) {
                        Label p5 = new Label(l_playertest[5]); 
                        p5.setFont(new Font("cmmi10", 20)); 
                        p5.setTextFill(Color.web("#FFF8DC"));
                        container_results.getChildren().add(p5);

                        if (l_playertest.length >= 6) {
                            Label p6 = new Label(l_playertest[6]); 
                            p6.setFont(new Font("cmmi10", 20)); 
                            p6.setTextFill(Color.web("#FFF8DC"));
                            container_results.getChildren().add(p6);
                        }
                    }
                }*/
            //}
        
        //}
        
			//for (int i = 0 ; i < l_player.length ; i ++ ) {
            //    container_results.getChildren().add(Control_creator.Label(l_player[1], "#FFF8DC", "cmmi10", 20.0));
            //    container_results.getChildren().add(Control_creator.Label("Relics: " + l_relics[1], "#FFF8DC", "cmmi10", 15.0));
            //    container_results.getChildren().add(Control_creator.Label("Etat du vaisseau : "  + l_life[1] + "/6 ", "#FFF8DC", "cmmi10", 15.0));
            
            
        // Bouton Next = passe au joueur suivant ou passe au début de la partie
        Button b_next = new Button("Next Game ?");
        EventHandler<ActionEvent> eventN = new EventHandler<ActionEvent>() {
            @Override
                public void handle(ActionEvent eventRed) {
                
            }
        };
        b_next.setOnAction(eventN);
        b_next.setLayoutX(screenHeight*0.5);
        b_next.setLayoutY(screenWidth*0.5); 
        container_results.getChildren().add(b_next);
        infos.getChildren().add(title);


    }    
}
