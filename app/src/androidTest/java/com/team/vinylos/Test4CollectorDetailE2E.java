package com.team.vinylos;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasBackground;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.assertEquals;

import android.content.Context;
import android.os.SystemClock;
import android.view.View;

import androidx.test.InstrumentationRegistry;
import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.runner.AndroidJUnit4;

import com.team.vinylos.ui.MainActivity;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class Test4CollectorDetailE2E {

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

        onView(withText("Vinilos, Tu app de música favorita")).check(matches(isDisplayed()));

        onView(allOf(withId(R.id.bottom_navigation),withId(R.id.bottom_navigation),isDisplayed()));

        ViewInteraction menu1 = onView(withId(R.id.collectors));
        menu1.perform(click());

        SystemClock.sleep(1500);

        //validar Vista de Coleccionistas
        onView(allOf(withId(R.id.textview_first))).check(matches(isDisplayed()));

        //validar Vista de imagenes de contacto
        onView(withIndex(withId(R.id.title1), 1)).perform(click());
        SystemClock.sleep(3000);

        //validar que muestre la palabra genero de cada coleccionista mostrado
        onView(withText("Email")).check(matches(isDisplayed()));

        onView(withText("Telefono")).check(matches(isDisplayed()));

        onView(withId(R.id.albumes_title)).check(matches(isDisplayed()));


        //validar Nombre de un coleccionista
        onView(withIndex(withId(R.id.textViewName), 0)).check(matches(isDisplayed()));

        //Volver a lista de coleccionista
        menu1.perform(click());
        SystemClock.sleep(1500);
        ViewInteraction textViews2 = onView(allOf(withId(R.id.textview_first))).check(matches(isDisplayed()));

        //Ir a albumes
        ViewInteraction menu2 = onView(withId(R.id.albums));
        menu2.perform(click());
        SystemClock.sleep(1500);
        //validar que muestre la palabra genero de cada album mostrado
        ViewInteraction genero = onView(allOf(withText("Género")));
        SystemClock.sleep(5000);

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

