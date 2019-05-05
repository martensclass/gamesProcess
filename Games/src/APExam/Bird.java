
package APExam;

abstract public class Bird {
    
    private String name;
    private String noise;
    
    public Bird(String na, String no){
        name = na;
        noise=no;
    }
    
    public String getName(){
        return name;
    }
    
    public String getNoise(){
        return noise;
    }
    
    public abstract String getFood();
    
}
