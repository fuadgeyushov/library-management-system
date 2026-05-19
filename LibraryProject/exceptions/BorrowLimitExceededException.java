package exceptions;

// Üzv öz limitini (məsələn Basic üzv 2 kitab limitini) aşanda bu xətanı yaşadacağıq
public class BorrowLimitExceededException extends RuntimeException {
    public BorrowLimitExceededException(String message) {
        super(message);
    }
}