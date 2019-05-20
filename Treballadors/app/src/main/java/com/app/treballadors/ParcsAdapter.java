package com.app.treballadors;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

class ParcAdapter extends RecyclerView.Adapter<ParcAdapter.ViewHolder>{
	private final MainActivity main;
	public List<parc> parcs;

	public ParcAdapter(List<parc> parcs,MainActivity ma) {
		this.main=ma;
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
		final parc p = parcs.get(i);
		viewHolder.nom.setText(p.nom);
		ImageLoader loader = ImageLoader.getInstance();
		loader.displayImage( p.url_foto, viewHolder.foto);
		viewHolder.foto.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				/*Intent myIntent = new Intent(v.getContext(), AtraccionsActivity.class);
				myIntent.putExtra("PARC_ID",p.id);
				v.getContext().startActivity(myIntent);*/
				main.parcSelected(p.id);
			}
		});
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
