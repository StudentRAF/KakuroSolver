package rs.raf.kakuro.gui.model.attribute;

import java.util.Arrays;

public class Combination implements Comparable<Combination> {

    private final int[] combination;

    private int count = 0;

    public Combination(int size) {
        combination = new int[size];
    }

    public Combination(Combination copy) {
        combination = new int[copy.size()];

        for (int value : copy.getCombination())
            if (value != 0)
                add(value);
    }

    public static Combination fromNotes(Notes notes) {
        Combination combination = new Combination(notes.size());

        for (int index = 0; index < notes.size(); ++index)
            if (notes.get(index))
                combination.add(index + 1);

        return combination;
    }

    public void add(int value) {
        addAt(count, value);
    }

    public void add(int... values) {
        for (int value : values)
            add(value);
    }

    public void add(Combination combination) {
        for (int value : combination.getValues())
            add(value);
    }

    public void addAt(int index, int value) {
        if (count == size() || index > count)
            return;

        shiftRight(index);
        combination[index] = value;
    }

    public void addAt(int index, Combination combination) {
        for (int value : combination.getValues())
            addAt(index++, value);
    }

    public void remove(int value) {
        for (int index = 0; index < count; ++index)
            if (combination[index] == value)
                removeAt(index);

    }

    public void removeAt(int index) {
        if (count == 0 || index >= count)
            return;

        shiftLeft(index);
        combination[count] = 0;
    }

    public void removeLast() {
        removeAt(count - 1);
    }

    private void shiftRight(int index) {
        for (int i = count++; i > index; --i)
            combination[i] = combination[i - 1];
    }

    private void shiftLeft(int index) {
        for (int i = index; i < --count; ++i, ++count)
            combination[i] = combination[i + 1];
    }

    public int[] getCombination() {
        return combination;
    }

    public int[] getValues() {
        int[] values = new int[count];

        for (int i = 0; i < count; ++i)
            values[i] = combination[i];

        return values;
    }

    public int get(int index) {
        if (index < count)
            return combination[index];

        return 0;
    }

    public int first() {
        if (count == 0)
            return 0;

        return combination[0];
    }

    public int last() {
        if (count == 0)
            return 0;

        return combination[count - 1];
    }

    public int size() {
        return combination.length;
    }

    public int count() {
        return count;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public boolean contains(int value) {
        for (int cValue : combination)
            if (cValue == value)
                return true;

        return false;
    }

    public void clear() {
        while (count > 0)
            combination[--count] = 0;
    }

    public void sort() {
        Arrays.sort(combination);
    }

    public Combination copy() {
        return new Combination(this);
    }

    @Override
    public int compareTo(Combination comparator) {
        if (count() != comparator.count())
            return Integer.compare(count(), comparator.count());

        for (int i = 0; i < count(); ++i)
            if (get(i) != comparator.get(i))
                return Integer.compare(get(i), comparator.get(i));

        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Combination combination))
            return false;

        return compareTo(combination) == 0;
    }

}
