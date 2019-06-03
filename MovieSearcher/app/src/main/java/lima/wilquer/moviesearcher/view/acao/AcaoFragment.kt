package lima.wilquer.moviesearcher.view.acao

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import kotlinx.android.synthetic.main.fragment_acao.view.*
import lima.wilquer.moviesearcher.BaseContract
import lima.wilquer.moviesearcher.R
import lima.wilquer.moviesearcher.data.models.movie.Movies
import lima.wilquer.moviesearcher.util.Constants
import lima.wilquer.moviesearcher.util.adapters.RecycleViewAdapter
import org.jetbrains.anko.support.v4.ctx

class AcaoFragment : Fragment(), BaseContract.View {

    override lateinit var presenter: BaseContract.Presenter
    var rv: RecyclerView? = null
    val moviesList = mutableListOf<Movies>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view :View = inflater.inflate(R.layout.fragment_acao, container, false)
        rv = view.recycleViewAcao
        return view
    }

    override fun onResume() {
        super.onResume()
        moviesList.clear()
        rv?.adapter?.notifyDataSetChanged()
        presenter.start()
    }

    override fun setProgress(active: Boolean) {
        if (active){
            view?.progress?.visibility = View.VISIBLE
        } else {
            view?.progress?.visibility = View.GONE
        }
    }

    override fun showMovies(movies: List<Movies>?) {

        //serve para apenas adicionar a lista de filmes, os filmes que forem correspondentes ao genero escolhido
        for (i in 0 until movies!!.size - 1) {
            for (j in 0 until movies[i].genre_ids.size - 1)
                if (Constants.ID_GENRE_ACAO == movies[i].genre_ids[j]) {
                    moviesList.add(movies[i])
                    break
                }
        }

        //lib para deixar os cardviews lado a lado
        val layoutManager = FlexboxLayoutManager(ctx)
        layoutManager.flexDirection = FlexDirection.ROW
        rv!!.layoutManager = layoutManager
        rv!!.adapter = RecycleViewAdapter(moviesList, ctx)
    }

    override fun onErrorMovies(msgError: String) {
        Toast.makeText(context, msgError, Toast.LENGTH_SHORT).show()
    }

    companion object {

        fun newInstance(): AcaoFragment {
            return AcaoFragment()
        }
    }
}