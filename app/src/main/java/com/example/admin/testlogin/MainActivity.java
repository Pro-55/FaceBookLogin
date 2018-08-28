package com.example.admin.testlogin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private ArrayList<ListItem> listItems;
    private ImageView searchMenuButton;
    private int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchMenuButton = findViewById(R.id.searchMenuButton);
        searchMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup(view);
            }
        });

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listItems = new ArrayList<>();

        adapter = new rAdapter(listItems, this);
        recyclerView.setAdapter(adapter);
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
                        return true;

                    case R.id.menuHelp:
                        Toast.makeText(MainActivity.this, "You Clicked : " + item.getTitle(), Toast.LENGTH_SHORT).show();
                        return true;

                    case R.id.menuAdd:
                        Toast.makeText(MainActivity.this, "You Clicked : " + item.getTitle(), Toast.LENGTH_SHORT).show();
                        addToList();
                        return true;

                    case R.id.menuClear:
                        Toast.makeText(MainActivity.this, "You Clicked : " + item.getTitle(), Toast.LENGTH_SHORT).show();
                        clearList();
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
    }
}
