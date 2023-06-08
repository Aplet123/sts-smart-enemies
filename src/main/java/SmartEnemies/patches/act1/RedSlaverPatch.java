
package SmartEnemies.patches.act1;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.monsters.exordium.SlaverRed;

import java.util.ArrayList;

import static com.megacrit.cardcrawl.monsters.AbstractMonster.Intent;

@SpirePatch(clz = SlaverRed.class, method = "getMove")
public class RedSlaverPatch {
    final static byte ATTACK = 1;
    final static byte ATTACK_DEBUFF = 3;
    final static byte DEBUFF = 2;

    public static void Replace(SlaverRed __instance, int num) {
        ArrayList<Byte> moveHistory = __instance.moveHistory;
        int n = moveHistory.size();
        if (n % 3 == 2) {
            __instance.setMove(SlaverRed.MOVES[0], ATTACK_DEBUFF, Intent.ATTACK_DEBUFF, __instance.damage.get(1).base);
        } else if (n == 1) {
            __instance.setMove(SlaverRed.MOVES[1], DEBUFF, Intent.STRONG_DEBUFF);
        } else {
            __instance.setMove(ATTACK, Intent.ATTACK, __instance.damage.get(0).base);
        }
    }
}
