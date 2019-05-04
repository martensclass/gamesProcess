import processing.core.PApplet;

public class Rectangle {

    public int x, y, w, h;
    PApplet host;

    public Rectangle(PApplet pa, int xp, int yp, int wp, int hp) {
        host = pa;
        x = xp;
        y = yp;
        w = wp;
        h = hp;
    }

    public void move(int xp, int yp) {
        x = xp;
        y = yp;
    }

    public void moveLR(float xp) {
        x += xp;
    }

    public void moveUD(float yp) {
        y += yp;
    }

    public boolean collidesWith(Rectangle other) {
        if (x + w > other.x && x < other.x + other.w && y + h > other.y && y < other.y + other.h) {
            return true;
        } else {
            return false;
        }
    }
    
    public char hitWall(){
        if(x < 0) return 'l';
        else if(x + w > host.width) return 'r';
        else if (y < 0) return 't';
        else if (y + h > host.height) return 'b';
        else
            return 'x';
    }
    
    public boolean collidesWith(float xp, float yp) {
        if (xp > x && xp < x + w && yp > y && yp < y + h ) {
            return true;
        } else {
            return false;
        }
    }
    
    public void draw(){
        host.rect(x, y, w, h);
        
    }
    
}
