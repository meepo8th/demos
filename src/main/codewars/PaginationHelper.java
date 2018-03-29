package codewars;

import java.util.List;

// TODO: complete this object/class

public class PaginationHelper<I> {
    private List<I> content;
    private int pageCount;
    private int pages = -1;

    /**
     * The constructor takes in an array of items and a integer indicating how many
     * items fit within a single page
     */
    public PaginationHelper(List<I> collection, int itemsPerPage) {
        this.content = collection;
        this.pageCount = itemsPerPage;
        if (null != collection) {
            pages = collection.size() / itemsPerPage + (collection.size() % itemsPerPage == 0 ? 0 : 1);
        }
    }

    /**
     * returns the number of items within the entire collection
     */
    public int itemCount() {
        return null != content ? content.size() : -1;
    }

    /**
     * returns the number of pages
     */
    public int pageCount() {
        return pages;
    }

    /**
     * returns the number of items on the current page. page_index is zero based.
     * this method should return -1 for pageIndex values that are out of range
     */
    public int pageItemCount(int pageIndex) {
        int rtn = -1;
        if (pageIndex < pages - 1) {
            rtn = pageCount;
        } else if (pageIndex == pages - 1) {
            rtn = content.size() % pageCount == 0 ? pageCount : content.size() % pageCount;
        }
        return rtn;
    }

    /**
     * determines what page an item is on. Zero based indexes
     * this method should return -1 for itemIndex values that are out of range
     */
    public int pageIndex(int itemIndex) {
        int rtn = -1;
        if (itemIndex >= 0 && itemIndex < content.size()) {
            rtn = itemIndex / pageCount;
        }
        return rtn;
    }
}