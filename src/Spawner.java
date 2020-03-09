import java.lang.ref.PhantomReference;
import java.util.Random;

public class Spawner {
    private int spawnCount = 0;
    private int timer;
    private int left;
    private Game game;
    private Random r = new Random();

    public Spawner(int left, Game game){
        this.left = left;
        this.game = game;
    }

    public void tick(Handler handler){
        timer++;
        if(timer == 240){
            int type = r.nextInt(3);
            if(type == 0) {
                for (int i = 0; i < 10; i++) {
                    handler.add(new BasicEnemy(left, game.top, ID.BasicEnemy, game, game.images.get(ID.BasicEnemy), game.width, game.height, handler));
                    left += (game.width - game.left) / 10;
                }
            } else if(type == 1){
                for (int i = 0; i < 10; i++) {
                    handler.add(new StrongEnemy(left, game.top, ID.StrongEnemy, game, game.images.get(ID.StrongEnemy), game.width, game.height, handler));
                    left += (game.width - game.left) / 10;
                }
            } else if(type == 2){
                for (int i = 0; i < 10; i++) {
                    handler.add(new LaserEnemy(left, game.top, ID.LaserEnemy, game, game.images.get(ID.LaserEnemy), game.width, game.height, handler));
                    left += (game.width - game.left) / 10;
                }
            }
            timer = 0;
            left = game.left;
        }
    }
}
