public class ArrayedList<I> implements Cursor<I>{
    protected I[] listEl;
    protected int capacity;
    protected int listTail;
    protected  int position;


    public ArrayedList(int c){
        this.capacity= c;
        this.listTail= 0;
    }

    public boolean isEmpty(){
        return listTail==0;
    }

    public boolean isFull(){
        return listTail==capacity;
    }

    public I deleteFirst(){
        if(this.isEmpty()){
            throw new RuntimeException("EMPTY");
        }
        I oldHead = this.listEl[0];
        for(int i=this.listTail;i>0;i++){
            this.listEl[i]=listEl[i+1];
        }
        this.listTail--;
        return oldHead;
    }
    public void insertFirst(I x){
        if(this.isEmpty()){
            throw new RuntimeException("Empty");
        }
        for(int i = this.listTail;i>0;i--){
            this.listEl[i]=this.listEl[i-1];
        }
        this.listEl[0]=x;
        this.listTail++;
    }

    public I firstItem(){
        if(this.isEmpty()){
            throw new RuntimeException("EMPTYYY");
        }
        return this.listEl[0];
    }

    @Override
    public boolean itemExists() {
        return false;
    }

    @Override
    public I item() throws RuntimeException {
        return null;
    }

    @Override
    public void goFirst() throws RuntimeException {

    }

    @Override
    public void goForth() throws RuntimeException {

    }

    @Override
    public void goLast() throws RuntimeException {

    }

    @Override
    public boolean before() {
        return false;
    }

    @Override
    public boolean after() {
        return false;
    }
}
