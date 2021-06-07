package sg.edu.rp.c346.id20011066.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Spinner spinner;
    EditText etActivity;
    Button btnAdd;
    Button btnDelete;
    Button btnClear;
    ListView lvActivity;

    ArrayList<String> alActivity;
    ArrayAdapter<String> aaActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinner);
        etActivity = findViewById(R.id.editTextActivity);
        btnAdd = findViewById(R.id.buttonAdd);
        btnDelete = findViewById(R.id.buttonDelete);
        btnClear = findViewById(R.id.buttonClear);
        lvActivity = findViewById(R.id.listViewActivities);

        alActivity = new ArrayList<String>();

        aaActivity = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alActivity);

        lvActivity.setAdapter(aaActivity);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        etActivity.setHint("Type in a new task here");
                        btnAdd.setEnabled(true);
                        btnDelete.setEnabled(false);
                        break;
                    case 1:
                        etActivity.setHint("Type in the index of the task to be removed");
                        btnDelete.setEnabled(true);
                        btnAdd.setEnabled(false);
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
                String strText = etActivity.getText().toString();

                alActivity.add(strText);
                aaActivity.notifyDataSetChanged();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (alActivity.size() == 0) {
                    Toast.makeText(MainActivity.this, "You do not have any task to remove", Toast.LENGTH_LONG).show();
                } else if (Integer.parseInt(etActivity.getText().toString()) >= alActivity.size()) {
                    Toast.makeText(MainActivity.this, "Wrong index number", Toast.LENGTH_LONG).show();

                } else {
                    alActivity.remove(Integer.parseInt(etActivity.getText().toString()));
                    aaActivity.notifyDataSetChanged();
                }
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               alActivity.clear();
               aaActivity.notifyDataSetChanged();
            }
        });
    }
}