import matrix.BombEnemy;

public class Main {
    public static void main(String[] args) {
        BombEnemy obj = new BombEnemy();
        System.out.println(obj.maxKilledEnemies(new char[][]{{'0','E','0','0'},{'E','0','W','E'},{'0','E','0','0'}}));
    }
}

