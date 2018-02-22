package com.example.khang.betapkechuyen.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.khang.betapkechuyen.R;
import com.example.khang.betapkechuyen.activity.TruyenCuaBeActivity;
import com.example.khang.betapkechuyen.adapter.TruyenCuaBeAdapter;
import com.example.khang.betapkechuyen.model.Story;

import java.util.ArrayList;
import java.util.List;


public class TruyenCuaBeFragment extends Fragment {
    View view;
    ListView lvTruyenCuaBe;
    TruyenCuaBeAdapter truyenCuaBeAdapter;
    List<Story> storyList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_truyen_cua_be, container, false);
        init();
        return view;
    }

    private void init() {
        storyList = new ArrayList<>();
        storyList.add(new Story("Một hôm hai cha con người nông dân dắt một con lừa vào chợ. Họ định bán con lừa...",R.drawable.haichaconvaconlua_cover_thumbnail, "Hai cha con và con lừa"));
        storyList.add(new Story("Một hôm hai cha con người nông dân dắt một con lừa vào chợ. Họ định bán con lừa...",R.drawable.rua_va_tho, "Rùa và thỏ"));
        storyList.add(new Story("Một hôm hai cha con người nông dân dắt một con lừa vào chợ. Họ định bán con lừa...", R.drawable.caovaqua_cover_thumbnail, "Cáo và quạ"));
        storyList.add(new Story("Một hôm hai cha con người nông dân dắt một con lừa vào chợ. Họ định bán con lừa...",R.drawable.chubechancuu_cover_thumbnail, "Chú bé chăn cừu"));
        storyList.add(new Story("Một hôm hai cha con người nông dân dắt một con lừa vào chợ. Họ định bán con lừa...",R.drawable.haichaconvaconlua_cover_thumbnail, "Hai cha con và con lừa"));
        storyList.add(new Story("Một hôm hai cha con người nông dân dắt một con lừa vào chợ. Họ định bán con lừa...",R.drawable.rua_va_tho, "Rùa và thỏ"));

        lvTruyenCuaBe = view.findViewById(R.id.lvTruyenCuaBe);
        truyenCuaBeAdapter = new TruyenCuaBeAdapter(getContext(), R.layout.list_truyen_cua_be, storyList);
        lvTruyenCuaBe.setAdapter(truyenCuaBeAdapter);

        //init event
        lvTruyenCuaBe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(getContext(), TruyenCuaBeActivity.class);
                intent.putExtra("STORY",storyList.get(i));
                startActivity(intent);
             }
        });

    }
}
