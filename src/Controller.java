import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;


public class Controller {
    @FXML
    AnchorPane box = new AnchorPane();
    @FXML
    Label msg = new Label();


    Model model = new Model();

    int level = 0;
    int ctr = 0;
    public void createGrid(){ // to create 3x3 grid
        int code = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Model.grid[i][j] =  new Tile();
                Model.grid[i][j].setTranslateY(i*200 + 5);
                Model.grid[i][j].setTranslateX(j*200 + 5);
                Model.grid[i][j].setCode(code); // to be able to generate code 0-9 later for random
                code++;
                box.getChildren().add(Model.grid[i][j]);
            }
        }
        msg.setText("Your turn!");
    }
    @FXML
    public void handleClick(MouseEvent me) {
        double posX = me.getX();
        double posY = me.getY();

        int row = (int) (posY/200);
        int col = (int) (posX/200);

        if (model.isPlayer1Turn() && !model.checkWinner()){
            Model.grid[row][col].drawX();
            if (model.checkWinner()){
                msg.setText("Player 1 wins");
            }

            model.setPlayer1Turn(false);
            ctr++;

            if (ctr < 8 && !model.player1Turn && !model.checkWinner()) {
                switch (level){
                    case 0:
                        model.level0();
                        break;
                }

                model.setPlayer1Turn(true);
                ctr++;
                if (model.checkWinner()){
                    msg.setText("Player 2 wins");
                }
            }
        }
        /*
        if (p1){
            Model.grid[row][col].drawX();
            p1 = false;
        } else {
            Model.grid[row][col].drawO();
            p1 = true;
        }
        System.out.println(p1);
        */

    }
    public static void wait(int ms) {
        try
        {
            Thread.sleep(ms);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }


}

