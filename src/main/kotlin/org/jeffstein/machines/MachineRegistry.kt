package org.jeffstein.machines

import org.jeffstein.AetherTekkitAddon
import xyz.xenondevs.nova.initialize.Init
import xyz.xenondevs.nova.initialize.InitStage

@Init(stage = InitStage.PRE_PACK)
object Machines {

    // === AETHER MAGICAL DEVICES ===
    val AMBROSIUM_ENCHANTER = AetherTekkitAddon.block("ambrosium_enchanter") {} // Magical enchanting station
    val ZANITE_EMPOWERMENT_CHAMBER = AetherTekkitAddon.block("zanite_empowerment_chamber") {} // Powers up tools based on durability
    val GRAVITITE_LEVITATION_CHAMBER = AetherTekkitAddon.block("gravitite_levitation_chamber") {} // Reverses gravity for items/blocks
    val SKYROOT_ESSENCE_EXTRACTOR = AetherTekkitAddon.block("skyroot_essence_extractor") {} // Extracts magical essence from wood

    // === AETHER ENERGY SYSTEMS ===
    val AETHER_CRYSTAL_RESONATOR = AetherTekkitAddon.block("aether_crystal_resonator") {} // Generates power from crystals
    val CLOUD_CONDENSER = AetherTekkitAddon.block("cloud_condenser") {} // Harvests energy from clouds
    val WIND_ESSENCE_COLLECTOR = AetherTekkitAddon.block("wind_essence_collector") {} // Collects wind magic
    val AMBROSIUM_REACTOR = AetherTekkitAddon.block("ambrosium_reactor") {} // High-power magical reactor

    // === HYBRID TECH-MAGIC ===
    val MAGITECH_WORKBENCH = AetherTekkitAddon.block("magitech_workbench") {} // Combines magic and technology
    val ETHEREAL_CIRCUIT_ASSEMBLER = AetherTekkitAddon.block("ethereal_circuit_assembler") {} // Creates magical circuits
    val QUANTUM_AETHER_BRIDGE = AetherTekkitAddon.block("quantum_aether_bridge") {} // Teleportation between dimensions
    val GRAVITITE_FIELD_GENERATOR = AetherTekkitAddon.block("gravitite_field_generator") {} // Creates anti-gravity fields

    // === MYSTICAL AUTOMATION ===
    val SPIRIT_CONVEYOR = AetherTekkitAddon.block("spirit_conveyor") {} // Magical item transport using spirits
    val AETHER_PORTAL_NETWORK = AetherTekkitAddon.block("aether_portal_network") {} // Network of small portals for items
    val ENCHANTED_HOPPER = AetherTekkitAddon.block("enchanted_hopper") {} // Hopper that can sort by enchantments
    val SKYROOT_PIPE_SYSTEM = AetherTekkitAddon.block("skyroot_pipe_system") {} // Living wood pipe network

    // === ADVANCED MAGICAL TECH ===
    val DIMENSIONAL_ANCHOR = AetherTekkitAddon.block("dimensional_anchor") {} // Keeps chunks loaded with magic
    val AETHER_WEATHER_CONTROLLER = AetherTekkitAddon.block("aether_weather_controller") {} // Controls weather in the Aether
    val CRYSTAL_GROWTH_ACCELERATOR = AetherTekkitAddon.block("crystal_growth_accelerator") {} // Grows crystals faster
    val MANA_BATTERY = AetherTekkitAddon.block("mana_battery") {} // Stores magical energy

    // === UNIQUE AETHER CONTRAPTIONS ===
    val FLOATING_ISLAND_GENERATOR = AetherTekkitAddon.block("floating_island_generator") {} // Creates new floating islands
    val AERCLOUD_SYNTHESIZER = AetherTekkitAddon.block("aercloud_synthesizer") {} // Creates different types of clouds
    val VALKYRIE_SUMMONING_ALTAR = AetherTekkitAddon.block("valkyrie_summoning_altar") {} // Summons helpful Valkyries
    val PHOENIX_NEST_INCUBATOR = AetherTekkitAddon.block("phoenix_nest_incubator") {} // Breeds magical creatures

    // === DEFENSIVE MAGIC TECH ===
    val AETHER_SHIELD_GENERATOR = AetherTekkitAddon.block("aether_shield_generator") {} // Protective magical barriers
    val LIGHTNING_ROD_ARRAY = AetherTekkitAddon.block("lightning_rod_array") {} // Harnesses storm energy
    val PURIFICATION_ALTAR = AetherTekkitAddon.block("purification_altar") {} // Cleanses corrupted items/blocks
    val WARD_STONE = AetherTekkitAddon.block("ward_stone") {} // Protects areas from hostile mobs

}
