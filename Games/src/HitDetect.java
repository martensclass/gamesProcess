import processing.core.*;
import g4p_controls.*;
import java.awt.Color;
import java.util.Random;

//change name of class:
public class HitDetect extends PApplet
{
        
        Rectangle box1, box2;
        
        int bx1dir, by1dir, bx2dir, by2dir;
        Color col;
        Color white = new Color(255,255,255);
        Color red = new Color(255,0,0);
        Color green = new Color(0,255,0);
        Random r = new Random();
        
                
    
	public void setup()
	{
		size(640, 480, JAVA2D);
                
                box1 = new Rectangle(this, 100,100,50,50);
                box2 = new Rectangle(this, 200,50,50,50);
                
                bx1dir=r.nextInt(5) + 2;
                by1dir=(r.nextInt(5) + 2) * -1;
                
                bx2dir= (r.nextInt(5) + 2) * -1;;
                by2dir=r.nextInt(5) + 2;;
                

	}

	public void handleButtonEvents(GButton button, GEvent event) 
	{

		//code for buttons goes here
	}		

	public void draw()
	{
		background(240, 240, 220); //light tan
                
                if(box1.collidesWith(box2)){
                   col = red;
                }
                else if(box1.collidesWith(mouseX, mouseY) && mousePressed){
                    col=green;
                }
                else
                    col = white;
                
                fill(col.getRGB());
                box1.draw();
                box2.draw();
                moveboxes();
         }
        
        public void moveboxes(){
                box1.moveLR(bx1dir);
                box1.moveUD(by1dir);
                box2.moveLR(bx2dir);
                box2.moveUD(by2dir);
                
                if(box1.x <0 || box1.x > 600) bx1dir=-bx1dir;
                if(box1.y <0 || box1.y > 420) by1dir=-by1dir;
                if(box2.x <0 || box2.x > 600) bx2dir=-bx2dir;
                if(box2.y <0 || box2.y > 420) by2dir=-by2dir;
        }
        
        

	//this is needed to run the program
	public static void main (String ags[])
	{
           PApplet.main(new String[]{HitDetect.class.getName()});
	}

}