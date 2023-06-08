package SmartEnemies.patches.act2;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.monsters.city.Byrd;

import java.util.ArrayList;

import static SmartEnemies.util.EnemyUtils.*;
import static com.megacrit.cardcrawl.monsters.AbstractMonster.Intent;

@SpirePatch(clz = Byrd.class, method = "getMove")
public class ByrdPatch {
    final static byte ATTACK_MULTI = 1;
    final static byte ATTACK_SMALL = 5;
    final static byte ATTACK_BIG = 3;
    final static byte FLY = 2;
    final static byte BUFF = 6;

    // Use a prefix patch with early return instead of replace patch in order to be able to capture private field
    public static SpireReturn<Void> Prefix(Byrd __instance, int num, boolean ___isFlying) {
        ArrayList<Byte> moveHistory = __instance.moveHistory;
        int n = moveHistory.size();
        int str = powerAmount(__instance, "Strength");
        int multiDmg = (__instance.damage.get(0).base + str) * 6;
        int bigDmg = __instance.damage.get(1).base + str;
        if (n == 0) {
            __instance.setMove(ATTACK_MULTI, Intent.ATTACK, __instance.damage.get(0).base, 6, true);
        } else if (!___isFlying) {
            __instance.setMove(ATTACK_SMALL, Intent.ATTACK, __instance.damage.get(2).base);
        } else if (str < 3) {
            if (lastMove(moveHistory, ATTACK_BIG)) {
                __instance.setMove(BUFF, Intent.BUFF);
            } else {
                if (bigDmg > multiDmg) {
                    __instance.setMove(ATTACK_BIG, Intent.ATTACK, __instance.damage.get(1).base);
                } else {
                    __instance.setMove(ATTACK_MULTI, Intent.ATTACK, __instance.damage.get(0).base, 6, true);
                }
            }
        } else {
            if (lastTwoMoves(moveHistory, ATTACK_MULTI)) {
                __instance.setMove(ATTACK_BIG, Intent.ATTACK, __instance.damage.get(1).base);
            } else {
                __instance.setMove(ATTACK_MULTI, Intent.ATTACK, __instance.damage.get(0).base, 6, true);
            }
        }
        return SpireReturn.Return();
    }
}
