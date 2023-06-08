package SmartEnemies.patches.act1;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.monsters.exordium.JawWorm;

import java.util.ArrayList;

import static SmartEnemies.util.EnemyUtils.*;
import static com.megacrit.cardcrawl.monsters.AbstractMonster.Intent;

@SpirePatch(clz = JawWorm.class, method = "getMove")
public class JawWormPatch {
    final static byte ATTACK = 1;
    final static byte ATTACK_BLOCK = 3;
    final static byte BUFF = 2;

    public static void Replace(JawWorm __instance, int num) {
        ArrayList<Byte> moveHistory = __instance.moveHistory;
        if (moveHistory.isEmpty()) {
            __instance.setMove(ATTACK, Intent.ATTACK, __instance.damage.get(0).base);
        } else {
            int str = powerAmount(__instance, "Strength");
            if (str < 10) {
                if (lastMove(moveHistory, BUFF)) {
                    __instance.setMove(ATTACK, Intent.ATTACK, __instance.damage.get(0).base);
                } else {
                    __instance.setMove(JawWorm.MOVES[0], BUFF, Intent.DEFEND_BUFF);
                }
            } else if (lastTwoMoves(moveHistory, ATTACK_BLOCK)) {
                __instance.setMove(ATTACK, Intent.ATTACK, __instance.damage.get(0).base);
            } else {
                __instance.setMove(ATTACK_BLOCK, Intent.ATTACK_DEFEND, __instance.damage.get(1).base);
            }
        }
    }
}
