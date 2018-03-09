package css.com.fuck.utils;

import android.content.Context;
import android.text.InputFilter;
import android.text.Spanned;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by css on 2018/3/6.
 */

public class InputIllegalFilter implements InputFilter {
    private Context mContext;
    public InputIllegalFilter(Context mContext){
        this.mContext = mContext;
    }

    Pattern pattern = Pattern.compile("[^a-zA-Z0-9\\u4E00-\\u9FA5_,.?!:;…~\\-\"/@*+'()<>{}/[/]()<>{}\\[\\]=%&$|\\/♀♂#¥£¢€\"^` ，。？！：；……～“”、“（）”、（——）‘’＠‘·’＆＊＃《》￥《〈〉》〈＄〉［］￡［］｛｝｛｝￠【】【】％〖〗〖〗／〔〕〔〕＼『』『』＾「」「」｜﹁﹂｀．]");

    @Override
    public CharSequence filter(CharSequence charSequence, int i, int i1, Spanned spanned, int i2, int i3) {
        Matcher matcher=  pattern.matcher(charSequence);
        if(!matcher.find()){
            return null;
        }else{
            Toast.makeText(mContext, "非法字符！", Toast.LENGTH_SHORT).show();
            return "";
        }

    }
}
