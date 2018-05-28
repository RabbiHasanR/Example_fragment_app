package com.example.diu.bangladictionary;

public class Word {
    //declear state for word
    private String mDefaultTranslation;
    private String mBanglaTranslation;
    private int mImagesresourceId=NO_IMAGE_PROVIED;
    private int mAudioresourceId;

    private static final int NO_IMAGE_PROVIED=-1;

    //create constructor
    public Word(String mDefaultTranslation,String mBanglaTranslation,int mAudioresourceId){
        this.mDefaultTranslation=mDefaultTranslation;
        this.mBanglaTranslation=mBanglaTranslation;
        this.mAudioresourceId=mAudioresourceId;
    }

    //create second constructor
    public Word(String mDefaultTranslation,String mBanglaTranslation,int mImagesresourceId,int mAudioresourceId){
        this.mDefaultTranslation=mDefaultTranslation;
        this.mBanglaTranslation=mBanglaTranslation;
        this.mImagesresourceId=mImagesresourceId;
        this.mAudioresourceId=mAudioresourceId;

    }

    //get the default translation of the word


    public String getmDefaultTranslation() {
        return mDefaultTranslation;
    }

    //get the bangla translation of the word


    public String getmBanglaTranslation() {
        return mBanglaTranslation;
    }

    //get image resource id
    public int getmImagesResourceId(){
        return mImagesresourceId;
    }

    //check has image
    public boolean hasImag(){
        return mImagesresourceId!=NO_IMAGE_PROVIED;
    }

    public int getmAudioresourceId(){
        return mAudioresourceId;
    }

    @Override
    public String toString() {
        return "Word{" +
                "mDefaultTranslation='" + mDefaultTranslation + '\'' +
                ", mBanglaTranslation='" + mBanglaTranslation + '\'' +
                ", mImagesresourceId=" + mImagesresourceId +
                ", mAudioresourceId=" + mAudioresourceId +
                '}';
    }
}
