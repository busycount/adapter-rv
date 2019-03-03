package com.busycount.rvadapter.click;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;

import com.busycount.rvadapter.BaseRvHolder;
import com.busycount.rvadapter.R;


/**
 * RecyclerView Click helper
 * <p>
 * 2018/11/19 | Count.C | Created
 */
public class RvClickHelper {

    private OnRvItemClickListener onRvItemClickListener;
    private OnRvItemLongClickListener onRvItemLongClickListener;

    private View.OnClickListener innerClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (onRvItemClickListener == null) {
                return;
            }
            int position = getPosition(v);
            if (position != -1) {
                onRvItemClickListener.onClick(v, position);
            }
        }
    };

    private View.OnLongClickListener innerLongClickListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            if (onRvItemLongClickListener != null) {
                int position = getPosition(v);
                if (position != -1) {
                    onRvItemLongClickListener.onLongClick(v, position);
                    return true;
                }
                return false;
            }
            return false;
        }
    };

    private int getPosition(View v) {
        int position = -1;
        try {
            position = (int) v.getTag(R.id.rv_tag_holder);
        } catch (Exception e) {
            Log.e("RvLib", "getPosition error");
        }
        return position;
    }


    public void setupClick(@NonNull BaseRvHolder holder) {
        holder.setClick(onRvItemClickListener);
//        holder.itemView.setOnClickListener(innerClickListener);
//        holder.itemView.setOnLongClickListener(innerLongClickListener);
    }

    public void configClick(@NonNull BaseRvHolder holder, int position) {
        holder.itemView.setTag(R.id.rv_tag_holder, position);
    }


    public void setOnRvItemClickListener(OnRvItemClickListener onRvItemClickListener) {
        this.onRvItemClickListener = onRvItemClickListener;
    }

    public void setOnRvItemLongClickListener(OnRvItemLongClickListener onRvItemLongClickListener) {
        this.onRvItemLongClickListener = onRvItemLongClickListener;
    }
}
