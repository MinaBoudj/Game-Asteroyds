package view;

/**
 * @author Matéo
 */

import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;

import java.io.FileInputStream;
import java.io.InputStream;

public class ShapeConstructor {
    public static boolean NOIMAGE = false;
    public static final double TORAD = Math.PI / 180;

    public static Double[] newHexagonCorners(double centerX,double centerY, double size) {
        Double[] hexCorners = new Double[14];
        for(int i = 0 ; i < 12 ; i += 2) {
            double angleDeg = 30 * (i - 1);
            hexCorners[i] = centerX + size * Math.cos(angleDeg * TORAD);
            hexCorners[i+1] = centerY + size * Math.sin(angleDeg * TORAD);
        }
        hexCorners[12] = centerX + size * Math.cos(-30 * TORAD);
        hexCorners[13] = centerY + size * Math.sin(-30 * TORAD);

        return hexCorners;
    }

    public static Text newText(String content, Color fill, double maxWidth,double maxHeight, double centerX,double centerY) {
        Text text = new Text(content);
        text.setFill(fill);

        Font font = new Font(maxWidth);
        text.setFont(font);
        while (text.getBoundsInLocal().getWidth() > maxWidth || text.getBoundsInLocal().getHeight() > maxHeight) {
            font = new Font(font.getSize() - 1);
            text.setFont(font);
        }

        double textWidth = text.getBoundsInLocal().getWidth(),
               textHeight = text.getBoundsInLocal().getHeight()/2;

        text.setX(centerX - textWidth/2);
        text.setY(centerY + textHeight/2);

        return text; 
    }

    public static Rectangle newRectangle(Color color, double width,double height, double centerX,double centerY) {
        Rectangle rectangle = new Rectangle(width, height);
        rectangle.setX(centerX - width/2);
        rectangle.setY(centerY - height/2);
        rectangle.setFill(color);

        return rectangle;
    }

    public static ImageView newImage(String imageName, double maxWidth,double maxHeight, double centerX,double centerY) throws Exception {
        return newImage(imageName, maxWidth,maxHeight, centerX,centerY, 1);
    }

    public static ImageView newImage(String imageName, double maxWidth,double maxHeight, double centerX,double centerY, int orientation) throws Exception {
        if(NOIMAGE)
            throw new Exception();
            
        InputStream imageStream = new FileInputStream("./res/images/" + imageName + ".png");
        Image image = new Image(imageStream);
        ImageView imageView = new ImageView(image);
        
        imageView.setRotate((orientation - 1) * 60);
        imageView.setFitWidth(maxWidth);
        imageView.setFitHeight(maxHeight);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        imageView.setX(centerX - imageView.getBoundsInLocal().getWidth()/2);
        imageView.setY(centerY - imageView.getBoundsInLocal().getHeight()/2);

        return imageView;
    }

    public static Polygon newHexagon(Color color, double size, double centerX,double centerY) {
        Polygon hexagon = new Polygon();
        hexagon.getPoints().addAll(newHexagonCorners(centerX,centerY, size));
        hexagon.setFill(color);

        return hexagon;
    }

    public static Polygon[] newHexagon(Color color1,Color color2, double size, double centerX,double centerY) {
        Double[] hexagonCorners = newHexagonCorners(centerX,centerY, size);

        Polygon halfHexagon1 = new Polygon();
        for (int i = 0 ; i < 8 ; i++) {
            halfHexagon1.getPoints().add(hexagonCorners[i]);
        }
        halfHexagon1.getPoints().add(hexagonCorners[0]);
        halfHexagon1.getPoints().add(hexagonCorners[1]);
        halfHexagon1.setFill(color1);

        Polygon halfHexagon2 = new Polygon();
        for (int i = 6 ; i < 14 ; i++) {
            halfHexagon2.getPoints().add(hexagonCorners[i]);
        }
        halfHexagon2.getPoints().add(hexagonCorners[6]);
        halfHexagon2.getPoints().add(hexagonCorners[7]);
        halfHexagon2.setFill(color2);

        return new Polygon[]{halfHexagon1, halfHexagon2};
    }

    public static Circle newCircle(Color color, double radius, double centerX,double centerY) {
        Circle circle = new Circle(centerX,centerY, radius);
        circle.setStrokeWidth(radius/10);
        circle.setStroke(color);
        circle.setFill(Color.TRANSPARENT);

        return circle;
    }

    public static Polygon newTriangle(Color color, double size, double centerX,double centerY, int orientation) {
        double distToCenter = size / Math.sqrt(3);
        Polygon triangle = new Polygon();
        for(int i = 0 ; i < 6 ; i += 2) {
            double angleDeg = 60 * i;
            triangle.getPoints().add(centerX + distToCenter * Math.cos(angleDeg * TORAD));
            triangle.getPoints().add(centerY + distToCenter * Math.sin(angleDeg * TORAD));
        }
        triangle.getPoints().add(centerX + distToCenter * Math.cos(-TORAD));
        triangle.getPoints().add(centerY + distToCenter * Math.sin(-TORAD));

        triangle.setFill(color);
        triangle.setRotate(orientation * 60);

        return triangle;
    }

    // A vérif
    public static Polygon newArrow(Color color, double startX,double startY, double endX, double endY, double arrowHeadSize, double orientation) {
        Polygon arrow = new Polygon();
        
        double angle = Math.atan2((endY - startY), (endX - startX)) - Math.PI / 2.0;
        arrow.getPoints().add((- 1.0 / 2.0 * Math.cos(angle) + Math.sqrt(3) / 2 * Math.sin(angle)) * arrowHeadSize + endX);
        arrow.getPoints().add((- 1.0 / 2.0 * Math.sin(angle) - Math.sqrt(3) / 2 * Math.cos(angle)) * arrowHeadSize + endY);
        
        arrow.getPoints().add((1.0 / 2.0 * Math.cos(angle) + Math.sqrt(3) / 2 * Math.sin(angle)) * arrowHeadSize + endX);
        arrow.getPoints().add((1.0 / 2.0 * Math.sin(angle) - Math.sqrt(3) / 2 * Math.cos(angle)) * arrowHeadSize + endY);

        arrow.setFill(color);
        arrow.setRotate(orientation * 60);

        return arrow;
    }
}
