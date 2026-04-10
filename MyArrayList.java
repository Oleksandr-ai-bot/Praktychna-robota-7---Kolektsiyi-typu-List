public class MyArrayList {

    private ArrayBlock head;
    private int razmer;

    public MyArrayList() {
        head   = new ArrayBlock();
        razmer = 0;
    }

    private ArrayBlock poluchitPosledniyBlok() {
        ArrayBlock tekuschiy = head;
        while (tekuschiy.next != null) {
            tekuschiy = tekuschiy.next;
        }
        return tekuschiy;
    }

    private ArrayBlock poluchitBlokPo(int nomerBloka) {
        ArrayBlock tekuschiy = head;
        for (int i = 0; i < nomerBloka; i++) {
            tekuschiy = tekuschiy.next;
        }
        return tekuschiy;
    }

    private Object poluchitElem(int index) {
        return poluchitBlokPo(index / ArrayBlock.razmer_bloka)
                    .dannye[index % ArrayBlock.razmer_bloka];
    }

    private void ustanovitElem(int index, Object value) {
        poluchitBlokPo(index / ArrayBlock.razmer_bloka)
                    .dannye[index % ArrayBlock.razmer_bloka] = value;
    }

    private void obespechitMesto() {
        ArrayBlock posledniy = poluchitPosledniyBlok();
        if (posledniy.zapolnen()) {
            ArrayBlock noviyBlok = new ArrayBlock();
            noviyBlok.prev  = posledniy;
            posledniy.next  = noviyBlok;
        }
    }

    private void obnovitCount() {
        ArrayBlock tekuschiy = head;
        int ostalos = razmer;
        while (tekuschiy != null) {
            tekuschiy.count = Math.min(ostalos, ArrayBlock.razmer_bloka);
            ostalos -= tekuschiy.count;
            tekuschiy = tekuschiy.next;
        }
    }

    private void udalitLishniyeBloki() {
        int neobhodimo = (razmer == 0) ? 1
                : (razmer + ArrayBlock.razmer_bloka - 1) / ArrayBlock.razmer_bloka;
        ArrayBlock tekuschiy = head;
        for (int i = 1; i < neobhodimo; i++) {
            tekuschiy = tekuschiy.next;
        }
        if (tekuschiy.next != null) {
            tekuschiy.next.prev = null;
            tekuschiy.next      = null;
        }
    }

    public void addLast(Object value) {
        obespechitMesto();
        ustanovitElem(razmer, value);
        razmer++;
        obnovitCount();
    }

    public void addFirst(Object value) {
        addAt(0, value);
    }

    public void addAt(int index, Object value) {
        if (index < 0 || index > razmer) {
            throw new IndexOutOfBoundsException(
                "Index " + index + " za predelami [0, " + razmer + "]");
        }
        if (index == razmer) {
            addLast(value);
            return;
        }
        obespechitMesto();
        for (int i = razmer; i > index; i--) {
            ustanovitElem(i, poluchitElem(i - 1));
        }
        ustanovitElem(index, value);
        razmer++;
        obnovitCount();
    }

    public Object get(int index) {
        if (index < 0 || index >= razmer) {
            throw new IndexOutOfBoundsException(
                "Index " + index + " za predelami [0, " + (razmer - 1) + "]");
        }
        return poluchitElem(index);
    }

    public int razmer()    { return razmer; }

    public int capacity() {
        int itog = 0;
        ArrayBlock tekuschiy = head;
        while (tekuschiy != null) {
            itog += ArrayBlock.razmer_bloka;
            tekuschiy = tekuschiy.next;
        }
        return itog;
    }

    public Object udalitPo(int index) {
        if (index < 0 || index >= razmer) {
            throw new IndexOutOfBoundsException(
                "Index " + index + " za predelami [0, " + (razmer - 1) + "]");
        }
        Object removed = poluchitElem(index);
        for (int i = index; i < razmer - 1; i++) {
            ustanovitElem(i, poluchitElem(i + 1));
        }
        ustanovitElem(razmer - 1, null);
        razmer--;
        udalitLishniyeBloki();
        obnovitCount();
        return removed;
    }

    public void ochistit() {
        head   = new ArrayBlock();
        razmer = 0;
    }

    @Override
    public String toString() {
        StringBuilder stroka = new StringBuilder();
        stroka.append("MyArrayList { razmer=").append(razmer)
              .append(", capacity=").append(capacity())
              .append(" } => [");
        for (int i = 0; i < razmer; i++) {
            if (i > 0) stroka.append(", ");
            stroka.append(poluchitElem(i));
        }
        stroka.append("]");
        return stroka.toString();
    }
}
