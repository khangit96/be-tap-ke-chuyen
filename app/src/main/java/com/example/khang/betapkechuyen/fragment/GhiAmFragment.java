package com.example.khang.betapkechuyen.fragment;

import android.app.ProgressDialog;
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
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.khang.betapkechuyen.R;
import com.example.khang.betapkechuyen.adapter.GhiAmAdapter;
import com.example.khang.betapkechuyen.model.GhiAm;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


public class GhiAmFragment extends Fragment {
    View view;
    FloatingActionButton fabGhiAm;
    boolean checkRecording = false;
    MediaRecorder recorder;
    StorageReference storageRef;
    String voicePath = "";
    ProgressDialog mP;
    String fileName = "";
    ListView lvGhiAm;
    GhiAmAdapter ghiAmAdapter;
    ArrayList<GhiAm> ghiAmList;

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
                fileName = randomString() + ".mp3";
                //Start recording
                startRecording();
                Toast.makeText(getContext(), "Start Recording", Toast.LENGTH_LONG).show();
                fabGhiAm.setImageResource(R.drawable.ic_stop_black_24dp);
            }
        });

        FirebaseStorage storage = FirebaseStorage.getInstance();
        storageRef = storage.getReferenceFromUrl("gs://smarthome-5d11a.appspot.com");

        mP = new ProgressDialog(getContext());

        lvGhiAm = view.findViewById(R.id.lvGhiAm);
        ghiAmList = new ArrayList<>();
        ghiAmAdapter = new GhiAmAdapter(getContext(), R.layout.list_ghi_am, ghiAmList);
        lvGhiAm.setAdapter(ghiAmAdapter);
        return view;
    }

    /*
    *
    * */
    protected String randomString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }

    /*
    *
    * */
    public void startRecording() {
        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        recorder.setOutputFile(Environment.getExternalStorageDirectory()
                .getAbsolutePath() + "/" + fileName);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
        try {
            recorder.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        recorder.start();
        voicePath = Environment.getExternalStorageDirectory()
                .getAbsolutePath() + "/" + fileName;
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
        mP.setMessage("Đang xử lý dữ liệu...");
        mP.setCanceledOnTouchOutside(false);
        mP.show();

        Uri file = Uri.fromFile(new File(voicePath));
        StorageReference riversRef = storageRef.child("" + file.getLastPathSegment());

        UploadTask uploadTask;
        uploadTask = riversRef.putFile(file);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Toast.makeText(getContext(), exception.toString(), Toast.LENGTH_LONG).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                mP.dismiss();

                GhiAm ghiAm = new GhiAm(taskSnapshot.getMetadata().getDownloadUrl().toString(), fileName);
                ghiAmList.add(ghiAm);
                ghiAmAdapter.notifyDataSetChanged();

                DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
                mDatabase.child("BeTapKeChuyen/DanhSachGhiAm").push().setValue(ghiAm);

                Toast.makeText(getContext(), taskSnapshot.getMetadata().getDownloadUrl().toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

}
