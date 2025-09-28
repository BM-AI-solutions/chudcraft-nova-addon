package org.jeffstein.entities

import org.jeffstein.AetherTekkitAddon
import xyz.xenondevs.nova.initialize.Init
import xyz.xenondevs.nova.initialize.InitStage
import org.bukkit.entity.Player
import org.bukkit.entity.Entity
import org.bukkit.Location
import org.bukkit.Particle
import org.bukkit.Sound
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

@Init(stage = InitStage.PRE_PACK)
object Entities {

    // === PASSIVE AETHER CREATURES ===

    /**
     * 🐄 FLYING COW - The most iconic Aether mob!
     * - Floats around peacefully in the sky
     * - Can be milked for special "Cloud Milk"
     * - Makes adorable "moo" sounds while flying
     * - Sometimes drops from the sky if it gets tired
     */
    object FlyingCow {
        fun spawnBehavior(location: Location) {
            // Spawns floating 2-3 blocks above ground
            // Slowly drifts around in circles
            // Occasionally "moos" and creates cloud particles
        }

        fun onMilk(player: Player): ItemStack {
            player.sendMessage("§b🥛 You milk the flying cow and get magical cloud milk!")
            return ItemStack(Material.MILK_BUCKET) // Would be custom cloud milk
        }

        fun createCloudTrail(location: Location) {
            location.world.spawnParticle(Particle.CLOUD, location, 3, 0.5, 0.2, 0.5, 0.01)
        }
    }

    /**
     * 🐷 PHYG - Flying pig that's rideable!
     * - Pink pig with tiny wings
     * - Can be saddled and ridden through the sky
     * - Makes snorting sounds while flying
     * - Loves carrots and will follow players holding them
     */
    object Phyg {
        fun onRide(player: Player) {
            player.sendMessage("§d🐷 Wheee! You're flying on a Phyg!")
            player.addPotionEffect(PotionEffect(PotionEffectType.SLOW_FALLING, 200, 0))
        }

        fun wingFlapEffect(location: Location) {
            location.world.spawnParticle(Particle.HEART, location, 1)
            location.world.playSound(location, Sound.ENTITY_PIG_AMBIENT, 0.5f, 1.5f)
        }
    }

    /**
     * 🐑 SHEEPUFF - Ultra fluffy cloud sheep!
     * - Extremely fluffy and bouncy
     * - Produces "Cloud Wool" instead of regular wool
     * - When sheared, creates a small cloud explosion
     * - Bounces players who touch it
     */
    object Sheepuff {
        fun onShear(player: Player): List<ItemStack> {
            player.sendMessage("§f☁️ The Sheepuff releases a puff of cloud wool!")

            // Create cloud explosion effect
            val loc = player.location
            for (i in 0..20) {
                loc.world.spawnParticle(
                    Particle.CLOUD,
                    loc.clone().add(
                        (Math.random() - 0.5) * 3,
                        Math.random() * 2,
                        (Math.random() - 0.5) * 3
                    ),
                    1
                )
            }

            return listOf(
                ItemStack(Material.WHITE_WOOL, 3), // Would be cloud wool
                ItemStack(Material.STRING, 2)
            )
        }

        fun bouncePlayer(player: Player) {
            player.velocity = player.velocity.add(org.bukkit.util.Vector(0.0, 0.8, 0.0))
            player.sendMessage("§f🦘 Boing! The fluffy Sheepuff bounces you!")
        }
    }

    /**
     * 🦅 MOA - Majestic rideable birds!
     * - Come in different colors (Blue, White, Black, Orange)
     * - Can be tamed with Aether food
     * - Rideable for fast sky travel
     * - Each color has different flight characteristics
     */
    object Moa {
        enum class MoaType(val displayName: String, val speed: Double, val specialAbility: String) {
            BLUE("Blue Moa", 1.2, "Extra height gain"),
            WHITE("White Moa", 1.0, "Slow falling when dismounting"),
            BLACK("Black Moa", 1.5, "Night vision while riding"),
            ORANGE("Orange Moa", 1.3, "Fire resistance")
        }

        fun onTame(player: Player, type: MoaType) {
            player.sendMessage("§6🦅 You have tamed a ${type.displayName}!")
            player.sendMessage("§7Special ability: ${type.specialAbility}")
        }

        fun flyingEffect(location: Location, type: MoaType) {
            val particle = when (type) {
                MoaType.BLUE -> Particle.DRIPPING_WATER
                MoaType.WHITE -> Particle.CLOUD
                MoaType.BLACK -> Particle.SMOKE
                MoaType.ORANGE -> Particle.FLAME
            }
            location.world.spawnParticle(particle, location, 2)
        }
    }

    /**
     * 🐰 AERBUNNY - Adorable floating bunny hat!
     * - Tiny floating bunny that follows players
     * - Can be caught and worn as a hat
     * - Provides slow falling while worn
     * - Makes cute squeaking sounds
     */
    object Aerbunny {
        fun onCapture(player: Player) {
            player.sendMessage("§a🐰 You caught an Aerbunny! It sits on your head!")
            player.sendMessage("§7The Aerbunny grants you slow falling!")

            // Give permanent slow falling while "wearing" the bunny
            player.addPotionEffect(PotionEffect(PotionEffectType.SLOW_FALLING, Int.MAX_VALUE, 0))
        }

        fun onRelease(player: Player) {
            player.sendMessage("§a🐰 You release the Aerbunny. It floats away happily!")
            player.removePotionEffect(PotionEffectType.SLOW_FALLING)
        }

        fun cuteEffect(location: Location) {
            location.world.spawnParticle(Particle.HEART, location, 1)
            if (Math.random() < 0.1) {
                location.world.playSound(location, Sound.ENTITY_RABBIT_AMBIENT, 0.3f, 2.0f)
            }
        }
    }

    // === NEUTRAL AETHER CREATURES ===

    /**
     * 💧 SWET - Bouncy slime creatures!
     * - Blue and Golden variants
     * - Bounce players around when touched
     * - Blue Swets are friendly, Golden ones are more aggressive
     * - Drop Swet Balls used for crafting
     */
    object Swet {
        enum class SwetType(val color: String, val bounceForce: Double, val friendly: Boolean) {
            BLUE("Blue", 0.5, true),
            GOLDEN("Golden", 1.0, false)
        }

        fun onTouch(player: Player, type: SwetType) {
            val message = if (type.friendly) {
                "§b💧 The ${type.color} Swet bounces you playfully!"
            } else {
                "§6💧 The ${type.color} Swet launches you into the air!"
            }

            player.sendMessage(message)
            player.velocity = player.velocity.add(org.bukkit.util.Vector(0.0, type.bounceForce, 0.0))

            // Create bounce effect
            player.world.spawnParticle(Particle.BUBBLE, player.location, 10)
            player.world.playSound(player.location, Sound.ENTITY_SLIME_JUMP, 1.0f, 1.2f)
        }
    }

    /**
     * 🌿 AECHOR PLANT - Dangerous stationary plant!
     * - Looks like an innocent flower until you get close
     * - Shoots poisonous needles at nearby players
     * - Can be harvested for Aechor Petals (alchemy ingredient)
     * - Makes hissing sounds when agitated
     */
    object AechorPlant {
        fun onPlayerNear(player: Player, plantLocation: Location) {
            if (player.location.distance(plantLocation) < 5) {
                shootNeedle(player, plantLocation)
            }
        }

        private fun shootNeedle(player: Player, plantLocation: Location) {
            player.sendMessage("§2🌿 The Aechor Plant shoots a poisonous needle at you!")

            // Create needle effect
            plantLocation.world.spawnParticle(
                Particle.CRIT,
                plantLocation.clone().add(0.0, 1.0, 0.0),
                5,
                0.1, 0.1, 0.1, 0.1
            )

            plantLocation.world.playSound(plantLocation, Sound.ENTITY_ARROW_SHOOT, 0.8f, 1.5f)

            // Apply poison if hit
            if (Math.random() < 0.7) {
                player.addPotionEffect(PotionEffect(PotionEffectType.POISON, 100, 0))
                player.sendMessage("§2💀 You've been poisoned by the Aechor Plant!")
            }
        }
    }

    /**
     * 🐓 COCKATRICE - Petrifying chicken!
     * - Looks like a chicken with a serpent tail
     * - Can temporarily turn players to stone (slowness + mining fatigue)
     * - Drops Cockatrice Feathers for crafting
     * - Makes weird chicken-snake hybrid sounds
     */
    object Cockatrice {
        fun petrifyPlayer(player: Player) {
            player.sendMessage("§8🐓 The Cockatrice's gaze turns you to stone!")

            // Apply "petrification" effects
            player.addPotionEffect(PotionEffect(PotionEffectType.SLOWNESS, 200, 3))
            player.addPotionEffect(PotionEffect(PotionEffectType.MINING_FATIGUE, 200, 2))
            player.addPotionEffect(PotionEffect(PotionEffectType.RESISTANCE, 200, 1)) // Some protection while petrified

            // Create stone effect
            player.world.spawnParticle(Particle.BLOCK, player.location, 20, Material.STONE.createBlockData())
            player.world.playSound(player.location, Sound.BLOCK_STONE_PLACE, 1.0f, 0.8f)
        }
    }

    // === HOSTILE AETHER CREATURES ===

    /**
     * 🗿 SENTRY - Ancient stone guardians!
     * - Stationary stone blocks that come alive when players approach
     * - Shoot stone projectiles at intruders
     * - Guard valuable loot and dungeon entrances
     * - Can be temporarily disabled with Ambrosium
     */
    object Sentry {
        fun activate(player: Player, sentryLocation: Location) {
            player.sendMessage("§8🗿 The ancient Sentry awakens and targets you!")

            sentryLocation.world.playSound(sentryLocation, Sound.BLOCK_STONE_BREAK, 1.0f, 0.5f)
            sentryLocation.world.spawnParticle(Particle.BLOCK, sentryLocation, 30, Material.STONE.createBlockData())
        }

        fun shootProjectile(target: Player, sentryLocation: Location) {
            target.sendMessage("§8💥 The Sentry hurls a stone projectile at you!")

            // Create projectile effect
            sentryLocation.world.spawnParticle(
                Particle.BLOCK,
                sentryLocation.clone().add(0.0, 2.0, 0.0),
                10,
                Material.COBBLESTONE.createBlockData()
            )

            sentryLocation.world.playSound(sentryLocation, Sound.ENTITY_ARROW_SHOOT, 1.0f, 0.8f)
        }
    }

    /**
     * 📦 MIMIC - Treasure chests that bite!
     * - Disguised as innocent treasure chests
     * - Spring to life when opened
     * - Have sharp teeth and try to eat players
     * - Drop valuable loot when defeated
     */
    object Mimic {
        fun reveal(player: Player, mimicLocation: Location) {
            player.sendMessage("§c📦 The chest suddenly sprouts teeth and lunges at you!")

            mimicLocation.world.playSound(mimicLocation, Sound.ENTITY_ZOMBIE_BREAK_WOODEN_DOOR, 1.0f, 1.5f)
            mimicLocation.world.spawnParticle(Particle.ANGRY_VILLAGER, mimicLocation, 5)

            // Create "teeth" effect with bone particles
            for (i in 0..8) {
                mimicLocation.world.spawnParticle(
                    Particle.ITEM,
                    mimicLocation.clone().add(Math.random() - 0.5, 1.0, Math.random() - 0.5),
                    1,
                    ItemStack(Material.BONE)
                )
            }
        }

        fun chomp(player: Player) {
            player.sendMessage("§c🦷 CHOMP! The Mimic tries to bite you!")
            player.world.playSound(player.location, Sound.ENTITY_WOLF_GROWL, 1.0f, 0.8f)
        }
    }

    /**
     * ⚔️ VALKYRIE - Warrior maidens of the sky!
     * - Elite female warriors with wings and spears
     * - Guard important Aether locations
     * - Can fly and perform aerial attacks
     * - Drop rare Valkyrie equipment when defeated
     */
    object Valkyrie {
        fun battleCry(player: Player, valkyrieLocation: Location) {
            player.sendMessage("§6⚔️ The Valkyrie lets out a fierce battle cry!")

            valkyrieLocation.world.playSound(valkyrieLocation, Sound.ENTITY_ENDER_DRAGON_GROWL, 1.0f, 1.3f)
            valkyrieLocation.world.spawnParticle(Particle.ENCHANT, valkyrieLocation, 20)
        }

        fun aerialStrike(target: Player, valkyrieLocation: Location) {
            target.sendMessage("§6🗡️ The Valkyrie dives from above with her spear!")

            // Create diving effect
            for (y in 5 downTo 0) {
                valkyrieLocation.world.spawnParticle(
                    Particle.CRIT,
                    valkyrieLocation.clone().add(0.0, y.toDouble(), 0.0),
                    3
                )
            }

            valkyrieLocation.world.playSound(valkyrieLocation, Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 1.2f)
        }
    }

    /**
     * 🔥 FIRE MINION - Living flames!
     * - Small elemental creatures made of fire
     * - Set blocks and players on fire
     * - Immune to fire damage
     * - Drop Fire Essence when defeated
     */
    object FireMinion {
        fun ignite(target: Player) {
            target.sendMessage("§c🔥 The Fire Minion sets you ablaze!")
            target.fireTicks = 100

            target.world.spawnParticle(Particle.FLAME, target.location, 15)
            target.world.playSound(target.location, Sound.ITEM_FLINTANDSTEEL_USE, 1.0f, 1.2f)
        }

        fun fireTrail(location: Location) {
            location.world.spawnParticle(Particle.FLAME, location, 3, 0.3, 0.1, 0.3, 0.01)
            if (Math.random() < 0.1) {
                location.world.playSound(location, Sound.BLOCK_FIRE_AMBIENT, 0.3f, 1.5f)
            }
        }
    }

    // === BOSS CREATURES ===

    /**
     * 🏔️ SLIDER - The Bronze Dungeon Boss!
     * - Massive stone cube that slides around
     * - Tries to crush players by slamming into walls
     * - Creates shockwaves when it hits something
     * - Drops the Bronze Dungeon Key when defeated
     */
    object Slider {
        fun slam(location: Location) {
            location.world.playSound(location, Sound.ENTITY_GENERIC_EXPLODE, 2.0f, 0.5f)

            // Create massive impact effect
            for (i in 0..50) {
                location.world.spawnParticle(
                    Particle.BLOCK,
                    location.clone().add(
                        (Math.random() - 0.5) * 8,
                        Math.random() * 3,
                        (Math.random() - 0.5) * 8
                    ),
                    1,
                    Material.STONE.createBlockData()
                )
            }

            // Damage nearby players
            location.world.getNearbyEntities(location, 5.0, 5.0, 5.0)
                .filterIsInstance<Player>()
                .forEach { player ->
                    player.sendMessage("§8💥 The Slider's impact sends shockwaves through the ground!")
                    player.damage(6.0)
                    player.velocity = player.velocity.add(org.bukkit.util.Vector(0.0, 0.5, 0.0))
                }
        }

        fun charge(target: Player, sliderLocation: Location) {
            target.sendMessage("§8🏔️ The massive Slider charges directly at you!")
            sliderLocation.world.playSound(sliderLocation, Sound.ENTITY_RAVAGER_ROAR, 2.0f, 0.8f)
        }
    }

    /**
     * 👑 VALKYRIE QUEEN - Ultimate Aether Boss!
     * - Majestic winged warrior with golden armor
     * - Commands other Valkyries in battle
     * - Has multiple attack phases
     * - Drops legendary equipment and the Silver Dungeon Key
     */
    object ValkyrieQueen {
        fun royalCommand(location: Location) {
            location.world.playSound(location, Sound.ENTITY_ENDER_DRAGON_GROWL, 2.0f, 1.2f)

            // Summon Valkyrie minions
            for (i in 0..2) {
                val spawnLoc = location.clone().add(
                    (Math.random() - 0.5) * 10,
                    2.0,
                    (Math.random() - 0.5) * 10
                )
                spawnLoc.world.spawnParticle(Particle.ENCHANT, spawnLoc, 30)
            }

            location.world.getNearbyEntities(location, 20.0, 20.0, 20.0)
                .filterIsInstance<Player>()
                .forEach { player ->
                    player.sendMessage("§6👑 The Valkyrie Queen commands her warriors to battle!")
                }
        }

        fun divineStrike(target: Player, queenLocation: Location) {
            target.sendMessage("§e⚡ The Valkyrie Queen calls down divine lightning!")

            target.world.strikeLightning(target.location)
            target.world.spawnParticle(Particle.ENCHANT, target.location, 50)
        }
    }

    /**
     * ☀️ SUN SPIRIT - The Gold Dungeon Boss!
     * - Radiant being of pure light and fire
     * - Controls solar energy and light beams
     * - Becomes more powerful during day time
     * - Drops the Gold Dungeon Key and Phoenix artifacts
     */
    object SunSpirit {
        fun solarFlare(location: Location) {
            location.world.playSound(location, Sound.ENTITY_BLAZE_SHOOT, 2.0f, 0.8f)

            // Create massive light explosion
            for (i in 0..100) {
                location.world.spawnParticle(
                    Particle.FLAME,
                    location.clone().add(
                        (Math.random() - 0.5) * 15,
                        Math.random() * 8,
                        (Math.random() - 0.5) * 15
                    ),
                    1
                )
            }

            // Blind and burn nearby players
            location.world.getNearbyEntities(location, 10.0, 10.0, 10.0)
                .filterIsInstance<Player>()
                .forEach { player ->
                    player.sendMessage("§e☀️ The Sun Spirit's radiance overwhelms you!")
                    player.addPotionEffect(PotionEffect(PotionEffectType.BLINDNESS, 100, 1))
                    player.fireTicks = 60
                }
        }

        fun lightBeam(target: Player, spiritLocation: Location) {
            target.sendMessage("§e🌟 A concentrated beam of solar energy targets you!")

            // Create beam effect from spirit to target
            val direction = target.location.subtract(spiritLocation).toVector().normalize()
            for (i in 0..20) {
                val beamPoint = spiritLocation.clone().add(direction.clone().multiply(i))
                beamPoint.world.spawnParticle(Particle.END_ROD, beamPoint, 1)
            }

            spiritLocation.world.playSound(spiritLocation, Sound.ENTITY_GUARDIAN_ATTACK, 1.0f, 1.5f)
        }
    }

    // Note: In a full Nova implementation, these would be actual entity classes
    // with proper AI, rendering, and behavior systems. This provides the design
    // framework and special ability implementations for the most awesome and
    // wacky Aether mobs ever created! 🎮✨

}
