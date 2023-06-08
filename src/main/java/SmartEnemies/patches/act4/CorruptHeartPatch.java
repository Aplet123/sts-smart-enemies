package SmartEnemies.patches.act4;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.monsters.ending.CorruptHeart;

import java.util.ArrayList;

import static SmartEnemies.util.EnemyUtils.lastMove;
import static com.megacrit.cardcrawl.monsters.AbstractMonster.Intent;

@SpirePatch(clz = CorruptHeart.class, method = "getMove")
public class CorruptHeartPatch {
    final static byte ATTACK = 2;
    final static byte ATTACK_MULTI = 1;
    final static byte DEBUFF = 3;
    final static byte BUFF = 4;

    public static void Replace(CorruptHeart __instance, int num) {
        ArrayList<Byte> moveHistory = __instance.moveHistory;
        int n = moveHistory.size();
        if (n == 0) {
            __instance.setMove(DEBUFF, Intent.STRONG_DEBUFF);
        } else if (n % 3 == 0) {
            __instance.setMove(BUFF, Intent.BUFF);
        } else if (n == 1) {
            if (num < 50) {
                __instance.setMove(ATTACK, Intent.ATTACK, __instance.damage.get(0).base);
            } else {
                __instance.setMove(ATTACK_MULTI, Intent.ATTACK, __instance.damage.get(1).base, 15, true);
            }
        } else if (n == 2) {
            if (lastMove(moveHistory, ATTACK)) {
                __instance.setMove(ATTACK_MULTI, Intent.ATTACK, __instance.damage.get(1).base, 15, true);
            } else {
                __instance.setMove(ATTACK, Intent.ATTACK, __instance.damage.get(0).base);
            }
        } else if (n % 3 == 1) {
            __instance.setMove(ATTACK_MULTI, Intent.ATTACK, __instance.damage.get(1).base, 15, true);
        } else {
            __instance.setMove(ATTACK, Intent.ATTACK, __instance.damage.get(0).base);
        }
    }
}
