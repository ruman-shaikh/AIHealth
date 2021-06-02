package org.scorp.aihealth;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PatientAdapter extends RecyclerView.Adapter<PatientAdapter.PatientViewHolder> {

    private Context context;
    private ArrayList<Patient> patientArrayList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;

    }

    public PatientAdapter(Context context, ArrayList<Patient> patientArrayList) {
        this.context = context;
        this.patientArrayList = patientArrayList;
    }

    @NonNull
    @Override
    public PatientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.patient_element, parent, false);
        return new PatientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PatientViewHolder holder, int position) {
        Patient currentPatient = patientArrayList.get(position);

        String name = currentPatient.getName();
        String username = currentPatient.getUsername();

        holder.p_username.setText(username);
        holder.p_name.setText(name);
    }

    @Override
    public int getItemCount() {
        return patientArrayList.size();
    }

    public class PatientViewHolder extends RecyclerView.ViewHolder {

        public TextView p_username;
        public TextView p_name;

        public PatientViewHolder(@NonNull View itemView) {
            super(itemView);
            p_username = itemView.findViewById(R.id.p_username);
            p_name = itemView.findViewById(R.id.p_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
