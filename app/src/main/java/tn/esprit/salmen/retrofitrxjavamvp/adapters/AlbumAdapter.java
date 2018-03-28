package tn.esprit.salmen.retrofitrxjavamvp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import tn.esprit.salmen.retrofitrxjavamvp.R;
import tn.esprit.salmen.retrofitrxjavamvp.model.Album;

/**
 * Created by Salmen on 27/03/2018.
 */

public class AlbumAdapter extends ArrayAdapter<Album> {

    private int resourceId = 0;
    private LayoutInflater inflater;
    public Context mContext;


    public AlbumAdapter(@NonNull Context context, int resource, @NonNull List<Album> objects) {
        super(context, resource, objects);
        this.resourceId = resource;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mContext = context;
    }

    static class AlbumHolder {
        public ImageView imgAlbum;
        public TextView nameAlbum, playcountAlbum, artistNameAlbum;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View rowView = convertView;
        AlbumAdapter.AlbumHolder albumHolder = new AlbumAdapter.AlbumHolder();

        if (rowView == null){
            rowView = inflater.inflate(resourceId, parent, false);
            albumHolder.imgAlbum = (ImageView) rowView.findViewById(R.id.img_album);
            albumHolder.nameAlbum = (TextView) rowView.findViewById(R.id.tv_album_name);
            albumHolder.playcountAlbum = (TextView) rowView.findViewById(R.id.tv_album_playcount);
            albumHolder.artistNameAlbum = (TextView) rowView.findViewById(R.id.tv_artist_name);

            rowView.setTag(albumHolder);
        }
        else {
            albumHolder = (AlbumAdapter.AlbumHolder) rowView.getTag();
        }
        Album album = getItem(position);

        Glide.with(mContext).load(album.getImage().get(2).getText()).centerCrop().placeholder(R.drawable.placeholder).into(albumHolder.imgAlbum);
        albumHolder.nameAlbum.setText(album.getName());
        albumHolder.playcountAlbum.setText(String.valueOf(album.getPlaycount()));
        albumHolder.artistNameAlbum.setText(album.getArtist().getName());
        return rowView;
    }
}
