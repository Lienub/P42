package com.example.projet;

import android.os.Bundle;

import com.example.projet.model.Contact;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projet.databinding.ActivityMainBinding;
import com.example.projet.viewmodel.AddressBookViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RequestQueue instance = null;
    private ActivityMainBinding binding;

    ArrayList<Contact> contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        instance = Volley.newRequestQueue(this);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        // Récupération du RV
        RecyclerView recyclerView = findViewById(R.id.recyclerview);

        // Création de la liste de contacts
        List<Item> items = new ArrayList<Item>();
        items.add(new Item("John", "Wick"));
        items.add(new Item("Stella", "Star"));
        items.add(new Item("Jean", "Faible"));
        items.add(new Item("John", "Wick"));
        items.add(new Item("Stella", "Star"));
        items.add(new Item("Jean", "Faible"));
        items.add(new Item("John", "Wick"));
        items.add(new Item("Stella", "Star"));
        items.add(new Item("Jean", "Faible"));
        items.add(new Item("John", "Wick"));
        items.add(new Item("Stella", "Star"));
        items.add(new Item("Jean", "Faible"));

        // Set des besoins du RV
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(new MyAdapter(getApplicationContext(), items));

    }

}