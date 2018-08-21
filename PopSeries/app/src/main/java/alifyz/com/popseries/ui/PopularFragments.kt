package alifyz.com.popseries.ui

import alifyz.com.popseries.R
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


class PopularFragments : Fragment() {

    val gridLayout =
            GridLayoutManager(
                    context,
                    2,
                    GridLayoutManager.VERTICAL,
                    false)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_popular, container, false)




        return rootView
    }
}