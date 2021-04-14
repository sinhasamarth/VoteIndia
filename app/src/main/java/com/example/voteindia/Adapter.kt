package open.git.votingmainpainel

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.voteindia.MainActivity
import com.example.voteindia.Maindata
import com.example.voteindia.ShowVoting.Companion.checkBtn
import com.example.voteindia.ShowVoting.Companion.resetColor
import com.example.voteindia.isselected
import com.example.voteindia.selectedno
import com.example.voteindia.*

class adapter(val datas:ArrayList<Maindata>):RecyclerView.Adapter<viewHolderMain>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolderMain {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.each_cand_view,
            parent,
            false
        )
        return viewHolderMain(view)
    }

    override fun onBindViewHolder(holder: viewHolderMain, position: Int) {
        holder.participantName.text = datas[position].CandName
        holder.partyName.text = datas[position].PartyName
        Glide.with(holder.partyLogo.context).load(datas[2]).into(holder.partyLogo)
        Glide.with(holder.candidateImage.context).load(datas[2]).into(holder.candidateImage)
    }

    override fun getItemCount(): Int = datas.size
}
class viewHolderMain(val itemview: View):RecyclerView.ViewHolder(itemview){
    val participantName = itemview.findViewById<TextView>(R.id.participantNameText)
    val partyName = itemview.findViewById<TextView>(R.id.partyNameText)
    val partyLogo = itemview.findViewById<ImageView>(R.id.partyImage)
    val candidateImage: ImageView = itemview.findViewById(R.id.CandidatePhoto)

    init {
        itemview.setOnClickListener{ v :View ->

            if (selectedno != -1)
                ShowVoting.resetColor(selectedno)
            selectedno = adapterPosition
            val cardviewMain:LinearLayout = itemview.findViewById(R.id.cardviewMain)
            cardviewMain.setBackgroundColor(Color.CYAN)
            isselected = true
            ShowVoting.checkBtn()
        }
    }


}