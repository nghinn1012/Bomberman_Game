package uet;

import javafx.application.Application;
import javafx.stage.Stage;

public class BombermanGame extends Application {
    
    public static void main(String[] args) {
        Application.launch(Game.class);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // TODO Auto-generated method stub
        try {
            Game.launch(Game.class);
        } catch(Exception e) {
			e.printStackTrace();
		}
        
    }

}
