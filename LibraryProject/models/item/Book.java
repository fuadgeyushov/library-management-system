package models.item;

import interfaces.Borrowable;
import models.member.Member;

public class Book extends LibraryItem implements Borrowable {

    public Book(String id, String title, String author) {
        super(id, title, author);
    }

    @Override
    public String getItemType() { return "Book"; } // 

    @Override
    public int getMaxLoanDays() { return 14; } // Kitab üçün 14 gün 

    @Override
    public void borrowItem(Member member) {
        this.setAvailable(false); // Kitab götürüldü, artıq rəfdə deyil
    }

    @Override
    public void returnItem(Member member) {
        this.setAvailable(true); // Kitab qaytarıldı, yenidən rəfdədir
    }

    @Override
    public double calculateFine(int overdueDays) {
        // Sənin variantında (Qalıq 1) günə cərimə 0.25 AZN-dir [cite: 14]
        return overdueDays * 0.25; 
    }
}