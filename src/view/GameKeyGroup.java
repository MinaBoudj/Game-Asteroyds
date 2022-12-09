package view;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class GameKeyGroup extends Group {
    private double screenHeight;
    private double screenWidth;
	
    public GameKeyGroup(double screenWidth, double screenHeight){
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
    }

    public void displayGameKeyGroup(Executable back){
        double maxObjHeight = screenHeight/9.5,
               hexSize = Math.min(screenWidth/Math.sqrt(3), maxObjHeight/1.5),
               hexWidth = Math.sqrt(3) * hexSize;

        try {getChildren().add(ShapeConstructor.newImage("background", screenWidth*1.5,screenHeight*1.5, screenWidth/2,screenHeight/2, 1));}
        catch (Exception e) {getChildren().add(ShapeConstructor.newRectangle(Color.BLACK, screenWidth*1.5, screenHeight*1.5, screenWidth/2, screenHeight/2));}

        Text gamekeyText = ShapeConstructor.newText("Game Key ", Color.WHITE, screenWidth,maxObjHeight, screenWidth/2,maxObjHeight*0.75);

        try {getChildren().add(ShapeConstructor.newImage("white_portal", screenWidth/4,maxObjHeight, screenWidth/8,maxObjHeight*2.5));} 
        catch (Exception e) {getChildren().add(ShapeConstructor.newCircle(Color.SILVER, hexWidth/2, screenWidth/8,maxObjHeight*2.5));}
        Text portal = ShapeConstructor.newText("- Portal", Color.WHITE, screenWidth/4,maxObjHeight*0.8, 0,maxObjHeight*2.5);
        portal.setX(screenWidth/4);

        try {getChildren().add(ShapeConstructor.newImage("red_asteroyd", screenWidth/4,maxObjHeight, screenWidth/8,maxObjHeight*4));} 
        catch (Exception e) {getChildren().add(ShapeConstructor.newHexagon(Color.RED, hexSize, screenWidth/8,maxObjHeight*4));}
        Text uAsteroyd = ShapeConstructor.newText("- Unicolored Asteroyd", Color.WHITE, screenWidth/4,maxObjHeight*0.8, 0,maxObjHeight*4);
        uAsteroyd.setX(screenWidth/4);

        try {getChildren().add(ShapeConstructor.newImage("white_blue_asteroyd", screenWidth/4,maxObjHeight, screenWidth/8,maxObjHeight*5.5));} 
        catch (Exception e) {getChildren().addAll(ShapeConstructor.newHexagon(Color.SILVER, Color.AQUA, hexSize, screenWidth/8,maxObjHeight*5.5));}
        Text bAsteroyd = ShapeConstructor.newText("- Bicolored Asteroyd", Color.WHITE, screenWidth/4,maxObjHeight*0.8, 0,maxObjHeight*5.5);
        bAsteroyd.setX(screenWidth/4);

        getChildren().add(ShapeConstructor.newHexagonStroke(Color.WHITE, hexSize, screenWidth/8,maxObjHeight*7));
        Text priorityNum = ShapeConstructor.newText("8", Color.WHITE, hexWidth/4,hexWidth, screenWidth/8,maxObjHeight*7);
        Rectangle priorityRect = ShapeConstructor.newRectangle(Color.BLACK, hexWidth/4, priorityNum.getBoundsInLocal().getHeight()*0.8, screenWidth/8,maxObjHeight*7);
        Text priority = ShapeConstructor.newText("- Asteroyd Priority", Color.WHITE, screenWidth/4,maxObjHeight*0.8, 0,maxObjHeight*7);
        priority.setX(screenWidth/4);

        getChildren().add(ShapeConstructor.newHexagonStroke(Color.WHITE, hexSize, screenWidth/8,maxObjHeight*8.5));
        for(int i = 0 ; i < 6 ; i++) {
            double centerX = screenWidth/8 + hexSize*2/3 * Math.cos((60 * i) * ShapeConstructor.TORAD),
                   centerY = maxObjHeight*8.5 + hexSize*2/3 * Math.sin((60 * i) * ShapeConstructor.TORAD);
            String direction = i == 0 ? "1" :
                                   i == 1 ? "5" :
                                       i == 2 ? "3" :
                                           i == 3 ? "6" :
                                               i == 4 ? "2" : "4";
            Text directionText = ShapeConstructor.newText(direction, Color.WHITE, hexSize/4,hexWidth, centerX,centerY);
            Rectangle directionRect = ShapeConstructor.newRectangle(Color.BLACK, hexSize/4,directionText.getBoundsInLocal().getHeight()*0.8, centerX, centerY);
            getChildren().addAll(directionRect, directionText);
        }
        Text directions = ShapeConstructor.newText("- Asteroyd Directions", Color.WHITE, screenWidth/4,maxObjHeight*0.8, 0,maxObjHeight*8.5);
        directions.setX(screenWidth/4);

        try {getChildren().add(ShapeConstructor.newImage("relic1", screenWidth/4,maxObjHeight, screenWidth*5/8,maxObjHeight*2.5));} 
        catch (Exception e) {getChildren().add(ShapeConstructor.newText("1", Color.CHARTREUSE, screenWidth/4,maxObjHeight, screenWidth*5/8,maxObjHeight*2.5));}
        Text relic = ShapeConstructor.newText("- Relic Symbol", Color.WHITE, screenWidth/4,maxObjHeight*0.8, 0,maxObjHeight*2.5);
        relic.setX(screenWidth*3/4);

        try {getChildren().add(ShapeConstructor.newImage("purple_space_ship", screenWidth/4,maxObjHeight, screenWidth*5/8,maxObjHeight*4));} 
        catch (Exception e) {getChildren().add(ShapeConstructor.newTriangle(Color.PURPLE, hexSize, screenWidth*5/8,maxObjHeight*4, 1));}
        Text spaceShip = ShapeConstructor.newText("- Space Ship", Color.WHITE, screenWidth/4,maxObjHeight*0.8, 0,maxObjHeight*4);
        spaceShip.setX(screenWidth*3/4);
        
        try {getChildren().add(ShapeConstructor.newImage("audience_pod", screenWidth/4,maxObjHeight, screenWidth*5/8,maxObjHeight*5.5));} 
        catch (Exception e) {getChildren().add(ShapeConstructor.newHexagon(Color.WHITE, hexSize, screenWidth*5/8,maxObjHeight*5.5));}
        Text audiencePod = ShapeConstructor.newText("- Audience Pod", Color.WHITE, screenWidth/4,maxObjHeight*0.8, 0,maxObjHeight*5.5);
        audiencePod.setX(screenWidth*3/4);

        try {getChildren().add(ShapeConstructor.newImage("launchpad", screenWidth/4,maxObjHeight, screenWidth*5/8,maxObjHeight*7));} 
        catch (Exception e) {getChildren().add(ShapeConstructor.newHexagon(Color.CHOCOLATE, hexSize, screenWidth*5/8,maxObjHeight*7));}
        Text launchpad = ShapeConstructor.newText("- Launchpad", Color.WHITE, screenWidth/4,maxObjHeight*0.8, 0,maxObjHeight*7);
        launchpad.setX(screenWidth*3/4);

        Text backButton = ControlConstructor.newButton("Go back game", Color.WHITE, screenWidth/2,maxObjHeight*0.8, screenWidth*3/4,maxObjHeight*8.5, Color.YELLOW, back);

        getChildren().addAll(gamekeyText, portal, uAsteroyd, bAsteroyd, priority,priorityRect,priorityNum, directions, relic, spaceShip, audiencePod, launchpad, backButton);
    }
}
/*
1.5
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
 */