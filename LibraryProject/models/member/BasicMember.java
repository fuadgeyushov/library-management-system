package models.member;

public class BasicMember extends Member {

    public BasicMember(String memberId, String name) {
        super(memberId, name);
    }

    @Override
    public int getMaxBorrowLimit() {
        return 2; // Basic üzv maksimum 2 element götürə bilər 
    }

    @Override
    public double getFineMultiplier() {
        return 1.0; // Standart cərimə dərəcəsi
    }
}