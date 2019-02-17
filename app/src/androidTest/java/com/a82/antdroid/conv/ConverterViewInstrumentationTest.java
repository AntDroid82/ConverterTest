package com.a82.antdroid.conv;

import android.support.test.runner.AndroidJUnit4;
import android.support.test.rule.ActivityTestRule;

import com.a82.antdroid.conv.view.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class ConverterViewInstrumentationTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void checkScreenElements() {
        onView(withId(R.id.spinner_from)).check(matches(isDisplayed()));
        onView(withId(R.id.spinner_to)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_calculate)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_calculate)).check(matches(isClickable()));
    }
}
