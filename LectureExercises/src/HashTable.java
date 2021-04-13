import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class HashTable {
    public static void main(String[] args) {
        Hashtable<Integer,String> H = new Hashtable<Integer, String>();

        H.put(1,"Mazz");
        H.put(2,"Charly");
        H.put(3,"Layton");
        H.put(4,"Carson");
        for(Map.Entry<Integer, String> e: H.entrySet()){
            System.out.println(e.getKey()+" " + e.getValue());
        }



    }
}
