package top.chuqin.keywords.vo;

import java.util.List;

public class AnalyzationVo {
    private List<String> terms;
    private List<String> ansjKeywords;

    public List<String> getTerms() {
        return terms;
    }

    public void setTerms(List<String> terms) {
        this.terms = terms;
    }

    public List<String> getAnsjKeywords() {
        return ansjKeywords;
    }

    public void setAnsjKeywords(List<String> ansjKeywords) {
        this.ansjKeywords = ansjKeywords;
    }
}
