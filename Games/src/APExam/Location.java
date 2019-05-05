package APExam;

public class Location {
    
    private int row, col;
    
    public Location(int r, int c){
        row=r;
        col=c;
    }
    
    public int getRowNumber(){
        return row;
    }
    
    public int getColumnNumber(){
        return col;
    }
    public String toString(){
        return ("[r=" + row + ", c=" + col + "]");
    }
    
    
    
}
