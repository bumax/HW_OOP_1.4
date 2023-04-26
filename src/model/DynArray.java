package model;

import java.util.Arrays;
import java.util.Comparator;

public class DynArray<T extends Comparable> {
    public DynArray() {
        this.size = 0;
        this.length = 10;
        this.db = (T[]) new Object[this.length];

    }

    public DynArray(T[] db) {
        this();
        for (T element : db) {
            this.add(element);
        }
    }

    private T[] db;
    private int size;
    private int length;

    /**
     * Добавление элемента в конец массива
     *
     * @param element Элемент
     */
    public void add(T element) {
        checkArrLen();
        db[size++] = element;
    }

    /**
     * Добавление элемента
     *
     * @param element Элемент
     * @param index   Индекс вставки
     */
    public void add(T element, int index) {
        checkArrLen();
        for (int i = size; i > index; i--) {
            db[i] = db[i - 1];
        }
        size++;
        db[index] = element;
    }

    /**
     * Проверка размера массива
     */
    private void checkArrLen() {
        // Если занято больше 80% массива, добавляем 20%
        if (size > 0 && (double) size / length > 0.8) {
            length = (int) (length * 1.2);
            db = Arrays.copyOf(db, length);
        }
        // Если занято менее 30%, то отнимаем 50%
        if (size > 10 && (double) size / length < 0.3) {
            length = (int) (length * 0.5);
            db = Arrays.copyOf(db, length);
        }
    }

    /**
     * Удалеини эелемента из массива по заданному индексу
     *
     * @param index Индекс
     * @return Удаленный элемент
     */
    public T remove(int index) {
        T res = null;
        if (index < size) {
            res = db[index];
            for (int i = index; i < size; i++) {
                db[i] = db[i + 1];
            }
            size--;
            checkArrLen();
        }
        return res;
    }

    /**
     * Удаление всех элементов с заданным значением
     *
     * @param element Элемент для поиска
     * @return Количество удаленных элементов
     */
    public int remove(T element) {
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (db[i].equals(element)) {
                remove(i);
                count++;
            }
        }
        return count;
    }

    public T getMin() throws Exception {
        T min = db[0];
        if (min instanceof Comparator) {
            for (int i = 1; i < size; i++) {
                if(((Comparator<T>) min).compare(min, db[i]) == 1)
                    min = db[i];
            }
            return min;
        }
        else
            throw new Exception("У типа " + min.getClass().getSimpleName() + " нет метода Compare");
    }

    public T getMax() throws Exception {
        T max = db[0];
        if (true) {
            for (int i = 1; i < size; i++) {
                if(max.compareTo(db[i]) == -1)
                    max = db[i];
            }
            return max;
        }
        else
            throw new Exception("У типа " + max.getClass().getSimpleName() + " нет метода Compare");
    }
}
