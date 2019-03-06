package com.github.desmondfox.coursework.entities;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Phone extends AbstractProcessable{
    private static String NO_EXIST = "-";

    private Map<JsonPhoneSpecsFields, String> values
            = new LinkedHashMap<>();

    static final List<JsonPhoneSpecsFields> fields
            = Arrays.asList(JsonPhoneSpecsFields.class.getEnumConstants());

    public Phone(JSONObject obj) throws JSONException {
        name = obj.getString("name");
        description = obj.getString("descr");
        id = obj.getInt("id");

        for (JsonPhoneSpecsFields val : fields) {
            values.put(val, obj.isNull(val.toString()) ?
                    NO_EXIST : obj.getString(val.toString()));
        }
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
        return values.get(JsonPhoneSpecsFields.OS);
    }

    public String getSoc() {
        return values.get(JsonPhoneSpecsFields.SOC);
    }

    public String getNfc() {
        return values.get(JsonPhoneSpecsFields.NFC).equals("true") ? "+" : NO_EXIST;
    }

    public String getBattery() {
        return values.get(JsonPhoneSpecsFields.BATT);
    }

    public String getDisplay() {
        return values.get(JsonPhoneSpecsFields.DISPLAY);
    }

    public String getFrontalCam() {
        return values.get(JsonPhoneSpecsFields.FRONTAL);
    }

    public String getMainCam() {
        return values.get(JsonPhoneSpecsFields.MAIN);
    }

    public String getRam() {
        return values.get(JsonPhoneSpecsFields.RAM);
    }

    public String getRom() {
        return values.get(JsonPhoneSpecsFields.ROM);
    }

    public String getFeatures() {
        return values.get(JsonPhoneSpecsFields.FEATURES);
    }

    public String getImgUrl() {
        return values.get(JsonPhoneSpecsFields.IMG);
    }
}
