package models.member;

import models.item.LibraryItem;
import java.util.ArrayList;
import java.util.List;

public abstract class Member {
    private String memberId;
    private String name;
    private List<LibraryItem> borrowedItems; // Üzvün götürdüyü kitabların siyahısı [cite: 43]

    // Konstruktor
    public Member(String memberId, String name) {
        this.memberId = memberId;
        this.name = name;
        this.borrowedItems = new ArrayList<>(); // Siyahını ilkin olaraq boş yaradırıq
    }

    // Abstrakt metodlar - Hər üzv tipi öz limitini və cərimə dərəcəsini təyin edəcək [cite: 44, 45]
    public abstract int getMaxBorrowLimit();
    public abstract double getFineMultiplier();

    // Kitab götürmək üçün metod [cite: 46]
    public void borrowItem(LibraryItem item) {
        borrowedItems.add(item);
    }

    // Kitabı qaytarmaq üçün metod [cite: 47]
    public void returnItem(LibraryItem item) {
        borrowedItems.remove(item);
    }

    // Getter-lər (Kapsulalama üçün)
    public String getMemberId() { return memberId; }
    public String getName() { return name; }
    public List<LibraryItem> getBorrowedItems() { return borrowedItems; }
}