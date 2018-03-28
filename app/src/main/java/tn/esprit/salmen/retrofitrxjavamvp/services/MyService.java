package tn.esprit.salmen.retrofitrxjavamvp.services;

import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import tn.esprit.salmen.retrofitrxjavamvp.utils.Constants;

/**
 * Created by Salmen on 27/03/2018.
 */

public class MyService {

    public static ITopAlbumsService getAlbumRetrofit(){

        RxJava2CallAdapterFactory rxAdapter = RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io());

        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addCallAdapterFactory(rxAdapter)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ITopAlbumsService.class);

    }

}
