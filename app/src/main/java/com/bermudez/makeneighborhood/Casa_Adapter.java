package com.bermudez.makeneighborhood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class Casa_Adapter  extends RecyclerView.Adapter<Casa_Adapter.ViewHolder> implements View.OnClickListener{

    LayoutInflater inflater;
    Context context;

    private View.OnClickListener listener;

    public Casa_Adapter(Context context) {
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }



    @Override
    public void onClick(View v) {
        if(listener!=null){
            listener.onClick(v);
        }
    }
    public void onClick(View.OnClickListener listener){this.listener=listener;}

    @NonNull
    @Override
    public Casa_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =inflater.inflate(R.layout.casa_list,parent,false);
        view.setOnClickListener((View.OnClickListener) this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Casa_Adapter.ViewHolder holder, int position) {
        // SIEMPRE SON STRING
        String strCalle=Neightborhood.lstCasas.get(position).getsCalle();
        String iNumero= String.valueOf(Neightborhood.lstCasas.get(position).getiNumero());
        String doSuperficie= String.valueOf(Neightborhood.lstCasas.get(position).getDoSperficie());

        holder.lblNameStreet.setText(strCalle);
        holder.lblNumber.setText(iNumero);
        holder.lblSize.setText(doSuperficie);

    }

    @Override
    public int getItemCount() {
        return Neightborhood.lstCasas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView lblNameStreet;
        TextView lblNumber;
        TextView lblSize;

        public ViewHolder(@NonNull View itemView){
            super(itemView);

            lblNameStreet= itemView.findViewById(R.id.lblNameStreet);
            lblNumber=itemView.findViewById(R.id.lblNumber);
            lblSize=itemView.findViewById(R.id.lblSize);

        }

    }
}