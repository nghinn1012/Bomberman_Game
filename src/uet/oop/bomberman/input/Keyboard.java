package uet.oop.bomberman.input;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * Tiếp nhận và xử lý các sự kiện nhập từ bàn phím
 */
public class Keyboard {

    private boolean[] keys = new boolean[120]; //120 is enough to this game
    public boolean up, down, left, right, space, pause;
    //public boolean vk_a, vk_s, vk_d, vk_w, vk_j;
    public Keyboard() {

    }
    public void update() {
        up = keys[KeyCode.UP.getCode()] || keys[KeyCode.W.getCode()];
        down = keys[KeyCode.DOWN.getCode()] || keys[KeyCode.S.getCode()];
        left = keys[KeyCode.LEFT.getCode()] || keys[KeyCode.A.getCode()];
        right = keys[KeyCode.RIGHT.getCode()] || keys[KeyCode.D.getCode()];
        space = keys[KeyCode.SPACE.getCode()];

    }

    private void createListener() {
//        Controller.scene.setOnKeyPressed(keyEvent -> {
//            if (keyEvent.getCode() == KeyCode.LEFT) {
//                keys[keyEvent.getCode().getCode()] = true;
//            } else if (keyEvent.getCode() == KeyCode.RIGHT) {
//                keys[keyEvent.getCode().getCode()] = true;
//            } else if (keyEvent.getCode() == KeyCode.UP) {
//                keys[keyEvent.getCode().getCode()] = true;
//            } else if (keyEvent.getCode() == KeyCode.DOWN) {
//                keys[keyEvent.getCode().getCode()] = true;
//            }
//        });
//
//        Controller.scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
//            public void handle(KeyEvent keyEvent) {
//                if (keyEvent.getCode() == KeyCode.LEFT) {
//                    keys[keyEvent.getCode().getCode()] = false;
//                } else if (keyEvent.getCode() == KeyCode.RIGHT) {
//                    keys[keyEvent.getCode().getCode()] = false;
//                } else if (keyEvent.getCode() == KeyCode.UP) {
//                    keys[keyEvent.getCode().getCode()] = false;
//                } else if (keyEvent.getCode() == KeyCode.DOWN) {
//                    keys[keyEvent.getCode().getCode()] = false;
//                }
//            }
//        });
    }
}
