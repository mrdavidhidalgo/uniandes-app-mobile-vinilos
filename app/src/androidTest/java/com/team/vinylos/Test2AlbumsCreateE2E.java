package com.team.vinylos;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasBackground;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.allOf;
import static org.junit.Assert.assertEquals;

import android.content.Context;
import android.os.SystemClock;
import android.view.View;

import androidx.test.InstrumentationRegistry;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
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
public class Test2AlbumsCreateE2E {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityTestRule = new ActivityScenarioRule<>(MainActivity.class);

    @Before
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("com.team.vinylos", appContext.getPackageName());

        ViewInteraction skipBtn = onView(withText("Vinilos, Tu app de música favorita")).check(matches(isDisplayed()));

        ViewInteraction toolBar = onView(allOf(withId(R.id.bottom_navigation),withId(R.id.bottom_navigation),isDisplayed()));

        ViewInteraction menu1 = onView(withId(R.id.albums));
        menu1.perform(click());

        SystemClock.sleep(1500);

        //ViewInteraction album1 = onView(withText("Poeta del pueblo")).check(matches(isDisplayed()));

        //validar que muestre la palabra genero de cada album mostrado
        ViewInteraction genero = onView(allOf(withText("Género")));

        //validar Vista de Albumes
        ViewInteraction textViews = onView(allOf(withId(R.id.textview_first))).check(matches(isDisplayed()));

        //validar Vista de imagenes
        //ViewInteraction images = onView(allOf(withId(R.id.cover)));
        onView(withIndex(withId(R.id.cover), 1)).perform(click());
        onView(allOf(withId(R.id.cover), hasBackground(R.drawable.ic_launcher_background)));
    }

    //Test Positivo Creacion de Album
    @Test
    public void mainActivityTest2() {
         //validar Nombre de un album de imagenes
        ViewInteraction album = onView(withId(R.id.textViewName));
        //onView(withIndex(withId(R.id.textViewName), 0)).check(matches(withText("Buscando América")));

        //Desplegar pantalla para creación de Album
        ViewInteraction album2 = onView(withId(R.id.createAlbumButton));
        album2.perform(click());
        SystemClock.sleep(1500);

        onView(withId(R.id.albumName)).perform(typeText("Album de Prueba"));
        onView(withId(R.id.albumDescription)).perform(typeText("EL primer album del artista"));
        onView(withId(R.id.albumGenre)).perform(click());
        onData(allOf(is(instanceOf(String.class)),is("Salsa"))).perform(click());
        onView(withId(R.id.albumGenre)).check(matches(withSpinnerText(containsString("Salsa"))));
        onView(withId(R.id.albumRecordLabel)).perform(click());
        onData(allOf(is(instanceOf(String.class)),is("EMI"))).perform(click());
        onView(withId(R.id.albumRecordLabel)).check(matches(withSpinnerText(containsString("EMI"))));
        onView(withId(R.id.albumCover)).perform(typeText("https://cdn.pixabay.com/photo/2019/12/18/04/11/dj-4702977_960_720.jpg"));

        //onView(withId(R.id.albumReleaseDate)).perform(typeText("10/10/2021"));

        onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard());
        //onView(withId(R.id.albumReleaseDate)).perform(click());
        SystemClock.sleep(1000);
        onView(withId(R.id.albumReleaseDate)).perform(typeText("2015/10/01"));



        onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.createAlbumButton)).perform(scrollTo(), click());
        //onView(withId(R.id.createAlbumButton)).perform(click());
        //onView(withText("CREAR")).perform(click());

        SystemClock.sleep(3000);
        ViewInteraction menu1 = onView(withId(R.id.albums));
        menu1.perform(click());

        SystemClock.sleep(1500);
        //textViewName
        ViewInteraction AlbumNuevo = onView(allOf(withText("Album de Prueba")));
        //ViewInteraction Album = onView(withText("Album de Prueba")).check(matches(isDisplayed()));
        SystemClock.sleep(15000);
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

