package exa.ycf.expbdd_jv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    EtudiantDataControl edc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        edc=new EtudiantDataControl(AddActivity.this);
        EditText name=findViewById(R.id.nameE);
        EditText note=findViewById(R.id.noteE);
        Button b=findViewById(R.id.button4);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Etudiant E=new Etudiant(0,
                        name.getText().toString(),
                        Double.valueOf(note.getText().toString()));
                long res = edc.addEtudiant(E);
                if(res==-1)
                    Toast.makeText(AddActivity.this,"Failed!",
                            Toast.LENGTH_SHORT).show();
                else Toast.makeText(AddActivity.this,"Added successfully!",
                        Toast.LENGTH_SHORT).show();

                finish();
            }
        });
    }
}