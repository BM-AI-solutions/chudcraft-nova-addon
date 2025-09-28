package org.jeffstein.blocks

import org.jeffstein.AetherTekkitAddon
import xyz.xenondevs.nova.initialize.Init
import xyz.xenondevs.nova.initialize.InitStage

@Init(stage = InitStage.PRE_PACK)
object Blocks {

    // === AETHER STONE BLOCKS ===
    val HOLYSTONE = AetherTekkitAddon.block("holystone") {}
    val MOSSY_HOLYSTONE = AetherTekkitAddon.block("mossy_holystone") {}
    val HOLYSTONE_BRICKS = AetherTekkitAddon.block("holystone_bricks") {}
    val CARVED_STONE = AetherTekkitAddon.block("carved_stone") {}
    val ANGELIC_STONE = AetherTekkitAddon.block("angelic_stone") {}
    val HELLFIRE_STONE = AetherTekkitAddon.block("hellfire_stone") {}

    // === AETHER NATURAL BLOCKS ===
    val AETHER_DIRT = AetherTekkitAddon.block("aether_dirt") {}
    val AETHER_GRASS_BLOCK = AetherTekkitAddon.block("aether_grass_block") {}
    val ENCHANTED_AETHER_GRASS_BLOCK = AetherTekkitAddon.block("enchanted_aether_grass_block") {}

    // === SKYROOT WOOD BLOCKS ===
    val SKYROOT_LOG = AetherTekkitAddon.block("skyroot_log") {}
    val SKYROOT_WOOD = AetherTekkitAddon.block("skyroot_wood") {}
    val STRIPPED_SKYROOT_LOG = AetherTekkitAddon.block("stripped_skyroot_log") {}
    val STRIPPED_SKYROOT_WOOD = AetherTekkitAddon.block("stripped_skyroot_wood") {}
    val SKYROOT_PLANKS = AetherTekkitAddon.block("skyroot_planks") {}
    val SKYROOT_LEAVES = AetherTekkitAddon.block("skyroot_leaves") {}
    val GOLDEN_SKYROOT_LEAVES = AetherTekkitAddon.block("golden_skyroot_leaves") {}

    // === SPECIAL AETHER BLOCKS ===
    val AERCLOUD = AetherTekkitAddon.block("aercloud") {}
    val BLUE_AERCLOUD = AetherTekkitAddon.block("blue_aercloud") {}
    val GOLDEN_AERCLOUD = AetherTekkitAddon.block("golden_aercloud") {}
    val QUICKSOIL = AetherTekkitAddon.block("quicksoil") {}

    // === AETHER ORES ===
    val AMBROSIUM_ORE = AetherTekkitAddon.block("ambrosium_ore") {}
    val ZANITE_ORE = AetherTekkitAddon.block("zanite_ore") {}
    val GRAVITITE_ORE = AetherTekkitAddon.block("gravitite_ore") {}

    // === AETHER METAL BLOCKS ===
    val ZANITE_BLOCK = AetherTekkitAddon.block("zanite_block") {}
    val ENCHANTED_GRAVITITE = AetherTekkitAddon.block("enchanted_gravitite") {}

    // === AETHER GLASS ===
    val QUICKSOIL_GLASS = AetherTekkitAddon.block("quicksoil_glass") {}

    // === AETHER PLANTS ===
    val AETHER_GRASS = AetherTekkitAddon.block("aether_grass") {}
    val ENCHANTED_AETHER_GRASS = AetherTekkitAddon.block("enchanted_aether_grass") {}
    val BERRY_BUSH = AetherTekkitAddon.block("berry_bush") {}
    val BERRY_BUSH_STEM = AetherTekkitAddon.block("berry_bush_stem") {}
    val PURPLE_FLOWER = AetherTekkitAddon.block("purple_flower") {}
    val WHITE_FLOWER = AetherTekkitAddon.block("white_flower") {}

    // === PORTAL BLOCKS ===
    val AETHER_PORTAL = AetherTekkitAddon.block("aether_portal") {} // The swirling portal block
    val PORTAL_FRAME = AetherTekkitAddon.block("portal_frame") {} // Decorative portal frame

}
