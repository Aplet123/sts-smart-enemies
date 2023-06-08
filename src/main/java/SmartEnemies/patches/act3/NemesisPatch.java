package SmartEnemies.patches.act3;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.beyond.Nemesis;

import java.util.ArrayList;

import static SmartEnemies.util.EnemyUtils.lastMove;
import static com.megacrit.cardcrawl.monsters.AbstractMonster.Intent;

@SpirePatch(clz = Nemesis.class, method = "getMove")
public class NemesisPatch {
    final static byte ATTACK = 3;
    final static byte ATTACK_MULTI = 2;
    final static byte DEBUFF = 4;

    public static void Replace(Nemesis __instance, int num) {
        ArrayList<Byte> moveHistory = __instance.moveHistory;
        int n = moveHistory.size();
        if (n == 0 || (n >= 3 && n % 2 == 1)) {
            __instance.setMove(DEBUFF, Intent.DEBUFF);
        } else if (n == 2) {
            __instance.setMove(ATTACK_MULTI, Intent.ATTACK, __instance.damage.get(1).base, 3, true);
        } else {
            __instance.setMove(ATTACK, Intent.ATTACK, __instance.damage.get(0).base);
        }
    }
}
