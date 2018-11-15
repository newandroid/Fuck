package css.com.fuck.net;


import java.util.Map;

import css.com.fuck.net.result.BaseResult;
import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Administrator on 2016/8/16.
 */
public interface MRetrofitService {
    @FormUrlEncoded
    @POST("shangyun/device/unlock")
    Observable<BaseResult> unLock(@FieldMap Map<String, String> params);
}
