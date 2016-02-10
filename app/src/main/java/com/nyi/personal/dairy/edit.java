package com.nyi.personal.dairy;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.nyi.personal.database.DatabaseHandler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.zip.Inflater;

/**
 * Created by IN-3442 on 21-Oct-15.
 */
public class edit extends AppCompatActivity {
    EditText edit;
    TextView txt_date;
    int mHour,mMinute,mYear,mMonth,mDay;
    String date;
    DatabaseHandler db;
    boolean found=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_layout);
        edit= (EditText) findViewById(R.id.edit_text);
        txt_date=(TextView) findViewById(R.id.txt_date);


        db=new DatabaseHandler(this);


        datePickerFunction();
        //while(txt_date.getText().length()==0) datePickerFunction();


        txt_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerFunction();
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.menu,null);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.save) {
            save();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void save(){
        if (found) {
            db.updateText(date, edit.getText().toString());
            Toast.makeText(getApplicationContext(), "Update", Toast.LENGTH_SHORT).show();
        } else {
            db.addTEXT(date, edit.getText().toString());
            Toast.makeText(getApplicationContext(), "Insert", Toast.LENGTH_SHORT).show();
        }
        this.finish();
    }


    public void datePickerFunction(){

        /*
        This is for datePicker
         */
        final Calendar c=Calendar.getInstance();
        mHour=c.get(Calendar.HOUR);
        mMinute =c.get(Calendar.MONTH);

        mYear =c.get(Calendar.YEAR);
        mMonth=c.get(Calendar.MONTH);
        mDay=c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dpd=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // TODO Auto-generated method stub
                date=dayOfMonth+"."+(monthOfYear+1)+"."+year;
                txt_date.setText(date);

                ArrayList<String> dateList=db.getAllDate();
                for(int a=0; a<dateList.size(); a++){
                    if(date.compareTo(dateList.get(a))==0){
                        edit.setText(db.getTextByDate(date));
                        found=true;
                        break;
                    }
                    else edit.setText("");
                }


            }
        }, mYear, mMonth, mDay);
        dpd.show();


    }


    public void readDataFromExternal()  {
        File root=new File(Environment.getExternalStorageDirectory(),"Notes");
        if(!root.exists()){
            root.mkdirs();

        }
        File gpxFile=new File(root,"Dairy.txt");

        try {
            FileInputStream in=new FileInputStream(gpxFile);

            InputStreamReader inputStreamReader=new InputStreamReader(in);
            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
            String str;
            String str2="";
            StringBuilder str1=new StringBuilder();



            while((str=bufferedReader.readLine())!=null){
                str1.append(str);

                if(str.compareTo("a")==0){

                    while((str=bufferedReader.readLine())!=null){
                        if(str.compareTo("b")==0) break;
                        else str2=str2+str+"\n";
                    }
                }
                //str2=str2+str+"\n";

            }
            in.close();
            edit.setText(str2);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void generateNoteOnSD(String sFileName,String sBody){
        try{
            File root=new File(Environment.getExternalStorageDirectory(),"Notes");
            if(!root.exists()){
                root.mkdirs();

            }
            File gpxFile=new File(root,sFileName+".txt");
            FileOutputStream fout=new FileOutputStream(gpxFile);
            OutputStreamWriter writer=new OutputStreamWriter(fout);
            writer.append(sBody);
            writer.flush();
            writer.close();
            fout.close();
            Toast.makeText(this, "Save", Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}
