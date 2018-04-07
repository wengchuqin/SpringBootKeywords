package top.chuqin.keywords.domain;

import top.chuqin.keywords.vo.Prf1;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "tb_extract_keyword_result")
public class ExtractKeywordResult {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name="summary_id", nullable=false)
    private Long summaryId;

    @NotNull
    @Column(name="algorithm", nullable=false)
    private String algorithm;

    @NotNull
    @Column(name="keywords", nullable=false)
    private String keywords;

    @NotNull
    @Column(name="p", nullable=false)
    private Double p;

    @NotNull
    @Column(name="r", nullable=false)
    private Double r;

    @NotNull
    @Column(name="f1", nullable=false)
    private Double f1;


    public enum AlgorithmEnum{
        PAGE_RANK,
        TF_IDF,
        ANSJ_SEG
    }

    public ExtractKeywordResult(Long summaryId, AlgorithmEnum algorithmEnum, String keywords, Prf1 prf1) {
        this.summaryId = summaryId;
        this.algorithm = algorithmEnum.name();
        this.keywords = keywords;
        p = prf1.getP();
        r = prf1.getR();
        f1 = prf1.getF1();
    }

    public ExtractKeywordResult() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSummaryId() {
        return summaryId;
    }

    public void setSummaryId(Long summaryId) {
        this.summaryId = summaryId;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExtractKeywordResult that = (ExtractKeywordResult) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(summaryId, that.summaryId) &&
                Objects.equals(algorithm, that.algorithm) &&
                Objects.equals(keywords, that.keywords) &&
                Objects.equals(p, that.p) &&
                Objects.equals(r, that.r) &&
                Objects.equals(f1, that.f1);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, summaryId, algorithm, keywords, p, r, f1);
    }

    @Override
    public String toString() {
        return "ExtractKeywordResult{" +
                "id=" + id +
                ", summaryId=" + summaryId +
                ", algorithm='" + algorithm + '\'' +
                ", keywords='" + keywords + '\'' +
                ", p=" + p +
                ", r=" + r +
                ", f1=" + f1 +
                '}';
    }
}
