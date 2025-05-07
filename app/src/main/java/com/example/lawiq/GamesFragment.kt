package com.example.navigationdrawer

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.lawiq.QuizActivity
import com.example.lawiq.R

class GamesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_games, container, false)

        view.findViewById<View>(R.id.card_union).setOnClickListener {
            navigateToQuizPage("Union and its Territory (Articles 1 - 4)")
        }

        view.findViewById<View>(R.id.card_Citizenship).setOnClickListener {
            navigateToQuizPage("Citizenship (Articles 5 – 11)")
        }

        view.findViewById<View>(R.id.card_Fundamental_Rights).setOnClickListener {
            navigateToQuizPage("Fundamental Rights (Articles 12 – 35)")
        }

        view.findViewById<View>(R.id.card_Directive_Principles).setOnClickListener {
            navigateToQuizPage("Directive Principles of State Policy (Articles 36 – 51)")
        }

        view.findViewById<View>(R.id.card_Fundamental_Duties).setOnClickListener {
            navigateToQuizPage("Fundamental Duties (Article 51A)")
        }

        view.findViewById<View>(R.id.card_The_Union).setOnClickListener {
            navigateToQuizPage("Union Government (Articles 52 – 78)")
        }

        view.findViewById<View>(R.id.card_The_States).setOnClickListener {
            navigateToQuizPage("State Government (Articles 153 – 167)")
        }

        view.findViewById<View>(R.id.card_Union_Territories).setOnClickListener {
            navigateToQuizPage("Union Territories (Articles 239 – 242)")
        }

        view.findViewById<View>(R.id.card_Panchayats).setOnClickListener {
            navigateToQuizPage("Panchayats (Articles 243 – 243O)")
        }

        view.findViewById<View>(R.id.card_Municipalities).setOnClickListener {
            navigateToQuizPage("Municipalities (Articles 243P – 243ZG)")
        }

        view.findViewById<View>(R.id.card_Cooperative_Societies).setOnClickListener {
            navigateToQuizPage("Co-operative Societies (Articles 243ZH – 243ZT)")
        }

        view.findViewById<View>(R.id.card_Tribunals).setOnClickListener {
            navigateToQuizPage("Tribunals (Articles 323A – 323B)")
        }

        view.findViewById<View>(R.id.card_Finance_Property_Contracts).setOnClickListener {
            navigateToQuizPage("Finance, Property, Contracts and Suits (Articles 264 – 300A)")
        }

        view.findViewById<View>(R.id.card_Trade_Commerce).setOnClickListener {
            navigateToQuizPage("Trade and Commerce (Articles 301 – 307)")
        }

        view.findViewById<View>(R.id.card_Elections).setOnClickListener {
            navigateToQuizPage("Elections (Articles 324 – 329A)")
        }

        view.findViewById<View>(R.id.card_Special_Provisions).setOnClickListener {
            navigateToQuizPage("Special Provisions relating to certain classes (Articles 330 – 342)")
        }

        view.findViewById<View>(R.id.card_Scheduled_Tribal_Areas).setOnClickListener {
            navigateToQuizPage("Scheduled and Tribal Areas (Articles 244 – 244A)")
        }

        view.findViewById<View>(R.id.card_Relations_Union_States).setOnClickListener {
            navigateToQuizPage("Relations between the Union and the States (Articles 245 – 263)")
        }

        view.findViewById<View>(R.id.card_Official_Language_Union).setOnClickListener {
            navigateToQuizPage("Official Language (Articles 343 – 351)")
        }

        view.findViewById<View>(R.id.card_Miscellaneous).setOnClickListener {
            navigateToQuizPage("Miscellaneous (Articles 352 – 367)")
        }

        view.findViewById<View>(R.id.card_Temporary_Transitional_Special_Provisions).setOnClickListener {
            navigateToQuizPage("Temporary, Transitional and Special Provisions (Articles 369 – 392)")
        }

        view.findViewById<View>(R.id.card_Part_XXII).setOnClickListener {
            navigateToQuizPage("Short title, Commencement, Authoritative Text in Hindi and Repeals (Articles 393 – 395)")
        }

        view.findViewById<View>(R.id.card_Amendments).setOnClickListener {
            navigateToQuizPage("Amendment of the Constitution (Article 368)")
        }

        return view
    }

    private fun navigateToQuizPage(sectionName: String) {
        val intent = Intent(requireContext(), QuizActivity::class.java)
        intent.putExtra("section_name", sectionName)
        startActivity(intent)
    }
}
