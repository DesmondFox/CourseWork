package com.github.desmondfox.coursework.proc;

import android.util.Log;

import com.github.desmondfox.coursework.entities.Phone;
import com.github.desmondfox.coursework.entities.Question;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class ItemParser {
    public static List<Phone> parsePhones(String raw)  {
        List<Phone> phones = new ArrayList<>();
        try {
            JSONArray arr = new JSONArray(raw);
            for (int i = 0; i < arr.length(); i++)
                phones.add(new Phone(arr.getJSONObject(i)));
        } catch (JSONException e) {
            Log.w("Item parser", e.getMessage());
        }
        return phones;
    }

    public static List<Question> parseQuestions(String raw)  {
        List<Question> questions = new ArrayList<>();
        try {
            JSONArray arr = new JSONArray(raw);
            for (int i = 0; i < arr.length(); i++)
                questions.add(new Question(arr.getJSONObject(i)));
        } catch (JSONException e) {
            Log.e("Item parser", e.getMessage());
        }
        return questions;
    }
//
//    private static <T extends AbstractProcessable> List<T> getSmth(String raw) {
//        List<T> phones = new ArrayList<>();
//        try {
//            JSONArray arr = new JSONArray(raw);
//            for (int i = 0; i < arr.length(); i++)
//                phones.add(new T(arr.getJSONObject(i)));
//        } catch (JSONException e) {
//            Log.w("Item parser", e.getMessage());
//        }
//        return phones;
//    }
}
