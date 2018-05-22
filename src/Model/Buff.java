package Model;

import javax.swing.*;
import java.awt.*;
import java.util.*;


public class Buff {
    private final String name;
    final int countScore;
    final int timeLive;
    boolean isInvertion;
    public int x;
    public int y;
    private final Image Image;

    public String getName() { return name; }
    public Image getImage() { return Image; }

    public void setBuffCoords(int newX, int newY)
    {
        x = newX;
        y = newY;
    }

    Buff(String name, int countScore, int timeLive){
        this.name = name;
        this.countScore = countScore;
        this.timeLive = timeLive;
        this.x = 0;
        this.y = 0;
        this.Image = new ImageIcon(getClass().getResource( "fruits/" + name + ".png")).getImage();
        isInvertion = getInvertion();
    }

    private boolean getInvertion(){
        Random rnd = new Random();
        Boolean[] probability = new Boolean[10];
        for (int i = 0; i < probability.length - 8; i++)
            probability[i] = false;
        for (int i = probability.length - 8; i < probability.length; i++)
            probability[i] = true;

        return probability[rnd.nextInt(probability.length)];

    }
}
