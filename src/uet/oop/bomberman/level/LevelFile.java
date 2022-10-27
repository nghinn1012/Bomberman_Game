package uet.oop.bomberman.level;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.StringTokenizer;

public class LevelFile extends Level {
    public void insertFile(String path) {
        try {
            URL link = LevelFile.class.getResource("/levels/Level" + path + ".txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(link.openStream()));
            String text = reader.readLine();
            StringTokenizer str = new StringTokenizer(text);
            level_ = Integer.parseInt(str.nextToken());
            height_ = Integer.parseInt(str.nextToken());
            width_ = Integer.parseInt(str.nextToken());
            lines = new String[height_];
            for (int i = 0; i < height_; i++) {
                lines[i] = reader.readLine().substring(0,width_);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}