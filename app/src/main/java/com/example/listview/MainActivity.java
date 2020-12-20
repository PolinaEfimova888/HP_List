package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    UserListAdapter adapter;
    ListView listView;
    ArrayList<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.list);

        //ArrayList<User> users = new ArrayList<>();

        // TODO: реализовать загрузку данных из JSON-файла
        // который загрузить в папку assets
        Gson gson = new Gson();
        try {
            InputStream stream = getAssets().open("users.json");
            User[] users_arr = gson.fromJson(new InputStreamReader(stream), User[].class);

            users = new ArrayList<>(Arrays.asList(users_arr));

            adapter = new UserListAdapter(this, users);
            adapter.notifyDataSetChanged();
            listView.setAdapter(adapter);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.name: {
                Collections.sort(users, new AddName());
                break;
            }
            case R.id.phone: {
                Collections.sort(users, new AddNumber());
                break;
            }
            case R.id.sex: {
                Collections.sort(users, new AddSex());
                break;
            }

        }
//        for (int i = 0; i < 10; i++) {
//            users.add(new User("Petya", "8(97)857-31-35", Sex.MAN));
//            users.add(new User("Vasya", "8(727)905-98-04", Sex.MAN));
//            users.add(new User("Valya", "8(744)046-08-44", Sex.WOMAN));
//            users.add(new User("UFO", "8(44)432-89-14", Sex.UNKNOWN));
//        }


        adapter = new UserListAdapter(this, users);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);
    }
}