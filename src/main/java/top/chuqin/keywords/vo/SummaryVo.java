package top.chuqin.keywords.vo;

import java.util.Arrays;
import java.util.Objects;

public class SummaryVo {
    /**
     * 文章摘要
     */
    private String summary;
    /**
     * 摘要包含的关键词
     */
    private String[] keywords;
    /**
     * 文章的分类号
     */
    private String[] catelogs;

    /**
     * 相对应的URL
     */
    private String url;

    public SummaryVo() {
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String[] getKeywords() {
        return keywords;
    }

    public void setKeywords(String[] keywords) {
        this.keywords = keywords;
    }

    public String[] getCatelogs() {
        return catelogs;
    }

    public void setCatelogs(String[] catelogs) {
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
        SummaryVo summaryVo = (SummaryVo) o;
        return Objects.equals(summary, summaryVo.summary) &&
                Arrays.equals(keywords, summaryVo.keywords) &&
                Arrays.equals(catelogs, summaryVo.catelogs) &&
                Objects.equals(url, summaryVo.url);
    }

    @Override
    public int hashCode() {

        int result = Objects.hash(summary, url);
        result = 31 * result + Arrays.hashCode(keywords);
        result = 31 * result + Arrays.hashCode(catelogs);
        return result;
    }

    @Override
    public String toString() {
        return "SummaryVo{" +
                "summary='" + summary + '\'' +
                ", keywords=" + Arrays.toString(keywords) +
                ", catelogs=" + Arrays.toString(catelogs) +
                ", url='" + url + '\'' +
                '}';
    }
}

