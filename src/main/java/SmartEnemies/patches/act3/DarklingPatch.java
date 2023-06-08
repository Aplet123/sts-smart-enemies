package SmartEnemies.patches.act3;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.beyond.Darkling;

import java.util.ArrayList;

import static SmartEnemies.util.EnemyUtils.lastMove;
import static com.megacrit.cardcrawl.monsters.AbstractMonster.Intent;

@SpirePatch(clz = Darkling.class, method = "getMove")
public class DarklingPatch {
    final static byte ATTACK = 3;
    final static byte ATTACK_MULTI = 1;
    final static byte DEFEND = 2;
    final static byte REGROW = 4;
    final static byte REVIVE = 5;

    public static void Replace(Darkling __instance, int num) {
        ArrayList<Byte> moveHistory = __instance.moveHistory;
        // use turn number instead of move history size since dying can insert extra intents
        int n = GameActionManager.turn;
        // jankness with how turn number is incremented
        if (moveHistory.size() == 0) {
            n = 0;
        }
        if (__instance.halfDead) {
            __instance.setMove(REVIVE, Intent.BUFF);
        } else if (n % 3 == 0) {
            __instance.setMove(DEFEND, Intent.DEFEND_BUFF);
        } else if (AbstractDungeon.getMonsters().monsters.lastIndexOf(__instance) % 2 == 0 && !lastMove(moveHistory, ATTACK_MULTI)) {
            __instance.setMove(ATTACK_MULTI, Intent.ATTACK, __instance.damage.get(0).base, 2, true);
        } else {
            __instance.setMove(ATTACK, Intent.ATTACK, __instance.damage.get(1).base);
        }
    }
}
