package org.jeffstein.portals

import org.bukkit.Material
import org.bukkit.Location
import org.bukkit.Particle
import org.bukkit.Sound
import org.bukkit.World
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.block.Action
import org.bukkit.inventory.ItemStack
import org.bukkit.block.Block

/**
 * Handles Aether portal creation and teleportation
 * Classic Aether portal: Glowstone frame with water bucket activation
 */
object AetherPortalSystem : Listener {
    
    private val activePortals = mutableSetOf<Location>()
    
    /**
     * Handle water bucket interaction with glowstone to create portal
     */
    @EventHandler
    fun onPlayerInteract(event: PlayerInteractEvent) {
        if (event.action != Action.RIGHT_CLICK_BLOCK) return
        
        val block = event.clickedBlock ?: return
        val player = event.player
        val item = event.item ?: return
        
        // Check if player is using water bucket on glowstone
        if (item.type == Material.WATER_BUCKET && block.type == Material.GLOWSTONE) {
            if (tryCreatePortal(block, player)) {
                // Consume water bucket and give back empty bucket
                if (item.amount > 1) {
                    item.amount = item.amount - 1
                    player.inventory.addItem(ItemStack(Material.BUCKET))
                } else {
                    player.inventory.setItemInMainHand(ItemStack(Material.BUCKET))
                }
                
                event.isCancelled = true
                
                player.sendMessage("§b✨ The glowstone shimmers and opens a portal to the Aether!")
                player.world.playSound(block.location, Sound.BLOCK_PORTAL_TRIGGER, 1.0f, 1.2f)
                
                // Create portal effect
                for (i in 0..50) {
                    player.world.spawnParticle(
                        Particle.PORTAL, 
                        block.location.clone().add(0.5, 0.5, 0.5), 
                        1,
                        Math.random() - 0.5,
                        Math.random() - 0.5, 
                        Math.random() - 0.5,
                        0.1
                    )
                }
            }
        }
    }
    
    /**
     * Try to create an Aether portal at the given location
     */
    private fun tryCreatePortal(centerBlock: Block, player: Player): Boolean {
        // Check if this is a valid 4x5 glowstone frame
        if (!isValidPortalFrame(centerBlock)) {
            player.sendMessage("§cYou need a 4x5 glowstone frame to create an Aether portal!")
            return false
        }
        
        // Fill the portal with "aether portal blocks" (we'll use barrier blocks as placeholder)
        fillPortalFrame(centerBlock)
        
        // Add to active portals
        activePortals.add(centerBlock.location)
        
        return true
    }
    
    /**
     * Check if the glowstone forms a valid 4x5 portal frame
     */
    private fun isValidPortalFrame(centerBlock: Block): Boolean {
        val world = centerBlock.world
        val x = centerBlock.x
        val y = centerBlock.y
        val z = centerBlock.z
        
        // Check corners and edges of a 4x5 frame
        val frameBlocks = listOf(
            // Bottom row
            world.getBlockAt(x-1, y, z-1), world.getBlockAt(x, y, z-1), 
            world.getBlockAt(x+1, y, z-1), world.getBlockAt(x+2, y, z-1),
            
            // Top row  
            world.getBlockAt(x-1, y+4, z-1), world.getBlockAt(x, y+4, z-1),
            world.getBlockAt(x+1, y+4, z-1), world.getBlockAt(x+2, y+4, z-1),
            
            // Left side
            world.getBlockAt(x-1, y+1, z-1), world.getBlockAt(x-1, y+2, z-1), world.getBlockAt(x-1, y+3, z-1),
            
            // Right side
            world.getBlockAt(x+2, y+1, z-1), world.getBlockAt(x+2, y+2, z-1), world.getBlockAt(x+2, y+3, z-1)
        )
        
        // All frame blocks must be glowstone
        return frameBlocks.all { it.type == Material.GLOWSTONE }
    }
    
    /**
     * Fill the portal frame with portal blocks
     */
    private fun fillPortalFrame(centerBlock: Block) {
        val world = centerBlock.world
        val x = centerBlock.x
        val y = centerBlock.y
        val z = centerBlock.z
        
        // Fill the 2x3 interior with portal blocks (using water as placeholder)
        for (dx in 0..1) {
            for (dy in 1..3) {
                world.getBlockAt(x + dx, y + dy, z - 1).type = Material.WATER
            }
        }
    }
    
    /**
     * Handle player stepping into portal
     */
    fun handlePortalEntry(player: Player, portalLocation: Location) {
        if (!activePortals.contains(portalLocation)) return
        
        // Teleport to Aether dimension
        teleportToAether(player)
    }
    
    /**
     * Teleport player to the Aether dimension
     */
    private fun teleportToAether(player: Player) {
        // In a real implementation, this would teleport to the actual Aether world
        // For now, we'll simulate it with effects and a message
        
        player.sendMessage("§d🌤️ You step through the portal and feel yourself rising into the sky...")
        player.sendMessage("§bWelcome to the Aether! A realm of floating islands and endless sky!")
        
        // Create teleportation effects
        val loc = player.location
        player.world.playSound(loc, Sound.ENTITY_ENDERMAN_TELEPORT, 1.0f, 0.8f)
        
        for (i in 0..100) {
            player.world.spawnParticle(
                Particle.PORTAL,
                loc.clone().add(
                    (Math.random() - 0.5) * 3,
                    Math.random() * 3,
                    (Math.random() - 0.5) * 3
                ),
                1
            )
        }
        
        // Give player some levitation to simulate floating islands
        player.addPotionEffect(
            org.bukkit.potion.PotionEffect(
                org.bukkit.potion.PotionEffectType.LEVITATION, 
                100, 
                1
            )
        )
        
        // In a full implementation, you would:
        // 1. Get or create the Aether world
        // 2. Find a safe spawn location (floating island)
        // 3. Teleport the player there
        // 4. Create a return portal
        
        player.sendMessage("§7(Portal system framework - full dimension teleportation would be implemented here)")
    }
    
    /**
     * Alternative portal creation methods
     */
    object AlternativePortals {
        
        /**
         * Create a portal using the Quantum Aether Bridge machine
         */
        fun createQuantumPortal(location: Location, player: Player) {
            player.sendMessage("§5⚡ The Quantum Aether Bridge hums with energy...")
            player.sendMessage("§dA stable dimensional rift opens before you!")
            
            // Create a more advanced portal effect
            for (i in 0..200) {
                location.world.spawnParticle(
                    Particle.ENCHANT,
                    location.clone().add(
                        Math.sin(i * 0.1) * 2,
                        i * 0.01,
                        Math.cos(i * 0.1) * 2
                    ),
                    1
                )
            }
            
            location.world.playSound(location, Sound.BLOCK_BEACON_ACTIVATE, 1.0f, 1.5f)
        }
        
        /**
         * Create a portal using magical research
         */
        fun createResearchPortal(player: Player) {
            player.sendMessage("§6📚 Your research into Dimensional Studies pays off!")
            player.sendMessage("§eYou weave reality itself to create a passage to the Aether!")
            
            val loc = player.location
            
            // Create mystical portal effect
            for (angle in 0..360 step 10) {
                val rad = Math.toRadians(angle.toDouble())
                val x = Math.cos(rad) * 3
                val z = Math.sin(rad) * 3
                
                loc.world.spawnParticle(
                    Particle.WITCH,
                    loc.clone().add(x, 1.0, z),
                    1
                )
            }
            
            loc.world.playSound(loc, Sound.ENTITY_EVOKER_CAST_SPELL, 1.0f, 1.2f)
        }
    }
}
