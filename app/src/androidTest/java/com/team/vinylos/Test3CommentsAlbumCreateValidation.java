package com.team.vinylos;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isDialog;
import static androidx.test.espresso.matcher.ViewMatchers.hasBackground;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
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
public class Test3CommentsAlbumCreateValidation {

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

    //Escenario 1:entrar a la aplicacion, ver la lista de albumes, entrar al detalle de un album, entrar a dar un comentario, intentar crear un comentario con rating vacio y verificar que se muestra el mensaje de error
    @Test
    public void Escenario1() {

        //validar Datos de Album
        ViewInteraction textViews4 = onView(allOf(withId(R.id.imageAlbum))).check(matches(isDisplayed()));
        ViewInteraction textViews2 = onView(allOf(withId(R.id.textViewEmailLabel))).check(matches(isDisplayed()));
        ViewInteraction textViews3 = onView(allOf(withId(R.id.textViewTelephone))).check(matches(isDisplayed()));
        //Desplegar pantalla para creación de comentario
        onView(withId(R.id.commentButton)).perform(scrollTo(), click());

        SystemClock.sleep(1500);


        //ingresar los valores
        //onView(withId(R.id.commentRating)).perform(typeText("5"));
        onView(withId(R.id.commentDescription)).perform(typeText("El album cuenta con temas ineditos y de estudio"));

        //Dar en Guardar
        onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.createAlbumCommentButton)).perform(scrollTo(), click());

        SystemClock.sleep(2000);
        //validar que muestre el mensaje de exito
        ViewInteraction error = onView(allOf(withText(startsWith("Debes diligenciar la calificación a realizar"))));
        SystemClock.sleep(5000);
    }

    //Escenario 2: entrar a la aplicacion, ver la lista de albumes, entrar al detalle de un album, entrar a dar un comentario, intentar crear un comentario con rating maypr a 5 y verificar que se muestra el mensaje de error
    @Test
    public void Escenario2() {

        //validar Datos de Album
        ViewInteraction textViews4 = onView(allOf(withId(R.id.imageAlbum))).check(matches(isDisplayed()));
        ViewInteraction textViews2 = onView(allOf(withId(R.id.textViewEmailLabel))).check(matches(isDisplayed()));
        ViewInteraction textViews3 = onView(allOf(withId(R.id.textViewTelephone))).check(matches(isDisplayed()));
        //Desplegar pantalla para creación de comentario
        onView(withId(R.id.commentButton)).perform(scrollTo(), click());

        SystemClock.sleep(1500);


        //ingresar los valores
        onView(withId(R.id.commentRating)).perform(typeText("8"));
        onView(withId(R.id.commentDescription)).perform(typeText("El album cuenta con temas ineditos y de estudio"));

        //Dar en Guardar
        onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.createAlbumCommentButton)).perform(scrollTo(), click());

        SystemClock.sleep(2000);
        //validar que muestre el mensaje de exito
        ViewInteraction error = onView(allOf(withText(startsWith("La calificación debe estar entre 1 y 5"))));
        SystemClock.sleep(5000);
    }


    //Escenario 3: entrar a la aplicacion, ver la lista de albumes, entrar al detalle de un album, entrar a dar un comentario, intentar crear un comentario con descripcion vacia y verificar que se muestra el mensaje de error
@Test
public void Escenario3() {

    //validar Datos de Album
    ViewInteraction textViews4 = onView(allOf(withId(R.id.imageAlbum))).check(matches(isDisplayed()));
    ViewInteraction textViews2 = onView(allOf(withId(R.id.textViewEmailLabel))).check(matches(isDisplayed()));
    ViewInteraction textViews3 = onView(allOf(withId(R.id.textViewTelephone))).check(matches(isDisplayed()));
    //Desplegar pantalla para creación de comentario
    onView(withId(R.id.commentButton)).perform(scrollTo(), click());

    SystemClock.sleep(1500);


    //ingresar los valores
    onView(withId(R.id.commentRating)).perform(typeText("4"));
    onView(withId(R.id.commentDescription)).perform(typeText(""));

    //Dar en Guardar
    onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard());
    onView(withId(R.id.createAlbumCommentButton)).perform(scrollTo(), click());

    SystemClock.sleep(2000);
    //validar que muestre el mensaje de exito
    ViewInteraction error = onView(allOf(withText(startsWith("Debes diligenciar el comentario del"))));
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

