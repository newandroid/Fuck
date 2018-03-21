package css.com.fuck.utils;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;


/**
 * Created by css on 2018-03-22.
 */

public class LayoutInflaterUtil {
    public static View inflateFillContainView(LayoutInflater inflater, @Nullable ViewGroup container, @LayoutRes int layoutRes) {
        if (container == null) {
            RelativeLayout containers = new RelativeLayout(inflater.getContext());
            RelativeLayout.LayoutParams lp =
                    new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                            RelativeLayout.LayoutParams.WRAP_CONTENT);
            containers.setLayoutParams(lp);
            return inflater.inflate(layoutRes,containers,true);
        }
        return inflater.inflate(layoutRes,container,false);
    }
}
