package SmartEnemies.patches.act2;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.monsters.city.Chosen;

import java.util.ArrayList;

import static com.megacrit.cardcrawl.monsters.AbstractMonster.Intent;

@SpirePatch(clz = Chosen.class, method = "getMove")
public class ChosenPatch {
    final static byte ATTACK = 1;
    final static byte ATTACK_DEBUFF = 3;
    final static byte HEX = 4;

    public static void Replace(Chosen __instance, int num) {
        ArrayList<Byte> moveHistory = __instance.moveHistory;
        int n = moveHistory.size();
        if (n == 0) {
            __instance.setMove(HEX, Intent.STRONG_DEBUFF);
        } else if (n % 2 == 0) {
            __instance.setMove(ATTACK, Intent.ATTACK, __instance.damage.get(0).base);
        } else {
            __instance.setMove(ATTACK_DEBUFF, Intent.ATTACK_DEBUFF, __instance.damage.get(1).base);
        }
    }
}
