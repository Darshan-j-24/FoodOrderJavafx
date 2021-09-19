package CustomExceptions;

public class AlreadyInsideCartException extends RuntimeException{
    public String toString(){
        return "!! Already Inside Cart !!";
    }
}