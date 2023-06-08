package SmartEnemies.patches.act3;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.beyond.Reptomancer;
import com.megacrit.cardcrawl.monsters.beyond.SnakeDagger;

import java.util.ArrayList;

import static SmartEnemies.util.EnemyUtils.lastMove;
import static SmartEnemies.util.EnemyUtils.lastTwoMoves;
import static com.megacrit.cardcrawl.monsters.AbstractMonster.Intent;

@SpirePatch(clz = Reptomancer.class, method = "getMove")
public class ReptomancerPatch {
    final static byte ATTACK = 3;
    final static byte ATTACK_MULTI = 1;
    final static byte SUMMON = 2;

    public static void Replace(Reptomancer __instance, int num) {
        ArrayList<Byte> moveHistory = __instance.moveHistory;
        int n = moveHistory.size();
        int daggers = 0;
        int livingDaggers = 0;
        ArrayList<AbstractMonster> monsters = AbstractDungeon.getMonsters().monsters;
        int mi = monsters.lastIndexOf(__instance);
        for (int i = 0; i < monsters.size(); i++) {
            AbstractMonster m = monsters.get(i);
            if (!m.isDying && !m.isEscaping && m instanceof SnakeDagger) {
                SnakeDagger dagger = (SnakeDagger) m;
                daggers++;
                if (i < mi || dagger.firstMove) {
                    livingDaggers++;
                }
            }
        }
        if (n == 0 || (livingDaggers >= 1 && livingDaggers <= 2 && daggers <= 3 && !lastTwoMoves(moveHistory, SUMMON))) {
            __instance.setMove(SUMMON, Intent.UNKNOWN);
        } else if (lastMove(moveHistory, ATTACK_MULTI)) {
            __instance.setMove(ATTACK, Intent.ATTACK, __instance.damage.get(1).base);
        } else {
            __instance.setMove(ATTACK_MULTI, Intent.ATTACK_DEBUFF, __instance.damage.get(0).base, 2, true);
        }
    }
}
