package pg.grigaliunas.paulius.atpaink10;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

public class HomeFragment extends Fragment {
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 1888;
    FloatingActionButton fab;
    ImageView imageView;
    private DatabaseHelper mydb;
    private Button NamBarBtnVar;
    private Toolbar toolbar;
    private Bitmap bitmap1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView =  inflater.inflate(R.layout.fragment_home, container, false);

        mydb = new DatabaseHelper(getActivity());
        fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        imageView = (ImageView) rootView.findViewById(R.id.imageView4);
        NamBarBtnVar = new Button(getActivity());
        changeToolBar();


        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,
                        CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
            }
        });

        saveObject();
        return rootView;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {

                Bitmap bmp = (Bitmap) data.getExtras().get("data");
                ByteArrayOutputStream stream = new ByteArrayOutputStream();

                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();

                // convert byte array to Bitmap

                bitmap1 = BitmapFactory.decodeByteArray(byteArray, 0,
                        byteArray.length);

                imageView.setImageBitmap(bitmap1);

            }
        }
    }

    private void changeToolBar(){

        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);

        toolbar.setSubtitle("Task info");
        NamBarBtnVar.setText("save");
        Toolbar.LayoutParams layoutParams = new Toolbar.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity=Gravity.END;
        toolbar.addView(NamBarBtnVar, layoutParams);
    }


    private void saveObject() {
        NamBarBtnVar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageObject image = new ImageObject( System.currentTimeMillis() , 5 , bitmap1);
                if(mydb.insertTaskData(image)){
                    Snackbar.make(v, "Picture was saved", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                }
                else
                    Snackbar.make(v, "Picture was't saved", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


}
