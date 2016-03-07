package com.greatschool.android;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.greatschool.android.model.School;
import com.greatschool.android.util.FileUtils;
import com.greatschool.android.util.GsonLocator;

public class Application extends android.app.Application {

    private School[] mSchools;

    private static Application instance = null;

    public static Application getInstance() {
        return instance;
    }

    public School[] getSchools() {
        return mSchools;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        Gson sGson = GsonLocator.get();

        String json = FileUtils.getFromAssets("school.json", this);
        mSchools = sGson.fromJson(json, new TypeToken<School[]>() {
        }.getType());
    }


}
