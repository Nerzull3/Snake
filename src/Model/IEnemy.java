package Model;

import GUI.Panelv2;
import jdk.jshell.spi.ExecutionControl;

import java.awt.*;

public interface IEnemy {
    public void move();
    public String checkCollisions(Snake playerSnake);
    public void getBuffCoords(Buff buff, boolean existBuff);
    public void draw(Graphics g, int scoreHeight, Panelv2 panel);
    public int getX();
    public int getY();
    public Point getCourseEnemy();
}
