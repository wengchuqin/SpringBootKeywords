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

    public SummaryVo() {
    }

    public SummaryVo(String summary, String[] keywords, String[] catelogs) {
        this.summary = summary;
        this.keywords = keywords;
        this.catelogs = catelogs;
    }

    public String getSummary() {
        return summary;
    }

    public String[] getKeywords() {
        return keywords;
    }

    public String[] getCatelogs() {
        return catelogs;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setKeywords(String[] keywords) {
        this.keywords = keywords;
    }

    public void setCatelogs(String[] catelogs) {
        this.catelogs = catelogs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SummaryVo summaryVo = (SummaryVo) o;
        return Objects.equals(summary, summaryVo.summary) &&
                Arrays.equals(keywords, summaryVo.keywords) &&
                Arrays.equals(catelogs, summaryVo.catelogs);
    }

    @Override
    public int hashCode() {

        int result = Objects.hash(summary);
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
                '}';
    }
}

