package SmartEnemies.patches.act3;

import com.evacipated.cardcrawl.modthespire.lib.ByRef;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.monsters.beyond.WrithingMass;

import java.util.ArrayList;

import static com.megacrit.cardcrawl.monsters.AbstractMonster.Intent;

@SpirePatch(clz = WrithingMass.class, method = "getMove")
public class WrithingMassPatch {
    final static byte ATTACK = 0;
    final static byte ATTACK_MULTI = 1;
    final static byte CURSE = 4;

    // Use prefix patch to capture private field
    public static SpireReturn<Void> Prefix(WrithingMass __instance, int num, boolean ___usedMegaDebuff, @ByRef boolean[] ___firstMove) {
        if (___firstMove[0]) {
            __instance.setMove(ATTACK_MULTI, Intent.ATTACK, __instance.damage.get(1).base, 3, true);
            ___firstMove[0] = false;
        } else {
            ArrayList<Byte> moveHistory = __instance.moveHistory;
            boolean empty = moveHistory.size() == 0;
            byte last = 0;
            byte intent = ___usedMegaDebuff ? ATTACK_MULTI : CURSE;
            if (!empty) {
                last = moveHistory.get(moveHistory.size() - 1);
            }
            boolean doIntent;
            if (empty || (last != ATTACK && last != intent)) {
                doIntent = num < 50;
            } else {
                doIntent = last == ATTACK;
            }
            if (doIntent) {
                if (___usedMegaDebuff) {
                    __instance.setMove(ATTACK_MULTI, Intent.ATTACK, __instance.damage.get(1).base, 3, true);
                } else {
                    __instance.setMove(CURSE, Intent.STRONG_DEBUFF);
                }
            } else {
                __instance.setMove(ATTACK, Intent.ATTACK, __instance.damage.get(0).base);
            }
        }
        __instance.createIntent();
        return SpireReturn.Return();
    }
}
