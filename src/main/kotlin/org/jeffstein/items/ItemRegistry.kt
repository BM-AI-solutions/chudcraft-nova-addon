package org.jeffstein.items

import org.jeffstein.AetherTekkitAddon
import org.jeffstein.blocks.Blocks
import org.jeffstein.machines.Machines
import xyz.xenondevs.nova.initialize.Init
import xyz.xenondevs.nova.initialize.InitStage

@Init(stage = InitStage.PRE_PACK)
object Items {

    // === BLOCK ITEMS ===
    val HOLYSTONE = AetherTekkitAddon.item(Blocks.HOLYSTONE) {}
    val MOSSY_HOLYSTONE = AetherTekkitAddon.item(Blocks.MOSSY_HOLYSTONE) {}
    val HOLYSTONE_BRICKS = AetherTekkitAddon.item(Blocks.HOLYSTONE_BRICKS) {}
    val CARVED_STONE = AetherTekkitAddon.item(Blocks.CARVED_STONE) {}
    val ANGELIC_STONE = AetherTekkitAddon.item(Blocks.ANGELIC_STONE) {}
    val HELLFIRE_STONE = AetherTekkitAddon.item(Blocks.HELLFIRE_STONE) {}

    val AETHER_DIRT = AetherTekkitAddon.item(Blocks.AETHER_DIRT) {}
    val AETHER_GRASS_BLOCK = AetherTekkitAddon.item(Blocks.AETHER_GRASS_BLOCK) {}
    val ENCHANTED_AETHER_GRASS_BLOCK = AetherTekkitAddon.item(Blocks.ENCHANTED_AETHER_GRASS_BLOCK) {}

    val SKYROOT_LOG = AetherTekkitAddon.item(Blocks.SKYROOT_LOG) {}
    val SKYROOT_WOOD = AetherTekkitAddon.item(Blocks.SKYROOT_WOOD) {}
    val STRIPPED_SKYROOT_LOG = AetherTekkitAddon.item(Blocks.STRIPPED_SKYROOT_LOG) {}
    val STRIPPED_SKYROOT_WOOD = AetherTekkitAddon.item(Blocks.STRIPPED_SKYROOT_WOOD) {}
    val SKYROOT_PLANKS = AetherTekkitAddon.item(Blocks.SKYROOT_PLANKS) {}
    val SKYROOT_LEAVES = AetherTekkitAddon.item(Blocks.SKYROOT_LEAVES) {}
    val GOLDEN_SKYROOT_LEAVES = AetherTekkitAddon.item(Blocks.GOLDEN_SKYROOT_LEAVES) {}

    val AERCLOUD = AetherTekkitAddon.item(Blocks.AERCLOUD) {}
    val BLUE_AERCLOUD = AetherTekkitAddon.item(Blocks.BLUE_AERCLOUD) {}
    val GOLDEN_AERCLOUD = AetherTekkitAddon.item(Blocks.GOLDEN_AERCLOUD) {}
    val QUICKSOIL = AetherTekkitAddon.item(Blocks.QUICKSOIL) {}

    val AMBROSIUM_ORE = AetherTekkitAddon.item(Blocks.AMBROSIUM_ORE) {}
    val ZANITE_ORE = AetherTekkitAddon.item(Blocks.ZANITE_ORE) {}
    val GRAVITITE_ORE = AetherTekkitAddon.item(Blocks.GRAVITITE_ORE) {}

    val ZANITE_BLOCK = AetherTekkitAddon.item(Blocks.ZANITE_BLOCK) {}
    val ENCHANTED_GRAVITITE = AetherTekkitAddon.item(Blocks.ENCHANTED_GRAVITITE) {}

    val QUICKSOIL_GLASS = AetherTekkitAddon.item(Blocks.QUICKSOIL_GLASS) {}

    val AETHER_GRASS = AetherTekkitAddon.item(Blocks.AETHER_GRASS) {}
    val ENCHANTED_AETHER_GRASS = AetherTekkitAddon.item(Blocks.ENCHANTED_AETHER_GRASS) {}
    val BERRY_BUSH = AetherTekkitAddon.item(Blocks.BERRY_BUSH) {}
    val BERRY_BUSH_STEM = AetherTekkitAddon.item(Blocks.BERRY_BUSH_STEM) {}
    val PURPLE_FLOWER = AetherTekkitAddon.item(Blocks.PURPLE_FLOWER) {}
    val WHITE_FLOWER = AetherTekkitAddon.item(Blocks.WHITE_FLOWER) {}

    // === AETHER MATERIALS ===
    val AMBROSIUM_SHARD = AetherTekkitAddon.item("ambrosium_shard") {}
    val ZANITE_GEMSTONE = AetherTekkitAddon.item("zanite_gemstone") {}
    val GRAVITITE_PLATE = AetherTekkitAddon.item("gravitite_plate") {}

    // === AETHER FOOD ===
    val BLUE_BERRY = AetherTekkitAddon.item("blue_berry") {}
    val ENCHANTED_BERRY = AetherTekkitAddon.item("enchanted_berry") {}
    val WHITE_APPLE = AetherTekkitAddon.item("white_apple") {}

    // === AETHER TOOLS (Basic) ===
    val SKYROOT_STICK = AetherTekkitAddon.item("skyroot_stick") {}
    val SKYROOT_PICKAXE = AetherTekkitAddon.item("skyroot_pickaxe") {}
    val SKYROOT_AXE = AetherTekkitAddon.item("skyroot_axe") {}
    val SKYROOT_SHOVEL = AetherTekkitAddon.item("skyroot_shovel") {}
    val SKYROOT_HOE = AetherTekkitAddon.item("skyroot_hoe") {}
    val SKYROOT_SWORD = AetherTekkitAddon.item("skyroot_sword") {}

    // === MAGICAL MACHINES ===
    val AMBROSIUM_ENCHANTER = AetherTekkitAddon.item(Machines.AMBROSIUM_ENCHANTER) {}
    val ZANITE_EMPOWERMENT_CHAMBER = AetherTekkitAddon.item(Machines.ZANITE_EMPOWERMENT_CHAMBER) {}
    val GRAVITITE_LEVITATION_CHAMBER = AetherTekkitAddon.item(Machines.GRAVITITE_LEVITATION_CHAMBER) {}
    val SKYROOT_ESSENCE_EXTRACTOR = AetherTekkitAddon.item(Machines.SKYROOT_ESSENCE_EXTRACTOR) {}
    val AETHER_CRYSTAL_RESONATOR = AetherTekkitAddon.item(Machines.AETHER_CRYSTAL_RESONATOR) {}
    val CLOUD_CONDENSER = AetherTekkitAddon.item(Machines.CLOUD_CONDENSER) {}
    val WIND_ESSENCE_COLLECTOR = AetherTekkitAddon.item(Machines.WIND_ESSENCE_COLLECTOR) {}
    val AMBROSIUM_REACTOR = AetherTekkitAddon.item(Machines.AMBROSIUM_REACTOR) {}
    val MAGITECH_WORKBENCH = AetherTekkitAddon.item(Machines.MAGITECH_WORKBENCH) {}
    val ETHEREAL_CIRCUIT_ASSEMBLER = AetherTekkitAddon.item(Machines.ETHEREAL_CIRCUIT_ASSEMBLER) {}
    val QUANTUM_AETHER_BRIDGE = AetherTekkitAddon.item(Machines.QUANTUM_AETHER_BRIDGE) {}
    val GRAVITITE_FIELD_GENERATOR = AetherTekkitAddon.item(Machines.GRAVITITE_FIELD_GENERATOR) {}

}
