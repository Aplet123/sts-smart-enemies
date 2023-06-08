package SmartEnemies.patches.act3;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.monsters.beyond.Repulsor;

import java.util.ArrayList;

import static com.megacrit.cardcrawl.monsters.AbstractMonster.Intent;

@SpirePatch(clz = Repulsor.class, method = "getMove")
public class RepulsorPatch {
    final static byte ATTACK = 2;
    final static byte DEBUFF = 1;

    public static void Replace(Repulsor __instance, int num) {
        ArrayList<Byte> moveHistory = __instance.moveHistory;
        int n = moveHistory.size();
        if (n % 2 == 0) {
            __instance.setMove(ATTACK, Intent.ATTACK, __instance.damage.get(0).base);
        } else {
            __instance.setMove(DEBUFF, Intent.DEBUFF);
        }
    }
}
