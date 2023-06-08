package SmartEnemies.patches.act1;

import SmartEnemies.util.MinRandom;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.exordium.Looter;

@SpirePatch(clz = Looter.class, method = "takeTurn")
public class LooterPatch {
    public static void Prefix(Looter __instance) {
        AbstractDungeon.aiRng = new MinRandom();
    }
}
