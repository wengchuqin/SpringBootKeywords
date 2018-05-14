package top.chuqin.keywords.vo;

import java.util.Objects;

public class Category {
    private String code;
    private Integer count;

    public Category() {
    }

    public Category(String code, Integer count) {
        this.code = code;
        this.count = count;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(code, category.code) &&
                Objects.equals(count, category.count);
    }

    @Override
    public int hashCode() {

        return Objects.hash(code, count);
    }
}
