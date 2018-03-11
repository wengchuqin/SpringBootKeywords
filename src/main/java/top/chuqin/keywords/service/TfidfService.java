package top.chuqin.keywords.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.chuqin.keywords.domain.Summary;
import top.chuqin.keywords.domain.Tfdif;
import top.chuqin.keywords.vo.WordFreq;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class TfidfService {
    private static Logger LOGGER = LoggerFactory.getLogger(TfidfService.class);

    @Autowired
    private SummaryService summaryService;

    public Tfdif tfidf(Long summaryId){
        List<WordWithTfidf> wordWithTfidfList = tfidfList(summaryId);
        Tfdif tfdif = new Tfdif();
        tfdif.setSummaryId(summaryId);
        StringBuffer sb = new StringBuffer();
        wordWithTfidfList.stream().forEach(w-> sb.append(w.toString() + Summary.SEPARATOR));
        tfdif.setAllWords(sb.toString());

        return null;
    }

    public List<WordWithTfidf> tfidfList(Long id) {
        Summary summary = summaryService.findOne(id);
        List<WordWithTfidf> wordWithTfidfList = new ArrayList<>();

        WordFreq wordFreq = new WordFreq(summary.getSummary());
        wordFreq.getWordList().forEach(word -> {

            double tf = word.getFreq();

            //计算idf
            List<Summary> summaryList = summaryService.getSimilarSummaryList(id);
            int D = summaryList.size();
            Long Di = summaryList.stream().filter((s) -> s.getSummary().contains(word.getName())).count();
            double log = Math.log(D / (Di + 1));
            double idf = log;

            WordWithTfidf wordWithTfidf = new WordWithTfidf(word.getName());
            wordWithTfidf.value = tf * idf;

            wordWithTfidfList.add(wordWithTfidf);
            LOGGER.debug("-----{}------", word.getName());
            LOGGER.debug("tf:{}", tf);
            LOGGER.debug("D:{}", D);
            LOGGER.debug("Di:{}", Di);
            LOGGER.debug("log:{}", log);
            LOGGER.debug("tf-idf:{}", wordWithTfidf.value);
        });

        Collections.sort(wordWithTfidfList);
        return wordWithTfidfList;
    }




    public static class WordWithTfidf implements Comparable<WordWithTfidf>{
        private String word;
        private double value;

        public WordWithTfidf(String word) {
            this.word = word;
        }

        @Override
        public int compareTo(WordWithTfidf o) {
            return Double.compare(o.value, this.value);
        }

        @Override
        public String toString() {
            return String.format("%s(%.2f)", word, value);
        }
    }
}
