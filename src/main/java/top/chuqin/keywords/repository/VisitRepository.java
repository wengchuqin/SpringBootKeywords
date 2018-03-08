package top.chuqin.keywords.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import top.chuqin.keywords.domain.Visit;

import java.util.List;


public interface VisitRepository extends JpaRepository<Visit, Long>{
    Visit findFirstByHasVisitedFalseOrderByIdAsc();
    Visit findFirstByUrl(String url);
}
