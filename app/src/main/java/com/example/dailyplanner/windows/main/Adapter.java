package com.example.dailyplanner.windows.main;

import android.app.Activity;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SortedList;

import com.example.dailyplanner.App;
import com.example.dailyplanner.R;
import com.example.dailyplanner.model.Task;
import com.example.dailyplanner.windows.details.TaskDetailsActivity;

public class Adapter extends RecyclerView.Adapter<Adapter.TaskViewHolder> {

    private SortedList<Task> sortedList;

    public Adapter(){
        sortedList = new SortedList<>(Task.class, new SortedList.Callback<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
                if(!o2.done && o1.done){
                    return 1;
                }
                if(o2.done && !o1.done){
                    return -1;
                }
                return (int) (o2.timestamp - o1.timestamp);
            }

            @Override
            public void onChanged(int position, int count) {
                notifyItemRangeChanged(position, count);
            }

            @Override
            public boolean areContentsTheSame(Task oldItem, Task newItem) {
                return oldItem.equals(newItem);
            }

            @Override
            public boolean areItemsTheSame(Task item1, Task item2) {
                return item1.id == item2.id;
            }

            @Override
            public void onInserted(int position, int count) {
                notifyItemRangeInserted(position, count);
            }

            @Override
            public void onRemoved(int position, int count) {
                notifyItemRangeRemoved(position, count);
            }

            @Override
            public void onMoved(int fromPosition, int toPosition) {
                notifyItemMoved(fromPosition, toPosition);
            }
        });
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TaskViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        holder.bind(sortedList.get(position));
    }

    @Override
    public int getItemCount() {
        return sortedList.size();
    }

    static class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView text;
        CheckBox completed;
        View delete;

        Task task;

        boolean flagUpdate;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);

            text = itemView.findViewById(R.id.task_text);
            completed = itemView.findViewById(R.id.completed);
            delete = itemView.findViewById(R.id.delete);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TaskDetailsActivity.start((Activity) itemView.getContext(),task);
                }
            });

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    App.getInstance().getTaskDao().delete(task);
                }
            });

            completed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                    if(!flagUpdate){
                        task.done = checked;
                        App.getInstance().getTaskDao().update(task);
                    }
                    updateTaskStatus();
                }
            });
        }

        public void bind(Task task){
            this.task = task;
            text.setText(task.taskText);
            updateTaskStatus();

            flagUpdate = true;
            completed.setChecked(task.done);
            flagUpdate = false;
        }

        private void updateTaskStatus() {
            if(task.done){
                text.setPaintFlags(text.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            } else {
                text.setPaintFlags(text.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
            }
        }
    }
}
