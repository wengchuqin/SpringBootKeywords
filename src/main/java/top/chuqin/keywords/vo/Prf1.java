package top.chuqin.keywords.vo;

import java.util.List;

public class Prf1 {
    private Double p;
    private Double r;
    private Double f1;

    public Prf1(List<String> expect, List<String> actually) {
        double m = expect.size();
        double n = actually.size();
        double k = 0;
        for (String s : expect) {
            if (actually.contains(s)) {
                k++;
            }
        }

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
