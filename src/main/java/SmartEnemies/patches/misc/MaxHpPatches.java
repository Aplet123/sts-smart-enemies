package SmartEnemies.patches.misc;

import SmartEnemies.util.MaxRandom;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.MonsterRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoomBoss;
import com.megacrit.cardcrawl.rooms.MonsterRoomElite;

public class MaxHpPatches {
    @SpirePatch(
            clz = MonsterRoom.class,
            method = "onPlayerEntry"
    )
    public static class MonsterRoomPatch {
        public static void Prefix(MonsterRoom __instance) {
            AbstractDungeon.monsterHpRng = new MaxRandom();
        }
    }

    @SpirePatch(
            clz = MonsterRoomElite.class,
            method = "onPlayerEntry"
    )
    public static class MonsterRoomElitePatch {
        public static void Prefix(MonsterRoomElite __instance) {
            AbstractDungeon.monsterHpRng = new MaxRandom();
        }
    }

    @SpirePatch(
            clz = MonsterRoomBoss.class,
            method = "onPlayerEntry"
    )
    public static class MonsterRoomBossPatch {
        public static void Prefix(MonsterRoomBoss __instance) {
            AbstractDungeon.monsterHpRng = new MaxRandom();
        }
    }
}
