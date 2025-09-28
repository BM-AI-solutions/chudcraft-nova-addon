package org.jeffstein.magic

import org.jeffstein.AetherTekkitAddon
import xyz.xenondevs.nova.initialize.Init
import xyz.xenondevs.nova.initialize.InitStage

@Init(stage = InitStage.PRE_PACK)
object MagicalItems {
    
    // === MAGICAL CRYSTALS & GEMS ===
    val AETHER_CRYSTAL = AetherTekkitAddon.item("aether_crystal") {}
    val CORRUPTED_CRYSTAL = AetherTekkitAddon.item("corrupted_crystal") {}
    val PURIFIED_CRYSTAL = AetherTekkitAddon.item("purified_crystal") {}
    val RESONANCE_CRYSTAL = AetherTekkitAddon.item("resonance_crystal") {}
    val STORM_CRYSTAL = AetherTekkitAddon.item("storm_crystal") {}
    val VOID_CRYSTAL = AetherTekkitAddon.item("void_crystal") {}
    
    // === MAGICAL ESSENCES ===
    val WIND_ESSENCE = AetherTekkitAddon.item("wind_essence") {}
    val CLOUD_ESSENCE = AetherTekkitAddon.item("cloud_essence") {}
    val LIGHTNING_ESSENCE = AetherTekkitAddon.item("lightning_essence") {}
    val GRAVITY_ESSENCE = AetherTekkitAddon.item("gravity_essence") {}
    val LIFE_ESSENCE = AetherTekkitAddon.item("life_essence") {}
    val VOID_ESSENCE = AetherTekkitAddon.item("void_essence") {}
    
    // === SPELL COMPONENTS ===
    val SPELL_SCROLL_BLANK = AetherTekkitAddon.item("spell_scroll_blank") {}
    val ENCHANTED_INK = AetherTekkitAddon.item("enchanted_ink") {}
    val PHOENIX_FEATHER = AetherTekkitAddon.item("phoenix_feather") {}
    val VALKYRIE_WING = AetherTekkitAddon.item("valkyrie_wing") {}
    val SPIRIT_DUST = AetherTekkitAddon.item("spirit_dust") {}
    val ETHEREAL_THREAD = AetherTekkitAddon.item("ethereal_thread") {}
    
    // === SPELL SCROLLS ===
    val LEVITATION_SCROLL = AetherTekkitAddon.item("levitation_scroll") {}
    val TELEPORTATION_SCROLL = AetherTekkitAddon.item("teleportation_scroll") {}
    val HEALING_SCROLL = AetherTekkitAddon.item("healing_scroll") {}
    val LIGHTNING_SCROLL = AetherTekkitAddon.item("lightning_scroll") {}
    val SHIELD_SCROLL = AetherTekkitAddon.item("shield_scroll") {}
    val FLIGHT_SCROLL = AetherTekkitAddon.item("flight_scroll") {}
    
    // === MAGICAL TOOLS ===
    val AETHER_STAFF = AetherTekkitAddon.item("aether_staff") {}
    val STORM_WAND = AetherTekkitAddon.item("storm_wand") {}
    val GRAVITY_MANIPULATOR = AetherTekkitAddon.item("gravity_manipulator") {}
    val CRYSTAL_RESONATOR = AetherTekkitAddon.item("crystal_resonator") {}
    val SPIRIT_COMMUNICATOR = AetherTekkitAddon.item("spirit_communicator") {}
    val DIMENSIONAL_COMPASS = AetherTekkitAddon.item("dimensional_compass") {}
    
    // === ENCHANTED WEAPONS ===
    val VALKYRIE_LANCE = AetherTekkitAddon.item("valkyrie_lance") {}
    val PHOENIX_BLADE = AetherTekkitAddon.item("phoenix_blade") {}
    val STORM_HAMMER = AetherTekkitAddon.item("storm_hammer") {}
    val VOID_SCYTHE = AetherTekkitAddon.item("void_scythe") {}
    val LIGHTNING_BOW = AetherTekkitAddon.item("lightning_bow") {}
    val GRAVITITE_MACE = AetherTekkitAddon.item("gravitite_mace") {}

    // === PVP WEAPONS ===
    val GRAVITITE_BLADE = AetherTekkitAddon.item("gravitite_blade") {} // Reverses enemy gravity
    val WIND_BLADE = AetherTekkitAddon.item("wind_blade") {} // Creates tornadoes
    val STORM_SPEAR = AetherTekkitAddon.item("storm_spear") {} // Chain lightning
    val PHOENIX_BOW = AetherTekkitAddon.item("phoenix_bow") {} // Flaming arrows
    val VOID_DAGGER = AetherTekkitAddon.item("void_dagger") {} // Teleportation strikes
    val CRYSTAL_STAFF = AetherTekkitAddon.item("crystal_staff") {} // Mana drain attacks
    val AERCLOUD_SHIELD = AetherTekkitAddon.item("aercloud_shield") {} // Bounces projectiles
    val ZANITE_CHAKRAM = AetherTekkitAddon.item("zanite_chakram") {} // Boomerang weapon
    
    // === MAGICAL ARMOR ===
    val VALKYRIE_HELMET = AetherTekkitAddon.item("valkyrie_helmet") {}
    val VALKYRIE_CHESTPLATE = AetherTekkitAddon.item("valkyrie_chestplate") {}
    val VALKYRIE_LEGGINGS = AetherTekkitAddon.item("valkyrie_leggings") {}
    val VALKYRIE_BOOTS = AetherTekkitAddon.item("valkyrie_boots") {}
    
    val PHOENIX_HELMET = AetherTekkitAddon.item("phoenix_helmet") {}
    val PHOENIX_CHESTPLATE = AetherTekkitAddon.item("phoenix_chestplate") {}
    val PHOENIX_LEGGINGS = AetherTekkitAddon.item("phoenix_leggings") {}
    val PHOENIX_BOOTS = AetherTekkitAddon.item("phoenix_boots") {}
    
    // === MAGICAL ACCESSORIES ===
    val CLOUD_WALKING_BOOTS = AetherTekkitAddon.item("cloud_walking_boots") {}
    val GRAVITITE_BELT = AetherTekkitAddon.item("gravitite_belt") {}
    val AETHER_RING = AetherTekkitAddon.item("aether_ring") {}
    val STORM_PENDANT = AetherTekkitAddon.item("storm_pendant") {}
    val PHOENIX_AMULET = AetherTekkitAddon.item("phoenix_amulet") {}
    val VOID_CLOAK = AetherTekkitAddon.item("void_cloak") {}
    
    // === MAGICAL CONSUMABLES ===
    val AMBROSIUM_POTION = AetherTekkitAddon.item("ambrosium_potion") {}
    val LEVITATION_POTION = AetherTekkitAddon.item("levitation_potion") {}
    val STORM_RESISTANCE_POTION = AetherTekkitAddon.item("storm_resistance_potion") {}
    val ETHEREAL_SIGHT_POTION = AetherTekkitAddon.item("ethereal_sight_potion") {}
    val GRAVITY_REVERSAL_POTION = AetherTekkitAddon.item("gravity_reversal_potion") {}
    val PHOENIX_TEARS = AetherTekkitAddon.item("phoenix_tears") {}
    
    // === MAGITECH COMPONENTS ===
    val ETHEREAL_CIRCUIT = AetherTekkitAddon.item("ethereal_circuit") {}
    val MANA_CAPACITOR = AetherTekkitAddon.item("mana_capacitor") {}
    val CRYSTAL_PROCESSOR = AetherTekkitAddon.item("crystal_processor") {}
    val GRAVITITE_CORE = AetherTekkitAddon.item("gravitite_core") {}
    val AETHER_BATTERY = AetherTekkitAddon.item("aether_battery") {}
    val DIMENSIONAL_STABILIZER = AetherTekkitAddon.item("dimensional_stabilizer") {}
    
    // === RARE ARTIFACTS ===
    val GOLDEN_FEATHER = AetherTekkitAddon.item("golden_feather") {} // Rare drop from golden birds
    val SILVER_PENDANT = AetherTekkitAddon.item("silver_pendant") {} // Found in dungeons
    val OBSIDIAN_GLOVES = AetherTekkitAddon.item("obsidian_gloves") {} // Fire immunity
    val ICE_RING = AetherTekkitAddon.item("ice_ring") {} // Freezes water
    val IRON_BUBBLE = AetherTekkitAddon.item("iron_bubble") {} // Underwater breathing
    val REGENERATION_STONE = AetherTekkitAddon.item("regeneration_stone") {} // Slow healing
    
    // === DUNGEON REWARDS ===
    val BRONZE_DUNGEON_KEY = AetherTekkitAddon.item("bronze_dungeon_key") {}
    val SILVER_DUNGEON_KEY = AetherTekkitAddon.item("silver_dungeon_key") {}
    val GOLD_DUNGEON_KEY = AetherTekkitAddon.item("gold_dungeon_key") {}
    val VICTORY_MEDAL = AetherTekkitAddon.item("victory_medal") {}
    val DUNGEON_LOOT_BOX = AetherTekkitAddon.item("dungeon_loot_box") {}

    // === MOB DROPS & SPECIAL ITEMS ===
    val CLOUD_MILK = AetherTekkitAddon.item("cloud_milk") {} // From Flying Cows
    val CLOUD_WOOL = AetherTekkitAddon.item("cloud_wool") {} // From Sheepuff
    val SWET_BALL = AetherTekkitAddon.item("swet_ball") {} // From Swets
    val AECHOR_PETAL = AetherTekkitAddon.item("aechor_petal") {} // From Aechor Plants
    val COCKATRICE_FEATHER = AetherTekkitAddon.item("cockatrice_feather") {} // From Cockatrice
    val MOA_FEATHER = AetherTekkitAddon.item("moa_feather") {} // From Moas
    val FIRE_ESSENCE = AetherTekkitAddon.item("fire_essence") {} // From Fire Minions
    val STONE_SHARD = AetherTekkitAddon.item("stone_shard") {} // From Sentries
    val MIMIC_TOOTH = AetherTekkitAddon.item("mimic_tooth") {} // From Mimics
    val VALKYRIE_WING_FRAGMENT = AetherTekkitAddon.item("valkyrie_wing_fragment") {} // From Valkyries

    // === SPECIAL PORTAL ITEMS ===
    val PORTAL_CATALYST = AetherTekkitAddon.item("portal_catalyst") {} // Activates portals
    val DIMENSIONAL_KEY = AetherTekkitAddon.item("dimensional_key") {} // Opens locked portals
    val AETHER_COMPASS = AetherTekkitAddon.item("aether_compass") {} // Points to Aether portals

}
