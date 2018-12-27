package pg.grigaliunas.paulius.atpaink10;

import android.graphics.Bitmap;

public class ImageObject {

    private String ID;
    private long date;
    private int prediction;
    private Bitmap image;

    public ImageObject(long date, int prediction, Bitmap image){
        this.date = date;
        this.prediction = prediction;
        this.image = image;
    }

    public void setID(String ID){
        this.ID = ID;
    }

    public String getID(){
        return ID;
    }

    public long getDate(){
        return date;
    }
    public int getPrediction(){
        return prediction;
    }
    public Bitmap getImage(){
        return image;
    }
}
