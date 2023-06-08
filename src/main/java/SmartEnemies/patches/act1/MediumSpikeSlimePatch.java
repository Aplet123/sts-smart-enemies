package SmartEnemies.patches.act1;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.monsters.exordium.SpikeSlime_M;

import java.util.ArrayList;

import static com.megacrit.cardcrawl.monsters.AbstractMonster.Intent;

@SpirePatch(clz = SpikeSlime_M.class, method = "getMove")
public class MediumSpikeSlimePatch {
    final static byte ATTACK_SLIME = 1;
    final static byte DEBUFF = 4;

    public static void Replace(SpikeSlime_M __instance, int num) {
        ArrayList<Byte> moveHistory = __instance.moveHistory;
        if (moveHistory.size() % 3 == 2) {
            __instance.setMove(SpikeSlime_M.MOVES[0], DEBUFF, Intent.DEBUFF);
        } else {
            __instance.setMove(ATTACK_SLIME, Intent.ATTACK_DEBUFF, __instance.damage.get(0).base);
        }
    }
}
