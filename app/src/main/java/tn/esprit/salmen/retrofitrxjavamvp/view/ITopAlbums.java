package tn.esprit.salmen.retrofitrxjavamvp.view;

import java.util.List;

import tn.esprit.salmen.retrofitrxjavamvp.model.Album;

/**
 * Created by Salmen on 27/03/2018.
 */

public interface ITopAlbums {
    void displayLoadingProgress();

    void displayError();

    void DismissLoadingProgress();

    void displayNoAlbum();

    void displayAlbums(List<Album> albums);

    public void hideKeyboard();
}
