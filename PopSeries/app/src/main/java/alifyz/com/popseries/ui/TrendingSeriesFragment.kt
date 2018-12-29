package alifyz.com.popseries.ui

import alifyz.com.popseries.R
import alifyz.com.popseries.adapter.HomeSeriesAdapter
import alifyz.com.popseries.arch.TrendingContract
import alifyz.com.popseries.arch.TrendingPresenter
import alifyz.com.popseries.model.SeriesModel
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_top.*
import retrofit2.Response

class TrendingSeriesFragment : Fragment(), TrendingContract.View {

    override lateinit var presenter: TrendingPresenter
    val gridLayout = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_top, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = TrendingPresenter(this)
        presenter.start()
    }


    override fun setLoadingIndicator(active: Boolean) {
        if(active) {
            progress.visibility = View.VISIBLE
            recyclerview_top.visibility = View.GONE
        } else {
            progress.visibility = View.GONE
            recyclerview_top.visibility = View.VISIBLE
        }
    }

    override fun showEmptyContent() {
        TODO("not implemented")
    }

    override fun showOffline() {
        TODO("not implemented")
    }

    override fun showSavedAlert() {
        TODO("not implemented")
    }

    override fun setAdapter(response: Response<SeriesModel>?) {
        recyclerview_top?.let {
            it.layoutManager = gridLayout
            it.adapter = HomeSeriesAdapter(context!!, response!!.body()!!)
        }
    }

    override fun showErrorScreen() {
        TODO("not implemented")
    }

}