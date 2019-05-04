
import processing.core.*;
import g4p_controls.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;

//change name of class:
public class FlyingRocks extends PApplet {

    ArrayList<Rectangle> rocks = new ArrayList<Rectangle>();
    ArrayList<int[]> move = new ArrayList<int[]>();
    ArrayList<Rectangle> bullets = new ArrayList<Rectangle>();
    PImage rock, ship, lazer, treasure;
    Random r = new Random();
    int rx, ry;
    int rm1, rm2;
    Rectangle shipbox, lazerbox, tbox;
    int health = 400;
    int dirx=0, diry=0;
    boolean showT = true, lazeron=false;
    int tscore=0;
    int lazertime=0;
    
    public void drawStatus(){
        fill(0,0,0);
        rect(540,10,80,20);
        fill(255,255,255);
        text("Score: " + tscore,550,25);
        String ltext;
        if(lazeron==false){
            ltext = "Laser: OFF";
            fill(0,0,0);
        }
        else{
            ltext = "Laser: ON";
            fill(255,0,0);
        }
        rect(450,10,90,20);
        fill(255,255,255);
        text(ltext,455,25);
        //health bar
        fill(255, 0, 0);
        rect(10, 10, 400, 20);
        fill(0, 255, 0);
        rect(10, 10, health, 20);
    }
    
    

    public void setup() {
        size(640, 480, JAVA2D);
        rock = loadImage("rock.png");
        ship = loadImage("ship.png");
        lazer = loadImage("gun.png");
        treasure = loadImage("treasure.png");
        for (int i = 0; i < 1; i++) {
            addRock();
        }
        shipbox = new Rectangle(this, 10,400,ship.width, ship.height);
        lazerbox = new Rectangle(this, -100, -100, lazer.width, lazer.height);
        tbox = new Rectangle(this, 200,100, treasure.width, treasure.height);
        noCursor();
    }
    
    
    public void addRock() {
        rx = r.nextInt(620);
        ry = r.nextInt(450);
        rm1 = r.nextInt(5) + 2;
        rm2 = r.nextInt(5) + 2;
        if (rm1 % 2 == 0) {
            rm1 = -rm1;
        }
        if (rm2 % 2 == 1) {
            rm2 = -rm2;
        }
        rocks.add(new Rectangle(this, rx, ry, rock.width, rock.height));
        int x[] = {rm1, rm2};
        move.add(x);
    }


    public void handleButtonEvents(GButton button, GEvent event) {

        //code for buttons goes here
    }
    
    public void Treasure(){
        if(showT==false)
        {
            tbox.x=-200;
            tbox.y=-200;
            if(frameCount%180==0){
                 tbox.x = r.nextInt(550);
                 tbox.y = r.nextInt(50) + 50;
                 showT = true;
            }
        }
        image(treasure, tbox.x, tbox.y);
      
        
    }
    
  
    public void keyPressed(){
        // if(keyCode == UP) diry = -3;
        // else if(keyCode ==DOWN) diry = 3;
        if(keyCode ==LEFT) dirx = -5;
         else if(keyCode ==RIGHT) dirx = 5;
         
         else if(key==' ' && bullets.size()<3){
              bullets.add(new Rectangle(this,shipbox.x+30, shipbox.y-10,5,5));
          }
        
          
    }
    
    public void mousePressed(){
         bullets.add(new Rectangle(this,shipbox.x+30, shipbox.y-10,5,5));
    }
    
    public void keyReleased(){
     if(key !=' ')
        dirx=0;
    }

    public void draw() {
        if(lazeron){
            lazerbox.x = mouseX;
            lazerbox.y = mouseY;
            lazertime++;
            if(lazertime==300){
                lazeron=false;
                lazerbox.x=-100;
                lazerbox.y=-100;
                lazertime=0;
            }
        }
        
        background(240, 240, 220); //light tan
        drawStatus();
        image(lazer, lazerbox.x, lazerbox.y);
        Treasure();
        
        if(frameCount % 180 ==0)
           addRock();
       
        
        //shipbox.moveUD(diry);
        shipbox.moveLR(dirx);
        /*
       if(shipbox.hitWall()=='t' || shipbox.hitWall()=='b')
        shipbox.moveUD(-diry);    
      
       if(shipbox.hitWall()=='l' || shipbox.hitWall()=='r')
        shipbox.moveLR(-dirx);
     */
        shipbox.x = mouseX;

        image(ship, shipbox.x, shipbox.y, 64, 64);
        fill(255,0,0);
        for (int i = 0; i < bullets.size(); i++) {
            Rectangle b = bullets.get(i);
            b.draw();
            b.moveUD(-10);
            if(b.y < -10){
                bullets.remove(b);
            }
            else if(b.collidesWith(tbox)){
                showT=false;
                bullets.remove(b);
                tscore++;
                if(tscore%5==0){
                    lazeron=true;
                }
            }
        }
        
        
        for (int i = 0; i < rocks.size(); i++) {
            Rectangle r = rocks.get(i);
            image(rock, r.x, r.y);
            if (r.collidesWith(lazerbox)) {
                rocks.remove(r);
                move.remove(i);
            } else {
                int dir[] = move.get(i);
                r.moveLR(dir[0]);
                r.moveUD(dir[1]);
                move.set(i, checkDir(dir, r));
                if (r.collidesWith(shipbox)) {
                    health--;
                }
                if (health == 0) {
                    JOptionPane.showMessageDialog(this, "Game Over");
                    System.exit(0);
                }
            }
        }

    }

    public int[] checkDir(int dir[], Rectangle r) {
        if (r.hitWall() == 'r' || r.hitWall() == 'l') {
            dir[0] = -dir[0];
        }
        if (r.hitWall() == 't' || r.hitWall() == 'b') {
            dir[1] = -dir[1];
        }
        return dir;
    }

    //this is needed to run the program
    public static void main(String ags[]) {
        PApplet.main(new String[]{FlyingRocks.class.getName()});
    }

}
