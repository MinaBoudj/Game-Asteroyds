package view;

/**
 * @author Matéo
 */

import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Callback;

public class ControlConstructor {
    public static Text newButton(String content, Color fill, double maxWidth,double maxHeight, double centerX,double centerY, Color selected, Executable toDo) {
        Text button = ShapeConstructor.newText(content, fill, maxWidth,maxHeight, centerX,centerY);

        button.setOnMouseEntered(mouseEvent -> {button.setFill(selected);});
        button.setOnMouseExited(mouseEvent -> {button.setFill(fill);});
        button.setOnMouseClicked((mouseEvent) -> {toDo.execute(mouseEvent);});

        return button; 
    }

    public static ComboBox<String> newComboBox(String[] items, double maxWidth,double maxHeight, double centerX,double centerY) {
        String longestText = "";
        for(String it : items) {
            longestText = longestText.length() < it.length() ? it : longestText;
        }

        Text text = new Text(longestText);
        Font font = new Font(maxWidth);

        do {
            font = new Font(font.getSize() - 1);
            text.setFont(font);
        } while(maxWidth/2 < text.getBoundsInLocal().getWidth() || maxHeight/2 < text.getBoundsInLocal().getHeight());

        double comboBoxWidth = text.getBoundsInLocal().getWidth()*1.25,
               comboBoxHeight = text.getBoundsInLocal().getHeight()*1.25;

        ComboBox<String> comboBox = new ComboBox<String>();
        comboBox.getItems().addAll(items);
        comboBox.setValue(items[0]);
        comboBox.setMinWidth(comboBoxWidth);
        comboBox.setMinHeight(comboBoxHeight);
        comboBox.setLayoutX(centerX - comboBoxWidth/2);
        comboBox.setLayoutY(centerY - comboBoxHeight/2);

        comboBox.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
                @Override
                public ListCell<String> call(ListView<String> p) {
                    return new ListCell<String>() {
                        @Override
                        protected void updateItem(String item, boolean empty) {
                            super.updateItem(item, empty);
                            if(item == null || empty)
                                setText(null);
                            else
                                setText(item);
                            setAlignment(Pos.CENTER);
                            setFont(text.getFont());
                        }
                    };
                };
            });

        comboBox.buttonCellProperty().set(new ListCell<String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if(item == null || empty)
                    setText(null);
                else
                    setText(item);
                setAlignment(Pos.CENTER);
                setFont(text.getFont());
            }
        });

        return comboBox;
    }

    public static CheckBox newCheckBox(double size, double centerX,double centerY) {
        CheckBox checkBox = new CheckBox();

        checkBox.setMinWidth(size);
        checkBox.setMinHeight(size);
        checkBox.setLayoutX(centerX - size/2);
        checkBox.setLayoutY(centerY - size/2);

        return checkBox;
    }
    
    public static Color getPlayerColor(String colorInfo) throws Exception {
        Color color;

        switch(colorInfo) {
            case "Red":
                color = Color.BROWN;
                break;

            case "Blue":
                color = Color.BLUE;
                break;

            case "Green":
                color = Color.GREEN;
                break;

            case "Yellow":
                color = Color.YELLOW;
                break;

            case "Orange":
                color = Color.ORANGE;
                break;

            case "Purple":
                color = Color.PURPLE;
                break;

            default:
                throw new Exception(/*TODO*/);
        }

        return color;
    }
}