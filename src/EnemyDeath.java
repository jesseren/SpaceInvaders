import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.*;
import java.io.File;

public class EnemyDeath extends GameObject{
    private int counter = 0;
    private Clip clip;

    public EnemyDeath (float x, float y, ID id, Game game, Image[] image, int width, int height, Handler handler){
        super(x,y,id, game, image, width, height, handler);
        this.setVelX(0);
        this.setVelY((float) 0.5);
        try {
            File soundFile = new File("aliendeath.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch(Exception ex){

        }
    }

    public void tick(){
        if(counter == 10){
            handler.remove(this);
        }
        counter++;
    }

    public void render(Graphics g){
        g.drawImage(image[0], (int) x, (int) y, 24, 24, null);
    }
}
