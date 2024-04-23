public class DndTool {
    public static void main(String[] args) {
        // input data for character stats; hard coded for the time being
        int level = 4;
        int[] abilityScores = new int[]{14,14,13,10,16,10};
        Skill[] skillProficiencies = new Skill[]{Skill.ANIMAL_HANDLING, Skill.DECEPTION, Skill.MEDICINE, Skill.NATURE, Skill.PERCEPTION, Skill.SURVIVAL};
        Ability[] savingThrowProficiencies = new Ability[]{Ability.INTELLIGENCE, Ability.WISDOM};

        // Character creation
        Character chara = new Character();
        chara.setLevel(level); // also sets proficiency bonus

        chara.setAbilityScores(abilityScores); // calculates modifiers as well
        
        chara.setSkillProficiencies(skillProficiencies);
        chara.updateSkillModifiers();

        chara.setSavingThrowProficiencies(savingThrowProficiencies);
        chara.updateSavingThrowModifiers();

        // Class assignment
        Class charClass = new Class("Druid");
        chara.setClass(charClass);

        if(charClass.isSpellcaster()) {
            chara.updateSpellModifiers();
        }

        // Print data
        System.out.println(chara);
        charClass.printClassFeatures(level);
    }
}
