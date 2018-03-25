package com.example.khang.betapkechuyen.fragment;

import android.content.Context;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.khang.betapkechuyen.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;


public class GhiAmFragment extends Fragment {
    View view;
    FloatingActionButton fabGhiAm;
    boolean checkRecording = false;
    MediaRecorder recorder;
    StorageReference storageRef;
    String voicePath = "";

    public GhiAmFragment() {
        // Required empty public constructor
    }

    public static GhiAmFragment newInstance() {
        GhiAmFragment fragment = new GhiAmFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_ghi_am, container, false);
        fabGhiAm = view.findViewById(R.id.fabGhiAm);
        fabGhiAm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Stop recording
                if (checkRecording) {
                    stopRecording();
                    Toast.makeText(getContext(), "Stop Recording", Toast.LENGTH_LONG).show();
                    fabGhiAm.setImageResource(R.drawable.ic_keyboard_voice_black_24dp);
                    checkRecording = false;
                    return;
                }
                checkRecording = true;
                //Start recording
                startRecording();
                Toast.makeText(getContext(), "Start Recording", Toast.LENGTH_LONG).show();
                fabGhiAm.setImageResource(R.drawable.ic_stop_black_24dp);
            }
        });

       // FirebaseStorage storage = FirebaseStorage.getInstance();
      //  storageRef = storage.getReferenceFromUrl("gs://smarthome-5d11a.appspot.com");

        return view;
    }

    /*
    *
    * */
    public void startRecording() {
        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        recorder.setOutputFile(Environment.getExternalStorageDirectory()
                .getAbsolutePath() + "/myrecording.mp3");
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
        try {
            recorder.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        recorder.start();
        voicePath = Environment.getExternalStorageDirectory()
                .getAbsolutePath() + "/myrecording.mp3";
    }

    /*
    * */
    public void stopRecording() {
        recorder.stop();
        uploadImageToFirebase();
    }

    /*
    *
    * */
    public void uploadImageToFirebase() {
        Uri file = Uri.fromFile(new File(voicePath));
        StorageReference riversRef = storageRef.child("data/" + file.getLastPathSegment());

        UploadTask uploadTask;
        uploadTask = riversRef.putFile(file);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Toast.makeText(getContext(), "Error", Toast.LENGTH_LONG).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(getContext(), taskSnapshot.getMetadata().getDownloadUrl().toString(), Toast.LENGTH_LONG).show();

            }
        });
    }

}
