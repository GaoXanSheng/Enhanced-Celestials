package corgitaco.enhancedcelestials;

import com.google.common.collect.ImmutableSet;
import corgitaco.enhancedcelestials.api.EnhancedCelestialsRegistry;
import corgitaco.enhancedcelestials.api.client.ColorSettings;
import corgitaco.enhancedcelestials.api.lunarevent.LunarMobSpawnInfo;
import corgitaco.enhancedcelestials.api.lunarevent.LunarTextComponents;
import corgitaco.enhancedcelestials.core.ECSounds;
import corgitaco.enhancedcelestials.lunarevent.BloodMoon;
import corgitaco.enhancedcelestials.lunarevent.BlueMoon;
import corgitaco.enhancedcelestials.lunarevent.HarvestMoon;
import corgitaco.enhancedcelestials.lunarevent.client.MoonClientSettings;
import corgitaco.enhancedcelestials.network.NetworkHandler;
import corgitaco.enhancedcelestials.util.CustomTranslationTextComponent;
import it.unimi.dsi.fastutil.objects.Object2DoubleArrayMap;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Path;
import java.util.Set;

public class Main {
    public static final String MOD_ID = "enhancedcelestials";
    public static final Logger LOGGER = LogManager.getLogger();
    public static final Path CONFIG_PATH = FMLPaths.CONFIGDIR.get().resolve(MOD_ID);

    public Main() {
    }

    public static void commonSetup() {
        NetworkHandler.init();
        registerDefaults();
    }

    public static void registerDefaults() {
        Registry.register(EnhancedCelestialsRegistry.LUNAR_EVENT, new ResourceLocation(MOD_ID, "blood_moon"), BloodMoon.CODEC);
        Registry.register(EnhancedCelestialsRegistry.LUNAR_EVENT, new ResourceLocation(MOD_ID, "blue_moon"), BlueMoon.CODEC);
        Registry.register(EnhancedCelestialsRegistry.LUNAR_EVENT, new ResourceLocation(MOD_ID, "harvest_moon"), HarvestMoon.CODEC);
        Registry.register(EnhancedCelestialsRegistry.LUNAR_CLIENT_EVENT_SETTINGS, new ResourceLocation(MOD_ID, "default"), MoonClientSettings.CODEC);

        Set<Integer> defaultMoonPhases = ImmutableSet.of(0, 1, 2, 3, 5, 6, 7);
        Set<Integer> defaultSuperMoonPhases = ImmutableSet.of(0);
        EnhancedCelestialsRegistry.DEFAULT_EVENTS.put(new ResourceLocation(MOD_ID, "blood_moon"), new BloodMoon(new MoonClientSettings(new ColorSettings("c85179", 0.6, "e9546b", 0.4), 20.0F, ECSounds.BLOOD_MOON), 4, 0.07, defaultMoonPhases, new LunarTextComponents(new CustomTranslationTextComponent("enhancedcelestials.name.blood_moon"),new CustomTranslationTextComponent("enhancedcelestials.notification.blood_moon.rise", Style.EMPTY.applyFormat(ChatFormatting.RED)), new CustomTranslationTextComponent("enhancedcelestials.notification.blood_moon.set")), true));
        EnhancedCelestialsRegistry.DEFAULT_EVENTS.put(new ResourceLocation(MOD_ID, "super_blood_moon"), new BloodMoon(new MoonClientSettings(new ColorSettings("e9546b", 1, "c85179", 1), 40.0F, ECSounds.BLOOD_MOON), 20, 0.0175, defaultSuperMoonPhases, new LunarTextComponents(new CustomTranslationTextComponent("enhancedcelestials.name.super_blood_moon"), new CustomTranslationTextComponent("enhancedcelestials.notification.super_blood_moon.rise", Style.EMPTY.applyFormat(ChatFormatting.RED).applyFormat(ChatFormatting.BOLD)), new CustomTranslationTextComponent("enhancedcelestials.notification.blood_moon.set")), true));
        EnhancedCelestialsRegistry.DEFAULT_EVENTS.put(new ResourceLocation(MOD_ID, "harvest_moon"), new HarvestMoon(new MoonClientSettings(new ColorSettings("6c2c2f", 0.6, "783c1d", 0.4), 20.0F, ECSounds.HARVEST_MOON), 4, 0.035, defaultMoonPhases, new LunarTextComponents(new CustomTranslationTextComponent("enhancedcelestials.name.harvest_moon"), new CustomTranslationTextComponent("enhancedcelestials.notification.harvest_moon.rise", Style.EMPTY.applyFormat(ChatFormatting.YELLOW)), new CustomTranslationTextComponent("enhancedcelestials.notification.harvest_moon.set")), false, ImmutableSet.of(new ResourceLocation(MOD_ID, "item_tag_harvest_moon_crops")), 2.0, false));
        EnhancedCelestialsRegistry.DEFAULT_EVENTS.put(new ResourceLocation(MOD_ID, "super_harvest_moon"), new HarvestMoon(new MoonClientSettings(new ColorSettings("783c1d", 1, "6c2c2f", 1), 40.0F, ECSounds.HARVEST_MOON), 20, 0.0175, defaultSuperMoonPhases, new LunarTextComponents(new CustomTranslationTextComponent("enhancedcelestials.name.super_harvest_moon"), new CustomTranslationTextComponent("enhancedcelestials.notification.super_harvest_moon.rise", Style.EMPTY.applyFormat(ChatFormatting.YELLOW).applyFormat(ChatFormatting.BOLD)), new CustomTranslationTextComponent("enhancedcelestials.notification.super_harvest_moon.set")), false, ImmutableSet.of(new ResourceLocation(MOD_ID, "item_tag_harvest_moon_crops")), 4.0, false));
        EnhancedCelestialsRegistry.DEFAULT_EVENTS.put(new ResourceLocation(MOD_ID, "blue_moon"), new BlueMoon(new MoonClientSettings(new ColorSettings("1e50a2", 0.6, "0095d9", 0.4), 20.0F, ECSounds.BLUE_MOON), 4, 0.02, defaultMoonPhases, new LunarTextComponents(new CustomTranslationTextComponent("enhancedcelestials.name.blue_moon"), new CustomTranslationTextComponent("enhancedcelestials.notification.blue_moon.rise", Style.EMPTY.applyFormat(ChatFormatting.AQUA)), new CustomTranslationTextComponent("enhancedcelestials.notification.blue_moon.set")), false, 1));
        EnhancedCelestialsRegistry.DEFAULT_EVENTS.put(new ResourceLocation(MOD_ID, "super_blue_moon"), new BlueMoon(new MoonClientSettings(new ColorSettings("0095d9", 1, "1e50a2", 1), 40.0F, ECSounds.BLUE_MOON), 20, 0.01, defaultSuperMoonPhases, new LunarTextComponents(new CustomTranslationTextComponent("enhancedcelestials.name.super_blue_moon"), new CustomTranslationTextComponent("enhancedcelestials.notification.super_blue_moon.rise", Style.EMPTY.applyFormat(ChatFormatting.AQUA).applyFormat(ChatFormatting.BOLD)), new CustomTranslationTextComponent("enhancedcelestials.notification.super_blue_moon.set")), false, 5));
    }
}
