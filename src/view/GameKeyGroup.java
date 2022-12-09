package view;

import javafx.scene.Group;
import javafx.scene.paint.Color; 
import javafx.scene.text.Text;
import javafx.scene.image.ImageView;

public class GameKeyGroup extends Group {
    private double screenHeight;
    private double screenWidth;
	
    public GameKeyGroup(double screenWidth, double screenHeight){
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
    }

    public void delayGameKeyGroup(Executable back){
        try{
            getChildren().add(ShapeConstructor.newRectangle(Color.BLACK, screenWidth*1.5, screenHeight*1.5, screenWidth/2, screenHeight/2));

            Text gamekeyText = ShapeConstructor.newText("GameKey ", Color.WHITE, screenWidth*0.20,screenHeight*0.15, screenWidth*0.40,screenHeight*0.1);
            
            ImageView audiencePodText = ShapeConstructor.newImage("audience_pod", screenWidth*0.15, screenHeight*0.15, screenWidth*0.15, screenHeight*0.2);
            Text audiencePodImage = ShapeConstructor.newText(" : non-displaceable spectator platform. It can not accommodate vessels.", Color.WHITE, screenWidth*0.15,screenHeight*0.05, screenWidth*0.30,screenHeight*0.2);

            ImageView asteroydRedImage = ShapeConstructor.newImage("red_asteroyd", screenWidth*0.15, screenHeight*0.15, screenWidth*0.15, screenHeight*0.3);
            ImageView asteroydWhiteImage = ShapeConstructor.newImage("white_asteroyd", screenWidth*0.15, screenHeight*0.15, screenWidth*0.15, screenHeight*0.35);
            ImageView asteroydBlueImage = ShapeConstructor.newImage("blue_asteroyd", screenWidth*0.15, screenHeight*0.15, screenWidth*0.15, screenHeight*0.4);

            Text asteroydText = ShapeConstructor.newText(" : the asteroids move in a direction indicated by the dice.", Color.WHITE, screenWidth*0.1,screenHeight*0.05, screenWidth*0.30,screenHeight*03);
            Text asteroydRedText = ShapeConstructor.newText(" : Reds can move twice.", Color.WHITE, screenWidth*0.15,screenHeight*0.05, screenWidth*0.30,screenHeight*0.35);
            Text asteroydWhiteText = ShapeConstructor.newText(" : Whites can move once.", Color.WHITE, screenWidth*0.15,screenHeight*0.05, screenWidth*0.30,screenHeight*0.4);
            Text asteroydBlueText = ShapeConstructor.newText(" : Blue can move once and push others asteroids.", Color.WHITE, screenWidth*0.05,screenHeight*0.30, screenWidth*0.15,screenHeight*0.45);
            Text asteroydVariantsText = ShapeConstructor.newText(" The asteroids at the same time red and white move according to the directions of the reds and then the whites. Same for the white and blue ones.", Color.WHITE, screenWidth*0.15,screenHeight*0.05, screenWidth*0.30,screenHeight*0.48);

            ImageView spaceShipImage = ShapeConstructor.newImage("orange_space_ship", screenWidth*0.15, screenHeight*0.15, screenWidth*0.15, screenHeight*0.55);
            Text spaceShipText = ShapeConstructor.newText(" : Representation of a player's ship.", Color.WHITE, screenWidth*0.05,screenHeight*0.15, screenWidth*0.30,screenHeight*0.55);

            ImageView launchpadImage = ShapeConstructor.newImage("launchpad", screenWidth*0.15, screenHeight*0.15, screenWidth*0.15, screenHeight*0.65);
            Text launchpadText = ShapeConstructor.newText(" : Representation of the launch pads where the ships start the race.", Color.WHITE, screenWidth*0.15,screenHeight*0.05, screenWidth*0.30,screenHeight*0.65);

            ImageView portailsImage = ShapeConstructor.newImage("white_portal", screenWidth*0.15, screenHeight*0.15, screenWidth*0.15, screenHeight*0.75);
            Text portailsText = ShapeConstructor.newText(" : Portals carried by asteroids, containing relics, to which the ships must go.", Color.WHITE, screenWidth*0.15,screenHeight*0.05, screenWidth*0.30,screenHeight*0.75);

            ImageView relicsImage = ShapeConstructor.newImage("relic1", screenWidth*0.15, screenHeight*0.15, screenWidth*0.15, screenHeight*0.85);
            Text relicsText = ShapeConstructor.newText(" : Artifact to obtain in order to win the race!", Color.WHITE, screenWidth*0.15,screenHeight*0.05, screenWidth*0.30,screenHeight*0.85);

            Text backButton = ControlConstructor.newButton("Go back game", Color.WHITE, screenWidth*0.28,screenHeight*0.05, screenWidth*0.85,screenHeight*0.800, Color.BLACK, back);

            getChildren().addAll(gamekeyText, audiencePodText, audiencePodImage, asteroydRedImage, asteroydWhiteImage, 
            asteroydBlueImage, asteroydText, asteroydRedText, asteroydWhiteText, asteroydBlueText, asteroydVariantsText,
            spaceShipImage, spaceShipText, launchpadImage, launchpadText, portailsImage, portailsText, relicsImage, relicsText, backButton);
        } catch ( Exception e){
            getChildren().add(ShapeConstructor.newRectangle(Color.BLACK, screenWidth*1.5, screenHeight*1.5, screenWidth/2, screenHeight/2));

        }
    }
}
