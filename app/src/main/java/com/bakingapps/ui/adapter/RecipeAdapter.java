package com.bakingapps.ui.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bakingapps.R;
import com.bakingappsdomain.model.RecipeModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by andiisfh on 18/09/17.
 */

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RVItemHolders> {

    private Context mContext;
    private List<RecipeModel> mRecipeModel;
    private Callback mCallback;

    public RecipeAdapter(Context context, List<RecipeModel> recipeModel) {
        mContext = context;
        mRecipeModel = recipeModel;
    }

    @Override
    public RVItemHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recipe, parent, false);
        return new RVItemHolders(v);
    }

    @Override
    public void onBindViewHolder(RVItemHolders holder, int position) {
        holder.mTVName.setText(mRecipeModel.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return mRecipeModel.size();
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
            mCallback.onItemClick(mRecipeModel.get(getAdapterPosition() - 1));
        }
    }

    public void OnItemClick(Callback callback) {
        mCallback = callback;
    }

    public interface Callback {
        void onItemClick(RecipeModel recipeModel);
    }
}
