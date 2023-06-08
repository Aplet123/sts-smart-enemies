package SmartEnemies.patches.act4;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.monsters.ending.SpireShield;

import java.util.ArrayList;

import static SmartEnemies.util.EnemyUtils.lastMove;
import static com.megacrit.cardcrawl.monsters.AbstractMonster.Intent;

@SpirePatch(clz = SpireShield.class, method = "getMove")
public class SpireShieldPatch {
    final static byte ATTACK_DEFEND = 3;
    final static byte ATTACK_DEBUFF = 1;
    final static byte DEFEND = 2;

    public static void Replace(SpireShield __instance, int num) {
        ArrayList<Byte> moveHistory = __instance.moveHistory;
        int n = moveHistory.size();
        if (n % 3 == 0) {
            if (n == 0 || num < 25) {
                __instance.setMove(DEFEND, Intent.DEFEND);
            } else {
                __instance.setMove(ATTACK_DEBUFF, Intent.ATTACK_DEBUFF, __instance.damage.get(0).base);
            }
        } else if (n % 3 == 1) {
            if (lastMove(moveHistory, DEFEND)) {
                __instance.setMove(ATTACK_DEBUFF, Intent.ATTACK_DEBUFF, __instance.damage.get(0).base);
            } else {
                __instance.setMove(DEFEND, Intent.DEFEND);
            }
        } else {
            __instance.setMove(ATTACK_DEFEND, Intent.ATTACK_DEFEND, __instance.damage.get(1).base);
        }
    }
}
