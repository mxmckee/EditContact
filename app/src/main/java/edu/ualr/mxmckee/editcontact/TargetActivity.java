package edu.ualr.mxmckee.editcontact;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class TargetActivity extends Activity {

    Contact c = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target);

        c = getIntent().getParcelableExtra(MainActivity.EXTRA_PERSON);

        TextView nameTextView = findViewById(R.id.text_view_name);
        TextView phoneTextView = findViewById(R.id.text_view_phone);
        TextView emailTextView = findViewById(R.id.text_view_email);
        TextView addressTextView = findViewById(R.id.text_view_address);
        TextView websiteTextView = findViewById(R.id.text_view_website);

        nameTextView.setText(String.format("%s %s", c.getFirstName(), c.getLastName()));
        phoneTextView.setText(c.getPhone());
        emailTextView.setText(c.getEmail());
        addressTextView.setText(c.getAddress());
        websiteTextView.setText(c.getWebsite());
    }

    public void onCallButtonClick(View view) {
        String phoneNumberUri = String.format("tel: %s", c.getPhone());
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(phoneNumberUri));
        startActivity(intent);
    }

    public void onTextButtonClick(View view) {
        String smsUri = String.format("smsto: %s", c.getPhone());
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse(smsUri));
        startActivity(intent);
    }

    public void onEmailButtonClick(View view) {
        String emailReceiverList[] = {c.getEmail()};
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_EMAIL, emailReceiverList);
        startActivity(Intent.createChooser(intent, "To complete action choose:"));
    }

    public void onMapButtonClick(View view) {
        String place = c.getAddress();
        String placeUri = String.format("geo:0,0?q=(%s)", place);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(placeUri));
        startActivity(intent);
    }

    public void onWebButtonClick(View view) {
        String webUri = null;
        if (c.getWebsite().contains("https://") || c.getWebsite().contains("http://")) {
            webUri = c.getWebsite();
        }
        else {
            webUri = String.format("https://%s", c.getWebsite());
        }
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(webUri));
        startActivity(intent);
    }
}
