package SmartEnemies.patches.act3;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.beyond.OrbWalker;

import java.util.ArrayList;

import static SmartEnemies.util.EnemyUtils.lastMove;
import static com.megacrit.cardcrawl.monsters.AbstractMonster.Intent;

@SpirePatch(clz = OrbWalker.class, method = "getMove")
public class OrbWalkerPatch {
    final static byte ATTACK_DEBUFF = 1;

    public static void Replace(OrbWalker __instance, int num) {
        __instance.setMove(ATTACK_DEBUFF, Intent.ATTACK_DEBUFF, __instance.damage.get(0).base);
    }
}
