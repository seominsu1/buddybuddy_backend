package com.buddy.api.domain;

import java.util.Arrays;

public enum LicenseLevel {
    NOTHING("none"),
    AIDAONE("AIDA1"),
    AIDATWO("AIDA2"),
    AIDATHREE("AIDA3"),
    AIDAFOUR("AIDA4");

    private String name;

    LicenseLevel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Boolean isExist(String level) {
        return Arrays.stream(LicenseLevel.values())
                .anyMatch(license -> license.name.equals(level));
    }
}
