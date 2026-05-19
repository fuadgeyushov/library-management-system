package exceptions;

// Əgər kitab rəfdə yoxdursa və kimisə onu götürmək istəyirsə bu xətanı yaşadacağıq
public class ItemNotAvailableException extends RuntimeException {
    public ItemNotAvailableException(String message) {
        super(message);
    }
}