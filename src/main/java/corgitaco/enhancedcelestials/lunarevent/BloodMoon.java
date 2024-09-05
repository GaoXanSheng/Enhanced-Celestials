package corgitaco.enhancedcelestials.lunarevent;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import corgitaco.enhancedcelestials.api.lunarevent.LunarEvent;
import corgitaco.enhancedcelestials.api.lunarevent.LunarMobSpawnInfo;
import corgitaco.enhancedcelestials.api.lunarevent.LunarTextComponents;
import corgitaco.enhancedcelestials.api.lunarevent.client.LunarEventClientSettings;
import it.unimi.dsi.fastutil.objects.Object2DoubleArrayMap;
import net.minecraft.world.entity.MobCategory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class BloodMoon extends LunarEvent {

    public static final Codec<BloodMoon> CODEC = RecordCodecBuilder.create((builder) -> {
        return builder.group(LunarEventClientSettings.CODEC.fieldOf("clientSettings").forGetter((clientSettings) -> {
            return clientSettings.getClientSettings();
        }), Codec.INT.fieldOf("minNumberOfNightsBetween").forGetter((clientSettings) -> {
            return clientSettings.getMinNumberOfNightsBetween();
        }), Codec.DOUBLE.fieldOf("chance").forGetter((clientSettings) -> {
            return clientSettings.getChance();
        }), Codec.list(Codec.INT).fieldOf("validMoonPhases").forGetter((clientSettings) -> {
            return new ArrayList<>(clientSettings.getValidMoonPhases());
        }), LunarTextComponents.CODEC.fieldOf("textComponents").forGetter((blueMoon) -> {
            return blueMoon.getTextComponents();
        }), Codec.BOOL.fieldOf("blockSleeping").forGetter((clientSettings) -> {
            return clientSettings.blockSleeping();
        })).apply(builder, BloodMoon::new);
    });

    public BloodMoon(LunarEventClientSettings clientSettings, int minNumberOfNightsBetween, double chance, Collection<Integer> validMoonPhases, LunarTextComponents lunarTextComponents, boolean blockSleeping) {
        super(clientSettings, minNumberOfNightsBetween, chance, validMoonPhases, lunarTextComponents, blockSleeping);
    }
    @Override
    public Codec<? extends LunarEvent> codec() {
        return CODEC;
    }

}
