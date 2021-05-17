package com.example.squff2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;


public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private LayoutInflater layoutInflater;
    private List<String> data;
    private List<Integer> images;



    Adapter(Context context, List<String> data, ArrayList<Integer> images){
        this.layoutInflater = LayoutInflater.from(context);
        this.data = data;
        this.images = images;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(R.layout.custom_view,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {


        String title = data.get(i);
        int image = images.get(i);
        viewHolder.textTitle.setText(title);
        viewHolder.imageView.setImageResource(image);


    }

    @Override
    public int getItemCount() {
    return images.size();

    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView textTitle;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (getAdapterPosition() == 0){
                        Intent i = new Intent(v.getContext(),MainActivity.class);
                        i.putExtra("title",data.get(getAdapterPosition()));
                        i.putExtra("image", images.get(getAdapterPosition()));
                        v.getContext().startActivity(i);
                    }

                    if (getAdapterPosition() == 1){
                        Intent i = new Intent(v.getContext(),Shipping.class);
                        i.putExtra("title",data.get(getAdapterPosition()));
                        v.getContext().startActivity(i);
                    }

                    if (getAdapterPosition() == 2){
                        Toasty.info(v.getContext(), "Not implemented yet!!", Toasty.LENGTH_SHORT).show();
                    }

                    if (getAdapterPosition() == 3){
                        Intent i = new Intent(v.getContext(), WarehouseStorage.class);
                        i.putExtra("title", data.get(getAdapterPosition()));
                        v.getContext().startActivity(i);
                    }

                    if (getAdapterPosition() == 4) {
                        Intent i = new Intent(v.getContext(), Contacts.class);
                        i.putExtra("title", data.get(getAdapterPosition()));
                        v.getContext().startActivity(i);

                    }

                    if (getAdapterPosition() == 5) {
                        Intent i = new Intent(v.getContext(), Settings.class);
                        v.getContext().startActivity(i);
                    }

                }
            });
            textTitle = itemView.findViewById(R.id.textTitle);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
