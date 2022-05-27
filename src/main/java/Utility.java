import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Utility {
    
    public static List<String> getDiff(List<String> A, List<String> B){
        Set<String> set2 = new HashSet<>(B);
        List<String> res = new ArrayList<>();
        for(String a: A){
            if(!set2.contains(a)){
                res.add(a);
            }
        }
        return res;
    }

}
