package com.bakingapps.ui.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bakingapps.R;
import com.bakingappsdomain.model.StepModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by andiisfh on 18/09/17.
 */

public class RecipeDetailAdapter extends RecyclerView.Adapter<RecipeDetailAdapter.RVItemHolders> {

    private Context mContext;
    private List<StepModel> mStepModel;
    private Callback mCallback;

    public RecipeDetailAdapter(Context context, List<StepModel> StepModel) {
        mContext = context;
        mStepModel = StepModel;
    }

    @Override
    public RVItemHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detail_recipe, parent, false);
        return new RVItemHolders(v);
    }

    @Override
    public void onBindViewHolder(RVItemHolders holder, int position) {
        holder.mTVName.setText((position + 1) + ". " + mStepModel.get(position).getShortDescription());
    }

    @Override
    public int getItemCount() {
        return mStepModel.size();
    }

    public class RVItemHolders extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.container)
        CardView mContainer;
        @BindView(R.id.tv_name)
        TextView mTVName;

        public RVItemHolders(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            mContainer.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition() - 2;
            mCallback.onItemClick(mStepModel, position, mStepModel.get(position).getShortDescription());
        }
    }

    public void OnItemClick(Callback callback) {
        mCallback = callback;
    }

    public interface Callback {
        void onItemClick(List<StepModel> stepModel, int position, String title);
    }
}
