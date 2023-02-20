import java.util.Arrays;

public class ObjectReverser<E> {
    private E[] obj;

    public ObjectReverser(E[] obj) {
        this.obj = obj;
    }

    public void reverseObject() {
        if (obj == null) {
            throw new IllegalArgumentException();
        }
        E[] objRev = (E[]) new Object[obj.length];
        for (int i = 0; i < obj.length; i++) {
            objRev[i] = obj[obj.length - (1 + i)];
        }
        this.obj = objRev;
    }

    public E[] getObj() {
        return obj.clone();
    }

    public static boolean runTest() {
        Integer[] obj1 = new Integer[] { 1, 2, 3, 4, 5 };
        ObjectReverser rev = new ObjectReverser(obj1);
        rev.reverseObject();
        if (!Arrays.equals(rev.getObj(), new Integer[] { 5, 4, 3, 2, 1 })) {
            return false;
        }
        return true;

    }

    public static void main(String[] args) {
        System.out.println(runTest());
    }

}