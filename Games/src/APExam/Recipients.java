package APExam;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Recipients {

    private List<String> lines;

    public Recipients() {
        lines = new ArrayList<String>();
        try {
            File file = new File("data.txt");
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
               lines.add(sc.nextLine());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public String getLine(int n){
        return lines.get(n);
    }
    
    public String extractCity(String s){
        int loc = s.indexOf(",");
        return s.substring(0, loc);
    }
    
    public void printNames(){
        System.out.println(lines.get(0));
        for (int i = 1; i < lines.size()-1; i++) {
            if(lines.get(i).equals("")){
                System.out.println(lines.get(i+1));
            }
        }
    }
    
    public String getAddress(String name){
        int loc=-1;
        String add;
        for (int i = 0; i < lines.size(); i++) {
            if(lines.get(i).equals(name)){
                loc=i;
                break;
            }
        }
        loc++;
        add=lines.get(loc) + "\n";
        while(true){
            loc++;
            add+=lines.get(loc) + "\n";
            if(lines.get(loc).equals(""))
                break;
        }
        return add;
    }
    
    public static void main(String args[]){
        Recipients r = new Recipients();
        String city = r.extractCity(r.getLine(2));
        System.out.println(city);
        r.printNames();
        String add = r.getAddress("Jack S. Smith");
        System.out.println(add);
    }
}
