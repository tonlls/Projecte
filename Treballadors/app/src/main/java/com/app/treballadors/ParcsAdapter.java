package com.app.treballadors;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

class ParcAdapter extends RecyclerView.Adapter<ParcAdapter.ViewHolder>{
	public List<parc> parcs;

	public ParcAdapter(List<parc> parcs) {
		this.parcs = parcs;
	}

	@NonNull
	@Override
	public ParcAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
		View fila = LayoutInflater.from(viewGroup.getContext()).inflate(
				R.layout.parc_row, viewGroup, false);
		return new ViewHolder(fila);
	}

	@Override
	public void onBindViewHolder(@NonNull ParcAdapter.ViewHolder viewHolder, int i) {
		parc p = parcs.get(i);
		viewHolder.nom.setText(p.nom);
		new DownloadImageTask(viewHolder.foto).execute(p.foto);
	}

	@Override
	public int getItemCount() {
		return parcs.size();
	}

	public class ViewHolder extends RecyclerView.ViewHolder {
		TextView nom;
		ImageView foto;
		public ViewHolder(View itemView) {
			super(itemView);
			nom = itemView.findViewById(R.id.nom);
			foto = itemView.findViewById(R.id.foto);
		}
	}
}
