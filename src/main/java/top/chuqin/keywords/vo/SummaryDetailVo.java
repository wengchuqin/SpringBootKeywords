package top.chuqin.keywords.vo;

import top.chuqin.keywords.domain.Summary;

import java.util.Objects;

public class SummaryDetailVo {
    private Summary summary;
    private AnalyzationVo analyzationVo;

    public SummaryDetailVo() {
    }

    public Summary getSummary() {
        return summary;
    }

    public void setSummary(Summary summary) {
        this.summary = summary;
    }

    public AnalyzationVo getAnalyzationVo() {
        return analyzationVo;
    }

    public void setAnalyzationVo(AnalyzationVo analyzationVo) {
        this.analyzationVo = analyzationVo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SummaryDetailVo that = (SummaryDetailVo) o;
        return Objects.equals(summary, that.summary) &&
                Objects.equals(analyzationVo, that.analyzationVo);
    }

    @Override
    public int hashCode() {

        return Objects.hash(summary, analyzationVo);
    }

    @Override
    public String toString() {
        return "SummaryDetailVo{" +
                "summary=" + summary +
                ", analyzationVo=" + analyzationVo +
                '}';
    }
}
