package SmartEnemies.patches.act2;

import com.evacipated.cardcrawl.modthespire.lib.ByRef;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.monsters.city.BookOfStabbing;

import java.util.ArrayList;

import static com.megacrit.cardcrawl.monsters.AbstractMonster.Intent;

@SpirePatch(clz = BookOfStabbing.class, method = "getMove")
public class BookOfStabbingPatch {
    final static byte ATTACK_MULTI = 1;
    final static byte ATTACK = 2;

    // Use prefix patch to capture private fields
    public static SpireReturn<Void> Prefix(BookOfStabbing __instance, int num, @ByRef int[] ___stabCount) {
        ArrayList<Byte> moveHistory = __instance.moveHistory;
        int n = moveHistory.size();
        ___stabCount[0] ++;
        if (n % 3 == 0) {
            __instance.setMove(ATTACK, Intent.ATTACK, __instance.damage.get(1).base);
        } else {
            __instance.setMove(ATTACK_MULTI, Intent.ATTACK, __instance.damage.get(0).base, ___stabCount[0], true);
        }
        return SpireReturn.Return();
    }
}
