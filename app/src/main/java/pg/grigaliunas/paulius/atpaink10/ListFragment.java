package pg.grigaliunas.paulius.atpaink10;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.service.autofill.UserData;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ListFragment extends Fragment {

    private List<ImageObject> listData;

    private DatabaseHelper mydb;
    private ListView listView;
    private ImageView imageView;
    private FloatingActionButton fab;

    private ArrayList arrayList;
    private HashMap<String, String> hmap;

    private TextView textView;

    public ListFragment() {
        // Required empty public constructor
    }


        // Array of strings storing country names
        String[] countries = new String[] {
                "India",
                "Pakistan",
                "Sri Lanka",
                "China",
                "Bangladesh",
                "Nepal",
                "Afghanistan",
                "North Korea",
                "South Korea",
                "Japan"
        };

        // Array of integers points to images stored in /res/drawable-ldpi/
        int[] flags = new int[]{
                R.drawable.ic_menu_camera,
                R.drawable.ic_menu_camera,
                R.drawable.ic_menu_camera,
                R.drawable.ic_menu_camera,
                R.drawable.ic_menu_camera,
                R.drawable.ic_menu_camera,
                R.drawable.ic_menu_camera,
                R.drawable.ic_menu_camera,
                R.drawable.ic_menu_camera,
                R.drawable.ic_menu_camera
        };

        // Array of strings to store currencies
        String[] currency = new String[]{
                "Indian Rupee",
                "Pakistani Rupee",
                "Sri Lankan Rupee",
                "Renminbi",
                "Bangladeshi Taka",
                "Nepalese Rupee",
                "Afghani",
                "North Korean Won",
                "South Korean Won",
                "Japanese Yen"
        };

        /** Called when the activity is first created. */
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            // Each row in the list stores country name, currency and flag
            List<HashMap<String,String>> aList = new ArrayList<HashMap<String,String>>();

            for(int i=0;i<10;i++){
                HashMap<String, String> hm = new HashMap<String,String>();
                hm.put("txt", "Country : " + countries[i]);
                hm.put("cur","Currency : " + currency[i]);
                hm.put("flag", Integer.toString(flags[i]) );
                aList.add(hm);
            }

            // Keys used in Hashmap
            String[] from = { "flag","txt"};

            // Ids of views in listview_layout
            int[] to = { R.id.imageView3,R.id.textView};

            // Instantiating an adapter to store each items
            // R.layout.listview_layout defines the layout of each item
            SimpleAdapter adapter = new SimpleAdapter(getActivity().getBaseContext(), aList, R.layout.list_row, from, to);

            // Getting a reference to listview of main.xml layout file
            ListView listView = ( ListView ) getActivity().findViewById(R.id.listView);

            // Setting the adapter to the listView
            listView.setAdapter(adapter);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_list, container, false);
        }


    @Override
    public void onResume() {
        super.onResume();
        //showList();
    }

    private void showList(){

        try{
            Cursor c = mydb.showAll();
            listData = new ArrayList<>();
            while(c.moveToNext())
            {
                byte[] byteArray = c.getBlob(3);
                listData.add(new ImageObject(c.getLong(1), c.getInt(2), BitmapFactory.decodeByteArray(byteArray, 0 ,byteArray.length)));
            }






           /* listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    HashMap element = (HashMap) arrayList.get(position);

                    int name =  Integer.parseInt(element.get("id").toString());

                    Bundle bundle = new Bundle();
                    bundle.putInt("data",name);

                    Fragment fragment = new ChildInfoFragment();
                    fragment.setArguments(bundle);
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.mainFrame, fragment);
                    ft.addToBackStack(null);
                    ft.commit();
                }
            });*/
        }

        catch(Exception e){
        Log.e("error",e.getMessage());

    }



/*
        arrayList = new ArrayList<HashMap<String,String>>();
        try{

            Cursor c = mydb.showAll();


            while(c.moveToNext())
            {

                arrayList.add(hmap);
            }
        }
        catch(Exception e){
            Log.e("error",e.getMessage());

        }

        String from[]={"id","name","points"};
        int to[] = {R.id.idText, R.id.taskText, R.id.pointText};

        SimpleAdapter adapter = new SimpleAdapter(
                getContext(),
                arrayList,
                R.layout.list_row,
                from, to );

        listView.setAdapter(adapter);
        */
        //selectItemFromList();
    }


    /*private void selectItemFromList(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                HashMap element = (HashMap) arrayList.get(position);

                int name =  Integer.parseInt(element.get("id").toString());

                Bundle bundle = new Bundle();
                bundle.putInt("data",name);

                Fragment fragment = new ChildInfoFragment();
                fragment.setArguments(bundle);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.mainFrame, fragment);
                ft.addToBackStack(null);
                ft.commit();
            }
        });
    }*/

}
