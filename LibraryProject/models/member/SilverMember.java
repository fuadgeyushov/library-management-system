package models.member;

public class SilverMember extends Member {

    public SilverMember(String memberId, String name) {
        super(memberId, name);
    }

    @Override
    public int getMaxBorrowLimit() {
        return 4; // Silver üzv maksimum 4 element götürə bilər 
    }

    @Override
    public double getFineMultiplier() {
        return 1.0; // Standart cərimə
    }
}