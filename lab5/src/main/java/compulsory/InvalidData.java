package compulsory;

public class InvalidData extends RuntimeException{
    public InvalidData(String message) {
        super(message);
    }
    public InvalidData(String message, CatalogItem o) {
        super(message + o.toString());
    }
}
