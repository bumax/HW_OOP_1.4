package presenter;

import model.DynArray;

import java.util.Random;

public class DemoData {
    public DemoData(){
        Random rnd = new Random();
        // Инициализация и заполнение массива случайными строками:
        strDA = new DynArray<>();
        for (int i = 0; i < 20; i++) {
            StringBuilder str = new StringBuilder();
            for (int j = 1; j < rnd.nextInt(2,15); j++) {
                str.append((char)rnd.nextInt(65,90));
            }
        strDA.add(str.toString());
        }
        // Инициализаци и заполнение массива случайными целочисленными значениями из заранее подготовленного массива:
        Integer[] rndArray = new Integer[20];
        for (int i = 0; i < rndArray.length; i++) {
            rndArray[i] = rnd.nextInt(1,1000);
        }
        intDA = new DynArray<>(rndArray);
        // Инициализаци и заполнение массива случайными числами с плавающей точкой:
        dblDA = new DynArray<>();
        for (int i = 0; i < 5; i++) {
            dblDA.add(rnd.nextDouble(1.0,10.0));
        }
    }
    public DynArray<String> strDA;
    public DynArray<Integer> intDA;
    public DynArray<Double> dblDA;

}
