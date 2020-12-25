import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;


public class Controller {
    @FXML
    AnchorPane box = new AnchorPane();
    @FXML
    Label msg = new Label();
    @FXML
    Button PlayButton = new Button();
    @FXML
    Button ExitButton = new Button();
    @FXML
    Button Level0 = new Button();
    @FXML
    Button Level1 = new Button();
    @FXML
    Button Level2 = new Button();
    @FXML
    Button PlayAgain = new Button();

    Model model = new Model();

    int level;

    int ctr = 0;
    public void createGrid(){ // to create 3x3 grid
        int code = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Model.grid[i][j] =  new Tile();
                Model.grid[i][j].setTranslateY(i*200);
                Model.grid[i][j].setTranslateX(j*200);
                Model.grid[i][j].setCode(code); // to be able to generate code 0-9 later for random
                code++;
                box.getChildren().add(Model.grid[i][j]);
            }
        }
        msg.setText("Your turn!");
        System.out.println(level);
        PlayAgain.setVisible(false);
    }

    @FXML
    public void handleClick(MouseEvent me) {
        double posX = me.getX();
        double posY = me.getY();
        int row = (int) (posY/200);
        int col = (int) (posX/200);

        if (model.isPlayer1Turn() && model.checkWinner() == 0 && Model.grid[row][col].getType().equalsIgnoreCase("blank")){
           if (Model.gameCount % 2 != 0)
               Model.grid[row][col].drawX();
           else
               Model.grid[row][col].drawO();

            if (model.checkWinner() == 1){
                msg.setText("Player X wins");
            }

            model.setPlayer1Turn(false);

            ctr++;

            if (ctr < 8 && !model.player1Turn && model.checkWinner() == 0) {
                switch (level){
                    case 0:
                        model.level0();
                        break;
                    case 2:
                        System.out.println("minimax");
                        model.level2();
                        break;
                }

                model.setPlayer1Turn(true);
                ctr++;

            }
        }
        System.out.println("check winner: " + model.checkWinner());
        if (model.checkWinner() == -1){
            msg.setText("Player O wins");
        }
        if (ctr == 9 && model.checkWinner() == 0){
            msg.setText("TIE!!");

        }
        if (ctr == 9 || model.checkWinner() != 0){
            PlayAgain.setVisible(true);
        }

    }

    @FXML
    public void handleAction(ActionEvent e) throws IOException {
        Stage stage;
        Parent root;
        Scene scene;

        if (e.getSource() == PlayButton){
            stage = (Stage) PlayButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("LevelView.fxml"));
            root = (Parent) loader.load();
            Controller controller = (Controller) loader.getController();
            controller.createGrid();

            scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Tic Tac Toe");
            stage.show();
        } else if (e.getSource() == ExitButton){
            stage = (Stage) ExitButton.getScene().getWindow();
            stage.close();
        } else if (e.getSource() == Level0){
            level = 0;
            stage = (Stage) Level0.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("TicTacToeView.fxml"));
            root = (Parent) loader.load();
            Controller controller = (Controller) loader.getController();
            controller.createGrid();

            scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Tic Tac Toe");
            stage.show();

            Model.gameCount++;
        } else if (e.getSource() == Level1){

        } else if (e.getSource() == Level2){
            level = 2;

            stage = (Stage) Level2.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("TicTacToeView.fxml"));
            root = (Parent) loader.load();
            Controller controller = (Controller) loader.getController();
            controller.createGrid();

            scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Tic Tac Toe");
            stage.show();

            Model.gameCount++;
        } else if (e.getSource() == PlayAgain){
            msg.setText("");
            Model.gameCount++;
            System.out.println(Model.gameCount);
            ctr = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    Model.grid[i][j].setInitial();
                }
            }
            PlayAgain.setVisible(false);

            if (Model.gameCount %2 == 0){
                model.bestMove();
                ctr++;
            } else {
                model.setPlayer1Turn(true);
            }

        }
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

