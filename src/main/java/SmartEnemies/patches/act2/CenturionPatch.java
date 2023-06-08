package SmartEnemies.patches.act2;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.city.Centurion;

import java.util.ArrayList;

import static SmartEnemies.util.EnemyUtils.lastTwoMoves;
import static com.megacrit.cardcrawl.monsters.AbstractMonster.Intent;

@SpirePatch(clz = Centurion.class, method = "getMove")
public class CenturionPatch {
    final static byte ATTACK = 1;
    final static byte DEFEND = 2;
    final static byte FURY = 3;

    public static void Replace(Centurion __instance, int num) {
        ArrayList<Byte> moveHistory = __instance.moveHistory;
        int aliveCount = 0;
        for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
            if (!m.isDying && !m.isEscaping) {
                aliveCount++;
            }
        }
        if (aliveCount > 1) {
            if (lastTwoMoves(moveHistory, ATTACK)) {
                __instance.setMove(DEFEND, Intent.DEFEND);
            } else {
                __instance.setMove(ATTACK, Intent.ATTACK, __instance.damage.get(0).base);
            }
        } else {
            if (lastTwoMoves(moveHistory, FURY) || lastTwoMoves(moveHistory, DEFEND)) {
                __instance.setMove(ATTACK, Intent.ATTACK, __instance.damage.get(0).base);
            } else {
                __instance.setMove(FURY, Intent.ATTACK, __instance.damage.get(1).base, 3, true);
            }
        }
    }
}
