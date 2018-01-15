package com.zve.redditassessment.app;

import android.app.Application;

import com.zve.redditassessment.di.AppComponent;
import com.zve.redditassessment.di.AppModule;
import com.zve.redditassessment.di.DaggerAppComponent;

import hugo.weaving.DebugLog;

public class RedditApplication extends Application {
    private AppComponent appComponent;
    private static RedditApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        appComponent = initDagger(this);
    }

    public static RedditApplication getInstance(){
        return instance;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    @DebugLog
    protected AppComponent initDagger(RedditApplication application) {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(application))
                .build();
    }
}
