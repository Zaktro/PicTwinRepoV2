package cl.ucn.disc.dsm.pictwin.frontend;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.google.android.material.appbar.MaterialToolbar;
import android.view.MenuItem;

/**
 * The Main Activity.
 *
 * @author Matias Canales Benavides.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * UserViewModel
     */
    private UserViewModel userViewModel;

    /**
     * The onCreate instance.
     * @param savedInstanceState instance.
     */
    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the Recyler View from the Layout.
        RecyclerView recyclerView = findViewById(R.id.am_rv_twins);

        // The Layout of the Recycler View.
        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL, false));

        // Build the Adapter.
        UserAdapter adapter = new UserAdapter();

        // Set the Adapter to RecyclerView.
        recyclerView.setAdapter(adapter);

        // Build the UserViewModel.
        this.userViewModel = ViewModelProvider
                .AndroidViewModelFactory
                .getInstance(super.getApplication())
                .create(UserViewModel.class);

        // Watch the View Model.
        userViewModel.getUserLiveData().observe(this, user-> {

            // Update the Adapter.
            adapter.setUser(user);

            // Refresh the GUI.
            adapter.notifyDataSetChanged();
        });

        // Menu in MaterialToolbar
        MaterialToolbar toolbar = findViewById(R.id.am_mt_toolbar);
        toolbar.setOnMenuItemClickListener(MenuItem -> {
            // If we click over the change theme
            if (MenuItem.getItemId() == R.id.menu_theme) {
                // Check the current theme
                if (AppCompatDelegate.getDefaultNightMode() != AppCompatDelegate.MODE_NIGHT_YES) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
                return true;
            }
            return false;
        });

    }

    /**
         *  Show the App.
         */
        @Override
                protected void onStart(){
            super.onStart();
            // Load the data from the backend.
            userViewModel.update();
        }
    }
