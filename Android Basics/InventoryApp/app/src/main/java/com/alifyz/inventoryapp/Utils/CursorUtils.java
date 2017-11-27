package com.alifyz.inventoryapp.Utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import com.alifyz.inventoryapp.ProductDetailsActivity;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Alifyz on 11/27/2017.
 */

public class CursorUtils {

    //Code based on: Carlos Jimenez (@crlsndrsjmnz)
    //https://github.com/crlsndrsjmnz/MyShareImageExample
    public static Bitmap getBitmapFromUri(Uri uri, Context context, ImageView mProductImage) {

        if (uri == null || uri.toString().isEmpty())
            return null;
        int targetW = mProductImage.getWidth();
        int targetH = mProductImage.getHeight();

        InputStream input = null;
        try {
            input = context.getContentResolver().openInputStream(uri);
            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
            bmOptions.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(input, null, bmOptions);
            input.close();

            int photoW = bmOptions.outWidth;
            int photoH = bmOptions.outHeight;

            // Determine how much to scale down the image
            int scaleFactor = Math.min(photoW / targetW, photoH / targetH);

            // Decode the image file into a Bitmap sized to fill the View
            bmOptions.inJustDecodeBounds = false;
            bmOptions.inSampleSize = scaleFactor;
            bmOptions.inPurgeable = true;

            input = context.getContentResolver().openInputStream(uri);
            Bitmap bitmap = BitmapFactory.decodeStream(input, null, bmOptions);
            input.close();
            return bitmap;

        } catch (FileNotFoundException fne) {
            Log.e("Product details", "Failed to load image.", fne);
            return null;
        } catch (Exception e) {
            Log.e("product Details", "Failed to load image.", e);
            return null;
        } finally {
            try {
                input.close();
            } catch (IOException ioe) {}
        }
    }


}
