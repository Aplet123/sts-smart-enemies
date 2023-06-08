
package SmartEnemies.patches.act1;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.monsters.exordium.FungiBeast;

import java.util.ArrayList;

import static com.megacrit.cardcrawl.monsters.AbstractMonster.Intent;

@SpirePatch(clz = FungiBeast.class, method = "getMove")
public class FungiBeastPath {
    final static byte ATTACK = 1;
    final static byte BUFF = 2;

    public static void Replace(FungiBeast __instance, int num) {
        ArrayList<Byte> moveHistory = __instance.moveHistory;
        if (moveHistory.size() % 3 == 0) {
            __instance.setMove(FungiBeast.MOVES[0], BUFF, Intent.BUFF);
        } else {
            __instance.setMove(ATTACK, Intent.ATTACK, __instance.damage.get(0).base);
        }
    }
}
