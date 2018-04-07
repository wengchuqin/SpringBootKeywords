package top.chuqin.keywords.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "tb_tfdif")
public class Tfidf {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name="summary_id", nullable=false)
    private Long summaryId;

    @NotNull
    @ElementCollection
    @CollectionTable(
            name = "tb_tfdif_word",
            joinColumns = @JoinColumn(name = "allword_id")
    )
    @Column(name="allWords", nullable=false)
    private List<String> allWords;

    @NotNull
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "tb_tfdif_keyword",
            joinColumns = @JoinColumn(name = "keyword_id")
    )
    @Column(name="keywords", nullable=false)
    private List<String> keywords;

    @NotNull
    @Column(name="p", nullable=false)
    private Double p;

    @NotNull
    @Column(name="r", nullable=false)
    private Double r;

    @NotNull
    @Column(name="f1", nullable=false)
    private Double f1;

    public Tfidf() {
    }


    public Tfidf(Long summaryId, List<String> allWords, List<String> keywords, Double p, Double r, Double f1) {
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

    public List<String> getAllWords() {
        return allWords;
    }

    public void setAllWords(List<String> allWords) {
        this.allWords = allWords;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
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
        Tfidf tfidf = (Tfidf) o;
        return Objects.equals(id, tfidf.id) &&
                Objects.equals(summaryId, tfidf.summaryId) &&
                Objects.equals(allWords, tfidf.allWords) &&
                Objects.equals(keywords, tfidf.keywords) &&
                Objects.equals(p, tfidf.p) &&
                Objects.equals(r, tfidf.r) &&
                Objects.equals(f1, tfidf.f1);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, summaryId, allWords, keywords, p, r, f1);
    }

    @Override
    public String toString() {
        return "Tfidf{" +
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
