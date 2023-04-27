package model;

import java.util.Arrays;
import java.util.Comparator;

public class DynArray<T> {
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

    /**
     * Поиск минимального значения
     *
     * @param comparator
     * @return
     */
    public T min(java.util.Comparator<? super T> comparator) {
        T minRes = db[0];
        for (int i = 1; i < size; i++) {
            if (comparator.compare(minRes, db[i]) > 0)
                minRes = db[i];
        }
        return minRes;
    }

    /**
     * Поиск максимального значения
     *
     * @param comparator
     * @return
     */
    public T max(java.util.Comparator<? super T> comparator) {
        T maxRes = db[0];
        for (int i = 1; i < size; i++) {
            if (comparator.compare(maxRes, db[i]) < 0)
                maxRes = db[i];
        }
        return maxRes;
    }

    /**
     * Суммирование элементов массива, если это возможно
     *
     * @return Сумма элементов
     * @throws Exception Исключение, при несоответствии типа
     */
    public T sum() throws Exception {
        if (db[0] instanceof Long || db[0] instanceof Integer || db[0] instanceof Short || db[0] instanceof Byte) {
            Long sumRes = 0L;
            for (int i = 0; i < size; i++)
                sumRes += ((Number) db[i]).longValue();
            return (T) sumRes;
        } else if (db[0] instanceof Double || db[0] instanceof Float) {
            Double sumRes = 0.0;
            for (int i = 0; i < size; i++)
                sumRes += ((Number) db[i]).doubleValue();
            return (T) sumRes;
        } else
            throw new Exception(db[0].getClass().getSimpleName() + " невозможно суммировать");
    }

    /**
     * Перемножение элементов массива, Overflow не учитывается
     *
     * @return
     * @throws Exception
     */
    public T mul() throws Exception {
        if (db[0] instanceof Long || db[0] instanceof Integer || db[0] instanceof Short || db[0] instanceof Byte) {
            Long mulRes = 1L;
            for (int i = 0; i < size; i++)
                mulRes *= ((Number) db[i]).longValue();
            return (T) mulRes;
        } else if (db[0] instanceof Double || db[0] instanceof Float) {
            Double mulRes = 1.0;
            for (int i = 0; i < size; i++)
                mulRes *= ((Number) db[i]).doubleValue();
            return (T) mulRes;
        } else
            throw new Exception(db[0].getClass().getSimpleName() + " невозможно суммировать");
    }

    /**
     * Поиск индекса заданного элемента в массиве
     *
     * @param element
     * @return Индекс массива, если нет совпадений - возвращает -1
     */
    public Integer find(T element) {
        for (int i = 0; i < size; i++) {
            if (element.equals(this.db[i])) return i;
        }
        return -1;
    }

    /**
     * Проверка наличия элемента в массиве
     *
     * @param element
     * @return
     */
    public boolean isExist(T element) {
        return this.find(element) != -1;
    }

    /**
     * Сортировка пузырьком
     *
     * @param comparator
     */
    public void bubbleSort(java.util.Comparator<? super T> comparator) {
        for (int j = 1; j < size; j++) {
            Boolean isSorted = true;
            for (int i = 0; i < size - j; i++) {
                if (comparator.compare(db[i], db[i + 1]) > 0) {
                    T tmp = db[i];
                    db[i] = db[i + 1];
                    db[i + 1] = tmp;
                    isSorted = false;
                }
            }
            if (isSorted) break;
        }
    }

    /**
     * Сортировка вставками
     *
     * @param comparator
     */
    public void insertionSort(java.util.Comparator<? super T> comparator) {
        for (int j = 1; j < size; j++) {
            T key = db[j];
            int i = j - 1;
            while ((i >= 0 && comparator.compare(db[i], key) > 0)) {
                db[i + 1] = db[i];
                i--;
            }
            db[i + 1] = key;
        }
    }

    /**
     * Сортировка выбором
     *
     * @param comparator
     */
    public void selectionSort(java.util.Comparator<? super T> comparator) {
        for (int i = 0; i < size - 1; ++i) {
            int minPos = i;
            for (int j = i + 1; j < size; ++j) {
                if (comparator.compare(db[j], db[minPos]) < 0) {
                    minPos = j;
                }
            }
            T tmp = db[minPos];
            db[minPos] = db[i];
            db[i] = tmp;
        }
    }

    /**
     * Получение элемента по индеку
     *
     * @param index
     * @return
     */
    public T get(int index) {
        if (index > 0 && index < size) return db[index];
        else return null;
    }

    /**
     * Замена элемента
     *
     * @param element Новый элемент
     * @param index   Индекс
     * @return Старое значение элемента
     */
    public T replace(T element, int index) {
        if (index > 0 && index < size) {
            T oldElement = db[index];
            db[index] = element;
            return oldElement;
        } else return null;
    }

    /**
     * Печать массива на экран
     */
    public void printArray() {
        System.out.println(db);
    }

    /**
     * Получение размера массива
     *
     * @return
     */
    public int size() {
        return this.size;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("[");
        for (int i = 0; i < size; i++) {
            str.append(db[i].toString());
            if (i != size - 1) str.append(", ");
        }
        str.append("]");
        return str.toString();
    }
}
