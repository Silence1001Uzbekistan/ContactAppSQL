package uz.silence.contactappsql

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import uz.silence.contactappsql.Adapters.ContactAdapter
import uz.silence.contactappsql.CLASS.Contact
import uz.silence.contactappsql.DB.MyDbHelper
import uz.silence.contactappsql.databinding.FragmentAddBinding

class AddFragment : Fragment() {

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!
    lateinit var myDbHelper: MyDbHelper
    lateinit var list: ArrayList<Contact>
    lateinit var contactAdapter: ContactAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAddBinding.inflate(inflater, container, false)

        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        list = ArrayList()
        myDbHelper = MyDbHelper(context!!)

        binding.addContacts.setOnClickListener {

            if (binding.contactName.text.trim().isNotEmpty() && binding.contactNumber.text.trim()
                    .isNotEmpty()
            ) {

                val name = binding.contactName.text.toString()
                val number = binding.contactNumber.text.toString()

                val contact = Contact(name, number)

                myDbHelper.addContact(contact)
                list.add(contact)
                Snackbar.make(it,"Save",Snackbar.LENGTH_LONG).show()
                findNavController().popBackStack()


            }else{
                Snackbar.make(it,"Enter correctly",Snackbar.LENGTH_LONG).show()
            }

        }

    }

}