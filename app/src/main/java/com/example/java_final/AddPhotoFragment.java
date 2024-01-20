package com.example.java_final;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AddPhotoFragment extends Fragment {

     private static final int REQUEST_IMAGE_CAPTURE = 1;
     ImageView imageView;
     Button captureButton, uploadButton;
     Bitmap imageBitmap;
     Spinner labelSpinner;
     FirebaseAuth mAuth;
     DatabaseReference databaseReference;
     StorageReference storageReference;

    public AddPhotoFragment() {
        // Gerekli boş kurucu metodu
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_photo, container, false);

        imageView = view.findViewById(R.id.imageView);
        captureButton = view.findViewById(R.id.captureButton);
        uploadButton = view.findViewById(R.id.uploadButton);
        labelSpinner = view.findViewById(R.id.labelSpinner);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("users").child(currentUser.getUid());
        storageReference = FirebaseStorage.getInstance().getReference();

        // Etiketleri Spinner'a ekle
        List<String> labels = new ArrayList<>();
        labels.add("Label 1");
        labels.add("Label 2");
        labels.add("Label 3");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_dropdown_item, labels);
        labelSpinner.setAdapter(adapter);

        captureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });

        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImageToFirebase();
            }
        });

        return view;
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(requireActivity().getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == getActivity().RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imageBitmap);
        }
    }

    private void uploadImageToFirebase() {
        if (imageBitmap != null) {
            // Bitmap'i byte dizisine dönüştür
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] imageData = baos.toByteArray();

            // Seçilen etiketi al
            String selectedLabel = labelSpinner.getSelectedItem().toString();

            // Firebase Storage'a yükleme işlemi
            StorageReference imageRef = storageReference.child("images/" + selectedLabel + "_image.jpg");
            imageRef.putBytes(imageData);

            // Firebase Realtime Database'e etiket ve fotoğraf URL'sini kaydetme işlemi
            String photoUrl = "images/" + selectedLabel + "_image.jpg";
            String userId = mAuth.getCurrentUser().getUid();
            String key = databaseReference.child("photos").push().getKey();
            Photo photo = new Photo(selectedLabel, photoUrl);

            databaseReference.child("photos").child(key).setValue(photo);
        }
    }

}
