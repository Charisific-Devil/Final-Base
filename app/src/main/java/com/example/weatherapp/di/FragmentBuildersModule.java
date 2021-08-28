package com.example.weatherapp.di;


import com.example.weatherapp.ui.settings.SettingsFragment;
import com.example.weatherapp.ui.today.TodayFragment;
import com.example.weatherapp.ui.weekly.WeeklyFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract TodayFragment contributeTodayFragment();

    @ContributesAndroidInjector
    abstract WeeklyFragment contributeWeeklyFragment();

    @ContributesAndroidInjector
    abstract SettingsFragment contributeSettingsFragment();
}
