package top.chuqin.keywords.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import top.chuqin.keywords.domain.Summary;
import top.chuqin.keywords.domain.Tfidf;


public interface TfidfRepository extends JpaRepository<Tfidf, Long> {
    Tfidf findFirstBySummaryId(Long summaryId);
}
