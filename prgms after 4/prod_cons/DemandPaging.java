class Page {
    int pageNumber;
    int frameNumber;
    boolean validBit;

    public Page(int pageNumber) {
        this.pageNumber = pageNumber;
        this.frameNumber = -1;
        this.validBit = false;
    }
}

class Memory {
    Page[] pages;

    public Memory(int numberOfFrames) {
        this.pages = new Page[numberOfFrames];
    }

    public void addPage(Page page, int frameNumber) {
        this.pages[frameNumber] = page;
        page.frameNumber = frameNumber;
        page.validBit = true;
    }

    public Page getPage(int pageNumber) {
        for (Page page : pages) {
            if (page!= null && page.pageNumber == pageNumber) {
                return page;
            }
        }
        return null;
    }
}

public class DemandPaging {
    public static void main(String[] args) {
        Memory memory = new Memory(10);

        // Simulate page access
        for (int i = 0; i < 20; i++) {
            int pageNumber = (int) (Math.random() * 20);
            Page page = memory.getPage(pageNumber);

            if (page == null) {
                // Page not found in memory, load it from disk
                System.out.println("Page Fault");
                page = new Page(pageNumber);
                memory.addPage(page, (int) (Math.random() * 10));
            }

            // Access the page
            System.out.println("Accessing page " + pageNumber);
        }
    }
}
