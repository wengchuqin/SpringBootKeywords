package top.chuqin.keywords.vo;

import java.util.Objects;

public class AnalyzeStatus {
    private long total;
    private long done;
    private boolean run;

    public AnalyzeStatus(long total, long done, boolean run) {
        this.total = total;
        this.done = done;
        this.run = run;
    }

    public AnalyzeStatus() {
    }

    public long getTotal() {
        return total;
    }

    public long getDone() {
        return done;
    }

    public boolean isRun() {
        return run;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnalyzeStatus that = (AnalyzeStatus) o;
        return total == that.total &&
                done == that.done &&
                run == that.run;
    }

    @Override
    public int hashCode() {

        return Objects.hash(total, done, run);
    }

    @Override
    public String toString() {
        return "AnalyzeStatus{" +
                "total=" + total +
                ", done=" + done +
                ", run=" + run +
                '}';
    }
}
