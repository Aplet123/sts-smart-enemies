package SmartEnemies.patches.act3;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.monsters.beyond.TimeEater;

import java.util.ArrayList;

import static SmartEnemies.util.EnemyUtils.*;
import static com.megacrit.cardcrawl.monsters.AbstractMonster.Intent;

@SpirePatch(clz = TimeEater.class, method = "getMove")
public class TimeEaterPatch {
    final static byte ATTACK_MULTI = 2;
    final static byte ATTACK_DEBUFF = 4;
    final static byte DEBUFF = 3;
    final static byte BUFF = 5;

    public static void Replace(TimeEater __instance, int num) {
        ArrayList<Byte> moveHistory = __instance.moveHistory;
        int str = powerAmount(__instance, "Strength");
        int n = moveHistory.size();
        if (__instance.currentHealth < __instance.maxHealth / 2 && !moveHistory.contains(BUFF)) {
            __instance.setMove(BUFF, Intent.BUFF);
        } else if (str < 8) {
            if (n == 0) {
                if (num < 50) {
                    __instance.setMove(DEBUFF, Intent.DEFEND_DEBUFF);
                } else {
                    __instance.setMove(ATTACK_DEBUFF, Intent.ATTACK_DEBUFF, __instance.damage.get(1).base);
                }
            } else if (lastMove(moveHistory, DEBUFF)) {
                __instance.setMove(ATTACK_DEBUFF, Intent.ATTACK_DEBUFF, __instance.damage.get(1).base);
            } else {
                __instance.setMove(DEBUFF, Intent.DEFEND_DEBUFF);
            }
        } else if (str < 12) {
            if (lastMove(moveHistory, ATTACK_DEBUFF)) {
                __instance.setMove(ATTACK_MULTI, Intent.ATTACK, __instance.damage.get(0).base, 3, true);
            } else {
                __instance.setMove(ATTACK_DEBUFF, Intent.ATTACK_DEBUFF, __instance.damage.get(1).base);
            }
        } else {
            if (lastTwoMoves(moveHistory, ATTACK_MULTI)) {
                __instance.setMove(ATTACK_DEBUFF, Intent.ATTACK_DEBUFF, __instance.damage.get(1).base);
            } else {
                __instance.setMove(ATTACK_MULTI, Intent.ATTACK, __instance.damage.get(0).base, 3, true);
            }
        }
    }
}
