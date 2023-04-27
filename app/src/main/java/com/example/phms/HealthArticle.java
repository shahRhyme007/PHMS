package com.example.phms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class HealthArticle extends AppCompatActivity
{
    private String[][] healthDetails =
            {
                    {"Daily Tips", "", "", "", "Click More Details", ""},
                    {"Summer Diet", "", "", "", "Click More Details",""},
                    {"Winter Diet", "", "", "", "Click More Details",""},
                    {"Spring food", "", "", "", "Click More Details",""},
                    {"Food for keeping you glowing", "", "", "", "Click More Details",""},
                    {"Four week diet", "", "", "", "Click More Details",""}
            };
    private int[] images = {
            R.drawable.dailytip,
            R.drawable.summerdiet,
            R.drawable.winterdiet,
            R.drawable.springfood,
            R.drawable.glowingskin,
            R.drawable.fourweekhabit
    };

    HashMap<String, String > item;
    ArrayList list;
    SimpleAdapter sa;
    ListView lst;

    Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_article);

        lst = findViewById(R.id.listVIEW) ;
        btnBack = findViewById(R.id.backToDiet);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(HealthArticle.this, dietTab.class));
            }
        });

        list = new ArrayList();
        for(int i = 0; i<(healthDetails.length); i++)
        {
            item = new HashMap<String, String>();
            item.put("line1", healthDetails[i][0]);
            item.put("line2", healthDetails[i][1]);
            item.put("line3", healthDetails[i][2]);
            item.put("line4", healthDetails[i][3]);
            item.put("line5", healthDetails[i][4]);
            item.put("line6", healthDetails[i][5]);

            list.add(item);
        }
        sa = new SimpleAdapter(this, list,
                R.layout.multi_lines,
                new String[] {"line1", "line2", "line3", "line4", "line5","line6"},
                new int[] {R.id.line_a, R.id.line_b,R.id.line_d,R.id.line_e,R.id.line_f});
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Intent it = new Intent(HealthArticle.this, healthArticleDetailActivity.class);
                it.putExtra("text1", healthDetails[i][0]);
                it.putExtra("text2", images[i]);
                startActivity(it);
            }
        });
    }
}