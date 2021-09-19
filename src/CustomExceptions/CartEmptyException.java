package CustomExceptions;

public class CartEmptyException extends RuntimeException{
    public String toString(){
        return "!! Cart is Empty !!";
    }
}
