package com.cactuslabs.boilerbites;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private Context context;
    private List<ListItem> list;

    public ListAdapter(Context context, List<ListItem> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.init(list.get(position).getKeyword());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView trash;
        public TextView keyword;

        public ViewHolder(View view) {
            super(view);
            trash = (ImageView) view.findViewById(R.id.trash_icon);
            keyword = (TextView) view.findViewById(R.id.keyword_text);
        }

        public void init(final String content) {
            keyword.setText(content);
            trash.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    (new DataUtil(view.getContext())).removeItem("" + content);
                }
            });
        }
    }
}