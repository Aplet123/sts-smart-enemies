package SmartEnemies.patches.act3;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.monsters.beyond.Spiker;

import java.util.ArrayList;

import static com.megacrit.cardcrawl.monsters.AbstractMonster.Intent;

@SpirePatch(clz = Spiker.class, method = "getMove")
public class SpikerPatch {
    final static byte ATTACK = 1;
    final static byte BUFF = 2;

    public static void Replace(Spiker __instance, int num) {
        ArrayList<Byte> moveHistory = __instance.moveHistory;
        int n = moveHistory.size();
        if (n == 0 || n > 6) {
            __instance.setMove(ATTACK, Intent.ATTACK, __instance.damage.get(0).base);
        } else {
            __instance.setMove(BUFF, Intent.BUFF);
        }
    }
}
