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
import com.example.khang.betapkechuyen.model.Sentence;
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

        List<Sentence> haiChaConSentenceList = new ArrayList<>();

        haiChaConSentenceList.add(new Sentence("Một hôm hai cha con người nông  dân dắt một con lừa vào chợ. Họ định bán con lừa.", R.drawable.haichaconvaconlua_1, R.raw.haichaconlua_1));
        haiChaConSentenceList.add(new Sentence("Trên đường đi, họ gặp một vài người khách du lịch. Một người nói: Hai cha con nhà này thật ngu xuẩn, có lừa mà không biết cởi, cứ thế mà đi bộ", R.drawable.haichaconvaconlua_2, R.raw.haichaconlua_2));
        haiChaConSentenceList.add(new Sentence("Người cha nghe thấy thế, liền bảo con cởi lên lưng lừa. Một lát sau, họ gặp một cụ già trong công viên.", R.drawable.haichaconvaconlua_3, R.raw.haichaconlua_3));
        haiChaConSentenceList.add(new Sentence("Cụ già nhìn hai cha con rồi nói: Bọn thanh niên bây giờ không biết kính trọng người già gì cả. Thầng con lười biến thì ngồi chểnh chệ trên lưng lừa, còn ông bố già cả thì phải cuốc bộ", R.drawable.haichaconvaconlua_4, R.raw.haichaconlua_4));
        haiChaConSentenceList.add(new Sentence("Thế là người cha bảo con xuống để mình ngồi lên. Họ tiếp tục đi vào chợ, một lúc sau họ gặp mấy người phụ nữ", R.drawable.haichaconvaconlua_5, R.raw.haichaconlua_5));
        haiChaConSentenceList.add(new Sentence("Mấy người đó liền lớn tiếng mắng: Lão già vô dụng, sao ông có thể ung dung cởi lên lưng lừa, bắt con trẻ thở không ra hơi thế kia.", R.drawable.haichaconvaconlua_6, R.raw.haichaconlua_6));
        haiChaConSentenceList.add(new Sentence("Người cha hiền lành liền bảo con trai cởi lên lưng lừa. Đi được một lát, họ lại gặp mấy người nông dân khác. Một người trong số họ bảo: Anh xem lừa đã mệt đến thế kia rồi, hai người khiên nón chắc còn nhanh hơn là cởi đấy.  ", R.drawable.haichaconvaconlua_7, R.raw.haichaconlua_7));
        haiChaConSentenceList.add(new Sentence("Người bố đáp: Vậy thì anh nghe theo lời anh vậy. Người nông dân và đứa con cùng xuống lấy dây thừng buộc chân lừa lại. Sau đó dùng cây gậy dài khiêng nó đi.", R.drawable.haichaconvaconlua_7, R.raw.haichaconlua_9));
        haiChaConSentenceList.add(new Sentence("Lúc đi qua ngang một cây cầu. Nhiều người nhìn cười to và chế giễu hai cha con. Những lợi nhạo báng cũng những hành động kì quặc, khiến con lừa rất không hài lòng. Nó liền giãy tung dây thừng và ngã xuống sông", R.drawable.haichaconvaconlua_7, R.raw.haichaconlua_10));
        haiChaConSentenceList.add(new Sentence("Cuối cùng hai cha con không còn lừa để bán, họ thiêu thiểu đi về.", R.drawable.haichaconvaconlua_7, R.raw.haichaconlua_10));

        List<Sentence> ruaVaThoSentencenList = new ArrayList<>();
        ruaVaThoSentencenList.add(new Sentence("Ngày xửa ngày xưa trong một khu rừng nọ, có một con thỏ sống cùng với nhiều loài muôn thú khác.", R.drawable.ruavatho_1, R.raw.ruavatho_1));
        ruaVaThoSentencenList.add(new Sentence("Nhưng thỏ ta luôn tự hào về tài chạy nhanh của mình. Họ ta luôn tự kêu cho rằng chẳng ai có thể thắng nổi được mình.", R.drawable.ruavatho_2, R.raw.ruavatho_2));
        ruaVaThoSentencenList.add(new Sentence("Thỏ đi thách đấu với các muôn thú khác, nhưng chẳng ai chịu tỉ thí với nó. Cuối cùng thỏ gặp rùa, thỏ ta liền thách đấu", R.drawable.ruavatho_3, R.raw.ruavatho_3));
        ruaVaThoSentencenList.add(new Sentence("Anh rùa, anh có dám thi đấu với tôi không. Vừa hỏi, thỏ vừa nghĩ bụng, ở rừng này ai chạy chậm hơn rùa cơ chứ. Nếu thi chạy với rùa, chắc chắn ta sẽ thắng.", R.drawable.ruavatho_4, R.raw.ruavatho_4));
        ruaVaThoSentencenList.add(new Sentence("Rùa ta bình thản trả lời: Được thôi, tôi nghe danh anh đã lâu, nay chúng ta đua thử, xem ai chạy nhanh hơn", R.drawable.ruavatho_5, R.raw.ruavatho_5));
        ruaVaThoSentencenList.add(new Sentence("Các muôn thú nghe vậy, vui mừng như có lễ hội. Chúng loan tin báo cho nhau để cùng đến xem cuộc thi đấu", R.drawable.ruavatho_6, R.raw.ruavatho_6));
        ruaVaThoSentencenList.add(new Sentence("Cuộc thi bắt đầu, thỏ ta nhanh chân phóng như tên lửa về phía trước. Trong khi đó, rùa ta vẫn từ từ đi từng bước một trên đường đua", R.drawable.ruavatho_7, R.raw.ruavatho_7));
        ruaVaThoSentencenList.add(new Sentence("Chạy được một lúc, thỏ ta nhìn mãi nhưng không thấy rùa đâu. Thỏ ta liền nghĩ chân dưới một gốc cây lớn. Trong khi đó, rùa vẫn từ từ lê từng bước một trên đường đua", R.drawable.ruavatho_8, R.raw.ruavatho_8));
        ruaVaThoSentencenList.add(new Sentence("Ngồi một lúc, vẫn chưa thấy rùa đi qua, thỏ ta liền nghĩ bụng: Chắc lão rùa còn lâu mới tới, ta tranh thủ đánh một giấc, thức dậy chạy theo vẫn còn kịp chán", R.drawable.ruavatho_9, R.raw.ruavatho_9));
        ruaVaThoSentencenList.add(new Sentence("Thế là thỏ ta ngủ no say, ngủ quên cho đến khi rùa đi qua mà vẫn không hề hay biết. Khi thức dậy thỏ ta không thấy rùa đâu, chột dạ thỏ ta liền ba chân bốn cẵng chạy về phía đích", R.drawable.ruavatho_10, R.raw.ruavatho_10));
        ruaVaThoSentencenList.add(new Sentence("Nhưng không kịp nữa rồi, rùa đã về đến đích muôn thú reo hò chúc mừng rùa chiến thắng, còn thỏ taa tiêu nghiễu vì đã thua cuộc", R.drawable.ruavatho_11, R.raw.ruavatho_11));

        storyList.add(new Story("Một hôm hai cha con người nông dân dắt một con lừa vào chợ. Họ định bán con lừa...", R.drawable.haichaconvaconlua_cover_thumbnail, "Hai cha con và con lừa", haiChaConSentenceList));
        storyList.add(new Story("Ngày xửa ngày xưa trong một khu rừng nọ, có một con thỏ sống cùng...", R.drawable.rua_va_tho, "Rùa và thỏ", ruaVaThoSentencenList));
        storyList.add(new Story("Ngày xưa, ở một ngôi làng nọ có một cậu bé chăn cừu...", R.drawable.chubechancuu_cover_thumbnail, "Chú bé chăn cừu", haiChaConSentenceList));
        storyList.add(new Story("Một hôm, Cáo thấy đói tới mức bụng sôi lên ùng ục, nó bèn mò ra khỏi hang để đi tìm thức ăn...", R.drawable.caovaqua_cover_thumbnail, "Cáo và quạ", ruaVaThoSentencenList));
        storyList.add(new Story("Một hôm hai cha con người nông dân dắt một con lừa vào chợ. Họ định bán con lừa...", R.drawable.rua_va_tho, "Hai cha con và con lừa", haiChaConSentenceList));
        storyList.add(new Story("Một hôm hai cha con người nông dân dắt một con lừa vào chợ. Họ định bán con lừa...", R.drawable.rua_va_tho, "Rùa và thỏ", ruaVaThoSentencenList));

        lvTruyenCuaBe = view.findViewById(R.id.lvTruyenCuaBe);
        truyenCuaBeAdapter = new TruyenCuaBeAdapter(getContext(), R.layout.list_truyen_cua_be, storyList);
        lvTruyenCuaBe.setAdapter(truyenCuaBeAdapter);

        //init event
        lvTruyenCuaBe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getContext(), TruyenCuaBeActivity.class);
                intent.putExtra("STORY", storyList.get(i));
                startActivity(intent);
            }
        });

    }
}
