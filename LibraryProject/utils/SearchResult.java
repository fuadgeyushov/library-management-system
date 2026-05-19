package utils;

import models.item.LibraryItem;
import java.util.List;

public class SearchResult<T extends LibraryItem> {
    private List<T> results;

    public SearchResult(List<T> results) {
        this.results = results;
    }

    public List<T> getResults() {
        return results;
    }

    // Main-in axtardığı və xəta verən metod tam olaraq budur:
    public void printResults() {
        if (results == null || results.isEmpty()) {
            System.out.println("Axtarışa uyğun heç bir nəticə tapılmadı.");
            return;
        }
        System.out.println("\n--- Axtarış Nəticələri ---");
        for (T item : results) {
            item.displayInfo();
        }
        System.out.println("--------------------------");
    }
}