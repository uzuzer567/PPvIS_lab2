package sample;

import java.util.List;

public class Page<T> {
    private List<Tournament> pageData;
    private int pageNumber;
    private int pageCount;
    private int totalRecordsCount;

    public Page(int pageNumber, List<Tournament> pageData, int pageCount, int totalRecordsCount) {
        this.pageNumber = pageNumber;
        this.pageData = pageData;
        this.pageCount = pageCount;
        this.totalRecordsCount = totalRecordsCount;
    }

    public int getPageCount() {
        return pageCount;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public int getTotalRecordsCount() {
        return totalRecordsCount;
    }

    public List<Tournament> getPatients() {
        return pageData;
    }
}
