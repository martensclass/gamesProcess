
package APExam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class WordSet {
    
    private List<String>wordlist;
    
    public WordSet(){
        wordlist = new ArrayList<String>();
        
    }
    
    public void addWords(String arr[]){
        for (int i = 0; i < arr.length; i++) {
            wordlist.add(arr[i]);
        }
       
    }
    
    public String findkth(int k){
        k--;
        List<String> temp = wordlist;
        Collections.sort(temp);
        return temp.get(k);
    }
    
    public int size(){
        return wordlist.size();
    }
    
    public void insert(String a){
        wordlist.add(a);
    }
    public void remove(String a){
        wordlist.remove(a);
    }
    public boolean contains(String a){
        return wordlist.contains(a);
    }
    
    public static int countA(WordSet w){
        int as=0;
        int count=1;
        while(count <=w.size()){
            if(w.findkth(count).charAt(0)=='A')
                as++;
            count++;
        }
        return as;
    }
    
    public static void removeA(WordSet w){
        int as = countA(w);
        for (int i = 1; i <=as; i++) {
            w.remove(w.findkth(1));
        }
       
    }
    
    public String toString(){
        return wordlist.toString();
    }
    
    public static WordSet commonElements(WordSet a, WordSet b){
        WordSet result = new WordSet();
        for (int i = 1; i <=a.size(); i++) {
            if(b.contains(a.findkth(i)))
                result.insert(a.findkth(i));
        }
        return result;
    }

   
    public static void main(String[] args) {
        WordSet w = new WordSet();
        String list[] ={"GRAPE","PEAR","FIG","APRICOT", "APPLE"}; 
        String list2[] =  {"BANANA", "CHERRY","PEAR","FIG"};
        w.addWords(list);
      //  System.out.println(w.findkth(3));
      //  System.out.println(countA(w));
        removeA(w);
        WordSet w2 = new WordSet();
        w2.addWords(list2);
        WordSet w3 = commonElements(w, w2);
        System.out.println(w3);
        
    }
    
}
