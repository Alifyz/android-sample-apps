package alifyz.com.horizontalviewpager;


import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.omega_r.libs.omegarecyclerview.OmegaRecyclerView;


import java.util.ArrayList;

public class RecyclerViewAdapter extends OmegaRecyclerView.Adapter<ViewHolder> {

    private Context mContext;
    private ArrayList<Integer> mResourceId;
    private LayoutInflater mInflater;

    public RecyclerViewAdapter(Context mContext, ArrayList<Integer> mResourceId) {
        this.mContext = mContext;
        this.mResourceId = mResourceId;
        mInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = mInflater.inflate(R.layout.recycler_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.image.setImageResource(mResourceId.get(position));
    }

    @Override
    public int getItemCount() {
        return mResourceId.size();
    }
}
