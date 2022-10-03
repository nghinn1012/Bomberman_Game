package uet.levels;

import static uet.Game.*;
import static uet.control.Menu.bombNumber;
import static uet.control.Menu.timeNumber;
import static uet.entities.animal.Bomber.swapKill;
import static uet.entities.block.Bomb.isBomb;
import static uet.entities.item.SpeedItem.speed;
import static uet.utility.SoundManager.isSoundDied;
import static uet.utility.SoundManager.isSoundTitle;

import javafx.scene.image.Image;
import uet.entities.animal.Animal;
import uet.entities.animal.Ballom;
import uet.entities.animal.Kondoria;
import uet.entities.animal.Oneal;
import uet.graphics.CreateMap;
import uet.graphics.Sprite;

public class Level2 {
    public Level2() {
        enemy.clear();
        block.clear();
        swapKill = 1;
        new CreateMap("res/levels/Level2.txt");
        player.setLife(true);
        player.setX(32);
        player.setY(32);
        speed = 1;
        isSoundDied = false;
        isSoundTitle = false;
        timeNumber = 120;
        bombNumber = 30;
        isBomb = 0;

        player.setImg(Sprite.player_right_2.getFxImage());
        Image transparent = new Image("images/transparent.png");
        authorView.setImage(transparent);

        Animal enemy1 = new Ballom(5, 5, Sprite.ballom_left1.getFxImage());
        Animal enemy2 = new Ballom(11, 9, Sprite.ballom_left1.getFxImage());
        enemy.add(enemy1);
        enemy.add(enemy2);

        Animal enemy3 = new Kondoria(1, 3, Sprite.kondoria_right1.getFxImage());
        Animal enemy4 = new Kondoria(1, 7, Sprite.kondoria_right1.getFxImage());
        Animal enemy5 = new Kondoria(1, 11, Sprite.kondoria_right1.getFxImage());
        enemy.add(enemy3);
        enemy.add(enemy4);
        enemy.add(enemy5);

        Animal enemy6 = new Oneal(7, 5, Sprite.oneal_right1.getFxImage());
        Animal enemy7 = new Oneal(19, 7, Sprite.oneal_right1.getFxImage());
        enemy.add(enemy6);
        enemy.add(enemy7);

        for (Animal animal : enemy) {
            animal.setLife(true);
        }
    }
}
