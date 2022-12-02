package view;

/*
 * @autor Maylis
*/

// Travail des proportions à faire dès que je peux excéuter

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.TimeUnit;

public class Asteroyds extends Application {

  private Stage stage;
  private Scene menu, select, game, results, finalresults;
  private Group root; 						// To see
  private double screenWidth,
                 screenHeight;

    @Override						
    public void start(Stage s) throws Exception { 
		
		// ---------------Création du menu principal---------------------
		StackPane stackmenu = new StackPane();

		// Background creation
		try {
            ImageView backmenu = ShapeConstructor.newImage("background_menu", 1,1, 0,0);
			backmenu.setFitHeight(screenWidth*1.1);	
			backmenu.setFitWidth(screenWidth*1.1);
        } catch (Exception e) {
			// si erreur, couleur par défaut en noir
            backmenu.setFill(Color.BLACK);		
        }
		
        
		// Menu creation
		HBox hmenu = new HBox();
		stackmenu.getChildren().addAll(backmenu, hmenu);
		
		// Sérapartion des espaces
		// Menu
		VBox container_menu = new VBox();
        container_menu.setPadding(new Insets(10));
        container_menu.setSpacing(10);
        
			// set background of container
			// note si marche pas, travailler avec les proportions screenWidth*1.1
			BackgroundFill background_fill = new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY);
			Background background = new Background(background_fill);
			container_menu.setBackground(background);
	
        // Rectangle vide
        Rectangle rect = new Rectangle(screenWidth*0.8,0, screenWidth*0.2,screenHeight);
		rect.setFill(Color.TRANSPARENT);    
		
        hmenu.getChildren().addAll(rect, container_menu);

		// Title creation
		Label title = new Label("Asteroyds");
		title.setFont(new Font("cmmi10", 30)); 
		title.setTextFill(Color.web("#FFF8DC"));

		// Choix du nombre de joueurs
		ComboBox cb_nbplayer = new ComboBox();
			cb_nbplayer.getItems().add("2");
			cb_nbplayer.getItems().add("3");
			cb_nbplayer.getItems().add("4");
			cb_nbplayer.getItems().add("5");
			cb_nbplayer.getItems().add("6");

		// Choix de la difficulté
		ComboBox cb_difficulty = new ComboBox();
			cb_difficulty.getItems().add("Easy - 50s");
			cb_difficulty.getItems().add("Normal - 40s");
			cb_difficulty.getItems().add("Hard - 30s");
			cb_difficulty.getItems().add("Very Hard - 20s");

		// Rules buttom
		Button b_rules = new Button("Rules");
		EventHandler<ActionEvent> eventR = new EventHandler<ActionEvent>() {
			Desktop desk = Desktop.getDesktop();
			@Override
			// ouvre dans le navigateur par défaut un lien menant aux règles du jeu
			public void handle(ActionEvent event) {
				desk.browse(new URI("http://www.cuk.ch/articles/4626/"));
			}  
		};
		b_rules.setOnAction(eventR);
		
		// Buttom Start
		Button b_start= new Button("Start");    
		EventHandler<ActionEvent> eventS = new EventHandler<ActionEvent>() {
           
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


		container_menu.getChildren().add(title);
		container_menu.getChildren().add(new Label("Select players number : "));
		container_menu.getChildren().add(cb_nbplayer);
		container_menu.getChildren().add(new Label("Select a difficulty :"));
		container_menu.getChildren().add(cb_difficulty);
		container_menu.getChildren().add(new Label("Rules here :"));
		container_menu.getChildren().add(b_rules);
		container_menu.getChildren().addAll(b_start);
		
		// ------------- Edition du stage ----------------------
		Scene menu = new Scene(stackmenu);

        stage = s;
        stage.setTitle("Asteroyds");		// nom de la fenetre
        stage.setScene(menu);				// spécifie la scene a utiliser
        stage.setFullScreen(true);			// met en plein écran
        stage.show();						// montre la scene
        
        screenWidth = scene.getWidth();		// largeur de la scene
        screenHeight = scene.getHeight();	// longueur de la scene
		
	}


	//----------- Création du scene de selection -----------
	public void selection() throws Exception { 
		
        StackPane stackselect = new StackPane();
        
		// Menu creation
		HBox hselect = new HBox();
		stackselect.getChildren().addAll(backmenu, hselect);
	
		VBox container_select = new VBox();
		container_select.setPadding(new Insets(15,20, 10,10));
        container_select.setSpacing(30);
        
			// set background of container
			BackgroundFill background_fill = new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY);
			Background background = new Background(background_fill);
			container_select.setBackground(background);   
		
        hselect.getChildren().addAll(rect, container_select);

				
		// Creation of the main title
		// To do = trouver un Png du titre du jeu ?
		Label playertitle = new Label("Joueur" i);
		playertitle.setFont(new Font("cmmi10", 18)); 
		playertitle.setTextFill(Color.web("#FFF8DC"));


		// Creation of Text Field (get the name of the player) 
			Label nameTF = new Label("Name: ");
			TextField TF = new TextField ("Joueur");
			HBox name = new HBox();
			name.getChildren().addAll(nameTF, TF);
			name.setSpacing(10);

			EventHandler<ActionEvent> name_event = new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e){
					l.setText(b.getText());							// TO DO
				}
			};
			TF.setOnAction(name_event);

			// Choix de la couleur 
			/*ComboBox cb_color = new ComboBox();
			 cb.getItems().add("Green");
			 cb.getItems().add("Blue");
			 cb.getItems().add("Red");
			 cb.getItems().add("Purple");
			 cb.getItems().add("Orange");
			 cb.getItems().add("Yellow");
			 */
			 
			ComboBox<Color> cb_color = new ComboBox<Color>();
			ObservableList<Color> l_color = List_color.getColorList();
			comboBox.setItems(l_color);


		// choix de la zone
			ComboBox cb_launch = new ComboBox();
				cb_launch.getItems().add("Top Left");
				cb_launch.getItems().add("Left");
				cb_launch.getItems().add("Bottom Left");
				cb_launch.getItems().add("Top Right");
				cb_launch.getItems().add("Right");
				cb_launch.getItems().add("Bottom Right");
				
		// Check box du mode simplifié
			CheckBox c_mode = new CheckBox("Simple Mode");
				EventHandler<ActionEvent> eventSM = new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e){
					if (c_mode.isSelected())
						l1.setText(s1 + " selected "); 									// TO DO
					else
						l1.setText(s1 + " not selected ");
					}
				};
				c_mode.setOnAction(eventSM);

		// Bouton Exit = retourne au main menu
			Button b_exit = new Button("Back to main menu");
        	b_name.setOnAction(new EventHandler<ActionEvent>()) {
				@Override
            	public void handle(ActionEvent event) {
               		stage.setScene(sceneMenu);
				// TO DO : réinitialiser toutes les valeurs ? (besoin ou pas selon le code fait plus haut)
            	}
        	};
		
		// Bouton Next = passe au joueur suivant ou passe au début de la partie
			Button b_next = new Button("Next");
        	b_next.setOnAction(new EventHandler<ActionEvent>()) {
				@Override
            	public void handle(ActionEvent event) {
					// incrémente de 1 la boucle
					// créer un objet player / vaisseau avec les valeurs données
					start(sceneMenu);
            	}
        	};
			
		container_select.getChildren().add(playertitle);
		container_select.getChildren().add(TF);
		container_select.getChildren().add(new Label("Select a Color:"));
        container_select.getChildren().add(cb_color);
		container_select.getChildren().add(new Label("Select a Launch Cell:"));
        container_select.getChildren().add(cb_launch);
        container_select.getChildren().addAll(b_exit);
        container_select.getChildren().add(c_mode);

		container_select.setAlignment(Pos.CENTER_RIGHT);
		// TO DO : creer des hbox spé aux items dont l'alignement est différent


        Scene select = new Scene(stackselect);
        stage.setScene(select);
        stage.show();

    }










	//----------- Création du scene de Jeu -----------
	// Note avant d'afficher cette scene ; calculer les directions des astéroides
	public void play_game() throws Exception { 
		
        StackPane stackgame = new StackPane();
        
		// Creation of the background
		try {
			// Demander à Matéo cmt calculs dimensions faits
			stackgame.getChildren().add(ShapeConstructor.newImage("background", screenWidth,screenHeight, screenWidth/2,screenHeight/2, 1));
		} catch (Exception e) {
			stackgame.setFill(Color.BLACK);
		}

		// Séparation des espaces board et interface
		HBox hgame = new HBox();
		stackgame.getChildren().add(hgame);
		
		// Set up du plateau de jeu
		// note a voir si placer bien le board au bon endroit, sinon essayer de le placer dans un poly rect
		displayGameBoard(new String[][]{
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

		// Set up de l'interface et des boutons (à droite)
		VBox container_game = new VBox();
		container_game.setPadding(new Insets(15,20, 10,10));
        container_game.setSpacing(30);
        
			// set background of container
			BackgroundFill background_fill = new BackgroundFill(Color.DARKBLUE, CornerRadii.EMPTY, Insets.EMPTY);
			Background background = new Background(background_fill);
			container_game.setBackground(background);   

			// test des couleurs a faire avec DIMGREY, GREY, LIGHTSLATEGRAY , MIDNIGHTBLUE, SLATEGREY 
			// de manière a ce que la délimitation ne soit pas trop brute

		hgame.getChildren().addAll(rect, container_game);


		// Label indiquant le nom / numéro du Joueur
		Label name_player = new Label("Joueur" + i); // TO DO = getname de l'objet i de la liste des joueurs
		name_player.setFont(new Font("cmmi10", 18)); 
		name_player.setTextFill(Color.web("#FFF8DC"));
				
		// Check box du mode simplifié
			CheckBox c_mode = new CheckBox("Simple Mode");
				EventHandler<ActionEvent> eventSM = new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e){
					if (c_mode.isSelected())
						l1.setText(s1 + " selected "); 		// TO DO = mode simplifié marche pen
					else
						l1.setText(s1 + " not selected ");
					}
				};
				c_mode.setOnAction(eventSM);

		// Bouton Exit = retourne au main menu
			Button b_exit = new Button("Back to main menu");
        	b_name.setOnAction(new EventHandler<ActionEvent>()) {
				@Override
            	public void handle(ActionEvent eventExit) {
               		stage.setScene(sceneMenu);
				// Si marche pas, faire une méthode spé appelant la scene
				// TO DO : réinitialiser toutes les valeurs ? (besoin ou pas selon le code fait plus haut)
            	}
        	};
		
		// Bouton Ready = passe au joueur suivant ou passe au début de la partie
			Button b_ready = new Button("Ready ?");
        	b_ready.setOnAction(new EventHandler<ActionEvent>()) {
				@Override
            	public void handle(ActionEvent eventRed) {
					// On supprime l'interface précédente
					container_game.getChildren().remove(name_player);
					container_game.getChildren().remove(b_ready);

					// On la remplace par les nouveaux boutons
					// note au timer à bien lancer a ce moment ci
					container_game.getChildren().add(haut_page);
					container_game.getChildren().add(progressBar);
					container_game.getChildren().add(liste_move);


					// incrémente de 1 la boucle
					// créer un objet player / vaisseau avec les valeurs données
					start(sceneMenu);
            	}
        	};
			

		// Set up de l'interface et des boutons après le ready
			HBox haut_page = new HBox();
			// voir si dimensions conviennent
			haut_page.setPadding(new Insets(15,20, 10,10));
			haut_page.setSpacing(30);

				GridPane liste_ast = new GridPane();
				liste_ast.setHgap(25);
				liste_ast.setVgap(15);

				// Ajout des images des astéroides 
				// Attention si proportions respectées
				Image image_ra = new Image(new FileInputStream("./res/images/red_asteroyd.png"));  
				ImageView red_ast = new ImageView(image_ra); 
				red_ast.setPreserveRatio(true);  

				Image image_wa = new Image(new FileInputStream("./res/images/white_asteroyd.png"));  
				ImageView white_ast = new ImageView(image_wa); 
				white_ast.setPreserveRatio(true); 

				Image image_ba = new Image(new FileInputStream("./res/images/blue_asteroyd.png"));  
				ImageView blue_ast = new ImageView(image_ba); 
				blue_ast.setPreserveRatio(true); 

     			liste_ast.add(red_ast, 0, 0, 1, 1);
				liste_ast.add(white_ast, 0, 1, 1, 1);
				liste_ast.add(blue_ast, 0, 2, 1, 1);

				// Ajout des directions
				Label dir_red = new Label();  // fonction random number soit ((int) Math.random()*6) +1 = a calculer avant de rentrer dans la boucle
				Label dir_white = new Label(); 
				Label dir_blue = new Label();
			
				liste_ast.add(dir_red, 1, 0, 1, 1);
				liste_ast.add(dir_white, 1, 1, 1, 1);
				liste_ast.add(dir_blue, 1, 2, 1, 1);


				HBox ast_red = new HBox();
				ast_red.setPadding(new Insets(15,20, 10,10));
				ast_red.setSpacing(30);
				ast_red.getChildren().add(ShapeConstructor.newImage(asteroydColor + "red_asteroyd", 10,10, 0,0));
				ast_red.getChildren().add()

				// Pour le timer = problème au niveau de l'affichage des chiffres ?
				
				Time time = new Time(); // donner le temps choisi selon difficulté
				private Text timer;
				
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
						progressBar.setProgress(progressBar.getProgress()+ 100/ --- );// temps choisi
				}));
				

				haut_page.getChildren().add(liste_ast);
				haut_page.getChildren().add(time);

				GridPane liste_move = new GridPane();
				liste_move.setHgap(25);
				liste_move.setVgap(15);
				
				// Combobox de choix des mouvements
				ComboBox cb_move = new ComboBox();
					cb_move.getItems().add("Move Forward");
					cb_move.getItems().add("Move right");
					cb_move.getItems().add("Move left");
					cb_move.getItems().add("Turn around");

				liste_move.add(1, 0, 0, 1, 1); liste_move.add(cb_move, 1, 0, 1, 1);
				liste_move.add(2, 0, 1, 1, 1); liste_move.add(cb_move, 1, 1, 1, 1);
				liste_move.add(3, 0, 2, 1, 1); liste_move.add(cb_move, 1, 2, 1, 1);
				liste_move.add(4, 0, 3, 1, 1); liste_move.add(cb_move, 1, 3, 1, 1);
				liste_move.add(5, 0, 4, 1, 1); liste_move.add(cb_move, 1, 4, 1, 1);
				liste_move.add(6, 0, 5, 1, 1); liste_move.add(cb_move, 1, 5, 1, 1);

				// TO DO si une combobox suivant une combobox non remplie est remplie, décaler sa valeur vers la box précédente
				// A voir si new méthode ou class a faire



// HERE

		container_game.getChildren().add(name_player);
		container_game.getChildren().add(c_mode);
		container_game.getChildren().add(b_exit);
        container_game.getChildren().add(b_ready);

        game = new Scene(container_game);
        stage.setScene(game);
        stage.show();

    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}
