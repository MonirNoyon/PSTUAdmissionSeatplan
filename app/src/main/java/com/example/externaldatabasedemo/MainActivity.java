package com.example.externaldatabasedemo;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class MainActivity extends AppCompatActivity {

    Myclass myclass;
    SQLiteDatabase sqLiteDatabase;
    Cursor mycursor;
    private EditText entrTxt;
    private CardView qury;

    private TextView showText,rslt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myclass = new Myclass(this);
        myclass.startWork();
        sqLiteDatabase = myclass.getWritableDatabase();
        entrTxt = (EditText) findViewById(R.id.enterId);
        qury = (CardView) findViewById(R.id.selectId);
        rslt = (TextView) findViewById(R.id.showresultId);

        entrTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                entrTxt.setText("");
                rslt.setText("See Your Result Here...");
            }
        });

        qury.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (entrTxt.getText().toString().matches("")) {
                    entrTxt.setError("Please enter a valid roll");
                } else {
                    int range = Integer.parseInt(entrTxt.getText().toString());
                    if (range < 10001 || range > 31381) {
                        if (range == 120676) {
                        } else {
                            int count = entrTxt.getText().length();
                            if (count < 5) {
                                entrTxt.setError("Please enter at least 5 Character...");
                            } else {
                                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                                alert.setTitle("Error");
                                alert.setMessage("You have entered incorrect Id, please try again...");
                                alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                });
                                alert.show();
                            }
                        }
                    } else {
                        try {
                            int j = Integer.parseInt(entrTxt.getText().toString());
                            String result = getData(j);
                            rslt.setText(result + "\n");
                        } catch (Exception ex) {
                            Toast.makeText(MainActivity.this, ex.getMessage().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

    }

    public String getData(int id)
    {
        mycursor = sqLiteDatabase.rawQuery("SELECT * FROM student WHERE  roll ='" +id+ "';",null);
        StringBuffer buffer = new StringBuffer();
        while(mycursor.moveToNext())
        {
            String address = mycursor.getString(1);
            String address1 = mycursor.getString(2);
            String address2 = mycursor.getString(3);
            buffer.append("\tUnit = "+address2+"\n\n");
            buffer.append("\tHall Name = "+address+"\n\n");
            buffer.append("\tRoom = "+address1+"\n");
        }
        return buffer.toString();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() ==R.id.dvlper)
        {
            Intent intent = new Intent(MainActivity.this,Developer.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
