package top.chuqin.keywords.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import top.chuqin.keywords.domain.Summary;
import top.chuqin.keywords.domain.Visit;


public interface SummaryRepository extends JpaRepository<Summary, Long>{

}
