package com.example.tradeai;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.List;

import at.theengine.android.simple_rss2_android.RSSItem;
import at.theengine.android.simple_rss2_android.SimpleRss2Parser;
import at.theengine.android.simple_rss2_android.SimpleRss2ParserCallback;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Home extends Fragment {
    private View view;
    private String[] newsTitles;
    private String[] newsContent;
    private CustomAdapter customAdapter;

    public Home() {}

    // TODO: Rename and change types and number of parameters
    public static Home newInstance(String param1, String param2) {
        Home fragment = new Home();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);

        SimpleRss2Parser parser =
                new SimpleRss2Parser("https://lorem-rss.herokuapp.com/feed", getRssCallback());
        parser.parseAsync();

        newsTitles = new String[] {"title1","title2","title3","title4","title5","title6"};
        newsContent = new String[] {"lorem ipsum","lorem ipsum","lorem ipsum","lorem ipsum","lorem ipsum","lorem ipsum",};

        customAdapter = new CustomAdapter(newsTitles,newsContent);
        FrameLayout recyclerviewContainer = (FrameLayout) view.findViewById(R.id.recyclerview_container);
        RecyclerView recyclerView = new RecyclerView(requireActivity());
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        recyclerviewContainer.addView(recyclerView);
        return view;
    }

    private SimpleRss2ParserCallback getRssCallback(){
        return new SimpleRss2ParserCallback() {
            @Override
            public void onFeedParsed(List<RSSItem> list) {
                newsTitles = new String[list.size()];
                newsContent = new String[list.size()];
                for(int i=0; i<list.size(); i++){
                    newsTitles[i] = list.get(i).getTitle();
                    newsContent[i] = list.get(i).getDescription();
                }
                customAdapter.updateData(newsTitles,newsContent);
            }

            @Override
            public void onError(Exception e) {

            }
        };
    }
}