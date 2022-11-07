package com.team.vinylos;

import androidx.test.InstrumentationRegistry;
import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.runner.AndroidJUnit4;

import com.team.vinylos.R;
import com.team.vinylos.ui.MainActivity;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Test;
import org.junit.Rule;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasBackground;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.Matchers.allOf;
import static org.junit.Assert.assertEquals;

import android.content.Context;
import android.os.SystemClock;
import android.view.View;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class Test1Collectors {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityTestRule = new ActivityScenarioRule<>(MainActivity.class);

    @Before
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("com.team.vinylos", appContext.getPackageName());
    }

    @Test
    public void mainActivityTest2() {
        ViewInteraction skipBtn = onView(withText("Vinilos, Tu app de música favorita")).check(matches(isDisplayed()));

        ViewInteraction toolBar = onView(allOf(withId(R.id.bottom_navigation),withId(R.id.bottom_navigation),isDisplayed()));

        ViewInteraction menu1 = onView(withId(R.id.collectors));
        menu1.perform(click());

        SystemClock.sleep(1500);

        //validar que muestre la palabra genero de cada coleccionista mostrado
        ViewInteraction Email = onView(allOf(withText("Email")));

        //validar que muestre la palabra Telefono de cada coleccionista mostrado
        ViewInteraction telefono = onView(allOf(withText("Telefono")));

        //validar Vista de Coleccionistas
        ViewInteraction textViews = onView(allOf(withId(R.id.textview_first))).check(matches(isDisplayed()));

        //validar Vista de imagenes de contacto
        //ViewInteraction images = onView(allOf(withId(R.id.cover)));
        onView(withIndex(withId(R.id.title1), 1)).perform(click());
        onView(allOf(withId(R.id.title1), hasBackground(R.drawable.circle)));


        //validar Nombre de un coleccionista
        ViewInteraction album = onView(withId(R.id.textViewName));
        onView(withIndex(withId(R.id.textViewName), 0)).check(matches(withText("Manolo Bellon")));

    }

    public static Matcher<View> withIndex(final Matcher<View> matcher, final int index) {
        return new TypeSafeMatcher<View>() {
            int currentIndex = 0;

            @Override
            public void describeTo(Description description) {
                description.appendText("with index: ");
                description.appendValue(index);
                matcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                return matcher.matches(view) && currentIndex++ == index;
            }
        };
    }

}

