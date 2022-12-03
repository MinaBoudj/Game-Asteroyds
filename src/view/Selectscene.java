package view;

/*
 * @autor Maylis
*/

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets; 
import javafx.scene.Group;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color; 
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.CornerRadii;
import javafx.scene.text.Font;
import javafx.scene.control.ComboBox;
import javafx.scene.effect.Glow; 
import javafx.stage.Screen;
import javafx.geometry.Rectangle2D;
import javafx.scene.layout.HBox;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.application.Platform;

public class Selectscene {
    
    public Selectscene(Stage stage, int player, double screenWidth, double screenHeight){  // + ObservableList l_color 

		// ------------- Edition du stage ----------------------

        StackPane stackselect = new StackPane();
		Scene select = new Scene(stackselect);

        stage.setTitle("Asteroyds");		// nom de la fenetre
        stage.setScene(select);				// spécifie la scene a utiliser
        stage.setFullScreen(true);			// met en plein écran
        stage.show();
       
        screenWidth = select.getWidth();		// largeur de la scene
        screenHeight = select.getHeight();	// longueur de la scene


        // ---------------Création du menu selection --------------------
		
		// Background creation
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        
        stage.setFullScreen(true);
        stage.setX(bounds.getMinX());
        stage.setY(bounds.getMinY());
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());

        StackPane back = new StackPane();
        back.setStyle("-fx-background-image: url(" +
                        "'https://cutewallpaper.org/28/cool-black-space-gif-wallpaper/resultado-de-imagem-para-space-gif-outer-space-wallpaper-star-wallpaper-wallpaper-space.gif'" +"); " +
                        "-fx-background-size: cover;"
        );
        stackselect.getChildren().add(back);

        // Sérapartion des espaces
        BorderPane pane_select = new BorderPane();
		Group root_select = new Group();
		VBox container_select = new VBox();

        stackselect.getChildren().add(pane_select);
        container_select.setPadding(new Insets(20));
        container_select.setSpacing(16);
        container_select.setPrefWidth(screenWidth*0.3);
        container_select.setPrefHeight(screenHeight);

        BackgroundFill background_fill = new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY);
		Background background = new Background(background_fill);
		container_select.setBackground(background);

        Rectangle rect = new Rectangle(screenWidth*0*6,0, screenWidth,screenHeight);
		rect.setFill(Color.BLUEVIOLET);    

        pane_select.setCenter(root_select);
        pane_select.setLeft(container_select);

        //AnchorPane.setLeftAnchor(rect, 0.0);
        //AnchorPane.setRightAnchor(container_select,0.0);
		//root_select.getChildren().add(container_select);
        //pane_select.getChildren().addAll(rect, root_select);


		Label title = new Label("Selection Player " + player); 
		title.setFont(new Font("DIN", 50));  
		title.setTextFill(Color.web("#FFE4B5"));
        title.setLayoutX(screenWidth*0.3);
		title.setLayoutY(screenHeight*0.1);
		Glow glow = new Glow();       
        glow.setLevel(0.9); 
        title.setEffect(glow);  

        Label text_name = new Label("Select your name : ");
        text_name.setTextFill(Color.web("#FFF8DC"));
        Label text_color = new Label("Select a color : ");
        text_color.setTextFill(Color.web("#FFF8DC"));
        Label text_launch = new Label("Select a launchpad: ");
        text_launch.setTextFill(Color.web("#FFF8DC"));
		
        // Creation of Text Field (get the name of the player) 
			Label nameTF = new Label("Name: ");
            nameTF.setTextFill(Color.web("#FFF8DC"));
			TextField TF = new TextField ("Joueur " + player); 
			HBox name = new HBox();
			name.getChildren().addAll(nameTF, TF);
			name.setSpacing(10);

			EventHandler<ActionEvent> name_event = new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e){
					//l.setText(b.getText());							// TO DO
				}
			};
			TF.setOnAction(name_event);

        ComboBox<String> cb_color = new ComboBox<String>(FXCollections.observableArrayList(colors));
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                String value = cb_color.getValue();
                if (value != null) {
                    Platform.runLater(() -> {
                        cb_color.setValue(null);        // à mettre dans le view
                        cb_color.getItems().remove(value);
                    });
                }
            }
        };
        cb_color.setOnAction(event);

        ComboBox<String> cb_launch = new ComboBox<String>();
        cb_launch.getItems().add("Top Left");
        cb_launch.getItems().add("Left");
        cb_launch.getItems().add("Bottom Left");
        cb_launch.getItems().add("Top Right");
        cb_launch.getItems().add("Right");
        cb_launch.getItems().add("Bottom Right");


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
        b_exit.setLayoutX(screenWidth*0.1);
        b_exit.setLayoutY(screenHeight*0.9); 


        // Bouton Next = passe au joueur suivant ou passe au début de la partie
        Button b_next = new Button("Next");
            EventHandler<ActionEvent> eventN = new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    // incrémente de 1 la boucle
                    // créer un objet player / vaisseau avec les valeurs données
                    //start(sceneMenu);
                } 
            };
        b_next.setOnAction(eventN);
        b_next.setLayoutX(screenWidth*0.4);
        b_next.setLayoutY(screenHeight*0.9); 

        stackselect.getChildren().add(root_select);
        
        root_select.getChildren().add(title);
        container_select.getChildren().add(text_name);
		container_select.getChildren().add(name);
		container_select.getChildren().add(text_color);
		container_select.getChildren().add(cb_color);
		container_select.getChildren().add(text_launch);
		container_select.getChildren().add(cb_launch);

        root_select.getChildren().addAll(b_exit, b_next);
    }	
}
