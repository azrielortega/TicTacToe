import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
	launch (args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenuView.fxml"));
        Parent root = (Parent) loader.load();
        Controller controller = (Controller) loader.getController();
        controller.createGrid();

        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.setScene(new Scene(root, 608, 730));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
