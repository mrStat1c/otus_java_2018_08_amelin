import org.apache.commons.collections.map.MultiValueMap;

import java.util.HashMap;

public class Program {

    public static void main(String[] args) {

        int x = 0;
        MultiValueMap multiValueMap = MultiValueMap.decorate(new HashMap<String, Integer>());
        for(String arg:args){
            multiValueMap.put(arg, ++x);
            System.out.println(arg);
        }
    }

}
