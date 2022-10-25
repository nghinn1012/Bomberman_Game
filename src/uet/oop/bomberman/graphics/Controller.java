package uet.oop.bomberman.graphics;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.stage.Stage;
import uet.oop.bomberman.BombermanGame;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private BombermanGame scene = new BombermanGame();
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void changeScreen(ActionEvent event){
        //BombermanGame.ai=0;
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        scene.start(stage);
    }

//    public void changeToAI(ActionEvent event) {
//        BombermanGame.ai=1;
//        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
//        scene.start(stage);
//    }
}
