import javafx.beans.WeakInvalidationListener;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

public class Game extends JFrame implements Runnable{
    private static final long serialVersionUID = 1550691097823471818L;

    private static final float WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
    private Thread thread;
    private boolean running = false;
    private Handler handler = new Handler();
    protected int width;
    protected int height;
    protected int top;
    protected int left;
    protected Hashtable<ID, Image[]> images = new Hashtable<ID, Image[]>();
    private HUD hud;
    private Spawner spawner;

    public Game(){
        this.setPreferredSize(new Dimension((int)WIDTH, (int)HEIGHT));
        this.setMinimumSize(new Dimension((int)WIDTH, (int)HEIGHT));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.addKeyListener(new KeyInput(handler, this));
        this.pack();
        width = (int) WIDTH - this.getInsets().right;
        height = (int) HEIGHT - this.getInsets().bottom;
        top = this.getInsets().top;
        left = this.getInsets().left;
        hud = new HUD(this);
        spawner = new Spawner(left,this);
        BufferedImage player = loadImage("Player.png");
        BufferedImage  basicEnemy = loadImage("basicalien1.png");
        BufferedImage  basicEnemy2 = loadImage("basicalien2.png");
        BufferedImage  strongEnemy = loadImage("strongalien1.png");
        BufferedImage  strongEnemy2 = loadImage("strongalien2.png");
        BufferedImage  laserEnemy = loadImage("laseralien1.png");
        BufferedImage  laserEnemy2 = loadImage("laseralien2.png");
        BufferedImage enemyLaser = loadImage("enemylaser.png");
        BufferedImage alienDeath = loadImage("aliendeath.png");
        images.put(ID.EnemyLaser, new BufferedImage[]{enemyLaser});
        images.put(ID.Player, new BufferedImage[]{player});
        images.put(ID.BasicEnemy, new BufferedImage[]{basicEnemy, basicEnemy2});
        images.put(ID.StrongEnemy, new BufferedImage[]{strongEnemy, strongEnemy2});
        images.put(ID.LaserEnemy, new BufferedImage[]{laserEnemy, laserEnemy2});
        images.put(ID.AlienDeath, new BufferedImage[]{alienDeath});

        handler.add(new Player(WIDTH/2, height,ID.Player,this, images.get(ID.Player), width, height, handler));
        this.start();
    }

    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop(){
        try {
            thread.join();
            running = false;
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run(){
        this.setFocusable(true);
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        while (running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1) {
                tick();
                delta--;
            }
            if (running) {
                render();
            }
        }
        stop();
    }

    public void tick(){
        handler.tick();
        spawner.tick(handler);
    }

    public void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.black);
        g.fillRect(0,0,(int)WIDTH,(int)HEIGHT);
        handler.render(g);
        hud.render(g);
        g.dispose();
        bs.show();
    }

    public static float clamp(float value, float lowerLimit, float upperLimit){
        if(value <= lowerLimit) {
            return lowerLimit;
        }   else if(value >= upperLimit){
            return upperLimit;
        } else {
            return value;
        }
    }

    public BufferedImage loadImage(String file){
        try {
            return ImageIO.read(new File(file));
        } catch (IOException e) {
            System.out.println("null");
            return null;
        }
    }

    public static void main(String[] args){
        Game game = new Game();
        System.out.println(game.getInsets());
    }
}
