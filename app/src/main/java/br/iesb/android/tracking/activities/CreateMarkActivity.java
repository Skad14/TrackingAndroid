package br.iesb.android.tracking.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;

import br.iesb.android.tracking.Constants;
import br.iesb.android.tracking.R;
import br.iesb.android.tracking.models.Location;
import butterknife.Bind;
import butterknife.ButterKnife;

import static java.security.AccessController.getContext;

public class CreateMarkActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.createMarkButton) Button mCreateMarkButton;
    @Bind(R.id.nameEditText) EditText mNameEditText;

    //private static final int REQUEST_IMAGE_CAPTURE = 111;
    public Location mLocation;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_mark);

        ButterKnife.bind(this);

        mCreateMarkButton.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_photo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_photo:
                //onLaunchCamera();
            default:
                break;
        }
        return false;
    }

    /*public void onLaunchCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }*/

    /*@Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == getActivity().RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            encodeBitmapAndSaveToFirebase(imageBitmap);
        }
    }*/

    /*public void encodeBitmapAndSaveToFirebase(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        String imageEncoded = Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT);
        DatabaseReference ref = FirebaseDatabase.getInstance()
                .getReference(Constants.FIREBASE_CHILD_LOCATIONS)
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child(mLocation.getPushId())
                .child("imageUrl");
        ref.setValue(imageEncoded);
    }*/


    @Override
    public void onClick(View view) {

        if (view == mCreateMarkButton) {

            Intent intent = new Intent(this, LocationMapFragment.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
    }

    /*public void saveMarkToFirebase(String location) {
        mNameEditText.setName(location);
    }

    private void createNewMark() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        DatabaseReference locationRef = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_LOCATIONS)
                .child(uid);

        DatabaseReference pushRef = locationRef.push();
        String pushId = pushRef.getKey();
        mLocation.setPushId(pushId);
        pushRef.setValue(mLocation);

        Toast.makeText(getContext(), "Salvo", Toast.LENGTH_SHORT).show();
    }*/
}
