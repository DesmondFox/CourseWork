package com.github.desmondfox.coursework.entities;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.json.JSONException;
import org.json.JSONObject;

public class Phone extends AbstractProcessable{

    private String os;
    private String soc;
    private boolean nfc;
    private String battery;
    private String display;
    private String frontalCam;
    private String mainCam;
    private String ram;
    private String features;
    private String img_url;

    public Phone(JSONObject obj) throws JSONException {
        name = obj.getString("name");
        description = obj.getString("descr");
        id = obj.getInt("id");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Phone phone = (Phone) o;

        return new EqualsBuilder()
                .append(name, phone.name)
                .append(description, phone.description)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(name)
                .append(description)
                .toHashCode();
    }

    public String getOs() {
        return os;
    }

    public String getSoc() {
        return soc;
    }

    public boolean isNfc() {
        return nfc;
    }

    public String getBattery() {
        return battery;
    }

    public String getDisplay() {
        return display;
    }

    public String getFrontalCam() {
        return frontalCam;
    }

    public String getMainCam() {
        return mainCam;
    }

    public String getRam() {
        return ram;
    }

    public String getFeatures() {
        return features;
    }

    public String getImg_url() {
        return img_url;
    }
}
