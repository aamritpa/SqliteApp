package com.example.sqliteapp;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText editName,editSurname,editMarks;
    Button btnAddData;
    Button btnviewAll;
    Button btnviewUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);

        editName =(EditText)findViewById(R.id.editText_name);
        editSurname =(EditText)findViewById(R.id.editText_surname);
        editMarks =(EditText)findViewById(R.id.editText_Marks);
        btnAddData =(Button)findViewById(R.id.button_add);
        btnviewAll = (Button)findViewById(R.id.button_viewAll);
        AddData();
        viewAll();
    }

    public void AddData() {
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                      boolean isInserted =  myDb.insertData(editName.getText().toString(), editSurname.getText().toString(), editMarks.getText().toString());
                      if(isInserted =true)
                          Toast.makeText(MainActivity.this,"Data Inserted", Toast.LENGTH_LONG).show();
                      else
                          Toast.makeText(MainActivity.this,"Data not Inserted", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void viewAll() {
        btnviewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick (View v) {
                        Cursor res=  myDb.getAllData();
                        if(res.getCount() ==0) {
                            // show message
                            showMessage("Error", "Nothing found");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer() ;
                        while (res.moveToNext()) {
                            buffer.append("Id :"+ res.getString(0)+"\n" );
                            buffer.append("Id :"+ res.getString(1)+"\n" );
                            buffer.append("Id :"+ res.getString(2)+"\n" );
                            buffer.append("Id :"+ res.getString(3)+"\n\n" );
                        }

                        //Show all Data
                        showMessage("Data", buffer.toString());
                    }
                }
        );
    }


        public void showMessage(String title, String Message){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(true);
            builder.setTitle(title);
            builder.setMessage(Message);
            builder.show();

        }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
