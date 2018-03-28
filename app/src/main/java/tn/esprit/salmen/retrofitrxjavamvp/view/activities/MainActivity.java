package tn.esprit.salmen.retrofitrxjavamvp.view.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.List;

import tn.esprit.salmen.retrofitrxjavamvp.R;
import tn.esprit.salmen.retrofitrxjavamvp.adapters.AlbumAdapter;
import tn.esprit.salmen.retrofitrxjavamvp.model.Album;
import tn.esprit.salmen.retrofitrxjavamvp.presenter.TopAlbumsPresenter;
import tn.esprit.salmen.retrofitrxjavamvp.view.ITopAlbums;

public class MainActivity extends AppCompatActivity implements ITopAlbums {

    TopAlbumsPresenter topAlbumsPresenter;
    static String artist = "Cher";
    ListView listView;
    Button btnSearch;
    EditText etSearch;
    ProgressBar pbAlbum;
    LinearLayout llData;
    public static String TAG="AlbumSearch";

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.lv_albums);
        btnSearch = (Button) findViewById(R.id.btn_search);
        etSearch = (EditText) findViewById(R.id.et_search);
        pbAlbum = (ProgressBar) findViewById(R.id.progressBar);
        llData = (LinearLayout) findViewById(R.id.ll_nodata);

        this.topAlbumsPresenter = new TopAlbumsPresenter(this);

        topAlbumsPresenter.getTopAlbums(artist);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard();
                topAlbumsPresenter.getTopAlbums(etSearch.getText().toString());

            }
        });
    }

    @Override
    public void displayLoadingProgress() {
        hideKeyboard();
        pbAlbum.setVisibility(View.VISIBLE);
    }

    @Override
    public void displayError() {
        hideKeyboard();
        pbAlbum.setVisibility(View.GONE);
        llData.setVisibility(View.VISIBLE);
        listView.setVisibility(View.GONE);
        Log.e(this.TAG,"error");
    }

    @Override
    public void DismissLoadingProgress() {
        hideKeyboard();
        pbAlbum.setVisibility(View.GONE);
        llData.setVisibility(View.GONE);
        listView.setVisibility(View.VISIBLE);
        Log.d(this.TAG,"no loading");
    }

    @Override
    public void displayNoAlbum() {
        pbAlbum.setVisibility(View.GONE);
        hideKeyboard();
        Log.d(this.TAG,"no album");
    }

    @Override
    public void displayAlbums(final List<Album> albums) {
        listView.setAdapter(new AlbumAdapter(this, R.layout.item_album, albums));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(albums.get(position).getUrl()));
                startActivity(intent);
            }
        });
        hideKeyboard();
    }

    @Override
    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
