package com.inveitix.demo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.IOException;

public class ContentActivity extends AppCompatActivity
{


    private String content;
    private TextView txtContent;
    private SharedPreferences mPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        mPreferences = this.getSharedPreferences(Consts.PREFS_NAME, MODE_PRIVATE);
        initViews();
    }

    private void initViews()
    {
        txtContent = findViewById(R.id.txt_content);
        String textToShow = mPreferences.getString(Consts.PREFS_CONTENT, "Men me nqma!!!");

        FileInputStream fis = null;
        try {
            fis = openFileInput("testFile.txt");
            textToShow = String.valueOf(fis.read());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            txtContent.setText(textToShow);

        }


    }

    public static Intent getIntent (Context context) {
        return new Intent(context, ContentActivity.class);
    }
}
