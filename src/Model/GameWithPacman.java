package Model;

import GUI.Panelv2;

import java.awt.*;
import java.util.ArrayList;


public class GameWithPacman extends Game {
    public ArrayList<IEnemy> enemies = new ArrayList<>();

    public GameWithPacman(int height, int width, int snakeLen) {
        super(height, width, snakeLen);
        enemies.add(new Enemy(3, 4, getHeight(), getWidth()));
        enemies.add(new Enemy(7, 7, getHeight(), getWidth()));
//        enemies.add(new Enemy(1, 1, getHeight(), getWidth()));
    }

    @Override
    public void refreshField() {
        if (!gameOver)
        {
            SpawnFood();

            ArrayList<IEnemy> tempEnemies = new ArrayList<>(); // создал временный лист для идеальной обработки
            tempEnemies.addAll(enemies);

            for (IEnemy enemy : enemies) {
                enemy.getBuffCoords(Buff, existBuff);

                if (!gameOver) {
                    switch (enemy.checkCollisions(this.Snake)) {
                        case "gameover":
                            this.gameOver = true;
                            break;
                        case "remove":
                            tempEnemies.remove(enemy);
                            break;
                        case "eat":
                            existBuff = false;
                            break;
                        case "remove and eat":
                            tempEnemies.remove(enemy);
                            existBuff = false;
                            break;
                        case "eat and add snake":
                            EnemySnake enemySnake = new EnemySnake(enemy.getX(), enemy.getY(), 3, enemy.getCourseEnemy());
                            tempEnemies.add(enemySnake);
                            enemySnake.width = this.width; // пришлось так сделать, чтобы инициализировать в классе EnemySnake это поле
                            enemySnake.height = this.height; // пришлось так сделать, чтобы инициализировать в классе EnemySnake это поле
                            tempEnemies.remove(enemy);
                            existBuff = false;
                            break;
                        case "eat and add pacman":
                            tempEnemies.add(new Enemy(enemy.getX(), enemy.getY(), height, width));
                            existBuff = false;
                        default:
                            break;
                    }
                }
            }
            enemies = tempEnemies;
            super.refreshField();

            for (IEnemy enemy : enemies) {
                enemy.getBuffCoords(Buff, existBuff);
                enemy.move();
            }
        }
    }

    @Override
    public void Draw(Graphics g, int scoreHeight, Panelv2 panel) {
        for (IEnemy enemy : enemies)
            enemy.draw(g, scoreHeight, panel);
    }
}