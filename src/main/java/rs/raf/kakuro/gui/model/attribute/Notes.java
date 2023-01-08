package rs.raf.kakuro.gui.model.attribute;

public class Notes {

    private final boolean[] notes;

    private int count = 0;

    public Notes() {
        this(false, false, false, false, false, false, false, false, false);
    }

    public Notes(boolean... notes) {
        this.notes = new boolean[] { false, false, false, false, false, false, false, false, false };

        for (int i = 0; i < notes.length; ++i)
            if (notes[i])
                setActive(i);
    }

    public Notes(Notes copy) {
        notes = new boolean[] { false, false, false, false, false, false, false, false, false };

        for (int i = 0; i < copy.size(); ++i)
            if (copy.get(i))
                setActive(i);
    }

    public static Notes fromCombination(Combination combination) {
        Notes notes = new Notes();

        for (int value : combination.getCombination())
            notes.setActive(value - 1);

        return notes;
    }

    public static Notes fromCombinations(Combinations combinations) {
        Notes notes = new Notes();

        for (Combination combination : combinations.getCombinations())
            notes.disjunction(fromCombination(combination));

        return notes;
    }

    public void set(int index, boolean value) {
        if (index >= size() || index  < 0)
            return;

        count += Boolean.compare(!notes[index] && value, notes[index] && !value);
        notes[index] = value;
    }

    public void set(boolean... values) {
        for (int index = 0; index < values.length; ++index)
            set(index, values[index]);
    }

    public void set(Notes notes) {
        set(notes.getValues());
    }

    public void setActive(int index) {
        if (index >= size() || index < 0)
            return;

        count += Boolean.compare(true, notes[index]);
        notes[index] = true;
    }

    public void setActive(int... indexes) {
        for (int index : indexes)
            setActive(index);
    }

    public void setInactive(int index) {
        if (index >= size() || index < 0)
            return;

        count += Boolean.compare(false, notes[index]);
        notes[index] = false;
    }

    public void setInactive(int... indexes) {
        for (int index : indexes)
            setInactive(index);
    }

    public void conjunction(int index, boolean value) {
        if (index >= size() || index < 0)
            return;

        count += Boolean.compare(false, notes[index] && !value);
        notes[index] &= value;
    }

    public void conjunction(boolean... values) {
        for (int index = 0; index < values.length; ++index)
            conjunction(index, values[index]);
    }

    public void conjunction(Notes notes) {
        conjunction(notes.getValues());
    }

    public void disjunction(int index, boolean value) {
        if (index >= size() || index < 0)
            return;

        count += Boolean.compare(!notes[index] && value, false);
        notes[index] |= value;
    }

    public void disjunction(boolean... values) {
        for (int index = 0; index < values.length; ++index)
            disjunction(index, values[index]);
    }

    public void disjunction(Notes notes) {
        disjunction(notes.getValues());
    }

    public void reverse(int index) {
        if (index >= size() || index < 0)
            return;

        count += Boolean.compare(notes[index] = !notes[index], !notes[index]);
    }

    public void reverse(int... indexes) {
        for (int index : indexes)
            reverse(index);
    }

    public void clear() {
        for (int index = 0; index < size(); ++index)
            setInactive(index);
    }

    public Notes copy() {
        return new Notes(this);
    }

    public boolean get(int index) {
        return notes[index];
    }

    public int[] getActiveIndexes() {
        int[] activeIndexes = new int[activeCount()];
        int count = 0;

        for (int index = 0; index < size(); ++index)
            if (notes[index])
                activeIndexes[count++] = index;

        return activeIndexes;
    }

    public boolean[] getValues() {
        return notes;
    }

    public int activeCount() {
        return count;
    }

    public int inactiveCount() {
        return size() - count;
    }

    public int size() {
        return notes.length;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Notes notes))
            return false;

        for (int index = 0; index < size(); ++index)
            if (get(index) != notes.get(index))
                return false;

        return true;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int index : getActiveIndexes())
            stringBuilder.append(index + 1).append(" | ");

        stringBuilder.delete(stringBuilder.length() - 3, stringBuilder.length() - 1);

        return stringBuilder.toString();
    }

}
