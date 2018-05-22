package Model;

import GUI.Constants;
import GUI.Panelv2;

import javax.swing.*;
import java.awt.*;

import static GUI.Constants.dotSize;

public class EnemySnake extends Snake implements IEnemy {

    private Buff buff;
    private boolean existBuff;
    private int x;
    private int y;

    public EnemySnake(int x, int y, int size, Point newCourse) {
        super(x, y, size, newCourse);
        this.x = x;
        this.y = y;
    }

    public void getBuffCoords(Buff buff, boolean existBuff){
        this.buff = buff;
        this.existBuff = existBuff;
    }

    public void move(){
        Point delta = getHead();
        delta = new Point(buff.x - delta.x , buff.y - delta.y);
        if(delta.x != 0)
            SetCourse(new Point((int)Math.signum(delta.x), 0));
        else if (delta.y !=0)
            SetCourse(new Point(0, (int)Math.signum(delta.y)));
        super.move();
        this.fixPositionSnakeHead(this.getHead(), super.width, super.height);
    }

    private boolean checkEatBuff(Buff buff) {
        return (getHead().x == buff.x && getHead().y == buff.y);
    }

    private boolean checkOnLittleSnakeSize(){
        return body.size() < Constants.minSnakeSize;
    }

    private boolean CheckOnCollisionWithPlayer(Snake playerSnake){
        Point enemyHead = getHead();
        for (Point element:playerSnake.body)
            if (enemyHead.equals(element))
                return true;
        return false;
    }

    @Override
    public String checkCollisions(Snake playerSnake) {
        if (playerSnake.CheckOnCollisionWithEnemySnake(this))
            return "gameover";
        if (this.CheckOnCollisionWithPlayer(playerSnake))
            return "remove";
        if (this.checkEatBuff(buff) && existBuff) {
            this.EatBuff(buff);
            if (this.checkOnLittleSnakeSize())
                return "remove and eat";
            return "eat";
        }
        return "";
    }

    @Override
    boolean CheckOnEatSelf(){
        Point snakeHead = getHead();
        Point course = getpCourseHead();
        for (int i = 1; i < body.size(); i++)
            if (snakeHead.x+ course.x == body.get(i).x && snakeHead.y == body.get(i).y)
                return true;
        return false;
    }

    @Override
    public void draw(Graphics g, int scoreHeight, Panelv2 panel) {
        Point course = this.getpCourseHead();
        Point courseTail = this.getNextCourseTail();
        Image snakeImageHead = new ImageIcon(getClass()
                .getResource("spritesEnemySnake/heads/h" + course.x + course.y + ".png"))
                .getImage();
        Image snakeImageBody = new ImageIcon(getClass()
                .getResource("spritesEnemySnake/body/snakebody.png")).getImage();
        Image snakeImageTail = new ImageIcon(getClass()
                .getResource("spritesEnemySnake/tails/t" + courseTail.x + courseTail.y + ".png"))
                .getImage();

        g.drawImage(snakeImageHead, this.getHead().x * dotSize,
                this.getHead().y * dotSize + scoreHeight, panel);

        for (int i = 1; i < this.body.size() - 1; i++)
            if (!(this.body.get(i + 1).x == -1 && this.body.get(i + 1).y == -1))
                g.drawImage(snakeImageBody,
                        this.body.get(i).x * dotSize,
                        this.body.get(i).y * dotSize + scoreHeight,
                        panel);
        g.drawImage(snakeImageTail,
                this.getTail().x * dotSize,
                this.getTail().y * dotSize + scoreHeight, panel);
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public Point getCourseEnemy() {
        return this.getpCourseHead();
    }
}
