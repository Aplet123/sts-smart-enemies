package SmartEnemies.patches.act3;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.monsters.beyond.AwakenedOne;

import java.util.ArrayList;

import static com.megacrit.cardcrawl.monsters.AbstractMonster.Intent;

@SpirePatch(clz = AwakenedOne.class, method = "getMove")
public class AwakenedOnePatch {
    final static byte ATTACK_1 = 1;
    final static byte ATTACK_MULTI1 = 2;
    final static byte ATTACK_2 = 5;
    final static byte ATTACK_MULTI2 = 8;
    final static byte ATTACK_DEBUFF2 = 6;

    // Use prefix patch to capture private variables
    public static SpireReturn<Void> Prefix(AwakenedOne __instance, int num, boolean ___form1, boolean ___firstTurn) {
        ArrayList<Byte> moveHistory = __instance.moveHistory;
        if (___form1) {
            if (moveHistory.size() == 0 || moveHistory.get(moveHistory.size() - 1) == ATTACK_MULTI1) {
                __instance.setMove(ATTACK_1, Intent.ATTACK, __instance.damage.get(0).base);
            } else {
                __instance.setMove(AwakenedOne.MOVES[0], ATTACK_MULTI1, Intent.ATTACK, __instance.damage.get(1).base, 4, true);
            }
        } else {
            if (___firstTurn) {
                __instance.setMove(AwakenedOne.MOVES[1], ATTACK_2, Intent.ATTACK, __instance.damage.get(2).base);
            } else if (moveHistory.size() >= 2 && moveHistory.get(moveHistory.size() - 1) == ATTACK_MULTI2 && moveHistory.get(moveHistory.size() - 2) == ATTACK_MULTI2) {
                __instance.setMove(AwakenedOne.MOVES[3], ATTACK_DEBUFF2, Intent.ATTACK_DEBUFF, __instance.damage.get(3).base);
            } else {
                __instance.setMove(ATTACK_MULTI2, Intent.ATTACK, __instance.damage.get(4).base, 3, true);
            }
        }
        return SpireReturn.Return();
    }
}
