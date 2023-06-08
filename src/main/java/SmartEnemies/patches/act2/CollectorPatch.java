package SmartEnemies.patches.act2;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.city.TheCollector;

import java.util.ArrayList;

import static com.megacrit.cardcrawl.monsters.AbstractMonster.Intent;

@SpirePatch(clz = TheCollector.class, method = "getMove")
public class CollectorPatch {
    final static byte ATTACK = 2;
    final static byte INITIAL_SUMMON = 1;
    final static byte SUMMON = 5;
    final static byte BUFF = 3;
    final static byte DEBUFF = 4;

    public static void Replace(TheCollector __instance, int num) {
        ArrayList<Byte> moveHistory = __instance.moveHistory;
        int n = moveHistory.size();
        int torchHeads = 0;
        for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
            if (!m.isDying && !m.isEscaping && m.id.equals("TorchHead")) {
                torchHeads++;
            }
        }
        if (n == 0) {
            __instance.setMove(INITIAL_SUMMON, Intent.UNKNOWN);
        } else if (n == 1 || (n > 3 && n % 3 == 0)) {
            if (n > 3 && torchHeads == 0) {
                __instance.setMove(SUMMON, Intent.UNKNOWN);
            } else {
                __instance.setMove(BUFF, Intent.DEFEND_BUFF);
            }
        } else if (n == 3) {
            __instance.setMove(DEBUFF, Intent.STRONG_DEBUFF);
        } else {
            __instance.setMove(ATTACK, Intent.ATTACK, __instance.damage.get(0).base);
        }
    }
}
