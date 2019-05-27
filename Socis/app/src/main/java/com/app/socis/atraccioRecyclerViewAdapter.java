package com.app.socis;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.app.socis.atraccioFragment.OnListFragmentInteractionListener;
import com.app.socis.model.atraccio;
import com.nostra13.universalimageloader.core.ImageLoader;
import java.util.ArrayList;
import java.util.HashMap;

public class atraccioRecyclerViewAdapter extends RecyclerView.Adapter<atraccioRecyclerViewAdapter.ViewHolder> {

	private final OnListFragmentInteractionListener mListener;
	private HashMap<Integer,ArrayList<atraccio>> atraccions;
	int parcid;


	public atraccioRecyclerViewAdapter(HashMap<Integer,ArrayList<atraccio>> atraccions,int parcid, OnListFragmentInteractionListener mListener) {
		this.mListener = mListener;
		this.atraccions=atraccions;
		this.parcid=parcid;
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.fragment_atraccio_item, parent, false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(final ViewHolder holder, int position) {
		atraccio a = atraccions.get(parcid).get(position);
		holder.a=a;
		holder.nom.setText(a.nom);
		ImageLoader loader = ImageLoader.getInstance();
		loader.displayImage(a.url_foto, holder.foto);
		holder.temps.setText(a.temps_espera_minuts+"");
		switch(a.codi_estat){
			case 1: loader.displayImage("drawable://" + R.drawable.ok, holder.estat_foto);break;
			case 2: loader.displayImage("drawable://" + R.drawable.error, holder.estat_foto);break;
			case 3:case 4: loader.displayImage("drawable://" + R.drawable.forbbiden, holder.estat_foto);break;
		}
		holder.row.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (mListener!=null) {
					mListener.onListFragmentInteraction(holder.a);
				}
			}
		});
	}

	@Override
	public int getItemCount() {
		return atraccions.get(parcid).size();
	}

	public class ViewHolder extends RecyclerView.ViewHolder {
		atraccio a;
		ConstraintLayout row;
		TextView nom;
		TextView temps;
		ImageView foto;
		ImageView estat_foto;

		public ViewHolder(View view) {
			super(view);
			row=view.findViewById(R.id.row);
			nom=view.findViewById(R.id.nom);
			temps=view.findViewById(R.id.temps);
			foto=view.findViewById(R.id.foto);
			estat_foto=view.findViewById(R.id.estat_foto);
		}
	}
}
