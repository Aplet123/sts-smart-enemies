package SmartEnemies.patches.act3;

import com.evacipated.cardcrawl.modthespire.lib.ByRef;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.monsters.beyond.Maw;

import java.util.ArrayList;

import static com.megacrit.cardcrawl.monsters.AbstractMonster.Intent;

@SpirePatch(clz = Maw.class, method = "getMove")
public class MawPatch {
    final static byte ATTACK = 3;
    final static byte ATTACK_MULTI = 5;
    final static byte DEBUFF = 2;
    final static byte BUFF = 4;

    // Use prefix patch to capture private variable
    public static SpireReturn<Void> Prefix(Maw __instance, int num, @ByRef int[] ___turnCount) {
        ___turnCount[0]++;
        ArrayList<Byte> moveHistory = __instance.moveHistory;
        int n = moveHistory.size();
        if (n == 0) {
            __instance.setMove(DEBUFF, Intent.STRONG_DEBUFF);
        } else if (n == 1 || n == 3) {
            __instance.setMove(ATTACK, Intent.ATTACK, __instance.damage.get(0).base);
        } else if (n % 2 == 0) {
            __instance.setMove(BUFF, Intent.BUFF);
        } else {
            __instance.setMove(ATTACK_MULTI, Intent.ATTACK, __instance.damage.get(1).base, ___turnCount[0] / 2, true);
        }
        return SpireReturn.Return();
    }
}
