/*
 * Copyright (C) 2013 AChep@xda <artemchep@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.mydemo;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.com.example.mydemo.R;
import com.example.mydemo.adapter.Home_menu_adapter;
import com.manuelpeinado.fadingactionbar.FadingActionBarHelper;

public class MainActivity extends Activity {

    private static final String LINK_TO_GITHUB = "https://github.com/AChep/Header2ActionBar";

    private FadingActionBarHelper mFadingActionBarHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFadingActionBarHelper = new FadingActionBarHelper()
        .actionBarBackground(R.drawable.ab_background)
        .headerLayout(R.layout.fragment_header)
        .contentLayout(R.layout.fragment_listview);
        
        setContentView(mFadingActionBarHelper.createView(this));
        mFadingActionBarHelper.initActionBar(this);
        
        
        ListView listView = (ListView) findViewById(android.R.id.list);
        String[] txt_m = getResources().getStringArray(R.array.home_item_menu);
        Home_menu_adapter adapter = new Home_menu_adapter(getApplicationContext(), R.layout.item_home_menu_fragment, txt_m);
        listView.setAdapter(adapter);
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_github) { // open repo at GitHub
            Uri uri = Uri.parse(LINK_TO_GITHUB);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public FadingActionBarHelper getFadingActionBarHelper() {
        return mFadingActionBarHelper;
    }

}
