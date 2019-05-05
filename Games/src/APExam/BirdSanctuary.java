
package APExam;

/**
 *
 * @author Martens
 */
public class BirdSanctuary {
    
    private Bird[] birdList;
    
    public BirdSanctuary(){
        birdList = new Bird[4];
        for (int i = 0; i < 4; i++) {
            birdList[i] = new SnowyOwl();
        }
    }
    
    public void allEat(){
        for(Bird b: birdList){
            System.out.println(b.getName() + "\tEats: " + b.getFood());
        }
    }
   
    public static void main(String[] args) {
       new BirdSanctuary().allEat();
    }
    
}
