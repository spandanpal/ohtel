package com.ohtel.ohtel.screens.screens.addrestaurant;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.ohtel.ohtel.BaseActivity;
import com.ohtel.ohtel.R;
import com.ohtel.ohtel.screens.screens.addrestaurant.model.AddRestaurantModel;
import com.ohtel.ohtel.screens.screens.dashboard.DashBoard;
import com.ohtel.ohtel.utils.CommonUtils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AddRestaurant extends BaseActivity implements AdapterView.OnItemSelectedListener,IAddRestaurantView {

    @Bind(R.id.image_back_icon_id)
    AppCompatImageView backIcon;
    @Bind(R.id.toolbar_menu_left)
    AppCompatImageView toolBarMenu;

    @Bind(R.id.et_restaurant_name)
    AppCompatEditText restaurantName;
    @Bind(R.id.et_restaurant_email_id)
    AppCompatEditText restaurantEmailId;
    @Bind(R.id.et_restaurant_contact_no)
    AppCompatEditText restaurantContactNo;
    @Bind(R.id.et_restaurant_altr_no)
    AppCompatEditText restaurantAltrNo;
    @Bind(R.id.restaurant_type_spinner)
    Spinner restaurantTypeSpinner;
    @Bind(R.id.et_no_tables)
    AppCompatEditText noTables;
    @Bind(R.id.et_restaurant_location)
    AppCompatEditText restaurantLocation;
    @Bind(R.id.restaurant_ac_non_an_spinner)
    Spinner restaurantAcNonAnSpinner;
    @Bind(R.id.et_restaurant_address)
    AppCompatEditText restaurantAddress;
    @Bind(R.id.restaurant_furnished_type_spinner)
    Spinner furnishedTypeSpinner;
    @Bind(R.id.btn_add_restaurant)
    AppCompatButton addRestaurant;
    @Bind(R.id.restaurant_image)
    AppCompatImageView restaurantImage;
    @Bind(R.id.restaurant_plus_image)
    AppCompatImageView restaurantPlusImage;
    @Bind(R.id.add_image_text)
    AppCompatTextView addImageText;
    private IAddRestaurantPresenter iAddRestaurantPresenter;
    private String encImage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.add_restaurant,rl_base_container);
        setTitle("Add Restaurant");
        ButterKnife.bind(this);
        toolBarMenu.setVisibility(View.GONE);
        backIcon.setVisibility(View.VISIBLE);
        init();
        initRestaurantTypeSpinner();
        initAcNonAcSpinner();
        initFurnishedSpinner();
        restaurantPlusImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getImage();
            }
        });
    }

    private void initFurnishedSpinner() {
        String[] furnishedType = {"Semi-Furnished","Furnished"};
        furnishedTypeSpinner.setOnItemSelectedListener(this);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, furnishedType);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        furnishedTypeSpinner.setAdapter(arrayAdapter);
    }

    private void initAcNonAcSpinner() {
        String[] restaurantAcNonAn = {"Yes","No"};
        restaurantAcNonAnSpinner.setOnItemSelectedListener(this);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, restaurantAcNonAn);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        restaurantAcNonAnSpinner.setAdapter(arrayAdapter);
    }

    private void initRestaurantTypeSpinner() {
        String[] restaurantType = {"Veg","Non-Veg","Veg-NonVeg"};
        restaurantTypeSpinner.setOnItemSelectedListener(this);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, restaurantType);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        restaurantTypeSpinner.setAdapter(arrayAdapter);
    }

    private void init() {
        iAddRestaurantPresenter = new AddRestaurantPresenter(this);

     /*   BitmapDrawable drawable = (BitmapDrawable) restaurantImage.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,bos);
        byte[] bb = bos.toByteArray();*/
//        String image = Base64.enc(bb,0);




        addRestaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(restaurantName.getText().toString().length()==0){
                    restaurantName.setError(getString(R.string.pls_restaurant_name));
                }else  if(restaurantEmailId.getText().toString().length()==0){
                    restaurantEmailId.setError(getString(R.string.pls_enter_email));
                }else  if(restaurantContactNo.getText().toString().length()==0){
                    restaurantContactNo.setError(getString(R.string.pls_enter_contact_number));
                }else  if(restaurantAltrNo.getText().toString().length()==0){
                    restaurantAltrNo.setError(getString(R.string.pls_altrno));
                }else  if(noTables.getText().toString().length()==0){
                    noTables.setError(getString(R.string.pls_noTables));
                }else  if(restaurantLocation.getText().toString().length()==0){
                    restaurantLocation.setError(getString(R.string.pls_enter_location));
                }else  if(restaurantAddress.getText().toString().length()==0){
                    restaurantAddress.setError(getString(R.string.pls_enter_address));
                }else  if(restaurantAddress.getText().toString().length()==0){
                    restaurantAddress.setError(getString(R.string.pls_enter_address));
                }else {
                    callApi();
                }
            }
        });
    }

    private void callApi() {
        showProgressBar();
        iAddRestaurantPresenter.addRestaurant(restaurantName.getText().toString(),restaurantEmailId.getText().toString(),restaurantContactNo.getText().toString(),
                restaurantAltrNo.getText().toString(),noTables.getText().toString(),restaurantLocation.getText().toString(),restaurantAddress.getText().toString(),
                restaurantAcNonAnSpinner.getSelectedItem().toString(),restaurantTypeSpinner.getSelectedItem().toString(),furnishedTypeSpinner.getSelectedItem().toString(),encImage);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void showProgressBar() {
        CommonUtils.showProgressBar(this);
    }

    @Override
    public void hideProgressBar() {
        CommonUtils.dismissProgressDialog();
    }

    @Override
    public void setResult(AddRestaurantModel model) {
        if (model!=null){
            if (model.getStatus().equalsIgnoreCase("Success")){
                Intent intent = new Intent(this, DashBoard.class);
                startActivity(intent);
            }else if (model.getStatus().equalsIgnoreCase("Failed")){
                Toast.makeText(this, model.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void getImage() {
        Dialog dialog = new Dialog(AddRestaurant.this, R.style.comm_dialog);
        dialog.setContentView(R.layout.select_photo);
        AppCompatTextView photoCamera = dialog.findViewById(R.id.camera);
        AppCompatTextView photoGallery = dialog.findViewById(R.id.gallery);
        photoCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(takePicture, 0);
            }
        });
        photoGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto , 1);
            }
        });
        dialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case 0:
                if(resultCode == RESULT_OK){
                    Uri cameraURI = data.getData();
                    restaurantImage.setImageURI(cameraURI);
                    restaurantPlusImage.setVisibility(View.GONE);
                    restaurantImage.setVisibility(View.VISIBLE);
                    addImageText.setVisibility(View.GONE);

                    try {
                        final InputStream imageStream = getContentResolver().openInputStream(cameraURI);
                        final Bitmap camera = BitmapFactory.decodeStream(imageStream);
                        encImage = encodeImage(camera);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

                break;
            case 1:
                if(resultCode == RESULT_OK){
                    Uri galleryURI = data.getData();
                    restaurantImage.setImageURI(galleryURI);
                    restaurantPlusImage.setVisibility(View.GONE);
                    restaurantImage.setVisibility(View.VISIBLE);
                    addImageText.setVisibility(View.GONE);
                try{
                    final InputStream imageStream = getContentResolver().openInputStream(galleryURI);
                    final Bitmap camera = BitmapFactory.decodeStream(imageStream);
                    encImage = encodeImage(camera);
                }catch (Exception e){
                e.printStackTrace();
            }
                }
                break;
        }
    }

    private String encodeImage(Bitmap camera) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        camera.compress(Bitmap.CompressFormat.JPEG,100,baos);
        byte[] b = baos.toByteArray();
        encImage = Base64.encodeToString(b, Base64.DEFAULT);

        return encImage;
    }
}
