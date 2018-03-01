package co.edu.udea.compumovil.gr05_20181.labscm20181;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;

import java.util.ArrayList;

import gun0912.tedbottompicker.TedBottomPicker;

public class MainActivity extends AppCompatActivity {
 private Button botonPrueba;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        botonPrueba= (Button) findViewById(R.id.botonPrueba);
botonPrueba.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

        Intent actividad2= new Intent(getApplicationContext(), activityPlatos.class);
        startActivity(actividad2);
    }
});

    }



}
