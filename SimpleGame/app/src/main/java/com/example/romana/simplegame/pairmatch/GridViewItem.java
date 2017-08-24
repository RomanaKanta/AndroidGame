package com.example.romana.simplegame.pairmatch;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Romana on 8/24/2017.
 */

public class GridViewItem  implements Parcelable {

    private static final String NEW_ITEM_TAG = "new_item";
    private static final String TEXT_TAG = "item_text";
    private static final String IMAGE_TAG = "item_image";

    private boolean mNewItem;
    private ItemFace mItemFace;
    private String mText;
    private int mImage;


    public enum ItemFace{
        FRONT,
        BACK
    }

    public GridViewItem(String text, int image){
        mNewItem = true;
        mText = text;
        mImage = image;
        mItemFace = ItemFace.FRONT;
    }

    private GridViewItem(Parcel in) {
        Bundle bundle = in.readBundle();
        mNewItem = bundle.getBoolean(NEW_ITEM_TAG);
        mText = bundle.getString(TEXT_TAG);
        mImage = bundle.getInt(IMAGE_TAG);
    }

    public int getmImage() {
        return mImage;
    }

    public void setmImage(int mImage) {
        this.mImage = mImage;
    }

    public ItemFace getFace(){
        return mItemFace;
    }

    public void setFace(ItemFace face){
        mItemFace = face;
    }

    public String getItemText(){
        return mText;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(NEW_ITEM_TAG, mNewItem);
        bundle.putString(TEXT_TAG, mText);
        bundle.putInt(IMAGE_TAG, mImage);
        dest.writeBundle(bundle);
    }

    public static final Parcelable.Creator<GridViewItem> CREATOR
            = new Parcelable.Creator<GridViewItem>() {
        public GridViewItem createFromParcel(Parcel in) {
            return new GridViewItem(in);
        }

        public GridViewItem[] newArray(int size) {
            return new GridViewItem[size];
        }
    };
}
