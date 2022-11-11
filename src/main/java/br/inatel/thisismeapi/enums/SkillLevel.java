package br.inatel.thisismeapi.enums;

public enum SkillLevel {

    BEGINNER_1("Beginner 1", 1, 0, 13),
    BEGINNER_2("Beginner 2", 2, 12, 39),
    BEGINNER_3("Beginner 3", 3, 40, 78),
    BEGINNER_4("Beginner 4", 4, 79, 130),
    BEGINNER_5("Beginner 5", 5, 131, 195),
    BEGINNER_6("Beginner 6", 6, 196, 273),

    INTERMEDIATE_1("Intermediate 1", 7, 274, 364),
    INTERMEDIATE_2("Intermediate 2", 8, 365, 468),
    INTERMEDIATE_3("Intermediate 3", 9, 469, 585),
    INTERMEDIATE_4("Intermediate 4", 10, 586, 715),
    INTERMEDIATE_5("Intermediate 5", 11, 716, 858),


    ADVANCED_1("Advanced 1", 12, 859, 1034),
    ADVANCED_2("Advanced 2", 13, 1035, 1227),
    ADVANCED_3("Advanced 3", 14, 1228, 1438),
    ADVANCED_4("Advanced 4", 15, 1439, 1665),

    ESPECIALIST_1("Especialist 1", 16, 1666, 1948),
    ESPECIALIST_2("Especialist 2", 17, 1949, 2256),
    ESPECIALIST_3("Especialist 3", 18, 2257, 2589),

    EXPERT_1("Expert 1", 19, 2590, 3063),
    EXPERT_2("Expert 2", 20, 3064, 3589),

    LEGENDARY("Legendary", 21, 3590, 999999999);

    private String levelName;

    private Integer level;

    private Integer minXP;

    private Integer maxXP;


    SkillLevel(String levelName, Integer level, Integer minXP, Integer maxXP) {
        this.levelName = levelName;
        this.level = level;
        this.minXP = minXP;
        this.maxXP = maxXP;
    }

    public String getLevelName() {
        return levelName;
    }

    public Integer getLevel() {
        return level;
    }

    public Integer getMinXP() {
        return minXP;
    }

    public Integer getMaxXP() {
        return maxXP;
    }

    public static SkillLevel getSkillLevelByXP(Integer xp) {
        for (SkillLevel skillLevel : SkillLevel.values()) {
            if (xp >= skillLevel.getMinXP() && xp <= skillLevel.getMaxXP()) {
                return skillLevel;
            }
        }
        return null;
    }

    public static SkillLevel getSkillLevelByLevel(Integer level) {
        for (SkillLevel skillLevel : SkillLevel.values()) {
            if (skillLevel.getLevel().equals(level)) {
                return skillLevel;
            }
        }
        return null;
    }

    public static SkillLevel getSkillLevelByLevelName(String levelName) {
        for (SkillLevel skillLevel : SkillLevel.values()) {
            if (skillLevel.getLevelName().equals(levelName)) {
                return skillLevel;
            }
        }
        return null;
    }

    public static String getLevelNameByXP(Integer xp) {
        for (SkillLevel skillLevel : SkillLevel.values()) {
            if (xp >= skillLevel.getMinXP() && xp <= skillLevel.getMaxXP()) {
                return skillLevel.getLevelName();
            }
        }
        return null;
    }

    public static Integer getLevelByXP(Integer xp) {
        for (SkillLevel skillLevel : SkillLevel.values()) {
            if (xp >= skillLevel.getMinXP() && xp <= skillLevel.getMaxXP()) {
                return skillLevel.getLevel();
            }
        }
        return null;
    }

    public static Integer getLevelByLevelName(String levelName) {
        for (SkillLevel skillLevel : SkillLevel.values()) {
            if (skillLevel.getLevelName().equals(levelName)) {
                return skillLevel.getLevel();
            }
        }
        return null;
    }
}
