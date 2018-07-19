package css.com.fuck.activites;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import css.com.fuck.adapter.CustomerExtendsListAdapter;
import css.com.fuck.view.CustomerExtendsListView;

/**
 * Created by css on 2018/7/9.
 */
public class EmptyActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListView listView = new CustomerExtendsListView(this);
        setContentView(listView);
        String keyMap = "content";
        List<Map<String,String>> datas = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Map<String,String> map = new HashMap<>();
            map.put(keyMap,String.valueOf(i));
            datas.add(map);
        }
        listView.setAdapter(new CustomerExtendsListAdapter(this,datas,android.R.layout.simple_list_item_1,new String[]{keyMap},new int[]{android.R.id.text1}));
    }
}
