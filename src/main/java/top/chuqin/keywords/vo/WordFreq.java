package top.chuqin.keywords.vo;

import org.ansj.domain.Result;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.springframework.util.Assert;

import java.util.*;

public class WordFreq {
    private final String content;
    private final TreeMap<String, Word> treeMap;
    private final List<Word> wordList;

    public WordFreq(String content) {
        Assert.notNull(content, "参数不能为null");

        this.content = content;
        treeMap = new TreeMap<>();

        //填充treeMap，用于查询
        Result result = ToAnalysis.parse(content);
        result.getTerms().forEach(term -> {
            String termName = term.toString();
            Word word = treeMap.get(termName);
            if(word == null){
                word = new Word(term.getName(), term.getNatureStr(), 1);
            }else {
                word.increaseFreq();
            }
            treeMap.put(term.toString(), word);
        });

        //填充wordList，用于排序
        wordList = new ArrayList<>(treeMap.size());
        treeMap.forEach((s, word) -> wordList.add(word));
        Collections.sort(wordList);
    }

    public List<Word> getWordList() {
        return new ArrayList<>(wordList);
    }

    @Override
    public String toString() {
        return wordList.toString();
    }

    public static class Word implements Comparable<Word>{
        private String name;
        private String nature;
        private int freq;

        public Word() {
        }

        public Word(String name, String nature, int freq) {
            this.name = name;
            this.nature = nature;
            this.freq = freq;
        }

        public void increaseFreq(){
            this.freq++;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNature() {
            return nature;
        }

        public void setNature(String nature) {
            this.nature = nature;
        }

        public int getFreq() {
            return freq;
        }

        public void setFreq(int freq) {
            this.freq = freq;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Word word = (Word) o;
            return freq == word.freq &&
                    Objects.equals(name, word.name) &&
                    Objects.equals(nature, word.nature);
        }

        @Override
        public int hashCode() {

            return Objects.hash(name, nature, freq);
        }

        @Override
        public int compareTo(Word o) {
            Assert.notNull(o, "参数不能为空");
            return Integer.compare(o.freq, this.freq);
        }

        @Override
        public String toString() {
            return "Word{" +
                    "name='" + name + '\'' +
                    ", nature='" + nature + '\'' +
                    ", freq=" + freq +
                    '}';
        }
    }
}
