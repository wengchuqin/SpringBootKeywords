package top.chuqin.keywords.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import top.chuqin.keywords.domain.ExtractKeywordResult;

import java.util.List;


public interface ExtractKeywordResultRepository extends JpaRepository<ExtractKeywordResult, Long> {
    List<ExtractKeywordResult> findAllByAlgorithm(String algorithm);
}
