package top.chuqin.keywords.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "tb_visit")
public class Visit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name="url", nullable=false, unique = true)
    private String url;

    @NotNull
    @Column(name="has_visited", nullable = false)
    private Boolean hasVisited;

    public static Visit of(String url){
        return new Visit(null, url, false);
    }

    public Visit(Long id, String url, Boolean hasVisited) {
        this.id = id;
        this.url = url;
        this.hasVisited = hasVisited;
    }

    public Visit() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getHasVisited() {
        return hasVisited;
    }

    public void setHasVisited(Boolean hasVisited) {
        this.hasVisited = hasVisited;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Visit visit = (Visit) o;
        return Objects.equals(id, visit.id) &&
                Objects.equals(url, visit.url) &&
                Objects.equals(hasVisited, visit.hasVisited);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, url, hasVisited);
    }

    @Override
    public String toString() {
        return "Visit{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", hasVisited=" + hasVisited +
                '}';
    }
}
