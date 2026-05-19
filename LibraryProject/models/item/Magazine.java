package models.item;

import interfaces.Borrowable;
import models.member.Member;

public class Magazine extends LibraryItem implements Borrowable {

    public Magazine(String id, String title, String author) {
        super(id, title, author);
    }

    @Override
    public String getItemType() { return "Magazine"; } // 

    @Override
    public int getMaxLoanDays() { return 7; } // Jurnal üçün 7 gün 

    @Override
    public void borrowItem(Member member) {
        this.setAvailable(false);
    }

    @Override
    public void returnItem(Member member) {
        this.setAvailable(true);
    }

    @Override
    public double calculateFine(int overdueDays) {
        return overdueDays * 0.25; // Sənin variantına uyğun cərimə [cite: 14]
    }
}