import android.content.Intent;
import android.os.SystemClock;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.intent.Intents;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.rule.ActivityTestRule;


import com.acme.a3csci3130.MainActivity;
import com.acme.a3csci3130.R;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;


import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;

import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static android.support.test.espresso.matcher.ViewMatchers.hasErrorText;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.doesNotExist;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class CRUDTests {

    @Rule
    public ActivityTestRule<MainActivity> activityrule =
        new ActivityTestRule<>(MainActivity.class);
    
    @Test
    public void A_Create() {
        /**
         * Create test
         */
        onView(withId(R.id.submitButton)).perform(click());
        SystemClock.sleep(1500);
        
        onView(withId(R.id.name)).perform(typeText("Superman"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.address)).perform(typeText("123 fake street"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.submitButton)).perform(click());
        onView(isRoot()).perform(ViewActions.pressBack());
        SystemClock.sleep(1500);

        onView(withText("Superman")).check(matches(isDisplayed()));
    }

   @Test
    public void B_Read() {
        /**
         * Read test
         */
       onView(withText("Superman")).check(matches(isDisplayed()));
    }

   @Test
    public void C_Update() {
        /**
         * Update test
         */
       onView(withText("Superman")).perform(click());
       SystemClock.sleep(1500);
       onView(withId(R.id.name)).perform(typeText("Aquaman"));
       Espresso.closeSoftKeyboard();
       onView(withId(R.id.updateButton)).perform(click());
       onView(isRoot()).perform(ViewActions.pressBack());
       SystemClock.sleep(1500);
       onView(withText("Aquaman")).check(matches(isDisplayed()));
       
    }

    @Test
    public void D_Delete() {
        /**
         * Delete test
         */
        onView(withText("Aquaman")).perform(click());
        SystemClock.sleep(1500);
        onView(withId(R.id.deleteButton)).perform(click());
        onView(isRoot()).perform(ViewActions.pressBack());
        SystemClock.sleep(1500);
        onView(withText("Aquaman")).check(matches(doesNotExist()));
    }
            

}
