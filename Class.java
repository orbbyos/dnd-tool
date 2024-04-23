import java.util.ArrayList;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Class {
    private String className;

    private ArrayList<ClassFeature> classFeatures = new ArrayList<ClassFeature>();
    private int hitDieType;
    private int[] abilityScoreIncLevels;
    
    private int[] cantripsKnownPerLevel;

    private Ability spellAbility;
    private int[][] spellSlotsPerLevel;

    // Constructor - className must match the name of a DnD class, first letter capitalized (ex. "Paladin", "Cleric", etc.)
    // Assigns values to hit die type, ability score levels, cantrips per level, spellcasting ability, spell slots per level, and class features
    Class(String className) {
        this.className = className;
          
        // Set hit die type
        switch(className) {
            case "Sorcerer":
            case "Wizard":
                hitDieType = 6;
                break;
            case "Artificer":
            case "Bard":
            case "Cleric":
            case "Druid":
            case "Monk":
            case "Rogue":
            case "Warlock":
                hitDieType = 8;
                break;
            case "Blood Hunter":
            case "Fighter":
            case "Ranger":
            case "Paladin":
                hitDieType = 10;
                break;
            case "Barbarian":
                hitDieType = 12;
                break;
        }

        // Set levels of ability score increase
        switch(className) {
            case "Rogue":
                abilityScoreIncLevels = new int[]{4,8,10,12,16,19};
                break;
            case "Fighter":
                abilityScoreIncLevels = new int[]{4,6,8,12,14,16,19};
                break;
            default:
                abilityScoreIncLevels = new int[]{4,8,12,16,19};
                break;
        }

        // Set cantrips known
        switch(className) {
            case "Artificer":
                cantripsKnownPerLevel = new int[]{2,2,2,2,2,2,2,2,2,3,3,3,3,4,4,4,4,4,4,4};
                break;
            case "Bard":
                cantripsKnownPerLevel = new int[]{2,2,2,3,3,3,3,3,3,4,4,4,4,4,4,4,4,4,4,4};
                break;
            case "Cleric":
                cantripsKnownPerLevel = new int[]{3,3,3,4,4,4,4,4,4,5,5,5,5,5,5,5,5,5,5,5};
                break;
            case "Druid":
                cantripsKnownPerLevel = new int[]{2,2,2,3,3,3,3,3,3,4,4,4,4,4,4,4,4,4,4,4};
                break;
            case "Sorcerer":
                cantripsKnownPerLevel = new int[]{4,4,4,5,5,5,5,5,5,6,6,6,6,6,6,6,6,6,6,6};
                break;
            case "Warlock":
                cantripsKnownPerLevel = new int[]{2,2,2,3,3,3,3,3,3,4,4,4,4,4,4,4,4,4,4,4};
                break;
            case "Wizard":
                cantripsKnownPerLevel = new int[]{3,3,3,4,4,4,4,4,4,5,5,5,5,5,5,5,5,5,5,5};
                break;
            default:
                cantripsKnownPerLevel = new int[20];
                break;
        }

        // Set spell ability
        switch(className) {
            case "Druid":
            case "Cleric":
            case "Ranger":
                spellAbility = Ability.WISDOM;
                break;
            case "Artificer":
            case "Wizard":
                spellAbility = Ability.INTELLIGENCE;
                break;
            case "Paladin":
            case "Bard":
            case "Sorcerer":
            case "Warlock":
                spellAbility = Ability.CHARISMA;
                break;
            default:
                spellAbility = null;
                break;
        }

        // set spell slots
        switch(className) {
            case "Artificer":
            case "Paladin":
            case "Ranger":
                spellSlotsPerLevel = new int[][]{
                    {0,0,0,0,0,0,0,0,0},
                    {2,0,0,0,0,0,0,0,0},
                    {3,0,0,0,0,0,0,0,0},
                    {3,0,0,0,0,0,0,0,0},
                    {4,2,0,0,0,0,0,0,0},
                    {4,2,0,0,0,0,0,0,0},
                    {4,3,0,0,0,0,0,0,0},
                    {4,3,0,0,0,0,0,0,0},
                    {4,3,2,0,0,0,0,0,0},
                    {4,3,2,0,0,0,0,0,0},
                    {4,3,3,0,0,0,0,0,0},
                    {4,3,3,0,0,0,0,0,0},
                    {4,3,3,1,0,0,0,0,0},
                    {4,3,3,1,0,0,0,0,0},
                    {4,3,3,2,0,0,0,0,0},
                    {4,3,3,2,0,0,0,0,0},
                    {4,3,3,3,1,0,0,0,0},
                    {4,3,3,3,1,0,0,0,0},
                    {4,3,3,3,2,0,0,0,0},
                    {4,3,3,3,2,0,0,0,0}
                };

                if(className.equals("Artificer")) {
                    spellSlotsPerLevel[0][0] = 2;
                }

                break;
            case "Bard":
            case "Cleric":
            case "Druid":
            case "Sorcerer":
            case "Wizard":
                spellSlotsPerLevel = new int[][]{
                    {2,0,0,0,0,0,0,0,0},
                    {3,0,0,0,0,0,0,0,0},
                    {4,2,0,0,0,0,0,0,0},
                    {4,3,0,0,0,0,0,0,0},
                    {4,3,2,0,0,0,0,0,0},
                    {4,3,3,0,0,0,0,0,0},
                    {4,3,3,1,0,0,0,0,0},
                    {4,3,3,2,0,0,0,0,0},
                    {4,3,3,3,1,0,0,0,0},
                    {4,3,3,3,2,0,0,0,0},
                    {4,3,3,3,2,1,0,0,0},
                    {4,3,3,3,2,1,0,0,0},
                    {4,3,3,3,2,1,1,0,0},
                    {4,3,3,3,2,1,1,0,0},
                    {4,3,3,3,2,1,1,1,0},
                    {4,3,3,3,2,1,1,1,0},
                    {4,3,3,3,2,1,1,1,1},
                    {4,3,3,3,3,1,1,1,1},
                    {4,3,3,3,3,2,1,1,1},
                    {4,3,3,3,3,2,2,1,1}
                };
                break;
            default:
                spellSlotsPerLevel = new int[20][9];
                break;
        }

        // Read from respective class feature file to fill classFeatures
        try {
            readClassFeatures(classFeatures);
        }
        catch(Exception e) {
            System.out.println("File for this class's features not found.");
        }
    }


    // --- METHODS --- //
    public String getName() {
        return className;
    }

    public int getHitDieType() {
        return hitDieType;
    }

    public int[] getAbilityScoreIncLevels() {
        return abilityScoreIncLevels;
    }

    public boolean isSpellcaster() {
        return spellAbility != null;
    }

    public Ability getSpellAbility() {
        return (isSpellcaster()? spellAbility:null);
    }

    public int getSpellSlots(int atLevel, int spellLevel) {
        return spellSlotsPerLevel[atLevel-1][spellLevel-1];
    }

    public int getCantripsKnown(int atLevel) {
        return cantripsKnownPerLevel[atLevel-1];
    }

    // readClassFeatures - Assigns class features from ./class-features/[className].txt to the classFeatures ArrayList
    private void readClassFeatures(ArrayList<ClassFeature> arr) throws FileNotFoundException {
        File classFile = new File("class-features/" + className + ".txt");
        Scanner scanner = new Scanner(classFile);

        while(scanner.hasNextLine()) {
            String[] ft = scanner.nextLine().split("_");
            arr.add(new ClassFeature(ft[1], Integer.parseInt(ft[0])));
        }

        scanner.close();
    }

    // printClassFeatures - Prints each feature and their level, using playerLevel to mark which ones have been obtained
    public void printClassFeatures(int playerLevel) {
        System.out.println("-- Class Features --");
        for(ClassFeature f: classFeatures) {
            System.out.println("[" + (playerLevel >= f.getLevel()? 'X':' ') + "] " + f);
        }
    }
    
}

class ClassFeature {
    private String title;
    private int level;
    
    public ClassFeature(String title, int level) {
        this.title = title;
        this.level = level;
    }

    public String getTitle() {
        return title;
    }
    public int getLevel() {
        return level;
    }

    public String toString() {
        return "Level " + level + " - " + title;
    }
}
