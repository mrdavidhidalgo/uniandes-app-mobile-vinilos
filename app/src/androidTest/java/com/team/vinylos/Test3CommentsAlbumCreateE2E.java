package com.team.vinylos;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isDialog;
import static androidx.test.espresso.matcher.ViewMatchers.hasBackground;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.startsWith;
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
public class Test3CommentsAlbumCreateE2E {

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

        SystemClock.sleep(2500);

        //ViewInteraction album1 = onView(withText("Poeta del pueblo")).check(matches(isDisplayed()));

        //validar que muestre la palabra genero de cada album mostrado
        ViewInteraction genero = onView(allOf(withText("Género")));

        //validar Vista de Albumes
        ViewInteraction textViews = onView(allOf(withId(R.id.textview_first))).check(matches(isDisplayed()));

        //validar Vista de imagenes
        //ViewInteraction images = onView(allOf(withId(R.id.cover)));

        onView(allOf(withId(R.id.cover), hasBackground(R.drawable.ic_launcher_background)));
        onView(withIndex(withId(R.id.cover), 1)).perform(click());
        SystemClock.sleep(1500);
    }

    //Test Positivo Creacion de COmentario de album
    @Test
    public void createAlbumCommentTest() {
        //validar Datos de Album
        ViewInteraction textViews = onView(allOf(withId(R.id.imageAlbum))).check(matches(isDisplayed()));
        ViewInteraction textViews2 = onView(allOf(withId(R.id.textViewEmailLabel))).check(matches(isDisplayed()));
        ViewInteraction textViews3 = onView(allOf(withId(R.id.textViewTelephone))).check(matches(isDisplayed()));

        //Desplegar pantalla para creación de comentario
        onView(withId(R.id.commentButton)).perform(scrollTo(), click());

        SystemClock.sleep(1500);


        //ingresar los valores
        onView(withId(R.id.commentRating)).perform(typeText("5"));
        onView(withId(R.id.commentDescription)).perform(typeText("El album cuenta con temas ineditos y de estudio"));

        //Dar en Guardar
        onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.createAlbumCommentButton)).perform(scrollTo(), click());

        SystemClock.sleep(2000);
        //validar que muestre el mensaje de exito
        ViewInteraction error = onView(allOf(withText(startsWith("Se ha registrado el comentario del album"))));
        SystemClock.sleep(5000);
    }

    //Test Negativo Creacion de COmentario de album. El usuario Cancela operación
    @Test
    public void cancelAlbumCommentTest() {
        //validar Datos de Album
        ViewInteraction textViews = onView(allOf(withId(R.id.imageAlbum))).check(matches(isDisplayed()));
        ViewInteraction textViews2 = onView(allOf(withId(R.id.textViewEmailLabel))).check(matches(isDisplayed()));
        ViewInteraction textViews3 = onView(allOf(withId(R.id.textViewTelephone))).check(matches(isDisplayed()));

        //Desplegar pantalla para creación de comentario
        onView(withId(R.id.commentButton)).perform(scrollTo(), click());

        SystemClock.sleep(1500);


        //ingresar los valores
        onView(withId(R.id.commentRating)).perform(typeText("5"));
        onView(withId(R.id.commentDescription)).perform(typeText("El album cuenta con temas ineditos y de estudio"));

        //Dar en Guardar
        onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.canceCreateAlbumCommentButton)).perform(scrollTo(), click());
        SystemClock.sleep(1500);

        //validar que muestre el cuadro de dialogo
        //ViewInteraction error = onView(allOf(withText(startsWith("Quieres cancelar la creación del comentario"))));
        ViewInteraction dialog = onView(withText(startsWith("Quieres cancelar la creación del comentario")))
                .inRoot(isDialog())
                .check(matches(isDisplayed()));
        onView(withText("SI")).perform(click());
        SystemClock.sleep(2000);

        //validar que vuelva a la lista de albumes
        //validar que muestre la palabra genero de cada album mostrado
        ViewInteraction genero = onView(allOf(withText("Género")));

        //validar Vista de Albumes
        ViewInteraction textViews6 = onView(allOf(withId(R.id.textview_first))).check(matches(isDisplayed()));

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

