package com.example.training;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import android.content.Context;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;



        });

        EditText nu1 = findViewById(R.id.nu1);
        EditText nu2 = findViewById(R.id.nu2);
        TextView sum = findViewById(R.id.sum);
        Button add = findViewById(R.id.add);
        Button save = findViewById(R.id.save);
        Button next = findViewById(R.id.next);
        EditText fileName = findViewById(R.id.fileName);


        // دا زرار الجمع بيجمع لما تضغط
        add.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View V)
            {
                double x = Double.parseDouble(nu1.getText().toString());
                double y = Double.parseDouble(nu2.getText().toString());
                sum.setText(String.valueOf(x + y));}
        });
        //دا بيحفظ الناتج في الذاكرة الداخلية
        save.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View V)
            {
                String name = fileName.getText().toString();
                String content = sum.getText().toString();
                FileOutputStream fos = null;
                OutputStreamWriter writer = null;
                try {
                    fos = openFileOutput(name, Context.MODE_PRIVATE);
                    writer = new OutputStreamWriter(fos);
                    writer.write(content);
                    writer.close();
                    Toast.makeText(getBaseContext(), "Saved to " + getFilesDir() + "/" + fileName.getText().toString(), Toast.LENGTH_LONG).show();
                }
                catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getBaseContext(), "Error saving file.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        // دا بيودي على الactivity البعدها
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, viewSaved.class);
                startActivity(intent);
            }
        });


    }
}