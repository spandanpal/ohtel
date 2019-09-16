package com.ohtel.ohtel.screens.screens.addhotel;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
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
import com.ohtel.ohtel.screens.screens.addhotel.model.AddHotelModel;
import com.ohtel.ohtel.screens.screens.hotelforsale.HotelForSaleActivity;
import com.ohtel.ohtel.utils.CommonUtils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AddHotelActivity extends BaseActivity implements IAddHotelView, AdapterView.OnItemSelectedListener {

    @Bind(R.id.image_back_icon_id)
    AppCompatImageView backIcon;
    @Bind(R.id.toolbar_menu_left)
    AppCompatImageView toolBarMenu;

    @Bind(R.id.et_hotel_name)
    AppCompatEditText hotelName;
    @Bind(R.id.et_hotel_email_id)
    AppCompatEditText emailId;
    @Bind(R.id.et_hotel_contact_no)
    AppCompatEditText contactNo;
    @Bind(R.id.et_hotel_altr_no)
    AppCompatEditText altrNo;
    @Bind(R.id.et_no_rooms)
    AppCompatEditText noRooms;
    @Bind(R.id.et_no_dulex_rooom)
    AppCompatEditText noDulexRoom;
    @Bind(R.id.et_no_of_suit_rooms)
    AppCompatEditText noSuitRooms;
    @Bind(R.id.et_hotel_location)
    AppCompatEditText location;
    @Bind(R.id.et_ac_rooms)
    AppCompatEditText acRooms;
    @Bind(R.id.et_hotel_address)
    AppCompatEditText address;
    @Bind(R.id.furnished_type_spinner)
    Spinner furnishedTypeSpinnner;
    @Bind(R.id.hotel_built_up_spinner)
    Spinner builtUpSpinnner;
    @Bind(R.id.hotel_lift_spinner)
    Spinner liftSpinnner;
    @Bind(R.id.hotel_gym_spinner)
    Spinner gymSpinnner;
    @Bind(R.id.hotel_wifi_spinner)
    Spinner wifiSpinnner;
    @Bind(R.id.btn_add_hotel)
    AppCompatButton btnAddHotel;
    @Bind(R.id.add_image_text)
    AppCompatTextView addImageText;
    @Bind(R.id.hotel_image)
    AppCompatImageView hotelImage;
    @Bind(R.id.hotel_plus_image)
    AppCompatImageView hotelPlusImage;

    private IAddHotelPresenter iAddHotelPresenter;
    private String strImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_add_hotel,rl_base_container);
        setTitle("Add Hotel");
        ButterKnife.bind(this);
        toolBarMenu.setVisibility(View.GONE);
        backIcon.setVisibility(View.VISIBLE);
        iAddHotelPresenter = new AddHotelPresenter(this);
        initFurnishedTypeSpinner();
        initBuiltUpAreaSpinner();
        initLiftSpinner();
        initGymSpinner();
        initWifiSpinner();
        hotelPlusImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getImage();
            }
        });
        btnAddHotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (hotelName.getText().toString().length()==0){
                    hotelName.setError(getString(R.string.pls_enter_hotel_name));
                }else if (emailId.getText().toString().length()==0){
                    emailId.setError(getString(R.string.pls_enter_email));
                }else if (contactNo.getText().toString().length()<10){
                    contactNo.setError(getString(R.string.pls_enter_contact_number));
                }else if (altrNo.getText().toString().length()==0){
                    altrNo.setError(getString(R.string.pls_altrno));
                }else if (noRooms.getText().toString().length()==0){
                    noRooms.setError(getString(R.string.pls_enter_no_rooms));
                }else if (noDulexRoom.getText().toString().length()==0){
                    noDulexRoom.setError(getString(R.string.pls_enter_no_dulex_room));
                }else if (noSuitRooms.getText().toString().length()==0){
                    noSuitRooms.setError(getString(R.string.pls_enter_suit_room));
                }else if (location.getText().toString().length()==0){
                    location.setError(getString(R.string.pls_enter_location));
                }else if (acRooms.getText().toString().length()==0){
                    acRooms.setError(getString(R.string.pls_enter_ac_rooms));
                }else if (address.getText().toString().length()==0){
                    address.setError(getString(R.string.pls_enter_address));
                }else {
                    callAPI();
                }
            }
        });
    }

    private void initWifiSpinner() {
        String[] wifiType = {"Yes","No"};
        wifiSpinnner.setOnItemSelectedListener(this);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, wifiType);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        wifiSpinnner.setAdapter(arrayAdapter);
    }

    private void initGymSpinner() {
        String[] gymType = {"Yes","No"};
        gymSpinnner.setOnItemSelectedListener(this);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, gymType);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gymSpinnner.setAdapter(arrayAdapter);
    }

    private void initLiftSpinner() {
        String[] lift = {"Yes","No"};
        liftSpinnner.setOnItemSelectedListener(this);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, lift);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        liftSpinnner.setAdapter(arrayAdapter);
    }

    private void initBuiltUpAreaSpinner() {
        String[] builtUpArea = {"1200 sq.ft","1800 sq.ft","2000 sq.ft","2400 sq.ft"};
        builtUpSpinnner.setOnItemSelectedListener(this);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, builtUpArea);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        builtUpSpinnner.setAdapter(arrayAdapter);
    }

    private void initFurnishedTypeSpinner() {
        String[] furnishedType = {"Semi-Furnished","Furnished"};
        furnishedTypeSpinnner.setOnItemSelectedListener(this);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, furnishedType);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        furnishedTypeSpinnner.setAdapter(arrayAdapter);
    }


    private void getImage() {
        Dialog dialog = new Dialog(AddHotelActivity.this, R.style.comm_dialog);
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
                    hotelImage.setImageURI(cameraURI);
                    hotelPlusImage.setVisibility(View.GONE);
                    hotelImage.setVisibility(View.VISIBLE);
                    addImageText.setVisibility(View.GONE);

                    try {
                        final InputStream imageStream = getContentResolver().openInputStream(cameraURI);
                        final Bitmap camera = BitmapFactory.decodeStream(imageStream);
                        strImage = encodeImage(camera);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

                break;
            case 1:
                if(resultCode == RESULT_OK){
                    Uri galleryURI = data.getData();
                    hotelImage.setImageURI(galleryURI);
                    hotelPlusImage.setVisibility(View.GONE);
                    hotelImage.setVisibility(View.VISIBLE);
                    addImageText.setVisibility(View.GONE);
                    try{
                        final InputStream imageStream = getContentResolver().openInputStream(galleryURI);
                        final Bitmap camera = BitmapFactory.decodeStream(imageStream);
                        strImage = encodeImage(camera);
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
        strImage = Base64.encodeToString(b, Base64.DEFAULT);

        return strImage;
    }


    private void callAPI() {
        showProgressBar();
        iAddHotelPresenter.getHotelDetails(hotelName.getText().toString(),contactNo.getText().toString(),altrNo.getText().toString(),location.getText().toString(),
                address.getText().toString(),emailId.getText().toString(),noRooms.getText().toString(),noDulexRoom.getText().toString(),
                noSuitRooms.getText().toString(),furnishedTypeSpinnner.getSelectedItem().toString(),acRooms.getText().toString(),builtUpSpinnner.getSelectedItem().toString(),
                liftSpinnner.getSelectedItem().toString(),gymSpinnner.getSelectedItem().toString(),wifiSpinnner.getSelectedItem().toString(),strImage);
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
    public void addHotelData(AddHotelModel model) {
        if(model!=null) {
            if (model.getStatus().equalsIgnoreCase("Success")) {
                Intent intent = new Intent(this, HotelForSaleActivity.class);
                startActivity(intent);
            } else if (model.getStatus().equalsIgnoreCase("Failed")) {
                Toast.makeText(this, model.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(this,"Something went wrong!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
