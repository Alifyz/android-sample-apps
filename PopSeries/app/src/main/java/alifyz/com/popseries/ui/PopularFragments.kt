package alifyz.com.popseries.ui


import alifyz.com.popseries.R
import alifyz.com.popseries.adapter.HomeSeriesAdapter
import alifyz.com.popseries.arch.PopularPresenter
import alifyz.com.popseries.arch.PopularUIContract
import alifyz.com.popseries.model.SeriesModel
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_popular.*
import retrofit2.Response

class PopularFragments : Fragment(), PopularUIContract.View {


    override lateinit var presenter : PopularPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_popular, container, false)
    }

    override fun onResume() {
        super.onResume()
        presenter = PopularPresenter(this)
        presenter.start()
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

    override fun showErrorScreen() {
        TODO("Not implemented")
    }

    override fun setLoadingIndicator(active: Boolean) {
        if(active) {
            progress.visibility = View.VISIBLE
            recyclerview_popular.visibility = View.GONE
        } else {
            progress.visibility = View.GONE
            recyclerview_popular.visibility = View.VISIBLE
        }
    }

    override fun setAdapter(response: Response<SeriesModel>?) {
        val gridLayout = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
        recyclerview_popular.layoutManager = gridLayout
        recyclerview_popular.adapter = HomeSeriesAdapter(context!!, response?.body()!!)
    }

}