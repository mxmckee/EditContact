package edu.ualr.mxmckee.editcontact;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_PERSON = "PersonData";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onButtonClick(View view) {
        EditText firstNameEditText = findViewById(R.id.edit_text_first_name);
        EditText lastNameEditText = findViewById(R.id.edit_text_last_name);
        EditText phoneEditText = findViewById(R.id.edit_text_phone);
        EditText emailEditText = findViewById(R.id.edit_text_email);
        EditText addressEditText = findViewById(R.id.edit_text_address);
        EditText websiteEditText = findViewById(R.id.edit_text_website);

        Intent intent = new Intent(this, TargetActivity.class);

        Contact p = new Contact(firstNameEditText.getText().toString(), lastNameEditText.getText().toString(), phoneEditText.getText().toString(), emailEditText.getText().toString(), addressEditText.getText().toString(), websiteEditText.getText().toString());
        intent.putExtra(EXTRA_PERSON, p);

        startActivity(intent);
    }
}
