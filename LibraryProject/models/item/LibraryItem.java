package models.item;

public abstract class LibraryItem {
    private String id;        // [cite: 21]
    private String title;     // [cite: 22]
    private String author;    // [cite: 23]
    private boolean isAvailable; // [cite: 24]

    // Konstruktor - element yaradılanda ilkin dəyərləri vermək üçün
    public LibraryItem(String id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isAvailable = true; // Yeni gələn element həmişə birinci rəfdə olur (əlçatandır)
    }

    // Abstrakt metodlar - alt klaslar mütləq bunu özünə görə doldurmalıdır
    public abstract String getItemType();   // [cite: 25]
    public abstract int getMaxLoanDays();   // [cite: 26]

    // Məlumatları ekrana çıxarmaq üçün metod
    public void displayInfo() { // [cite: 27]
        System.out.println("[" + getItemType() + "] ID: " + id + 
                           " | Başlıq: " + title + 
                           " | Müəllif: " + author + 
                           " | Status: " + (isAvailable ? "Rəfdədir" : "Götürülüb"));
    }

    // Getter və Setter-lər (Encapsulation - Kapsulalama tələbi üçün) [cite: 95]
    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }
}