package SmartEnemies.patches.act2;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.monsters.city.Champ;

import java.util.ArrayList;

import static com.megacrit.cardcrawl.monsters.AbstractMonster.Intent;

@SpirePatch(clz = Champ.class, method = "getMove")
public class ChampPatch {
    final static byte ATTACK = 1;
    final static byte ATTACK_DEBUFF = 4;
    final static byte EXECUTE = 3;
    final static byte DEFEND = 2;
    final static byte BUFF = 5;
    final static byte DEBUFF = 6;
    final static byte ANGER = 7;

    public static void Replace(Champ __instance, int num) {
        ArrayList<Byte> moveHistory = __instance.moveHistory;
        int splitTurn = -1;
        for (int i = 0; i < moveHistory.size(); i++) {
            if (moveHistory.get(i) == ANGER) {
                splitTurn = i;
            }
        }
        int n = moveHistory.size();
        if (splitTurn != -1) {
            int afterSplit = n - splitTurn;
            if (afterSplit % 3 == 1) {
                __instance.setMove(Champ.MOVES[1], EXECUTE, Intent.ATTACK, __instance.damage.get(1).base, 2, true);
            } else if (afterSplit % 3 == 2) {
                __instance.setMove(Champ.MOVES[2], ATTACK_DEBUFF, Intent.ATTACK_DEBUFF, __instance.damage.get(2).base);
            } else {
                __instance.setMove(ATTACK, Intent.ATTACK, __instance.damage.get(0).base);
            }
        } else {
            if (__instance.currentHealth < __instance.maxHealth / 2) {
                __instance.setMove(ANGER, Intent.BUFF);
            } else if (n == 0 || n == 2) {
                __instance.setMove(Champ.MOVES[0], DEFEND, Intent.DEFEND_BUFF);
            } else if (n == 1 || (n > 3 && n % 4 == 2)) {
                __instance.setMove(ATTACK, Intent.ATTACK, __instance.damage.get(0).base);
            } else if (n % 4 == 3) {
                __instance.setMove(DEBUFF, Intent.DEBUFF);
            } else if (n % 4 == 0) {
                __instance.setMove(BUFF, Intent.BUFF);
            } else {
                __instance.setMove(Champ.MOVES[2], ATTACK_DEBUFF, Intent.ATTACK_DEBUFF, __instance.damage.get(2).base);
            }
        }
    }
}
