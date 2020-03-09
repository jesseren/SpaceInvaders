import java.awt.*;
import java.lang.ref.PhantomReference;

public class BasicEnemy extends GameObject{
    private int counter = 0;
    private int bulletCount = 0;
    private int deathCounter = 0;
    private Image currentImage;
    private boolean end = false;

    public BasicEnemy (float x, float y, ID id, Game game, Image[] image, int width, int height, Handler handler){
        super(x,y,id, game, image, width, height, handler);
        this.health = 1;
        this.setVelX(0);
        this.setVelY((float) 0.5);
    }

    public Rectangle getBounds(){
        return new Rectangle((int) x, (int) y, 24, 12);
    }

    public void tick(){
        if(health == 0) {
            handler.add(new EnemyDeath(x,y,ID.AlienDeath,game,game.images.get(ID.AlienDeath),gameWidth,gameHeight,handler));
            handler.remove(this);
        }
        x += velX;
        y += velY;
        if(y >= gameHeight) handler.remove(this);
        if(counter <= 30){
            currentImage = image[0];
        } else if(counter > 30){
            currentImage = image[1];
            if(counter == 60) {
                counter = 0;
                bulletCount++;
                if(bulletCount == 2 && !end) {
                    handler.add(new EnemyBullet(x+8,y,ID.EnemyBullet,game,null,gameWidth,gameHeight,handler));
                    bulletCount = 0;
                }
            }
        }
        GameObject player = null;
        for(int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if(tempObject.getId() == ID.Player){
                player = handler.object.get(i);
                if(player.getBounds().intersects(this.getBounds())){
                    HUD.health -= 2;
                    handler.remove(this);
                }
            }
        }
        if(y>=gameHeight/2 && y <= gameHeight-100){
            end = true;
            this.setVelX((player.getX()-x)/100);
            this.setVelY((player.getY()-y)/100);
        }
        counter++;
    }

    public void render(Graphics g){
        g.drawImage(currentImage, (int) x, (int) y, 24, 24, null);
    }
}
