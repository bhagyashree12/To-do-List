package com.example.to_do_list_room;

import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.Node_ViewHolder> {


    List<Entity_node> list = new ArrayList<>();
    public ItemClickListener listner;

    @NonNull
    @Override
    public Node_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);

        return new Node_ViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull Node_ViewHolder holder, int position) {

        Entity_node node = list.get(position);
        holder.title.setText(node.getTitle());
        holder.description.setText(node.getDetail());
        holder.priority.setText(String.valueOf(node.getPriority()));


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setNodes(List<Entity_node> list) {
        this.list = list;
        notifyDataSetChanged();
    }


    //function to delete node
    public Entity_node getNodeAt(int position) {

        return list.get(position);
    }

    public class Node_ViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView description;
        private Button button;
        private TextView priority;

        public Node_ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            button = itemView.findViewById(R.id.button);
            priority = itemView.findViewById(R.id.priority);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listner != null && position != RecyclerView.NO_POSITION) {
                        listner.OnItemClick(list.get(position));
                    }
                }
            });

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    title.setPaintFlags(title.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    description.setText("");
                }
            });

        }
    }

    public interface ItemClickListener {
        void OnItemClick(Entity_node node);
    }

    public void setOnClick(ItemClickListener listner) {
        this.listner = listner;
    }

}
