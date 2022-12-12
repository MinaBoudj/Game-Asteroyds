package view;

/**
 * @author Maylis
 */

import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.paint.Color; 
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;


public class EndGroup extends Group {
    private double screenWidth;
    private double screenHeight;
    private ArrayList<String[]> ranking;
	
    public EndGroup(double screenWidth, double screenHeight) {
        super();
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    public void updateRoot(String[] players, Executable mainMenu) throws Exception {
        getChildren().removeAll(getChildren());

        double maxTextHeight = screenHeight/14.5,
               maxTextWidth = screenWidth/8.5;
        ranking = new ArrayList<String[]>();
               
        try {getChildren().add(ShapeConstructor.newImage("background", screenWidth*1.5,screenHeight*1.5, screenWidth/2,screenHeight/2, 1));}
        catch (Exception e) {getChildren().add(ShapeConstructor.newRectangle(Color.BLACK, screenWidth*1.5, screenHeight*1.5, screenWidth/2, screenHeight/2));}
        
        ranking_player(players);
        Text title = ShapeConstructor.newText("Scores : ", Color.WHITE, screenWidth,maxTextHeight*1.5, screenWidth/2,maxTextHeight*0.75);

		Rectangle scorePane = ShapeConstructor.newRectangle(Color.web("A9A9A9",0.6), maxTextWidth*7.5,maxTextHeight*10.8, screenWidth/2,screenHeight/2);
        Text rank = ShapeConstructor.newText("Rank", Color.BLACK, maxTextWidth,maxTextHeight, maxTextWidth,maxTextHeight*3),
             name = ShapeConstructor.newText("Pseudo", Color.BLACK, maxTextWidth,maxTextHeight*2, maxTextWidth*2.5,maxTextHeight*3),
             relics = ShapeConstructor.newText("Number of Relics", Color.BLACK, maxTextWidth,maxTextHeight*2, maxTextWidth*4.5,maxTextHeight*3),
             structPoints = ShapeConstructor.newText("Structure Points", Color.BLACK, maxTextWidth,maxTextHeight*2, maxTextWidth*7,maxTextHeight*3);

        int minRank = 0;
        for(int i = 0 ; i < ranking.size() ; i++) {
            Color pColor = ControlConstructor.getPlayerColor(ranking.get(i)[3]);
            double y = maxTextHeight*(i*1.5 + 4.5);
            if(minRank == 0 || comparePlayers(ranking.get(i-1), ranking.get(i)) != 0)
                minRank++;

            Text pRank = ShapeConstructor.newText(Integer.toString(minRank), pColor, maxTextWidth,maxTextHeight, maxTextWidth,y),
                 pName = ShapeConstructor.newText(ranking.get(i)[1], pColor, maxTextWidth,maxTextHeight*2, maxTextWidth*2.5,y),
                 pRelics = ShapeConstructor.newText(ranking.get(i)[0], pColor, maxTextWidth,maxTextHeight*2, maxTextWidth*4.5,y),
                 pStructPoints = ShapeConstructor.newText(ranking.get(i)[2], pColor, maxTextWidth,maxTextHeight*2, maxTextWidth*7,y);
            getChildren().addAll(pRank, pName, pRelics, pStructPoints);
        }

		Text menuButton = ControlConstructor.newButton("Main Menu", Color.WHITE, screenWidth,maxTextHeight, screenWidth/2,maxTextHeight*14, Color.YELLOW, mainMenu);

		getChildren().addAll(title, scorePane, rank,name,relics,structPoints, menuButton);
	}

    public void ranking_player(String[] players){
        for (String p : players) {
            String[] playerInfos = p.split("-");

            int relics = 0;
            for(String r : playerInfos[0].split("/"))
                if(r != "")
                    relics++;
            playerInfos[0] = Integer.toString(relics);

            int i = 0;
            while(i < ranking.size() && comparePlayers(ranking.get(i), playerInfos) == 1)
                i++;
            ranking.add(i, playerInfos);
        }
    }

    private int comparePlayers(String[] first, String[] second) {
        if(Integer.parseInt(first[2]) <= 0)
            if(Integer.parseInt(second[2]) == 0)
                return comparePlayerRelics(first, second);
            else
                return -1;
        else if(Integer.parseInt(second[2]) <= 0)
            return 1;
        else
            return comparePlayerRelics(first, second) == 0 ? comparePlayerStructurePoints(first, second) : comparePlayerRelics(first, second);
    }

    private int comparePlayerRelics(String[] first, String[] second) {
        return Integer.parseInt(first[0]) > Integer.parseInt(second[0]) ? 1 :
                   Integer.parseInt(first[0]) == Integer.parseInt(second[0]) ? 0 : -1;
    }

    private int comparePlayerStructurePoints(String[] first, String[] second) {
        return Integer.parseInt(first[2]) > Integer.parseInt(second[2]) ? 1 :
                   Integer.parseInt(first[2]) == Integer.parseInt(second[2]) ? 0 : -1;
    }
}
/* -1-1-2-2-
1.5
1
1
-
1
-
1
-
1
-
1
-
1
-
1
1
1
 */