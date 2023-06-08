package SmartEnemies.util;

import com.megacrit.cardcrawl.random.Random;

public class MaxRandom extends Random {
    public MaxRandom() {
    }

    @Override
    public Random copy() {
        return new MaxRandom();
    }

    @Override
    public void setCounter(int target) {
    }

    @Override
    public int random(int range) {
        return range;
    }

    @Override
    public int random(int start, int end) {
        return end;
    }

    @Override
    public long random(long range) {
        return range;
    }

    @Override
    public long random(long start, long end) {
        return end;
    }

    @Override
    public long randomLong() {
        return Long.MAX_VALUE;
    }

    @Override
    public boolean randomBoolean() {
        return true;
    }

    @Override
    public boolean randomBoolean(float chance) {
        return true;
    }

    @Override
    public float random() {
        return 1.0f - Float.MIN_NORMAL;
    }

    @Override
    public float random(float range) {
        return Math.max(0.0f, range - Float.MIN_NORMAL);
    }

    @Override
    public float random(float start, float end) {
        return Math.max(start, end - Float.MIN_NORMAL);
    }
}
