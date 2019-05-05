
package APExam;

public class Contestant {
    private String name;
    private int score;
    private Location location;
    
    public Contestant(String n, int s, int x, int y){
        name = n;
        score = s;
        location = new Location(x, y);
    }
    
    public String getName(){
        return name;
    }
    
    public int getScore(){
        return score;
    }
    
    public Location getLocation(){
        return location;
    }
    
    public String toString(){
        return("|Name: " + name + "|\n|Score: " + score + "|\n" + location.toString() + "\n");
    }
    
    public void upDateLocation(int newRow, int newCol){
        location = new Location(newRow, newCol);
    }
    
}
