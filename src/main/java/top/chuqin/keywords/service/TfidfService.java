package top.chuqin.keywords.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.chuqin.keywords.domain.Summary;
import top.chuqin.keywords.domain.Tfidf;
import top.chuqin.keywords.repository.SummaryRepository;
import top.chuqin.keywords.repository.TfidfRepository;
import top.chuqin.keywords.utils.Progress;
import top.chuqin.keywords.vo.WordFreq;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class TfidfService {
    private static Logger LOGGER = LoggerFactory.getLogger(TfidfService.class);

    @Autowired
    private SummaryService summaryService;

    @Autowired
    private SummaryRepository summaryRepository;

    @Autowired
    private TfidfRepository tfidfRepository;

    private Progress progress = new Progress();

    public Tfidf findBySummaryId(Long summaryId) {
        Tfidf tfidf = tfidf(summaryId);
        tfidfRepository.save(tfidf);
        return tfidf;
    }

    public void calcAllTfidf() {
        if (progress.isStart()) {
            return;
        }
        progress.setStart(true);
        progress.setHasDone(false);

        new Thread(() -> {
            List<Summary> allSummary = summaryRepository.findAll();
            progress.setTotal(allSummary.size());
            allSummary.stream().forEach(summary -> {
                LOGGER.debug("start");
                Tfidf tmp = tfidfRepository.findFirstBySummaryId(summary.getId());
                Tfidf tfidf = tfidf(summary.getId());
                if(tmp != null){
                    tfidf.setId(tmp.getId());
                }
                tfidfRepository.save(tfidf);
                progress.setCompletion(progress.getCompletion() + 1);
                LOGGER.debug("end{}", progress);
            });

            progress.setStart(false);
            progress.setHasDone(true);
        }).start();
    }

    public Progress calcAllTfidfStatus(){
        return progress;
    }

    public Tfidf tfidf(Long summaryId) {
        Summary summary = summaryService.findOne(summaryId);
        List<WordWithTfidf> tfidfList = tfidfList(summaryId);
        Tfidf tfidf = tfdif(summary, tfidfList);
        return tfidf;
    }

    public Tfidf tfdif(Summary summary, List<WordWithTfidf> tfidfList) {
        Tfidf tfidf = new Tfidf();
        List<String> allwords = new ArrayList<>();
        tfidfList.stream().forEach(wordWithTfidf -> allwords.add(wordWithTfidf.toString()));
        tfidf.setAllWords(allwords);

        List<String> keywords = new ArrayList<>();
        int endStart = -1;
        if (tfidfList.size() >= summary.getKeywords().size()) {
            endStart = summary.getKeywords().size();
        } else {
            endStart = tfidfList.size();
        }
        tfidfList.subList(0, endStart).forEach(wordWithTfidf -> keywords.add(wordWithTfidf.word));
        tfidf.setKeywords(keywords);

        tfidf.setSummaryId(summary.getId());

        double m = summary.getKeywords().size();
        double n = tfidf.getKeywords().size();
        double k = 0;
        for (String pattern : summary.getKeywords()) {
            if (tfidf.getKeywords().contains(pattern)) {
                k++;
            }
        }


        Double p = k / n;
        tfidf.setP(p);
        Double r = k / m;
        tfidf.setR(r);
        Double f1 = (2 * k) / (m + n);
        tfidf.setF1(f1);

        LOGGER.debug("tfidf:[{}]", tfidf);
        return tfidf;
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


    public static class WordWithTfidf implements Comparable<WordWithTfidf> {
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
