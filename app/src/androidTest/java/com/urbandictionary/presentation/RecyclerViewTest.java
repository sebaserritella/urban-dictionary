package com.urbandictionary.presentation;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.PerformException;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.urbandictionary.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


/**
 * Test class showcasing some {@link RecyclerViewActions} from Espresso.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class RecyclerViewTest {

    private static final int ITEM_BELOW_THE_FOLD = 40;

    /**
     * Use {@link ActivityScenario} to create and launch the activity under test. This is a
     * replacement for {@link androidx.test.rule.ActivityTestRule}.
     */
    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule =
            new ActivityScenarioRule<MainActivity>(MainActivity.class);

    @Test(expected = PerformException.class)
    public void itemWithText_doesNotExist() {
        // Attempt to scroll to an item that contains the special text.
        onView(ViewMatchers.withId(R.id.urbanDictionaryList))
                // scrollTo will fail the test if no item matches.
                .perform(RecyclerViewActions.scrollTo(
                        hasDescendant(withText("not in the list"))
                ));
    }

    @Test
    public void scrollToItemBelowFold_checkItsText() {
        // First scroll to the position that needs to be matched and click on it.
        onView(ViewMatchers.withId(R.id.urbanDictionaryList))
                .perform(RecyclerViewActions.actionOnItemAtPosition(ITEM_BELOW_THE_FOLD, click()));

        // Match the text in an item below the fold and check that it's displayed.
        String itemElementText = getApplicationContext().getResources().getString(
                R.string.label) + String.valueOf(ITEM_BELOW_THE_FOLD);
        onView(withText(itemElementText)).check(matches(isDisplayed()));
    }

    @Test
    public void itemInMiddleOfList_hasSpecialText() {
        // First, scroll to the view holder using the isInTheMiddle matcher.
        onView(ViewMatchers.withId(R.id.urbanDictionaryList))
                .perform(RecyclerViewActions.scrollToHolder(isInTheMiddle()));

        // Check that the item has the special text.
        String middleElementText =
                getApplicationContext().getResources().getString(R.string.app_name);
        onView(withText(middleElementText)).check(matches(isDisplayed()));
    }

    /**
     * Matches the {@link RecyclerView.ViewHolder}s in the middle of the list.
     */
    private static Matcher<RecyclerView.ViewHolder> isInTheMiddle() {
        return new TypeSafeMatcher<RecyclerView.ViewHolder>() {
            @Override
            protected boolean matchesSafely(RecyclerView.ViewHolder customHolder) {
                return false;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("item in the middle");
            }
        };
    }
}
