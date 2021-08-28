package com.example.weatherapp.db;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.weatherapp.model.SavedDailyForecast;
import com.example.weatherapp.model.UviDb;


@Database(entities = {SavedDailyForecast.class, UviDb.class},
        version = 1,
        exportSchema = false)

public abstract class WeatherDB extends RoomDatabase {
    abstract public ForecastDao forecastDao();
}
