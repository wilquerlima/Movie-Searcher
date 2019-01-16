package lima.wilquer.moviesearcher

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.util.*
import kotlinx.android.synthetic.main.movie_item.view.*
import lima.wilquer.moviesearcher.data.models.movie.Movies

/**
 * Created by wilqu on 15/01/2019.
 */

class RecycleViewAdapter(val list : ArrayList<Movies>, val context : Context) : RecyclerView.Adapter<RecycleViewAdapter.ViewHolder>() {

    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        val imgMovie = view.imgMovie
        val titleMovie = view.titleMovie
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.titleMovie.text = list.get(p1).title
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.movie_item, p0, false))
    }

}