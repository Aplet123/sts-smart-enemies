package SmartEnemies.util;

import com.megacrit.cardcrawl.random.Random;

public class MinRandom extends Random {
    public MinRandom() {
    }

    @Override
    public Random copy() {
        return new MinRandom();
    }

    @Override
    public void setCounter(int target) {
    }

    @Override
    public int random(int range) {
        return 0;
    }

    @Override
    public int random(int start, int end) {
        return start;
    }

    @Override
    public long random(long range) {
        return 0;
    }

    @Override
    public long random(long start, long end) {
        return start;
    }

    @Override
    public long randomLong() {
        return Long.MIN_VALUE;
    }

    @Override
    public boolean randomBoolean() {
        return false;
    }

    @Override
    public boolean randomBoolean(float chance) {
        return false;
    }

    @Override
    public float random() {
        return 0.0f;
    }

    @Override
    public float random(float range) {
        return 0.0f;
    }

    @Override
    public float random(float start, float end) {
        return start;
    }
}
