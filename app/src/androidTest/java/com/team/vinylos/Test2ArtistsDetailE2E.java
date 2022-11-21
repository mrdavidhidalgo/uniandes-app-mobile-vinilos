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
public class Test2ArtistsDetailE2E {

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

        ViewInteraction menu1 = onView(withId(R.id.artists));
        menu1.perform(click());


        SystemClock.sleep(1500);

        //validar que muestre la palabra Nacimeinto de cada artista mostrado
        ViewInteraction Email = onView(allOf(withText("Nacimiento")));

       //validar Vista de artistas
        ViewInteraction textViews = onView(allOf(withId(R.id.textview_first))).check(matches(isDisplayed()));

        //validar Vista de imagenes de artista
        //ViewInteraction images = onView(allOf(withId(R.id.cover)));
        //onView(withIndex(withId(R.id.image), 0)).perform(click());
        onView(allOf(withId(R.id.image), hasBackground(R.drawable.ic_launcher_background)));


        //validar Nombre de un artista
        ViewInteraction album = onView(withId(R.id.textViewName));
        onView(withIndex(withId(R.id.textViewName), 0)).check(matches(withText(startsWith("Rubén Blades"))));

        //Ir a detalle de Artista
        onView(withIndex(withId(R.id.textViewName), 0)).perform(click());
        SystemClock.sleep(1500);
        ViewInteraction originview = onView(allOf(withId(R.id.textView12))).check(matches(isDisplayed()));
        onView(withIndex(withId(R.id.textView12), 0)).check(matches(withText(startsWith("Origen"))));
        ViewInteraction Descview = onView(allOf(withId(R.id.textView8))).check(matches(isDisplayed()));
        onView(withIndex(withId(R.id.textView8), 0)).check(matches(withText(startsWith("Descripcion"))));
        SystemClock.sleep(1500);

        //Volver a lista de artistas
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

