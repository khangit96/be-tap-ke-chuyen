package com.example.khang.betapkechuyen.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.khang.betapkechuyen.R;
import com.example.khang.betapkechuyen.activity.TruyenCuaBeActivity;
import com.example.khang.betapkechuyen.model.GhiAm;
import com.example.khang.betapkechuyen.model.Sentence;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

/**
 * Created by khang on 22/02/2018.
 */

public class GhiAmAdapter extends ArrayAdapter<GhiAm> {
    Context context;
    List<GhiAm> ghiAmList;
    int layout;

    public GhiAmAdapter(@NonNull Context context, int layout, List<GhiAm> ghiAmList) {
        super(context, layout, ghiAmList);
        this.context = context;
        this.ghiAmList = ghiAmList;
        this.layout = layout;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        convertView = inflater.inflate(layout, parent, false);

        final GhiAm ghiAm = ghiAmList.get(position);

        TextView tvGhiAm = convertView.findViewById(R.id.tvGhiAm);

        tvGhiAm.setText("Ghi âm " + String.valueOf(position + 1));

        Button btPhat = convertView.findViewById(R.id.btPhat);
        btPhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
                mDatabase.child("BeTapKeChuyen/LastGhiAm").setValue(ghiAm);
            }
        });

        return convertView;
    }
}
