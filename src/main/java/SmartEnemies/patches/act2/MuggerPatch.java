package SmartEnemies.patches.act2;

import SmartEnemies.util.MinRandom;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.city.Mugger;

@SpirePatch(clz = Mugger.class, method = "takeTurn")
public class MuggerPatch {
    public static void Prefix(Mugger __instance) {
        AbstractDungeon.aiRng = new MinRandom();
    }
}
