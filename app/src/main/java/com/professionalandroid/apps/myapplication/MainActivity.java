package com.professionalandroid.apps.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;

    List<Memo> memoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        memoList = new ArrayList<>();
        memoList.add(new Memo("test1", "testtest", 0));
        memoList.add(new Memo("test2", "testtest", 0));
        memoList.add(new Memo("test3", "testtest", 0));
        memoList.add(new Memo("test4", "testtest", 1));
        memoList.add(new Memo("test5", "testtest", 1));

        recyclerView = findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerAdapter = new RecyclerAdapter(memoList);
        recyclerView.setAdapter(recyclerAdapter);
    }

    class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder> {
        private List<Memo> listdata;

        public RecyclerAdapter(List<Memo> listdata) {
            this.listdata = listdata;
        }

        @NonNull
        @Override
        public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
            return new ItemViewHolder(view);
        }

        @Override
        public int getItemCount() {
            return listdata.size();
        }

        @Override
        public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, int i) {
            Memo memo = listdata.get(i);

            itemViewHolder.maintext.setText(memo.getMaintext());
            itemViewHolder.subtext.setText(memo.getSubtext());

            if(memo.getIsdone() == 0) {
                itemViewHolder.img.setBackgroundColor(Color.LTGRAY);
            } else {
                itemViewHolder.img.setBackgroundColor(Color.GREEN);
            }
        }

        void addItem(Memo memo) {
            listdata.add(memo);
        }

        void removeItem(int position) {
            listdata.remove(position);
        }

        class ItemViewHolder extends RecyclerView.ViewHolder {

            private TextView maintext;
            private TextView subtext;
            private ImageView img;

            public ItemViewHolder(@NonNull View itemView){
                super(itemView);

                maintext = itemView.findViewById(R.id.item_maintext);
                subtext = itemView.findViewById(R.id.item_subtext);
                img = itemView.findViewById(R.id.item_image);
            }
        }
    }
}