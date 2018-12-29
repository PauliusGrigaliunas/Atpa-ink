package pg.grigaliunas.paulius.atpaink10;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
public class DataAdapter extends ArrayAdapter <ImageObject> {

    Context context;
    int layoutResourceId;
    List<ImageObject> objects;

    public DataAdapter(Context context, int resource, List<ImageObject> objects) {
        super(context, resource, objects);

        this.context = context;
        layoutResourceId =  resource;
        this.objects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        DataHolder holder = null;

        if(convertView == null){

            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            convertView = inflater.inflate(layoutResourceId, parent);

            holder = new DataHolder();

            holder.countryName =(TextView) convertView.findViewById(R.id.textView3);
            holder.imageView =(ImageView) convertView.findViewById(R.id.imageView3);

            convertView.setTag(holder);
        }
        else
        {
            holder = (DataHolder) convertView.getTag();
        }

        ImageObject imageObject = objects.get(position);
        holder.countryName.setText(imageObject.getPrediction());
        holder.imageView.setImageBitmap(imageObject.getImage());

        return convertView;

    }



    static class DataHolder
    {
        ImageView imageView;
        TextView countryName;
    }
}
