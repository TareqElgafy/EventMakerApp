package com.example.reem.eventmaker;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Edit extends Activity {
    RegistrationAdapter regadapter;
    RegistrationOpenHelper openHelper;
    int rowId;
    Cursor c;
    String fNameValue, lNameValue, dNameValue, daNameValue, tnameValue;
    EditText fname, lname, dname, daname, tname;
    Button editSubmit, btnDelete;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        fname = (EditText) findViewById(R.id.et_editfname);
        lname = (EditText) findViewById(R.id.et_editlname);
        dname = (EditText) findViewById(R.id.et_editdname);
        daname = (EditText) findViewById(R.id.et_editdaname);
        tname = (EditText) findViewById(R.id.et_edittname);
        editSubmit = (Button) findViewById(R.id.btn_update);
        btnDelete = (Button) findViewById(R.id.btn_delete);

        Bundle showData = getIntent().getExtras();
        rowId = showData.getInt("keyid");
        // Toast.makeText(getApplicationContext(), Integer.toString(rowId),
        // 500).show();
        regadapter = new RegistrationAdapter(this);

        c = regadapter.queryAll(rowId);

        if (c.moveToFirst()) {
            do {
                fname.setText(c.getString(1));
                lname.setText(c.getString(2));
                dname.setText(c.getString(3));
                daname.setText(c.getString(4));
                tname.setText(c.getString(5));
            } while (c.moveToNext());
        }

        editSubmit.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                regadapter.updateldetail(rowId, fname.getText().toString(),
                        lname.getText().toString(), dname.getText().toString(), daname.getText().toString(), tname.getText().toString());
                finish();
            }
        });
        btnDelete.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                regadapter.deletOneRecord(rowId);
                finish();
            }
        });
    }
}
