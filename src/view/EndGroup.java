package view;


import java.util.ArrayList;
import javafx.scene.Group;
import javafx.scene.paint.Color; 
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;


public class EndGroup extends Group {
    private double screenWidth;
    private double screenHeight;
    private ArrayList<String[]> ranking_player;
    private ArrayList<String[]> player_over;
    private String[] playersplit;
	
    public EndGroup(double screenWidth, double screenHeight) {
        super();
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    public void updateEndScene(Executable exit, Executable mainMenu, String players){ 
	
        ranking_player(players);
        Text resultsText = ShapeConstructor.newText("Results : ", Color.WHITE, screenWidth*0.28,screenHeight*0.1, screenWidth*0.5,screenHeight*0.5);
		Rectangle paneResultats = ShapeConstructor.newRectangle(Color.web("A9A9A9",0.6), screenWidth, screenHeight*0.3, screenWidth, screenHeight*0.2);
        
        displayRanking(playersplit.length);
		Text backmainButton = ControlConstructor.newButton("Start", Color.WHITE, screenWidth*0.28,screenHeight*0.05, screenWidth*0.15,screenHeight*0.800, Color.BLACK, mainMenu);
		Text exitButton = ControlConstructor.newButton("Exit Game", Color.WHITE, screenWidth*0.28,screenHeight*0.05, screenWidth*0.15,screenHeight*0.875, Color.BLACK, exit);

		getChildren().addAll(resultsText, paneResultats, backmainButton, exitButton);
	}

    public void ranking_player(String players){
        playersplit = players.split("_");
        for (int i = 0; i < playersplit.length; i++){
            String [] playerInfos = playersplit[i].split("-");
            ranking_player.get(i)[0] = playerInfos[0];
            ranking_player.get(i)[1] = playerInfos[1];
            ranking_player.get(i)[2] = playerInfos[2];
        }   
        
        for (String[] player : ranking_player){
            if (player[2] == "0"){
                player_over.add(player);
                ranking_player.remove(player);
            }
        }
        
        String x;
        for (int i = ranking_player.size(); i > 0; i--){
            for (int j = 1; j < ranking_player.size(); j++){
                int compare = ranking_player.get(j+1)[0].compareTo(ranking_player.get(j)[0]);
                if (compare > 0){
                    x = ranking_player.get(j+1)[0];
                    ranking_player.get(j+1)[0] = ranking_player.get(j)[0];
                    ranking_player.get(j)[0] = x;
                }

            }
        }
        ranking_player.addAll(player_over);
    }
        // usefull ?
        //Collections.sort(ranking_player.get().get() , Collections.reverseOrder()); // marche pas ?
        //Collections.sort(player_over , Collections.reverseOrder());
        
    public void displayRanking(int nb_players){
        String [] places = {"First : ", "Second : ", "Third : ", "Fourth : ", "Fifth : ", "Sixth : "};
        int nombre_players = nb_players;
        switch(nb_players){
            case 2:  // test les dimentions
                for (int i = 0; i < nombre_players ; i++){
                    getChildren().addAll(ShapeConstructor.newText(places[i] + ranking_player.get(i)[1] , Color.WHITE, screenWidth*0.28,screenHeight*0.15, screenWidth*0.3,screenHeight*(0.25+ 0.12*i )),
                        ShapeConstructor.newText("Relics : " + ranking_player.get(i)[0] , Color.WHITE, screenWidth*0.28,screenHeight*0.10, screenWidth*0.33,screenHeight*(0.31 +0.12*i)),
                        ShapeConstructor.newText("Condition of the Ship : " + ranking_player.get(i)[2] , Color.WHITE, screenWidth*0.28,screenHeight*0.10, screenWidth*0.33,screenHeight*(0.37 +0.12*i)));
                } break;
            case 3:
                displayRanking(2); break;
                
            case 4:
                displayRanking(2); break;

            case 5:
                getChildren().addAll(ShapeConstructor.newText(places[0] + ranking_player.get(0)[1] , Color.WHITE, screenWidth*0.28,screenHeight*0.15, screenWidth*0.3,screenHeight*(0.25)),
                        ShapeConstructor.newText("Relics : " + ranking_player.get(0)[0] , Color.WHITE, screenWidth*0.28,screenHeight*0.10, screenWidth*0.33,screenHeight*(0.31)),
                        ShapeConstructor.newText("Condition of the Ship : " + ranking_player.get(0)[2] , Color.WHITE, screenWidth*0.28,screenHeight*0.10, screenWidth*0.33,screenHeight*(0.37)));
                for (int i = 1; i < 3 ; i++){
                    getChildren().addAll(ShapeConstructor.newText(places[i] + ranking_player.get(i)[1] , Color.WHITE, screenWidth*0.28,screenHeight*0.15, screenWidth*0.2,screenHeight*(0.25+ 0.15*i )),
                        ShapeConstructor.newText("Relics : " + ranking_player.get(i)[0] , Color.WHITE, screenWidth*0.28,screenHeight*0.10, screenWidth*0.2,screenHeight*(0.31 +0.15*i)),
                        ShapeConstructor.newText("Condition of the Ship : " + ranking_player.get(i)[2] , Color.WHITE, screenWidth*0.28,screenHeight*0.10, screenWidth*0.2,screenHeight*(0.37 +0.15*i)));
                }
                for (int i = 3; i < 5 ; i++){
                    getChildren().addAll(ShapeConstructor.newText(places[i] + ranking_player.get(i)[1] , Color.WHITE, screenWidth*0.28,screenHeight*0.15, screenWidth*0.5,screenHeight*(0.25+ 0.16*(i-2) )),
                        ShapeConstructor.newText("Relics : " + ranking_player.get(i)[0] , Color.WHITE, screenWidth*0.28,screenHeight*0.10, screenWidth*0.5,screenHeight*(0.31 +0.16*(i-2))),
                        ShapeConstructor.newText("Condition of the Ship : " + ranking_player.get(i)[2] , Color.WHITE, screenWidth*0.28,screenHeight*0.10, screenWidth*0.5,screenHeight*(0.37 +0.16*(i-2))));
                } break;
            case 6:
                displayRanking(5);
                getChildren().addAll(ShapeConstructor.newText(places[5] + ranking_player.get(5)[1] , Color.WHITE, screenWidth*0.28,screenHeight*0.15, screenWidth*0.3,screenHeight*0.66),
                            ShapeConstructor.newText("Relics : " + ranking_player.get(5)[0] , Color.WHITE, screenWidth*0.28,screenHeight*0.10, screenWidth*0.33,screenHeight*0.71),
                            ShapeConstructor.newText("Condition of the Ship : " + ranking_player.get(5)[2] , Color.WHITE, screenWidth*0.28,screenHeight*0.10, screenWidth*0.33,screenHeight*0.76)
                );
                break;
        }
    } 
}