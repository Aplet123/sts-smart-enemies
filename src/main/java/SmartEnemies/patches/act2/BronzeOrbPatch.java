package SmartEnemies.patches.act2;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.monsters.city.BronzeOrb;

import java.util.ArrayList;

import static com.megacrit.cardcrawl.monsters.AbstractMonster.Intent;

@SpirePatch(clz = BronzeOrb.class, method = "getMove")
public class BronzeOrbPatch {
    final static byte ATTACK = 1;
    final static byte DEFEND = 2;
    final static byte STASIS = 3;

    public static void Replace(BronzeOrb __instance, int num) {
        ArrayList<Byte> moveHistory = __instance.moveHistory;
        int n = moveHistory.size();
        if (n % 2 == 0) {
            __instance.setMove(ATTACK, Intent.ATTACK, 8);
        } else if (n == 1) {
            __instance.setMove(STASIS, Intent.STRONG_DEBUFF);
        } else {
            __instance.setMove(DEFEND, Intent.DEFEND);
        }
    }
}
