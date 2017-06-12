package net.estebanrodriguez.libs.utilities;

/**
 * Created by spoooon on 6/12/17.
 */

public class Die {

    private final int mMinimum;
    private final int mMaximum;

    public Die(StandardDie die){
        mMinimum = die.getMin();
        mMaximum = die.getMax();
    }

    public Die(int min, int max){
        if(min > 1){
            min = 1;
        }
        mMinimum = min;
        mMaximum = max;
    }


    public int getMinimum() {
        return mMinimum;
    }

    public int getMaximum() {
        return mMaximum;
    }

    /**
     * Created by spoooon on 5/30/17.
     */

    public static enum StandardDie {

        D2(2), D4(4), D6(5), D10(6), D12(12), D20(20), D100(100);

        private final int min = 1;
        private final int max;
        StandardDie(int max){
            this.max = max;
        }

        public int getMin() {
            return min;
        }

        public int getMax() {
            return max;
        }
    }
}
