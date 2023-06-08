package SmartEnemies.patches.act1;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.monsters.exordium.LouseDefensive;

import java.util.ArrayList;

import static com.megacrit.cardcrawl.monsters.AbstractMonster.Intent;

@SpirePatch(clz = LouseDefensive.class, method = "getMove")
public class GreenLousePatch {
    final static byte ATTACK = 3;
    final static byte DEBUFF = 4;

    public static void Replace(LouseDefensive __instance, int num) {
        ArrayList<Byte> moveHistory = __instance.moveHistory;
        if (moveHistory.size() % 3 == 2) {
            __instance.setMove(LouseDefensive.MOVES[0], DEBUFF, Intent.DEBUFF);
        } else {
            __instance.setMove(ATTACK, Intent.ATTACK, __instance.damage.get(0).base);
        }
    }
}
