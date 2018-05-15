package it.justdevelop.catexamtipsandtricks;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * A simple {@link Fragment} subclass.
 */
public class ContentFragment extends Fragment {

    FirebaseDatabase database;
    DatabaseReference content_reference;

    public ContentFragment() {
        // Required empty public constructor
    }

    View view;
    ListView content_list;
    List<String> contentAdapter = new ArrayList<>();
    Context context;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_quants, container, false);


        initializeViews();

        return view;
    }

    public void initializeViews(){
        content_list = view.findViewById(R.id.content_list);
        context = getActivity().getApplicationContext();

        database = FirebaseDatabase.getInstance();
        Log.d("FIREBASE_DATA", "Content reference : "+getArguments().getString("content"));
        content_reference = database.getReference(getArguments().getString("content"));
        content_reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();

                contentAdapter.addAll(map.keySet());
                displayList();
                Log.d("FIREBASE_DATA", "Content data: " + map.keySet());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w("FIREBASE_DATA", "Failed to read content values.", error.toException());
            }
        });
    }

    public void displayList(){
        ArrayAdapter<String> adapter = new myContentAdapterClass();
        content_list.setAdapter(adapter);
    }


    public class myContentAdapterClass extends ArrayAdapter<String> {

        myContentAdapterClass() {
            super(context, R.layout.item_content_list, contentAdapter);
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, @NonNull ViewGroup parent) {
            View itemView = convertView;
            if (itemView == null) {
                LayoutInflater inflater = LayoutInflater.from(context);
                itemView = inflater.inflate(R.layout.item_content_list, parent, false);
            }
            String current = contentAdapter.get(position);

            TextView item = itemView.findViewById(R.id.item_content_list_item);
            item.setText(current);

            return itemView;
        }
    }




}
