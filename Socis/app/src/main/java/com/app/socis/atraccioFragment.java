package com.app.socis;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.app.socis.model.atraccio;
import com.app.socis.model.parc;
import java.util.ArrayList;
public class atraccioFragment extends Fragment {

	public static final String ATRACCIONS = "ATRACCIONS";
	private OnListFragmentInteractionListener mListener;
	private ArrayList<atraccio> atraccions;
	private parc parc;
	private atraccioRecyclerViewAdapter adapt;

	public atraccioFragment() {
	}

	public static atraccioFragment newInstance(parc p, ArrayList<atraccio> a) {
		atraccioFragment fragment = new atraccioFragment();
		Bundle args = new Bundle();
		args.putSerializable("PARC",p);
		args.putSerializable(ATRACCIONS,a);
		//args.putInt(ARG_COLUMN_COUNT, columnCount);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			atraccions= (ArrayList<atraccio>) getArguments().getSerializable(ATRACCIONS);
			parc= (parc) getArguments().getSerializable("PARC");
			//mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_atraccio_list, container, false);

		// Set the adapter
		//if (view instanceof RecyclerView) {
			Context context = view.getContext();
			RecyclerView recyclerView = (RecyclerView) view;
			recyclerView.setLayoutManager(new LinearLayoutManager(context));
			this.adapt=new atraccioRecyclerViewAdapter(atraccions, mListener);
			recyclerView.setAdapter(this.adapt);
		//}
		return view;
	}
	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		if (context instanceof OnListFragmentInteractionListener) {
			mListener = (OnListFragmentInteractionListener) context;
		}
		else {
			throw new RuntimeException(context.toString()+ " must implement OnListFragmentInteractionListener");
		}
	}
	@Override
	public void onDetach() {
		super.onDetach();
		mListener = null;
	}

	public void update() {
		if(adapt!=null)
		adapt.notifyDataSetChanged();
	}

	public interface OnListFragmentInteractionListener {
		void onListFragmentInteraction(atraccio a);
	}
}
