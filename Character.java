enum Ability {
    STRENGTH,
    DEXTERITY,
    CONSTITUTION,
    INTELLIGENCE,
    WISDOM,
    CHARISMA
}

enum Skill {
    ACROBATICS,
    ANIMAL_HANDLING,
    ARCANA,
    ATHLETICS,
    DECEPTION,
    HISTORY,
    INSIGHT,
    INTIMIDATION,
    INVESTIGATION,
    MEDICINE,
    NATURE,
    PERCEPTION,
    PERFORMANCE,
    PERSUASION,
    RELIGION,
    SLEIGHT_OF_HAND,
    STEALTH,
    SURVIVAL
}

public class Character {
    // -- Cosmetics/character creation stuff, left out for now --
    // private String name;
    // private Class characterClass; // syntax moment
    // private String(?) race;
    // private String(?) background;

    private int level;
    private int proficiencyBonus;

    // private int maxHP;
    private int[] abilityScores = new int[6];
    private int[] abilityMods = new int[6];

    private int[] skillMods = new int[18];
    private boolean[] skillProfs = new boolean[18]; // Parallel to Skill enum

    private int[] savingThrowMods = new int[6];
    private boolean[] savingThrowProfs = new boolean[6]; // Parallel to Ability enum

    // Spell variables -- Need implementation of classes
    // private int spellSaveDC;
    // private int spellAtkMod;
    // private int spellSlotMaxima[] = new int[9]; // max spell slots per spell level, 1-9


    // setLevel - Sets level and calculates proficiency bonus
    public void setLevel(int level) {
        this.level = level;

        proficiencyBonus = 2 + (level-1)/4;
    }


    // --- ABILITY SCORE METHODS --- //

    // setAbilityScores - Copies a size 6 int array to ability scores 
    public void setAbilityScores(int[] scores) {
        for(int i = 0; i < abilityScores.length; i++) {
            abilityScores[i] = scores[i];
        }
        updateAbilityModifiers();
    }

    // addToAbility - Adds specified amount to character's respective Ability score
    public void addToAbility(Ability stat, int amount) {
        abilityScores[stat.ordinal()] += amount;
        updateAbilityModifiers();
    }

    // updateAbilityModifiers - Uses ability scores to calculate and store ability modifiers
    public void updateAbilityModifiers() {
        for(int i = 0; i < 6; i++) {
            abilityMods[i] = (abilityScores[i]-10)/2;
        }
    }


    // --- SKILL METHODS --- //

    // setSkillProficiencies - Uses an array of Skills to set each as proficient in skillProfs
    public void setSkillProficiencies(Skill[] profs) {
        for(Skill x: profs) {
            skillProfs[x.ordinal()] = true;
        }
    }

    // updateSkillModifiers - Refreshes and stores skill modifiers
    // [!] Requires ability scores and level (prof. bonus)
    public void updateSkillModifiers() {
        // Indicates each skill's respective ability
        Ability[] skillStats = new Ability[]{Ability.DEXTERITY,Ability.WISDOM,Ability.INTELLIGENCE,Ability.STRENGTH,Ability.CHARISMA,Ability.INTELLIGENCE,Ability.WISDOM,Ability.CHARISMA,Ability.INTELLIGENCE,Ability.WISDOM,Ability.INTELLIGENCE,Ability.WISDOM,Ability.CHARISMA,Ability.CHARISMA,Ability.INTELLIGENCE,Ability.DEXTERITY,Ability.DEXTERITY,Ability.WISDOM};

        for(int i = 0; i < 18; i++) {
            skillMods[i] = abilityMods[skillStats[i].ordinal()] + (skillProfs[i]? proficiencyBonus:0);
        }
    }


    // --- SAVING THROW METHODS --- //

    // setSavingThrowProficiencies - Uses an array of Abilities to set each as proficient in savingThrowProfs
    public void setSavingThrowProficiencies(Ability[] profs) {
        for(Ability x: profs) {
            savingThrowProfs[x.ordinal()] = true;
        }
    }

    // updateSavingThrowModifiers - Refreshes and stores saving throw modifiers
    // [!] Requires ability scores and level (prof. bonus)
    public void updateSavingThrowModifiers() {
        for(Ability x: Ability.values()) {
            savingThrowMods[x.ordinal()] = abilityMods[x.ordinal()] + (savingThrowProfs[x.ordinal()]? proficiencyBonus:0);
        }
    }

    
    // -------------------- //

    // Returns organized stats
    public String toString() {
        String output = "\n";

        output += "Level:\t\t" + level + '\n';
        output += "Prof. Bonus:\t" + proficiencyBonus + '\n';
        
        // (should probably add getAbilityMod(x) / getSkillMod(x) / etc. methods to make this stuff readable)
        output += "-- Ability Scores --\n";
        for(Ability a: Ability.values()) {
            output += abilityScores[a.ordinal()] + " (" + (abilityMods[a.ordinal()] >= 0? '+':"") + abilityMods[a.ordinal()] + ") - " + a.name() + '\n';
        }

        output += "\n-- Skills --\n";
        for(Skill s: Skill.values()) {
            output += "[" + (skillProfs[s.ordinal()]? 'X':' ') + "] " + skillMods[s.ordinal()] + " - " + s.name() + '\n';
        }

        output += "\n-- Saving Throws --\n";
        for(Ability a: Ability.values()) {
            output += "[" + (savingThrowProfs[a.ordinal()]? 'X':' ') + "] " + savingThrowMods[a.ordinal()] + " - " + a.name() + '\n';
        }

        return output;
    }
}