package open.git.votingmainpainel

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.voteindia.Item
import com.example.voteindia.R

var tempInfo=ArrayList<Item>(0)
var tempPosition:Int=0
class adapter(val info:ArrayList<Item>):RecyclerView.Adapter<adapter.ViewHanger>(){


    inner class ViewHanger(val itemview:View):RecyclerView.ViewHolder(itemview){

        var NameofCandidate=itemview.findViewById<TextView>(R.id.name_of_candidate)
        var NameofParty=itemview.findViewById<TextView>(R.id.name_of_party)
        var PictureofCandidate=itemview.findViewById<ImageView>(R.id.photo_of_candidate)
        var LogoofParty=itemview.findViewById<ImageView>(R.id.photo_of_party)

        init {
            for(i in 0..2){
                tempInfo.add(Item(info[i].CandidateName,info[i].PartyName,info[i].CandidatePhoto,info[i].PartyLogo))
            }
        }
        init{
            itemview.setOnClickListener(){
                tempPosition=adapterPosition

                Toast.makeText(itemview.context,"You want to vote ${info[tempPosition].CandidateName} with candidate number $tempPosition",Toast.LENGTH_LONG).show()
                tempInfo.add(Item(info[tempPosition].CandidateName,info[tempPosition].PartyName,info[tempPosition].CandidatePhoto,info[tempPosition].PartyLogo))
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHanger {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.each_cand_view,parent,false)
        return ViewHanger(view)
    }

    override fun onBindViewHolder(holder: ViewHanger, position: Int) {
        val currentItem=info[position]
        holder.NameofCandidate.text=currentItem.CandidateName
        holder.NameofParty.text=currentItem.PartyName
        Glide.with(holder.LogoofParty.getContext())
            .load(currentItem.PartyLogo)
            .into(holder.LogoofParty)
        Glide.with(holder.PictureofCandidate.getContext())
            .load(currentItem.CandidatePhoto)
            .into(holder.PictureofCandidate);

    }

    override fun getItemCount(): Int=info.size
}