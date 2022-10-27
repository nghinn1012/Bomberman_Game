package uet.oop.bomberman.level;
public  class Level {
    protected int level_,width_, height_;
    public String[] lines;
    Level() {
        level_ = 0;
        width_=0;
        height_=0;
    }
    Level(int level_,int width_,int height_) {
        this.level_ = level_;
        this.width_ = width_;
        this.height_ = height_;
    }

    public int getLevel() {
        return level_;
    }

    public void setLevel(int level_) {
        this.level_ = level_;
    }

    public int getWidth() {
        return width_;
    }

    public void setWidth(int width_) {
        this.width_ = width_;
    }

    public int getHeight() {
        return height_;
    }

    public void setHeight(int height_) {
        this.height_ = height_;
    }

}

