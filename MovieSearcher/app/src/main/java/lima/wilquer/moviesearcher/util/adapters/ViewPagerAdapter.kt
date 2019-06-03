package lima.wilquer.moviesearcher.util.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * Created by wilqu on 15/01/2019.
 */
class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    val listFragments = mutableListOf<Fragment>()

    override fun getItem(p0: Int): Fragment {
        return listFragments[p0]
    }

    override fun getCount(): Int {
        return listFragments.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Ação"
            1 -> "Drama"
            2 -> "Fantasia"
            3 -> "Ficção"
            else -> "Error"
        }
    }

    fun addFragment(fragment: Fragment){
        listFragments.add(fragment)
    }
}