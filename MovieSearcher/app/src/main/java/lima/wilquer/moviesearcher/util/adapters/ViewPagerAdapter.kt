package lima.wilquer.moviesearcher.util.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import lima.wilquer.moviesearcher.view.fragments.AcaoFragment
import lima.wilquer.moviesearcher.view.fragments.DramaFragment
import lima.wilquer.moviesearcher.view.fragments.FantasiaFragment
import lima.wilquer.moviesearcher.view.fragments.FiccaoFragment

/**
 * Created by wilqu on 15/01/2019.
 */
class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(p0: Int): Fragment {
        return when (p0) {
            0 -> AcaoFragment()
            1 -> DramaFragment()
            2 -> FantasiaFragment()
            else -> FiccaoFragment()
        }
    }

    override fun getCount(): Int {
        return 4
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Ação"
            1 -> "Drama"
            2 -> "Fantasia"
            else -> "Ficção"
        }
    }
}