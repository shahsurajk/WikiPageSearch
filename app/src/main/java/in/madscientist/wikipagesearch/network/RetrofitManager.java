package in.madscientist.wikipagesearch.network;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by frapp on 5/8/17.
 */

public class RetrofitManager {

    public static Retrofit getInstance(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        Log.d(RetrofitManager.class.getCanonicalName().toString(),
                                "intercept: "+request.method()+" url: "+request.url());
                        return chain.proceed(request);
                    }
                }).build();
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(URL.BASE_URL)
                .client(okHttpClient)
                .build();
        return retrofit;
    }
}
