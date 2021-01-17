package com.jhoander.therickandmorty.presentation.adapter

import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.jhoander.therickandmorty.R
import com.jhoander.therickandmorty.domain.model.Character
import com.jhoander.therickandmorty.utils.extension.inflate
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_character_list.view.*

class CharacterListAdapter :
    RecyclerView.Adapter<CharacterListAdapter.ViewHolder>() {

    private lateinit var characters: List<Character>

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CharacterListAdapter.ViewHolder {
        return ViewHolder(
            parent.inflate(R.layout.item_character_list)
        )
    }

    override fun getItemCount(): Int {
        return characters.size
    }

    override fun onBindViewHolder(holder: CharacterListAdapter.ViewHolder, position: Int) {
        holder.initView(characters[position], position)
    }

    fun setList(articles: List<Character>) {
        this.characters = articles
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun initView(item: Character?, pos: Int) {
            if (item == null) {
                itemView.visibility = View.GONE
                return
            }
            if (item.status.equals("Alive")) {
                itemView.tvStatusIndicator.setTextColor(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.colorGreen
                    )
                )
            } else if (item.status.equals("Dead")) {
                itemView.tvStatusIndicator.setTextColor(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.colorRed
                    )
                )
            }
            itemView.tvName.text = item.name
            itemView.tvGender.text = item.gender
            itemView.tvSpecie.text = item.species
            itemView.tvStatus.text = item.status

            item.image?.let {
                if (itemView.image.tag == it) {
                    return@let
                }
                Picasso.get()
                    .load(it)
                    .into(itemView.image)

                itemView.image.tag = it
            }
        }
    }
}