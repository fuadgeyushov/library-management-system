package main;

import exceptions.BorrowLimitExceededException;
import exceptions.ItemNotAvailableException;
import models.item.Book;
import models.item.LibraryItem;
import models.item.Magazine;
import models.item.Thesis;
import models.member.BasicMember;
import models.member.GoldMember;
import models.member.Member;
import models.member.SilverMember;
import services.Library;
import utils.SearchResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] mainArgs) {
        Library library = new Library();

        // Əgər fayllar boşdursa, ilkin test məlumatlarını doldurmaq üçün siyahı hazırlayırıq
        List<LibraryItem> initialItems = new ArrayList<>();
        initialItems.add(new Book("B1", "OOP Esaslari", "Resad Memmedov"));
        initialItems.add(new Book("B2", "Java Proqramlasdirma", "Ayan Aliyeva"));
        initialItems.add(new Magazine("M1", "Tech Magazine v.5", "Tech Corp"));
        initialItems.add(new Thesis("T1", "AI in Finance 2026", "Elvin Isayev"));

        List<Member> initialMembers = new ArrayList<>();
        initialMembers.add(new BasicMember("U1", "Kenan"));
        initialMembers.add(new SilverMember("U2", "Leyla"));
        initialMembers.add(new GoldMember("U3", "Ferid"));

        // Faylları ilk dəfə doldurur (əgər fayl boşdursa)
        library.seedInitialData(initialItems, initialMembers);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== KITABXANA SISTEMI (FAYL-BAZALI) =====");
            System.out.println("1. Add New Library Item");
            System.out.println("2. Register New Member");
            System.out.println("3. Borrow Item");
            System.out.println("4. Return Item");
            System.out.println("5. Search Item by Title");
            System.out.println("6. Search Item by Author");
            System.out.println("7. View All Available Items");
            System.out.println("8. View Member Report");
            System.out.println("9. Exit");
            System.out.print("Enter choice: ");

            int choice;
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("❌ Zəhmət olmasa siyahıdakı uyğun rəqəmlərdən birini daxil edin!");
                scanner.nextLine();
                continue;
            }

            if (choice == 9) {
                System.out.println("Sistemdən çıxılır. Sağ olun!");
                break;
            }

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Item Type (1-Book, 2-Magazine, 3-Thesis): ");
                        int type = scanner.nextInt();
                        scanner.nextLine();

                        // Validasiya (Yoxlama) əlavə edirik:
                        if (type < 1 || type > 3) {
                            System.out.println("\n[SİSTEM XƏTASI] -> Yanlış növ seçimi! Yalnız 1, 2 və ya 3 daxil edə bilərsiniz.");
                            break; // case 1-dən çıxır və ana menyuya qayıdır
                        }

                        System.out.print("Enter ID: ");
                        String id = scanner.nextLine();
                        System.out.print("Enter Title: ");
                        String title = scanner.nextLine();
                        System.out.print("Enter Author: ");
                        String author = scanner.nextLine();

                        if (type == 1) library.addItem(new Book(id, title, author));
                        else if (type == 2) library.addItem(new Magazine(id, title, author));
                        else if (type == 3) library.addItem(new Thesis(id, title, author));
                        break;

                    case 2:
                        System.out.print("Member Type (1-Basic, 2-Silver, 3-Gold): ");
                        int mType = scanner.nextInt();
                        scanner.nextLine();

                        // Üzv qeydiyyatı üçün də validasiya şərti əlavə edirik:
                        if (mType < 1 || mType > 3) {
                            System.out.println("\n[SİSTEM XƏTASI] -> Yanlış üzv növü! Yalnız 1, 2 və ya 3 daxil edə bilərsiniz.");
                            break; // case 2-dən çıxır və ana menyuya qayıdır
                        }

                        System.out.print("Enter Member ID: ");
                        String mId = scanner.nextLine();
                        System.out.print("Enter Name: ");
                        String mName = scanner.nextLine();

                        if (mType == 1) library.registerMember(new BasicMember(mId, mName));
                        else if (mType == 2) library.registerMember(new SilverMember(mId, mName));
                        else if (mType == 3) library.registerMember(new GoldMember(mId, mName));
                        break;

                    case 3:
                        System.out.print("Uzv ID daxil edin: ");
                        String bMemberId = scanner.nextLine();
                        System.out.print("Element ID daxil edin: ");
                        String bItemId = scanner.nextLine();
                        library.borrowItem(bMemberId, bItemId);
                        break;

                    case 4:
                        System.out.print("Uzv ID daxil edin: ");
                        String rMemberId = scanner.nextLine();
                        System.out.print("Element ID daxil edin: ");
                        String rItemId = scanner.nextLine();
                        System.out.print("Nece gun sizde qaldi? ");
                        int days = scanner.nextInt();
                        scanner.nextLine();
                        library.returnItem(rMemberId, rItemId, days);
                        break;

                    case 5:
                        System.out.print("Axtarış sözünü daxil edin: ");
                        String tKeyword = scanner.nextLine();
                        SearchResult<LibraryItem> titleResult = new SearchResult<>(library.searchByTitle(tKeyword));
                        titleResult.printResults();
                        break;

                    case 6:
                        System.out.print("Müəllif adını daxil edin: ");
                        String aKeyword = scanner.nextLine();
                        SearchResult<LibraryItem> authorResult = new SearchResult<>(library.searchByAuthor(aKeyword));
                        authorResult.printResults();
                        break;

                    case 7:
                        library.listAllAvailable();
                        break;

                    case 8:
                        System.out.print("Uzv ID daxil edin: ");
                        String repId = scanner.nextLine();
                        library.getMemberReport(repId);
                        break;

                    default:
                        System.out.println("Yanlış seçim! Yenidən yoxlayın.");
                }
            } catch (ItemNotAvailableException | BorrowLimitExceededException e) {
                System.out.println("\n[MƏHDUDİYYƏT XƏTASI] -> " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Gözlənilməz xəta: " + e.getMessage());
            }
        }
        scanner.close();
    }
}