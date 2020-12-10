package com.vhallyun.lss.widgets;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.vhallyun.lss.R;

import java.util.List;

public class LangDialog extends Dialog {

    private RecyclerView listView;
    private List<String> strs;
    private Callback callback;
    private CheckBox checkBox;

    public interface Callback {
        void onLang(String lang);
        void onShow(boolean show);
    }

    public LangDialog(@NonNull Context context, List<String> strs, Callback callback) {
        super(context);
        this.strs = strs;
        this.callback = callback;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lang_dialog);
        listView = findViewById(R.id.lang_list);
        checkBox = findViewById(R.id.check_box);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                callback.onShow(isChecked);
            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        listView.setLayoutManager(layoutManager);
        listView.setAdapter(new ListAdapter());
    }

    private class ListAdapter extends RecyclerView.Adapter<LangViewHolder> {

        @NonNull
        @Override
        public LangViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.lang_dialog_item,viewGroup,false);
            LangViewHolder holder = new LangViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull LangViewHolder langViewHolder, int i) {
            langViewHolder.textView.setText(strs.get(i));
            langViewHolder.textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callback.onLang(strs.get(i));
                    dismiss();
                }
            });
        }

        @Override
        public int getItemCount() {
            return strs.size();
        }
    }

    private class LangViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public LangViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.item);
        }
    }
}
