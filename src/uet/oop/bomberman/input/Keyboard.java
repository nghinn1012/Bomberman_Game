package uet.oop.bomberman.input;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * Tiếp nhận và xử lý các sự kiện nhập từ bàn phím
 */
public class Keyboard {

    private boolean[] keys = new boolean[120]; 
    public boolean up, down, left, right, space, pause;
    public Keyboard() {

    }
    public void update() {
        left = keys[KeyCode.A.getCode()] || keys[KeyCode.LEFT.getCode()];
        right = keys[KeyCode.D.getCode()] || keys[KeyCode.RIGHT.getCode()];
        up = keys[KeyCode.W.getCode()] || keys[KeyCode.UP.getCode()];
        down = keys[KeyCode.S.getCode()] || keys[KeyCode.DOWN.getCode()];
        space = keys[KeyCode.SPACE.getCode()];

    }
}
