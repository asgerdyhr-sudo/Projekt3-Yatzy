package models;

import java.util.Arrays;


//Used to calculate the score of throws with 5 dice

public class YatzyResultCalculator {
    private final int[] dice = new int[5];

    public YatzyResultCalculator(Die[] dice) {
        //henter index værdi og gemmer den i int[] dice på index plads
        for (int index = 0; index < dice.length; index++) {
            this.dice[index] = dice[index].getEyes();
        }
        Arrays.sort(this.dice);
    }

    public int upperSectionScore(int eyes) {
        //TODO: Implement upperSectionScore method.
        int score = 0;
        for (int die : dice) {
            if (die == eyes) {
                score += eyes;
            }
        }

        return score;
    }

    public int onePairScore() {
        //TODO: implement onePairScore method.
        for (int index = dice.length - 1; index > 0; index--) {
            if (dice[index] == dice[index - 1]) {
                return dice[index] * 2;
            }
        }
        return 0;
    }

    public int twoPairScore() {
        //TODO: implement twoPairScore method.
        int towPairScore = 0;
        for (int index = dice.length - 1; index > 0; index--) {
            if (dice[index] == dice[index - 1]) {
                if (towPairScore == 0) {
                    towPairScore = dice[index] * 2;
                    index--;
                } else {
                    return towPairScore + dice[index] * 2;
                }
            }
        }
        return 0;
    }

    public int threeOfAKindScore() {
        //TODO: implement threeOfAKindScore method.
        int[] counts = new int[7];
        for (int die : this.dice) {
            counts[die]++;
        }

        for (int index = 1; index < 7; index++) {
            if (counts[index] >= 3) {
                return index * 3;
            }
        }
        return 0;
    }

    public int fourOfAKindScore() {
        //TODO: implement fourOfAKindScore method.
        int[] counts = new int[7];
        for (int die : this.dice) {
            counts[die]++;
        }

        for (int index = 1; index < 7; index++) {
            if (counts[index] >= 4) {
                return index * 4;
            }
        }
        return 0;
    }

    public int smallStraightScore() {
        //TODO: implement smallStraightScore method.
        int[] smallStraight = {1, 2, 3, 4, 5};
        if (Arrays.equals(dice, smallStraight)) {
            return 15;
        }
        return 0;
    }

    public int largeStraightScore() {
        //TODO: implement largeStraightScore method.
        int[] largeStraight = {2, 3, 4, 5, 6};
        if (Arrays.equals(dice, largeStraight)) {
            return 20;
        }
        return 0;
    }

    public int fullHouseScore() {
        //TODO: implement fullHouseScore method.
        int[] counts = new int[7];
        boolean foundThree = false;
        boolean foundTow = false;

        for (int die : this.dice) {
            counts[die]++;
        }

        for (int index = 1; index < 7; index++) {
            if (counts[index] == 3) {
                foundThree = true;
            }
            if (counts[index] == 2) {
                foundTow = true;
            }
        }

        if (foundThree == true && foundTow == true) {
            return chanceScore();
        }
        return 0;
    }

    public int chanceScore() {
        //TODO: implement chanceScore method.
        int sum = 0;
        for (int die : dice) {
            sum += die;
        }
        return sum;
    }

    public int yatzyScore() {
        //TODO: implement yatzyScore method.
        for (int index = 0; index < dice.length - 1; index++) {
            if (dice[index] != dice[index + 1]) {
                return 0;
            }
        }
        return 50;
    }
}
