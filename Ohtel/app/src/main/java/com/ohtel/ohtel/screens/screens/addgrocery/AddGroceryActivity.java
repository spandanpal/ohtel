package com.ohtel.ohtel.screens.screens.addgrocery;

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
import com.ohtel.ohtel.screens.screens.addgrocery.model.AddGroceryModel;
import com.ohtel.ohtel.screens.screens.grocery.GroceryActivity;
import com.ohtel.ohtel.utils.CommonUtils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AddGroceryActivity extends BaseActivity implements IAddGroceryView, AdapterView.OnItemSelectedListener {

    @Bind(R.id.image_back_icon_id)
    AppCompatImageView backIcon;
    @Bind(R.id.toolbar_menu_left)
    AppCompatImageView toolBarMenu;

    @Bind(R.id.et_grocery_shop_name)
    AppCompatEditText groceryShopName;
    @Bind(R.id.et_grocery_contact_no)
    AppCompatEditText groceryContactNo;
    @Bind(R.id.et_grocery_items)
    AppCompatEditText groceryItems;
    @Bind(R.id.et_grocery_address)
    AppCompatEditText groceryAddress;
    @Bind(R.id.et_grocery_landmark)
    AppCompatEditText groceryLandmark;
    @Bind(R.id.et_grocery_city)
    AppCompatEditText groceryCity;
    @Bind(R.id.btn_add_grocery)
    AppCompatButton addGrocery;
    @Bind(R.id.et_grocery_country)
    AppCompatEditText groceryCountry;
    @Bind(R.id.home_delivery_spinner)
    Spinner spinner;
    @Bind(R.id.hotel_plus_image)
    AppCompatImageView plusImage;
    @Bind(R.id.grocery_image)
    AppCompatImageView groceryImage;
    @Bind(R.id.add_image_text)
    AppCompatTextView addImageText;
    private String grocery_image;
    private IAddGroceryPresenter iAddGroceryPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_add_grocery,rl_base_container);
        setTitle("Add Grocery");
        ButterKnife.bind(this);
        toolBarMenu.setVisibility(View.GONE);
        backIcon.setVisibility(View.VISIBLE);
        iAddGroceryPresenter = new AddGroceryPresenter(this);
        init();
        String[] deliverySpinner = {"Yes","No"};
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, deliverySpinner);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        plusImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getImage();
            }
        });
    }

    private void init() {
        addGrocery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (groceryShopName.getText().toString().length()==0){
                    groceryShopName.setError(getString(R.string.enter_shop_name));
                }else if (groceryContactNo.getText().toString().length()==0){
                    groceryContactNo.setError(getString(R.string.pls_enter_contact_number));
                }else if (groceryItems.getText().toString().length()==0){
                    groceryItems.setError(getString(R.string.enter_items));
                }else if (groceryAddress.getText().toString().length()==0){
                    groceryAddress.setError(getString(R.string.pls_enter_address));
                }else if (groceryLandmark.getText().toString().length()==0){
                    groceryLandmark.setError(getString(R.string.enter_landmark));
                }else if (groceryCity.getText().toString().length()==0){
                    groceryCity.setError(getString(R.string.enter_city));
                }else if (groceryCountry.getText().toString().length()==0){
                    groceryCountry.setError(getString(R.string.enter_country));
                }else {
                    showProgressBar();
                    iAddGroceryPresenter.addData(groceryShopName.getText().toString(),groceryContactNo.getText().toString(),spinner.getSelectedItem().toString(),groceryItems.getText().toString(),"yes",groceryAddress.getText().toString(),groceryLandmark.getText().toString(),groceryCity.getText().toString(),groceryCountry.getText().toString(),grocery_image);
                }
            }
        });
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
    public void setData(AddGroceryModel model) {
        if (model!=null){
            if (model.getStatus().equalsIgnoreCase("Success")) {
                Intent intent = new Intent(this, GroceryActivity.class);
                startActivity(intent);
            } else if (model.getStatus().equalsIgnoreCase("Failed")) {
                Toast.makeText(this, model.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void getImage() {
        Dialog dialog = new Dialog(AddGroceryActivity.this, R.style.comm_dialog);
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
                    groceryImage.setImageURI(cameraURI);
                    plusImage.setVisibility(View.GONE);
                    groceryImage.setVisibility(View.VISIBLE);
                    addImageText.setVisibility(View.GONE);

                    try {
                        final InputStream imageStream = getContentResolver().openInputStream(cameraURI);
                        final Bitmap camera = BitmapFactory.decodeStream(imageStream);
                        grocery_image = encodeImage(camera);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

                break;
            case 1:
                if(resultCode == RESULT_OK){
                    Uri galleryURI = data.getData();
                    groceryImage.setImageURI(galleryURI);
                    plusImage.setVisibility(View.GONE);
                    groceryImage.setVisibility(View.VISIBLE);
                    addImageText.setVisibility(View.GONE);
                    try{
                        final InputStream imageStream = getContentResolver().openInputStream(galleryURI);
                        final Bitmap camera = BitmapFactory.decodeStream(imageStream);
                        grocery_image = encodeImage(camera);
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
        grocery_image = Base64.encodeToString(b, Base64.DEFAULT);

        return grocery_image;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
