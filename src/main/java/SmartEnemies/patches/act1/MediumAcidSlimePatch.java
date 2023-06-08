package SmartEnemies.patches.act1;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.monsters.exordium.AcidSlime_M;

import java.util.ArrayList;

import static com.megacrit.cardcrawl.monsters.AbstractMonster.Intent;

@SpirePatch(clz = AcidSlime_M.class, method = "getMove")
public class MediumAcidSlimePatch {
    final static byte ATTACK_SLIME = 1;
    final static byte ATTACK = 2;
    final static byte DEBUFF = 4;

    public static void Replace(AcidSlime_M __instance, int num) {
        ArrayList<Byte> moveHistory = __instance.moveHistory;
        if (moveHistory.size() % 2 == 1) {
            __instance.setMove(ATTACK_SLIME, Intent.ATTACK_DEBUFF, __instance.damage.get(0).base);
        } else {
            __instance.setMove(ATTACK, Intent.ATTACK, __instance.damage.get(1).base);
        }
    }
}
