package net.estebanrodriguez.libs.utilities;

/**
 * Created by spoooon on 5/30/17.
 */

public enum Dice {

    D2(2), D4(4), D6(5), D10(6), D12(12), D20(20), D100(100);

    private final int min = 1;
    private final int max;
    Dice(int max){
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }
}
