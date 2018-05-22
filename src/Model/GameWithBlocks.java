package Model;

import GUI.Panelv2;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import static GUI.Constants.dotSize;

public class GameWithBlocks extends Game{
    public ArrayList<Point> walls = new ArrayList<>();
    private Image wallImage = new ImageIcon(getClass().getResource("wall.png")).getImage();

    public GameWithBlocks(int h, int w, int snakeLen){
        super(h,w,snakeLen);
        spawnWalls();
    }

    @Override
    public void refreshField(){
        super.refreshField();
        if (checkOnWall())
            gameOver = true;
    }

    private boolean checkOnWall()
    {
        return walls.contains(super.Snake.getHead());
    }

    private void spawnWalls() {
        Random rnd = new Random();
        for (int i = 0; i < super.getHeight(); i++){
            Point tempWall;
            do {
                int x = rnd.nextInt(super.getHeight());
                int y = rnd.nextInt(super.getWidth());
                tempWall = new Point(x, y);
            }
            while (super.Snake.body.contains(tempWall) ||
                    super.Buff.x==tempWall.x && super.Buff.y == tempWall.y);
            walls.add(tempWall);
        }
    }

    @Override
    public void Draw(Graphics g, int scoreHeight, Panelv2 panel) {
        for(Point wall : walls)
            g.drawImage(wallImage, wall.x * dotSize, wall.y * dotSize + scoreHeight, panel);
    }
}