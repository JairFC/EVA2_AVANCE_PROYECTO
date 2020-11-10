package com.example.healthyhabits;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.Query;

import java.util.ArrayList;


/**
 * Created by Matteo on 24/08/2015.
 */
public class AdapterEmergency extends FirebaseAdapterEmergency<AdapterEmergency.ViewHolder, EmergencyObject> {

    /**
     * @param query     The Firebase location to watch for data changes.
     *                  Can also be a slice of a location, using some combination of
     *                  <code>limit()</code>, <code>startAt()</code>, and <code>endAt()</code>.
     * @param itemClass The class of the items.
     */
    public AdapterEmergency(Query query, Class<EmergencyObject> itemClass) {
        super(query, itemClass);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvRoutineID;
        public TextView tvTitle;
        public TextView tvIni;
        public TextView tvFin;
        public TextView tvDesc;

        public ViewHolder(View view) {
            super(view);
            view.setClickable(true);

            tvRoutineID =  view.findViewById(R.id.tvRoutineId);
            tvTitle = view.findViewById(R.id.tvRoutineTitle);
            tvIni = view.findViewById(R.id.tvRoutineFechaIni);
            tvFin = view.findViewById(R.id.tv1routineFechaFin);
            tvDesc = view.findViewById(R.id.tvRoutineDesc);

        }
    }

    public AdapterEmergency(Query query, Class<EmergencyObject> itemClass, @Nullable ArrayList<EmergencyObject> items,
                            @Nullable ArrayList<String> keys) {
        super(query, itemClass, items, keys);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_emergency, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


        EmergencyObject EmergencyObject = getItem(position);

        holder.tvDesc.setText(EmergencyObject.getDescr());
        holder.tvFin.setText(String.valueOf(EmergencyObject.getmPulse()));
        holder.tvIni.setText(EmergencyObject.getMfechaIni()+" "+EmergencyObject.getmHourIni());
        holder.tvTitle.setText(EmergencyObject.getMtitulo());

    }

    @Override
    protected void itemAdded(EmergencyObject item, String key, int position) {
    }

    @Override
    protected void itemChanged(EmergencyObject oldItem, EmergencyObject newItem, String key, int position) {
    }

    @Override
    protected void itemRemoved(EmergencyObject item, String key, int position) {
    }

    @Override
    protected void itemMoved(EmergencyObject item, String key, int oldPosition, int newPosition) {
    }
}