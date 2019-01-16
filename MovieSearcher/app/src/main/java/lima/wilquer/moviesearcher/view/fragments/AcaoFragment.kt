package lima.wilquer.moviesearcher.view.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import lima.wilquer.moviesearcher.R
import kotlinx.android.synthetic.main.fragment_acao.*
import kotlinx.android.synthetic.main.fragment_acao.view.*
import lima.wilquer.moviesearcher.RecycleViewAdapter
import lima.wilquer.moviesearcher.data.models.movie.Movies

/**
 * Created by wilqu on 15/01/2019.
 */

class AcaoFragment : Fragment() {

    var ctx : Context? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view:View = inflater.inflate(R.layout.fragment_acao, container, false)

        var rv : RecyclerView = view.recycleViewAcao

        rv.layoutManager = LinearLayoutManager(ctx)
        rv.adapter = RecycleViewAdapter(ctx!!)

        return view
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        ctx = context
    }

}