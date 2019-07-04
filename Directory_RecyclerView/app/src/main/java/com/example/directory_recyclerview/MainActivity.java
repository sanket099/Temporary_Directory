package com.example.directory_recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements Person_adapter.ItemClicked {

    TextView tvname;
    TextView tvtel;
    EditText etname, ettel;
    Button btnadd;
    List_frag listFrag;
    FragmentManager fragmentManager;
    ImageView iv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvname = findViewById(R.id.tvname);
        tvtel = findViewById(R.id.tvtel);
        etname = findViewById(R.id.etname);
        ettel = findViewById(R.id.ettel);
        btnadd=findViewById(R.id.btnadd);
        fragmentManager=this.getSupportFragmentManager();
        listFrag = (List_frag)fragmentManager.findFragmentById(R.id.fragment);
        iv = findViewById(R.id.iv);

        Toast.makeText(MainActivity.this,"you may call your contacts by tapping the call button",Toast.LENGTH_SHORT).show();

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (etname.getText().toString().trim().isEmpty() || ettel.getText().toString().trim().isEmpty())
                {
                    Toast.makeText(MainActivity.this,"all fields are compulsory",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    ApplicationClass.people.add(new Person(etname.getText().toString().trim(),ettel.getText().toString().trim()));
                    Toast.makeText(MainActivity.this,"successful",Toast.LENGTH_SHORT).show();
                    etname.setText(null);
                    ettel.setText(null);
                    listFrag.notifyDataChanged();
                }

            }
        });



    }

    @Override
    public void onItemClicked(int index) {
        tvname.setText(ApplicationClass.people.get(index).getName());
        tvtel.setText(ApplicationClass.people.get(index).getTelnr());
        final String a = tvtel.getText().toString();


        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+ a));
                startActivity(intent);
            }
        });



    }
}
