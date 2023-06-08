package SmartEnemies.patches.act2;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.monsters.city.Snecko;

import java.util.ArrayList;

import static com.megacrit.cardcrawl.monsters.AbstractMonster.Intent;

@SpirePatch(clz = Snecko.class, method = "getMove")
public class SneckoPatch {
    final static byte DEBUFF = 1;
    final static byte ATTACK = 2;
    final static byte ATTACK_DEBUFF = 3;

    public static void Replace(Snecko __instance, int num) {
        ArrayList<Byte> moveHistory = __instance.moveHistory;
        int n = moveHistory.size();
        if (n == 0) {
            __instance.setMove(Snecko.MOVES[0], DEBUFF, Intent.STRONG_DEBUFF);
        } else if (n % 3 == 1) {
            __instance.setMove(Snecko.MOVES[1], ATTACK_DEBUFF, Intent.ATTACK_DEBUFF, __instance.damage.get(1).base);
        } else {
            __instance.setMove(Snecko.MOVES[2], ATTACK, Intent.ATTACK, __instance.damage.get(0).base);
        }
    }
}
