package org.billing.jlab.collections;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

public class UseMaps {
    public static void main(String[] args) {

        String s1, s2, s3, s4, s5, s6;
        Map<String, String> mapa = new HashMap<>();

        mapa.put("key1", "value1");
        mapa.put("key2", "value2");
        mapa.put("key3", "value3");
        mapa.put("key4", null);
        mapa.put("key3", "new_value3"); // перезапишет {... key3=new_value3}

        s3 = mapa.putIfAbsent("key3", "value3"); // не перезапишет non-null value значение. s3=new_value3
        s4 = mapa.putIfAbsent("key4", "new_value4"); // перезапишет null value {... key4=new_value4}, s4=null
        s5 = mapa.putIfAbsent("key5", "new_value5"); // добавит новое value {... key5=new_value5}, s5=null
        System.out.println(s3 + " " + s4 + " " + s5);
        System.out.println(mapa);

        // v1 - существующее значение, v2 - переданное в merge()
        BiFunction<String, String, String> mapper = (v1, v2) -> v1.length() > v2.length() ? v1+"_xxx": v2+"_yyy";
        s1 = mapa.merge("key1", "new_value", mapper); // обновление {key1=new_value_yyy}
        s6 = mapa.merge("key6", "new_value6", mapper); // добавление, т.к. key не существует
        s6 = mapa.merge(null, "null", mapper); // добавление, т.к. key=null нет
      //  s6 = mapa.merge("key66", "null", (v1, v2) -> null); // добавление, т.к. key=null нет
        System.out.println(s1 + " " + s6);
        System.out.println("Res=" + mapa);

        // k - key, v - соответствующее значение для key
        BiFunction<String, String, String> mapper1 = (k, v) -> v + "_qqq";
        s1 = mapa.computeIfPresent("key2", mapper1); // обновление {... key2=value2_yyy}, s1=value2_yyy
        s2 = mapa.computeIfPresent("key7", mapper1); // нет key7, ничего не произошло, s2=null
        s2 = mapa.computeIfPresent(null, (k, v) -> null); // удаление key=null, s2=null
        System.out.println(mapa);

        Function<String, String> mapper2 = (k) -> "aaa";
        s1 = mapa.computeIfAbsent("key8", mapper2);

    }
}
