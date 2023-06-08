package SmartEnemies.patches.act2;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.city.GremlinLeader;

import java.util.ArrayList;

import static SmartEnemies.util.EnemyUtils.lastTwoMoves;
import static com.megacrit.cardcrawl.monsters.AbstractMonster.Intent;

@SpirePatch(clz = GremlinLeader.class, method = "getMove")
public class GremlinLeaderPatch {
    final static byte ATTACK_MULTI = 4;
    final static byte DEFEND = 3;
    final static byte SUMMON = 2;

    public static void Replace(GremlinLeader __instance, int num) {
        ArrayList<Byte> moveHistory = __instance.moveHistory;
        int n = moveHistory.size();
        int gremlinCount = 0;
        for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
            if (m != null && m != __instance && !m.isDying) {
                gremlinCount++;
            }
        }
        if (n % 2 == 0) {
            if (gremlinCount >= 2) {
                __instance.setMove(DEFEND, Intent.DEFEND_BUFF);
            } else {
                __instance.setMove(GremlinLeader.MOVES[0], SUMMON, Intent.UNKNOWN);
            }
        } else {
            __instance.setMove(ATTACK_MULTI, Intent.ATTACK, __instance.damage.get(0).base, 3, true);
        }
    }
}
