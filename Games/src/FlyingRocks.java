
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
    Random r = new Random();
    int rx, ry;
    int rm1, rm2;
    Rectangle ship, gun, treasure;
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
        
        for (int i = 0; i < 1; i++) {
            addRock();
        }
        ship = new Rectangle(this, loadImage("ship.png"),10,400);
        gun = new Rectangle(this, loadImage("gun.png"), -100, -100);
        treasure = new Rectangle(this, loadImage("treasure.png"),200,100);
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
        rocks.add(new Rectangle(this, loadImage("rock.png"), rx, ry));
        int x[] = {rm1, rm2};
        move.add(x);
    }


    public void handleButtonEvents(GButton button, GEvent event) {

        //code for buttons goes here
    }
    
    public void Treasure(){
        if(showT==false)
        {
            treasure.x=-200;
            treasure.y=-200;
            if(frameCount%180==0){
                 treasure.x = r.nextInt(550);
                 treasure.y = r.nextInt(50) + 50;
                 showT = true;
            }
        }
        treasure.draw();
      
        
    }
    
  
    public void keyPressed(){
          ship.move();
    }
    
    public void mousePressed(){
         bullets.add(new Rectangle(this,ship.x+30, ship.y-10,5,5));
    }
    
    public void keyReleased(){
     if(key !=' ')
        dirx=0;
    }

    public void draw() {
        if(lazeron){
            gun.x = mouseX;
            gun.y = mouseY;
            lazertime++;
            if(lazertime==300){
                lazeron=false;
                gun.x=-100;
                gun.y=-100;
                lazertime=0;
            }
        }
        
        background(240, 240, 220); //light tan
        drawStatus();
        gun.draw();
        Treasure();
        
        if(frameCount % 180 ==0)
           addRock();
       
        
        if(keyPressed){
            ship.move();
        }
       
        
        /*
       if(ship.hitWall()=='t' || ship.hitWall()=='b')
        ship.moveUD(-diry);    
      
       if(ship.hitWall()=='l' || ship.hitWall()=='r')
        ship.moveLR(-dirx);
     */
        //ship.x = mouseX;

        ship.draw();
        fill(255,0,0);
        for (int i = 0; i < bullets.size(); i++) {
            Rectangle b = bullets.get(i);
            b.draw();
            b.moveUD(-10);
            if(b.y < -10){
                bullets.remove(b);
            }
            else if(b.collidesWith(treasure)){
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
            r.draw();
            if (r.collidesWith(gun)) {
                rocks.remove(r);
                move.remove(i);
            } else {
                int dir[] = move.get(i);
                r.moveLR(dir[0]);
                r.moveUD(dir[1]);
                move.set(i, checkDir(dir, r));
                if (r.collidesWith(ship)) {
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
