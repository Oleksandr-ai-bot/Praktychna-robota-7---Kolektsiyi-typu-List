public class Demo {

    private static void pokazat(String napis, MyArrayList spisok) {
        System.out.println(napis);
        System.out.println(spisok);
        System.out.println();
    }

    public static void run() {
        MyArrayList spisok = new MyArrayList();

        spisok.addLast(10); spisok.addLast(20); spisok.addLast(30);
        spisok.addLast(40); spisok.addLast(50); spisok.addLast(60); spisok.addLast(70);
        pokazat("1. Dodavannya elementu v kinets' strukturi danih", spisok);

        spisok.addAt(4, 99);
        pokazat("2. Dodavannya elementu v seredinu", spisok);

        spisok.addFirst(5);
        pokazat("3. Dodavannya elementu v pochatok strukturi danih", spisok);

        System.out.println("4. Otrimannya elementu za indeksom");
        System.out.println("get(0) = " + spisok.get(0));
        System.out.println("get(4) = " + spisok.get(4));
        System.out.println("get(" + (spisok.razmer()-1) + ") = " + spisok.get(spisok.razmer()-1));
        System.out.println();

        System.out.println("5. Otrimannya metrik strukturi danih (size ta capacity)");
        System.out.println("razmer   = " + spisok.razmer());
        System.out.println("capacity = " + spisok.capacity());
        System.out.println();

        Object removed = spisok.udalitPo(4);
        System.out.println("6. Vidalennya elementu za indeksom");
        System.out.println("Udalyon: " + removed);
        System.out.println(spisok);
        System.out.println();

        spisok.ochistit();
        pokazat("7. Vidschinennya strukturi danih (vidalennya vsih elementiv)", spisok);

        System.out.println("8. Test IndexOutOfBoundsException");
        try {
            spisok.get(0);
        } catch (IndexOutOfBoundsException oshibka) {
            System.out.println("Perekhvacheno: " + oshibka.getMessage());
        }
    }
}
