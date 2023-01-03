package rs.raf.kakuro.gui.solver;

import rs.raf.kakuro.gui.model.attribute.Combination;
import rs.raf.kakuro.gui.model.attribute.Combinations;
import rs.raf.kakuro.gui.model.attribute.Notes;

import java.util.List;

public class Algorithms {

    public static int minimumSumForNumberOfDigits(int digitCount) {
        return digitCount * (digitCount + 1) / 2;
    }

    public static int minimumSumForNumberOfDigitsStartingWith(int digitCount, int startDigit) {
        return minimumSumForNumberOfDigits(digitCount) + digitCount * (startDigit - 1);
    }

    public static int maximumSumForNumberOfDigits(int digitCount) {
        return digitCount * (19 - digitCount) / 2;
    }

    public static Combinations combinationsOfNonRepeatingDigitsWithSum(int sum, int digitCount) {
        return combinationsOfNonRepeatingDigitsWithSum(sum, digitCount, 1);
    }

    private static Combinations combinationsOfNonRepeatingDigitsWithSum(int sum, int digitCount, int startDigit) {
        if (sum > maximumSumForNumberOfDigits(digitCount) || sum < minimumSumForNumberOfDigitsStartingWith(digitCount, startDigit))
            return new Combinations();

        Combinations combinations = new Combinations();

        if (digitCount == 1) {
            Combination newCombination = new Combination(digitCount);

            newCombination.add(sum);
            combinations.addCombination(newCombination);

            return combinations;
        }

        for (int value = startDigit; value <= 10 - digitCount; ++value) {
            int newSum = sum - value;

            for (Combination combination : combinationsOfNonRepeatingDigitsWithSum(newSum, digitCount - 1, value + 1).getCombinations()) {
                Combination newCombination = new Combination(digitCount);

                newCombination.add(value);
                newCombination.add(combination);

                combinations.addCombination(newCombination);
            }
        }

        return combinations;
    }

    public static void removeExcessCombinationsAndNotes(int sum, int digitCount, Combinations combinations, List<Notes> notesList) {
        Combinations allowedNoteCombinations = Combinations.fromNotesList(notesList);

        combinations.clear();
        combinations.addCombination(new Combination(digitCount));

        for (Notes notes : notesList)
            notes.clear();

        removeExcessCombinationsAndNotes(sum, digitCount, combinations, notesList, allowedNoteCombinations, new Combination(digitCount));

        if (combinations.last().count() < digitCount)
            combinations.removeLast();

        combinations.sort();
    }

    private static void removeExcessCombinationsAndNotes(int sum, int digitCount, Combinations combinations, List<Notes> notesList, Combinations allowedNoteCombinations, Combination path) {
        Combination allowedNoteCombination = allowedNoteCombinations.getCombination(0);

        if (digitCount == 1) {
            if (!allowedNoteCombination.contains(sum) || combinations.last().contains(sum))
                return;

            Combination combination = combinations.last();
            combination.add(sum);
            combinations.removeLast();

            for (int i = 0; i < notesList.size(); ++i)
                notesList.get(i).setActive(combination.get(i) - 1);

            if (!combinations.contains(combination))
                combinations.addCombination(combination);

            return;
        }

        for (int value : allowedNoteCombination.getValues()) {
            Combinations newAllowedNoteCombinations = allowedNoteCombinations.copy();

            newAllowedNoteCombinations.removeCombination(allowedNoteCombination);
            newAllowedNoteCombinations.removeValues(value);

            if (combinations.last().count() == notesList.size())
                combinations.addCombination(path.copy());

            if (path.count() < notesList.size() - digitCount)
                path.add(combinations.last().last());

            combinations.last().add(value);

            removeExcessCombinationsAndNotes(sum - value, digitCount - 1, combinations, notesList, newAllowedNoteCombinations, path);

            if (path.count() > notesList.size() - digitCount)
                path.removeLast();

            if (combinations.last().count() < notesList.size())
                combinations.last().removeLast();
        }
    }

}
