public class ArrayBlock {

    public static final int razmer_bloka = 5;

    public Object[] dannye;
    public int count;
    public ArrayBlock next;
    public ArrayBlock prev;

    public ArrayBlock() {
        dannye = new Object[razmer_bloka];
        count  = 0;
        next   = null;
        prev   = null;
    }

    public boolean zapolnen() {
        return count == razmer_bloka;
    }
}
