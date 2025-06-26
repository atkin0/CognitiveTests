/**
 * Handle menu selections
 *
 * @author Atkin Rong
 */

public abstract class SelectionHandler {

    protected SelectionHandler nextHandler;

    public void setNextHandler(SelectionHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract void handleSelection(String action);
}
