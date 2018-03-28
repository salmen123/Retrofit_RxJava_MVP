package tn.esprit.salmen.retrofitrxjavamvp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Salmen on 27/03/2018.
 */

public class Image {

    @SerializedName("#text")
    private String text;
    private String size;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
