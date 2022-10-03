package uet.graphics;

import static uet.Game.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

import uet.entities.Entity;
import uet.entities.block.Brick;
import uet.entities.block.Grass;
import uet.entities.block.Portal;
import uet.entities.block.Wall;
import uet.entities.item.FlameItem;
import uet.entities.item.SpeedItem;

public class CreateMap {
    public CreateMap(String level) {
        System.out.println(System.getProperty("user.dir"));
        final File fileName = new File(level);
        try (FileReader inputFile = new FileReader(fileName)) {
            Scanner sc = new Scanner(inputFile);
            String line = sc.nextLine();

            StringTokenizer tokens = new StringTokenizer(line);
            _level = Integer.parseInt(tokens.nextToken());
            _height = Integer.parseInt(tokens.nextToken());
            _width = Integer.parseInt(tokens.nextToken());

            while (sc.hasNextLine()) {
                idObjects = new int[_width][_height];
                listKill = new int[_width][_height];
                for (int i = 0; i < _height; ++i) {
                    String lineTile = sc.nextLine();
                    StringTokenizer tokenTile = new StringTokenizer(lineTile);

                    for (int j = 0; j < _width; j++) {
                        int s = Integer.parseInt(tokenTile.nextToken());
                        Entity entity;
                        switch (s) {
                            case 1:
                                entity = new Portal(j, i, Sprite.grass.getFxImage());
                                s = 0;
                                break;
                            case 2:
                                entity = new Wall(j, i, Sprite.wall.getFxImage());
                                break;
                            case 3:
                                entity = new Brick(j, i, Sprite.brick.getFxImage());
                                break;
                            case 6:
                                entity = new SpeedItem(j, i, Sprite.brick.getFxImage());
                                break;
                            case 7:
                                entity = new FlameItem(j, i, Sprite.brick.getFxImage());
                                break;
                            default:
                                entity = new Grass(j, i, Sprite.grass.getFxImage());
                        }
                        idObjects[j][i] = s;
                        block.add(entity);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
