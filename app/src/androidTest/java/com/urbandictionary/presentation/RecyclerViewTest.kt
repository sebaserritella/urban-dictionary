package com.urbandictionary.presentation

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.PerformException
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.urbandictionary.R
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class RecyclerViewTest {
    private val ITEM_BELOW_THE_FOLD = 40

    /**
     * Use [ActivityScenario] to create and launch the activity under test. This is a
     * replacement for [androidx.test.rule.ActivityTestRule].
     */
    @Rule
    var activityScenarioRule = ActivityScenarioRule(
        MainActivity::class.java
    )

    @Test(expected = PerformException::class)
    fun itemWithText_doesNotExist() {
        // Attempt to scroll to an item that contains the special text.
        Espresso.onView(ViewMatchers.withId(R.id.urbanDictionaryList)) // scrollTo will fail the test if no item matches.
            .perform(
                RecyclerViewActions.scrollTo<RecyclerView.ViewHolder>(
                    ViewMatchers.hasDescendant(ViewMatchers.withText("not in the list"))
                )
            )
    }

    @Test
    fun scrollToItemBelowFold_checkItsText() {
        // First scroll to the position that needs to be matched and click on it.
        Espresso.onView(ViewMatchers.withId(R.id.urbanDictionaryList))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    ITEM_BELOW_THE_FOLD,
                    ViewActions.click()
                )
            )

        // Match the text in an item below the fold and check that it's displayed.
        val itemElementText =
            ApplicationProvider.getApplicationContext<Context>().resources.getString(
                R.string.label
            ) + ITEM_BELOW_THE_FOLD.toString()
        Espresso.onView(ViewMatchers.withText(itemElementText))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun itemInMiddleOfList_hasSpecialText() {
        // First, scroll to the view holder using the isInTheMiddle matcher.
        Espresso.onView(ViewMatchers.withId(R.id.urbanDictionaryList))
            .perform(RecyclerViewActions.scrollToHolder(isInTheMiddle()))

        // Check that the item has the special text.
        val middleElementText =
            ApplicationProvider.getApplicationContext<Context>().resources.getString(R.string.app_name)
        Espresso.onView(ViewMatchers.withText(middleElementText))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    /**
     * Matches the [RecyclerView.ViewHolder]s in the middle of the list.
     */
    private fun isInTheMiddle(): TypeSafeMatcher<RecyclerView.ViewHolder?> {
        return object : TypeSafeMatcher<RecyclerView.ViewHolder?>() {
            override fun matchesSafely(customHolder: RecyclerView.ViewHolder?): Boolean {
                return false
            }

            override fun describeTo(description: Description) {
                description.appendText("item in the middle")
            }
        }
    }
}