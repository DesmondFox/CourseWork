package com.github.desmondfox.coursework.entities;

public enum JsonPhoneSpecsFields {
    NFC("NFC"),
    NO_EXIST("-"),
    OS("OS"),
    SOC("SoC"),
    BATT("AKB"),
    DISPLAY("display"),
    FRONTAL("frontal_cam"),
    MAIN("main_cam"),
    RAM("RAM"),
    ROM("ROM"),
    FEATURES("others"),
    IMG("img");

    private String text;

    JsonPhoneSpecsFields(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
