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
public class atraccioRecyclerViewAdapter extends RecyclerView.Adapter<atraccioRecyclerViewAdapter.ViewHolder> {

	private final OnListFragmentInteractionListener mListener;
	private ArrayList<atraccio> atraccions=new ArrayList<atraccio>();


	public atraccioRecyclerViewAdapter(ArrayList<atraccio> atraccions, OnListFragmentInteractionListener mListener) {
		this.mListener = mListener;
		this.atraccions=atraccions;
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.fragment_atraccio_item, parent, false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(final ViewHolder holder, int position) {
		atraccio a = atraccions.get(position);
		holder.nom.setText(a.nom);
		ImageLoader loader = ImageLoader.getInstance();
		loader.displayImage(a.url_foto, holder.foto);

		holder.row.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (mListener!=null) {
					// Notify the active callbacks interface (the activity, if the
					// fragment is attached to one) that an item has been selected.
					mListener.onListFragmentInteraction(holder.a);
				}
			}
		});
	}

	@Override
	public int getItemCount() {

		return atraccions.size();
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
