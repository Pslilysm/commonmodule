package pers.cxd.commonmodule.network;

import android.util.Log;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import pers.cxd.baselibrary.SingletonManager;
import pers.cxd.networklibrary.BaseHttpClient;
import pers.cxd.networklibrary.BuildConfig;
import retrofit2.Converter;
import retrofit2.converter.gson.GsonConverterFactory;

public class DemoHttpClient extends BaseHttpClient<DemoApiInterface> {

    public static DemoHttpClient get(){
        return SingletonManager.getInstance(DemoHttpClient.class);
    }

    private DemoHttpClient(){}

    @Override
    protected Class<?> getApiInterfaceClass() {
        return DemoApiInterface.class;
    }

    @Override
    protected String getBaseUrl() {
        return "http://www.your.domin.com/";
    }

    @Override
    protected Converter.Factory[] getConvertFactories() {
        return new Converter.Factory[]{GsonConverterFactory.create()};
    }

    @Override
    protected OkHttpClient createHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(20, TimeUnit.SECONDS);
        builder.retryOnConnectionFailure(true);
        if (BuildConfig.DEBUG){
            builder.addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                String TAG = DemoHttpClient.class.getSimpleName();
                @Override
                public void log(@NotNull String s) {
                    Log.d(TAG, s);
                }
            }).setLevel(HttpLoggingInterceptor.Level.BODY));
        }
        return builder.build();
    }
}