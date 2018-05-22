package Model;

import GUI.Constants;
import GUI.Panelv2;

import java.awt.*;
import java.util.*;

public class Game {
    public Buff Buff;
    public Snake Snake;
    public boolean gameOver = false;
    public int Score;
    public ArrayList<Point> walls = new ArrayList<>();
    private boolean firstTimeFruitAppear = true;
    private static Buff[] Buffs = new Buff[]{
            new Buff("poison", -1, 20),
            new Buff("apple", 1, 20),
            new Buff("banan", 3, 15),
            new Buff("grapes", 5,15),
            new Buff("pomegranate", 7, 10)
    };
    final int height;
    final int width;
    private int timeLiveBuff;
    boolean existBuff;

    public Game(int h, int w, int snakeLength){
        this.Snake = new Snake(snakeLength);
        this.existBuff = false;
        this.height = h;
        this.width = w;
        SpawnFood();
    }

    public void refreshField(){
        if (!(checkOnLittleSnakeSize() || Snake.CheckOnEatSelf())) { // перенес сюда проверку
            timeLiveBuff--;
            if (timeLiveBuff <= 0)
                existBuff = false;

            Snake.move();
            eatBuff();
            this.Snake.fixPositionSnakeHead(Snake.getHead(), width, height);
            SpawnFood();

            //if (checkOnLittleSnakeSize() || Snake.CheckOnEatSelf()) // эту проверку
            //    gameOver = true;
        }
        else
            gameOver = true;
    }

    public void Print(){
        for (int i = 0; i < height; i++)
        {
            for (int j = 0; j < width; j++){
                Point cp = new Point(j,i);
                if (cp.x == Buff.x && cp.y == Buff.y){
                    switch (Buff.getName()){
                        case "apple": System.out.print("a"); break;
                        case "poison": System.out.print("p"); break;
                        case "banan": System.out.print("b"); break;
                        case "grapes": System.out.print("g"); break;
                        default:System.out.print("o"); break;
                    }
                }
                else{
                    if (Snake.body.contains(cp)){
                        if (cp.x == Snake.getHead().x && cp.y == Snake.getHead().y)
                            System.out.print("S");
                        else
                            System.out.print("s");}
                    else{
                        if (walls.contains(cp))
                            System.out.print("#");
                        else System.out.print(".");
                    }
                }
            }
            System.out.println();
        }
    }

    void SpawnFood(){
        if (firstTimeFruitAppear) {
            Buff = new Buff("pomegranate", 5, 8);
            Buff.x = 3;
            Buff.y = 3;
            existBuff = true;
            timeLiveBuff = Buff.timeLive;
            firstTimeFruitAppear = false;
        }
        else {
            Random rnd = new Random();
            int n = rnd.nextInt(Buffs.length);
            while (!existBuff){
                int x = rnd.nextInt(height);
                int y = rnd.nextInt(width);
                Point tempBuff = new Point(x, y);
                if (!Snake.body.contains(tempBuff) && !walls.contains(tempBuff))
                {
                    Buff = Buffs[n];
                    Buff.x = tempBuff.x;
                    Buff.y = tempBuff.y;
                    existBuff = true;
                    timeLiveBuff = Buff.timeLive;
                }
            }
        }
    }

    private void eatBuff(){
        if (Snake.getHead().x == Buff.x && Snake.getHead().y == Buff.y)
        {
            Score += Buff.countScore;
            Snake.EatBuff(Buff);
            existBuff = false;
        }
    }

    boolean checkOnLittleSnakeSize()
    {
        return Snake.body.size() < Constants.minSnakeSize;
    }

    public int getHeight() { return height; }
    public int getWidth() { return width; }

    public void Draw(Graphics g, int scoreHeight, Panelv2 panel) {}
}
