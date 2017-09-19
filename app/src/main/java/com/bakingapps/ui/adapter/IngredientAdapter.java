package com.bakingapps.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bakingapps.R;
import com.bakingappsdomain.model.IngredientModel;

import java.text.DecimalFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by andiisfh on 18/09/17.
 */

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.RVItemHolders> {

    private Context mContext;
    private List<IngredientModel> mIngredientModel;
    private Callback mCallback;

    public IngredientAdapter(Context context, List<IngredientModel> IngredientModel) {
        mContext = context;
        mIngredientModel = IngredientModel;
    }

    @Override
    public RVItemHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ingredient, parent, false);
        return new RVItemHolders(v);
    }

    @Override
    public void onBindViewHolder(RVItemHolders holder, int position) {
        DecimalFormat value = new DecimalFormat("#.#");
        String quantity = String.valueOf(value.format(mIngredientModel.get(position).getQuantity()));
        String measure = mIngredientModel.get(position).getMeasure();
        String ingredient = mIngredientModel.get(position).getIngredient();

        holder.mTVName.setText(mContext.getResources().getString(R.string.ingredient_item_name, quantity, measure, ingredient));
    }

    @Override
    public int getItemCount() {
        return mIngredientModel.size();
    }

    public class RVItemHolders extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.container)
        LinearLayout mLinearLayout;
        @BindView(R.id.tv_name)
        TextView mTVName;

        public RVItemHolders(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            mLinearLayout.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mCallback.onItemClick(mIngredientModel.get(getAdapterPosition() - 1));
        }
    }

    public void OnItemClick(Callback callback) {
        mCallback = callback;
    }

    public interface Callback {
        void onItemClick(IngredientModel IngredientModel);
    }
}
