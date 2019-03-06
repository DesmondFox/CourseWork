package com.github.desmondfox.coursework.entities;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.json.JSONException;
import org.json.JSONObject;

public class Question extends AbstractProcessable {
    @Nullable private Integer id_yes;
    @Nullable private Integer id_no;
    @Nullable private Integer phone_yes_id;
    @Nullable private Integer phone_no_id;

    public Question(JSONObject obj) throws JSONException {
        name    = obj.getString("name");
        description = obj.getString("descr");
        id      = obj.getInt("id");

        id_yes  = obj.isNull("yes_id") ? null : obj.getInt("yes_id");
        id_no   = obj.isNull("no_id") ? null : obj.getInt("no_id");
        phone_yes_id    = obj.isNull("phone_yes") ? null : obj.getInt("phone_yes");
        phone_no_id     = obj.isNull("phone_no") ? null : obj.getInt("phone_no");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Question question = (Question) o;

        return new org.apache.commons.lang3.builder.EqualsBuilder()
                .append(name, question.name)
                .append(description, question.description)
                .append(id, question.id)
                .append(id_yes, question.id_yes)
                .append(id_no, question.id_no)
                .append(phone_yes_id, question.phone_yes_id)
                .append(phone_no_id, question.phone_no_id)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new org.apache.commons.lang3.builder.HashCodeBuilder(17, 37)
                .append(name)
                .append(description)
                .append(id)
                .append(id_yes)
                .append(id_no)
                .append(phone_yes_id)
                .append(phone_no_id)
                .toHashCode();
    }

    @Nullable
    public Integer getId_yes() {
        return id_yes;
    }

    @Nullable
    public Integer getId_no() {
        return id_no;
    }

    @Nullable
    public Integer getPhone_yes_id() {
        return phone_yes_id;
    }

    @Nullable
    public Integer getPhone_no_id() {
        return phone_no_id;
    }


}
