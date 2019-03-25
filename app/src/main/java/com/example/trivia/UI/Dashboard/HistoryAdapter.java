package com.example.trivia.UI.Dashboard;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.trivia.Data.Room.QAEntiry;
import com.example.trivia.R;


import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder>{
    private List<QAEntiry> taskArrayList;

    public HistoryAdapter(List<QAEntiry> taskArrayList) {
        this.taskArrayList = taskArrayList;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView name,q1 ,q2 ,a1,a2,date,game;

        public ViewHolder(View itemView) {
            super(itemView);
            q1 = itemView.findViewById(R.id.cell_q1);
            q2 = itemView.findViewById(R.id.cell_q2);
            name = itemView.findViewById(R.id.cell_user_nm);
            a1 = itemView.findViewById(R.id.cell_a1);
            a2 = itemView.findViewById(R.id.cell_a2);
            date = itemView.findViewById(R.id.date);
            game = itemView.findViewById(R.id.game_nm);

        }
    }

    @Override
    public HistoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_cell,parent,false);
        return new ViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final HistoryAdapter.ViewHolder holder, final int position) {

        final QAEntiry task = taskArrayList.get(position);
        holder.name.setText("Name: "+task.getUserName());
        holder.game.setText("GAME"+task.getUserId());
        holder.date.setText(task.getDate());
        holder.q1.setText(task.getQ1ques());
        holder.q2.setText(task.getQ2ques());
        holder.a1.setText("Answer: "+task.getQ1ans());
        holder.a2.setText("Answer: "+task.getQ2ans());

    }

    public void setData(List<QAEntiry> taskArrayList) {
        this.taskArrayList = taskArrayList;
        notifyDataSetChanged();
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() { return taskArrayList.size(); }
}

