package top.chuqin.keywords.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;


@Entity
@Table(name = "tb_tfdif")
public class Tfdif {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    @Column(name="summary_id", nullable=false)
    private Long summaryId;
    @NotNull
    @Column(name="allWords", nullable=false, length=100000)
    private String allWords;
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

    public Tfdif() {
    }

    public Tfdif(Long id, Long summaryId, String allWords, String keywords, Double p, Double r, Double f1) {
        this.id = id;
        this.summaryId = summaryId;
        this.allWords = allWords;
        this.keywords = keywords;
        this.p = p;
        this.r = r;
        this.f1 = f1;
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

    public String getAllWords() {
        return allWords;
    }

    public void setAllWords(String allWords) {
        this.allWords = allWords;
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
        Tfdif tfdif = (Tfdif) o;
        return Objects.equals(id, tfdif.id) &&
                Objects.equals(summaryId, tfdif.summaryId) &&
                Objects.equals(allWords, tfdif.allWords) &&
                Objects.equals(keywords, tfdif.keywords) &&
                Objects.equals(p, tfdif.p) &&
                Objects.equals(r, tfdif.r) &&
                Objects.equals(f1, tfdif.f1);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, summaryId, allWords, keywords, p, r, f1);
    }

    @Override
    public String toString() {
        return "Tfdif{" +
                "id=" + id +
                ", summaryId=" + summaryId +
                ", allWords='" + allWords + '\'' +
                ", keywords='" + keywords + '\'' +
                ", p=" + p +
                ", r=" + r +
                ", f1=" + f1 +
                '}';
    }
}
