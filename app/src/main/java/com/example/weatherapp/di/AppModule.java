package com.example.weatherapp.di;

import android.app.Application;

import androidx.room.Room;


import com.example.weatherapp.api.WeatherService;
import com.example.weatherapp.db.ForecastDao;
import com.example.weatherapp.db.WeatherDB;
import com.example.weatherapp.util.Constants;
import com.example.weatherapp.util.LiveDataCallAdapterFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = ViewModelModule.class)
class AppModule {
    @Singleton
    @Provides
    WeatherService provideWeatherService() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .client(httpClient.build())
                .build();

        return retrofit.create(WeatherService.class);
    }

    @Singleton @Provides
    WeatherDB provideDb(Application app) {
        return Room.databaseBuilder(app, WeatherDB.class,"weather.db")
                .fallbackToDestructiveMigration()
                .build();
    }

    @Singleton @Provides
    ForecastDao provideForecastDao(WeatherDB db) {
        return db.forecastDao();
    }
}
