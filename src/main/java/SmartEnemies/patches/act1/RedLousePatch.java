package SmartEnemies.patches.act1;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.monsters.exordium.LouseNormal;

import java.util.ArrayList;

import static com.megacrit.cardcrawl.monsters.AbstractMonster.Intent;

@SpirePatch(clz = LouseNormal.class, method = "getMove")
public class RedLousePatch {
    final static byte ATTACK = 3;
    final static byte BUFF = 4;

    public static void Replace(LouseNormal __instance, int num) {
        ArrayList<Byte> moveHistory = __instance.moveHistory;
        if (moveHistory.size() % 3 == 2) {
            __instance.setMove(LouseNormal.MOVES[0], BUFF, Intent.BUFF);
        } else {
            __instance.setMove(ATTACK, Intent.ATTACK, __instance.damage.get(0).base);
        }
    }
}
