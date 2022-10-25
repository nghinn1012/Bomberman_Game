// package uet.oop.bomberman.graphics;

// import javafx.scene.control.Label;
// import javafx.scene.layout.AnchorPane;
// import javafx.scene.text.Font;
// import uet.oop.bomberman.BombermanGame;

// import static javafx.scene.paint.Color.WHITE;

// public class JPANEL extends AnchorPane {
//    public Label labelLevel;

//    public Label labelTime;

//    public Label labelPoint;

//    public Label labelLives;

//    public JPANEL() {
//        labelLevel = new Label("LEVEL : "+ BombermanGame.level);
//        labelLevel.setLayoutX(50);
//        labelLevel.setLayoutY(1);
//        labelLevel.setFont(Font.font(18));
//        labelLevel.setTextFill(WHITE);

//        labelTime = new Label("TIME : "+ BombermanGame.time);
//        labelTime.setLayoutX(300);
//        labelTime.setLayoutY(1);
//        labelTime.setFont(Font.font(18));
//        labelTime.setTextFill(WHITE);

//        labelPoint = new Label("POINT : "+ BombermanGame.point);
//        labelPoint.setLayoutX(550);
//        labelPoint.setLayoutY(1);
//        labelPoint.setFont(Font.font(18));
//        labelPoint.setTextFill(WHITE);

//        labelLives = new Label("LIVES : "+ BombermanGame.lives);
//        labelLives.setLayoutX(850);
//        labelLives.setLayoutY(1);
//        labelLives.setFont(Font.font(18));
//        labelLives.setTextFill(WHITE);
//    }
//    public void setPanel() {
//        BombermanGame.ro.getChildren().addAll(labelLevel,labelTime,labelPoint,labelLives);
//    }

//    public void setLevel(int t) {
//        labelLevel.setText("LEVEL : " + t);
//    }

//    public void setTimes(int t) {
//        labelTime.setText("TIMES : "+t);
//    }

//    public void setPoint(int t) {
//        labelPoint.setText("POINT : "+t);
//    }

//    public void setLives(int t) {
//        labelLives.setText("LIVES : "+t);
//    }
// }

