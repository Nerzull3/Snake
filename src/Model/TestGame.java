//package Model;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.experimental.runners.Enclosed;
//import org.junit.runner.RunWith;
//
//import static org.junit.Assert.*;
//import java.awt.*;
//
//
//@RunWith(Enclosed.class)
//public class TestGame {
//    public static class TestsGameBase {
//        Game game = new Game(20, 21, 4);
//
//        @Test
//        public void test_GameModel_initialization() {
//            assertEquals(game.getHeight(), 20);
//            assertEquals(game.getWidth(), 21);
//            assertEquals(game.Snake.body.size(), 4);
//        }
//
//        @Test(expected = IllegalArgumentException.class)
//        public void test_wrong_initalization() {
//            Game game = new Game(-1, -5, 3);
//        }
//
//        @Test
//        public void test_Snake_not_null() {
//            assertNotNull(game.Snake);
//        }
//
//        @Test
//        public void test_Score_initialization() {
//            assertEquals(game.Score, 0);
//        }
//
//        @Test
//        public void test_Buff_not_null() {
//            assertNotNull(game.Buff);
//        }
//
//        @Test
//        public void test_little_snake_size_gameover() {
//            while (game.Snake.body.size() > 1) {
//                game.Snake.body.pop();
//            }
//            game.refreshField();
//            assertTrue(game.gameOver);
//        }
//
//        @Test
//        public void test_snake_head_spawns_not_in_corners() {
//            assertNotEquals("Snake is in upper left corner", game.Snake.getHead(), new Point(0, 0));
//            assertNotEquals("Snake is in upper right corner", game.Snake.getHead(), new Point(game.getWidth() - 1, 0));
//            assertNotEquals("Snake is in lower left corner", game.Snake.getHead(), new Point(0, game.getHeight() - 1));
//            assertNotEquals("Snake is in lower right corner", game.Snake.getHead(), new Point(game.getWidth() - 1, game.getHeight() - 1));
//        }
//
//        @Test
//        public void test_spawn_snake_head_futher_than_3_cells_to_boundaries() {
//
//            int headX = game.Snake.getHead().x;
//            int headY = game.Snake.getHead().y;
//
//
//            if (2 > headX && headX > game.getWidth() - 3 && 2 > headY && headY > game.getHeight() - 3)
//                fail("Snake is too close to the boundaries");
//        }
//
//
//        @Test
//        public void test_CheckOnEatSelf_gameover() {
//            Game game = new Game(15, 15, 6);
//            if (game.Snake.getpCourseHead().equals(Course.UP))
//                game.Snake.SetCourse(Course.LEFT);
//
//            game.Snake.SetCourse(Course.DOWN);
//            game.refreshField();
//            game.Snake.SetCourse(Course.LEFT);
//            game.refreshField();
//            game.Snake.SetCourse(Course.UP);
//            game.refreshField();
//            game.Snake.SetCourse(Course.RIGHT);
//            game.refreshField();
//
//            assertTrue(game.gameOver);
//        }
//
//        @Test
//        public void test_correct_eating() {
//            Game game = new Game(6, 6, 3);
//            game.Snake.SetCourse(Course.DOWN);
//            game.Buff.x = game.Snake.getHead().x + game.Snake.getpCourseHead().x;
//            game.Buff.y = game.Snake.getHead().y + game.Snake.getpCourseHead().y;
//            game.Buff.isInvertion = false;
//            int sLength = game.Snake.body.size();
//            int prevScore = game.Score;
//            game.refreshField();
//
//            assertFalse(new Point(game.Buff.x, game.Buff.y) ==
//                    new Point(game.Snake.getHead().x, game.Snake.getHead().y));
//            assertNotEquals(game.Snake.body.size(), sLength);
//            assertNotEquals(game.Score, prevScore);
//        }
//
//        @Test
//        public void test_reverse_direction_DOWN() {
//            game.Snake = new Snake(5, 5, 3, Course.DOWN);
//            Point original = game.Snake.getpCourseHead();
//
//            game.refreshField();
//            game.Snake.SetCourse(Course.UP);
//
//            assertEquals("The course should not change on the opposite", game.Snake.getpCourseHead(), original);
//        }
//
//        @Test
//        public void test_reverse_direction_UP() {
//            game.Snake = new Snake(5, 5, 3, Course.UP);
//            Point original = game.Snake.getpCourseHead();
//
//            game.refreshField();
//            game.Snake.SetCourse(Course.DOWN);
//
//            assertEquals("The course should not change on the opposite", game.Snake.getpCourseHead(), original);
//        }
//
//        @Test
//        public void test_reverse_direction_RIGHT() {
//            game.Snake = new Snake(5, 5, 3, Course.RIGHT);
//            Point original = game.Snake.getpCourseHead();
//
//            game.refreshField();
//            game.Snake.SetCourse(Course.LEFT);
//
//            assertEquals("The course should not change on the opposite", game.Snake.getpCourseHead(), original);
//        }
//
//        @Test
//        public void test_reverse_direction_LEFT() {
//            game.Snake = new Snake(5, 5, 3, Course.LEFT);
//            Point original = game.Snake.getpCourseHead();
//
//            game.refreshField();
//            game.Snake.SetCourse(Course.RIGHT);
//
//            assertEquals("The course should not change on the opposite", game.Snake.getpCourseHead(), original);
//        }
//
//        @Test
//        public void test_vertical_out_of_bounds() {
//            Game game = new Game(5, 5, 2);
//            game.Snake.SetCourse(Course.RIGHT);
//            game.refreshField();
//            game.Snake.SetCourse(Course.DOWN);
//            while (game.Snake.getHead().y != game.getHeight() - 1)
//                game.refreshField();
//            game.refreshField();
//            assertFalse(game.gameOver);
//        }
//
//        @Test
//        public void test_horizontal_out_of_bounds() {
//            game.Snake.SetCourse(Course.LEFT);
//
//            while (game.Snake.getHead().x != 0)
//                game.refreshField();
//            game.refreshField();
//
//            assertFalse(game.gameOver);
//        }
//
//        @Test
//        public void test_Snake_move_after_refreshing() {
//            Game game = new Game(5, 5, 3);
//            Point prevHead = game.Snake.getHead();
//            game.refreshField();
//            assertNotEquals(prevHead, game.Snake.getHead());
//            Point suggestedPoint = new Point(prevHead.x + game.Snake.getpCourseHead().x, prevHead.y + game.Snake.getpCourseHead().y);
//            assertEquals(game.Snake.getHead(), suggestedPoint);
//        }
//
//        @Test
//        public void test_snake_getTail_basic() {
//            Snake snake = new Snake(2, 2, 5, new Point(2, 2));
//            assertEquals(snake.getTail(), snake.body.get(snake.body.size() - 1));
//        }
//
//        @Test
//        public void test_snake_getTail_with_minus_one() {
//            Snake snake = new Snake(2, 2, 5, new Point(2, 2));
//            snake.body.add(new Point(-1, -1));
//            assertEquals(snake.getTail(), snake.body.get(snake.body.size() - 2));
//        }
//
//        @Test
//        public void test_snake_getTail_course_1() {
//            Snake snake = new Snake(2, 2, 3, new Point(2, 2));
//
//            assertEquals(snake.getNextCourseTail(), new Point(-1, 0));
//        }
//
//        @Test
//        public void test_on_invertion_of_snake() {
//            game = new Game(2, 2, 2);
//            game.Snake.SetCourse(Course.LEFT);
//
//            do {
//                game.existBuff = false;
//                game.SpawnFood();
//            }
//            while (game.Buff.getName().equals("poison"));
//            game.Buff.x = 0;
//            game.Buff.y = 0;
//            game.Buff.isInvertion = true;
//
//            game.refreshField();
//            game.refreshField();
//
//            assertEquals(new Point(1, 0), game.Snake.getpCourseHead());
//            assertEquals(new Point(1, 0), game.Snake.getHead());
//            assertEquals(new Point(1, 0), game.Snake.getNextCourseTail());
//            assertEquals(new Point(0, 0), game.Snake.getTail());
//            assertEquals(0, game.Score);
//        }
//    }
//
//    public static class TestGameWithBlocks{
//        @Test
//        public void test_snake_bump_in_walls_gameover() {
//            GameWithBlocks game = new GameWithBlocks(10, 10, 4);
//            game.Snake.SetCourse(Course.DOWN);
//            game.walls.add(new Point(game.Snake.getHead().x, game.Snake.getHead().y + 1));
//
//            game.refreshField();
//
//            assertTrue(game.gameOver);
//        }
//
//        @Test
//        public void test_random_respawning_buff() {
//            GameWithBlocks game = new GameWithBlocks(20, 20, 4);
//            Point prevBuffPoint = new Point(game.Buff.x, game.Buff.y);
//            game.walls.add(new Point(game.Buff.x, game.Buff.y));
//            game.existBuff = false;
//
//            game.refreshField();
//            Point newBuffPoint = new Point(game.Buff.x, game.Buff.y);
//
//            assertTrue(game.existBuff);
//            assertNotEquals(newBuffPoint, prevBuffPoint);
//        }
//
//        @Test
//        public void test_block_mode_has_walls() {
//            GameWithBlocks game = new GameWithBlocks(10, 10, 3);
//
//            assertNotNull(game.walls);
//            assertTrue(game.walls.size() > 0);
//        }
//    }
//
//    public static class TestGameWithPacman{
//        private GameWithPacman game;
//        private Enemy enemy_1;
//        private Enemy enemy_2;
//
//        @Before
//        public void test_data(){
//            game = new GameWithPacman(5,5,3);
//            game.Snake.SetCourse(new Point(0,0));
//
//            enemy_1 = game.enemies.get(0);
//            enemy_1.setCourseEnemy(new Point(-1,0));
//            enemy_1.x = 2;
//            enemy_1.y = 2;
//
//            enemy_2 = game.enemies.get(1);
//            enemy_2.setCourseEnemy(new Point(1,0));
//            enemy_2.x = 3;
//            enemy_2.y = 3;
//        }
//
//        @Test
//        public void test_enemy_change_course() {
//            enemy_1.x = 1;
//
//            enemy_1.setCourseEnemy(new Point(1, 0));
//            Point oldCourse = enemy_1.getCourseEnemy();
//            enemy_1.move(3, 3);
//            assertEquals(oldCourse, enemy_1.getCourseEnemy());
//            enemy_1.move(3, 3);
//            assertNotEquals(oldCourse, enemy_1.getCourseEnemy());
//        }
//
//        @Test
//        public void test_enemy_collision_snake_len3() {
//            game.Snake.SetCourse(new Point(0,0));
//
//            game.refreshField();
//            game.refreshField();
//
//            assertEquals(2, game.enemies.size());
//            assertEquals(2, game.Snake.body.size());
//        }
//
//        @Test
//        public void test_enemy_collision_snake_len2() {
//            game.Snake = new Snake(2);
//            game.Snake.SetCourse(new Point(0,0));
//
//            enemy_1.y = 1;
//
//            game.refreshField();
//            game.refreshField();
//
//            assertEquals(1, game.Snake.body.size());
//            assertEquals(true, game.gameOver);
//        }
//
//        @Test
//        public void test_enemy_collision_snake_len5() {
//            game.Snake = new Snake(5);
//            game.Snake.SetCourse(new Point(0,0));
//
//            game.refreshField();
//            game.refreshField();
//
//            assertEquals(2, game.Snake.body.size());
//            assertEquals(false, game.gameOver);
//        }
//
//        @Test
//        public void test_enemy_collision_head_snake() {
//            game.Snake.SetCourse(new Point(0,0));
//
//            enemy_1.y = 0;
//
//            game.refreshField();
//            game.refreshField();
//
//            assertEquals(0, game.Snake.body.size());
//            assertEquals(true, game.gameOver);
//        }
//
//        @Test
//        public void test_enemy_collision_moving_snake() {
//            game.Snake = new Snake(5);
//            game.Snake.SetCourse(new Point(1,0));
//
//            game.refreshField();
//            game.refreshField();
//
//            assertEquals(3, game.Snake.body.size());
//            assertEquals(false, game.gameOver);
//        }
//
//        @Test
//        public void test_enemies_collision_snake_simultaneously() {
//            game.Snake = new Snake(5);
//            game.Snake.SetCourse(new Point(0,0));
//
//            enemy_1.x = 3;
//            enemy_1.y = 3;
//
//            enemy_2.setCourseEnemy(new Point(-1,0));
//            enemy_2.x = 2;
//            enemy_2.y = 2;
//
//            game.refreshField();
//            game.refreshField();
//
//            assertEquals(2, game.Snake.body.size());
//            assertEquals(false, game.gameOver);
//        }
//
//        @Test
//        public void test_collision_enemy_with_fruit() {
//            game = new GameWithPacman(2,2,2);
//            do {
//                game.existBuff = false;
//                game.SpawnFood();
//            }
//            while (game.Buff.getName().equals("poison"));
//            game.Buff.x = 0;
//            game.Buff.y = 0;
//
//            game.enemies.remove(1);
//            enemy_1 = game.enemies.get(0);
//            enemy_1.setCourseEnemy(new Point(0,-1));
//            enemy_1.x = 0;
//            enemy_1.y = 1;
//
//            assertEquals(1, game.enemies.size());
//            game.refreshField();
//            assertEquals(2, game.enemies.size());
//            Enemy enemy_3 = game.enemies.get(1);
//            assertEquals(new Point(0, -1), enemy_3.getCourseEnemy());
//            assertEquals(0, enemy_3.x);
//            assertEquals(0, enemy_3.y);
//        }
//
//        @Test
//        public void teat_collision_enemy_with_poison() {
//            game = new GameWithPacman(2,2,2);
//            do {
//                game.existBuff = false;
//                game.SpawnFood();
//            }
//            while (!game.Buff.getName().equals("poison"));
//            game.Buff.x = 0;
//            game.Buff.y = 0;
//
//            game.enemies.remove(1);
//            enemy_1 = game.enemies.get(0);
//            enemy_1.setCourseEnemy(new Point(0,-1));
//            enemy_1.x = 0;
//            enemy_1.y = 1;
//
//            assertEquals(1, game.enemies.size());
//            game.refreshField();
//            assertEquals(0, game.enemies.size());
//        }
//    }
//}
