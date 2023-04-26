import model.DynArray;

public class Main {
    public static void main(String[] args) throws Exception {
        DynArray<Integer> data = new DynArray();
        Integer i = 10;
        for (int j = 0; j < i; j++) {
            data.add(j);
        }
        System.out.println(data.getMax());

    }
}