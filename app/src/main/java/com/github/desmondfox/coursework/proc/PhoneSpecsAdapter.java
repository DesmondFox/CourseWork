package com.github.desmondfox.coursework.proc;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.TextView;

import com.github.desmondfox.coursework.R;
import com.github.desmondfox.coursework.entities.Phone;
import com.github.desmondfox.coursework.utils.Pair;

import java.util.ArrayList;
import java.util.List;

public class PhoneSpecsAdapter extends RecyclerView.Adapter<PhoneSpecsAdapter.SpecViewHolder> {
    private List<Pair<String, String>> specList = new ArrayList<>();
    private @NonNull Context context;

    public PhoneSpecsAdapter(@NonNull Context context) {
        this.context = context;
    }

    public void setPhone(Phone phone) {
        Resources r = context.getResources();

        specList.clear();
        specList.add(new Pair<>(r.getString(R.string.spc_proccessor), phone.getSoc()));
        specList.add(new Pair<>(r.getString(R.string.spc_os), phone.getOs()));
        specList.add(new Pair<>(r.getString(R.string.spc_display), phone.getDisplay()));
        specList.add(new Pair<>(r.getString(R.string.spc_ram), phone.getRam()));
        specList.add(new Pair<>(r.getString(R.string.spc_rom), phone.getRom()));
        specList.add(new Pair<>(r.getString(R.string.spc_batt), phone.getBattery()));
        specList.add(new Pair<>(r.getString(R.string.spc_mncam), phone.getMainCam()));
        specList.add(new Pair<>(r.getString(R.string.spc_frontcam), phone.getFrontalCam()));
        specList.add(new Pair<>(r.getString(R.string.spc_nfc), phone.getNfc()));
        specList.add(new Pair<>(r.getString(R.string.spc_features), phone.getFeatures()));
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SpecViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new SpecViewHolder(
                LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.spec_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SpecViewHolder specViewHolder, int i) {
        if (i <= specList.size())
            specViewHolder.bind(
                    specList.get(i).key,
                    specList.get(i).value);
    }

    @Override
    public int getItemCount() {
        return specList.size();
    }

    class SpecViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        private TextView specName;
        private TextView specValue;

        public SpecViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.spec_card);
            specName = cardView.findViewById(R.id.spec_name);
            specValue = cardView.findViewById(R.id.spec_value);
        }

        public void bind(String name, String value) {
            specName.setText(name);
            specValue.setText(value);
        }
    }

}
