package SmartEnemies.patches.act2;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.city.Healer;

import java.util.ArrayList;

import static SmartEnemies.util.EnemyUtils.lastMove;
import static SmartEnemies.util.EnemyUtils.lastTwoMoves;
import static com.megacrit.cardcrawl.monsters.AbstractMonster.Intent;

@SpirePatch(clz = Healer.class, method = "getMove")
public class MysticPatch {
    final static byte ATTACK_DEBUFF = 1;
    final static byte HEAL = 2;
    final static byte BUFF = 3;

    public static void Replace(Healer __instance, int num) {
        ArrayList<Byte> moveHistory = __instance.moveHistory;
        int missingHp = 0;
        for (AbstractMonster m: AbstractDungeon.getMonsters().monsters) {
            if (!m.isDying && !m.isEscaping) {
                missingHp += m.maxHealth - m.currentHealth;
            }
        }
        if (missingHp > 20 && !lastTwoMoves(moveHistory, HEAL)) {
            __instance.setMove(HEAL, Intent.BUFF);
        } else if (!lastMove(moveHistory, ATTACK_DEBUFF)) {
            __instance.setMove(ATTACK_DEBUFF, Intent.ATTACK_DEBUFF, __instance.damage.get(0).base);
        } else {
            __instance.setMove(BUFF, Intent.BUFF);
        }
    }
}
