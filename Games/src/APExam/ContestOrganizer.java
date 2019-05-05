
package APExam;


public class ContestOrganizer {
    
    public static final int NUM_ROWS = 4;
    public static int PEOPLE_PER_ROW = 3;
    
    private static String peeps[]={"JOE","AMY","DON","JAC","SUE","PEG","BOB","TIM","CAM","LES","JIM","GIL"};
    
    private Contestant[][]cont;
    
    public ContestOrganizer(){
        cont = new Contestant[NUM_ROWS][PEOPLE_PER_ROW];
        int count=0;
        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < PEOPLE_PER_ROW; j++) {
                cont[i][j] = new Contestant(peeps[count],(int)(Math.random() * 100 + 10),i,j);
                count++;
            }
        }
    }
    
    private void sort(Contestant arr[]){
        //bubble
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if(arr[i].getScore() < arr[j].getScore()){
                    Contestant temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }
    
    public void sortAllRows(){
        for(Contestant row[]: cont){
            sort(row);
        }
    }
    
    public String toString(){
        String table = "";
        for(Contestant row[]: cont){
            for(Contestant c: row){
                table += c.getName() + "\t\t";
            }
            table +="\n";
            for(Contestant c: row){
                table += c.getScore() + "\t\t";
            }
            table +="\n";
            for(Contestant c: row){
                table += c.getLocation().toString() + "\t";
            }
            table +="\n---------------------------------\n";
        }
        return table;
    }
    
    public String findWinnerName(){
        int bestScore=0;
        Contestant best=null;
        sortAllRows();
        for(Contestant row[]:cont){
            Contestant temp = row[row.length-1];
            if (temp.getScore() > bestScore){
                best=temp;
                bestScore = temp.getScore();
            }
        }
        return best.getName();
    }
    
    
    public static void main(String args[]){
        //Contestant c = new Contestant("John Smith", 20, 1, 2);
        //System.out.println(c);
        //c.upDateLocation(0, 0);
        //System.out.println(c);
        ContestOrganizer co = new ContestOrganizer();
       //co.sortAllRows();
        System.out.println(co);
        System.out.println(co.findWinnerName());
    }
}
