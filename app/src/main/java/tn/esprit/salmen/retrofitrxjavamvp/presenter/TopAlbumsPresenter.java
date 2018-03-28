package tn.esprit.salmen.retrofitrxjavamvp.presenter;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import tn.esprit.salmen.retrofitrxjavamvp.model.Album;
import tn.esprit.salmen.retrofitrxjavamvp.model.TopAlbumResponse;
import tn.esprit.salmen.retrofitrxjavamvp.services.MyService;
import tn.esprit.salmen.retrofitrxjavamvp.utils.Constants;
import tn.esprit.salmen.retrofitrxjavamvp.view.ITopAlbums;
import tn.esprit.salmen.retrofitrxjavamvp.view.activities.MainActivity;

/**
 * Created by Salmen on 27/03/2018.
 */

public class TopAlbumsPresenter {

    ITopAlbums view;
    CompositeDisposable subscriptions = new CompositeDisposable();
    public TopAlbumsPresenter(ITopAlbums view){
        this.view= view;
    }

    public void getTopAlbums(String artist){
        view.displayLoadingProgress();
        this.subscriptions.add(
                MyService.getAlbumRetrofit().getTopAlbums(artist, Constants.API_KEY).map(new Function<TopAlbumResponse, List<Album>>() {
                    @Override
                    public List<Album> apply(TopAlbumResponse topAlbumResponse) throws Exception {
                        if(topAlbumResponse != null&&topAlbumResponse.getTopalbums().getAlbum()!=null){
                            return topAlbumResponse.getTopalbums().getAlbum();
                        }
                        return new ArrayList<Album>();
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io()).subscribeWith(
                        new DisposableObserver<List<Album>>(){
                            @Override
                            public void onNext(List<Album> albums) {
                                if(albums.isEmpty()){
                                    view.displayNoAlbum();
                                }else{
                                    view.displayAlbums(albums);
                                }
                            }
                            @Override
                            public void onError(Throwable e) {
                                Log.e("presenter",e.getMessage());
                                view.displayError();
                            }

                            @Override
                            public void onComplete() {
                                view.DismissLoadingProgress();
                            }
                        }
                )
        );
    }
    public void unsubscribe(){
        subscriptions.clear();
    }

}
