
package SmartEnemies.patches.act1;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.monsters.exordium.SlaverBlue;

import java.util.ArrayList;

import static com.megacrit.cardcrawl.monsters.AbstractMonster.Intent;

@SpirePatch(clz = SlaverBlue.class, method = "getMove")
public class BlueSlaverPatch {
    final static byte ATTACK = 1;
    final static byte ATTACK_DEBUFF = 4;

    public static void Replace(SlaverBlue __instance, int num) {
        ArrayList<Byte> moveHistory = __instance.moveHistory;
        if (moveHistory.size() % 3 == 2) {
            __instance.setMove(SlaverBlue.MOVES[0], ATTACK_DEBUFF, Intent.ATTACK_DEBUFF, __instance.damage.get(1).base);
        } else {
            __instance.setMove(ATTACK, Intent.ATTACK, __instance.damage.get(0).base);
        }
    }
}
