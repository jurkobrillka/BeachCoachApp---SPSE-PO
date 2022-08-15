package sk.upjs.ics.android.beachcoachapp;

import android.widget.ImageButton;

import java.io.Serializable;

public class ExampleItem {
    private int ImageResource;
    private String titleMeno;
    private String titlePriezvisko;
    private int ImageDelete;


    public ExampleItem(int imageResource, String titleMeno, String titlePriezvisko) {
        ImageResource = imageResource;
        this.titleMeno = titleMeno;
        this.titlePriezvisko = titlePriezvisko;
    }

    public ExampleItem(int imageResource, String titleMeno, String titlePriezvisko, int imageDeletel) {
        ImageResource = imageResource;
        this.titleMeno = titleMeno;
        this.titlePriezvisko = titlePriezvisko;
        ImageDelete = imageDeletel;
    }
    public void changeText(String text){
        titleMeno = text;
    }
    public void changeImage(int ImgRes){
        ImageResource = ImgRes;
    }



    public int getImageDelete() {
        return ImageDelete;
    }

    public void setImageDelete(int imageDelete) {
        ImageDelete = imageDelete;
    }

    public void goCreateNew(){

    }


    public int getImageResource() {
        return ImageResource;
    }

    public void setImageResource(int imageResource) {
        ImageResource = imageResource;
    }

    public String getTitleMeno() {
        return titleMeno;
    }

    public void setTitleMeno(String titleMeno) {
        this.titleMeno = titleMeno;
    }

    public String getTitlePriezvisko() {
        return titlePriezvisko;
    }

    public void setTitlePriezvisko(String titlePriezvisko) {
        this.titlePriezvisko = titlePriezvisko;
    }
}
