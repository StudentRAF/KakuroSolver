package rs.raf.kakuro.gui.model.attribute;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Combinations {

    private final List<Combination> combinations = new ArrayList<>();

    public Combinations() { }

    public Combinations(Combinations copy) {
        for (Combination combination : copy.getCombinations())
            addCombination(combination.copy());
    }

    public static Combinations fromNotesList(List<Notes> notesList) {
        Combinations combinations = new Combinations();

        for (Notes notes : notesList)
            combinations.addCombination(Combination.fromNotes(notes));

        return combinations;
    }

    public void addCombination(Combination combination) {
        combinations.add(combination);
    }

    public void removeCombination(Combination combination) {
        combinations.remove(combination);
    }

    public void removeValues(int value) {
        for (Combination combination : combinations)
            combination.remove(value);

    }

    public List<Combination> getCombinations() {
        return combinations;
    }

    public Combination getCombination(int index) {
        return combinations.get(index);
    }

    public Combination first() {
        return isEmpty() ? null : combinations.get(0);
    }

    public Combination last() {
        return isEmpty() ? null : combinations.get(combinations.size() - 1);
    }

    public void removeFirst() {
        if (isEmpty())
            return;

        combinations.remove(0);
    }

    public void removeLast() {
        if (isEmpty())
            return;

        combinations.remove(size() - 1);
    }

    public boolean isEmpty() {
        return combinations.size() == 0;
    }

    public int size() {
        return combinations.size();
    }

    public void clear() {
        combinations.clear();
    }

    public void sort() {
        for (Combination combination : combinations)
            combination.sort();

        Collections.sort(combinations);
    }

    public Combinations copy() {
        return new Combinations(this);
    }

    public boolean contains(Combination combination) {
        Combinations combinationsCopy = copy();
        combinationsCopy.sort();

        Combination combinationCopy = combination.copy();
        combinationCopy.sort();

        return combinationsCopy.getCombinations().contains(combinationCopy);
    }

}
