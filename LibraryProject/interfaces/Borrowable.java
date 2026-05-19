package interfaces;

import models.member.Member;

public interface Borrowable {
    void borrowItem(Member member);     // Elementi üzvə borc vermək [cite: 34]
    void returnItem(Member member);     // Elementi üzvdən geri təhvil almaq [cite: 35]
    double calculateFine(int overdueDays); // Cəriməni hesablamaq [cite: 36]
}