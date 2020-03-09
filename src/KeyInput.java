import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import static java.awt.event.KeyEvent.*;

public class KeyInput extends KeyAdapter {
    private boolean[] keyDown = new boolean[4];
    private Handler handler;
    private Game game;
    private BufferedImage laser;
    private float vel = 3;
    private Clip clip;

    public KeyInput(Handler handler, Game game){
        this.handler = handler;
        this.game = game;
        this.laser = game.loadImage("laser.png");
        for(int i = 0; i < keyDown.length; i++){
            keyDown[i] = false;
        }
        try {
            File soundFile = new File("laser.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            clip = AudioSystem.getClip();
            clip.open(audioIn);
        } catch(Exception ex){

        }
    }

    public void keyPressed(KeyEvent e) {
        for(int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if(tempObject.getId() == ID.Player){
                int key = e.getKeyCode();
                 if (key == VK_A) {
                    tempObject.setVelX(-vel);
                    keyDown[2] = true;
                } else if (key == VK_D) {
                    tempObject.setVelX(vel);
                    keyDown[3] = true;
                }
            }
        }
    }

    public void keyReleased(KeyEvent e){
        for(int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if(tempObject.getId() == ID.Player){
                int key = e.getKeyCode();
                if (key == VK_W) {
                    keyDown[0] = false;
                } else if (key == VK_S) {
                    keyDown[1] = false;
                } else if (key == VK_A) {
                    keyDown[2] = false;
                } else if (key == VK_D) {
                    keyDown[3] = false;
                }

                if (!keyDown[2] && !keyDown[3]){
                    tempObject.setVelX(0);
                }
                if(key == VK_H){
                    handler.add(new Laser(tempObject.getX(),tempObject.getY(),ID.Laser,game,new BufferedImage[]{laser},game.width,game.height,handler));
                    clip.start();
                    clip.setFramePosition(0);
                }
            }
        }
    }
}
