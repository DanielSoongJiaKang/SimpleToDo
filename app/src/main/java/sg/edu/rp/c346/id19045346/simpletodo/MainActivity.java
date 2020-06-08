package sg.edu.rp.c346.id19045346.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etTask;
    Button btnAdd;
    Button btnClear;
    ListView lvtask;
    Spinner spnchoice;
    Button btndelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        etTask = findViewById(R.id.editTextTask);
        btnAdd = findViewById(R.id.buttonAdd);
        btnClear = findViewById(R.id.buttonClear);
        lvtask = findViewById(R.id.listViewTask);
        spnchoice = findViewById(R.id.spinnerchoice);
        btndelete = findViewById(R.id.buttondel);

        final ArrayList<String> taskList = new ArrayList<String>();

        final ArrayAdapter<String> taskadapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,taskList);
        lvtask.setAdapter(taskadapter);


        spnchoice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        btndelete.setEnabled(false);
                        btnAdd.setEnabled(true);
                        etTask.setHint("Type in a new task here");
                        etTask.setText("");
                        etTask.setInputType(InputType.TYPE_CLASS_TEXT);
                        break;
                    case 1:
                        btndelete.setEnabled(true);
                        btnAdd.setEnabled(false);
                        etTask.setInputType(InputType.TYPE_CLASS_NUMBER);
                        etTask.setText("");
                        etTask.setHint("Type in the index of the task to be removed");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!etTask.getText().toString().isEmpty()) {
                    String add = etTask.getText().toString();
                    taskList.add(add);
                    taskadapter.notifyDataSetChanged();
                }
                else {
                    Toast.makeText(MainActivity.this,"Please enter text",Toast.LENGTH_SHORT).show();
                }

            }
        });


        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(taskList.size() == 0) {
                    Toast.makeText(MainActivity.this,"You don't have any task to remove",Toast.LENGTH_SHORT).show();
                }
                else {
                    taskList.clear();
                    taskadapter.notifyDataSetChanged();
                }


            }
        });

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean isTrue = false;
                if(!etTask.getText().toString().isEmpty()) {
                    String del = etTask.getText().toString();
                    int delete = Integer.parseInt(del);
                    for(int i =0 ; i<taskList.size();i++) {
                        if(i == delete) {

                            taskList.remove(delete);
                            taskadapter.notifyDataSetChanged();
                            isTrue = true;
                            break;
                        }
                    }

                    if(!isTrue) {
                        taskadapter.notifyDataSetChanged();
                        Toast.makeText(MainActivity.this,"Wrong indeex number",Toast.LENGTH_SHORT).show();


                    }
                }






            }
        });

    }


}
