package SmartEnemies.patches.act2;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.monsters.city.SnakePlant;

import java.util.ArrayList;

import static com.megacrit.cardcrawl.monsters.AbstractMonster.Intent;

@SpirePatch(clz = SnakePlant.class, method = "getMove")
public class SnakePlantPatch {
    final static byte ATTACK = 1;
    final static byte DEBUFF = 2;

    public static void Replace(SnakePlant __instance, int num) {
        ArrayList<Byte> moveHistory = __instance.moveHistory;
        int n = moveHistory.size();
        if (n % 3 == 2) {
            __instance.setMove(SnakePlant.MOVES[0], DEBUFF, Intent.STRONG_DEBUFF);
        } else {
            __instance.setMove(ATTACK, Intent.ATTACK, __instance.damage.get(0).base, 3, true);
        }
    }
}
