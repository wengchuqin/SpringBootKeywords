package top.chuqin.keywords.utils;

public class Progress {
    private boolean isStart;
    private boolean hasDone;
    private long total;
    private long completion;

    public Progress() {
    }

    public boolean isStart() {
        return isStart;
    }

    public void setStart(boolean start) {
        isStart = start;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getCompletion() {
        return completion;
    }

    public void setCompletion(long completion) {
        this.completion = completion;
    }

    public boolean isHasDone() {
        return hasDone;
    }

    public void setHasDone(boolean hasDone) {
        this.hasDone = hasDone;
    }

    @Override
    public String toString() {
        return "Progress{" +
                "isStart=" + isStart +
                ", hasDone=" + hasDone +
                ", total=" + total +
                ", completion=" + completion +
                '}';
    }
}
