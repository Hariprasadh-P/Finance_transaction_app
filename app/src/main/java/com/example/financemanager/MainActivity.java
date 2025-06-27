package com.example.financemanager;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper db;
    TextView balanceText;
    ListView listView;
    Button addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this);
        balanceText = findViewById(R.id.totalBalance);
        listView = findViewById(R.id.listView);
        addBtn = findViewById(R.id.btnAdd);

        addBtn.setOnClickListener(v ->
            startActivity(new Intent(MainActivity.this, AddTransactionActivity.class))
        );
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    private void loadData() {
        Cursor cursor = db.getAllTransactions();
        ArrayList<String> list = new ArrayList<>();
        int total = 0;

        while (cursor.moveToNext()) {
            String type = cursor.getString(1);
            int amount = cursor.getInt(2);
            String note = cursor.getString(3);
            list.add(type + ": $" + amount + " - " + note);
            total += type.equals("Income") ? amount : -amount;
        }

        balanceText.setText("Balance: $" + total);
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list));
    }
}
