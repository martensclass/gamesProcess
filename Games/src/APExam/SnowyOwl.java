
package APExam;

public class SnowyOwl extends Owl{
    private static final String name="Snowy owl";
    
    public SnowyOwl(){
        super(name);
    }
    
    public String getFood(){
        String menu[]={"hare", "lemming", "small bird"};
        return menu[(int)(Math.random() * 3)];
    }
    
}
