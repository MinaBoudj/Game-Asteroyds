package view;

/*
 * @autor Maylis
*/
<<<<<<< HEAD
 
import javafx.application.Application;
=======

//import javafx.application.Application;
>>>>>>> fe72e24d85f6973482ad747a4eed0326cf8e60af
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets; 
import javafx.scene.Group;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color; 
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.CornerRadii;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.control.ComboBox;
import javafx.scene.effect.Glow; 
import javafx.stage.Screen;
import javafx.geometry.Rectangle2D;



<<<<<<< HEAD
public class Mainscene{
=======
public class Mainscene {

>>>>>>> fe72e24d85f6973482ad747a4eed0326cf8e60af
    public Mainscene(Stage stage, double screenWidth, double screenHeight){ 

		// ------------- Edition du stage ----------------------
        StackPane stackmenu = new StackPane();
		Scene menu = new Scene(stackmenu);

<<<<<<< HEAD
        stage.setTitle("Asteroyds");		// nom de la fenetre
        stage.setScene(menu);				// spécifie la scene a utiliser
        stage.setFullScreen(true);			// met en plein écran
        stage.show();						// montre la scene
=======
        stage.setTitle("Asteroyds");		
        stage.setScene(menu);				
        stage.setFullScreen(true);			
        stage.show();						
>>>>>>> fe72e24d85f6973482ad747a4eed0326cf8e60af
        
        screenWidth = menu.getWidth();		// largeur de la scene
        screenHeight = menu.getHeight();	// longueur de la scene


		// ---------------Création du menu principal---------------------
		
<<<<<<< HEAD
		// Background creation
=======
		// Background build
>>>>>>> fe72e24d85f6973482ad747a4eed0326cf8e60af
		try {
			Screen screen = Screen.getPrimary();
			Rectangle2D bounds = screen.getVisualBounds();
			
<<<<<<< HEAD
			stage.setFullScreen(true);
=======
>>>>>>> fe72e24d85f6973482ad747a4eed0326cf8e60af
			stage.setX(bounds.getMinX());
			stage.setY(bounds.getMinY());
			stage.setWidth(bounds.getWidth());
			stage.setHeight(bounds.getHeight());
	
			StackPane back = new StackPane();
<<<<<<< HEAD
			back.setStyle("-fx-background-image: url(" +
=======
			stack.setStyle("-fx-background-image: url(" +
>>>>>>> fe72e24d85f6973482ad747a4eed0326cf8e60af
						  "'https://cutewallpaper.org/28/cool-black-space-gif-wallpaper/resultado-de-imagem-para-space-gif-outer-space-wallpaper-star-wallpaper-wallpaper-space.gif'" +"); " +
						  "-fx-background-size: cover;"
			);
			stackmenu.getChildren().add(back);

<<<<<<< HEAD
            //stackmenu.getChildren().add(ShapeConstructor.newImage("background", screenWidth,screenHeight, screenWidth/2,screenHeight/2, 1));
            //ImageView backmenu = ShapeConstructor.newImage("Back_menu", 1,1, 0,0);
			//backmenu.setFitHeight(screenWidth*1.1);	
			//backmenu.setFitWidth(screenWidth*1.1);
=======
>>>>>>> fe72e24d85f6973482ad747a4eed0326cf8e60af
        } catch (Exception e) {
			// si erreur, couleur par défaut en noir
            menu.setFill(Color.BLACK);	
        }
<<<<<<< HEAD
		
        
		AnchorPane pane_menu = new AnchorPane();
		stackmenu.getChildren().add(pane_menu);
		
		// Sérapartion des espaces
		// Menu
		Group test = new Group();
=======
	
		// Sérapartion des espaces
		// Menu
		AnchorPane pane_menu = new AnchorPane();
		stackmenu.getChildren().add(pane_menu);
		Group container = new Group();

>>>>>>> fe72e24d85f6973482ad747a4eed0326cf8e60af
		VBox container_menu = new VBox();
        container_menu.setPadding(new Insets(20));
        container_menu.setSpacing(16);
        container_menu.setPrefWidth(screenWidth*0.3);
        container_menu.setPrefHeight(screenHeight);

        
<<<<<<< HEAD
			// set background of container
			// note si marche pas, travailler avec les proportions screenWidth*1.1
			BackgroundFill background_fill = new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY);
			Background background = new Background(background_fill);
			container_menu.setBackground(background);
=======
		// set background of container
		BackgroundFill background_fill = new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY);
		Background background = new Background(background_fill);
		container_menu.setBackground(background);
>>>>>>> fe72e24d85f6973482ad747a4eed0326cf8e60af
	
        // Rectangle vide
        Rectangle rect = new Rectangle(0,0, screenWidth*0.7,screenHeight);
		rect.setFill(Color.TRANSPARENT);    
		
        AnchorPane.setLeftAnchor(rect, 0.0);
        AnchorPane.setRightAnchor(container_menu,0.0);
<<<<<<< HEAD
		test.getChildren().add(container_menu);
        pane_menu.getChildren().addAll(rect, test);
=======
		container.getChildren().add(container_menu);
        pane_menu.getChildren().addAll(rect, container);
>>>>>>> fe72e24d85f6973482ad747a4eed0326cf8e60af

		// Title creation
		Label title = new Label("Asteroyds");
		title.setFont(new Font("DIN", 60));  //cmmi10
		title.setTextFill(Color.web("#FFE4B5"));
        title.setLayoutX(screenWidth*0.4);
		title.setLayoutY(screenWidth*0.1);
		Glow glow = new Glow();       
        glow.setLevel(0.9); 
        title.setEffect(glow);  

<<<<<<< HEAD

=======
		// Creation des titres du menu
>>>>>>> fe72e24d85f6973482ad747a4eed0326cf8e60af
        Label players = new Label("Select players number : ");
        players.setTextFill(Color.web("#FFF8DC"));
        Label difficulty = new Label("Select a difficulty : ");
        difficulty.setTextFill(Color.web("#FFF8DC"));
        Label rules = new Label("Rules here : ");
        rules.setTextFill(Color.web("#FFF8DC"));
		Label parameters = new Label("Parameters");
        parameters.setTextFill(Color.web("#FFF8DC"));
		parameters.setFont(new Font("cmmi10", 30)); 


		// Choix du nombre de joueurs
		ComboBox<String> cb_nbplayer = new ComboBox<String>();
			cb_nbplayer.getItems().add("2 players");
			cb_nbplayer.getItems().add("3 players");
			cb_nbplayer.getItems().add("4 players");
			cb_nbplayer.getItems().add("5 players");
			cb_nbplayer.getItems().add("6 players");

		// Choix de la difficulté
		ComboBox<String> cb_difficulty = new ComboBox<String>();
<<<<<<< HEAD
			cb_difficulty.getItems().add("Easy - 50s");
			cb_difficulty.getItems().add("Normal - 40s");
			cb_difficulty.getItems().add("Hard - 30s");
			cb_difficulty.getItems().add("Very Hard - 20s");
=======
			cb_difficulty.getItems().add("Amateur driver");
			cb_difficulty.getItems().add("Co-pilot");
			cb_difficulty.getItems().add("Captain");
			cb_difficulty.getItems().add("Flight champion");
>>>>>>> fe72e24d85f6973482ad747a4eed0326cf8e60af

		// Rules buttom
        String URL = "http://www.cuk.ch/articles/4626/";
        Button b_rules = new Button("Rules");
        //b_rules.setOnAction(e -> getHostServices().showDocument(URL)); // TO DO = faire une méthode reprenant getHostServices dans application
		

		// Buttom Start
		Button b_start= new Button("Start");  
        b_start.setLayoutX(screenHeight*0.76);
        b_start.setLayoutY(screenWidth*0.5); 
		
        /*EventHandler<ActionEvent> eventS = new EventHandler<ActionEvent>() {
           
            @Override
            public void handle(ActionEvent event) {
				// TO DO = get les values de tous les boutons, et créer les objets adaptés
				// String nb_player = cb_nbplayer.Text;
				// A voir avec le controller
				
				// Changement de scene
				select();
               	s.setScene(sceneselect);
            } 
		};
		b_start.setOnAction(eventS);
*/

<<<<<<< HEAD
		test.getChildren().add(title);
=======
		container.getChildren().add(title);
>>>>>>> fe72e24d85f6973482ad747a4eed0326cf8e60af
		container_menu.getChildren().add(parameters);
		container_menu.getChildren().add(players);
		container_menu.getChildren().add(cb_nbplayer);
		container_menu.getChildren().add(difficulty);
		container_menu.getChildren().add(cb_difficulty);
		container_menu.getChildren().add(rules);
		container_menu.getChildren().add(b_rules);
<<<<<<< HEAD
		test.getChildren().add(b_start);
	
		
	}
}
=======
		container.getChildren().add(b_start);
	
		
	}
}
>>>>>>> fe72e24d85f6973482ad747a4eed0326cf8e60af
