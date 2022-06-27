package uz.silence.contactappsql

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.silence.contactappsql.Adapters.ContactAdapter
import uz.silence.contactappsql.CLASS.Contact
import uz.silence.contactappsql.DB.MyDbHelper
import uz.silence.contactappsql.databinding.FragmentListBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListFragment : Fragment() {

    private var _binding:FragmentListBinding? = null
    private val binding get() = _binding!!

    lateinit var myDbHelper: MyDbHelper
    lateinit var contactAdapter: ContactAdapter
    lateinit var list: ArrayList<Contact>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentListBinding.inflate(inflater,container,false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        myDbHelper = MyDbHelper(context!!)

        list = myDbHelper.getAllContacts()
        contactAdapter = ContactAdapter(list)

        binding.rv.adapter = contactAdapter


    }


}