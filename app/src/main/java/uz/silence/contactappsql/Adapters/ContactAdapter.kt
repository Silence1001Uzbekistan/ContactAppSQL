package uz.silence.contactappsql.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.silence.contactappsql.CLASS.Contact
import uz.silence.contactappsql.databinding.ItemContactBinding

class ContactAdapter(var list: ArrayList<Contact>) :
    RecyclerView.Adapter<ContactAdapter.Vh>() {

    inner class Vh(var itemContactBinding: ItemContactBinding) :
        RecyclerView.ViewHolder(itemContactBinding.root) {

        fun onBind(contact: Contact) {

            itemContactBinding.nameId.text = contact.name
            itemContactBinding.numberId.text = contact.number

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemContactBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int {

        return list.size

    }

}