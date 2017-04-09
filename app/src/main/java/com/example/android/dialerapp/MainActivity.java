package com.example.android.dialerapp;

import android.app.ListActivity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;
import android.database.Cursor;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends ListActivity implements View.OnClickListener {
    private EditText screen;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    ListView lv;
    Cursor cursor1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intilializeView();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        cursor1=getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        startManagingCursor(cursor1);
        String [] from={ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,ContactsContract.CommonDataKinds.Phone._ID};

        int[] to={android.R.id.text1,android.R.id.text2};
        SimpleCursorAdapter listAdapter=new SimpleCursorAdapter(this,android.R.layout.simple_list_item_2,cursor1,from,to);
        setListAdapter(listAdapter);
        lv=getListView();
        lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
    }

    @Override
    public int getSelectedItemPosition() {
        return super.getSelectedItemPosition();
    }

    @Override
    public long getSelectedItemId() {
        return super.getSelectedItemId();
    }

    private void intilializeView() {
        screen = (EditText) findViewById(R.id.screen);
        int idList[] = {R.id.button0, R.id.button1, R.id.button2, R.id.button3,
                R.id.button4, R.id.button5, R.id.button6,
                R.id.button7, R.id.button8, R.id.button9,
                R.id.star, R.id.hash, R.id.delete,
                R.id.dial, R.id.delete};

        for (int d : idList) {
            View v = (View) findViewById(d);
            v.setOnClickListener(this);
        }
    }

    public void display(String str) {
        screen.append(str);
    }

    private boolean checkCallPermission() {
        String permission = "android.permission.CALL_PHONE";
        int res = getApplicationContext().checkCallingOrSelfPermission(permission);
        return (res == PackageManager.PERMISSION_GRANTED);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.button0:
                display("0");
                break;
            case R.id.button1:
                display("1");
                break;
            case R.id.button2:
                display("2");
                break;
            case R.id.button3:
                display("3");
                break;
            case R.id.button4:
                display("4");
                break;
            case R.id.button5:
                display("5");
                break;
            case R.id.button6:
                display("6");
                break;
            case R.id.button7:
                display("7");
                break;
            case R.id.button8:
                display("8");
                break;
            case R.id.button9:
                display("9");
                break;
            case R.id.star:
                display("*");
                break;
            case R.id.hash:
                display("#");
                break;
            case R.id.dial:
                if (screen.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Enter Some  digits", Toast.LENGTH_SHORT).show();
                } else if (checkCallPermission()) {
                    startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + screen.getText())));
                }
                break;
            case R.id.delete:
                if (screen.getText().toString().length() >= 1) {
                    String newScreen = screen.getText().toString().substring(0, screen.getText().toString().length() - 1);
                    screen.setText(newScreen);
                }
                break;
        }

    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.android.dialerapp/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.android.dialerapp/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}