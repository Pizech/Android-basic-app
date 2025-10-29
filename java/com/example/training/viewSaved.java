package com.example.training;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.Toast;
import android.view.View;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import androidx.appcompat.app.AppCompatActivity;




public class viewSaved extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_saved);

        //بيجيب اسماء الملفات المتخذنة
        String[] savedFiles = fileList();

        ListView list = findViewById(R.id.listView);

        // بيربط ما بين اسماء الملفات الهي عبارة عن string variable ويجهزها عشان تنفع تتحط في listview
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,                               // The current Activity context
                android.R.layout.simple_list_item_1, // A standard layout for a simple text item
                savedFiles                           // The data source (your file names)
        );

        // بيعرض الlist
        list.setAdapter(adapter);

        // لما element من list تنضغط يقوم يبعت اسم الملف للfunction readFromFile ويطبع الاسم والمحتويات
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String fileName = savedFiles[position];
                String fileContent = readFromFile(fileName);
                Toast.makeText(viewSaved.this, "Content of " + fileName + ":\n" + fileContent, Toast.LENGTH_SHORT).show();
            }


        });



    }
    private String readFromFile(String fileName) {
        StringBuilder contentBuilder = new StringBuilder();
        try (FileInputStream fis = openFileInput(fileName);
             BufferedReader reader = new BufferedReader(new InputStreamReader(fis))) {

            String line;
            while ((line = reader.readLine()) != null) {
                contentBuilder.append(line).append("\n");
            }
        } catch (FileNotFoundException e) {
            return "Error: File not found.";
        } catch (IOException e) {
            e.printStackTrace();
            return "Error reading file content.";
        }
        return contentBuilder.toString();
    }


}
