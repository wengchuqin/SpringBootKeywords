package top.chuqin.keywords.vo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Prf1 {
    private Double p;
    private Double r;
    private Double f1;

    public boolean softContains(List<String> expectList, String actually){
        for(String expect : expectList){
            if(expect.contains(actually)){
                return true;
            }
        }
        return false;
    }

    public int softContains(List<String> expectList, List<String> actuallyList){
        expectList = new ArrayList<>(expectList);
        actuallyList = new ArrayList<>(actuallyList);

        int k = 0;
        for (String actually : actuallyList) {
            Iterator<String> it = expectList.iterator();
            while (it.hasNext()){
                String exp = it.next();
                if(exp.contains(actually)){
                    k++;
                    it.remove();
                }
            }
        }

        return k;
    }

    private boolean hardContains(List<String> expectList, String actually){
        return expectList.contains(actually);
    }

    public Prf1(List<String> expectList, List<String> actuallyList) {
        double m = expectList.size();
        double n = actuallyList.size();
        double k = softContains(expectList, actuallyList);

        p = k / n;
        r = k / m;
        f1 = (2 * k) / (m + n);
    }

    public Double getP() {
        return p;
    }

    public Double getR() {
        return r;
    }

    public Double getF1() {
        return f1;
    }

}
