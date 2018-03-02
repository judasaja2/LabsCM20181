package co.edu.udea.compumovil.gr05_20181.labscm20181;

import android.Manifest;
import android.net.Uri;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.ArrayList;

import gun0912.tedbottompicker.TedBottomPicker;

public class activityPlatos extends AppCompatActivity {
    Button botonGaleria;
    ArrayList<Uri> selectedUriList;
    Uri selectedUri;
    private ViewGroup mSelectedImagesContainer;
    ImageView iv_image;

    CheckBox rbm,rbt,rbn;
    public RequestManager mGlideRequestManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_platos);
        botonGaleria= (Button) findViewById(R.id.botonGaleria);
        mGlideRequestManager = Glide.with(this);

        rbm= (CheckBox) findViewById(R.id.mañanaRb);
        rbn= (CheckBox) findViewById(R.id.nocheRb);
        botonGaleria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSingleShowButton();
            }
        });
/*
        rbm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                rbt.setChecked(false);
                rbn.setChecked(false);

            }
        });

        rbn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                rbm.setChecked(false);
                rbt.setChecked(false);

            }
        });

        rbt.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                rbn.setChecked(false);
                rbm.setChecked(false);

            }
        });
        */


    }
    private void setSingleShowButton() {


        Button btn_single_show = (Button) findViewById(R.id.botonGaleria);
        btn_single_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                PermissionListener permissionlistener = new PermissionListener() {
                    @Override
                    public void onPermissionGranted() {

                        TedBottomPicker bottomSheetDialogFragment = new TedBottomPicker.Builder(activityPlatos.this)
                                .setOnImageSelectedListener(new TedBottomPicker.OnImageSelectedListener() {
                                    @Override
                                    public void onImageSelected(final Uri uri) {
                                        Log.d("ted", "uri: " + uri);
                                        Log.d("ted", "uri.getPath(): " + uri.getPath());
                                        selectedUri = uri;

                                        iv_image.setVisibility(View.VISIBLE);
                                        mSelectedImagesContainer.setVisibility(View.GONE);
                                        iv_image.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                mGlideRequestManager
                                                        .load(uri)
                                                        .into(iv_image);
                                            }
                                        });
                                        /*
                                        Glide.with(MainActivity.this)
                                                //.load(uri.toString())
                                                .load(uri)
                                                .into(iv_image);
                                         */
                                    }
                                })
                                //.setPeekHeight(getResources().getDisplayMetrics().heightPixels/2)
                                .setSelectedUri(selectedUri)
                                //.showVideoMedia()
                                .setPeekHeight(1200)
                                .create();

                        bottomSheetDialogFragment.show(getSupportFragmentManager());


                    }

                    @Override
                    public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                        Toast.makeText(activityPlatos.this, "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
                    }


                };

                 TedPermission.with(activityPlatos.this)
                        .setPermissionListener(permissionlistener)
                        .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                        .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .check();

            }
        });
    }


}
