package com.app.treballadors;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.treballadors.model.atraccio;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

class AtraccioAdapter extends RecyclerView.Adapter<AtraccioAdapter.ViewHolder> {
	public IActivity main;
	public List<atraccio> atraccions;
	public AtraccioAdapter(List<atraccio> atraccions, IActivity parcsActivity) {
		this.atraccions=atraccions;
		this.main= parcsActivity;
	}

	@Override
	public AtraccioAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
		View fila = LayoutInflater.from(viewGroup.getContext()).inflate(
				R.layout.atraccio_row, viewGroup, false);
		return new AtraccioAdapter.ViewHolder(fila);
	}

	@Override
	public void onBindViewHolder(@NonNull AtraccioAdapter.ViewHolder viewHolder, int i) {
		final atraccio a = atraccions.get(i);
		viewHolder.nom.setText(a.nom);
		ImageLoader loader = ImageLoader.getInstance();
		loader.displayImage( a.url_foto, viewHolder.foto);
		viewHolder.row.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent myIntent = new Intent(v.getContext(), ActivityAtraccio.class);
				myIntent.putExtra("ATRACCIO_ID",a.id);
				myIntent.putExtra("ATRACCIO_NOM",a.nom);
				myIntent.putExtra("ATRACCIO_URL",a.url_foto);
				v.getContext().startActivity(myIntent);
				//main.atraccioSelected(a.id);
			}
		});
	}

	@Override
	public int getItemCount() {
		return atraccions.size();
	}

	public class ViewHolder extends RecyclerView.ViewHolder {
		TextView nom;
		ImageView foto;
		ConstraintLayout row;
		public ViewHolder(View itemView) {
			super(itemView);
			nom = itemView.findViewById(R.id.nom);
			foto = itemView.findViewById(R.id.foto);
			row=itemView.findViewById(R.id.row);
		}
	}
}
