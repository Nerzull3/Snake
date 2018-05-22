package Model;

import GUI.Panelv2;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

import static GUI.Constants.dotSize;

public class Enemy implements IEnemy {
    public int x;
    public int y;
    private int height;
    private int width;
    Buff buff;
    boolean existBuff;
    private Point courseEnemy = new Point(0, -1);
    private final static Point[] courses = {
            new Point(0,1),
            new Point(0,-1),
            new Point(1,0),
            new Point(-1,0)};

    void setCourseEnemy(Point course){ courseEnemy = course; }
    private int countSteps;

    Enemy(int x, int y, int height, int width){
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.countSteps = 1;
    }

    public void move(){
        if (countSteps % 5 == 0)
            courseEnemy = changeCourse();
        while (x + courseEnemy.x < 0 || y+courseEnemy.y >= height ||
                y + courseEnemy.y < 0 || x + courseEnemy.x >= width)
            courseEnemy = changeCourse();

        x += courseEnemy.x;
        y += courseEnemy.y;
        countSteps++;
    }

    private String checkOnCollisionEnemyWithFood(Enemy enemy, Buff buff) {
        if (enemy.x == buff.x && enemy.y == buff.y)
            switch (buff.getName()){
                case "poison":
                    return "remove";
                case "pomegranate":
                    return "eat and add snake";
                default:
                    return "eat and add pacman";
            }
        return "";
    }

    private int findPointMeeting(Snake playerSnake) {
        for (int i = 0; i < playerSnake.body.size(); i++)
            if (playerSnake.body.get(i).x == this.x && playerSnake.body.get(i).y == this.y)
                return i;
        // отсюда убрал строку game.gameOver = game.checkOnLittleSnakeSize();
        return playerSnake.body.size();
    }

    private Point changeCourse(){
        Random rnd = new Random();
        int i = rnd.nextInt(4);

        while ((courseEnemy.x == courses[i].x && courseEnemy.y == courses[i].x))
            i = rnd.nextInt(4);

        return courses[i];
    }

    @Override
    public String checkCollisions(Snake playerSnake) {
        int i = findPointMeeting(playerSnake);
        for (int j = playerSnake.body.size() - 1; j >= i; j--)
            playerSnake.body.remove(j);
        return checkOnCollisionEnemyWithFood(this, buff);
    }

    @Override
    public void getBuffCoords(Buff buff, boolean existBuff) {
        this.buff = buff;
        this.existBuff = existBuff;
    }

    @Override
    public void draw(Graphics g, int scoreHeight, Panelv2 panel) {
        Point course =  this.getCourseEnemy();
        Image enemyImage = new ImageIcon(getClass()
                .getResource("enemies/e" + course.x + course.y + ".png")).getImage();
        g.drawImage(enemyImage, this.x * dotSize, this.y * dotSize + scoreHeight, panel);
    }

    @Override
    public int getX() { return this.x; }

    @Override
    public int getY() { return this.y; }

    @Override
    public Point getCourseEnemy() { return courseEnemy; }

}
