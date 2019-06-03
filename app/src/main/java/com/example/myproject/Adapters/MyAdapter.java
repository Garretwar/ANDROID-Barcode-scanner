package com.example.myproject.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.myproject.Model.ListItem;
import com.example.myproject.R;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyAdapterViewHolder> {


    List<ListItem> listItemsArrayList;
    Context context;
    public MyAdapter(List<ListItem> listItemsArrayList, Context context){
        this.listItemsArrayList = listItemsArrayList;
        this.context = context;

    }

    @Override
    public MyAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_layout,viewGroup,false);
        return new MyAdapterViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapterViewHolder myAdapterViewHolder, int i) {
         ListItem listItem = listItemsArrayList.get(i);

        myAdapterViewHolder.textViewType.setText(listItem.getType());
        myAdapterViewHolder.textViewCode.setText(listItem.getCode());
        Linkify.addLinks(myAdapterViewHolder.textViewCode, Linkify.ALL);
    }

    @Override
    public int getItemCount() {
        return listItemsArrayList.size();
    }

    public class MyAdapterViewHolder extends RecyclerView.ViewHolder{

        TextView textViewCode, textViewType;
        CardView cardView;
        Button Open;
        public MyAdapterViewHolder(final View itemView){
            super(itemView);
            textViewCode = itemView.findViewById(R.id.textViewCode);
            textViewType = itemView.findViewById(R.id.textViewType);
            cardView = itemView.findViewById(R.id.cardView);
            Open = itemView.findViewById(R.id.Open);


            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String type = listItemsArrayList.get(getAdapterPosition()).getType();

                    Intent i = new Intent();
                    i.setAction(Intent.ACTION_SEND);
                    i.putExtra(Intent.EXTRA_TEXT,type);
                    i.setType("text/plain");
                    itemView.getContext().startActivity(i);


                }
            });
            Open.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String type = listItemsArrayList.get(getAdapterPosition()).getType();
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(type));
                    itemView.getContext().startActivity(browserIntent);
                }
            });
        }


    }

}
