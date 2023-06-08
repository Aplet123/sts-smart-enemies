package SmartEnemies.patches.misc;

import SmartEnemies.util.MaxRandom;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

@SpirePatch(
        clz = AbstractMonster.class,
        method = SpirePatch.CONSTRUCTOR,
        paramtypez = {
                String.class, String.class, int.class,
                float.class, float.class, float.class, float.class,
                String.class, float.class, float.class, boolean.class
        }
)
public class AbstractMonsterPatch {
    public static void Prefix(AbstractMonster __instance) {
        AbstractDungeon.monsterHpRng = new MaxRandom();
    }
}
