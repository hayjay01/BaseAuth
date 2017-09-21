package xyz.belvi.baseauth.adapters;

import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import appzonegroup.com.phonenumberverifier.PhoneNumberVerifier;
import xyz.belvi.baseauth.R;

/**
 * Created by zone2 on 9/18/17.
 */

abstract public class CCAdapter extends RecyclerView.Adapter<CCHolder> {
    private PhoneNumberVerifier.Countries selectedCountries;

    public CCAdapter(PhoneNumberVerifier.Countries countries) {
        this.selectedCountries = countries;

    }

    @Override
    public CCHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CCHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cc_selector_tiem, parent, false));
    }

    @Override
    public void onBindViewHolder(CCHolder holder, final int position) {
        holder.getCountryName().setText(PhoneNumberVerifier.Countries.values()[position].getCountryName());
        holder.getCountryCode().setText("( +" + String.valueOf(PhoneNumberVerifier.Countries.values()[position].getCountryCode()) + " )");
        if (selectedCountries == PhoneNumberVerifier.Countries.values()[position]) {
            holder.getCountryName().setTypeface(null, Typeface.BOLD);
            holder.getCountryCode().setTypeface(null, Typeface.BOLD);
        } else {
            holder.getCountryName().setTypeface(Typeface.DEFAULT);
            holder.getCountryCode().setTypeface(Typeface.DEFAULT);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCountrySelected(PhoneNumberVerifier.Countries.values()[position]);
            }
        });
    }

    @Override
    public int getItemCount() {
        return PhoneNumberVerifier.Countries.values().length;
    }

    abstract public void onCountrySelected(PhoneNumberVerifier.Countries countries);
}
