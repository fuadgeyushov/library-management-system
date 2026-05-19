package services;

import exceptions.BorrowLimitExceededException;
import exceptions.ItemNotAvailableException;
import models.item.LibraryItem;
import models.member.Member;
import utils.FileHandler;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Library {
    private Map<String, LibraryItem> catalog;
    private Map<String, Member> members;

    public Library() {
        // Məlumatları birbaşa fayldan yükləyirik!
        this.catalog = FileHandler.loadItems();
        this.members = FileHandler.loadMembers();
    }

    // Test məlumatlarını ilkin olaraq fayla yükləmək üçün köməkçi metod
    public void seedInitialData(List<LibraryItem> initialItems, List<Member> initialMembers) {
        if (catalog.isEmpty() && members.isEmpty()) {
            for (LibraryItem item : initialItems) catalog.put(item.getId(), item);
            for (Member m : initialMembers) members.put(m.getMemberId(), m);
            FileHandler.saveItems(catalog);
            FileHandler.saveMembers(members);
        }
    }

    public void addItem(LibraryItem item) {
        if (catalog.containsKey(item.getId())) {
            System.out.println("\n[SİSTEM XƏTASI] -> '" + item.getId() + "' ID-li element artıq sistemdə mövcuddur!");
            return;
        }
        catalog.put(item.getId(), item);
        FileHandler.saveItems(catalog); // Fayla yadda saxla
        System.out.println("Sistemə əlavə olundu və fayla yazıldı: " + item.getTitle());
    }

    public void registerMember(Member member) {
        if (members.containsKey(member.getMemberId())) {
            System.out.println("\n[SİSTEM XƏTASI] -> '" + member.getMemberId() + "' ID-li üzv artıq mövcuddur!");
            return;
        }
        members.put(member.getMemberId(), member);
        FileHandler.saveMembers(members); // Fayla yadda saxla
        System.out.println("Yeni üzv qeydiyyata alındı və fayla yazıldı: " + member.getName());
    }

    public void borrowItem(String memberId, String itemId) {
        Member member = members.get(memberId);
        LibraryItem item = catalog.get(itemId);

        if (member == null || item == null) {
            System.out.println("Xəta: Üzv və ya Element tapılmadı!");
            return;
        }
        if (!item.isAvailable()) {
            throw new ItemNotAvailableException("Xəta: '" + item.getTitle() + "' artıq götürülüb!");
        }
        if (member.getBorrowedItems().size() >= member.getMaxBorrowLimit()) {
            throw new BorrowLimitExceededException("Xəta: " + member.getName() + " limitə çatıb!");
        }

        item.setAvailable(false);
        member.borrowItem(item);
        FileHandler.saveItems(catalog); // Status dəyişdiyi üçün faylı yenilə
        System.out.println("\nUğurlu! " + member.getName() + " '" + item.getTitle() + "' elementini götürdü.");
    }

    public void returnItem(String memberId, String itemId, int daysKept) {
        Member member = members.get(memberId);
        LibraryItem item = catalog.get(itemId);

        if (member == null || item == null) {
            System.out.println("Xəta: Üzv və ya Element tapılmadı!");
            return;
        }

        if (member.getBorrowedItems().contains(item)) {
            item.setAvailable(true);
            member.returnItem(item);
            FileHandler.saveItems(catalog); // Status dəyişdiyi üçün faylı yenilə
            System.out.println("\nUğurlu! '" + item.getTitle() + "' geri təhvil alındı.");

            if (daysKept > item.getMaxLoanDays()) {
                int overdueDays = daysKept - item.getMaxLoanDays();
                double fine = (overdueDays * 0.25) * member.getFineMultiplier();
                System.out.println("DİQQƏT: Gecikmə var! Cərimə: " + fine + " AZN.");
            }
        } else {
            System.out.println("Xəta: Bu üzv bu elementi borc götürməyib!");
        }
    }

    public List<LibraryItem> searchByTitle(String keyword) {
        List<LibraryItem> found = new ArrayList<>();
        for (LibraryItem item : catalog.values()) {
            if (item.getTitle().toLowerCase().contains(keyword.toLowerCase())) found.add(item);
        }
        return found;
    }

    public List<LibraryItem> searchByAuthor(String keyword) {
        List<LibraryItem> found = new ArrayList<>();
        for (LibraryItem item : catalog.values()) {
            if (item.getAuthor().toLowerCase().contains(keyword.toLowerCase())) found.add(item);
        }
        return found;
    }

    public void listAllAvailable() {
        System.out.println("\n--- RƏFDƏ olan elementlər ---");
        boolean empty = true;
        for (LibraryItem item : catalog.values()) {
            if (item.isAvailable()) {
                item.displayInfo();
                empty = false;
            }
        }
        if (empty) System.out.println("Hazırda rəfdə heç bir element yoxdur.");
    }

    public void getMemberReport(String memberId) {
        Member member = members.get(memberId);
        if (member == null) {
            System.out.println("Xəta: Üzv tapılmadı!");
            return;
        }
        System.out.println("\n===== ÜZV HESABATI =====");
        System.out.println("Adı: " + member.getName());
        System.out.println("Statusu: " + member.getClass().getSimpleName());
        System.out.println("Hazırda əlində olan element sayısı: " + member.getBorrowedItems().size());
        System.out.println("========================");
    }
}