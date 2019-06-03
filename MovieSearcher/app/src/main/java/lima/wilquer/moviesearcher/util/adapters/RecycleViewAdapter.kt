package lima.wilquer.moviesearcher.util.adapters

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_item.view.*
import lima.wilquer.moviesearcher.R
import lima.wilquer.moviesearcher.data.models.movie.Movies
import lima.wilquer.moviesearcher.util.Constants
import lima.wilquer.moviesearcher.view.describe.DescribeMovieActivity

/**
 * Created by wilqu on 15/01/2019.
 */

class RecycleViewAdapter(val list: List<Movies>?, val context: Context) : RecyclerView.Adapter<RecycleViewAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgMovie = view.imgMovie
        val titleMovie = view.titleMovie
        val cardView = view.card_movie

        fun updateWithUrl(url: String) {
            Picasso.with(itemView.context).load(url).into(imgMovie)
        }
    }

    override fun getItemCount(): Int {
        return list!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val url = Constants.URL_IMAGE + Constants.POSTER_SIZE_RECYCLE + list!![position].poster_path
        holder.updateWithUrl(url)
        holder.titleMovie.text = list[position].title

        //onClick para descrever o filme do cardView
        holder.cardView.setOnClickListener({
            val intent = Intent(context, DescribeMovieActivity::class.java)
            intent.putExtra("id", list[position].id)
            intent.putExtra("title", list[position].title)
            startActivity(context, intent, Bundle.EMPTY)
        })
    }

    override fun onCreateViewHolder(p0: ViewGroup, position: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.movie_item, p0, false))
    }

}