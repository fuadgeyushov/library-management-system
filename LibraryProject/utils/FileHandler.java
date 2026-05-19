package utils;

import models.item.Book;
import models.item.LibraryItem;
import models.item.Magazine;
import models.item.Thesis;
import models.member.BasicMember;
import models.member.GoldMember;
import models.member.Member;
import models.member.SilverMember;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class FileHandler {
    private static final String ITEMS_FILE = "data/items.txt";
    private static final String MEMBERS_FILE = "data/members.txt";

    // Bütün kitabları fayla yazmaq
    public static void saveItems(Map<String, LibraryItem> catalog) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ITEMS_FILE))) {
            for (LibraryItem item : catalog.values()) {
                writer.println(item.getId() + "," + item.getTitle() + "," + item.getAuthor() + "," + item.isAvailable() + "," + item.getItemType());
            }
        } catch (IOException e) {
            System.out.println("Fayla yazılarkən xəta: " + e.getMessage());
        }
    }

    // Kitabları fayldan oxumaq
    public static Map<String, LibraryItem> loadItems() {
        Map<String, LibraryItem> catalog = new HashMap<>();
        File file = new File(ITEMS_FILE);
        if (!file.exists()) return catalog;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 5) continue;
                String id = parts[0];
                String title = parts[1];
                String author = parts[2];
                boolean isAvailable = Boolean.parseBoolean(parts[3]);
                String type = parts[4];

                LibraryItem item;
                if (type.equals("Magazine")) {
                    item = new Magazine(id, title, author);
                } else if (type.equals("Thesis")) {
                    item = new Thesis(id, title, author);
                } else {
                    item = new Book(id, title, author);
                }
                item.setAvailable(isAvailable);
                catalog.put(id, item);
            }
        } catch (IOException e) {
            System.out.println("Fayldan oxunarkən xəta: " + e.getMessage());
        }
        return catalog;
    }

    // Bütün üzvləri fayla yazmaq
    public static void saveMembers(Map<String, Member> members) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(MEMBERS_FILE))) {
            for (Member m : members.values()) {
                String type = m.getClass().getSimpleName();
                writer.println(m.getMemberId() + "," + m.getName() + "," + type);
            }
        } catch (IOException e) {
            System.out.println("Üzvlər fayla yazılarkən xəta: " + e.getMessage());
        }
    }

    // Üzvləri fayldan oxumaq
    public static Map<String, Member> loadMembers() {
        Map<String, Member> members = new HashMap<>();
        File file = new File(MEMBERS_FILE);
        if (!file.exists()) return members;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 3) continue;
                String id = parts[0];
                String name = parts[1];
                String type = parts[2];

                Member member;
                if (type.equals("SilverMember")) {
                    member = new SilverMember(id, name);
                } else if (type.equals("GoldMember")) {
                    member = new GoldMember(id, name);
                } else {
                    member = new BasicMember(id, name);
                }
                members.put(id, member);
            }
        } catch (IOException e) {
            System.out.println("Fayldan oxunarkən xəta: " + e.getMessage());
        }
        return members;
    }
}