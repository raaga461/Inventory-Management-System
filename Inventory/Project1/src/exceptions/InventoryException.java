package exceptions;

public class InventoryException extends Exception {
    @SuppressWarnings("compatibility:-574598374737542760")
    private static final long serialVersionUID = 1L;

    public InventoryException() {
        
    }
    public InventoryException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public InventoryException(String message)
    {
        super(message);
    }

    public InventoryException(Throwable cause)
    {
        super(cause);
    }

  
}
