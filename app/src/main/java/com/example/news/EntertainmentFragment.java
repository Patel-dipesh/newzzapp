package com.example.news;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EntertainmentFragment extends Fragment {
    private String apikey = "5ea2477c9947400180671da174f0894f";
    private RecyclerView recyclerviewofentertainment;
    private String category = "entertainment";
    ArrayList<ModelClass> modelClassArrayList;
    Adaptor adaptor;
    String country = "in";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.entertainmentfragment, container,false);

        recyclerviewofentertainment = view.findViewById(R.id.recyclerviewofentertainment);
        modelClassArrayList = new ArrayList<>();
        recyclerviewofentertainment.setLayoutManager(new LinearLayoutManager(getContext()));
        adaptor = new Adaptor(getContext(), modelClassArrayList);
        recyclerviewofentertainment.setAdapter(adaptor);

        findNews();
        return view;

    }

    private void findNews() {
        ApiUtilities.getApiInterface().getcategoryNews(country, category, 100, apikey).enqueue(new Callback<mainNews>() {
            @Override
            public void onResponse(Call<mainNews> call, Response<mainNews> response) {
                if (response.isSuccessful()) {
                    modelClassArrayList.addAll(response.body().getArticles());
                    adaptor.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<mainNews> call, Throwable t) {

            }
        });

    }
}

