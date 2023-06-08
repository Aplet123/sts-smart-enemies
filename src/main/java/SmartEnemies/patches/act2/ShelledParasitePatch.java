package SmartEnemies.patches.act2;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.monsters.city.ShelledParasite;

import java.util.ArrayList;

import static com.megacrit.cardcrawl.monsters.AbstractMonster.Intent;
import static SmartEnemies.util.EnemyUtils.*;

@SpirePatch(clz = ShelledParasite.class, method = "getMove")
public class ShelledParasitePatch {
    final static byte ATTACK_DEBUFF = 1;
    final static byte SUCK = 3;

    public static void Replace(ShelledParasite __instance, int num) {
        ArrayList<Byte> moveHistory = __instance.moveHistory;
        if (lastMove(moveHistory, ATTACK_DEBUFF)) {
            __instance.setMove(SUCK, Intent.ATTACK_BUFF, __instance.damage.get(2).base);
        } else {
            __instance.setMove(ATTACK_DEBUFF, Intent.ATTACK_DEBUFF, __instance.damage.get(1).base);
        }
    }
}
