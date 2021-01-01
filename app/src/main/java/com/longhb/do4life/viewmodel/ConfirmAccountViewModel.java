package com.longhb.do4life.viewmodel;

import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.longhb.do4life.model.retrofit.json.JsonUpdateCMND;
import com.longhb.do4life.network.RetrofitModule;

import java.io.ByteArrayOutputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConfirmAccountViewModel extends ViewModel {
    private FirebaseStorage storage;
    private StorageReference storageRef;

    public ConfirmAccountViewModel() {
        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();
    }


    public void updateAccount(JsonUpdateCMND jsonUpdateCMND, UpdateAccountEvent callback) {
        RetrofitModule.getInstance().updateAccount(jsonUpdateCMND).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful()){
                    callback.onUpdateSuccess();
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                callback.onUpdateError();
            }
        });
    }

    public void uploadImage(Bitmap bitmap,UploadImageEvent callback) {
        String name="image"+System.currentTimeMillis()+".jpg";
        StorageReference mountainsRef = storageRef.child(name);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

        UploadTask uploadTask = mountainsRef.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
                exception.printStackTrace();
                Log.d("longhbb", "CommfimAccountViewModel | onFailure: Upload image error");
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                // ...
                Log.d("longhbb", "CommfimAccountViewModel | onSuccess: upload image success");
            }
        });


         uploadTask.continueWithTask(task -> {
             if (!task.isSuccessful()) {
                 throw task.getException();
             }

             // Continue with the task to get the download URL
             return mountainsRef.getDownloadUrl();
         }).addOnCompleteListener(task -> {
             if (task.isSuccessful()) {
                 Uri downloadUri = task.getResult();
                 Log.d("longhbb", "ConfirmAccountViewModel | onComplete: "+downloadUri.toString());
                 callback.onUploadSuccess(downloadUri.toString());
             } else {

             }
         });

    }


  public   interface UpdateAccountEvent {
        void onUpdateSuccess();
        void onUpdateError();
    }
   public interface UploadImageEvent {
        void onUploadSuccess(String url);
    }

}
