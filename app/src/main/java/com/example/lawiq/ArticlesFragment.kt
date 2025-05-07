package com.example.lawiq

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class ArticlesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_article, container, false)

        // Get references to the card views
        val card1 = view.findViewById<CardView>(R.id.card1)
        val card2 = view.findViewById<CardView>(R.id.card2)
        val card3 = view.findViewById<CardView>(R.id.card3)
        val card4 = view.findViewById<CardView>(R.id.card4)
        val card5 = view.findViewById<CardView>(R.id.card5)
        val card6 = view.findViewById<CardView>(R.id.card6)
        val card7 = view.findViewById<CardView>(R.id.card7)
        val card8 = view.findViewById<CardView>(R.id.card8)
        val card9 = view.findViewById<CardView>(R.id.card9)
        val card10 = view.findViewById<CardView>(R.id.card10)
        val card11 = view.findViewById<CardView>(R.id.card11)
        val card12 = view.findViewById<CardView>(R.id.card12)
        val card13 = view.findViewById<CardView>(R.id.card13)
        val card14 = view.findViewById<CardView>(R.id.card14)
        val card15 = view.findViewById<CardView>(R.id.card15)
        val card16 = view.findViewById<CardView>(R.id.card16)
        val card17 = view.findViewById<CardView>(R.id.card17)
        val card18 = view.findViewById<CardView>(R.id.card18)
        val card19 = view.findViewById<CardView>(R.id.card19)
        val card20 = view.findViewById<CardView>(R.id.card20)
        val card21 = view.findViewById<CardView>(R.id.card21)
        val card22 = view.findViewById<CardView>(R.id.card22)
        val card23 = view.findViewById<CardView>(R.id.card23)

        // Set click listeners for each card
        card1.setOnClickListener { navigateToFragment(UnionAndTerritoryFragment()) }
        card2.setOnClickListener { navigateToFragment(CitizenshipFragment()) }
        card3.setOnClickListener { navigateToFragment(FundamentalRightsFragment()) }
        card4.setOnClickListener { navigateToFragment(DirectivePrinciplesFragment()) }
        card5.setOnClickListener { navigateToFragment(UnionGovtFragment()) }
        card6.setOnClickListener { navigateToFragment(StateGovtFragment()) }
        card7.setOnClickListener { navigateToFragment(UnionTerritoriesFragment()) }
        card8.setOnClickListener { navigateToFragment(PanchayatsFragment()) }
        card9.setOnClickListener { navigateToFragment(MunicipalitiesFragment()) }
        card10.setOnClickListener { navigateToFragment(CooperativeSocietiesFragment()) }
        card11.setOnClickListener { navigateToFragment(ScheduledAndTribalAreasFragment()) }
        card12.setOnClickListener { navigateToFragment(RelationsUnionStatesFragment()) }
        card13.setOnClickListener { navigateToFragment(FinancePropertyContractsFragment()) }
        card14.setOnClickListener { navigateToFragment(TradeCommerceIntercourseFragment()) }
        card15.setOnClickListener { navigateToFragment(ServicesUnionStatesFragment()) }
        card16.setOnClickListener { navigateToFragment(TribunalsFragment()) }
        card17.setOnClickListener { navigateToFragment(ElectionsFragment()) }
        card18.setOnClickListener { navigateToFragment(SpecialProvisionsFragment()) }
        card19.setOnClickListener { navigateToFragment(OfficialLanguageFragment()) }
        card20.setOnClickListener { navigateToFragment(EmergencyFragment()) }
        card21.setOnClickListener { navigateToFragment(MiscellaneousFragment()) }
        card22.setOnClickListener { navigateToFragment(ShortTitleCommencementFragment()) }
//        card23.setOnClickListener { navigateToFragment(Fragment23()) }

        return view
    }

    private fun navigateToFragment(fragment: Fragment) {
        val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.addToBackStack(null) // Add to back stack to navigate back
        transaction.commit()
    }
}
