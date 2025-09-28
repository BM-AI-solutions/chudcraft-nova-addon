package org.jeffstein.magic

import org.bukkit.entity.Player
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import org.bukkit.Location
import org.bukkit.Particle
import org.bukkit.Sound

/**
 * Spell system for the Aether-Tekkit addon
 * Provides magical abilities that players can learn and use
 */
object SpellSystem {
    
    enum class SpellType(
        val displayName: String,
        val manaCost: Int,
        val cooldown: Long, // in milliseconds
        val description: String
    ) {
        LEVITATION("Levitation", 20, 5000, "Grants temporary levitation"),
        TELEPORTATION("Teleportation", 50, 10000, "Teleports to target location"),
        HEALING("Healing", 30, 8000, "Restores health and removes negative effects"),
        LIGHTNING("Lightning Strike", 40, 6000, "Summons lightning at target location"),
        SHIELD("Magical Shield", 25, 12000, "Creates a protective barrier"),
        FLIGHT("Flight", 60, 15000, "Grants temporary flight ability"),
        GRAVITY_REVERSAL("Gravity Reversal", 80, 20000, "Reverses gravity in an area"),
        STORM_CALL("Storm Call", 100, 30000, "Summons a magical storm"),
        DIMENSIONAL_RIFT("Dimensional Rift", 150, 60000, "Opens a portal to the Aether"),
        PHOENIX_REBIRTH("Phoenix Rebirth", 200, 120000, "Resurrects with full health on death"),

        // === PVP SPELLS ===
        MANA_DRAIN("Mana Drain", 40, 8000, "Drains enemy mana and gives it to you"),
        SPELL_REFLECT("Spell Reflect", 60, 15000, "Reflects next spell back at caster"),
        CHAIN_LIGHTNING("Chain Lightning", 80, 12000, "Lightning that jumps between enemies"),
        WIND_PRISON("Wind Prison", 70, 18000, "Traps enemy in a tornado"),
        VOID_STEP("Void Step", 50, 10000, "Teleport behind target for surprise attack"),
        CRYSTAL_BARRIER("Crystal Barrier", 90, 25000, "Creates protective crystal wall"),
        PHOENIX_DIVE("Phoenix Dive", 120, 20000, "Dive attack with fire explosion"),
        GRAVITITE_SLAM("Gravitite Slam", 110, 22000, "Reverses gravity in large area")
    }
    
    data class PlayerMana(
        var currentMana: Int,
        var maxMana: Int,
        val spellCooldowns: MutableMap<SpellType, Long> = mutableMapOf()
    )
    
    private val playerManaMap = mutableMapOf<Player, PlayerMana>()
    
    /**
     * Get or create mana data for a player
     */
    fun getPlayerMana(player: Player): PlayerMana {
        return playerManaMap.getOrPut(player) { 
            PlayerMana(currentMana = 100, maxMana = 100) 
        }
    }
    
    /**
     * Check if player can cast a spell
     */
    fun canCastSpell(player: Player, spell: SpellType): Boolean {
        val manaData = getPlayerMana(player)
        
        // Check mana cost
        if (manaData.currentMana < spell.manaCost) {
            return false
        }
        
        // Check cooldown
        val lastCast = manaData.spellCooldowns[spell] ?: 0
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastCast < spell.cooldown) {
            return false
        }
        
        return true
    }
    
    /**
     * Cast a spell
     */
    fun castSpell(player: Player, spell: SpellType, targetLocation: Location? = null): Boolean {
        if (!canCastSpell(player, spell)) {
            return false
        }
        
        val manaData = getPlayerMana(player)
        
        // Consume mana
        manaData.currentMana -= spell.manaCost
        
        // Set cooldown
        manaData.spellCooldowns[spell] = System.currentTimeMillis()
        
        // Execute spell effect
        executeSpellEffect(player, spell, targetLocation)
        
        // Play casting effects
        player.world.playSound(player.location, Sound.ENTITY_ENDER_DRAGON_SHOOT, 1.0f, 1.5f)
        player.world.spawnParticle(Particle.ENCHANT, player.location, 20)
        
        return true
    }
    
    /**
     * Execute the actual spell effect
     */
    private fun executeSpellEffect(player: Player, spell: SpellType, targetLocation: Location?) {
        when (spell) {
            SpellType.LEVITATION -> {
                player.addPotionEffect(PotionEffect(PotionEffectType.LEVITATION, 200, 1))
                player.sendMessage("§bYou feel lighter than air!")
            }
            
            SpellType.TELEPORTATION -> {
                targetLocation?.let { loc ->
                    player.teleport(loc)
                    player.world.spawnParticle(Particle.PORTAL, loc, 50)
                    player.sendMessage("§dYou have been teleported!")
                }
            }
            
            SpellType.HEALING -> {
                player.health = player.maxHealth
                player.activePotionEffects.filter { it.type.name.contains("HARM") || it.type.name.contains("POISON") }
                    .forEach { player.removePotionEffect(it.type) }
                player.world.spawnParticle(Particle.HEART, player.location, 10)
                player.sendMessage("§aYou feel completely restored!")
            }
            
            SpellType.LIGHTNING -> {
                targetLocation?.let { loc ->
                    player.world.strikeLightning(loc)
                    player.sendMessage("§eLightning strikes at your command!")
                }
            }
            
            SpellType.SHIELD -> {
                player.addPotionEffect(PotionEffect(PotionEffectType.RESISTANCE, 600, 2))
                player.addPotionEffect(PotionEffect(PotionEffectType.ABSORPTION, 600, 1))
                player.world.spawnParticle(Particle.CLOUD, player.location, 20)
                player.sendMessage("§6A magical shield protects you!")
            }
            
            SpellType.FLIGHT -> {
                player.allowFlight = true
                player.isFlying = true
                // Schedule to remove flight after 30 seconds
                // This would need proper scheduling in a real implementation
                player.sendMessage("§bYou can now fly for a limited time!")
            }
            
            SpellType.GRAVITY_REVERSAL -> {
                // This would need custom implementation to reverse gravity
                player.addPotionEffect(PotionEffect(PotionEffectType.SLOW_FALLING, 400, 0))
                player.sendMessage("§5Gravity bends to your will!")
            }
            
            SpellType.STORM_CALL -> {
                // Create a storm effect around the player
                for (i in 0..10) {
                    val randomLoc = player.location.clone().add(
                        (Math.random() - 0.5) * 20,
                        Math.random() * 10,
                        (Math.random() - 0.5) * 20
                    )
                    player.world.strikeLightning(randomLoc)
                }
                player.sendMessage("§4You have summoned a mighty storm!")
            }
            
            SpellType.DIMENSIONAL_RIFT -> {
                // This would open a portal to the Aether dimension
                player.world.spawnParticle(Particle.PORTAL, player.location, 100)
                player.sendMessage("§dA rift to another dimension opens before you!")
            }
            
            SpellType.PHOENIX_REBIRTH -> {
                // This would be a passive effect that triggers on death
                player.sendMessage("§cThe power of the Phoenix flows through you!")
            }

            // === PVP SPELL IMPLEMENTATIONS ===
            SpellType.MANA_DRAIN -> {
                targetLocation?.let { loc ->
                    val nearbyPlayers = loc.world.getNearbyEntities(loc, 5.0, 5.0, 5.0)
                        .filterIsInstance<Player>()
                        .filter { it != player }

                    nearbyPlayers.forEach { target ->
                        val targetMana = getPlayerMana(target)
                        val drainAmount = minOf(30, targetMana.currentMana)

                        targetMana.currentMana -= drainAmount
                        getPlayerMana(player).currentMana += drainAmount

                        target.sendMessage("§5💀 Your mana is being drained!")
                        player.sendMessage("§5⚡ You drain ${target.name}'s mana!")

                        // Create drain effect
                        for (i in 0..10) {
                            val midPoint = player.location.clone().add(target.location).multiply(0.5)
                            midPoint.world.spawnParticle(Particle.WITCH, midPoint, 1)
                        }
                    }
                }
            }

            SpellType.SPELL_REFLECT -> {
                player.addPotionEffect(PotionEffect(PotionEffectType.ABSORPTION, 200, 0))
                player.sendMessage("§6🛡️ Next spell will be reflected back!")

                // Create shield effect
                for (angle in 0..360 step 30) {
                    val rad = Math.toRadians(angle.toDouble())
                    val x = Math.cos(rad) * 2
                    val z = Math.sin(rad) * 2
                    player.world.spawnParticle(
                        Particle.ENCHANT,
                        player.location.clone().add(x, 1.0, z),
                        1
                    )
                }
            }

            SpellType.CHAIN_LIGHTNING -> {
                targetLocation?.let { loc ->
                    val targets = loc.world.getNearbyEntities(loc, 8.0, 8.0, 8.0)
                        .filterIsInstance<Player>()
                        .filter { it != player }
                        .take(3)

                    targets.forEach { target ->
                        target.world.strikeLightning(target.location)
                        target.sendMessage("§e⚡ Chain lightning strikes you!")
                        target.damage(4.0)
                    }

                    player.sendMessage("§e⚡ Chain lightning strikes ${targets.size} enemies!")
                }
            }

            SpellType.WIND_PRISON -> {
                targetLocation?.let { loc ->
                    val nearbyPlayers = loc.world.getNearbyEntities(loc, 3.0, 3.0, 3.0)
                        .filterIsInstance<Player>()
                        .filter { it != player }

                    nearbyPlayers.forEach { target ->
                        target.addPotionEffect(PotionEffect(PotionEffectType.SLOWNESS, 200, 3))
                        target.addPotionEffect(PotionEffect(PotionEffectType.LEVITATION, 100, 1))
                        target.sendMessage("§b🌪️ You're trapped in a wind prison!")

                        // Create tornado effect around target
                        for (height in 0..5) {
                            for (angle in 0..360 step 45) {
                                val rad = Math.toRadians(angle.toDouble())
                                val x = Math.cos(rad) * 2
                                val z = Math.sin(rad) * 2
                                target.world.spawnParticle(
                                    Particle.CLOUD,
                                    target.location.clone().add(x, height.toDouble(), z),
                                    1
                                )
                            }
                        }
                    }
                }
            }

            SpellType.VOID_STEP -> {
                targetLocation?.let { loc ->
                    val nearbyPlayers = loc.world.getNearbyEntities(loc, 5.0, 5.0, 5.0)
                        .filterIsInstance<Player>()
                        .filter { it != player }
                        .firstOrNull()

                    nearbyPlayers?.let { target ->
                        // Teleport behind target
                        val behindTarget = target.location.clone().subtract(target.location.direction.multiply(2))
                        player.teleport(behindTarget)

                        player.sendMessage("§5🌌 You step through the void!")
                        target.sendMessage("§5👤 ${player.name} appears behind you!")

                        // Create void effect
                        player.world.spawnParticle(Particle.PORTAL, player.location, 30)
                        target.world.spawnParticle(Particle.PORTAL, target.location, 30)
                    }
                }
            }

            SpellType.CRYSTAL_BARRIER -> {
                player.sendMessage("§b💎 Crystal barrier erected!")

                // Create crystal wall effect
                val forward = player.location.direction.normalize()
                val right = forward.clone().crossProduct(org.bukkit.util.Vector(0, 1, 0)).normalize()

                for (i in -3..3) {
                    for (j in 0..3) {
                        val crystalPos = player.location.clone()
                            .add(forward.clone().multiply(3))
                            .add(right.clone().multiply(i))
                            .add(0.0, j.toDouble(), 0.0)

                        crystalPos.world.spawnParticle(Particle.END_ROD, crystalPos, 1)
                    }
                }

                player.world.playSound(player.location, Sound.BLOCK_GLASS_PLACE, 2.0f, 1.5f)
            }

            SpellType.PHOENIX_DIVE -> {
                targetLocation?.let { loc ->
                    // Launch player into air then dive down
                    player.velocity = player.velocity.add(org.bukkit.util.Vector(0.0, 1.5, 0.0))

                    player.sendMessage("§c🔥 PHOENIX DIVE ATTACK!")

                    // Create fire explosion at target location
                    loc.world.createExplosion(loc, 3.0f, false, false)

                    // Damage nearby enemies
                    loc.world.getNearbyEntities(loc, 5.0, 5.0, 5.0)
                        .filterIsInstance<Player>()
                        .filter { it != player }
                        .forEach { target ->
                            target.damage(8.0)
                            target.fireTicks = 100
                            target.sendMessage("§c🔥 Phoenix dive burns you!")
                        }

                    // Create phoenix effect
                    for (i in 0..50) {
                        loc.world.spawnParticle(
                            Particle.FLAME,
                            loc.clone().add(
                                (Math.random() - 0.5) * 8,
                                Math.random() * 5,
                                (Math.random() - 0.5) * 8
                            ),
                            1
                        )
                    }
                }
            }

            SpellType.GRAVITITE_SLAM -> {
                player.sendMessage("§5🌪️ GRAVITITE SLAM! Gravity reversed!")

                val loc = player.location

                // Reverse gravity for all nearby players
                loc.world.getNearbyEntities(loc, 10.0, 10.0, 10.0)
                    .filterIsInstance<Player>()
                    .filter { it != player }
                    .forEach { target ->
                        target.addPotionEffect(PotionEffect(PotionEffectType.LEVITATION, 300, 2))
                        target.sendMessage("§5🌪️ Gravity reversal affects you!")
                    }

                // Create massive gravitite effect
                for (i in 0..100) {
                    loc.world.spawnParticle(
                        Particle.PORTAL,
                        loc.clone().add(
                            (Math.random() - 0.5) * 20,
                            Math.random() * 15,
                            (Math.random() - 0.5) * 20
                        ),
                        1
                    )
                }

                loc.world.playSound(loc, Sound.ENTITY_ENDER_DRAGON_FLAP, 3.0f, 0.5f)
            }
        }
    }
    
    /**
     * Regenerate mana over time
     */
    fun regenerateMana(player: Player, amount: Int = 1) {
        val manaData = getPlayerMana(player)
        manaData.currentMana = minOf(manaData.currentMana + amount, manaData.maxMana)
    }
    
    /**
     * Get remaining cooldown for a spell
     */
    fun getRemainingCooldown(player: Player, spell: SpellType): Long {
        val manaData = getPlayerMana(player)
        val lastCast = manaData.spellCooldowns[spell] ?: 0
        val currentTime = System.currentTimeMillis()
        val remaining = spell.cooldown - (currentTime - lastCast)
        return maxOf(0, remaining)
    }
}
