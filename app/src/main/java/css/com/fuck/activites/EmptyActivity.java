package css.com.fuck.activites;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import css.com.fuck.R;
import css.com.fuck.view.TableConstraintLayout;

/**
 * Created by css on 2018/7/9.
 */
public class EmptyActivity extends AppCompatActivity {
    private ArrayList<String> fakeData  = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivyt_empty);
//        MyBaseAdapter adapter   = new MyBaseAdapter();
//        new ListView(this).setAdapter(adapter);
        dolayout(getWindowManager().getDefaultDisplay());
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (hasFocus) {
        }
    }
    void dolayout(Display display){
        TableConstraintLayout view = findViewById(R.id.root);
        for (int i = 0; i < 15; i++) {
            fakeData.add(""+i);
        }

        view.setAdapter(new TableConstraintLayout.TableConstraintAdapter(){

            @Override
            public int getColumnCount() {
                return 3;
            }

            @Override
            public int getRowCount() {
                return 4;
            }

            @Override
            public int getCount() {
                return 10;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView textView;
                if (convertView==null){
                    textView = new TextView(EmptyActivity.this);
                }else {
                    textView = (TextView) convertView;
                }
                textView.setWidth(50);
                textView.setHeight(100);
                textView.setText(fakeData.get(position));
                return textView;
            }
        },display);
    }
}
