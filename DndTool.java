public class DndTool {
    public static void main(String[] args) {

        // input data for character stats; hard coded for the time being
        int level = 4;
        int[] abilityScores = new int[]{14,14,14,10,16,10};
        Skill[] skillProficiencies = new Skill[]{Skill.ANIMAL_HANDLING, Skill.DECEPTION, Skill.NATURE, Skill.PERCEPTION, Skill.SURVIVAL};
        Ability[] savingThrowProficiencies = new Ability[]{Ability.INTELLIGENCE, Ability.WISDOM};

        Character chara = new Character();
        chara.setLevel(level); // also sets proficiency bonus

        chara.setAbilityScores(abilityScores); // calculates modifiers as well
        
        chara.setSkillProficiencies(skillProficiencies);
        chara.updateSkillModifiers();

        chara.setSavingThrowProficiencies(savingThrowProficiencies);
        chara.updateSavingThrowModifiers();

        System.out.println(chara);
    }
}

/* vague ideas for class classes:

public class Class {
    private ArrayList<ClassFeature> features = new ArrayList<ClassFeature>(); // each one stores feature title, description, and each level it occurs
    
    private int hitDieType;

    private Ability spellAbility;
    private int[][] spellSlotsPerLevel;

    // etc...
}

public class ClassFeature {
    // feature & description might also need to be ArrayLists if their names/descriptions change between levels
    // (so they can be indexed depending on the current level's position in levelsOfOccurrence)

    private String feature;                          // Feature title
    private String description;                      // Feature description
    private ArrayList<Integer> levelsOfOccurrence;   // Each level where it occurs

    // etc...
}
*/