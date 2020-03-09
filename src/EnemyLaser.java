import java.awt.*;

public class EnemyLaser extends GameObject{
    private boolean fire = false;
    private int counter = 0;

    public EnemyLaser(float x, float y, ID id, Game game, Image[] image, int width, int height, Handler handler){
        super(x,y,id, game, image, width, height, handler);
        this.setVelY((float)0.5);
        this.setVelX(0);
    }

    public Rectangle getBounds(){
        if(fire){
            return new Rectangle((int) x, (int) y, 8, gameHeight);
        }else {
            return new Rectangle((int) x, (int) y, 4, 1);
        }
    }

    public void tick(){
        x += velX;
        y += velY;
        if(counter == 120){
            fire = true;
        } else if(counter == 240 || y >= gameHeight/2){
            handler.remove(this);
        }
        counter++;
        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);
            if(tempObject.getId() == ID.Player && this.getBounds().intersects(tempObject.getBounds())){
                handler.remove(this);
                HUD.health -= 5;
            }
        }
    }

    public void render(Graphics g){
        if(fire){
            g.drawImage(image[0], (int) x, (int) y, 8, gameHeight, null);
        } else {
            g.setColor(Color.red);
            g.fillRect((int) x, (int) y, 8, gameHeight);
        }
    }
}
