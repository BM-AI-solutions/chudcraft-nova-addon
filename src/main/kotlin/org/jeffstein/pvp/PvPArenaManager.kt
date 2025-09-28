package org.jeffstein.pvp

import org.bukkit.Location
import org.bukkit.World
import org.bukkit.entity.Player
import org.bukkit.Particle
import org.bukkit.Sound
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

/**
 * 🏟️ PVP ARENA MANAGER
 * Manages different PvP arenas with unique mechanics and environments
 */
object PvPArenaManager {
    
    private val activeArenas = mutableMapOf<String, Arena>()
    private val playersInArenas = mutableMapOf<Player, String>()
    
    data class Arena(
        val name: String,
        val type: ArenaType,
        val center: Location,
        val radius: Int,
        val players: MutableSet<Player> = mutableSetOf(),
        val gameActive: Boolean = false
    )
    
    enum class ArenaType(
        val displayName: String,
        val description: String,
        val maxPlayers: Int
    ) {
        FLOATING_ISLANDS("🏝️ Floating Islands", "Fight across multiple floating platforms", 8),
        SPELL_DUEL("⚔️ Spell Duel", "1v1 magical combat arena", 2),
        GRAVITITE_CHAMBER("🌪️ Gravitite Chamber", "Anti-gravity 3D combat", 6),
        PHOENIX_ROYALE("🔥 Phoenix Royale", "Battle royale with rebirth mechanics", 16),
        STORM_ARENA("⛈️ Storm Arena", "Lightning-filled combat zone", 10),
        CRYSTAL_MAZE("💎 Crystal Maze", "Maze with crystal power-ups", 12),
        VOID_PLATFORM("🌌 Void Platform", "Floating platform over the void", 8),
        WIND_TUNNELS("💨 Wind Tunnels", "Arena with wind currents and updrafts", 10)
    }
    
    /**
     * Create a new arena
     */
    fun createArena(name: String, type: ArenaType, center: Location): Arena {
        val arena = Arena(name, type, center, getArenaRadius(type))
        activeArenas[name] = arena
        
        // Build arena structure
        buildArenaStructure(arena)
        
        return arena
    }
    
    /**
     * Join an arena
     */
    fun joinArena(player: Player, arenaName: String): Boolean {
        val arena = activeArenas[arenaName] ?: return false
        
        if (arena.players.size >= arena.type.maxPlayers) {
            player.sendMessage("§c🏟️ Arena is full!")
            return false
        }
        
        if (arena.gameActive) {
            player.sendMessage("§c🏟️ Game already in progress!")
            return false
        }
        
        arena.players.add(player)
        playersInArenas[player] = arenaName
        
        // Teleport to arena
        val spawnPoint = getArenaSpawnPoint(arena, arena.players.size - 1)
        player.teleport(spawnPoint)
        
        // Apply arena-specific effects
        applyArenaEffects(player, arena.type)
        
        player.sendMessage("§6🏟️ Joined ${arena.type.displayName}!")
        player.sendMessage("§e${arena.type.description}")
        
        // Announce to other players
        arena.players.forEach { p ->
            if (p != player) {
                p.sendMessage("§a${player.name} joined the arena! (${arena.players.size}/${arena.type.maxPlayers})")
            }
        }
        
        return true
    }
    
    /**
     * Start arena game
     */
    fun startArenaGame(arenaName: String): Boolean {
        val arena = activeArenas[arenaName] ?: return false
        
        if (arena.players.size < 2) {
            arena.players.forEach { p ->
                p.sendMessage("§c🏟️ Need at least 2 players to start!")
            }
            return false
        }
        
        arena.players.forEach { player ->
            player.sendMessage("§6🏟️ ${arena.type.displayName} BEGINS!")
            
            // Clear inventory and give arena-specific items
            player.inventory.clear()
            giveArenaLoadout(player, arena.type)
            
            // Full health and clear effects
            player.health = player.maxHealth
            player.activePotionEffects.forEach { player.removePotionEffect(it.type) }
            
            // Apply arena effects
            applyArenaEffects(player, arena.type)
        }
        
        // Create start effect
        createArenaStartEffect(arena)
        
        return true
    }
    
    /**
     * Get arena radius based on type
     */
    private fun getArenaRadius(type: ArenaType): Int {
        return when (type) {
            ArenaType.SPELL_DUEL -> 15
            ArenaType.FLOATING_ISLANDS -> 50
            ArenaType.GRAVITITE_CHAMBER -> 25
            ArenaType.PHOENIX_ROYALE -> 100
            ArenaType.STORM_ARENA -> 30
            ArenaType.CRYSTAL_MAZE -> 40
            ArenaType.VOID_PLATFORM -> 20
            ArenaType.WIND_TUNNELS -> 35
        }
    }
    
    /**
     * Build arena structure
     */
    private fun buildArenaStructure(arena: Arena) {
        when (arena.type) {
            ArenaType.FLOATING_ISLANDS -> buildFloatingIslands(arena)
            ArenaType.SPELL_DUEL -> buildSpellDuelArena(arena)
            ArenaType.GRAVITITE_CHAMBER -> buildGravititeChamber(arena)
            ArenaType.STORM_ARENA -> buildBasicArena(arena) // Placeholder
            ArenaType.CRYSTAL_MAZE -> buildBasicArena(arena) // Placeholder
            ArenaType.VOID_PLATFORM -> buildBasicArena(arena) // Placeholder
            ArenaType.WIND_TUNNELS -> buildBasicArena(arena) // Placeholder
            else -> buildBasicArena(arena)
        }
    }
    
    private fun buildFloatingIslands(arena: Arena) {
        val center = arena.center
        
        // Main central island
        createFloatingIsland(center, 8)
        
        // Surrounding islands
        for (angle in 0..360 step 60) {
            val rad = Math.toRadians(angle.toDouble())
            val x = Math.cos(rad) * 25
            val z = Math.sin(rad) * 25
            val islandCenter = center.clone().add(x, Math.random() * 10 - 5, z)
            createFloatingIsland(islandCenter, 5)
        }
        
        // Connecting bridges (optional)
        // Add some smaller floating platforms for movement
    }
    
    private fun createFloatingIsland(center: Location, radius: Int) {
        val world = center.world
        
        // Create island base
        for (x in -radius..radius) {
            for (z in -radius..radius) {
                for (y in -2..0) {
                    val distance = Math.sqrt((x * x + z * z).toDouble())
                    if (distance <= radius) {
                        val block = world.getBlockAt(
                            center.blockX + x,
                            center.blockY + y,
                            center.blockZ + z
                        )
                        
                        when (y) {
                            0 -> block.type = Material.GRASS_BLOCK
                            -1 -> block.type = Material.DIRT
                            -2 -> block.type = Material.STONE
                        }
                    }
                }
            }
        }
    }
    
    private fun buildSpellDuelArena(arena: Arena) {
        val center = arena.center
        val world = center.world
        
        // Create circular platform
        for (x in -10..10) {
            for (z in -10..10) {
                val distance = Math.sqrt((x * x + z * z).toDouble())
                if (distance <= 10) {
                    world.getBlockAt(center.blockX + x, center.blockY - 1, center.blockZ + z).type = Material.QUARTZ_BLOCK
                }
            }
        }
        
        // Create barrier walls
        for (angle in 0..360 step 10) {
            val rad = Math.toRadians(angle.toDouble())
            val x = Math.cos(rad) * 12
            val z = Math.sin(rad) * 12
            
            for (y in 0..5) {
                world.getBlockAt(
                    center.blockX + x.toInt(),
                    center.blockY + y,
                    center.blockZ + z.toInt()
                ).type = Material.BARRIER
            }
        }
    }
    
    private fun buildGravititeChamber(arena: Arena) {
        val center = arena.center
        val world = center.world
        
        // Create spherical chamber
        for (x in -15..15) {
            for (y in -15..15) {
                for (z in -15..15) {
                    val distance = Math.sqrt((x * x + y * y + z * z).toDouble())
                    if (distance >= 14 && distance <= 15) {
                        world.getBlockAt(
                            center.blockX + x,
                            center.blockY + y,
                            center.blockZ + z
                        ).type = Material.PURPLE_STAINED_GLASS
                    }
                }
            }
        }
        
        // Add floating platforms inside
        for (i in 0..5) {
            val angle = i * 60.0
            val rad = Math.toRadians(angle)
            val x = Math.cos(rad) * 8
            val z = Math.sin(rad) * 8
            val y = (Math.random() - 0.5) * 10
            
            createSmallPlatform(center.clone().add(x, y, z), 3)
        }
    }
    
    private fun createSmallPlatform(center: Location, radius: Int) {
        val world = center.world
        
        for (x in -radius..radius) {
            for (z in -radius..radius) {
                val distance = Math.sqrt((x * x + z * z).toDouble())
                if (distance <= radius) {
                    world.getBlockAt(
                        center.blockX + x,
                        center.blockY,
                        center.blockZ + z
                    ).type = Material.STONE
                }
            }
        }
    }
    
    private fun buildBasicArena(arena: Arena) {
        // Default arena structure
        val center = arena.center
        val world = center.world
        val radius = arena.radius
        
        // Create floor
        for (x in -radius..radius) {
            for (z in -radius..radius) {
                val distance = Math.sqrt((x * x + z * z).toDouble())
                if (distance <= radius) {
                    world.getBlockAt(center.blockX + x, center.blockY - 1, center.blockZ + z).type = Material.STONE
                }
            }
        }
    }
    
    /**
     * Get spawn point for player in arena
     */
    private fun getArenaSpawnPoint(arena: Arena, playerIndex: Int): Location {
        val center = arena.center
        val angle = (360.0 / arena.type.maxPlayers) * playerIndex
        val rad = Math.toRadians(angle)
        val spawnRadius = arena.radius * 0.7
        
        val x = Math.cos(rad) * spawnRadius
        val z = Math.sin(rad) * spawnRadius
        
        return center.clone().add(x, 2.0, z)
    }
    
    /**
     * Apply arena-specific effects to player
     */
    private fun applyArenaEffects(player: Player, type: ArenaType) {
        when (type) {
            ArenaType.GRAVITITE_CHAMBER -> {
                player.addPotionEffect(PotionEffect(PotionEffectType.LEVITATION, Int.MAX_VALUE, 1))
                player.addPotionEffect(PotionEffect(PotionEffectType.SLOW_FALLING, Int.MAX_VALUE, 0))
            }
            ArenaType.STORM_ARENA -> {
                player.addPotionEffect(PotionEffect(PotionEffectType.RESISTANCE, Int.MAX_VALUE, 0))
            }
            ArenaType.PHOENIX_ROYALE -> {
                player.addPotionEffect(PotionEffect(PotionEffectType.FIRE_RESISTANCE, Int.MAX_VALUE, 0))
            }
            ArenaType.WIND_TUNNELS -> {
                player.addPotionEffect(PotionEffect(PotionEffectType.SPEED, Int.MAX_VALUE, 1))
            }
            else -> {}
        }
    }
    
    /**
     * Give arena-specific loadout
     */
    private fun giveArenaLoadout(player: Player, type: ArenaType) {
        when (type) {
            ArenaType.SPELL_DUEL -> {
                // Spell focus items only
                player.inventory.addItem(org.bukkit.inventory.ItemStack(Material.BLAZE_ROD))
            }
            ArenaType.GRAVITITE_CHAMBER -> {
                // Anti-gravity weapons
                player.inventory.addItem(org.bukkit.inventory.ItemStack(Material.DIAMOND_SWORD))
            }
            ArenaType.PHOENIX_ROYALE -> {
                // Phoenix equipment
                player.inventory.addItem(org.bukkit.inventory.ItemStack(Material.BOW))
                player.inventory.addItem(org.bukkit.inventory.ItemStack(Material.ARROW, 64))
            }
            else -> {
                // Standard PvP loadout
                player.inventory.addItem(org.bukkit.inventory.ItemStack(Material.IRON_SWORD))
                player.inventory.addItem(org.bukkit.inventory.ItemStack(Material.BOW))
                player.inventory.addItem(org.bukkit.inventory.ItemStack(Material.ARROW, 32))
            }
        }
    }
    
    /**
     * Create arena start effect
     */
    private fun createArenaStartEffect(arena: Arena) {
        val center = arena.center
        
        // Create countdown effect
        for (i in 3 downTo 1) {
            // This would be scheduled with delays in a real implementation
            center.world.playSound(center, Sound.BLOCK_NOTE_BLOCK_PLING, 2.0f, 1.0f + (i * 0.2f))
            
            // Create visual countdown
            for (angle in 0..360 step 30) {
                val rad = Math.toRadians(angle.toDouble())
                val x = Math.cos(rad) * (i * 5)
                val z = Math.sin(rad) * (i * 5)
                center.world.spawnParticle(
                    Particle.FIREWORK,
                    center.clone().add(x, 5.0, z),
                    1
                )
            }
        }
        
        // Final start effect
        center.world.playSound(center, Sound.ENTITY_ENDER_DRAGON_GROWL, 3.0f, 1.2f)
        center.world.spawnParticle(Particle.EXPLOSION, center, 10)
    }
}
