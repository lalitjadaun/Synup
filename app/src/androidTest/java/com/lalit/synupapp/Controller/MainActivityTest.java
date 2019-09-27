package com.lalit.synupapp.Controller;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.lalit.synupapp.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    private static final int ITEMS = 1;
    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void recycleTest() {
        // Espresso.onView(withId(R.id.recyclerView_list)).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));

//        RecyclerView recyclerView = mainActivityActivityTestRule.getActivity().findViewById(R.id.recyclerView_list);
//        int itemcount = recyclerView.getAdapter().getItemCount();
//        Espresso.onView(withId(R.id.recyclerView_list)).perform(RecyclerViewActions.scrollToPosition(itemcount - 1));


        Espresso.onView(ViewMatchers.withId(R.id.recyclerView_list)).perform(RecyclerViewActions.actionOnItemAtPosition(ITEMS, click()));
        String itemval = "1";
        Espresso.onView(withText(itemval)).check(matches(isDisplayed()));
    }
}