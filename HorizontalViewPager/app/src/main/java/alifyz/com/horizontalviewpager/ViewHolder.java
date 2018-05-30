package alifyz.com.horizontalviewpager;

import android.view.View;
import android.widget.ImageView;

import com.omega_r.libs.omegarecyclerview.OmegaRecyclerView;

public class ViewHolder extends OmegaRecyclerView.ViewHolder{

    ImageView image;

    public ViewHolder(View itemView) {
        super(itemView);
        image = itemView.findViewById(R.id.image);
    }
}
