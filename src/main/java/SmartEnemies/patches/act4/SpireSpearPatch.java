package SmartEnemies.patches.act4;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.monsters.ending.SpireSpear;

import java.util.ArrayList;

import static com.megacrit.cardcrawl.monsters.AbstractMonster.Intent;

@SpirePatch(clz = SpireSpear.class, method = "getMove")
public class SpireSpearPatch {
    final static byte ATTACK_MULTI = 3;
    final static byte ATTACK_DEBUFF = 1;
    final static byte BUFF = 2;

    public static void Replace(SpireSpear __instance, int num) {
        ArrayList<Byte> moveHistory = __instance.moveHistory;
        int n = moveHistory.size();
        if (n == 0 || n % 3 == 2) {
            __instance.setMove(ATTACK_DEBUFF, Intent.ATTACK_DEBUFF, __instance.damage.get(0).base, 2, true);
        } else if (n % 3 == 1) {
            __instance.setMove(ATTACK_MULTI, Intent.ATTACK, __instance.damage.get(1).base, 4, true);
        } else {
            __instance.setMove(BUFF, Intent.BUFF);
        }
    }
}
