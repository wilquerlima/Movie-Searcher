package lima.wilquer.moviesearcher

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import java.util.*
import kotlinx.android.synthetic.main.movie_item.view.*
import lima.wilquer.moviesearcher.data.models.movie.Movies
import lima.wilquer.moviesearcher.util.Constants

/**
 * Created by wilqu on 15/01/2019.
 */

class RecycleViewAdapter(/*val list: ArrayList<Movies>,*/ val context: Context) : RecyclerView.Adapter<RecycleViewAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgMovie = view.imgMovie
        val titleMovie = view.titleMovie

        fun updateWithUrl(url: String) {
            Picasso.with(itemView.context).load(url).into(imgMovie)
        }
    }

    override fun getItemCount(): Int {
        return 1//list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, p1: Int) {
        val url = Constants.URL_IMAGE + Constants.POSTER_SIZE_RECYCLE + "kqjL17yufvn9OVLyXYpvtyrFfak.jpg" //list[p1].poster_path
        holder.updateWithUrl(url)
        holder.titleMovie.text = "Mad Max"//list[p1].title
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.movie_item, p0, false))
    }

}