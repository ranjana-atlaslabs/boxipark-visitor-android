package com.example.boxipark_visitor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ProductMenu extends AppCompatActivity implements  ExampleBottomSheetDialog.BottomSheetListener {
    ListView listView;
    ListView listView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_menu);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        listView = (ListView) findViewById(R.id.aList1);
        listView2 = (ListView) findViewById(R.id.aList2);
        populateUsersList();
        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                     //   Toast.makeText(getApplicationContext(),String.valueOf(position), Toast.LENGTH_SHORT).show();
                        ExampleBottomSheetDialog bottomSheet = new ExampleBottomSheetDialog();
                        Bundle data = new Bundle();//create bundle instance
                        data.putInt("key_value", position);//put string to pass with a key value


                        bottomSheet.setArguments(data);//Set bundle data to fragment
                        bottomSheet.show(getSupportFragmentManager(), "exampleBottomSheet");
                    }
                }
        );

    }


    private void populateUsersList() {
        // Construct the data source
        ArrayList<User> arrayOfUsers = User.getUsers();
        // Create the adapter to convert the array to views
        CustomUsersAdapter adapter = new CustomUsersAdapter(this, arrayOfUsers);
        // Attach the adapter to a ListView



        listView.setAdapter(adapter);
        listView2.setAdapter(adapter);
        setListViewHeightBasedOnChildren(listView);
        setListViewHeightBasedOnChildren(listView2);
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount()));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }

    @Override
    public void onButtonClicked(String text) {
        //Toast.makeText(getApplicationContext(),text, Toast.LENGTH_SHORT).show();

    }
}
