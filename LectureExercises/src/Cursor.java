public interface Cursor<I> {

    public  boolean itemExists();

    public I item() throws RuntimeException;

    public void goFirst() throws RuntimeException;

    public void goForth() throws RuntimeException;

    public void goLast() throws RuntimeException;

    public boolean before();

    public boolean after();

}
