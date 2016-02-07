package csc207.airline;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // Make the button do stuff.
        Button button = (Button) findViewById(R.id.loginButton);
        button.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        // Since there's only this single button, we can handle this easily
        // and assume this will always be the login button.
        TextView textView = (TextView) findViewById(R.id.headerLoginLabel);
        EditText userEditText = (EditText) findViewById(R.id.usernameTextfield);
        EditText passEditText = (EditText) findViewById(R.id.passwordField);

        // Load the data and confirm. This could always be done better, but
        // for now lets just do it this way and be clearer later.
        // Learning source: http://stackoverflow.com/questions/2902689/how-
        // can-i-read-a-text-file-from-the-sd-card-in-android
        File sdcard = Environment.getExternalStorageDirectory();
        File usersFile = new File(sdcard, "users.txt");

        HashMap<String, String> userToPass = new HashMap<String, String>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(usersFile));
            String line;
            while ((line = br.readLine()) != null) {
                Log.i("MainActivity", "Reading " + line);
                String[] tokens = line.split(",");
                if (tokens.length == 2) {
                    Log.i("MainActivity", "Adding " + tokens[0] + " " + tokens[1]);
                    userToPass.put(tokens[0], tokens[1]);
                } else {
                    Log.e("MainActivity", "Line in users corrupt: " + line);
                }
            }
        } catch (IOException e) {
            Log.e("MainActivity", "Error loading users.txt: " + e.getMessage());
        }

        // Text color learning source:
        // http://stackoverflow.com/questions/4602902/how-to-set-the-text-
        // color-of-textview-in-code
        String user = userEditText.getText().toString();
        String pass = passEditText.getText().toString();
        if (userToPass.containsKey(user) && userToPass.get(user).equals(pass)) {
            textView.setTextColor(Color.GREEN);
            textView.setText("Welcome " + user + "!");

            // Switch to the activity on a new screen. Source:
            // http://stackoverflow.com/questions/736571/using-intent
            // -in-an-android-application-to-show-another-activity
            Intent intent = new Intent(this, AdminActivity.class);
            startActivity(intent);
        } else {
            textView.setTextColor(Color.RED);
            textView.setText("Login failed");
        }
    }
}
