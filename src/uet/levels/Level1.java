package uet.levels;

import static uet.Game.*;
import static uet.control.Menu.bombNumber;
import static uet.control.Menu.timeNumber;
import static uet.entities.animal.Bomber.swapKill;
import static uet.entities.block.Bomb.isBomb;
import static uet.entities.block.Bomb.powerBomb;
import static uet.entities.item.SpeedItem.speed;
import static uet.utility.SoundManager.isSoundDied;
import static uet.utility.SoundManager.isSoundTitle;

import javafx.scene.image.Image;
import uet.entities.animal.Animal;
import uet.entities.animal.Ballom;
import uet.entities.animal.Oneal;
import uet.graphics.CreateMap;
import uet.graphics.Sprite;

public class Level1 {
    public Level1() {
        enemy.clear();
        block.clear();
        swapKill = 1;
        powerBomb = 0;
        new CreateMap("res/levels/Level1.txt");
        player.setLife(true);
        player.setX(32);
        player.setY(32);
        isSoundDied = false;
        isSoundTitle = false;
        timeNumber = 120;
        bombNumber = 20;
        isBomb = 0;
        speed = 1;

        player.setImg(Sprite.player_right_2.getFxImage());
        Image transparent = new Image("images/transparent.png");
        authorView.setImage(transparent);

        Animal enemy1 = new Ballom(4, 4, Sprite.ballom_left1.getFxImage());
        Animal enemy2 = new Ballom(9, 9, Sprite.ballom_left1.getFxImage());
        Animal enemy3 = new Ballom(22, 6, Sprite.ballom_left1.getFxImage());
        enemy.add(enemy1);
        enemy.add(enemy2);
        enemy.add(enemy3);

        Animal enemy4 = new Oneal(7, 6, Sprite.oneal_right1.getFxImage());
        Animal enemy5 = new Oneal(13, 8, Sprite.oneal_right1.getFxImage());
        enemy.add(enemy4);
        enemy.add(enemy5);

        for (Animal animal : enemy) {
            animal.setLife(true);
        }
    }
}
