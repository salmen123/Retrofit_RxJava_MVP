package tn.esprit.salmen.retrofitrxjavamvp.services;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import tn.esprit.salmen.retrofitrxjavamvp.model.TopAlbumResponse;

/**
 * Created by Salmen on 27/03/2018.
 */

public interface ITopAlbumsService {

    @GET("?method=artist.gettopalbums&format=json")
    Observable<TopAlbumResponse> getTopAlbums(@Query("artist") String artist, @Query("api_key") String api_key);

}
