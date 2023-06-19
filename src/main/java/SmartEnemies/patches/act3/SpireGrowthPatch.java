package SmartEnemies.patches.act3;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.beyond.SpireGrowth;

import java.util.ArrayList;

import static SmartEnemies.util.EnemyUtils.lastMove;
import static SmartEnemies.util.EnemyUtils.lastTwoMoves;
import static com.megacrit.cardcrawl.monsters.AbstractMonster.Intent;

@SpirePatch(clz = SpireGrowth.class, method = "getMove")
public class SpireGrowthPatch {
    final static byte ATTACK = 1;
    final static byte ATTACK_BIG = 3;
    final static byte DEBUFF = 2;

    public static void Replace(SpireGrowth __instance, int num) {
        ArrayList<Byte> moveHistory = __instance.moveHistory;
        int n = moveHistory.size();
        if (!AbstractDungeon.player.hasPower("Constricted") && !lastMove(moveHistory, DEBUFF)) {
            __instance.setMove(DEBUFF, Intent.STRONG_DEBUFF);
        } else if (lastTwoMoves(moveHistory, ATTACK_BIG)) {
            __instance.setMove(ATTACK, Intent.ATTACK, __instance.damage.get(0).base);
        } else {
            __instance.setMove(ATTACK_BIG, Intent.ATTACK, __instance.damage.get(1).base);
        }
    }
}
