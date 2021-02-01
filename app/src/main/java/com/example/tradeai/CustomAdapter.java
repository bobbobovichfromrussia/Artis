package com.example.tradeai;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private String[] newsTitleList;
    private String[] newsContentList;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView newsTitle;
        private final TextView newsContent;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            newsTitle = (TextView) view.findViewById(R.id.news_title);
            newsContent = (TextView) view.findViewById(R.id.news_content);
        }

        public TextView getNewsTitleView() {
            return newsTitle;
        }

        public TextView getNewsContentView(){
            return newsContent;
        }
    }

    public CustomAdapter(String[] titles, String[] contents) {
        newsTitleList = titles;
        newsContentList = contents;
    }

    public void updateData(String[] titles, String[] contents){
        newsTitleList = titles;
        newsContentList = contents;
        this.notifyDataSetChanged();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.text_row_item, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.getNewsTitleView().setText(newsTitleList[position]);
        viewHolder.getNewsContentView().setText(newsContentList[position]);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return newsTitleList.length;
    }
}
