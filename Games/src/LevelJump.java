
import processing.core.*;
import g4p_controls.*;

import java.util.ArrayList;
import java.util.Random;

//change name of class:
public class LevelJump extends PApplet {

    String mode = "down", dir = "none";
    boolean jump = false;
    int vel = 5;
    int yloc = 30;
    int xloc = 10;
    int onrect[] = {0, 0, 0, 0, 0};

    ArrayList<int[]> floors = new ArrayList<int[]>();

    int platform[] = {0, 520, 800, 10, 0};

    public void setup() {
        size(800, 600, JAVA2D);
        floors.add(new int[]{0, 520, 800, 10, 0});
        floors.add(new int[]{100, 420, 80, 10, 4});
        floors.add(new int[]{300, 320, 80, 10, 1});
        floors.add(new int[]{425, 200, 80, 10, 3});
        floors.add(new int[]{540, 255, 80, 10, 2});
        floors.add(new int[]{300, 125, 80, 10, 5});
        floors.add(new int[]{0, 50, 350, 10, 0});
        floors.add(new int[]{390, 50, 410, 10, 0});

    }

    boolean hittop() {
        for (int[] f : floors) {
            if (xloc + 20 > f[0] && xloc < f[0] + f[2]) {
                if (yloc + vel <= f[1] && yloc >= f[1]) {
                    return true;
                }
            }
        }
        return false;
    }

    boolean hitting() {
        int bottom = yloc + 20;
        for (int[] f : floors) {
            if (xloc + 20 > f[0] && xloc < f[0] + f[2]) {
                if (bottom >= f[1] - 3 && bottom <= f[1] + 3) {
                    yloc = f[1] - 20;
                    onrect = f;
                    if (f[2] == 800) {
                        mode = "up";

                    }
                    return true;
                }
                if (bottom - vel <= f[1] && bottom >= f[1]) {
                    yloc = f[1] - 20;
                    return true;
                }

            } //end if in x-range

        } //next floor
        return false;
    }

    public void handleButtonEvents(GButton button, GEvent event) {

        //code for buttons goes here
    }

    public void keyReleased() {
        if (keyCode == RIGHT) {
            dir = "none";
        }
        if (keyCode == LEFT) {
            dir = "none";
        }
    }

    public void keyPressed() {

        if (mode == "down") {
            if (keyCode == RIGHT) {
                dir = "right";
            }
            if (keyCode == LEFT) {
                dir = "left";
            }
            return;
        }

        if (keyCode == 32 && jump == false && hitting()) {
            jump = true;
            yloc -= 10;
            vel = -16;
        }

    }

    public void draw() {
        background(220, 220, 210);
        fill(0, 100, 0);
        rect(xloc, yloc, 20, 20);
        fill(100, 100, 0);
        for (int[] f : floors) {
            rect(f[0], f[1], f[2], f[3]);
            f[0] += f[4];
            if (f[0] > 800 - f[2] || f[0] <= 0) {
                f[4] = -f[4];
            }
        }
        if (dir == "right") {
            xloc += 3;
        }
        if (dir == "left") {
            xloc -= 3;
        }

        if (hitting() == false) {
            yloc += vel;
        } else {
            xloc += onrect[4];
        }

        if (jump) {
            vel++;
            if (vel < 0 && hittop()) {
                vel = -vel;
            }
            if (vel > 0 && hitting()) {
                jump = false;
                vel = 5; //stop
            }
        }

    }

    //this is needed to run the program
    public static void main(String ags[]) {
        PApplet.main(new String[]{LevelJump.class.getName()});
    }

}
