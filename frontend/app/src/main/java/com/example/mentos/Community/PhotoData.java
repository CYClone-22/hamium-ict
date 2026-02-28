package com.example.mentos.Community;

import android.net.Uri;

public class PhotoData {
    int image;
    Uri imageUri;

    public PhotoData(int image, Uri imageUri) {
        this.image = image;
        this.imageUri = imageUri;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public Uri getImageUri() {
        return imageUri;
    }

    public void setImageUri(Uri imageUri) {
        this.imageUri = imageUri;
    }
}
