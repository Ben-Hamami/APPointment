package com.example.appointment.Fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.appointment.Adapter.MyBarberAdapter;
import com.example.appointment.Common.Common;
import com.example.appointment.Common.SpacesItemDecoration;
import com.example.appointment.Model.Barber;
import com.example.appointment.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class BookingStep2Fragment extends Fragment {

    LocalBroadcastManager localBroadcastManager;
    RecyclerView recycler_barber;

    private BroadcastReceiver barberDoneReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            ArrayList<Barber> barberArrayList = intent.getParcelableArrayListExtra(Common.KEY_BARBER_LOAD_DONE);

            MyBarberAdapter adapter = new MyBarberAdapter(getContext(), barberArrayList);
            recycler_barber.setAdapter(adapter);
        }
    };

    static BookingStep2Fragment instance;

    public static BookingStep2Fragment getInstance() {
        instance = new BookingStep2Fragment();
        return instance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        localBroadcastManager = LocalBroadcastManager.getInstance(getContext());
        localBroadcastManager.registerReceiver(barberDoneReceiver, new IntentFilter(Common.KEY_BARBER_LOAD_DONE));

    }

    @Override
    public void onDestroy() {
        localBroadcastManager.unregisterReceiver(barberDoneReceiver);
        super.onDestroy();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View itemView = inflater.inflate(R.layout.fragment_booking_step_two, container, false);
        recycler_barber = itemView.findViewById(R.id.recycler_barber);
        recycler_barber.setRotationY(180);
        //unbinder = ButterKnife.bind(this, itemView);

        initView();

        return itemView;
    }

    private void initView() {
        recycler_barber.setHasFixedSize(true);
        recycler_barber.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recycler_barber.addItemDecoration(new SpacesItemDecoration(4));

    }

}
