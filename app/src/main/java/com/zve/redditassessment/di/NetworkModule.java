package com.zve.redditassessment.di;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zve.redditassessment.api.DateTimeDeserializer;
import com.zve.redditassessment.api.RedditObjectDeserializer;
import com.zve.redditassessment.api.RedditService;
import com.zve.redditassessment.app.ApplicationConstants;
import com.zve.redditassessment.models.RedditObject;

import org.joda.time.DateTime;
import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Peter on 11.01.2018.
 */

@Module
public class NetworkModule {
    private static final String NAME_BASE_URL = "NAME_BASE_URL";

    @Provides
    @Named(NAME_BASE_URL)
    String provideBaseUrlString() {
        return ApplicationConstants.INSTANCE.getBASE_URL();
    }

    @Provides
    @Singleton
    Gson providesGsonConverter() {
        return new GsonBuilder()
                .registerTypeAdapter(RedditObject.class, new RedditObjectDeserializer())
                .registerTypeAdapter(DateTime.class, new DateTimeDeserializer())
                .create();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, @Named(NAME_BASE_URL) String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    @Provides
    @Singleton
    RedditService provideRedditService(Retrofit retrofit) {
        return retrofit.create(RedditService.class);
    }
}
