package top.chuqin.keywords.vo;

import java.util.Objects;

public class AlgorithmCompareVo {
    private Double p;
    private Double r;
    private Double f1;
    private String algorithmName;

    public AlgorithmCompareVo(Double p, Double r, Double f1, String algorithmName) {
        this.p = p;
        this.r = r;
        this.f1 = f1;
        this.algorithmName = algorithmName;
    }

    public AlgorithmCompareVo() {
    }

    public Double getP() {
        return p;
    }

    public void setP(Double p) {
        this.p = p;
    }

    public Double getR() {
        return r;
    }

    public void setR(Double r) {
        this.r = r;
    }

    public Double getF1() {
        return f1;
    }

    public void setF1(Double f1) {
        this.f1 = f1;
    }

    public String getAlgorithmName() {
        return algorithmName;
    }

    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlgorithmCompareVo that = (AlgorithmCompareVo) o;
        return Objects.equals(p, that.p) &&
                Objects.equals(r, that.r) &&
                Objects.equals(f1, that.f1) &&
                Objects.equals(algorithmName, that.algorithmName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(p, r, f1, algorithmName);
    }

    @Override
    public String toString() {
        return "AlgorithmCompareVo{" +
                "p=" + p +
                ", r=" + r +
                ", f1=" + f1 +
                ", algorithmName='" + algorithmName + '\'' +
                '}';
    }
}
