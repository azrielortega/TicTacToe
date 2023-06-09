import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Tile extends StackPane {
    public int code;
    public boolean empty = true;
    public String type = "blank";
    private Text text = new Text();

    public Tile(){
        Rectangle tile = new Rectangle (200,200);
        tile.setFill(null);
        tile.setStroke(Color.WHITE);
        text.setFont(Font.font(72));
        setAlignment(Pos.CENTER);
        getChildren().addAll(tile, text);
    }
    public void setCode(int c){
        code = c;
    }
    public int getCode(){
        return code;
    }
    public void setEmpty(boolean bool){
        empty = bool;
    }
    public boolean getEmpty(){
        return empty;
    }

    public String getType() {
        return type;
    }

    public void setType(String t) {
        type = t;
    }

    public void drawX(){
        type = "X";
        text.setText("X");
        text.setFill(Color.rgb(34,163,196));
        text.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 80));
    }
    public void setInitial(){
        type = "blank";
        text.setText("");
    }
    public void drawO(){
        type = "O";
        text.setText("O");
        text.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 80));
        text.setFill(Color.rgb(252,85,139));
    }

    public void drawType(String t){
        System.out.println(t);
        switch (t){
            case "O":
                drawO();
                break;
            case "X":
                drawX();
                break;
            case "blank":
                setInitial();
                break;
        }
    }
}