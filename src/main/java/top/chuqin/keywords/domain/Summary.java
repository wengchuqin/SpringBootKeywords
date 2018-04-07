package top.chuqin.keywords.domain;

import org.springframework.data.repository.cdi.Eager;
import org.springframework.util.Assert;
import top.chuqin.keywords.vo.SummaryVo;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "tb_summary")
public class Summary implements Serializable {

    public static final String SEPARATOR = " ";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * 文章的url
     */
    @NotNull
    @Column(name = "url", nullable = false)
    private String url;

    /**
     * 文章摘要
     */
    @NotNull
    @Column(name = "summary", nullable = false, length = 100000)
    private String summary;


    /**
     * 摘要包含的关键词，用空格分开。
     */
    @NotNull
    @ElementCollection
    @CollectionTable(
            name = "tb_summary_keyword",
            joinColumns = @JoinColumn(name = "summary_id")
    )
    @Column(name = "keyword")
    private List<String> keywords;


    /**
     * 文章的分类号，用空格分开。
     */
    @NotNull
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "tb_summary_catelog",
            joinColumns = @JoinColumn(name = "summary_id")
    )
    @Column(name = "catelog")
    private List<String> catelogs;


    public Summary() {
    }

    public Summary(String url, String summary, List<String> keywords, List<String> catelogs) {
        this.url = url;
        this.summary = summary;
        this.keywords = keywords;
        this.catelogs = catelogs;
    }

    public static Summary fromVo(SummaryVo vo) {
        Assert.notNull(vo, "参数不能为null");
        Assert.notNull(vo.getSummary(), "summary不能为null");
        Assert.notEmpty(vo.getKeywords(), "keywords不能为空");
        Assert.notEmpty(vo.getCatelogs(), "keywords不能为空");

        return new Summary(vo.getUrl(), vo.getSummary(), Arrays.asList(vo.getKeywords()), Arrays.asList(vo.getCatelogs()));
    }

    public Long getId() {
        return id;
    }

    public static String getSEPARATOR() {
        return SEPARATOR;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public List<String> getCatelogs() {
        return catelogs;
    }

    public void setCatelogs(List<String> catelogs) {
        this.catelogs = catelogs;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Summary summary1 = (Summary) o;
        return Objects.equals(id, summary1.id) &&
                Objects.equals(summary, summary1.summary) &&
                Objects.equals(keywords, summary1.keywords) &&
                Objects.equals(catelogs, summary1.catelogs);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, summary, keywords, catelogs);
    }

    @Override
    public String toString() {
        return "Summary{" +
                "id=" + id +
                ", summary='" + summary + '\'' +
                ", keywords='" + keywords + '\'' +
                ", catelogs='" + catelogs + '\'' +
                '}';
    }
}
