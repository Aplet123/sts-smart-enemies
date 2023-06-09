package SmartEnemies.patches.misc;

import SmartEnemies.util.MaxRandom;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.MonsterRoom;

@SpirePatch(
        clz = MonsterRoom.class,
        method = "onPlayerEntry"
)
public class MonsterRoomPatch {
    public static void Prefix(MonsterRoom __instance) {
        AbstractDungeon.monsterHpRng = new MaxRandom();
    }
}
