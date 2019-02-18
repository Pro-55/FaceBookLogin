package com.example.admin.testlogin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetSequence;
import com.getkeepsafe.taptargetview.TapTargetView;

import java.util.List;

public class rAdapter extends RecyclerView.Adapter<rAdapter.ViewHolder> {

    private List<ListItem> listItems;
    private Context context;
    private SparseBooleanArray sparseBooleanArray;
    private rAdapterInterface adapterInterface;

    public rAdapter(List<ListItem> listItems, Context context, rAdapterInterface adapterInterface) {
        this.listItems = listItems;
        this.context = context;
        this.adapterInterface = adapterInterface;
        sparseBooleanArray = new SparseBooleanArray();
        sparseBooleanArray.put(15, true);
    }

    @NonNull
    @Override
    public rAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final rAdapter.ViewHolder holder, final int position) {
        final ListItem listItem = listItems.get(position);

        if (sparseBooleanArray.get(position)) {
            adapterInterface.scrollToPosition(position);
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    explainUI(holder);
                    sparseBooleanArray.put(position, false);
                }
            }, 500);
        }

        holder.textViewHead.setText(listItem.getHead());
        holder.textViewInfo.setText(listItem.getInfo());

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SecondActivity.class);
                intent.putExtra("Head", listItem.getHead());
                intent.putExtra("Info", listItem.getInfo());
                context.startActivity(intent);
            }
        });
    }

    private void explainUI(ViewHolder holder) {
        final TapTargetSequence tapTargetSequence = new TapTargetSequence((Activity) context)
                .targets(TapTarget.forView(holder.idIvBicMic, "", "").id(1).outerCircleColor(R.color.colorTwitterBlue).outerCircleAlpha(0.8f).transparentTarget(true),
                        TapTarget.forView(holder.idIvBicG, "", "").id(2).outerCircleColor(R.color.colorFerrariYellow).outerCircleAlpha(0.8f).transparentTarget(true))
                .considerOuterCircleCanceled(true)
                .continueOnCancel(true);
        tapTargetSequence.start();
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewHead;
        public TextView textViewInfo;
        public LinearLayout linearLayout;
        public CardView idCvListItemCardView;
        public AppCompatImageView idIvBicMic;
        public AppCompatImageView idIvBicG;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewHead = itemView.findViewById(R.id.headTextView);
            textViewInfo = itemView.findViewById(R.id.infoTextView);
            linearLayout = itemView.findViewById(R.id.linearLayout);
            idCvListItemCardView = itemView.findViewById(R.id.idCvListItemCardView);
            idIvBicMic = itemView.findViewById(R.id.idIvBicMic);
            idIvBicG = itemView.findViewById(R.id.idIvBicG);
        }
    }
}
