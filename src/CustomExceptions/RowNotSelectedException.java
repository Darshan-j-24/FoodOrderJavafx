package CustomExceptions;

public class RowNotSelectedException extends RuntimeException{
    public String toString(){
        return "!! Please select row to apply specific action !!";
    }
}
