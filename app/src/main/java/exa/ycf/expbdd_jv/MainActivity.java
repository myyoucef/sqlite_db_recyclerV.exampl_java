package exa.ycf.expbdd_jv;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EtudiantDataControl edc;
    DBHelper dbHelper;
    ArrayList<Etudiant> listEtud;

    RecyclerView recyclerView;
    myAdapter adapter;
    Button add,clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add = findViewById(R.id.button2);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, AddActivity.class);
                startActivityForResult(i, 1);
            }
        });
        clear= findViewById(R.id.button3);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Clear_list();
            }
        });

        dbHelper = new DBHelper(MainActivity.this);
        edc = new EtudiantDataControl(MainActivity.this);
        listEtud = new ArrayList<>();

        loadAllE();

        recyclerView = findViewById(R.id.Recyc);
        recyclerView.setHasFixedSize(true);
        adapter = new myAdapter(MainActivity.this, listEtud);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

    }


    void loadAllE() {
        Cursor curso = edc.readAll();
        if (curso.getCount() == 0) {
            Toast.makeText(this, "No data !", Toast.LENGTH_SHORT).show();
        } else {
            while (curso.moveToNext()) {
                Etudiant e = new Etudiant(
                        curso.getLong(0),
                        curso.getString(1),
                        curso.getDouble(2));
                listEtud.add(e);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            recreate();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.deleteAll) {
            Clear_list();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.pos) {

            int pos=adapter.getPosition();

            RecyclerView.ViewHolder vholder=recyclerView.findViewHolderForLayoutPosition(pos);

            TextView tv=(TextView)vholder.itemView.findViewById(R.id.textView1);
            String txt=tv.getText().toString();
            Toast.makeText(this, "Position : "+pos+"\n"+txt,Toast.LENGTH_SHORT).show();
        }
        return super.onContextItemSelected(item);
    }

    void Clear_list(){
        AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
        dlg.setTitle("Confirmation");
        dlg.setMessage("Are you sure you want to remove all ?");
        dlg.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                EtudiantDataControl edc = new EtudiantDataControl(MainActivity.this);
                edc.deleteAll();
                recreate();
            }
        });
        dlg.setNegativeButton("No", null);
        dlg.show();
    }

}
