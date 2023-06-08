package SmartEnemies.util;

import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;

public class EnemyUtils {
    public static boolean lastMove(ArrayList<Byte> moveList, byte move) {
        return !moveList.isEmpty() && moveList.get(moveList.size() - 1) == move;
    }

    public static boolean lastTwoMoves(ArrayList<Byte> moveList, byte move) {
        return moveList.size() >= 2 && moveList.get(moveList.size() - 1) == move && moveList.get(moveList.size() - 2) == move;
    }

    public static int powerAmount(AbstractMonster monster, String id) {
        AbstractPower power = monster.getPower(id);
        if (power == null) {
            return 0;
        } else {
            return power.amount;
        }
    }
}
