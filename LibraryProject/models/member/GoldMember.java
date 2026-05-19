package models.member;

public class GoldMember extends Member {

    public GoldMember(String memberId, String name) {
        super(memberId, name);
    }

    @Override
    public int getMaxBorrowLimit() {
        return 6; // Gold üzv maksimum 6 element götürə bilər 
    }

    @Override
    public double getFineMultiplier() {
        return 1.0; // Standart cərimə
    }
}