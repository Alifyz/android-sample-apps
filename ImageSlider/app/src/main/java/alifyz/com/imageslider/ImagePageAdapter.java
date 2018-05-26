package alifyz.com.imageslider;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class ImagePageAdapter extends PagerAdapter {

    Context mContext;
    int[] mResourcesId;
    LayoutInflater inflater;

    public ImagePageAdapter(Context mContext, int[] mResourcesId) {
        this.mContext = mContext;
        this.mResourcesId = mResourcesId;
        inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mResourcesId.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        ImageView imageView;
        View view = inflater.inflate(R.layout.pager_item, container, false);
        imageView = view.findViewById(R.id.pager_item);


        imageView.setImageResource(mResourcesId[position]);
        ((ViewPager) container).addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ((ViewPager) container).removeView((ImageView) object);
    }
}
