package com.example.admin.testlogin;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetSequence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements rAdapterInterface {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private LinearLayout searchBar;
    private ArrayList<ListItem> listItems;
    private ImageView searchMenuButton, searchMic, searchButton;
    private FloatingActionButton floatingAddButton;
    private final static String TAG = "MainActivity";
    private int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchBar = findViewById(R.id.searchBar);

        searchButton = findViewById(R.id.searchButton);

        searchMenuButton = findViewById(R.id.searchMenuButton);
        searchMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup(view);
            }
        });

        floatingAddButton = findViewById(R.id.floatingAddButton);
        floatingAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToList();
            }
        });

        searchMic = findViewById(R.id.searchMic);
        searchMic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, R.string.feature_not_available, Toast.LENGTH_SHORT).show();
            }
        });

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listItems = new ArrayList<>();

        adapter = new rAdapter(listItems, this, this);
        recyclerView.setAdapter(adapter);

        explainUI();
    }

    private void explainUI() {
        final List<View> targetViewList = Arrays.asList(floatingAddButton, searchBar, searchButton, searchMenuButton, searchMic);

        final TapTargetSequence tapTargetSequence = new TapTargetSequence(this)
                .targets(TapTarget.forView(targetViewList.get(0), "Add a row!", "Just Click Me to add a row, it's that simple!").id(1).tintTarget(false).outerCircleAlpha(1.0f).outerCircleColor(R.color.colorBurgerKingYellow),
                        TapTarget.forView(targetViewList.get(1), "Search anything!", "Find what you are lookin' for...").id(2).tintTarget(false).targetCircleColor(R.color.colorPrimaryDark).outerCircleAlpha(1.0f).outerCircleColor(R.color.colorPrimaryDark),
                        TapTarget.forView(targetViewList.get(2), "Tap Me", "Look closer...").id(3).tintTarget(false).outerCircleAlpha(1.0f).outerCircleColor(R.color.colorTwitterBlue),
                        TapTarget.forView(targetViewList.get(3), "Click", "And get more Options.").id(4).tintTarget(false).outerCircleAlpha(1.0f).outerCircleColor(R.color.colorFacebookBlue),
                        TapTarget.forView(targetViewList.get(4), "Say what you want!", "Just tap me...").id(5).tintTarget(false).outerCircleAlpha(1.0f).outerCircleColor(R.color.colorAccent))
                .considerOuterCircleCanceled(true)
                .continueOnCancel(true)
                .listener(new TapTargetSequence.Listener() {
                    @Override
                    public void onSequenceFinish() {
                        targetViewList.get(0).performClick();
                    }

                    @Override
                    public void onSequenceStep(TapTarget lastTarget, boolean targetClicked) {
                        int lastTargetId = lastTarget.id();
                        int currentTargetId = lastTargetId + 1;
                        int targetListSize = targetViewList.size();

                        if (currentTargetId != 2 && lastTargetId != targetListSize) {
                            targetViewList.get(lastTargetId).performClick();
                        }
                    }

                    @Override
                    public void onSequenceCanceled(TapTarget lastTarget) {
                    }
                });
        tapTargetSequence.start();
    }

    private void showPopup(View view) {
        PopupMenu popupMenu = new PopupMenu(MainActivity.this, view);
        Menu menu = popupMenu.getMenu();
        popupMenu.getMenuInflater().inflate(R.menu.search_bar_menu, menu);

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.menuSettings:
                        Toast.makeText(MainActivity.this, "You Clicked : " + item.getTitle(), Toast.LENGTH_SHORT).show();
                        goToSettings();
                        return true;

                    case R.id.menuLogin:
                        Toast.makeText(MainActivity.this, "You Clicked : " + item.getTitle(), Toast.LENGTH_SHORT).show();
                        goToLogin();
                        return true;

                    case R.id.menuRemove:
                        Toast.makeText(MainActivity.this, "You Clicked : " + item.getTitle(), Toast.LENGTH_SHORT).show();
                        removeLast();
                        return true;

                    case R.id.menuClear:
                        Toast.makeText(MainActivity.this, "You Clicked : " + item.getTitle(), Toast.LENGTH_SHORT).show();
                        clearList();
                        return true;

                    case R.id.menuGoAgain:
                        explainUI();
                        return true;

                    default:
                        Toast.makeText(MainActivity.this, "You Clicked : " + item.getTitle(), Toast.LENGTH_SHORT).show();
                        return true;
                }
            }
        });
        popupMenu.show();
    }

    private void addToList() {
        ListItem listItem = new ListItem(
                "Item: " + (i + 1),
                "Information of item: " + (i + 1)
        );
        listItems.add(listItem);
        recyclerView.setAdapter(adapter);
        i = i + 1;
    }

    private void clearList() {
        listItems.clear();
        recyclerView.setAdapter(adapter);
        i = 0;
    }

    private void removeLast() {
        if (i != 0) {
            listItems.remove(i - 1);
            recyclerView.setAdapter(adapter);
            i = i - 1;
        } else {
            Toast.makeText(MainActivity.this, "Welp!", Toast.LENGTH_SHORT).show();
        }
    }

    private void goToLogin() {
        Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(loginIntent);
    }

    private void goToSettings() {
        Intent settingsIntent = new Intent(MainActivity.this, SettingsActivity.class);
        startActivity(settingsIntent);
    }

    @Override
    public void scrollToPosition(int position) {
//        recyclerView.smoothScrollToPosition(position);
            recyclerView.stopScroll();
//        recyclerView.scrollToPosition(position);
    }
}
