package com.example.financemanager;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class AddTransactionActivity extends AppCompatActivity {
    EditText amountInput, noteInput;
    RadioGroup typeGroup;
    Button saveBtn;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);

        amountInput = findViewById(R.id.editAmount);
        noteInput = findViewById(R.id.editNote);
        typeGroup = findViewById(R.id.radioGroupType);
        saveBtn = findViewById(R.id.btnSave);
        db = new DatabaseHelper(this);

        saveBtn.setOnClickListener(v -> {
            String type = (typeGroup.getCheckedRadioButtonId() == R.id.radioIncome) ? "Income" : "Expense";
            int amount = Integer.parseInt(amountInput.getText().toString());
            String note = noteInput.getText().toString();

            db.insertTransaction(type, amount, note);
            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
