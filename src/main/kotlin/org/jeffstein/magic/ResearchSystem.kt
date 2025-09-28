package org.jeffstein.magic

import org.bukkit.entity.Player
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

/**
 * Research and progression system for magical advancement
 */
object ResearchSystem {
    
    enum class ResearchField(
        val displayName: String,
        val description: String,
        val maxLevel: Int
    ) {
        ELEMENTAL_MAGIC("Elemental Magic", "Mastery over fire, water, air, and earth", 10),
        DIMENSIONAL_STUDIES("Dimensional Studies", "Understanding of space and portals", 8),
        CRYSTAL_RESONANCE("Crystal Resonance", "Harnessing the power of magical crystals", 12),
        GRAVITATIONAL_THEORY("Gravitational Theory", "Manipulation of gravity and levitation", 6),
        LIFE_ESSENCE("Life Essence", "Healing and regeneration magic", 8),
        STORM_MASTERY("Storm Mastery", "Control over weather and lightning", 7),
        VOID_KNOWLEDGE("Void Knowledge", "Dangerous magic from the void", 5),
        MAGITECH_ENGINEERING("Magitech Engineering", "Fusion of magic and technology", 15),
        ANCIENT_LORE("Ancient Lore", "Knowledge of the old Aether civilization", 10),
        PHOENIX_MYSTERIES("Phoenix Mysteries", "Secrets of rebirth and immortality", 4)
    }
    
    data class PlayerResearch(
        val researchLevels: MutableMap<ResearchField, Int> = mutableMapOf(),
        val researchProgress: MutableMap<ResearchField, Int> = mutableMapOf(),
        val unlockedSpells: MutableSet<SpellSystem.SpellType> = mutableSetOf(),
        val discoveredSecrets: MutableSet<String> = mutableSetOf()
    )
    
    private val playerResearchMap = mutableMapOf<Player, PlayerResearch>()
    
    /**
     * Get or create research data for a player
     */
    fun getPlayerResearch(player: Player): PlayerResearch {
        return playerResearchMap.getOrPut(player) { PlayerResearch() }
    }
    
    /**
     * Add research points to a field
     */
    fun addResearchProgress(player: Player, field: ResearchField, points: Int): Boolean {
        val research = getPlayerResearch(player)
        val currentLevel = research.researchLevels.getOrDefault(field, 0)
        
        if (currentLevel >= field.maxLevel) {
            return false // Already maxed out
        }
        
        val currentProgress = research.researchProgress.getOrDefault(field, 0)
        val newProgress = currentProgress + points
        val requiredPoints = getRequiredPointsForLevel(currentLevel + 1)
        
        research.researchProgress[field] = newProgress
        
        // Check if level up
        if (newProgress >= requiredPoints) {
            levelUpResearch(player, field)
            research.researchProgress[field] = newProgress - requiredPoints
            return true
        }
        
        return false
    }
    
    /**
     * Calculate required research points for a level
     */
    private fun getRequiredPointsForLevel(level: Int): Int {
        return level * level * 10 + level * 5
    }
    
    /**
     * Level up a research field
     */
    private fun levelUpResearch(player: Player, field: ResearchField) {
        val research = getPlayerResearch(player)
        val newLevel = research.researchLevels.getOrDefault(field, 0) + 1
        research.researchLevels[field] = newLevel
        
        player.sendMessage("§6§l[RESEARCH] §eYou have advanced in ${field.displayName}! Level: $newLevel")
        
        // Unlock spells based on research progress
        checkSpellUnlocks(player, field, newLevel)
        
        // Grant special abilities or items
        grantResearchRewards(player, field, newLevel)
    }
    
    /**
     * Check if new spells should be unlocked
     */
    private fun checkSpellUnlocks(player: Player, field: ResearchField, level: Int) {
        val research = getPlayerResearch(player)
        
        val spellsToUnlock = when (field) {
            ResearchField.ELEMENTAL_MAGIC -> {
                when (level) {
                    2 -> listOf(SpellSystem.SpellType.LIGHTNING)
                    5 -> listOf(SpellSystem.SpellType.STORM_CALL)
                    else -> emptyList()
                }
            }
            ResearchField.DIMENSIONAL_STUDIES -> {
                when (level) {
                    3 -> listOf(SpellSystem.SpellType.TELEPORTATION)
                    6 -> listOf(SpellSystem.SpellType.DIMENSIONAL_RIFT)
                    else -> emptyList()
                }
            }
            ResearchField.GRAVITATIONAL_THEORY -> {
                when (level) {
                    1 -> listOf(SpellSystem.SpellType.LEVITATION)
                    4 -> listOf(SpellSystem.SpellType.FLIGHT)
                    6 -> listOf(SpellSystem.SpellType.GRAVITY_REVERSAL)
                    else -> emptyList()
                }
            }
            ResearchField.LIFE_ESSENCE -> {
                when (level) {
                    2 -> listOf(SpellSystem.SpellType.HEALING)
                    6 -> listOf(SpellSystem.SpellType.SHIELD)
                    else -> emptyList()
                }
            }
            ResearchField.PHOENIX_MYSTERIES -> {
                when (level) {
                    4 -> listOf(SpellSystem.SpellType.PHOENIX_REBIRTH)
                    else -> emptyList()
                }
            }
            else -> emptyList()
        }
        
        spellsToUnlock.forEach { spell ->
            if (research.unlockedSpells.add(spell)) {
                player.sendMessage("§d§l[SPELL UNLOCKED] §5You have learned: ${spell.displayName}!")
            }
        }
    }
    
    /**
     * Grant rewards for research advancement
     */
    private fun grantResearchRewards(player: Player, field: ResearchField, level: Int) {
        when (field) {
            ResearchField.CRYSTAL_RESONANCE -> {
                if (level == 5) {
                    // Grant a special crystal
                    player.sendMessage("§b§l[DISCOVERY] §3You have discovered the secrets of Crystal Resonance!")
                }
            }
            ResearchField.MAGITECH_ENGINEERING -> {
                if (level == 10) {
                    // Grant access to advanced machines
                    player.sendMessage("§6§l[BREAKTHROUGH] §eYou can now craft advanced Magitech devices!")
                }
            }
            ResearchField.ANCIENT_LORE -> {
                if (level % 3 == 0) {
                    // Reveal ancient secrets
                    val secrets = listOf(
                        "The Aether was once home to a great civilization",
                        "Gravitite was created by ancient Aether mages",
                        "The Valkyries are guardians of ancient knowledge",
                        "Phoenix tears can restore any magical item"
                    )
                    val secret = secrets.random()
                    getPlayerResearch(player).discoveredSecrets.add(secret)
                    player.sendMessage("§e§l[ANCIENT SECRET] §7$secret")
                }
            }
            else -> {}
        }
    }
    
    /**
     * Award research points for various activities
     */
    fun awardResearchPoints(player: Player, activity: ResearchActivity) {
        when (activity) {
            ResearchActivity.CRAFT_MAGICAL_ITEM -> {
                addResearchProgress(player, ResearchField.MAGITECH_ENGINEERING, 5)
                addResearchProgress(player, ResearchField.CRYSTAL_RESONANCE, 2)
            }
            ResearchActivity.USE_SPELL -> {
                addResearchProgress(player, ResearchField.ELEMENTAL_MAGIC, 1)
            }
            ResearchActivity.DISCOVER_AETHER_STRUCTURE -> {
                addResearchProgress(player, ResearchField.ANCIENT_LORE, 10)
                addResearchProgress(player, ResearchField.DIMENSIONAL_STUDIES, 5)
            }
            ResearchActivity.DEFEAT_AETHER_BOSS -> {
                addResearchProgress(player, ResearchField.PHOENIX_MYSTERIES, 15)
                addResearchProgress(player, ResearchField.ANCIENT_LORE, 8)
            }
            ResearchActivity.MINE_RARE_ORE -> {
                addResearchProgress(player, ResearchField.CRYSTAL_RESONANCE, 3)
            }
            ResearchActivity.CREATE_PORTAL -> {
                addResearchProgress(player, ResearchField.DIMENSIONAL_STUDIES, 8)
            }
        }
    }
    
    enum class ResearchActivity {
        CRAFT_MAGICAL_ITEM,
        USE_SPELL,
        DISCOVER_AETHER_STRUCTURE,
        DEFEAT_AETHER_BOSS,
        MINE_RARE_ORE,
        CREATE_PORTAL
    }
    
    /**
     * Check if player has required research level
     */
    fun hasResearchLevel(player: Player, field: ResearchField, requiredLevel: Int): Boolean {
        val research = getPlayerResearch(player)
        return research.researchLevels.getOrDefault(field, 0) >= requiredLevel
    }
    
    /**
     * Check if player has unlocked a spell
     */
    fun hasUnlockedSpell(player: Player, spell: SpellSystem.SpellType): Boolean {
        val research = getPlayerResearch(player)
        return research.unlockedSpells.contains(spell)
    }
}
