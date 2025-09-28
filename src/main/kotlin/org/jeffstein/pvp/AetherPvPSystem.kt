package org.jeffstein.pvp

import org.bukkit.entity.Player
import org.bukkit.Location
import org.bukkit.Particle
import org.bukkit.Sound
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.jeffstein.magic.SpellSystem
import org.jeffstein.magic.ResearchSystem

/**
 * 🗡️⚡ AETHER PVP SYSTEM - Epic magical combat!
 * Combines spells, special weapons, environmental hazards, and unique mechanics
 */
object AetherPvPSystem : Listener {
    
    // === PVP GAME MODES ===
    
    /**
     * 🏰 FLOATING ISLAND CONQUEST
     * - Teams fight for control of floating islands
     * - Islands have capture points that grant buffs
     * - Environmental hazards like wind currents and lightning storms
     */
    object IslandConquest {
        
        enum class IslandType(val displayName: String, val buff: String) {
            STORM_ISLAND("⛈️ Storm Island", "Lightning resistance + storm spells"),
            CRYSTAL_ISLAND("💎 Crystal Island", "Mana regeneration + crystal weapons"),
            WIND_ISLAND("💨 Wind Island", "Flight + wind magic"),
            FIRE_ISLAND("🔥 Fire Island", "Fire immunity + flame weapons"),
            VOID_ISLAND("🌌 Void Island", "Void magic + teleportation")
        }
        
        fun captureIsland(player: Player, island: IslandType) {
            player.sendMessage("§6🏰 You have captured ${island.displayName}!")
            player.sendMessage("§e⚡ Buff gained: ${island.buff}")
            
            // Grant island-specific buffs
            when (island) {
                IslandType.STORM_ISLAND -> {
                    player.addPotionEffect(PotionEffect(PotionEffectType.RESISTANCE, 6000, 1))
                    // Unlock storm spells temporarily
                }
                IslandType.CRYSTAL_ISLAND -> {
                    // Double mana regeneration
                    player.addPotionEffect(PotionEffect(PotionEffectType.REGENERATION, 6000, 0))
                }
                IslandType.WIND_ISLAND -> {
                    player.allowFlight = true
                    player.addPotionEffect(PotionEffect(PotionEffectType.SPEED, 6000, 1))
                }
                IslandType.FIRE_ISLAND -> {
                    player.addPotionEffect(PotionEffect(PotionEffectType.FIRE_RESISTANCE, 6000, 0))
                }
                IslandType.VOID_ISLAND -> {
                    // Grant void magic abilities
                    player.addPotionEffect(PotionEffect(PotionEffectType.INVISIBILITY, 200, 0))
                }
            }
            
            // Create capture effect
            val loc = player.location
            for (i in 0..50) {
                loc.world.spawnParticle(
                    Particle.ENCHANT,
                    loc.clone().add(
                        Math.sin(i * 0.2) * 3,
                        i * 0.1,
                        Math.cos(i * 0.2) * 3
                    ),
                    1
                )
            }
            loc.world.playSound(loc, Sound.BLOCK_BEACON_ACTIVATE, 2.0f, 1.2f)
        }
    }
    
    /**
     * ⚔️ SPELL DUELING ARENA
     * - 1v1 magical combat with spell-only restrictions
     * - Mana management becomes crucial
     * - Special duel spells and combos
     */
    object SpellDueling {
        
        private val activeDuels = mutableMapOf<Player, Player>()
        
        fun startDuel(player1: Player, player2: Player) {
            activeDuels[player1] = player2
            activeDuels[player2] = player1
            
            // Teleport to duel arena
            val arena = player1.world.getSpawnLocation().clone().add(0.0, 100.0, 0.0)
            player1.teleport(arena.clone().add(10.0, 0.0, 0.0))
            player2.teleport(arena.clone().add(-10.0, 0.0, 0.0))
            
            // Give full mana and clear effects
            SpellSystem.getPlayerMana(player1).currentMana = SpellSystem.getPlayerMana(player1).maxMana
            SpellSystem.getPlayerMana(player2).currentMana = SpellSystem.getPlayerMana(player2).maxMana
            
            player1.activePotionEffects.forEach { player1.removePotionEffect(it.type) }
            player2.activePotionEffects.forEach { player2.removePotionEffect(it.type) }
            
            // Clear inventories and give spell focus items
            player1.inventory.clear()
            player2.inventory.clear()
            
            player1.inventory.addItem(ItemStack(Material.BLAZE_ROD)) // Spell casting focus
            player2.inventory.addItem(ItemStack(Material.BLAZE_ROD))
            
            // Announce duel
            player1.sendMessage("§c⚔️ SPELL DUEL BEGINS! §e${player2.name} §cvs §e${player1.name}")
            player2.sendMessage("§c⚔️ SPELL DUEL BEGINS! §e${player1.name} §cvs §e${player2.name}")
            
            // Create arena effects
            for (i in 0..360 step 10) {
                val rad = Math.toRadians(i.toDouble())
                val x = Math.cos(rad) * 15
                val z = Math.sin(rad) * 15
                arena.world.spawnParticle(
                    Particle.FLAME,
                    arena.clone().add(x, 0.0, z),
                    1
                )
            }
            
            arena.world.playSound(arena, Sound.ENTITY_ENDER_DRAGON_GROWL, 2.0f, 1.0f)
        }
        
        fun endDuel(winner: Player, loser: Player) {
            activeDuels.remove(winner)
            activeDuels.remove(loser)
            
            winner.sendMessage("§6🏆 VICTORY! You have won the spell duel!")
            loser.sendMessage("§c💀 DEFEAT! Better luck next time!")
            
            // Award research points to winner
            ResearchSystem.awardResearchPoints(winner, ResearchSystem.ResearchActivity.USE_SPELL)
            
            // Create victory effect
            winner.world.spawnParticle(Particle.FIREWORK, winner.location, 50)
            winner.world.playSound(winner.location, Sound.ENTITY_PLAYER_LEVELUP, 2.0f, 1.2f)
        }
    }
    
    /**
     * 🌪️ GRAVITITE GLADIATOR
     * - Anti-gravity combat arena
     * - Players fight in 3D space with reversed gravity
     * - Special gravitite weapons and abilities
     */
    object GravititeGladiator {
        
        fun enterArena(player: Player) {
            player.sendMessage("§5🌪️ Welcome to the Gravitite Gladiator Arena!")
            player.sendMessage("§dGravity is reversed - fight in 3D space!")
            
            // Apply anti-gravity effects
            player.addPotionEffect(PotionEffect(PotionEffectType.LEVITATION, Int.MAX_VALUE, 1))
            player.addPotionEffect(PotionEffect(PotionEffectType.SLOW_FALLING, Int.MAX_VALUE, 0))
            
            // Give gravitite equipment
            player.inventory.addItem(
                ItemStack(Material.DIAMOND_SWORD).apply {
                    itemMeta = itemMeta?.apply {
                        setDisplayName("§5⚡ Gravitite Blade")
                        lore = listOf("§7Reverses enemy gravity on hit")
                    }
                }
            )
            
            // Create anti-gravity field effect
            val loc = player.location
            for (i in 0..100) {
                loc.world.spawnParticle(
                    Particle.PORTAL,
                    loc.clone().add(
                        (Math.random() - 0.5) * 20,
                        Math.random() * 30,
                        (Math.random() - 0.5) * 20
                    ),
                    1
                )
            }
        }
        
        fun gravititeWeaponHit(attacker: Player, victim: Player) {
            victim.sendMessage("§5🌪️ The Gravitite weapon reverses your gravity!")
            
            // Reverse gravity for victim
            victim.addPotionEffect(PotionEffect(PotionEffectType.LEVITATION, 200, 2))
            
            // Create gravity reversal effect
            victim.world.spawnParticle(Particle.PORTAL, victim.location, 30)
            victim.world.playSound(victim.location, Sound.ENTITY_ENDERMAN_TELEPORT, 1.0f, 0.5f)
        }
    }
    
    /**
     * 🔥 PHOENIX BATTLE ROYALE
     * - Last player standing wins
     * - Players get phoenix rebirth abilities
     * - Shrinking play area with storm effects
     */
    object PhoenixBattleRoyale {

        val playersInGame = mutableSetOf<Player>()
        private var gameActive = false
        
        fun joinGame(player: Player) {
            if (gameActive) {
                player.sendMessage("§c🔥 Game already in progress!")
                return
            }
            
            playersInGame.add(player)
            player.sendMessage("§6🔥 You joined the Phoenix Battle Royale!")
            player.sendMessage("§e⚡ You have 3 phoenix rebirths!")
            
            // Give phoenix abilities
            givePhoenixPowers(player)
        }
        
        private fun givePhoenixPowers(player: Player) {
            // Phoenix rebirth counter (stored in player data)
            player.sendMessage("§c🔥 Phoenix powers activated!")
            
            // Fire resistance
            player.addPotionEffect(PotionEffect(PotionEffectType.FIRE_RESISTANCE, Int.MAX_VALUE, 0))
            
            // Phoenix equipment
            player.inventory.addItem(
                ItemStack(Material.BOW).apply {
                    itemMeta = itemMeta?.apply {
                        setDisplayName("§c🔥 Phoenix Bow")
                        lore = listOf("§7Shoots flaming arrows")
                    }
                }
            )
        }
        
        fun phoenixRebirth(player: Player): Boolean {
            // Check if player has rebirths left (would be stored in player data)
            val rebirthsLeft = 2 // Placeholder
            
            if (rebirthsLeft > 0) {
                player.sendMessage("§c🔥 PHOENIX REBIRTH! You rise from the ashes!")
                
                // Full heal and effects
                player.health = player.maxHealth
                player.addPotionEffect(PotionEffect(PotionEffectType.REGENERATION, 200, 2))
                player.addPotionEffect(PotionEffect(PotionEffectType.FIRE_RESISTANCE, 400, 0))
                
                // Create rebirth effect
                val loc = player.location
                for (i in 0..100) {
                    loc.world.spawnParticle(
                        Particle.FLAME,
                        loc.clone().add(
                            (Math.random() - 0.5) * 5,
                            Math.random() * 8,
                            (Math.random() - 0.5) * 5
                        ),
                        1
                    )
                }
                
                loc.world.playSound(loc, Sound.ENTITY_BLAZE_HURT, 2.0f, 1.2f)
                return true
            }
            
            return false
        }
    }
    
    // === SPECIAL PVP WEAPONS ===
    
    /**
     * ⚡ STORM WEAPONS
     * - Lightning-based attacks
     * - Chain lightning between enemies
     * - Storm calling abilities
     */
    object StormWeapons {
        
        fun lightningStrike(attacker: Player, target: Player) {
            target.sendMessage("§e⚡ ${attacker.name} calls down lightning upon you!")
            
            target.world.strikeLightning(target.location)
            
            // Chain lightning to nearby enemies
            target.world.getNearbyEntities(target.location, 5.0, 5.0, 5.0)
                .filterIsInstance<Player>()
                .filter { it != attacker && it != target }
                .take(2)
                .forEach { chainTarget ->
                    chainTarget.world.strikeLightning(chainTarget.location)
                    chainTarget.sendMessage("§e⚡ Chain lightning strikes you!")
                }
        }
    }
    
    /**
     * 🌪️ WIND WEAPONS
     * - Knockback and movement abilities
     * - Tornado creation
     * - Wind barriers
     */
    object WindWeapons {
        
        fun createTornado(player: Player, location: Location) {
            player.sendMessage("§b🌪️ You summon a mighty tornado!")
            
            // Create tornado effect
            for (height in 0..10) {
                for (angle in 0..360 step 20) {
                    val rad = Math.toRadians(angle.toDouble() + height * 10)
                    val radius = 3.0 - (height * 0.2)
                    val x = Math.cos(rad) * radius
                    val z = Math.sin(rad) * radius
                    
                    location.world.spawnParticle(
                        Particle.CLOUD,
                        location.clone().add(x, height.toDouble(), z),
                        1
                    )
                }
            }
            
            // Pull in nearby enemies
            location.world.getNearbyEntities(location, 8.0, 8.0, 8.0)
                .filterIsInstance<Player>()
                .filter { it != player }
                .forEach { target ->
                    val direction = location.subtract(target.location).toVector().normalize()
                    target.velocity = direction.multiply(0.5)
                    target.sendMessage("§b🌪️ The tornado pulls you in!")
                }
            
            location.world.playSound(location, Sound.ENTITY_ENDER_DRAGON_FLAP, 2.0f, 0.8f)
        }
    }
    
    // === EVENT HANDLERS ===
    
    @EventHandler
    fun onPlayerDeath(event: PlayerDeathEvent) {
        val player = event.entity
        
        // Check for phoenix rebirth in battle royale
        if (PhoenixBattleRoyale.playersInGame.contains(player)) {
            if (PhoenixBattleRoyale.phoenixRebirth(player)) {
                event.isCancelled = true
                return
            }
        }
        
        // Award research points to killer
        val killer = player.killer
        if (killer != null) {
            ResearchSystem.awardResearchPoints(killer, ResearchSystem.ResearchActivity.USE_SPELL)
            killer.sendMessage("§6⚔️ PvP Victory! Research progress gained!")
        }
    }
    
    @EventHandler
    fun onEntityDamage(event: EntityDamageByEntityEvent) {
        val attacker = event.damager as? Player ?: return
        val victim = event.entity as? Player ?: return
        
        val weapon = attacker.inventory.itemInMainHand
        
        // Check for special weapon effects
        when (weapon.itemMeta?.displayName) {
            "§5⚡ Gravitite Blade" -> {
                GravititeGladiator.gravititeWeaponHit(attacker, victim)
            }
            "§e⚡ Storm Hammer" -> {
                StormWeapons.lightningStrike(attacker, victim)
            }
            "§b🌪️ Wind Blade" -> {
                WindWeapons.createTornado(attacker, victim.location)
            }
        }
    }
}
